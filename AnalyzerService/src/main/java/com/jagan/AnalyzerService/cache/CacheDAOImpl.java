package com.jagan.AnalyzerService.cache;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jagan.AnalyzerService.models.Product;

public class CacheDAOImpl implements CacheDAO {

		private List<Product> list;
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
			if (list!=null)
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

			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			try {
				list = session.createSQLQuery(query).list();
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
			} finally {
				session.close();
			}
			return list;
		}

	}
