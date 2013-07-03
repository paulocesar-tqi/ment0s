package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccMotivoRejeicaoDAO;
import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;

public class SccMotivoRejeicaoDAOImpl extends HibernateBasicDAOImpl<SccMotivoRejeicao> implements SccMotivoRejeicaoDAO {

	 
	public List<SccMotivoRejeicao> getAll() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccMotivoRejeicao.class);
			criteria.addOrder(Order.asc("cdMotivoRejeicao"));
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}
	

	public List<SccMotivoRejeicao> getAllConfigPenalidade() throws DAOException {
		try {
			List<String> ids = Arrays.asList("104","144","146","145","105","121","100","102","103","117","122","120","131","101","124","126","127","128","129","130","132","119","138","133","137","136","140","141","109","143","125","150","134","135","107","372","110");
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccMotivoRejeicao.class);
			criteria.add(Restrictions.in("cdMotivoRejeicao", ids));  
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

}
