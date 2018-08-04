package com.yuxia.blog.mapper;

import com.yuxia.blog.entity.User;

public interface UserMapper {

	public User selectUserByName(String userName);
	public Integer insertUser(User user);
}
