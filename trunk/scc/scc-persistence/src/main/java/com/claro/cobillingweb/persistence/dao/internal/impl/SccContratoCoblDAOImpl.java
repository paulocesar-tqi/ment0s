package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccContratoCoblDAO;
import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;

public class SccContratoCoblDAOImpl extends HibernateBasicDAOImpl<SccContratoCobl> implements SccContratoCoblDAO {
	
	public List<SccContratoCobl> getAll() throws DAOException {
		try { 
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccContratoCobl.class);
			return criteria.list();
		} catch (Exception e)
		{
		throw new DAOException(e.getMessage(), e);
		}
	}
	
	public List<SccContratoCobl> pesquisaPorNome(String dsContrato) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccContratoCobl.class);
			criteria.add(Restrictions.eq("dsContratoCobilling", dsContrato));
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
		}		
	}
	
	public List<SccProdutoContratado> pesquisaProdutosContratados(Long cdContratoCobilling) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccProdutoContratado.class);
			criteria.add(Restrictions.eq("sccContratoCobl.cdContratoCobilling", cdContratoCobilling));
			criteria.setFetchMode("sccProdutoCobilling", FetchMode.JOIN);
			criteria.setFetchMode("sccContratoCobl", FetchMode.JOIN);
			criteria.createAlias("sccProdutoCobilling", "sccProdutoCobilling");
			criteria.add(Restrictions.isNotNull("sccProdutoCobilling.cdProdutoCobilling"));
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}		
	}
	
	public void delete(SccContratoCobl entity) throws DAOException {
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery("DELETE SccContratoCobl where cdContratoCobilling = :id");			
			query.setLong("id", entity.getCdContratoCobilling());
			query.executeUpdate();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
		}		
	}
	
	public void update(SccContratoCobl entity) throws DAOException {
		try {
			/*
			Query query = getSessionFactory().getCurrentSession().createQuery("DELETE SccContratoCobl where cdContratoCobilling = :id");			
			query.setLong("id", entity.getCdContratoCobilling());
			query.executeUpdate();
			*/
			/* CESAR SMITH 20/07/2012 */
			Query query = getSessionFactory().getCurrentSession().createQuery("UPDATE SccContratoCobl set dsContratoCobilling = :ds, dtInicioVigencia = :dtIni, dtFimVigencia = :dtFim where cdContratoCobilling = :id");			
			query.setLong("id", entity.getCdContratoCobilling());
			query.setString("ds", entity.getDsContratoCobilling());
			query.setDate("dtIni", entity.getDtInicioVigencia());
			query.setDate("dtFim", entity.getDtFimVigencia());
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}		
	}
	
}
