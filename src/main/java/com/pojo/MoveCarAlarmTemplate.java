package com.pojo;

import java.util.Map;

public class MoveCarAlarmTemplate {
private String touser;
	
	private String template_id;
	
	private Miniprogram miniprogram;
	 
	private Map<String, TemplateData> data;
 
	public Miniprogram getMiniprogram() {
		return miniprogram;
	}

	public void setMiniprogram(Miniprogram miniprogram) {
		this.miniprogram = miniprogram;
	}

	public String getTouser() {
		return touser;
	}
 
	public void setTouser(String touser) {
		this.touser = touser;
	}
 
	public String getTemplate_id() {
		return template_id;
	}
 
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

 
	public Map<String, TemplateData> getData() {
		return data;
	}
 
	public void setData(Map<String, TemplateData> data) {
		this.data = data;
	}


}
