package com.service;


import java.util.HashMap;
import java.util.List;

import com.pojo.JkCompany;

/**
 * @Author: yuanci
 * @Date: 2018/11/9 11:09
 * @Version 1.0
 */
public interface JkCompanyService {
    public int insertJkCompanyList(HashMap<String,Object> map);

    public int insertJkCompany(JkCompany jkCompany);
}
