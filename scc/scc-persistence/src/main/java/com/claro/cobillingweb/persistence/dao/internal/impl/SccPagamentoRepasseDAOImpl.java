package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPagamentoRepasseDAO;
import com.claro.cobillingweb.persistence.entity.SccPagamentoRepasse;

public class SccPagamentoRepasseDAOImpl extends HibernateBasicDAOImpl<SccPagamentoRepasse> implements SccPagamentoRepasseDAO {

	 
	public List<SccPagamentoRepasse> getAll() throws DAOException {
		throw new UnsupportedOperationException();
	}

	 
	public SccPagamentoRepasse getPagamentoByRepasse(Long nuRepasse,String cdEOTClaro,String cdEOTLD) throws DAOException {
		SccPagamentoRepasse pagamentoRepasse = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPagamentoRepasse.class);
			criteria.add(Restrictions.eq("id.nuRepasse", nuRepasse));
			criteria.add(Restrictions.eq("id.cdEotHolding", cdEOTClaro));
			criteria.add(Restrictions.eq("id.cdEotLd", cdEOTLD));
			List<SccPagamentoRepasse> found = criteria.list();
			if ((found != null) && (found.size() > 0))
				{
				pagamentoRepasse = found.get(0);
				}
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
		return pagamentoRepasse;
	}

	
	
}
