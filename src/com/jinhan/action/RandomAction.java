/**
 * 
 * 验证码生成器动作
 * 作者：锦瀚
 * 时间：2015.5.24
 * 
 * */
package com.jinhan.action;

import java.io.ByteArrayInputStream;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import utils.RandomNumUtil;

public class RandomAction extends ActionSupport {
	private static final long serialVersionUID = -5046423922828931251L;
	private ByteArrayInputStream inputStream;

	public String execute() throws Exception {
		RandomNumUtil rdnu = RandomNumUtil.Instance();
		this.setInputStream(rdnu.getImage());// 取得带有随机字符串的图片
		ActionContext.getContext().getSession()
				.put("sessionCode", rdnu.getString());// 取得随机字符串放入HttpSession
		return SUCCESS;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
}
