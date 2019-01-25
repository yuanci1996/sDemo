package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mapper.JkCommunityMapper;
import com.pojo.JkCommunity;
import com.service.JkCommunityService;
import com.utils.HttpUtils;

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
@Service("jkCommunityService")
public class JkCommunityServiceImpl implements JkCommunityService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JkCommunityMapper jkCommunityMapper;

    @Override
    public int insertJkCommunityList(HashMap<String,Object> map) {
        String s = HttpUtils.postParam("http://api-development.tq-service.com/ucenter/api/base/getcommunitylist", map);
        JSONObject jsonObject = JSONObject.parseObject(s);
        int result = 0;
//        if (jsonObject.get("code").equals("200")){
            List<JkCommunity>  list = JSONObject.parseArray(jsonObject.getString("data"),JkCommunity.class);
            result = jkCommunityMapper.insertJkCommunityList(list);
//        }
        return result;
    }
}
