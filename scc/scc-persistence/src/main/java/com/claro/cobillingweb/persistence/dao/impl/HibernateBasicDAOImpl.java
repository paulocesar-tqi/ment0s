package com.claro.cobillingweb.persistence.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;

/**
 * Implementacao da interface de DAO utilizando como framework o Hibernate 4 .
 *
 */
public abstract class HibernateBasicDAOImpl<E> implements BasicDAO<E>{

	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public E getByPk(Serializable pk, Class entityClazz) throws DAOException {
		return (E)sessionFactory.getCurrentSession().get(entityClazz, pk);
	}
	
	
	
	public void delete(E entity) throws DAOException {
		try {
			sessionFactory.getCurrentSession().delete(entity);
			sessionFactory.getCurrentSession().flush();
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }
	}
	
	public Serializable create(E entity) throws DAOException {	
		try {
			return save(entity);
		} catch (Exception e) { 
			throw new DAOException(e.getMessage(), e); 
		}
	}
	
	public Serializable save(E entity) throws DAOException {
		Serializable id = sessionFactory.getCurrentSession().save(entity);
		sessionFactory.getCurrentSession().flush();
		return id;
	}
	
	public void update(E entity) throws DAOException {
		try {
			sessionFactory.getCurrentSession().update(entity);
			sessionFactory.getCurrentSession().flush();
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }
	}
	

	public void saveOrUpdate(E entity) throws DAOException {
		
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(entity);
			sessionFactory.getCurrentSession().flush();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}
	
	

	@Override
	public void merge(E entity) throws DAOException {
		try {
			sessionFactory.getCurrentSession().merge(entity);
			sessionFactory.getCurrentSession().flush();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}



	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
