package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.JkCompany;
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
@RequestMapping("jkCompany")
public class JkCompanyAction {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JkCompanyService jkCompanyService;
    
    @RequestMapping("/getCompanyList")
    @ResponseBody
    public int getCompanyList() throws Exception{
        HashMap<String,Object> pars = resultPars();
        String sign = SignUtils.signUp(pars);
        pars.put("sign",sign);
        pars.remove("appsecret");
        int total = jkCompanyService.insertJkCompanyList(pars);
        if (total > 0 ){
            return total;
        }else{
            return total;
        }
    }

    @RequestMapping("/aa")
    public void ins(){
        JkCompany j=new JkCompany();
        j.setId("1");
        j.setName("2");
        j.setProvinceId("3");
        j.setProvinceName("4");
        System.out.println("2222");
        int a= jkCompanyService.insertJkCompany(j);
        System.out.println(a);
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
