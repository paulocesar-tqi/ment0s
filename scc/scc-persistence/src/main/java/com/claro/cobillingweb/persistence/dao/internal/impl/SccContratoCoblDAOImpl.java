package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccContratoCoblDAO;
import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;

public class SccContratoCoblDAOImpl extends HibernateBasicDAOImpl<SccContratoCobl> implements SccContratoCoblDAO {
	
	@SuppressWarnings("unchecked")
	public List<SccContratoCobl> getAll() throws DAOException {
		try { 
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccContratoCobl.class);
			return criteria.list();
		} catch (Exception e)
		{
		throw new DAOException(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings("unchecked")
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
	
	private String gerarHql(){
		StringBuilder hql = new StringBuilder();
		hql.append("select pct from SccProdutoContratado pct ");
		hql.append("inner join fetch pct.sccContratoCobl as cbl ");
		hql.append("inner join fetch pct.sccProdutoCobilling as pcb ");
		hql.append("where cbl.cdContratoCobilling = :cdContratoCobilling ");
		hql.append("and pcb.cdProdutoCobilling is not null");
		return hql.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SccProdutoContratado> pesquisaProdutosContratados(Long cdContratoCobilling) throws DAOException {
		
		List<SccProdutoContratado> list = null;
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHql());
			query.setLong("cdContratoCobilling", cdContratoCobilling);
			list = (List<SccProdutoContratado>) query.list();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
		
	}
	
/*	public List<SccProdutoContratado> pesquisaProdutosContratados(Long cdContratoCobilling) throws DAOException {
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
*/	
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
