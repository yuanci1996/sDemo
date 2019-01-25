package com.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mapper.PushService;
import com.pojo.ResultRespone;
import com.pojo.UserAq;

@Controller
@RequestMapping(value="/push")
public class PushController {

	@Resource(name="userPushService")
	private PushService userPushService;

	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	@ResponseBody
	public ResultRespone userPush(UserAq info){
		ResultRespone respone = new ResultRespone();
		try {
			userPushService.push(info);//发送信息到队列中去
			respone.setData(info);
			respone.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respone;
	}

}
