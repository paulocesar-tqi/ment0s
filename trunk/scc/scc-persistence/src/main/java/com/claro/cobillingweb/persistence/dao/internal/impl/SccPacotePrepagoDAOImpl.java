package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPacotePrepagoDAO;
import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;

public class SccPacotePrepagoDAOImpl extends HibernateBasicDAOImpl<SccPacotePrepago> implements SccPacotePrepagoDAO {

	 
	public List<SccPacotePrepago> getAll() throws DAOException {
		return null;
	}

	 
	public List<SccPacotePrepago> pesquisaPacotes(Long cdPacotePrepago,String noPacotePrepago, String cdProdutoPrepago) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPacotePrepago.class);
			if (cdPacotePrepago != null)
				criteria.add(Restrictions.eq("cdPacotePrepago", cdPacotePrepago));
			if ((noPacotePrepago != null) && (!noPacotePrepago.equals("")))
				criteria.add(Restrictions.like("noPacotePrepago", noPacotePrepago));
			if ((cdProdutoPrepago != null) && (!cdProdutoPrepago.equals(BasicDAO.GET_ALL_STRING)))
				criteria.add(Restrictions.eq("produtoPrepago.cdProdutoPrepago", cdProdutoPrepago));
			criteria.setFetchMode("produtoPrepago", FetchMode.JOIN);
			return criteria.list();			
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	
}
