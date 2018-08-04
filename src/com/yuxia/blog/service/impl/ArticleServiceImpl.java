package com.yuxia.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.Category;
import com.yuxia.blog.entity.Tag;
import com.yuxia.blog.entity.User;
import com.yuxia.blog.mapper.ArticleMapper;
import com.yuxia.blog.mapper.CategoryMapper;
import com.yuxia.blog.mapper.CommentMapper;
import com.yuxia.blog.mapper.TagMapper;
import com.yuxia.blog.other.Page;
import com.yuxia.blog.service.ArticleService;

import redis.clients.jedis.Jedis;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private TagMapper tagMapper;

	/**
	 * 获取文章数
	 */
	@Override
	public Integer getArticleCount(String userName) {
		return articleMapper.selectArticleCount(userName);
	}

	/**
	 * 评论总数
	 */
	@Override
	public Integer getArticleCommentCount(String userName) {
		return articleMapper.selectArticleCommentCount(userName);
	}

	/**
	 * 访问量
	 */
	@Override
	public Integer getArticleViewCount(String userName) {
		return articleMapper.selectArticleViewCount(userName);
	}

	/**
	 * 点赞数
	 */
	@Override
	public Integer getArticleLikeCount(String userName) {
		return articleMapper.selectArticleLikeCount(userName);
	}

	/**
	 * 获取用户文章列表
	 */
	@Override
	public List<Article> getArticles(String userName, Page page) {
		List<Article> articles = articleMapper.selectArticleByUserName(userName,
				(page.getPageNow() - 1) * page.getPageSize(), page.getPageSize());
		for (Article a : articles) {
			if (a.getArticleTagIds() == null) {
				continue;
			}
			String[] tagList = a.getArticleTagIds().split(",");
			StringBuffer tagsName = new StringBuffer();
			for (String tagId : tagList) {
				if (tagId.equals("") || tagId == null)
					continue;
				Tag tag = tagMapper.selectTagByTagId(Integer.parseInt(tagId));// 根据Id获取这个Tag
				tagsName.append(tag.getTagName() + ",");
			}
			if (tagsName.lastIndexOf(",") != -1 && tagsName.lastIndexOf(",") == tagsName.length() - 1) {
				tagsName.deleteCharAt(tagsName.length() - 1);
			}
			a.setArticleTagIds(tagsName.toString());
		}
		return articles;
	}

	/**
	 * 根据分类获取文章列表
	 */
	@Override
	public List<Article> getArticlesByCategoryId(String userName, Page page, Integer categoryId) {
		List<Article> articles = articleMapper.selectArticleByCategory(userName, categoryId,
				(page.getPageNow() - 1) * page.getPageSize(), page.getPageSize());
		for (Article a : articles) {
			if (a.getArticleTagIds() == null) {
				continue;
			}
			String[] tagList = a.getArticleTagIds().split(",");
			StringBuffer tagsName = new StringBuffer();
			for (String tagId : tagList) {
				if (tagId.equals("") || tagId == null)
					continue;
				Tag tag = tagMapper.selectTagByTagId(Integer.parseInt(tagId));// 根据Id获取这个Tag
				tagsName.append(tag.getTagName() + ",");
			}
			if (tagsName.lastIndexOf(",") != -1 && tagsName.lastIndexOf(",") == tagsName.length() - 1) {
				tagsName.deleteCharAt(tagsName.length() - 1);
			}
			a.setArticleTagIds(tagsName.toString());
		}
		return articles;
	}

	/**
	 * 根据文章分类获取文章数量
	 */
	@Override
	public Integer getArticleCountByCategory(String userName, Integer categoryId) {
		return articleMapper.selectCountByCategory(userName, categoryId);
	}

	/**
	 * 删除文章
	 */
	@Override
	public Integer delArticle(Integer articleId) {
		commentMapper.delCommentByArticleId(articleId);// 先把评论给删除
		return articleMapper.delArticleById(articleId);
	}

	/**
	 * 根据ID获取文章
	 */
	@Override
	public Article getArticleById(Integer articleId) {
		return articleMapper.selectArticleById(articleId);
	}

	/**
	 * 修改文章
	 */
	@Override
	public Integer updateArticle(Article article, User user, String pCategoryName, String cCategoryName) {
		if (pCategoryName.equals(cCategoryName)) {
			return 0;
		}
		Category pCategory = categoryMapper.selectCategoryByName(pCategoryName, user.getUserName());
		Category cCategory = categoryMapper.selectCategoryByName(cCategoryName, user.getUserName());
		if (pCategory == null && cCategory == null) {
			pCategory = new Category();
			pCategory.setCategoryDescription("");
			pCategory.setCategoryName(pCategoryName);
			pCategory.setCategoryPid(0);
			pCategory.setUser(user);
			categoryMapper.insertCategory(pCategory);
		} else if (pCategory == null && cCategory != null) {
			return 0;
		}
		Integer articleParentCategoryId = pCategory.getCategoryId();
		if (cCategory == null) {
			cCategory = new Category();
			cCategory.setCategoryDescription("");
			cCategory.setCategoryName(cCategoryName);
			cCategory.setCategoryPid(articleParentCategoryId);
			cCategory.setUser(user);
			categoryMapper.insertCategory(cCategory);
		}
		if (pCategory.getCategoryId() != cCategory.getCategoryPid()) {
			return 0;
		}
		article.setArticlePostTime(new Date());
		article.setArticleStatus(1);
		StringBuffer ids = new StringBuffer();
		for (String tagName : article.getArticleTagIds().split(",|，")) {
			Tag tag = tagMapper.selectTagByTagName(user.getUserName(), tagName);
			if (tag != null) {
				ids.append(tag.getTagId() + ",");
			} else {
				tag = new Tag();
				tag.setTagDescription("");
				tag.setTagName(tagName);
				tag.setUser(user);
				tagMapper.insertTag(tag);
				ids.append(tag.getTagId() + ",");
			}
		}
		if (ids.lastIndexOf(",") != -1 && ids.lastIndexOf(",") == ids.length() - 1) {
			ids.deleteCharAt(ids.length() - 1);
		}
		article.setArticleTagIds(ids.toString());
		article.setArticleUpdateTime(new Date());
		article.setArticleChildCategory(cCategory);
		article.setArticleParentCategory(pCategory);
		return articleMapper.updateArtcile(article);
	}

	/**
	 * 添加文章
	 */
	@Override
	public Integer addArticle(Article article, User user, String pCategoryName, String cCategoryName) {
		if (pCategoryName.equals(cCategoryName)) {
			return 0;
		}
		Category pCategory = categoryMapper.selectCategoryByName(pCategoryName, user.getUserName());
		Category cCategory = categoryMapper.selectCategoryByName(cCategoryName, user.getUserName());
		if (pCategory == null && cCategory == null) {
			pCategory = new Category();
			pCategory.setCategoryDescription("");
			pCategory.setCategoryName(pCategoryName);
			pCategory.setCategoryPid(0);
			pCategory.setUser(user);
			categoryMapper.insertCategory(pCategory);
		} else if (pCategory == null && cCategory != null) {
			return 0;
		}
		Integer articleParentCategoryId = pCategory.getCategoryId();
		if (cCategory == null) {
			cCategory = new Category();
			cCategory.setCategoryDescription("");
			cCategory.setCategoryName(cCategoryName);
			cCategory.setCategoryPid(articleParentCategoryId);
			cCategory.setUser(user);
			categoryMapper.insertCategory(cCategory);
		}
		if (pCategory.getCategoryId() != cCategory.getCategoryPid()) {
			return 0;
		}
		StringBuffer ids = new StringBuffer();
		for (String tagName : article.getArticleTagIds().split(",|，")) {
			Tag tag = tagMapper.selectTagByTagName(user.getUserName(), tagName);
			if (tag != null) {
				ids.append(tag.getTagId() + ",");
			} else {
				tag = new Tag();
				tag.setTagDescription("");
				tag.setTagName(tagName);
				tag.setUser(user);
				tagMapper.insertTag(tag);
				ids.append(tag.getTagId() + ",");
			}
		}
		if (ids.lastIndexOf(",") != -1 && ids.lastIndexOf(",") == ids.length() - 1) {
			ids.deleteCharAt(ids.length() - 1);
		}
		article.setArticleChildCategory(cCategory);// 设置父分类
		article.setArticleParentCategory(pCategory);// 设置子分类
		article.setArticleTagIds(ids.toString());// 设置标签
		article.setUser(user);// 设置用户
		article.setArticlePostTime(new Date());// 提交时间
		article.setArticleStatus(1);// 状态
		return articleMapper.insertArticle(article);
	}

	/**
	 * 点赞
	 */
	@Override
	public Integer like(Integer articleId) {
		Article article = articleMapper.selectArticleById(articleId);
		article.setArticleLikeCount(article.getArticleLikeCount() + 1);

		return articleMapper.updateArtcile(article);
	}

	/**
	 * 阅读
	 */
	@Override
	public Integer view(Integer articleId) {
		Article article = articleMapper.selectArticleById(articleId);
		article.setArticleViewCount(article.getArticleViewCount() + 1);
		return articleMapper.updateArtcile(article);
	}

	/**
	 * 根据标签获取文章列表
	 */
	@Override
	public List<Article> getArticlesByTagId(String userName, Page page, Integer tagId) {
		List<Article> articles = articleMapper.selectArticleByTagId(userName, tagId,
				(page.getPageNow() - 1) * page.getPageSize(), page.getPageSize());
		for (Article a : articles) {
			if (a.getArticleTagIds() == null) {
				continue;
			}
			String[] tagList = a.getArticleTagIds().split(",");
			StringBuffer tagsName = new StringBuffer();
			for (String tagId1 : tagList) {
				if (tagId1.equals("") || tagId1 == null)
					continue;
				Tag tag = tagMapper.selectTagByTagId(Integer.parseInt(tagId1));// 根据Id获取这个Tag
				tagsName.append(tag.getTagName() + ",");
			}
			if (tagsName.lastIndexOf(",") != -1 && tagsName.lastIndexOf(",") == tagsName.length() - 1) {
				tagsName.deleteCharAt(tagsName.length() - 1);
			}
			a.setArticleTagIds(tagsName.toString());
		}
		return articles;
	}
	/**
	 * 获取热门文章
	 */
	@Override
	public List<Article> getHotArticles(String userName) {
		return articleMapper.selectHotArticles(userName);
	}
}
