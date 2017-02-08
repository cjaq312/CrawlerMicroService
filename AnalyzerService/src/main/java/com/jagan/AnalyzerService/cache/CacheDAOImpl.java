package com.jagan.AnalyzerService.cache;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jagan.AnalyzerService.models.Product;

public class CacheDAOImpl implements CacheDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void insertProduct(Product p) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(p);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	public void insertProducts(List<Product> products) {
		for (Product p : products)
			insertProduct(p);
	}

	public void deleteProduct(String id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Product c1 = (Product) session.load(Product.class, id);
			session.delete(c1);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	public void deleteProducts(List<String> products) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			for (String p : products) {
				Product c1 = (Product) session.load(Product.class, p);
				session.delete(c1);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	public Product getProduct(String id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Product c1 = null;
		try {
			c1 = (Product) session.load(Product.class, id);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return c1;
	}

	public List<Product> getProducts() {
		List<Product> list = null;
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createSQLQuery("select * from products");
			list = q.list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	public List<Product> executeQuery(String query) {
		List<Product> list = null;
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createSQLQuery(query);
			list = q.list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return list;
	}

}
