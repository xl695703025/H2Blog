package com.yuxia.blog.service;

import java.util.List;


import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.User;
import com.yuxia.blog.other.Page;

public interface ArticleService {
	public Integer getArticleCountByCategory(String userName,Integer categoryId);

	public Integer getArticleCount(String userName);

	public Integer getArticleCommentCount(String userName);

	public Integer getArticleViewCount(String userName);

	public Integer getArticleLikeCount(String userName);
	
	public List<Article> getArticles(String userName,Page page);
	
	public List<Article> getArticlesByCategoryId(String userName,Page page,Integer categoryId);
	
	public List<Article> getArticlesByTagId(String userName,Page page,Integer tagId);
	
	public Integer delArticle(Integer articleId);
	
	public Article getArticleById(Integer articleId);
	
	public Integer updateArticle(Article article, User user,String pCategoryName, String cCategoryName);
	
	public Integer addArticle(Article article, User user,String pCategoryName, String cCategoryName);
	
	public Integer like(Integer articleId);

	public Integer view(Integer articleId);
	
	public List<Article> getHotArticles(String userName);
}
