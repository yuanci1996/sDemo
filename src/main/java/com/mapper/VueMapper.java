package com.mapper;

import org.apache.ibatis.annotations.Param;

import com.pojo.UserForm;

public interface VueMapper {

	public UserForm login(@Param("account") String account, @Param("password") String password);
}
