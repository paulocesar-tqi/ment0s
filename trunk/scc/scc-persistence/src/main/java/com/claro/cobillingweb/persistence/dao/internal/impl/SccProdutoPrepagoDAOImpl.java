package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;
import java.math.BigDecimal;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoPrepagoDAO;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

public class SccProdutoPrepagoDAOImpl extends HibernateBasicDAOImpl<SccProdutoPrepago> implements SccProdutoPrepagoDAO{

	 
	public List<SccProdutoPrepago> getAll() throws DAOException {
		try {
		return getSessionFactory().getCurrentSession().getNamedQuery("SccProdutoPrepago.GET_ALL").list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(),e);
			}
	}

	 
	public List<SccProdutoPrepago> pesquisaProdutosOperadoras(
			String cdEOTClaro, String cdEOTLD) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

		public Integer proxSequence() throws DAOException {
		try {
			Query query = getSessionFactory().getCurrentSession().createSQLQuery("SELECT NVL(MAX(TO_NUMBER(CD_PRODUTO_PREPAGO)),0)+1 CD_PRODUTO_PREPAGO FROM SCC_PRODUTO_PREPAGO");
			
			BigDecimal pxSeq = (BigDecimal) query.list().get(0);
			Integer cdProduto =  pxSeq.intValue();
		
			return cdProduto;
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}
	
}
