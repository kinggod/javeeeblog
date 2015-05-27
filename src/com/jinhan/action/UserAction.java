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

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import utils.Chao;

import com.jinhan.bean.User;
import com.jinhan.dao.UserDao;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>,
		SessionAware, ServletRequestAware, ServletResponseAware {
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
		this.response = response;
	}

	@Override
	public User getModel() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

	public void deleteUser(){
		String id = request.getParameter("id");
		System.out.println(id);
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			if(id==null){
				out.print("error");
			}
			else{
				int i=Integer.parseInt(id);
				String deleteuser=dao.deleteUser(i);
				System.out.println(deleteuser);
				if(deleteuser.equals("yes")){
					out.print("success");
				}
				else{
					out.print("error");
				}
			}
			out.close();
		} catch (IOException e) {
		}
	}
	
	public void addUser() {
		/* 获取表单 */
		String username = user.getUserName();
		System.out.println(username);
		String password1 = getPassword1();
		System.out.println(password1);
		String id = request.getParameter("id");
		if (username != null && password1 != null) {
			/* 加密 */
			byte[] password = Chao.md5(password1);
			/* 传送信息 */
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			/* 更新信息 */
			if (!id.equals("")) {
				System.out.println("更改用户");
				System.out.println("用户标志是"+id);
				int i = Integer.parseInt(id);
				System.out.println(i);
				String updateuser = dao.loadAndUpdateUser(i, username, password);
				System.out.println(updateuser);
				if (updateuser.equals("yes")) {
					System.out.println("成功插入");
					try {
						out = response.getWriter();
						out.print("success");
					} catch (IOException e) {
					} finally {
						out.close();
					}
				}
			}

			else {
				/* 插入用户 */
				System.out.println("增加用户");
				User u = new User();
				u.setUserName(username);
				u.setPassword(password);
				String adduser = dao.addUser(u);

				try {
					out = response.getWriter();
					if (adduser.equals("yes")) {
						System.out.println("success");
						out.print("success");
					} else {
						System.out.println("error");
						out.print("error");
					}
				} catch (IOException e) {
				} finally {
					out.close();
				}
			}
		}
		else{
			System.out.println("什么都没有");
		}
	}

	public String hasUser() {
		String sessionCode = (String) session.get("sessionCode");
		String hasuser = dao.findUser(user.getUserName(), getPassword1());// 对比
		if (sessionCode == null || !sessionCode.equals(code)) {
			message = "验证码错误";
			/* System.out.println("验证码错误"); */
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
		int total = dao.getTotalUser();
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		int page = Integer.parseInt(request.getParameter("page"));
		int pageStart = (page - 1) * pageSize;
		List<User> users = dao.getUser(pageStart, pageSize);
		response.setContentType("text/json;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", users);
		JSONObject json = JSONObject.fromObject(map);
		out.print(json.toString());
		out.close();
		return null;
	}

	public String test() {
		request.setAttribute("jinhan", "cheng");
		return SUCCESS;
	}

}
