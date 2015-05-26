package com.jinhan.dao;

import java.util.*;

import org.hibernate.*;

import com.jinhan.bean.Content;
public class ContentDao {
	public static SessionFactory sessionFactory = utils.HibernateSessionFactory
			.getSessionFactory();

	@SuppressWarnings("unchecked")
	public List<Content> getContent() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Content> content = new ArrayList<Content>();
		try {
			tx = session.beginTransaction();
			content = session.createQuery("from Content").list();
			tx.commit();
			return content;
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}finally{
			session.close();
		}
	}

	public void addContent(Content  content) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(content);
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
	
	public void deleteContent(Content content) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(content);
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
	public static void main(String[] agr){
		List<Content> content=new ContentDao().getContent();
		for(Content s:content){
			System.out.println(s.getId());
		}
	}

}
