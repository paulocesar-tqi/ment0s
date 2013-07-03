/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernateImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccGrupoCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;

/**
 * @author 93046251
 *
 */
@Repository
@Configurable(autowire=Autowire.NO)
public class SccGrupoCobillingDAOImpl extends FwjBaseDaoHibernateImpl<SccGrupoCobilling, String> implements SccGrupoCobillingDAO {

/*	@SuppressWarnings("unchecked")
	@Override
	public List<SccGrupoCobilling> getAll() throws DAOException {
		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccGrupoCobilling.class);
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	@Override
	public List<SccGrupoCobilling> findAll() throws DAOException {
		
		return getAll();
	}

	@Override
	public SccGrupoCobilling findById(Long id) throws DAOException {
		
		return findByParametro(id);
		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccGrupoCobilling.class);
			criteria.add(Restrictions.eq("sqGrupo", id));
			criteria.setFetchMode("sccAssociacaoRelatorioGrupos", FetchMode.JOIN);
			criteria.setFetchMode("sccComposicaoGrupoEmails", FetchMode.JOIN);

			return  (SccGrupoCobilling)criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}
	
	private String gerarHql(){
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct g from SccGrupoCobilling g ");
		sql.append("left join g.sccAssociacaoRelatorioGrupos arg ");
		sql.append("left join g.sccComposicaoGrupoEmails cge ");
		sql.append("where g.sqGrupo = :sqGrupo ");
		return sql.toString();
	}
	private SccGrupoCobilling findByParametro(Long id) throws DAOException {
		SccGrupoCobilling entity = null;
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHql());
			query.setLong("sqGrupo", id);
			entity = (SccGrupoCobilling) query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return entity;
	}
	
	private String gerarSqlUpdate(){
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SccGrupoCobilling set noGrupo = :noGrupo , cdUsuarioManut = :cdUsuarioManut, dtAtualizacao = :dtAtualizacao ");
		sql.append("where sqGrupo = :sqGrupo");
		return sql.toString();
	}

	@Override
	public void updateGrupo(SccGrupoCobilling entity) throws DAOException {
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarSqlUpdate());
			query.setLong("sqGrupo", entity.getSqGrupo());
			query.setString("noGrupo", entity.getNoGrupo());
			query.setString("cdUsuarioManut", entity.getCdUsuarioManut());
			query.setDate("dtAtualizacao", entity.getDtAtualizacao());
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}

	@Override
	public void saveGrupo(SccGrupoCobilling entity) throws DAOException {
		// TODO Auto-generated method stub
		
	}
*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SccGrupoCobilling> pesquisarBySeqGrupo(Long seqGrupo) throws DAOException{
		
		List<SccGrupoCobilling> list = null;
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery("Select gpo from SccGrupoCobilling gpo where gpo.sqGrupo = :sqGrupo ");
			query.setParameter("sqGrupo", seqGrupo);
			list = (List<SccGrupoCobilling>) query.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;
		
	}
	private String gerarSqlUpdate(){
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE SccGrupoCobilling set noGrupo = :noGrupo , cdUsuarioManut = :cdUsuarioManut, dtAtualizacao = :dtAtualizacao ");
		sql.append("where sqGrupo = :sqGrupo");
		return sql.toString();
	}
	@Override
	public void updateGrupo(SccGrupoCobilling entity) throws DAOException {
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarSqlUpdate());
			query.setLong("sqGrupo", entity.getSqGrupo());
			query.setString("noGrupo", entity.getNoGrupo());
			query.setString("cdUsuarioManut", entity.getCdUsuarioManut());
			query.setDate("dtAtualizacao", entity.getDtAtualizacao());
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}
	
	@Override
	public void deleteGrupo(Long sqGrupo) throws DAOException{
		
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery("DELETE SccGrupoCobilling grupo where grupo.sqGrupo = :sqGrupo ");
			query.setLong("sqGrupo", sqGrupo);
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

}
