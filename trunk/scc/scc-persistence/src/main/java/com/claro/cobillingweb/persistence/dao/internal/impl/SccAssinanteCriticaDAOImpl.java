package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccAssinanteCriticaDAO;
import com.claro.cobillingweb.persistence.entity.SccAssinanteCritica;

public class SccAssinanteCriticaDAOImpl extends HibernateBasicDAOImpl<SccAssinanteCritica> implements SccAssinanteCriticaDAO {

	 
	public List<SccAssinanteCritica> getAll() throws DAOException {
		return null;
	}

	 
	public List<SccAssinanteCritica> pesquisaCritica(String cdCritica) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccAssinanteCritica.class);
			if ((cdCritica != null) && (!cdCritica.equals(GET_ALL_STRING)))
				{
				criteria.add(Restrictions.eq("criticaPreBilling.cdCritica", cdCritica));
				}			
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	
	
}
