package com.jagan.SearchApiService.persistence;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jagan.SearchApiService.models.Product;

public class PersistDAOImpl implements PersistDAO {
	private List<Product> list;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public Product insertProduct(Product p) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Product product = null;
		try {
			session.save(p);
			product = p;
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return product;
	}

	public Product deleteProduct(String id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Product c1 = null;
		try {
			c1 = (Product) session.load(Product.class, id);
			session.delete(c1);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return c1;
	}


	@SuppressWarnings("unchecked")
	public Product getProduct(String id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		try {
			list = session.createQuery("from Product where id = :id").setParameter("id", id).list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		if (list != null)
			return list.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			list = session.createQuery("from Product").list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Product> executeQuery(String query) {

		if (query != null && !query.isEmpty())
			if (!query.startsWith("from"))
				query = query.substring(query.indexOf("from"));

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			list = session.createQuery(query).list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return list;
	}

}
