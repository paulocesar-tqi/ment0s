package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPreDominioDAO;
import com.claro.cobillingweb.persistence.entity.SccPreDominio;

public class SccPreDominioDAOImpl extends HibernateBasicDAOImpl<SccPreDominio> implements SccPreDominioDAO {

	 
	public List<SccPreDominio> getAll() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPreDominio.class);	
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}
	
	 
	public List<SccPreDominio> getTipos() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPreDominio.class);
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.distinct(Projections.property("id.tpDominio")));
			criteria.setProjection(projectionList);
			ResultTransformer resultTransformer = Transformers.aliasToBean(SccPreDominio.class);
			criteria.setResultTransformer(resultTransformer);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	 
	public List<SccPreDominio> pesquisaPorTipoDominio(String tipo) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPreDominio.class);
			criteria.add(Restrictions.eq("id.tpDominio", tipo));
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccPreDominioDAO.pesquisaPorTipoDominio");
			}	
	}

	
	
}
