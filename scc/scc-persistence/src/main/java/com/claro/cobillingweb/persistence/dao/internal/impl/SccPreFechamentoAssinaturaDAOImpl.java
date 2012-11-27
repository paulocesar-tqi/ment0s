package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPreFechamentoAssinaturaDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreFechamentoAssinatura;

public class SccPreFechamentoAssinaturaDAOImpl extends HibernateBasicDAOImpl<SccPreFechamentoAssinatura> implements
		SccPreFechamentoAssinaturaDAO {
	
	private SccOperadoraDAOImpl operadoraDAO;
	
	public List<SccPreFechamentoAssinatura> getAll() throws DAOException {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<SccPreFechamentoAssinatura> pesquisaAssinaturas(SccPreFechamentoAssinatura filtro) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(SccPreFechamentoAssinatura.class);
			if ((filtro.getCdEOTClaro() != null) && (!filtro.getCdEOTClaro().equals(BasicDAO.GET_ALL_STRING)))
				criteria.add(Restrictions.eq("cdEOTClaro", filtro.getCdEOTClaro()));

			if ((filtro.getCdEOTLD() != null) && (!filtro.getCdEOTLD().equals(BasicDAO.GET_ALL_STRING)))
				criteria.add(Restrictions.eq("cdEOTLD", filtro.getCdEOTLD()));

			criteria.add(Restrictions.eq("cdProdutoPrepago", filtro.getCdProdutoPrepago()));
			criteria.add(Restrictions.eq("dtInicialFechamento", filtro.getDtInicialFechamento()));
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccPreFechamentoAssinaturaDAO.pesquisaAssinaturas");
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<SccPreFechamentoAssinatura> pesquisaAssinaturasHolding(SccPreFechamentoAssinatura filtro) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPreFechamentoAssinatura.class);
			criteria.add(Restrictions.eq("cdEOTLD", filtro.getCdEOTLD()));			
			criteria.add(Restrictions.in("cdEOTClaro", sccOperadora2String(getOperadoraDAO().pesquisaOperadorasHolding(filtro.getCdEOTClaro()))));
			criteria.add(Restrictions.eq("cdProdutoPrepago", filtro.getCdProdutoPrepago()));
			criteria.add(Restrictions.eq("dtInicialFechamento", filtro.getDtInicialFechamento()));

			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.sum("qtCdrs").as("qtCdrs"));
			projectionList.add(Projections.sum("qtCdrsOm").as("qtCdrsOm"));
			projectionList.add(Projections.sum("qtAssinaturas").as("qtAssinaturas"));
			projectionList.add(Projections.sum("qtdAssinaturasOm").as("qtdAssinaturasOm"));
			projectionList.add(Projections.sum("qtMinutosConcedidos").as("qtMinutosConcedidos"));
			projectionList.add(Projections.sum("qtMinutosConcedidosOm").as("qtMinutosConcedidosOm"));
			projectionList.add(Projections.sum("qtMinutosExpirados").as("qtMinutosExpirados"));
			projectionList.add(Projections.sum("qtMinutosExpiradosOm").as("qtMinutosExpiradosOm"));
			
			projectionList.add(Projections.sum("qtMinutosUtilizados").as("qtMinutosUtilizados"));
			projectionList.add(Projections.sum("qtMinutosUtilizadosOm").as("qtMinutosUtilizadosOm"));
			projectionList.add(Projections.sum("vlBrutoAssinatura").as("vlBrutoAssinatura"));
			projectionList.add(Projections.sum("vlBrutoAssinaturaOm").as("vlBrutoAssinaturaOm"));
			
			projectionList.add(Projections.sum("vlCofins").as("vlCofins"));
			projectionList.add(Projections.sum("vlCofinsOm").as("vlCofinsOm"));
			projectionList.add(Projections.sum("vlCusto").as("vlCusto"));
			projectionList.add(Projections.sum("vlCustoOm").as("vlCustoOm"));
			projectionList.add(Projections.sum("vlIcms").as("vlIcms"));
			projectionList.add(Projections.sum("vlIcmsOm").as("vlIcmsOm"));
			projectionList.add(Projections.sum("vlIcmsAnt").as("vlIcmsAnt"));
			projectionList.add(Projections.sum("vlIcmsAntOm").as("vlIcmsAntOm"));
			
			projectionList.add(Projections.sum("vlLiquidoAssinatura").as("vlLiquidoAssinatura"));
			projectionList.add(Projections.sum("vlLiquidoAssinaturaOm").as("vlLiquidoAssinaturaOm"));
			projectionList.add(Projections.sum("vlPis").as("vlPis"));
			projectionList.add(Projections.sum("vlPisOm").as("vlPisOm"));
			
			projectionList.add(Projections.sum("vlRepasse").as("vlRepasse"));
			projectionList.add(Projections.sum("vlRepasseOm").as("vlRepasseOm"));

			projectionList.add(Projections.sum("vlRepasseFinal").as("vlRepasseFinal"));
			projectionList.add(Projections.sum("vlRepasseFinalOm").as("vlRepasseFinalOm"));
			
			projectionList.add(Projections.groupProperty("flRepassaIcms").as("flRepassaIcms"));
			projectionList.add(Projections.groupProperty("cdEOTLD").as("cdEOTLD"));
			projectionList.add(Projections.groupProperty("cdProdutoPrepago").as("cdProdutoPrepago"));
			criteria.setProjection(projectionList);
			ResultTransformer resultTransformer = Transformers.aliasToBean(SccPreFechamentoAssinatura.class);			
			criteria.setResultTransformer(resultTransformer);				
			return criteria.list();
		} catch (Exception e) { throw new DAOException("SccPreFechamentoAssinaturaDAO.pesquisaAssinaturasHolding",e); }		
	}
	
	public List<String> sccOperadora2String(List<SccOperadora> lista) {
		List<String> pks = new ArrayList<String>();
		for (int i=0;i<lista.size();i++) {
			pks.add(lista.get(i).getCdEot());
		}
		return pks;
	}

	public SccOperadoraDAOImpl getOperadoraDAO() {
		return operadoraDAO;
	}

	public void setOperadoraDAO(SccOperadoraDAOImpl operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}

	
}
