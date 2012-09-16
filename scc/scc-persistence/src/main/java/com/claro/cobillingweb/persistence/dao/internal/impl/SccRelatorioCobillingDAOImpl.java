/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling;

/**
 * @author 93046251
 *
 */
public class SccRelatorioCobillingDAOImpl extends HibernateBasicDAOImpl<SccRelatorioCobilling>
		implements SccRelatorioCobillingDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SccRelatorioCobilling> getAll() throws DAOException {
		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccRelatorioCobilling.class);
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		
		
	}

	@Override
	public List<SccRelatorioCobilling> findAll() throws DAOException {
		
		return getAll();
	}

	@Override
	public SccRelatorioCobilling findById(Long id) throws DAOException {
		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccRelatorioCobilling.class);
			criteria.add(Restrictions.eq("codigo", id));
			return  (SccRelatorioCobilling)criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}

}
