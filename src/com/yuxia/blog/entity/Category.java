package com.yuxia.blog.entity;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable  {

	private static final long serialVersionUID = 8847956859466283886L;
	private Integer categoryId;//文章分类Id
	private User user;//分类用户Id
	private Integer categoryPid;//文章父分类Id
	private List<Category> childCategoryList;//子分类
	private String categoryName;//分类名称
	private String categoryDescription;//分类描述
	private int categoryStatus;//分类状态
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getCategoryPid() {
		return categoryPid;
	}
	public void setCategoryPid(Integer categoryPid) {
		this.categoryPid = categoryPid;
	}
	public List<Category> getChildCategoryList() {
		return childCategoryList;
	}
	public void setChildCategoryList(List<Category> childCategoryList) {
		this.childCategoryList = childCategoryList;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public int getCategoryStatus() {
		return categoryStatus;
	}
	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", user=" + user + ", categoryPid=" + categoryPid
				+ ", childCategoryList=" + childCategoryList + ", categoryName=" + categoryName
				+ ", categoryDescription=" + categoryDescription + ", categoryStatus=" + categoryStatus + "]";
	}

	

}
