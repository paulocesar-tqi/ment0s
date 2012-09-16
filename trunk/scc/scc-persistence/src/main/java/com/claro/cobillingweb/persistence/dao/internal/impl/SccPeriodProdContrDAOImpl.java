package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPeriodProdContrDAO;
import com.claro.cobillingweb.persistence.entity.SccPeriodProdContr;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPK;

public class SccPeriodProdContrDAOImpl extends HibernateBasicDAOImpl<SccPeriodProdContr> implements SccPeriodProdContrDAO {

	 
	public List<SccPeriodProdContr> getAll() throws DAOException {
		return null;
	}

	 
	public List<SccPeriodProdContr> carregaPeriodicidadesContrato(SccProdutoContratadoPK pk) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPeriodProdContr.class);
			criteria.add(Restrictions.eq("sccProdutoContratado.id.cdContratoCobilling", pk.getCdContratoCobilling()));
			criteria.add(Restrictions.eq("sccProdutoContratado.id.cdProdutoCobilling", pk.getCdProdutoCobilling()));
			criteria.setFetchMode("sccProdutoContratado", FetchMode.JOIN);
			criteria.setFetchMode("sccProdutoContratado.sccContratoCobl", FetchMode.JOIN);
			criteria.setFetchMode("sccProdutoContratado.sccProdutoCobilling", FetchMode.JOIN);
			criteria.setFetchMode("sccPeriodicidadeRepasse", FetchMode.JOIN);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}	
	}

	
	
}
