package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.JkCompany;
import com.service.JkBuildService;
import com.service.JkCommunityService;
import com.service.JkCompanyService;
import com.utils.SignUtils;
import com.utils.StringUtil;

import java.util.HashMap;

import javax.annotation.Resource;

/**
 * @Author: yuanci
 * @Date: 2018/11/9 13:57
 * @Version 1.0
 */
@Controller
@RequestMapping("jkBuild")
public class JkBuildAction {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JkBuildService jkBuildService;
    
    @RequestMapping("/getBuildList")
    @ResponseBody
    public int getBuildList() throws Exception{
        HashMap<String,Object> pars = resultPars();
        int total = jkBuildService.insertJkBuildList(pars);
        if (total > 0 ){
            return total;
        }else{
            return total;
        }
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
