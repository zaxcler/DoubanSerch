package com.zaxcler.infos;

import java.io.Serializable;



public class BookInfo implements Serializable{
	
	private int id;//图书id
	private String photo_url;//图书图片
	private String name;//图书名称
	private String auther;//图书作者
	private String describe;//图书描述
	private int score;//图书评分
	private String pubdate;//出版日期
	private String author_info;//作者信息
	private String translator;//翻译者
	private String catalog;//图书描述
	private String pages;//页数
	private String publisher;//出版商
	

	public BookInfo() {
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPhoto_url() {
		return photo_url;
	}


	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAuther() {
		return auther;
	}


	public void setAuther(String auther) {
		this.auther = auther;
	}


	public String getDescribe() {
		return describe;
	}


	public void setDescribe(String describe) {
		this.describe = describe;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public String getPubdate() {
		return pubdate;
	}


	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}


	public String getAuthor_info() {
		return author_info;
	}


	public void setAuthor_info(String author_info) {
		this.author_info = author_info;
	}


	public String getTranslator() {
		return translator;
	}


	public void setTranslator(String translator) {
		this.translator = translator;
	}


	public String getCatalog() {
		return catalog;
	}


	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}


	public String getPages() {
		return pages;
	}


	public void setPages(String pages) {
		this.pages = pages;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	

}
