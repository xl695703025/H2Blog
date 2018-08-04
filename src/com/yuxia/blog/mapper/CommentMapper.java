package com.yuxia.blog.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxia.blog.entity.Comment;

public interface CommentMapper {

	public List<Comment> selectCommentByUserName(@Param("userName")String userName);
	
	public int delCommentById(@Param("commentId")Integer commentId);
	
	public int delCommentByArticleId(Integer articleId);
	
	public List<Comment> selectCommentArticleId(@Param("articleId")Integer articleId);
	
	public int insertComment(@Param("articleId")Integer articleId,
			@Param("commentAuthorName")String commentAuthorName,
			@Param("commentAuthorEmail")String commentAuthorEmail,
			@Param("commentContent")String commentContent,
			@Param("commentIp")String commentIp,
			@Param("commentCreateTime")Date commentCreateTime
			);
}
