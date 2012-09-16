package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccServicoAdicionalDAO;
import com.claro.cobillingweb.persistence.entity.SccServicoAdicional;

public class SccServicoAdicionalDAOImpl extends HibernateBasicDAOImpl<SccServicoAdicional> implements SccServicoAdicionalDAO {

	 
	public List<SccServicoAdicional> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public List<SccServicoAdicional> pesquisaByProduto(Long cdProdutoCobilling) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccServicoAdicional.class);
			criteria.add(Restrictions.eq("sccProdutoCobilling.cdProdutoCobilling", cdProdutoCobilling));
			criteria.setFetchMode("sccProdutoCobilling", FetchMode.JOIN);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

	
	
}
