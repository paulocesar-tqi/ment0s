/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernateImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccAssociacaoRelatorioGrupoDAO;
import com.claro.cobillingweb.persistence.entity.SccAssociacaoRelatorioGrupo;

/**
 * @author 92038883
 *
 */
@Repository
public class SccAssociacaoRelatorioGrupoDAOImpl extends	FwjBaseDaoHibernateImpl<SccAssociacaoRelatorioGrupo, Long> implements	SccAssociacaoRelatorioGrupoDAO {
	
	
	private String gerarHql(){
		StringBuilder hql = new StringBuilder();
		hql.append("select arg from SccAssociacaoRelatorioGrupo arg ");
		hql.append("inner join fetch arg.sccGrupoCobilling grupo ");
		//hql.append("where arg.id.sqRelatorio = :sqRelatorio ");
		return hql.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SccAssociacaoRelatorioGrupo> buscarGrupoAssociado(Long id) throws DAOException{
		
		List<SccAssociacaoRelatorioGrupo> list = null;
		try {
			String hql = gerarHql() + " where arg.id.sqRelatorio = :sqRelatorio";
			Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			query.setLong("sqRelatorio", id);
			list = query.list();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;
	}
	
	@Override
	public SccAssociacaoRelatorioGrupo editEntity(Long sqGrupo, Long sqRelatorio, Date dtInicioVigencia) throws DAOException{
		
		SccAssociacaoRelatorioGrupo entity = null;
		try {
			String hql = gerarHql() + "where arg.id.sqRelatorio = :sqRelatorio ";
			hql = hql + "and arg.id.sqGrupo = :sqGrupo ";
			hql = hql + "and arg.id.dtInicioVigencia = :dtInicioVigencia";
			Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			query.setLong("sqGrupo", sqGrupo);
			query.setLong("sqRelatorio", sqRelatorio);
			query.setDate("dtInicioVigencia", dtInicioVigencia);
			entity = (SccAssociacaoRelatorioGrupo) query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return entity;
	}
	
	@Override
	@Transactional
	public void deleteEntity(Long sqGrupo, Long sqRelatorio, Date dtInicioVigencia) throws DAOException{
		
		try {
			
			String hql = "Delete SccAssociacaoRelatorioGrupo where id.sqRelatorio = :sqRelatorio and id.sqGrupo = :sqGrupo";// and id.dtInicioVigencia = :dtInicioVigencia";
			Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			query.setLong("sqGrupo", sqGrupo);
			query.setLong("sqRelatorio", sqRelatorio);
			//query.setTimestamp("dtInicioVigencia", dtInicioVigencia);
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
	


}
