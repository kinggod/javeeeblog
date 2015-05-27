package com.jinhan.dao;

import java.util.*;

import org.hibernate.*;

import utils.Chao;

import com.jinhan.bean.User;

public class UserDao {
	public static SessionFactory sessionFactory = utils.HibernateSessionFactory
			.getSessionFactory();
	/*得到所有用户人数*/
	public  int getTotalUser() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			 String hqlString = "select count(*) from User";   
			 Query query = session.createQuery(hqlString);   
			 int count = ((Number)query.uniqueResult()).intValue();
			tx.commit();
			return count;
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}finally{
			session.close();
		}
	}
	
	/*更新用户*/
	public String loadAndUpdateUser(int id,String userName,byte[]password){
		Session session=sessionFactory.openSession();
		Transaction tx=null;
		try{
			tx=session.beginTransaction();
			User m=(User) session.get(User.class, id);
			m.setUserName(userName);
			m.setPassword(password);
			session.update(m);
			tx.commit();
			return "yes";
		}catch(RuntimeException e){
			if (tx != null) {
				tx.rollback();
				return "no";
			}
			return "no";
		}
	}
	
	@SuppressWarnings("unchecked")
	/*得到所有用户*/
	public List<User> getUser(int pageStart, int pageSize) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<User> user = new ArrayList<User>();
		try {
			tx = session.beginTransaction();
			Query query= session.createQuery("from User");
			query.setFirstResult(pageStart);  
			query.setMaxResults(pageSize); 	
			user=query.list();
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
	public String addUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			return "yes";
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			return "no";
		}finally{
			session.close();
		}
	}
	/*删除用户*/
	public String deleteUser(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user=(User) session.get(User.class, id);
			session.delete(user);
			tx.commit();
			System.out.println("yes");
			return "yes";
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			return "no";
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
			return "no";
		}finally{
			session.close();
		}
	}

}
