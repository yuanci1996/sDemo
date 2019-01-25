package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.VueMapper;
import com.pojo.UserForm;
import com.service.VueService;

@Service("vueService")
public class VueServiceImpl implements VueService{

	@Autowired
	private VueMapper vueMapper;
	
	
	@Override
	public UserForm login(String account, String password) {
		return vueMapper.login(account, password);
	}

}
