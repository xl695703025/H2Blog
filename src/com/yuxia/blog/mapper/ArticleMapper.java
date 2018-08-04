package com.yuxia.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxia.blog.entity.Article;

public interface ArticleMapper {

	public Integer selectArticleCount(@Param("userName") String userName);

	public Integer selectArticleViewCount(@Param("userName") String userName);

	public Integer selectArticleLikeCount(@Param("userName") String userName);

	public Integer selectArticleCommentCount(@Param("userName") String userName);

	public Integer delArticleById(@Param("articleId") Integer articleId);
	
	public Integer updateArtcile(Article article);
	
	public Article selectArticleById(Integer articleId);
	
	public List<Article> selectArticleByUserName(@Param("userName")String userName,@Param("index")Integer index,@Param("pageSize")Integer pageSize);
	
	public List<Article> selectArticleByCategory(@Param("userName")String userName,@Param("categoryId")Integer categoryId,@Param("index")Integer index,@Param("pageSize")Integer pageSize);
	public Integer selectCountByCategory(@Param("userName")String userName,@Param("categoryId")Integer categoryId);
	
	public List<Article> selectArticleByTagId(@Param("userName")String userName,@Param("tagId")Integer tagId,@Param("index")Integer index,@Param("pageSize")Integer pageSize);
	
	public Integer insertArticle(Article article);
	
	public List<Article> selectHotArticles(String userName);
}
