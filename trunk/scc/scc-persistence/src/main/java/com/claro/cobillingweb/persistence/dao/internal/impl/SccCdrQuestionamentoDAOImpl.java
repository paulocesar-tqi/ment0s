/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.support.DaoSupport;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccCdrQuestionamentoDAO;
import com.claro.cobillingweb.persistence.entity.SccCdrQuestionamento;

/**
 * @author vagner.souza
 *
 */
public class SccCdrQuestionamentoDAOImpl extends HibernateBasicDAOImpl<SccCdrQuestionamento> implements SccCdrQuestionamentoDAO{

	@Override
	public List<SccCdrQuestionamento> getAll() throws DAOException {
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SccCdrQuestionamento> listAll() throws DAOException{
		
		List<SccCdrQuestionamento> list = null;
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery("select distinct cdr from SccCdrQuestionamento cdr");
			list = (List<SccCdrQuestionamento>) query.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccCdrQuestionamentoDAO.listAll");
		}
		return list;
		
		
	}

	@Override
	public void updateAnaliseChamadas(SccCdrQuestionamento entidade)
			throws DAOException {
		
		
		String sql = "UPDATE SccCdrQuestionamento set inResultadoAnalise = :procedente, cdStatusAnalise = :statusAnalise "+
					"where sqRemessaQuestionamento = :sqRemessaQuestionamento and sqCdrQuestionamento = :sqCdrQuestionamento ";
		
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setString("procedente", entidade.getInResultadoAnalise());
			query.setString("statusAnalise", entidade.getCdStatusAnalise());
			query.setLong("sqRemessaQuestionamento", entidade.getSqRemessaQuestionamento());
			query.setLong("sqCdrQuestionamento", entidade.getSqCdrQuestionamento());
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccCdrQuestionamentoDAO.updateAnaliseChamadas");
		}
		
	}
	

}
