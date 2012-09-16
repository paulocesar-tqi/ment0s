package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccFaixaPenalidadeDAO;
import com.claro.cobillingweb.persistence.entity.SccFaixaPenalidade;

public class SccFaixaPenalidadeDAOImpl extends HibernateBasicDAOImpl<SccFaixaPenalidade> implements SccFaixaPenalidadeDAO {

	 
	public List<SccFaixaPenalidade> getAll() throws DAOException {
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccFaixaPenalidade.class);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

	 
	public List<SccFaixaPenalidade> pesquisarPorTipo(String tipo) throws DAOException {
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccFaixaPenalidade.class);
			criteria.add(Restrictions.eq("inTipoPenalidade", tipo));
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

		
	
}
