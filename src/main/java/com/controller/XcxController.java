package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.Article;
import com.pojo.Picture;
import com.pojo.Sort;

@Controller
@RequestMapping(value="/api")
public class XcxController {

	@RequestMapping(value="/getArticle")
	@ResponseBody
	public List<Article> getArticle() {
		int a=0;
		Article article=new Article();
		Picture pic=new Picture();
		Sort sort=new Sort();
		sort.setSort_id(1);sort.setSort_name("rpg");
		pic.setPic_path("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=1335295803,2282322221&fm=96&app=25&f=JPEG?w=121&h=75&s=59164598560778EC3B18C952030060EB");
		List<Picture> piclist=new ArrayList<Picture>();
		piclist.add(pic);
		article.setPicture(piclist);
		article.setSort(sort);
		article.setArticle_id(1);
		article.setContent("wo是文章主体内容。");
		article.setCreate_time("2018-8-30");
		article.setHits(5000);
		article.setIselite(1);
		article.setLikes(200);
		article.setTitle("我是标题");
		article.setThumb("https://ss0.baidu.com/73t1bjeh1BF3odCf/it/u=656097665,2045104178&fm=85&s=3831369C564353514E048D7D0300D073");
		a=a++;
		System.out.println("------------------------->"+a);
		List<Article> aList=new ArrayList<Article>();
		aList.add(article);
	    return aList;
	}
}
