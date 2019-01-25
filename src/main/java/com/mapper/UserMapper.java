package com.mapper;

import org.apache.ibatis.annotations.Param;

import com.pojo.User;
import com.pojo.UserForm;

public interface UserMapper {

	public UserForm login(@Param("account") String account, @Param("password") String password);
	
	public User findUserByName(String name);
}
