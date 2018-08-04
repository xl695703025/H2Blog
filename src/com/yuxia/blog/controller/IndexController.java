package com.yuxia.blog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("index")
public class IndexController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private TagService tagService;

	@RequestMapping(value = ("{userName}"), method = RequestMethod.GET)
	public ModelAndView index(@PathVariable String userName) {
		ModelAndView mav = new ModelAndView();
		User user=userService.getUserInfo(userName);
		if(user==null){
			mav.setViewName("redirect:/404");
			return mav;
		}
		mav.addObject("user", user);
		// 博文总数
		Integer articleCount = articleService.getArticleCount(userName);
		mav.addObject("articleCount", articleCount);
		// 点赞数
		Integer likeCount = articleService.getArticleLikeCount(userName);
		if (likeCount == null)// 数据库用sum()计算的，可能为空，文章数是用count()计数，不会为空
			likeCount = 0;
		mav.addObject("likeCount", likeCount);
		// 浏览次数
		Integer viewCount = articleService.getArticleViewCount(userName);
		if (viewCount == null)
			viewCount = 0;
		mav.addObject("viewCount", viewCount);
		// 留言总数
		Integer commentCount = articleService.getArticleCommentCount(userName);
		if (commentCount == null)
			commentCount = 0;
		mav.addObject("commentCount", commentCount);
		// 获取分类列表
		List<Category> categoryList = categoryService.getAllCategory(userName);
		mav.addObject("categoryList", categoryList);
		mav.setViewName("user");
		Page page=new Page();
		page.setPageNow(1);
		page.setPageSize(10);
		List<Article> articles=articleService.getArticles(userName, page);
		Map<Integer,String> imgMap=new HashMap<Integer,String>();
		List<List<String>> TagsList=new ArrayList<List<String>>();
		for(Article a:articles){
			imgMap.put(a.getArticleId(), HtmlParser.getImg(a.getArticleContent()));
			TagsList.add(Arrays.asList(a.getArticleTagIds().split(",|，")));
			a.setArticleContent(HtmlParser.parseHtml(a.getArticleContent(), 200));
		}
		String userHotAArticles="hotArticles"+user.getUserName();
		List<Article> hotArticles = new ArrayList<Article>();
		Jedis jedis=new Jedis("localhost");
		jedis.auth("970713");
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
		jedis.close();
		return mav;
	}
}
