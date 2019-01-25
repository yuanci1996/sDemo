package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mapper.JkBuildMapper;
import com.pojo.JkBuild;
import com.pojo.JkCommunity;
import com.service.JkBuildService;
import com.utils.HttpUtils;
import com.utils.SignUtils;
import com.utils.StringUtil;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yuanci
 * @Date: 2018/11/9 11:16
 * @Version 1.0
 */
@Service("jkBuildService")
public class JkBuildServiceImpl implements JkBuildService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JkBuildMapper jkBuildMapper;

    @Override
    public int insertJkBuildList(HashMap<String,Object> map) {
        String sign;
		try {
			sign = SignUtils.signUp(map);
	        map.put("sign",sign);
	        map.remove("appsecret");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	String a = HttpUtils.postParam("http://api-development.tq-service.com/ucenter/api/base/getcommunitylist", map);
    	JSONObject jsonObjectCommunity = JSONObject.parseObject(a);
    	List<JkCommunity>  communityList = JSONObject.parseArray(jsonObjectCommunity.getString("data"),JkCommunity.class);
    	int result = 0;
    	HashMap<String, Object> pars = new HashMap<>();
    	for (int i = 0; i < communityList.size(); i++) {	
    		pars = resultPars(); 			
			pars.put("projectId", communityList.get(i).getEsProjectId());
			try {
				sign = SignUtils.signUp(pars);
				pars.put("sign", sign);
			} catch (Exception e) {
				e.printStackTrace();
			}
			pars.remove("appsecret");
			String s = HttpUtils.postParam("http://api-development.tq-service.com/ucenter/api/base/getbuildlist", pars);
			JSONObject jsonObject = JSONObject.parseObject(s);
			System.out.println("code:"+jsonObject.getString("code"));
			System.out.println("msg:"+jsonObject.getString("msg"));
			if(jsonObject.getString("code").equals("200")) {
			List<JkBuild>  list = JSONObject.parseArray(jsonObject.getString("data"),JkBuild.class);
			result += jkBuildMapper.insertJkBuildList(list);
			}
			pars.clear();
		}
        return result;
    }
    
    public HashMap<String,Object> resultPars(){
        HashMap<String,Object> pars = new HashMap<>();
        pars.put("appid","tqtfohkydkh402ss91");
        pars.put("mchid","1877179068");
        pars.put("timestamp", System.currentTimeMillis());
        pars.put("noise", StringUtil.UUID());
        pars.put("appsecret","vfCnFw1nD3mbF6PL22IO2bsC2VehGRCJ");
        return pars;
    }
}
