package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoContratadoPrepagoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoPrepagoDAO;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPrepago;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;

public class SccProdutoContratadoPrepagoDAOImpl extends HibernateBasicDAOImpl<SccProdutoContratadoPrepago> implements SccProdutoContratadoPrepagoDAO {

	private SccOperadoraDAO operadoraDAO;
	private SccProdutoPrepagoDAO produtoPrepagoDAO;
	
	 
	public List<SccProdutoContratadoPrepago> getAll() throws DAOException {
		return null;
	}

	 
	public List<SccProdutoPrepago> pesquisaProdutosContratadosEmpresa(String cdEOTClaro, String cdEOTLD, boolean holding)
			throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccProdutoContratadoPrepago.class);
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)))
				{
				if (holding)					
					criteria.add(Restrictions.in("id.cdEotClaro", getOperadoraDAO().pesquisaOperadorasHolding(cdEOTClaro)));					
				else					
					criteria.add(Restrictions.eq("id.cdEotClaro", cdEOTClaro));
				}
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING)))
				{
				criteria.add(Restrictions.eq("id.cdEotLd", cdEOTLD));
				}
			if ((cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)) && (cdEOTLD.equals(BasicDAO.GET_ALL_STRING)))
				{
				return getProdutoPrepagoDAO().getAll();
				}
			criteria.add(Restrictions.isNull("inDesabilitarRepasse"));
			List<SccProdutoContratadoPrepago> contratados = criteria.list();
			List<SccProdutoPrepago> produtos = new ArrayList<SccProdutoPrepago>();
			for (int i=0;i<contratados.size();i++)
				{
				SccProdutoContratadoPrepago item = contratados.get(i);
				item.getId().getCdProdutoPrepago();
				SccProdutoPrepago produtoPrepago = getProdutoPrepagoDAO().getByPk(item.getId().getCdProdutoPrepago(), SccProdutoPrepago.class);
				produtos.add(produtoPrepago);
				}
			return produtos;
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccProdutoContratadoPrepagoDAO.pesquisaProdutosContratadosEmpresa");
			}		
	}
	
	
	 
	public List<SccProdutoContratadoPrepago> pesquisaAcordosPrePagoEmpresa(String cdEOTClaro, String cdEOTLD, String cdProdutoPrepago,boolean holding)
			throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccProdutoContratadoPrepago.class);
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)))
				{
				if (holding)					
					criteria.add(Restrictions.in("id.cdEotClaro", getOperadoraDAO().pesquisaOperadorasHolding(cdEOTClaro)));					
				else					
					criteria.add(Restrictions.eq("id.cdEotClaro", cdEOTClaro));
				}
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING)))
				{
				criteria.add(Restrictions.eq("id.cdEotLd", cdEOTLD));
				}
			criteria.add(Restrictions.isNull("inDesabilitarRepasse"));
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccProdutoContratadoPrepagoDAO.pesquisaProdutosContratadosEmpresa");
			}	
		
	}

	public SccOperadoraDAO getOperadoraDAO() {
		return operadoraDAO;
	}

	public void setOperadoraDAO(SccOperadoraDAO operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}

	public SccProdutoPrepagoDAO getProdutoPrepagoDAO() {
		return produtoPrepagoDAO;
	}

	public void setProdutoPrepagoDAO(SccProdutoPrepagoDAO produtoPrepagoDAO) {
		this.produtoPrepagoDAO = produtoPrepagoDAO;
	}



	

	
	
}
