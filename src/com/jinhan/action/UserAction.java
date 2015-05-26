/**
 * 
 * 用户动作
 * 作者：锦瀚
 * 时间：2015.5.24
 * 
 * */
package com.jinhan.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.jinhan.bean.User;
import com.jinhan.dao.UserDao;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>,
		SessionAware,ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID = 1264746309941722128L;
	private Map<String, Object> session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String code;
	private String message;
	private String password1;
	private User user;
	private UserDao dao = new UserDao();

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
	@Override
	public User getModel() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

	public String addUser() {
		String sessionCode = (String) session.get("sessionCode");
		if (sessionCode.equals(code)) {
			dao.addUser(user);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String hasUser() {
		String sessionCode = (String) session.get("sessionCode");
		String hasuser = dao.findUser(user.getUserName(), getPassword1());//对比
		if (sessionCode == null || !sessionCode.equals(code)) {
			message = "验证码错误";
		/*	System.out.println("验证码错误");*/
			return INPUT;
		} else if (hasuser.equals("yes")) {
			session.put("userName", user.getUserName());// 若登陆成功则将该user记录在session中
			System.out.println("用户存在");
			return SUCCESS;
		} else {
			message = "用户不存在或密码错误";
			System.out.println("用户不存在");
			return INPUT;
		}
	}

	public String listUser() throws IOException {
		List<User> users = new ArrayList<User>();
		users = dao.getUser();
		JSONArray jsonArray = JSONArray.fromObject(users);
		response.setContentType("text/json;charset=utf-8"); 
		response.setCharacterEncoding("UTF-8");   
		PrintWriter out = response.getWriter();   
		out.println(jsonArray);  
		out.close();
		return null;
	}
	
	public String test(){
		request.setAttribute("jinhan", "cheng");
		return SUCCESS;
	}

}
