package com.pojo;

public class Picture {

	private int pic_id;
	private int article_id;
	private String pic_path;
	public Picture() {
		
	}
	public int getPic_id() {
		return pic_id;
	}
	public void setPic_id(int pic_id) {
		this.pic_id = pic_id;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public String getPic_path() {
		return pic_path;
	}
	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}
	@Override
	public String toString() {
		return "Picture [pic_id=" + pic_id + ", article_id=" + article_id + ", pic_path=" + pic_path + "]";
	}
	
}
