package com.yuxia.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.Comment;
import com.yuxia.blog.mapper.ArticleMapper;
import com.yuxia.blog.mapper.CommentMapper;
import com.yuxia.blog.service.CommentService;

import redis.clients.jedis.Jedis;
@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ArticleMapper articleMapper;
	/**
	 * 获取留言
	 */
	@Override
	public List<Comment> getComment(String userName) {
		return commentMapper.selectCommentByUserName(userName);
	}
	/**
	 * 删除留言
	 */
	@Override
	public int delComment(Integer commentId) {
		return commentMapper.delCommentById(commentId);
	}
	/**
	 * 根据文章Id获取留言
	 */
	@Override
	public List<Comment> getCommentByArticleId(Integer articleId) {
		return commentMapper.selectCommentArticleId(articleId);
	}
	/**
	 * 添加留言
	 */
	@Override
	public int addComment(Integer articleId, String commentAuthorName, String commentAuthorEmail, String commentContent,
			String commentIp, Date commentCreateTime) {
		Article article = articleMapper.selectArticleById(articleId);
		Jedis jedis=new Jedis("localhost");
		jedis.auth("970713");
		jedis.del("hotArticles"+article.getUser().getUserName());
		jedis.close();
		article.setArticleCommentCount(article.getArticleCommentCount()+1);
		articleMapper.updateArtcile(article);
		return commentMapper.insertComment(articleId, commentAuthorName, commentAuthorEmail, commentContent, commentIp, commentCreateTime);
	}
}
