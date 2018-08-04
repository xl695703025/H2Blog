package com.yuxia.blog.service;

import java.util.List;

import com.yuxia.blog.entity.Category;
import com.yuxia.blog.entity.User;

public interface CategoryService {

	public List<Category> getAllCategory(String userName);
	
	public Category getCategoryById(Integer categoryId);
	
	public Integer updateCategory(Category category,String userName);
	
	public int addCategory(User user,String pCategoryName,String cCategoryName,String categoryDescription);
	
	public int delCategoryById(Integer categoryId);
}
