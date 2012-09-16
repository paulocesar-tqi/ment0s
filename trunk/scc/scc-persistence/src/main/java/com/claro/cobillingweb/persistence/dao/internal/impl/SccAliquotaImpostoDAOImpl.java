package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccAliquotaImpostoDAO;
import com.claro.cobillingweb.persistence.entity.SccAliquotaImposto;

public class SccAliquotaImpostoDAOImpl extends HibernateBasicDAOImpl<SccAliquotaImposto> implements SccAliquotaImpostoDAO {
	
	public List<SccAliquotaImposto> getAll() throws DAOException {
		return null;
	}
	
	public List<SccAliquotaImposto> pesquisaAliquotas(String noImposto,String inPlataforma) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccAliquotaImposto.class);
			
			if ((inPlataforma != null) && (!inPlataforma.equals(GET_ALL_STRING)))
			{
				criteria.add(Restrictions.eq("id.inPlataformaTarifacao", inPlataforma));
			}
			if ((noImposto != null) && (!noImposto.equals(GET_ALL_STRING)))
			{
				criteria.add(Restrictions.eq("id.noImposto", noImposto));
			}
			
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
	
}
