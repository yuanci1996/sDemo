package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mapper.JkCompanyMapper;
import com.pojo.JkCompany;
import com.service.JkCompanyService;
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
@Service("jkCompanyService")
public class JkCompanyServiceImpl implements JkCompanyService {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JkCompanyMapper jkCompanyMapper;

    @Override
    public int insertJkCompanyList(HashMap<String,Object> map) {
        String s = HttpUtils.postParam("http://api-development.tq-service.com/ucenter/api/base/getcompanylist", map);
        JSONObject jsonObject = JSONObject.parseObject(s);
        int result = 0;
//        if (jsonObject.get("code").equals("200")){
            List<JkCompany>  list = JSONObject.parseArray(jsonObject.getString("data"),JkCompany.class);
            result = jkCompanyMapper.insertJkCompanyList(list);
//        }
        return result;
    }
    @Override
    public int insertJkCompany(JkCompany jkCompany) {
        return jkCompanyMapper.insertJkCompany(jkCompany);
    }
}
