package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernateImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccContratoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.query.SccContratoCobillingDAONativeSQL;
import com.claro.cobillingweb.persistence.entity.SccContratoCobilling;
import com.claro.cobillingweb.persistence.entity.SccContratoCobillingPK;
import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;
import com.claro.cobillingweb.persistence.view.SccProdutoContratadoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccContratoCobillingDAOImpl extends FwjBaseDaoHibernateImpl<SccContratoCobilling, String> implements SccContratoCobillingDAO{
	
	public List<SccContratoCobilling> getAll() throws DAOException {
		return null;
	}
	
	@Override
	public List<SccProdutoContratadoView> pesquisarProdutoContatatado(String cdEotClaro, String cdEotLd, Date dtInicioContrato) throws DAOException{
		
		List<SccProdutoContratadoView> list = null;
		try {

			NativeSQLViewMapper<SccProdutoContratadoView> mapper = new NativeSQLViewMapper<SccProdutoContratadoView>(getSessionFactory().getCurrentSession(), SccContratoCobillingDAONativeSQL.PRODUTOS_CONTRATO_SQL, SccProdutoContratadoView.class);
			mapper.addArgument("cdEotLd", cdEotLd);
			mapper.addArgument("cdEotClaro", cdEotClaro);
			mapper.addArgument("dtInicio", dtInicioContrato);
			
			mapper.addResultMap("cdEotLd", String.class);
			mapper.addResultMap("cdEotClaro", String.class);
			mapper.addResultMap("dtInicioContrato", Date.class);
			mapper.addResultMap("cdTipoContrato", String.class);
			mapper.addResultMap("cdProdutoPrePago", Long.class);
			mapper.addResultMap("cdTipoEvento", String.class);
			mapper.addResultMap("inCategoriaEvento", String.class);
			mapper.addResultMap("flagRepasse", String.class);
			mapper.addResultMap("descTipoEvento", String.class);
			mapper.addResultMap("descProduto", String.class);
			
			list = (List<SccProdutoContratadoView>) mapper.execute();			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;
	}
	
	@Override
	public List<SccContratoCobillingSearchView> pesquisarProdutosContratos(String cdEotClaro, String cdEotLD)throws DAOException {
		
		List<SccContratoCobillingSearchView> list = null;
		try {

			NativeSQLViewMapper<SccContratoCobillingSearchView> mapper = new NativeSQLViewMapper<SccContratoCobillingSearchView>(getSessionFactory().getCurrentSession(), SccContratoCobillingDAONativeSQL.PESQUISA_PRODUTO_CONTRATO, SccContratoCobillingSearchView.class);
			mapper.addArgument("cdEotLd", cdEotLD);
			mapper.addArgument("cdEotClaro", cdEotClaro);
			
			mapper.addResultMap("cdEOTLD", String.class);
			mapper.addResultMap("cdEOTClaro", String.class);
			mapper.addResultMap("dtInicioContrato", Date.class);
			mapper.addResultMap("dtFinalContrato", Date.class);
			mapper.addResultMap("cdTipoContrato", String.class);
			mapper.addResultMap("peRetencao", Long.class);
			mapper.addResultMap("dsCriterioCusto", String.class);
			mapper.addResultMap("valorAssociadoCriterioCusto", Double.class);
			mapper.addResultMap("vlCriterioCustoLiquido", Double.class);
			mapper.addResultMap("dsPeriodoRepasse", String.class);
			mapper.addResultMap("valorCPMF", Double.class);
			mapper.addResultMap("valorISS", Double.class);
			mapper.addResultMap("dtCriacao", String.class);
			mapper.addResultMap("dtAtualizacao", String.class);
			mapper.addResultMap("cdUsuarioManut", String.class);
			mapper.addResultMap("qtRepasses", Long.class);
			mapper.addResultMap("dsOperadoraLD", String.class);
			mapper.addResultMap("dsOperadoraClaro", String.class);
			mapper.addResultMap("dsTipoContrato", String.class);
			mapper.addResultMap("peCofins", Double.class);
			mapper.addResultMap("pePis", Double.class);
			mapper.addResultMap("flagRepasseIcms", String.class);
			mapper.addResultMap("flagRepasseCpmf", String.class);

			list = (List<SccContratoCobillingSearchView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;
		
	}
	
	@Override
	public List<SccContratoCobillingSearchView> pesquisaContratosOperadorasByFiltro(String cdEotClaro, String cdEotLD, String cdTipo) throws DAOException {
		try {
			NativeSQLViewMapper<SccContratoCobillingSearchView> mapper = new NativeSQLViewMapper<SccContratoCobillingSearchView>(getSessionFactory().getCurrentSession(), SccContratoCobillingDAONativeSQL.PESQUISA_OPERADORAS_SQL, SccContratoCobillingSearchView.class);
			if ((cdEotClaro != null) && (!cdEotClaro.equals(BasicDAO.GET_ALL_STRING))) {
				mapper.addArgument("cdEotClaro", cdEotClaro);
				mapper.appendSQL(" AND CC.CD_EOT_CLARO = :cdEotClaro ");
			}
			if ((cdEotLD != null) && (!cdEotLD.equals(BasicDAO.GET_ALL_STRING))) {
				mapper.addArgument("cdEotLD", cdEotLD);
				mapper.appendSQL(" AND CC.CD_EOT_LD = :cdEotLD ");
			}
			
			if ((cdTipo != null) && (!cdTipo.equals(BasicDAO.GET_ALL_STRING))) {
				mapper.addArgument("cdTipo", cdTipo);
				mapper.appendSQL(" AND CC.CD_TIPO_CONTRATO = :cdTipo ");
			}
			
			mapper.setProjections(SccContratoCobillingDAONativeSQL.PESQUISA_OPERADORAS_PROJECTIONS);
			mapper.addResultMap("dsOperadoraLD",String.class);
			mapper.addResultMap("dsOperadoraClaro",String.class);
			mapper.addResultMap("cdEOTLD",String.class);
			mapper.addResultMap("cdEOTClaro",String.class);
			mapper.addResultMap("dtInicioContrato",Date.class);
			mapper.addResultMap("dtFinalContrato",Date.class);
			mapper.addResultMap("cdTipoContrato",String.class);
			mapper.addResultMap("dsCriterioCusto",String.class);
			mapper.addResultMap("valorAssociadoCriterioCusto",Double.class);
			mapper.addResultMap("dsPeriodoBase",String.class);
			mapper.addResultMap("valorCPMF",Double.class);
			mapper.addResultMap("valorISS",Double.class);
			mapper.addResultMap("qtRepasses",Long.class);
			mapper.addResultMap("vlCriterioCustoLiquido",Double.class);			
			return mapper.execute();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}
		

	public List<SccContratoCobillingSearchView> pesquisaContratosOperadoras(String cdEotClaro, String cdEotLD) throws DAOException {
		try {
			NativeSQLViewMapper<SccContratoCobillingSearchView> mapper = new NativeSQLViewMapper<SccContratoCobillingSearchView>(getSessionFactory().getCurrentSession(), SccContratoCobillingDAONativeSQL.PESQUISA_OPERADORAS_SQL, SccContratoCobillingSearchView.class);
			if ((cdEotClaro != null) && (!cdEotClaro.equals(BasicDAO.GET_ALL_STRING))) {
				mapper.addArgument("cdEotClaro", cdEotClaro);
				mapper.appendSQL(" AND CC.CD_EOT_CLARO = :cdEotClaro ");
			}
			if ((cdEotLD != null) && (!cdEotLD.equals(BasicDAO.GET_ALL_STRING))) {
				mapper.addArgument("cdEotLD", cdEotLD);
				mapper.appendSQL(" AND CC.CD_EOT_LD = :cdEotLD ");
			}
			mapper.setProjections(SccContratoCobillingDAONativeSQL.PESQUISA_OPERADORAS_PROJECTIONS);
			mapper.addResultMap("dsOperadoraLD",String.class);
			mapper.addResultMap("dsOperadoraClaro",String.class);
			mapper.addResultMap("cdEOTLD",String.class);
			mapper.addResultMap("cdEOTClaro",String.class);
			mapper.addResultMap("dtInicioContrato",Date.class);
			mapper.addResultMap("dtFinalContrato",Date.class);
			mapper.addResultMap("cdTipoContrato",String.class);
			mapper.addResultMap("dsCriterioCusto",String.class);
			mapper.addResultMap("valorAssociadoCriterioCusto",Double.class);
			mapper.addResultMap("dsPeriodoBase",String.class);
			mapper.addResultMap("valorCPMF",Double.class);
			mapper.addResultMap("valorISS",Double.class);
			mapper.addResultMap("qtRepasses",Long.class);
			mapper.addResultMap("vlCriterioCustoLiquido",Double.class);			
			return mapper.execute();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}
	
	private String hqlEditar(){
		StringBuilder hql = new StringBuilder();
		hql.append("select scc from  SccContratoCobilling scc ");
		hql.append("where scc.id.cdEotLd = :cdEotLd ");
		hql.append(" and scc.id.cdEotClaro = :cdEotClaro ");
		hql.append(" and scc.id.dtInicioContrato = :dtInicioContrato ");
		hql.append(" and scc.id.cdTipoContrato = :cdTipoContrato ");
		return hql.toString();
	}
	
	@Override
	public SccContratoCobilling findByFiltro(SccContratoCobillingPK pk) throws DAOException{
		
		SccContratoCobilling entity = null;
		try{
			
			Query query = getSessionFactory().getCurrentSession().createQuery(hqlEditar());
			query.setParameter("cdEotLd", pk.getCdEotLd());
			query.setParameter("cdEotClaro", pk.getCdEotClaro());
			query.setParameter("dtInicioContrato", pk.getDtInicioContrato());
			query.setParameter("cdTipoContrato", pk.getCdTipoContrato());
			entity = (SccContratoCobilling) query.uniqueResult();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return entity;

	}
	
	@Override
	public void excluirEntity(SccContratoCobillingPK pk) throws DAOException {
		
		String hql = "delete from SccContratoCobilling where id.cdEotLd = :cdEotLd and id.cdEotClaro = :cdEotClaro and id.dtInicioContrato = :dtInicioContrato and id.cdTipoContrato = :cdTipoContrato ";
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			query.setParameter("cdEotLd", pk.getCdEotLd());
			query.setParameter("cdEotClaro", pk.getCdEotClaro());
			query.setParameter("dtInicioContrato", pk.getDtInicioContrato());
			query.setParameter("cdTipoContrato", pk.getCdTipoContrato());
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}
	

	
}
