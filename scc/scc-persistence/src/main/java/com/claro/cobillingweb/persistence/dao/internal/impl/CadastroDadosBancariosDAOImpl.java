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
import com.claro.cobillingweb.persistence.dao.internal.CadastroDadosBancariosDAO;
import com.claro.cobillingweb.persistence.entity.SccDadosBancario;

/**
 * @author 93046251
 *
 */
@Repository
@Configurable(autowire=Autowire.NO)
public class CadastroDadosBancariosDAOImpl extends FwjBaseDaoHibernateImpl<SccDadosBancario, String> implements CadastroDadosBancariosDAO {

	
	private String gerarHql(){
		StringBuilder hql = new StringBuilder();
		hql.append("select db from SccDadosBancario db ");
		hql.append("inner join fetch db.id.cdEotLd op ");
		return hql.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SccDadosBancario> listAll() throws DAOException{
		
		List<SccDadosBancario> list = null;
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHql());
			list = (List<SccDadosBancario>)query.list();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SccDadosBancario findById(String cdEotLd, String nuBanco, String nuAgencia) throws DAOException {

		SccDadosBancario entity = null;
		try {
			String sql = gerarHql() + " where op.cdEot = :cdEotLd and db.id.nuBanco = :nuBanco and db.id.nuAgencia = :nuAgencia ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setParameter("cdEotLd", cdEotLd);
			query.setParameter("nuBanco", nuBanco);
			query.setParameter("nuAgencia", nuAgencia);
			
			entity = (SccDadosBancario)query.uniqueResult();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return entity;
		
	}



	@Override
	public void deleteBancario(String cdEotLd, String nuBanco, String nuAgencia) throws DAOException{
		
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery("DELETE SccDadosBancario where id.cdEotLd.cdEot = :cdEotLd and id.nuBanco = :nuBanco and id.nuAgencia = :nuAgencia ");
			query.setParameter("cdEotLd", cdEotLd);
			query.setParameter("nuBanco", nuBanco);
			query.setParameter("nuAgencia", nuAgencia);
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

}
