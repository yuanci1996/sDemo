package com.service;

import org.apache.ibatis.annotations.Param;

import com.pojo.UserForm;

public interface VueService {

	public UserForm login(@Param("account") String account, @Param("password") String password);
}
