package com.yuxia.blog.service;

import com.yuxia.blog.entity.User;

public interface UserService {
	public User login(String userName,String password);
	public Integer register(User user);
	public Boolean checkUserName(String userName);
	public User getUserInfo(String userName);
}
