package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoCreditoDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCredito;

public class SccArquivoCreditoDAOImpl extends HibernateBasicDAOImpl<SccArquivoCredito> implements SccArquivoCreditoDAO {

	 
	public List<SccArquivoCredito> getAll() throws DAOException {
		return null;
	}

	 
	public List<SccArquivoCredito> pesquisaArquivosCredito(String origem,String status, Date dataInicial, Date dataFinal) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccArquivoCredito.class);
			if ((origem != null) && (!origem.equals(GET_ALL_STRING)))
				{
				criteria.add(Restrictions.eq("cdOrigem", origem));
				}
			if ((status != null) && (!status.equals(GET_ALL_STRING)))
				{
				criteria.add(Restrictions.eq("cdStatus", status));
				}
			criteria.add(Restrictions.between("dtCarga", dataInicial, dataFinal));
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

	
	
}
