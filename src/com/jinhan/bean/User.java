/**
 * 
 * 用户数据库+Bean
 * 作者：锦瀚
 * 时间：2015.5.26
 * 
 * */
package com.jinhan.bean;
import javax.persistence.*;
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue
	private int id;
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private byte[] password;//没用到
	public User() {
		super();
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
