package com.mapper;


import java.util.List;

import com.pojo.JkCommunity;

/**
 * @Author: yuanci
 * @Date: 2018/11/9 10:58
 * @Version 1.0
 */
public interface JkCommunityMapper extends BaseMapper<JkCommunity> {
    public int insertJkCommunityList(List<JkCommunity> cpList);

}
