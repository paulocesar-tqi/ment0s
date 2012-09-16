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
	
	public E getByPk(Serializable pk, Class entityClazz) throws DAOException {
		return (E)sessionFactory.getCurrentSession().get(entityClazz, pk);
	}
	
	public void delete(E entity) throws DAOException {
		try {
			sessionFactory.getCurrentSession().delete(entity);
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }
	}
	
	public Serializable create(E entity) throws DAOException {	
		try {
			return sessionFactory.getCurrentSession().save(entity);
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }
	}
	
	public void update(E entity) throws DAOException {
		try {
			sessionFactory.getCurrentSession().update(entity);
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
