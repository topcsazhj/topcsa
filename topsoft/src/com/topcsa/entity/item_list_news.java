package com.topcsa.entity;

import java.io.Serializable;

public class item_list_news implements Serializable {
	private String title;
	private String author;
	private String date;
	
	
	public item_list_news(String title, String author, String date) {
		
		this.title = title;
		this.author = author;
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	

}
