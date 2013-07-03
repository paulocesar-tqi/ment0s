package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Query;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernateImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccComposicaoProdutoDAO;
import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;

public class SccComposicaoProdutoDAOImpl extends FwjBaseDaoHibernateImpl<SccComposicaoProduto, String> implements SccComposicaoProdutoDAO {

	 
	public List<SccComposicaoProduto> getAll() throws DAOException {
		return null;
	}
	
	private String gerarSql(){
		StringBuilder sql = new StringBuilder();
		sql.append("select cp from SccComposicaoProduto cp ");
		sql.append("join cp.sccItemCobilling ic ");
		sql.append("join cp.sccProdutoCobilling pc ");
		sql.append("where cp.cdComponenteProduto = :cdComponente ");
		return sql.toString();
	}
	
	@Override
	public SccComposicaoProduto carregarEntidade(Long cdComponente) throws DAOException {
		SccComposicaoProduto entity = null;
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarSql());
			query.setParameter("cdComponente", cdComponente.longValue());
			entity = (SccComposicaoProduto) query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return entity;
	}
	
	
	private String gerarDelete(){
		StringBuilder hql = new StringBuilder();
		hql.append("Delete SccComposicaoProduto cp where cp.cdComponenteProduto = :cdComponente ");
		return hql.toString();
	}
	
	@Override
	public void deleteEntity(Long cdComponente) throws DAOException {
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarDelete());
			query.setLong("cdComponente", cdComponente);
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}


	
	
}
