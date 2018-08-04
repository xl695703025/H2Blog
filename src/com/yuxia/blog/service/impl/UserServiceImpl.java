package com.yuxia.blog.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuxia.blog.entity.User;
import com.yuxia.blog.mapper.UserMapper;
import com.yuxia.blog.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Override
	/**
	 * 登录
	 */
	public User login(String userName, String password) {
		if(userName==null||password==null)
			return null;
		User user=userMapper.selectUserByName(userName);
		if(!user.getUserPassword().equals(password))
			user=null;
		return user;
	}
	/**
	 * 注册
	 */
	@Override
	public Integer register(User user) {
		return userMapper.insertUser(user);
	}
	/**
	 * 检测用户名
	 */
	@Override
	public Boolean checkUserName(String userName) {
		User user=userMapper.selectUserByName(userName);
		if(user==null)
			return true;
		else
			return false;
	}
	/**
	 * 获取用户信息
	 */
	@Override
	public User getUserInfo(String userName) {
		return userMapper.selectUserByName(userName);
	}
	
}
