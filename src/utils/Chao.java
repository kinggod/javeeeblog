package utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Chao {

	public Chao() {
		// TODO Auto-generated constructor stub
	}

	public static void forword(HttpServletRequest request,
			HttpServletResponse response, String uri) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(uri);
		rd.forward(request, response);
	}

	public static java.sql.Date converUtilToSql(java.util.Date udate) {
		return new java.sql.Date(udate.getTime());
	}

	/**
	 * 将java.sql.Date日期转化为java.util.Date
	 * 
	 * @param udate
	 * @return
	 */
	public static java.util.Date converSqlToUtil(java.sql.Date udate) {
		return new java.util.Date(udate.getTime());
	}

	public static java.sql.Date now() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	public static String localtime(java.sql.Date udate) {
		java.util.Date date = converSqlToUtil(udate);
		SimpleDateFormat time = new SimpleDateFormat("yyyy年MM月dd日");
		return time.format(date);
	}

	public static String safeInput(String htmlStr) {
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		htmlStr = htmlStr.replace(" ", "");
		htmlStr = htmlStr.replaceAll("\\s*|\t|\r|\n", "");
		htmlStr = htmlStr.replace("“", "");
		htmlStr = htmlStr.replace("”", "");
		htmlStr = htmlStr.replaceAll("　", "");

		return htmlStr.trim(); // 返回文本字符串
	}

	public static byte[] md5(String password) {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[12];
		byte[] encryptPassword = null;
		// 生成12位的随机值
		random.nextBytes(salt);
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 通过update()方法依次对salt盐及用户输入的密码password进行加密
		messageDigest.update(salt);
		messageDigest.update(password.getBytes());
		byte[] digest = messageDigest.digest();
		encryptPassword = new byte[digest.length + 12];
		// 数据库中所保存的密码由salt及digest组成
		System.arraycopy(salt, 0, encryptPassword, 0, 12);
		System.arraycopy(digest, 0, encryptPassword, 12, digest.length);
		return encryptPassword;
	}

	/**
	 * 该方法完成登录时密码的验证
	 * 
	 * @param password
	 * @param encryptPassword
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static boolean vmd5(String password, byte[] md5) {
		byte[] salt = new byte[12];
		// 从encryptPassword这一数据库中保存的密码中取得12位的随机值
		System.arraycopy(md5, 0, salt, 0, 12);
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageDigest.update(salt);
		messageDigest.update(password.getBytes());
		byte[] digest = messageDigest.digest();
		byte[] digestInDB = new byte[md5.length - 12];
		System.arraycopy(md5, 12, digestInDB, 0, md5.length - 12);
		// 比较重新加密后的值与数据库中保存的密码（去掉salt之后的值）是否相等
		if (Arrays.equals(digest, digestInDB)) {
			return true;
		} else {
			return false;
		}
	}

}
