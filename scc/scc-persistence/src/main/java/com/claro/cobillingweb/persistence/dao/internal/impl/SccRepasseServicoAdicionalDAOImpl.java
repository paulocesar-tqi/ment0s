package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRepasseServicoAdicionalDAO;
import com.claro.cobillingweb.persistence.entity.SccRepasseServicoAdicional;

public class SccRepasseServicoAdicionalDAOImpl extends HibernateBasicDAOImpl<SccRepasseServicoAdicional> implements SccRepasseServicoAdicionalDAO {

	private SccOperadoraDAO operadoraDAO;
	
	 
	public List<SccRepasseServicoAdicional> getAll() throws DAOException {	
		return null;
	}

	 
	public List<SccRepasseServicoAdicional> pesquisaAssinaturas(String cdEOTClaro,String cdEOTLD,	Long cdProdutoCobilling, Long cdPeriodicidade, Date dtInicioRepasse,boolean holding)
			throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccRepasseServicoAdicional.class);
			criteria.add(Restrictions.eq("eotLd", cdEOTLD));
			if (holding)
				criteria.add(Restrictions.in("operadoraClaro", getOperadoraDAO().pesquisaOperadorasHolding(cdEOTClaro)));
			else
				criteria.add(Restrictions.eq("operadoraClaro.cdEot", cdEOTClaro));
			criteria.add(Restrictions.eq("sccProdutoCobilling.cdProdutoCobilling", cdProdutoCobilling));			
			criteria.add(Restrictions.eq("nuQuinzena", cdPeriodicidade));
			criteria.add(Restrictions.eq("dtInicialRepasse", dtInicioRepasse));
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccRepasseServicoAdicionalDAO.pesquisaAssinaturas");
			}
	}

	public SccOperadoraDAO getOperadoraDAO() {
		return operadoraDAO;
	}

	public void setOperadoraDAO(SccOperadoraDAO operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}

	
	
	
}
