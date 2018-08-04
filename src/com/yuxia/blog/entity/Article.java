package com.yuxia.blog.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Article implements Serializable {

	private static final long serialVersionUID = 8796779202921057926L;
	private int articleId;
	private User user;
	private String userNickName;//文章用户
	private String articleTitle;//文章标题
	private String articleContent;//文章内容
	private Category articleParentCategory;//文章父分类
	private Category articleChildCategory;//文章子分类
	private String articleTagIds;//文章所有标签Id
	private int articleViewCount;//文章浏览次数
	private int articleCommentCount;//文章评论数
	private int articleLikeCount;//文章点赞数
	@JSONField(format="yyyy-MM-dd HH:dd:ss")
	private Date articlePostTime;//上传时间
	@JSONField(format="yyyy-MM-dd HH:dd:ss")
	private Date articleUpdateTime;//更新时间
	private int articleStatus;//文章状态
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public Category getArticleParentCategory() {
		return articleParentCategory;
	}
	public void setArticleParentCategory(Category articleParentCategory) {
		this.articleParentCategory = articleParentCategory;
	}
	public Category getArticleChildCategory() {
		return articleChildCategory;
	}
	public void setArticleChildCategory(Category articleChildCategory) {
		this.articleChildCategory = articleChildCategory;
	}
	public String getArticleTagIds() {
		return articleTagIds;
	}
	public void setArticleTagIds(String articleTagIds) {
		this.articleTagIds = articleTagIds;
	}
	public int getArticleViewCount() {
		return articleViewCount;
	}
	public void setArticleViewCount(int articleViewCount) {
		this.articleViewCount = articleViewCount;
	}
	public int getArticleCommentCount() {
		return articleCommentCount;
	}
	public void setArticleCommentCount(int articleCommentCount) {
		this.articleCommentCount = articleCommentCount;
	}
	public int getArticleLikeCount() {
		return articleLikeCount;
	}
	public void setArticleLikeCount(int articleLikeCount) {
		this.articleLikeCount = articleLikeCount;
	}
	public Date getArticlePostTime() {
		return articlePostTime;
	}
	public void setArticlePostTime(Date articlePostTime) {
		this.articlePostTime = articlePostTime;
	}
	public Date getArticleUpdateTime() {
		return articleUpdateTime;
	}
	public void setArticleUpdateTime(Date articleUpdateTime) {
		this.articleUpdateTime = articleUpdateTime;
	}
	public int getArticleStatus() {
		return articleStatus;
	}
	public void setArticleStatus(int articleStatus) {
		this.articleStatus = articleStatus;
	}
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", user=" + user + ", userNickName=" + userNickName
				+ ", articleTitle=" + articleTitle + ", articleContent=" + articleContent + ", articleParentCategory="
				+ articleParentCategory + ", articleChildCategory=" + articleChildCategory + ", articleTagIds="
				+ articleTagIds + ", articleViewCount=" + articleViewCount + ", articleCommentCount="
				+ articleCommentCount + ", articleLikeCount=" + articleLikeCount + ", articlePostTime="
				+ articlePostTime + ", articleUpdateTime=" + articleUpdateTime + ", articleStatus=" + articleStatus
				+ "]";
	}

	
}
