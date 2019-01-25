package com.pojo;

public class Miniprogram {

	private String appid;
	private String pagepath;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPagepath() {
		return pagepath;
	}
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
	@Override
	public String toString() {
		return "Miniprogram [appid=" + appid + ", pagepath=" + pagepath + "]";
	}
	
}
