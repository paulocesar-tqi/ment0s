package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccConfigRepasseCobrancaDAO;
import com.claro.cobillingweb.persistence.entity.SccConfigRepasseCobranca;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPK;

public class SccConfigRepasseCobrancaDAOImpl extends HibernateBasicDAOImpl<SccConfigRepasseCobranca> implements SccConfigRepasseCobrancaDAO{

	 
	public List<SccConfigRepasseCobranca> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public List<SccConfigRepasseCobranca> carregaConfiguracaoRepasse(SccProdutoContratadoPK pk) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccConfigRepasseCobranca.class);
			criteria.setFetchMode("sccComposicaoProduto", FetchMode.JOIN);
			criteria.setFetchMode("sccProdutoContratado", FetchMode.JOIN);
			criteria.setFetchMode("statusCdrRepasse", FetchMode.JOIN);
			criteria.setFetchMode("statusCdrCobranca", FetchMode.JOIN);
			criteria.setFetchMode("sccProdutoContratado", FetchMode.JOIN);
			criteria.setFetchMode("sccProdutoContratado.sccContratoCobl", FetchMode.JOIN);
			criteria.setFetchMode("sccProdutoContratado.sccProdutoCobilling", FetchMode.JOIN);
			criteria.setFetchMode("sccComposicaoProduto", FetchMode.JOIN);
			criteria.setFetchMode("sccComposicaoProduto.sccItemCobilling", FetchMode.JOIN);
			criteria.add(Restrictions.eq("sccProdutoContratado.id", pk));
			
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	
	
}
