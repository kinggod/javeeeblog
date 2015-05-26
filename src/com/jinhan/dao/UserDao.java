package com.jinhan.dao;

import java.util.*;

import org.hibernate.*;

import utils.Chao;

import com.jinhan.bean.User;

public class UserDao {
	public static SessionFactory sessionFactory = utils.HibernateSessionFactory
			.getSessionFactory();

	@SuppressWarnings("unchecked")
	/*得到所有用户*/
	public List<User> getUser() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<User> user = new ArrayList<User>();
		try {
			tx = session.beginTransaction();
			user = session.createQuery("from User").list();
			tx.commit();
			return user;
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}finally{
			session.close();
		}
	}
	/*添加用户*/
	public void addUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}finally{
			session.close();
		}
	}
	/*删除用户*/
	public void deleteUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}finally{
			session.close();
		}
	}
	
	/*验证用户*/
	public String findUser(String userName,String bs) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query=session.createQuery("from User as m where m.userName=:userName");
			query.setString("userName", userName);
			@SuppressWarnings("unchecked")
			List<User> result=query.list();
			tx.commit();
			if(result!=null && result.size()>0){
				byte[] passwd=result.get(0).getPassword();//得到数据库中二进制
				if(Chao.vmd5(bs, passwd)){
				System.out.println("has");
				return "yes";
				}
				else{
					return "no";
				}
			}else{	
				return "no";
			}
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}finally{
			session.close();
		}
	}

}
