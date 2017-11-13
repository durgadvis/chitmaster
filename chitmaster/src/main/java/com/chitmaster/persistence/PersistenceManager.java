package com.chitmaster.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Component;

@Component
public class PersistenceManager {

	@Inject
	private HibernateTransactionManager transactionManager;
	
	private SessionFactory sessionFactory;
	
	@PostConstruct
	public void init() {
		if (sessionFactory == null) {
			initializeSessionFactory();
		}
	}
	
	private void initializeSessionFactory() {
		sessionFactory = transactionManager.getSessionFactory();
	}
	
	public void saveObject(Object object) {
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
		} catch (RuntimeException exception) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	public void updateObject(Object object) {
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		} catch (RuntimeException exception) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}
	
	public void deleteObject(Object object) {
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
		} catch (RuntimeException exception) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}
	
	public <X> X readObject(Class<X> clazz, Serializable primaryKey) {
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			return (X)session.get(clazz, primaryKey);
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	public <X> List<X> readObjectWithCriteria(Class<X> clazz, Criterion criterion) {
		List<X> listOfRows = new ArrayList<X>();
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			criteria.add(criterion);
			listOfRows = (List<X>) criteria.list();
			
			session.getTransaction().commit();
		} catch (RuntimeException exception) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return listOfRows;
	}
	
	public <X> List<X> readObjectWithCriteria(Class<X> clazz, LogicalExpression logicalExp) {
		List<X> listOfRows = new ArrayList<X>();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			criteria.add(logicalExp);
			listOfRows = (List<X>) criteria.list();
			
			session.getTransaction().commit();
		} catch (RuntimeException exception) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return listOfRows;
	}

	public <X> List<X> getAll(Class<X> clazz) {
		List<X> listOfRows = new ArrayList<X>();
		Session session = sessionFactory.openSession();
		String className = clazz.getName();
		try {
			session.beginTransaction();
			Query q = session.createQuery("from " + className);

            listOfRows = (List<X>) q.list();
            session.getTransaction().commit();
		} catch (RuntimeException exception) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return listOfRows;
	}
	
	public <X, Y> List<X> getByProjection(Class<Y> clazz, String property) {
		List<X> listOfRows = new ArrayList<X>();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz)
										.setProjection(Projections.property(property));
			listOfRows = (List<X>) criteria.list();
			
			session.getTransaction().commit();
		} catch (RuntimeException exception) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return listOfRows;
	}
	
	public <X, Y> List<X> getByCriteriaAndProjection(Class<Y> clazz, String property, Criterion criterion) {
		List<X> listOfRows = new ArrayList<X>();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz)
										.add(criterion)
										.setProjection(Projections.property(property));
			listOfRows = (List<X>) criteria.list();
			
			session.getTransaction().commit();
		} catch (RuntimeException exception) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return listOfRows;
	}
		
	public <X> List<X> getByQuery(Class<X> clazz, String sql) {
		List<X> listOfRows = new ArrayList<X>();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			TypedQuery<X> query = session.createQuery(sql, clazz);
			listOfRows = query.getResultList(); 
			session.getTransaction().commit();
		} catch (RuntimeException exception) {
			System.out.println(exception.getMessage());
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return listOfRows;
	}
}
