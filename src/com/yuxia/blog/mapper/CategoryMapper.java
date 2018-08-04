package com.yuxia.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxia.blog.entity.Category;

public interface CategoryMapper {
	
	public List<Category> selectAllCategory(String userName);
	
	public List<Category> selectCategoryByPCategory(Integer pCategoryId);
	
	public Category selectCategoryById(Integer categoryId);
	
	public Integer updateCategory(Category category);
	
	public Category selectCategoryByName(@Param("categoryName")String categoryName,@Param("userName")String userName);
	
	public int insertCategory(Category category);
	
	public int delCategoryById(Integer categoryId);
}
