package com.yuxia.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.Article;
import com.yuxia.blog.entity.Category;
import com.yuxia.blog.entity.User;
import com.yuxia.blog.mapper.ArticleMapper;
import com.yuxia.blog.mapper.CategoryMapper;
import com.yuxia.blog.mapper.CommentMapper;
import com.yuxia.blog.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	CommentMapper commentMapper;
	/**
	 * 获取所有的分类
	 */
	@Override
	public List<Category> getAllCategory(String userName) {
		return categoryMapper.selectAllCategory(userName);
	}
	/**
	 * 根据分类ID获取分类信息
	 */
	@Override
	public Category getCategoryById(Integer categoryId) {
		return categoryMapper.selectCategoryById(categoryId);
	}
	/**
	 * 修改分类
	 */
	@Override
	public Integer updateCategory(Category category,String userName) {
		Category newCategory=categoryMapper.selectCategoryByName(category.getCategoryName(), userName);
		if(newCategory!=null&&newCategory.getCategoryId()!=category.getCategoryId())
			return 0;
		else
			return categoryMapper.updateCategory(category);
	}
	/**
	 * 添加分类
	 */
	@Override
	public int addCategory(User user,String pCategoryName,String cCategoryName,String categoryDescription) {
		if(pCategoryName.equals(cCategoryName)){
			return 0;
		}
		Category oldCCategory=categoryMapper.selectCategoryByName(cCategoryName, user.getUserName());
		if(oldCCategory!=null){//和子分类重复，直接返回0
			return 0;
		}
		Category oldPCategory=categoryMapper.selectCategoryByName(pCategoryName, user.getUserName());
		Category pCategory=null;
		if(oldPCategory==null){//不存在父分类，添加父分类
			pCategory=new Category();
			pCategory.setCategoryName(pCategoryName);
			if(cCategoryName.equals(""))
				pCategory.setCategoryDescription(categoryDescription);
			pCategory.setCategoryPid(0);
			pCategory.setCategoryStatus(1);
			pCategory.setUser(user);
			categoryMapper.insertCategory(pCategory);
		}
		if(!cCategoryName.equals("")){//子分类名称不为空，添加子分类
			Category cCategory=new Category();
			if(oldPCategory!=null){//如果存在父分类，设置子分类的父分类Id
				cCategory.setCategoryPid(oldPCategory.getCategoryId());
			}else{//如果不存在，设置子分类的父分类Id为新建父分类Id
				cCategory.setCategoryPid(pCategory.getCategoryId());
			}
			cCategory.setCategoryName(cCategoryName);
			cCategory.setCategoryDescription(categoryDescription);
			cCategory.setCategoryStatus(1);
			cCategory.setUser(user);
			categoryMapper.insertCategory(cCategory);
		}
		return 1;
	}
	/**
	 * 删除分类
	 */
	@Override
	public int delCategoryById(Integer categoryId) {
		Category category = categoryMapper.selectCategoryById(categoryId);
		List<Article> articleList;
		if(category.getCategoryPid()!=0){
			articleList= articleMapper.selectArticleByCategory(category.getUser().getUserName(), category.getCategoryId(), 0, Integer.MAX_VALUE);
		}else{
			articleList=new ArrayList<Article>();
			List<Category> categories=categoryMapper.selectCategoryByPCategory(categoryId);
			for(Category c:categories){
				articleList.addAll(articleMapper.selectArticleByCategory(c.getUser().getUserName(), c.getCategoryId(), 0, Integer.MAX_VALUE));
			}
		}
		
		for (Article a : articleList) {
			commentMapper.delCommentByArticleId(a.getArticleId());// 把评论给删除
			articleMapper.delArticleById(a.getArticleId());// 删除文章
		}
		return categoryMapper.delCategoryById(categoryId);
	}

}
