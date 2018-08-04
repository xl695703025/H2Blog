package com.yuxia.blog.entity;

import java.io.Serializable;

public class Tag implements Serializable {

	private static final long serialVersionUID = 5265648872358951633L;
	private int tagId;//标签Id
	private String tagName;//标签名
	private User user;//用户
	private String tagDescription;//标签描述
	private int tagStatus;//标签状态
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTagDescription() {
		return tagDescription;
	}
	public void setTagDescription(String tagDescription) {
		this.tagDescription = tagDescription;
	}
	public int getTagStatus() {
		return tagStatus;
	}
	public void setTagStatus(int tagStatus) {
		this.tagStatus = tagStatus;
	}
	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", tagName=" + tagName + ", user=" + user + ", tagDescription=" + tagDescription
				+ ", tagStatus=" + tagStatus + "]";
	}
	
}
