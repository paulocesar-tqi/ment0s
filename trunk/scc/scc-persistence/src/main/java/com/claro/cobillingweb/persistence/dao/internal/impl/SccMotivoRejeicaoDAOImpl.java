package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccMotivoRejeicaoDAO;
import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;

public class SccMotivoRejeicaoDAOImpl extends HibernateBasicDAOImpl<SccMotivoRejeicao> implements SccMotivoRejeicaoDAO {

	 
	public List<SccMotivoRejeicao> getAll() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccMotivoRejeicao.class);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	
	
}
