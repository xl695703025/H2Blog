package com.yuxia.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.Category;
import com.yuxia.blog.entity.Tag;
import com.yuxia.blog.entity.User;
import com.yuxia.blog.other.Page;
import com.yuxia.blog.service.ArticleService;
import com.yuxia.blog.service.CategoryService;
import com.yuxia.blog.service.TagService;
import com.yuxia.blog.service.UserService;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private TagService tagService;
	/**
	 * 文章管理页面
	 */
	@RequestMapping("manage")
	public ModelAndView getArticleByCategory(HttpSession session,Page page,Category category){
		ModelAndView mav=new ModelAndView();
		//获取用户
		User user = (User) session.getAttribute("user");
		//获取分类列表
		List<Category> categoryList=categoryService.getAllCategory(user.getUserName());
		mav.addObject("categoryList",categoryList);
		//获取该分类的文章
		List<Article> articleList=articleService.getArticlesByCategoryId(user.getUserName(), page, category.getCategoryId());
		//设置总共文章条数
		page.setTotalCount(articleService.getArticleCountByCategory(user.getUserName(), category.getCategoryId()));
		mav.addObject(articleList);
		mav.setViewName("articleManage");
		mav.addObject("page", page);
		category=categoryService.getCategoryById(category.getCategoryId());
		mav.addObject("category", category);
		return mav;
	}
	/**
	 * 根据文章Id删除文章
	 */
	@RequestMapping(value = "delArticle", method = RequestMethod.POST)
	@ResponseBody
	public Object delArticle(Integer articleId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		int res = articleService.delArticle(articleId);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}	
	/**
	 * 根据Id获取文章
	 */
	@RequestMapping(value = "getArticleById", method = RequestMethod.POST)
	@ResponseBody
	public Object getArticlesByCategory(Integer id) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		Article article=articleService.getArticleById(id);
		hashMap.put("article", article);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 修改文章
	 */
	@RequestMapping(value = "updateArticle", method = RequestMethod.POST)
	@ResponseBody
	public Object updateArticle(HttpSession session, Article article,
			String pCategory, String cCategory,String tags) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		int res=articleService.updateArticle(article, user, pCategory, cCategory);
		hashMap.put("res", res);
		Jedis jedis=new Jedis("localhost");
		jedis.auth("970713");
		jedis.del("hotArticles"+user.getUserName());
		jedis.close();
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 上传文章图片
	 */
	@RequestMapping(value = "uploadImg", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadImg(HttpServletRequest request, @RequestParam("img") MultipartFile img) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		List<String> imgFile = new ArrayList<String>();
		// 获取路径
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadImg");
		// 随机生成文件名
		String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + ".jpg";
		// 生成目标文件
		File targetFile = new File(path, fileName);
		// 上传图片
		try {
			img.transferTo(targetFile);
			imgFile.add("http://www.h2blog.xyz/statics/uploadImg/" + fileName);
			hashMap.put("data", imgFile);
			hashMap.put("errno", 0);
		} catch (IOException e) {
			hashMap.put("errno", 1);
			e.printStackTrace();
		}
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 添加文章页面
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView addCategory(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ModelAndView mav = new ModelAndView();
		// 获取分类列表
		List<Category> categoryList = categoryService.getAllCategory(user.getUserName());
		mav.addObject("categoryList", categoryList);
		mav.setViewName("addArticle");
		return mav;
	}
	/**
	 * 添加文章
	 */
	@RequestMapping(value = "addArticle", method = RequestMethod.POST)
	@ResponseBody
	public Object addArticle(HttpSession session, Article article,
			String pCategory, String cCategory,String tags) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		int res=articleService.addArticle(article, user, pCategory, cCategory);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 文章页面
	 */
	@RequestMapping(value = ("{userName}/{articleId}"), method = RequestMethod.GET)
	public ModelAndView index(HttpSession session,@PathVariable String userName,@PathVariable Integer articleId) {
		ModelAndView mav = new ModelAndView();
		User user=userService.getUserInfo(userName);
		mav.addObject("user", user);
		// 获取分类列表
		List<Category> categoryList = categoryService.getAllCategory(userName);
		mav.addObject("categoryList", categoryList);
		Article article=articleService.getArticleById(articleId);
		mav.addObject("article", article);
		//阅读次数更新
		Jedis jedis=new Jedis("localhost");
		jedis.auth("970713");
		String userHotAArticles="hotArticles"+user.getUserName();
		if(session.getAttribute("view"+articleId)==null){
			session.setAttribute("view"+articleId, true);
			Long rank=jedis.zrank(userHotAArticles,JSON.toJSONString(article));
			if(rank!=null){
				jedis.zremrangeByRank(userHotAArticles, rank, rank);
			}
			articleService.view(articleId);
			article.setArticleViewCount(article.getArticleViewCount()+1);
			jedis.zadd(userHotAArticles, article.getArticleViewCount(),JSON.toJSONString(article));
			jedis.zremrangeByRank(userHotAArticles, 10, -1);
		}
		List<Article> hotArticles = new ArrayList<Article>();
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
		mav.setViewName("article");
		jedis.close();
		return mav;
	}
	/**
	 * 点赞
	 */
	@RequestMapping(value = "like", method = RequestMethod.POST)
	@ResponseBody
	public Object like(HttpServletResponse response,HttpServletRequest request,Integer articleId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		Cookie[] cookies = request.getCookies();
		for(Cookie c:cookies){
			if(c.getName().equals("article"+articleId)){
				hashMap.put("res", 0);
				return JSON.toJSONString(hashMap);
			}
		}
		Article article=articleService.getArticleById(articleId);
		Jedis jedis=new Jedis("localhost");
		jedis.auth("970713");
		jedis.del("hotArticles"+article.getUser().getUserName());
		jedis.close();
		int res=articleService.like(articleId);
		Cookie cookie=new Cookie("article"+articleId, "ture");
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
		hashMap.put("res", res);
		return JSON.toJSONString(hashMap);
	}

}
