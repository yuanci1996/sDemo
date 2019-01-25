package com.pojo;

/**
 * 分公司
 * @Author: yuanci
 * @Date: 2018/11/9 10:27
 * @Version 1.0
 */
public class JkCompany {
    //分公司id
    private String id;
    //分公司名称
    private String name;
    //省份 ID
    private String provinceId;
    //省份名称
    private String provinceName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
    
}
