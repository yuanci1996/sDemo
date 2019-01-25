package com.mapper;


import java.util.List;

import com.pojo.JkCompany;

/**
 * @Author: yuanci
 * @Date: 2018/11/9 10:58
 * @Version 1.0
 */
public interface JkCompanyMapper extends BaseMapper<JkCompany> {
    public int insertJkCompanyList(List<JkCompany> cpList);

    public int insertJkCompany(JkCompany jkCompany);
}
