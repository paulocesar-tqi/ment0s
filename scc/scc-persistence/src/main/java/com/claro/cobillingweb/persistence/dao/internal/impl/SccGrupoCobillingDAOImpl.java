/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccGrupoCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;

/**
 * @author 93046251
 *
 */
public class SccGrupoCobillingDAOImpl extends HibernateBasicDAOImpl<SccGrupoCobilling> implements SccGrupoCobillingDAO {

	@SuppressWarnings("unchecked")
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
		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccGrupoCobilling.class);
			criteria.add(Restrictions.eq("codigo", id));
			return  (SccGrupoCobilling)criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}

}
