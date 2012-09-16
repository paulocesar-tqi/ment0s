package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPreFechamentoAssinaturaDAO;
import com.claro.cobillingweb.persistence.entity.SccPreFechamentoAssinatura;

public class SccPreFechamentoAssinaturaDAOImpl extends HibernateBasicDAOImpl<SccPreFechamentoAssinatura> implements SccPreFechamentoAssinaturaDAO {

	 
	public List<SccPreFechamentoAssinatura> getAll() throws DAOException {		
		return null;
	}

	 
	public List<SccPreFechamentoAssinatura> pesquisaAssinaturas(SccPreFechamentoAssinatura filtro) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPreFechamentoAssinatura.class);
			if ((filtro.getCdEOTClaro() != null) && (!filtro.getCdEOTClaro().equals(BasicDAO.GET_ALL_STRING)))
				criteria.add(Restrictions.eq("cdEOTClaro", filtro.getCdEOTClaro()));
			
			if ((filtro.getCdEOTLD() != null) && (!filtro.getCdEOTLD().equals(BasicDAO.GET_ALL_STRING)))
					criteria.add(Restrictions.eq("cdEOTLD", filtro.getCdEOTLD()));
			
			criteria.add(Restrictions.eq("cdProdutoPrepago", filtro.getCdProdutoPrepago()));
			criteria.add(Restrictions.eq("dtInicialFechamento", filtro.getDtInicialFechamento()));
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccPreFechamentoAssinaturaDAO.pesquisaAssinaturas");
			}
	}

	
	
}
