package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pojo.VueLoginInfoVo;
import com.result.Result;
import com.result.ResultFactory;
import com.service.VueService;

@RestController
public class VueController {

	@Autowired
	@Qualifier(value="vueService")
	private VueService vueService;
	
	    @CrossOrigin
	    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	    public Result login(@Valid @RequestBody VueLoginInfoVo loginInfoVo, BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
	            return ResultFactory.buildFailResult(message);
	        }
	        if (!Objects.equals("admin", loginInfoVo.getUsername()) || !Objects.equals("admin", loginInfoVo.getPassword())) {
	            String message = String.format("登陆失败，详细信息[用户名、密码信息不正确]。");
	            return ResultFactory.buildFailResult(message);
	        }
	        Map<String,Object> map= new HashMap<String,Object>();
//	        List<Object> a=new ArrayList<Object>();
	        map.put("token","admin-token");
	        return ResultFactory.buildSuccessResult("登录成功");
	    }

	    @CrossOrigin
	    @RequestMapping(value = "/api/info",method = RequestMethod.GET,produces = "application/json; charset=UTF-8")
	    public Result result(String token) {
	    	if("admin-token".equals(token)) {
	        Map<String,Object> map= new HashMap<String,Object>();
	        map.put("name","admin");
	        map.put("roles","admin");
	        map.put("avatar",null);
	        System.out.println("vue----->login");
	        System.out.println(map);
	        return ResultFactory.buildSuccessResult(map);
	    	}
	    	return null;
	    }
}
