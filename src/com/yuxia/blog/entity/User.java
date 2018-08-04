package com.yuxia.blog.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = 420472426217465396L;
	private int userId;
	private String userName;//用户名
	private String userPassword;//用户密码
	private String userNickName;//昵称
	private String userEmail;//邮箱
	private String userAvatar;//头像
	private Date userRegisterTime;//注册时间
	private Date userLastLoginTime;//最后一次登录时间
	private int userStatus;//用户状态
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickname) {
		this.userNickName = userNickname;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public Date getUserRegisterTime() {
		return userRegisterTime;
	}
	public void setUserRegisterTime(Date userRegisterTime) {
		this.userRegisterTime = userRegisterTime;
	}
	public Date getUserLastLoginTime() {
		return userLastLoginTime;
	}
	public void setUserLastLoginTime(Date userLastLoginTime) {
		this.userLastLoginTime = userLastLoginTime;
	}
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", userNickname="
				+ userNickName + ", userEmail=" + userEmail + ", userAvatar="
				+ userAvatar + ", userRegisterTime=" + userRegisterTime
				+ ", userLastLoginTime=" + userLastLoginTime + ", userStatus="
				+ userStatus + "]";
	}
}
