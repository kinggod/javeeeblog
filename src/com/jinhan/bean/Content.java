/**
 * 
 * 目录数据库+Bean
 * 作者：锦瀚
 * 时间：2015.5.26
 * 
 * */
package com.jinhan.bean;

import java.util.*;

public class Content {
	private int id;
	private String title;
	private String desc;
	private Date date;
	private int sort;
	private int rank;
	private int father;
	private Set<Page> pages=new HashSet<Page>();
	public Content() {
		super();
	}
	
	public Set<Page> getPages() {
		return pages;
	}

	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getFather() {
		return father;
	}
	public void setFather(int father) {
		this.father = father;
	}
}
