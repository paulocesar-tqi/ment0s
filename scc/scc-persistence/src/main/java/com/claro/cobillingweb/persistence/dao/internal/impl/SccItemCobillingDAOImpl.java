package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccItemCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccItemCobilling;

public class SccItemCobillingDAOImpl extends HibernateBasicDAOImpl<SccItemCobilling> implements SccItemCobillingDAO{

	 
	public List<SccItemCobilling> getAll() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccItemCobilling.class);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	
}
