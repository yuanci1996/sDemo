package com.mapper;


import java.util.List;

import com.pojo.JkBuild;

/**
 * @Author: yuanci
 * @Date: 2018/11/9 10:58
 * @Version 1.0
 */
public interface JkBuildMapper extends BaseMapper<JkBuild> {
    public int insertJkBuildList(List<JkBuild> cpList);

}
