package com.yuxia.blog.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.Category;
import com.yuxia.blog.entity.Comment;
import com.yuxia.blog.entity.User;
import com.yuxia.blog.other.IpAdrressUtil;
import com.yuxia.blog.service.CategoryService;
import com.yuxia.blog.service.CommentService;
import com.yuxia.blog.service.impl.ArticleServiceImpl;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("comment")
public class CommentController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	CommentService commentService;
	/**
	 * 获取所有留言
	 */
	@RequestMapping("manage")
	public ModelAndView getArticleByCategory(HttpSession session,Category category){
		ModelAndView mav=new ModelAndView();
		//获取用户
		User user = (User) session.getAttribute("user");
		//获取分类列表
		List<Category> categoryList=categoryService.getAllCategory(user.getUserName());
		mav.addObject("categoryList",categoryList);
		//获取留言列表
		List<Comment> commentList=commentService.getComment(user.getUserName());
		mav.addObject("commentList",commentList);
		mav.setViewName("commentManage");
		mav.addObject("category", category);
		return mav;
	}
	/**
	 * 删除留言
	 */
	@RequestMapping(value="delComment",method=RequestMethod.POST)
	@ResponseBody
	public Object delComment(Integer commentId){
		Map< String, Object> hashMap=new HashMap<String, Object>();
		int res=commentService.delComment(commentId);
		if(res>0){
			hashMap.put("res",true);
		}else{
			hashMap.put("res",false);
		}
		return JSON.toJSONString(hashMap);
	}

	/**
	 * 根据文章Id获取留言
	 */
	@RequestMapping(value="getCommentByArticleId",method=RequestMethod.POST)
	@ResponseBody
	public Object getCommentByArticleId(Integer articleId){
		Map< String, Object> hashMap=new HashMap<String, Object>();
		hashMap.put("commentList", commentService.getCommentByArticleId(articleId));
		return JSON.toJSONString(hashMap);
	}
	/**
	 * 添加留言
	 */
	@RequestMapping(value="addComment",method=RequestMethod.POST)
	@ResponseBody
	public Object addComment(HttpServletRequest request,Integer articleId,String nickName,String email,String commentContent){
		Map< String, Object> hashMap=new HashMap<String, Object>();
		int res=commentService.addComment(articleId, nickName, email, commentContent, IpAdrressUtil.getIp(request), new Date());
		hashMap.put("res",res);
		hashMap.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return JSON.toJSONString(hashMap);
	}
}
