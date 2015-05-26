/**
 * 
 * 文章数据库+Bean
 * 作者：锦瀚
 * 时间：2015.5.26
 * 
 * */
package com.jinhan.bean;

import java.util.Date;

public class Page {
	private int id;
	private int content;
	private String title;
	private String author;
	private String descshort;
	private String desc;
	private Date date;
	private int view;
	private int sort;
	public Page() {
		super();
	}
	public int getId() {
		return id;
	}
	public int getContent() {
		return content;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getDescshort() {
		return descshort;
	}
	public String getDesc() {
		return desc;
	}
	public Date getDate() {
		return date;
	}
	public int getView() {
		return view;
	}
	public int getSort() {
		return sort;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setContent(int content) {
		this.content = content;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setDescshort(String descshort) {
		this.descshort = descshort;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setView(int view) {
		this.view = view;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
