package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccTipoArquivoDAO;
import com.claro.cobillingweb.persistence.entity.SccTipoArquivo;

public class SccTipoArquivoDAOImpl extends HibernateBasicDAOImpl<SccTipoArquivo> implements SccTipoArquivoDAO {

	 
	public List<SccTipoArquivo> getAll() throws DAOException {
		try {
		Query query = getSessionFactory().getCurrentSession().getNamedQuery("SccTipoArquivo.GET_ALL");
		return query.list();		
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(),"SccTipoArquivo.GET_ALL");
			}
	}

	 
	public List<Long> pesquisaPorTipoBatimento(String cdTipoBatimento) throws DAOException {
		try {
			Query query = getSessionFactory().getCurrentSession().getNamedQuery("SccTipoArquivo.BY_BATIMENTO");
			query.setString("cdBatimento", cdTipoBatimento);
			return query.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccTipoArquivo.BY_BATIMENTO");
			}
	}

	 
	public List<SccTipoArquivo> pesquisaEntidadePorTipoBatimento(String cdTipoBatimento) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccTipoArquivo.class);
			criteria.add(Restrictions.eq("cdTipoBatimento", cdTipoBatimento));
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccTipoArquivoDAO.pesquisaEntidadePorTipoBatimento");
			}		
	}

}
