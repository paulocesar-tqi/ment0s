package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccCriticaPrebillingDAO;
import com.claro.cobillingweb.persistence.entity.SccCriticaPrebilling;

public class SccCriticaPrebillingDAOImpl extends HibernateBasicDAOImpl<SccCriticaPrebilling> implements SccCriticaPrebillingDAO {

	 
	public List<SccCriticaPrebilling> getAll() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccCriticaPrebilling.class);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	
	
}
