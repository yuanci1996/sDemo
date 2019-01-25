package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.Student;
import com.utils.AesCbcUtil;
import com.utils.CheckoutUtil;
import com.utils.HttpRequest;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wechat")
public class WxController {    
	private final Logger logger = LoggerFactory.getLogger(WxController.class);
    @RequestMapping("/token")
    public void weChat(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
    	System.out.println("---------------------------");
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter print;
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
                try {
                    print = response.getWriter();
                    print.write(echostr);
                    print.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
	@ResponseBody
	@RequestMapping(value = "/decodeUserInfo", method = RequestMethod.GET)
	public Map decodeUserInfo(String encryptedData, String iv, String code) {

		System.out.println("11111");
	    Map map = new HashMap();
	    //登录凭证不能为空
	    if (code == null || code.length() == 0) {
	        map.put("status", 0);
	        map.put("msg", "code 不能为空");
	        return map;
	    }

	    //小程序唯一标识   (在微信小程序管理后台获取)
	    String wxspAppid ="wxc8972f992087aebc";
	    //小程序的 app secret (在微信小程序管理后台获取)
	    String wxspSecret ="116c55c50c7ab69f0fed48f728916783";
	    //授权（必填）
	    String grant_type ="authorization_code";


	    //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
	    //请求参数
	    String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
	    //发送请求
	    String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
	    System.out.println("Object:"+sr);
	    //解析相应内容（转换成json对象）
	    JSONObject json = JSONObject.fromObject(sr);
	    //获取会话密钥（session_key）
	    String session_key = json.get("session_key").toString();
	    System.out.println("session_key:"+session_key);
	    //用户的唯一标识（openid）
	    String openid = (String) json.get("openid");
        System.out.println("openid:"+openid);
	    //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
	    try {
	        String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
	        if (null != result && result.length() > 0) {
	            map.put("status", 1);
	            map.put("msg", "解密成功");

	            JSONObject userInfoJSON = JSONObject.fromObject(result);
	            Map userInfo = new HashMap();
	            userInfo.put("openId", userInfoJSON.get("openId"));
	            userInfo.put("nickName", userInfoJSON.get("nickName"));
	            userInfo.put("gender", userInfoJSON.get("gender"));
	            userInfo.put("city", userInfoJSON.get("city"));
	            userInfo.put("province", userInfoJSON.get("province"));
	            userInfo.put("country", userInfoJSON.get("country"));
	            userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
	            userInfo.put("unionId", userInfoJSON.get("unionId"));
	            map.put("userInfo", userInfo);
	            System.out.println("userInfo"+userInfo);
	            return map;
	            
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    map.put("status", 0);
	    map.put("msg", "解密失败");
	    return map;
	}

	@RequestMapping(value="/getPeopleList")
	@ResponseBody
	public List<Student> getPeopleList() {
		logger.info("log.....");
		Student stu1=new Student();
		stu1.setId(1);stu1.setName("学生1");
		Student stu2=new Student();
		stu2.setId(2);stu2.setName("学生2");
		List<Student> lis=new ArrayList<Student>();
		lis.add(stu1);lis.add(stu2);
		System.out.println(lis.toString());
		return lis;
	}
}