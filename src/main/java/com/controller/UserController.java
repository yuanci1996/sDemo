package com.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pojo.Miniprogram;
import com.pojo.MoveCarAlarmTemplate;
import com.pojo.TemplateData;
import com.pojo.UserForm;
import com.service.UserService;
import com.utils.AesCbcUtil;
import com.utils.HttpRequest;

import io.netty.util.internal.StringUtil;
import net.sf.json.JSONObject;


@Controller
@RequestMapping(value="/user")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	@Qualifier(value="userService")
	private UserService userService;
	
	@RequestMapping(value="/login")
	public String loginJsp() {
		return "login";
	}
	
	@RequestMapping(value="/welcome.do")
	@ResponseBody
	public Boolean loginS() {
		Boolean res=true;
		return res;
	}
	
	@RequestMapping(value="/login.jsp?error")
	@ResponseBody
	public Boolean loginF() {
		Boolean res=false;
		return res;
	}
	
	@RequestMapping(value="/main.html")
	public String mainJsp() {
		return "welcome";
	}
	
	@RequestMapping(value="testlogin")
	@ResponseBody
	public com.pojo.UserForm logindd(String account, String password) {
		log.info("login----->");
		UserForm a=userService.login(account, password);
		return a;
	}
	
	@RequestMapping(value="/getOpenid")
	public  String getOpenId(String code){  
		System.out.println(code);
	    if(StringUtil.isNullOrEmpty(code)){  
	        String appid = "wx151a1176d9e41caf";  
	        String secret = "2c00ff2ac369c535f4500e31a2d93ea2";  
	        String params = "appid=" + appid + "&secret=" + secret +"&code="+code+"&grant_type=authorization_code";
	        String result = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token" ,params);  
	        System.out.println(result);
	        if(StringUtil.isNullOrEmpty(result)){  
	            JSONObject json = JSONObject.fromObject(result);  
	            if(json.get("openid")!=null){ 
	            	System.out.println(json.get("openid").toString());
	                return json.get("openid").toString();  
	            }  
	        }  
	    }  
	    return "";  
	}  
	@RequestMapping(value="/sendReqs")
	public void sendReq() {
    String par= "		  {\"touser\":\"oo56X0069wg2WhLU4zzT79_O0nMA\",\"template_id\":\"fApD3cU42g8ypWlxB7GXmEaWO_U4FkccUfhpB69tvaA\",\r\n" + 
    		"	       \"miniprogram\":{ \"appid\":\"wxffce3f703d0a5039\"},          \r\n" + 
    		"	        \"data\":{ \"first\": { \"value\":\"duijiang request\", \"color\":\"#173177\"},\r\n" + 
    		"	                  \"keyword1\":{ \"value\":\"request\",\"color\":\"#173177\"},\r\n" + 
    		"	                  \"keyword2\":{\"value\":\"respond\",\"color\":\"#173177\" },\r\n" + 
    		"	                  \"remark\":{\"value\":\"欢迎再次购买！\", \"color\":\"#173177\"}}}";
    try {
		par= new String(par.toString().getBytes("UTF-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    String res=HttpRequest.sendPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=12_29YJRcxLyCebroYErfS8kAUfpcKtdbprm-XjPMGirG61RNWTZkzaTIfHJ9HSJDUEuB8_pB67rjvuTnMfnfRH17fx8qnuuXK1VBdgC_cgr8E4RUI71t4IyoYdIyHM_jxopemjZIndV9mpQkFAHYYeABABSS", par);
	  System.out.println(res);
	}
	
	@RequestMapping(value="/sendReq")
	public void snedM() {
		String par="{\r\n" + 
				"	           \"touser\":\"oo56X0069wg2WhLU4zzT79_O0nMA\",\r\n" + 
				"	           \"template_id\":\"fApD3cU42g8ypWlxB7GXmEaWO_U4FkccUfhpB69tvaA\",\r\n" + 
				"	           \"data\":{\r\n" + 
				"	                   \"first\": {\r\n" + 
				"	                       \"value\":\"request\",\r\n" + 
				"	                       \"color\":\"#173177\"\r\n" + 
				"	                   },\r\n" + 
				"	                   \"keyword1\":{\r\n" + 
				"	                       \"value\":\"people1\",\r\n" + 
				"	                       \"color\":\"#173177\"\r\n" + 
				"	                   },\r\n" + 
				"	                   \"keyword2\": {\r\n" + 
				"	                       \"value\":\"people1\",\r\n" + 
				"	                       \"color\":\"#173177\"\r\n" + 
				"	                   },\r\n" + 
				"	                   \"remark\":{\r\n" + 
				"	                       \"value\":\"return\",\r\n" + 
				"	                       \"color\":\"#173177\"\r\n" + 
				"	                   }\r\n" + 
				"	           }\r\n" + 
				"	       }";
		try {
			par= new String(par.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String res=HttpRequest.sendPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=12_-eqFbQZ5LjLc6di0gHHtDNIurn9AcUsIJ7ewVdlfD-wfwhSLTww8fnQLP9wT7orTgwEYXg_5sZmjGBDZx7KzYU315cEXy81buZGPyGDyVWY1ql3yxWBijUWlxDbnP4imP_QJ2M4vqpYfNaW6DJKiAHANTU", par);
		  System.out.println(res);
	}
	
	@RequestMapping(value="/sendReqMsg")
	public void sendReqMsg(String x,String y) {
		System.out.println(x);System.out.println(y);
		MoveCarAlarmTemplate m=new MoveCarAlarmTemplate();
		m.setTouser("oT-FJ0j8O3BVkaOilkcgwKCpW1q8");
		m.setTemplate_id("7XbV6tRrvQSGp9DmAUPFRB3DNGwhbQ_GOH1elz4dUrg");
		TemplateData first=new TemplateData();
		first.setValue("hujiao request");
		first.setColor("#173177");
		TemplateData keyword1=new TemplateData();
		keyword1.setValue("xiaocehngxu");
		keyword1.setColor("#173177");
		TemplateData keyword2=new TemplateData();
		keyword2.setValue("2018.7.27");
		keyword2.setColor("#173177");
		TemplateData remark=new TemplateData();
		remark.setValue("tiaozhuan");
		remark.setColor("#173177");
		Map<String, TemplateData> map=new HashMap<String,TemplateData>();
		map.put("first", first);		
		map.put("keyword1", keyword1);		
		map.put("keyword2", keyword2);
		map.put("remark", remark);
		m.setData(map);
		Miniprogram min=new Miniprogram();
		min.setAppid("wxffce3f703d0a5039");
		String pagepath= "index/index?x="+x+"&y="+y;
//		min.setPagepath(pagepath);
		m.setMiniprogram(min);
//		System.out.println(map.get("keyword1"));
//		System.out.println(map.get("keyword2"));
		System.out.println(JSON.toJSONString(m));
		String par=JSON.toJSONString(m);
		String res=HttpRequest.sendPost("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=12_QyCJShPunjHGJfidfMXJ9NU2JUsWOLQiKjqm0QLhMbssc1bYlVe5aux9GKBHRiyn-Gmx7460tkN5Hw8T1F324LQy4lICPJjz0-Nunv3-UYfRN9nNmG0tE0sYLZ0JRDeADARZL", par);
		  System.out.println(res);
	}

}
