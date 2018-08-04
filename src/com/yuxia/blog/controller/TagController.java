package com.yuxia.blog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.Category;
import com.yuxia.blog.entity.Tag;
import com.yuxia.blog.entity.User;
import com.yuxia.blog.other.HtmlParser;
import com.yuxia.blog.other.Page;
import com.yuxia.blog.service.ArticleService;
import com.yuxia.blog.service.CategoryService;
import com.yuxia.blog.service.TagService;
import com.yuxia.blog.service.UserService;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("tag")
public class TagController {

	@Autowired
	TagService tagService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	/**
	 * 标签管理
	 */
	@RequestMapping("manage")
	public ModelAndView getArticleByCategory(HttpSession session,Category category){
		ModelAndView mav=new ModelAndView();
		//获取用户
		User user = (User) session.getAttribute("user");
		//获取分类列表
		List<Category> categoryList=categoryService.getAllCategory(user.getUserName());
		mav.addObject("categoryList",categoryList);
		//获取标签列表
		List<Tag> tagList=tagService.getTagList(user.getUserName());
		mav.addObject("tagList",tagList);
		mav.setViewName("tagManage");
		mav.addObject("category", category);
		return mav;
	}
	/**
	 * 根据id删除Tag
	 * 
	 */
	@RequestMapping(value = "delTag", method = RequestMethod.POST)
	@ResponseBody
	public Object delComment(Integer tagId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		int res = tagService.delTagById(tagId);
		if (res > 0) {
			hashMap.put("res", true);
		} else {
			hashMap.put("res", false);
		}
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 修改标签
	 */
	@RequestMapping(value = "updateTag", method = RequestMethod.POST)
	@ResponseBody
	public Object addComment(Integer tagId,String tagName, String tagDescription) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		int res=tagService.updateTag(tagId, tagName, tagDescription);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 添加标签页面
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView addTag(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ModelAndView mav = new ModelAndView();
		// 获取分类列表
		List<Category> categoryList = categoryService.getAllCategory(user.getUserName());
		mav.addObject("categoryList", categoryList);
		mav.setViewName("addTag");
		return mav;
	}
	/**
	 * 添加标签
	 */
	@RequestMapping(value = "addTag", method = RequestMethod.POST)
	@ResponseBody
	public Object addTag(HttpSession session, String tagName, String tagDescription) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();
		int res = tagService.addTag(userName, tagName, tagDescription);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 标签文章列表页面
	 */
	@RequestMapping(value = ("{userName}/{tagName}"), method = RequestMethod.GET)
	public ModelAndView index(@PathVariable String userName,@PathVariable String tagName) {
		ModelAndView mav = new ModelAndView();
		User user=userService.getUserInfo(userName);
		mav.addObject("user", user);
		Tag tag=tagService.getTagByName(userName, tagName);
		mav.addObject("tag",tag);
		// 获取分类列表
		List<Category> categoryList = categoryService.getAllCategory(userName);
		mav.addObject("categoryList", categoryList);
		Page page=new Page();
		page.setPageNow(1);
		page.setPageSize(Integer.MAX_VALUE);
		List<Article> articles=articleService.getArticlesByTagId(userName, page, tag.getTagId());
		Map<Integer,String> imgMap=new HashMap<Integer,String>();
		List<List<String>> TagsList=new ArrayList<List<String>>();
		for(Article a:articles){
			imgMap.put(a.getArticleId(), HtmlParser.getImg(a.getArticleContent()));
			TagsList.add(Arrays.asList(a.getArticleTagIds().split(",|，")));
			a.setArticleContent(HtmlParser.parseHtml(a.getArticleContent(), 200));
		}
		List<Article> hotArticles = new ArrayList<Article>();
		Jedis jedis=new Jedis("localhost");
		jedis.auth("970713");
		String userHotAArticles="hotArticles"+user.getUserName();
		if(jedis.exists(userHotAArticles)){
			for(String s:jedis.zrevrange(userHotAArticles, 0, 10)){
				hotArticles.add(JSON.parseObject(s,Article.class));
			}
		}else{
			hotArticles=articleService.getHotArticles(userName);
			for(Article a:hotArticles){
				jedis.zadd(userHotAArticles, a.getArticleViewCount(),JSON.toJSONString(a));
			}
		}
		mav.addObject("hotArticles", hotArticles);
		List<Tag> hotTags=tagService.getHotTags(userName);
		mav.addObject("hotTags", hotTags);
		mav.addObject("TagsList", TagsList);
		mav.addObject("imgMap", imgMap);
		mav.addObject("articles", articles);
		mav.setViewName("tag");
		jedis.close();
		return mav;
	}
}
