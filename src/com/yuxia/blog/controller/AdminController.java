package com.yuxia.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.Category;
import com.yuxia.blog.entity.User;
import com.yuxia.blog.other.Page;
import com.yuxia.blog.service.ArticleService;
import com.yuxia.blog.service.CategoryService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	@Autowired
	ArticleService articleService;
	@Autowired
	CategoryService categoryService;
	@RequestMapping("index")
	public ModelAndView index(HttpSession session,Page page){
		ModelAndView mav=new ModelAndView();
		User user = (User) session.getAttribute("user");
		//设置文章总数，自动计算页面熟练
		page.setTotalCount(articleService.getArticleCount(user.getUserName()));
		//博文总数
		Integer articleCount = articleService.getArticleCount(user.getUserName());
		mav.addObject("articleCount", articleCount);
		//点赞数
		Integer likeCount = articleService.getArticleLikeCount(user.getUserName());
		if (likeCount == null)// 数据库用sum()计算的，可能为空，文章数是用count()计数，不会为空
			likeCount = 0;
		mav.addObject("likeCount", likeCount);
		//浏览次数
		Integer viewCount = articleService.getArticleViewCount(user.getUserName());
		if (viewCount == null)
			viewCount = 0;
		mav.addObject("viewCount", viewCount);
		//留言总数
		Integer commentCount = articleService.getArticleCommentCount(user.getUserName());
		if (commentCount == null)
			commentCount = 0;
		mav.addObject("commentCount", commentCount);
		//根据用户分类列表
		List<Category> categoryList=categoryService.getAllCategory(user.getUserName());
		mav.addObject("categoryList",categoryList);
		//获取文章列表
		List<Article> articleList=articleService.getArticles(user.getUserName(), page);
		mav.addObject(articleList);
		mav.addObject("page", page);
		mav.setViewName("index");
		return mav;
	}
}
