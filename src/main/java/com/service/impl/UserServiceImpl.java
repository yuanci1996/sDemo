package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.UserMapper;
import com.pojo.User;
import com.pojo.UserForm;
import com.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Override
	
	public UserForm login(String account, String password) {
//		UserForm a=new UserForm();
//		a.setAccount("admin");
//		a.setPassword("admin");
//		return a;
		return userMapper.login(account, password);
	}
	@Override
	public User findUserByName(String name) {
		return userMapper.findUserByName(name);
	}

}
