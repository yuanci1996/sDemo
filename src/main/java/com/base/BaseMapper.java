
package com.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 说明: 通用Mapper
 *
 * @param <T>
 * @author liweibing
 * @since 2018年7月22日
 */
public interface BaseMapper<T> {

    /**
     * 说明: 根据条件查询
     *
     * @param obj
     * @return List<T>
     */
    public List<T> listByParams(Object obj);

    /**
     * 说明: 根据id查询
     *
     * @param id
     * @return T
     */
    public T seleteById(@Param("id") String id);

    public List<T> seleteByIds(@Param("list") List<String> list);

    /**
     * 说明: 插入
     *
     * @param entity
     * @return int
     */
    public int insert(T entity);

    /**
     * 说明: 批量插入
     *
     * @param entities
     * @return int
     */
    public int batchInsert(List<T> entities);

    /**
     * 说明: 更新
     *
     * @param entity
     * @return int
     */
    public int update(T entity);

    public int updateByParams(Map map);

    /**
     * 说明: 删除
     *
     * @param obj
     * @return int
     */
    public int delete(Object obj);

    /**
     * 批量删除
     *
     * @param uuids
     * @return int
     */
    public int batchDelete(List<String> uuids);

}
