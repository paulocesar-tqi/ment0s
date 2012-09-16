package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccTipoEventoDAO;
import com.claro.cobillingweb.persistence.entity.SccTipoEvento;

public class SccTipoEventoDAOImpl extends HibernateBasicDAOImpl<SccTipoEvento> implements SccTipoEventoDAO {

	 
	public List<SccTipoEvento> getAll() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccTipoEvento.class);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	
	
}
