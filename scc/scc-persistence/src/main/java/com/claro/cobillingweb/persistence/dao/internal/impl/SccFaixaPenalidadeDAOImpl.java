package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernateImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccFaixaPenalidadeDAO;
import com.claro.cobillingweb.persistence.entity.SccFaixaPenalidade;

@Repository
@Configurable(autowire=Autowire.NO)
public class SccFaixaPenalidadeDAOImpl extends FwjBaseDaoHibernateImpl<SccFaixaPenalidade, String> implements SccFaixaPenalidadeDAO {

	
	@SuppressWarnings("unchecked")
	public List<SccFaixaPenalidade> getAll() throws DAOException {
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccFaixaPenalidade.class);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

	 
	@SuppressWarnings("unchecked")
	@Override
	public List<SccFaixaPenalidade> pesquisarPorTipo(String tipo) throws DAOException {
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccFaixaPenalidade.class);
			criteria.add(Restrictions.eq("inTipoPenalidade", tipo));
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
		}
	}


	@Override
	public void deleteEntity(Long cdFaixaPenalidade) throws DAOException{
		try {
			String hql = "Delete SccFaixaPenalidade fp where fp.cdFaixaPenalidade = :cdFaixaPenalidade ";
			Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			query.setLong("cdFaixaPenalidade", cdFaixaPenalidade);
			query.executeUpdate();

			
		} catch (Exception e)
		{
		throw new DAOException(e.getMessage(), e);
	}
	}
	

		
	
}
