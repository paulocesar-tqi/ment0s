package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;

public class SccOperadoraDAOImpl extends HibernateBasicDAOImpl<SccOperadora> implements SccOperadoraDAO {
	
	public List<SccOperadora> getAll() throws DAOException {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SccOperadora> pesquisaOperadorasExternas() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccOperadora.class);
			criteria.add(Restrictions.eq("cdTipoServico", "C"));
			criteria.addOrder(Order.asc("dsOperadora"));
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccOperadoraDAO.pesquisaOperadorasExternas");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SccOperadora> pesquisaHoldingClaro() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccOperadora.class);
			criteria.add(Restrictions.eq("cdTipoServico", "M"));		
			criteria.add(Restrictions.eqProperty("cdEot", "cdOperadoraHolding"));
			criteria.add(Restrictions.not(Restrictions.in("cdEot", new String[] {"410"})));
			criteria.addOrder(Order.asc("dsOperadora"));
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccOperadoraDAO.pesquisaHoldingClaro");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SccOperadora> pesquisaHoldingClaroByCdEotHolding(String cdEOT) throws DAOException{
		
		List<SccOperadora> list = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccOperadora.class);
			if(StringUtils.isNotEmpty(cdEOT) && !cdEOT.equals("*")){
				criteria.add(Restrictions.eq("cdOperadoraHolding", cdEOT));
			}
			criteria.add(Restrictions.eq("cdTipoServico", "M"));		
			criteria.add(Restrictions.eqProperty("cdEot", "cdOperadoraHolding"));
			criteria.add(Restrictions.not(Restrictions.in("cdEot", new String[] {"410"})));
			criteria.addOrder(Order.asc("dsOperadora"));
			list = (List<SccOperadora>) criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccOperadoraDAO.pesquisaHoldingClaro");
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<SccOperadora> pequisaOperadorasClaro() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccOperadora.class);
			//criteria.add(Restrictions.eq("cdTipoServico", "M"));
			criteria.add(Restrictions.not(Restrictions.in("cdEot", new String[] {"410"})));
			criteria.addOrder(Order.asc("dsOperadora"));
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccOperadoraDAO.pequisaOperadorasClaro");
		}
	}
	
	/* alteracao para atender ao controle de arquivo transmissao */
	@SuppressWarnings("unchecked")
	public List<SccOperadora> pequisaOperadorasClaroComM() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccOperadora.class);
			criteria.add(Restrictions.eq("cdTipoServico", "M"));
			criteria.add(Restrictions.not(Restrictions.in("cdEot", new String[] {"410"})));
			criteria.addOrder(Order.asc("dsOperadora"));
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccOperadoraDAO.pequisaOperadorasClaro");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SccOperadora> pesquisaOperadorasHolding(String cdEOT) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccOperadora.class);
			if(StringUtils.isNotEmpty(cdEOT) && !cdEOT.equals("*")){
				criteria.add(Restrictions.eq("cdOperadoraHolding", cdEOT));
			}
			criteria.add(Restrictions.eq("cdTipoServico", "M"));
			criteria.add(Restrictions.not(Restrictions.in("cdEot", new String[] {"410"})));
			criteria.addOrder(Order.asc("dsOperadora"));
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccOperadoraDAO.pesquisaOperadorasHolding");
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<SccOperadora> getAllCSP() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccOperadora.class);
			criteria.add(Restrictions.eq("cdTipoServico", "C"));
			criteria.add(Restrictions.not(Restrictions.in("cdCsp", new String[] {"96","91","43"})));
			criteria.addOrder(Order.asc("dsOperadora"));
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
	
}
