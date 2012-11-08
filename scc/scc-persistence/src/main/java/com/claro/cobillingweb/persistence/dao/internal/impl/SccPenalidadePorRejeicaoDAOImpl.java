package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPenalidadePorRejeicaoDAO;
import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicao;

public class SccPenalidadePorRejeicaoDAOImpl extends HibernateBasicDAOImpl<SccPenalidadePorRejeicao> implements SccPenalidadePorRejeicaoDAO {

	 
	public List<SccPenalidadePorRejeicao> getAll() throws DAOException {
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPenalidadePorRejeicao.class);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

	 
	public List<SccPenalidadePorRejeicao> pesquisar(String cdEotLd, String cdMotivoRejeicao) throws DAOException {
		try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPenalidadePorRejeicao.class);
			
			if(cdEotLd != null && !cdEotLd.equals(BasicDAO.GET_ALL_STRING))
				criteria.add(Restrictions.eq("id.cdEotLd", cdEotLd));

			if(cdMotivoRejeicao != null && !cdMotivoRejeicao.equals(BasicDAO.GET_ALL_STRING))
				criteria.add(Restrictions.eq("id.cdMotivoRejeicao", cdMotivoRejeicao));
			
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}
		
}
