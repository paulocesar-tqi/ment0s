package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccMapaStatusDAO;
import com.claro.cobillingweb.persistence.entity.SccMapaStatus;

public class SccMapaStatusDAOImpl extends HibernateBasicDAOImpl<SccMapaStatus> implements SccMapaStatusDAO {

	 
	public List<SccMapaStatus> getAll() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccMapaStatus.class);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

	 
	public List<SccMapaStatus> pesquisaMapaStatus(Long cdStatusInicial,Long cdStatusFinal) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccMapaStatus.class);
			if ((cdStatusInicial != null) && (!cdStatusInicial.equals(GET_ALL)))
				{
				criteria.add(Restrictions.eq("id.cdStatusDe", cdStatusInicial));
				}
			if ((cdStatusFinal != null) && (!cdStatusFinal.equals(GET_ALL)))
				{
				criteria.add(Restrictions.eq("id.cdStatusPara", cdStatusFinal));
				}
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	
	
}
