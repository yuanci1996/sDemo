package com.pojo;

import java.util.List;

public class Article {

	private int article_id;
	private int sort_id;
	private String title;
	private String content;
	private String create_time;
	private int hits;
	private int likes;
	private int iselite;
	private String thumb;
	private List<Picture> picture;
	private Sort sort;
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public int getSort_id() {
		return sort_id;
	}
	public void setSort_id(int sort_id) {
		this.sort_id = sort_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getIselite() {
		return iselite;
	}
	public void setIselite(int iselite) {
		this.iselite = iselite;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public List<Picture> getPicture() {
		return picture;
	}
	public void setPicture(List<Picture> picture) {
		this.picture = picture;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	@Override
	public String toString() {
		return "Article [article_id=" + article_id + ", sort_id=" + sort_id + ", title=" + title + ", content="
				+ content + ", create_time=" + create_time + ", hits=" + hits + ", likes=" + likes + ", iselite="
				+ iselite + ", thumb=" + thumb + ", picture=" + picture + ", sort=" + sort + "]";
	}
	
}
