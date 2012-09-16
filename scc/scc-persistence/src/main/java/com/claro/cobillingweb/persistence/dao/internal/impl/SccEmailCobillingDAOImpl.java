/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccEmailCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;

/**
 * @author 93046251
 *
 */
public class SccEmailCobillingDAOImpl extends HibernateBasicDAOImpl<SccEmailCobilling>
		implements SccEmailCobillingDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SccEmailCobilling> getAll() throws DAOException {
		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccEmailCobilling.class);
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}

	}

	@Override
	public List<SccEmailCobilling> findAll() throws DAOException {
		
		return getAll();
	}

	@Override
	public SccEmailCobilling findById(Long id) throws DAOException {
		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccEmailCobilling.class);
			criteria.add(Restrictions.eq("codigo", id));
			return  (SccEmailCobilling)criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}

	}

}
