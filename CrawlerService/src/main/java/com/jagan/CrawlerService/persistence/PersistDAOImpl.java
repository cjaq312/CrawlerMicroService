package com.jagan.CrawlerService.persistence;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.jagan.CrawlerService.models.ScrappedUrl;

public class PersistDAOImpl implements PersistDAO {
	private List<ScrappedUrl> list;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public void insertUrl(ScrappedUrl p) {
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

	public void insertUrls(List<ScrappedUrl> ScrappedUrls) {
		for (ScrappedUrl p : ScrappedUrls)
			insertUrl(p);
	}

	public void deleteUrl(String id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			ScrappedUrl c1 = (ScrappedUrl) session.load(ScrappedUrl.class, id);
			session.delete(c1);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	public void deleteUrls(List<String> ScrappedUrls) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			for (String p : ScrappedUrls) {
				ScrappedUrl c1 = (ScrappedUrl) session.load(ScrappedUrl.class, p);
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
	public List<ScrappedUrl> getUnvisitedUrls(String retailerName, String urlType) {

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			list = session
					.createQuery(
							"from ScrappedUrl where visited = false and retailerName = :retailerName and urlType = :urlType")
					.setParameter("retailerName", retailerName).setParameter("urlType", urlType).list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<ScrappedUrl> getVisitedUrls(String retailerName, String urlType) {

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			list = session
					.createQuery(
							"from ScrappedUrl where visited = true and retailerName = :retailerName and urlType = :urlType")
					.setParameter("retailerName", retailerName).setParameter("urlType", urlType).list();
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			session.close();
		}
		return list;
	}

	
}
