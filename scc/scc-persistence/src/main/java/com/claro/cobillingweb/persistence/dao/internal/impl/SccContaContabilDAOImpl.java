package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccContaContabilDAO;
import com.claro.cobillingweb.persistence.entity.SccContaContabil;

public class SccContaContabilDAOImpl extends HibernateBasicDAOImpl<SccContaContabil> implements SccContaContabilDAO {

	 
	public List<SccContaContabil> getAll() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccContaContabil.class);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	
}
