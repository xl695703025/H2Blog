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
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private TagService tagService;
	/**
	 * 获取分类列表
	 */
	@RequestMapping("manage")
	public ModelAndView getArticleByCategory(HttpSession session, Category category) {
		ModelAndView mav = new ModelAndView();
		// 获取用户
		User user = (User) session.getAttribute("user");
		// 获取分类列表
		List<Category> categoryList = categoryService.getAllCategory(user.getUserName());
		mav.addObject("categoryList", categoryList);
		mav.setViewName("categoryManage");
		mav.addObject("category", category);
		return mav;
	}

	/**
	 * 修改分类
	 */
	@RequestMapping(value = "updateCategory", method = RequestMethod.POST)
	@ResponseBody
	public Object updateCategory(Category category, HttpSession session) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String userName = ((User) session.getAttribute("user")).getUserName();
		int res = categoryService.updateCategory(category, userName);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}

	/**
	 * 添加分类页面
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView addCategory(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ModelAndView mav = new ModelAndView();
		// 获取分类列表
		List<Category> categoryList = categoryService.getAllCategory(user.getUserName());
		mav.addObject("categoryList", categoryList);
		mav.setViewName("addCategory");
		return mav;
	}

	/**
	 * 添加分类
	 */
	@RequestMapping(value = "addCategory", method = RequestMethod.POST)
	@ResponseBody
	public Object addCategory(String pCategoryName, String cCategoryName, String categoryDescription,
			HttpSession session) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		int res = categoryService.addCategory(user, pCategoryName, cCategoryName, categoryDescription);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}

	/**
	 * 根据id删除分类
	 */
	@RequestMapping(value = "delCategory", method = RequestMethod.POST)
	@ResponseBody
	public Object delCategory(Integer categoryId, HttpSession session) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		int res = categoryService.delCategoryById(categoryId);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 文章分类页面
	 */
	@RequestMapping(value = ("{userName}/{categoryId}"), method = RequestMethod.GET)
	public ModelAndView index(@PathVariable String userName,@PathVariable Integer categoryId) {
		ModelAndView mav = new ModelAndView();
		User user=userService.getUserInfo(userName);
		Category category=categoryService.getCategoryById(categoryId);
		mav.addObject("category", category);
		mav.addObject("user", user);
		// 获取分类列表
		List<Category> categoryList = categoryService.getAllCategory(userName);
		mav.addObject("categoryList", categoryList);
		Page page=new Page();
		page.setPageNow(1);
		page.setPageSize(Integer.MAX_VALUE);
		List<Article> articles=articleService.getArticlesByCategoryId(userName, page, categoryId);
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
		mav.setViewName("category");
		jedis.close();
		return mav;
	}
}
