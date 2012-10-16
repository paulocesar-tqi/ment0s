package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccRepasseDAO;
import com.claro.cobillingweb.persistence.dao.query.RelContabilViewSQL;
import com.claro.cobillingweb.persistence.dao.query.RelPrestacaoServicoSQL;
import com.claro.cobillingweb.persistence.dao.query.SccRepasseDAONativeSQL;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.cobillingweb.persistence.view.RelContabilView;
import com.claro.cobillingweb.persistence.view.RelPrestacaoServicoView;
import com.claro.cobillingweb.persistence.view.mapper.LongEntity;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccRepasseDAOImpl extends HibernateBasicDAOImpl<SccRepasse> implements SccRepasseDAO {

	/**
	 * DAO injetado pelo Spring.
	 */
	private SccOperadoraDAO operadoraDAO;
	
	 
	public List<SccRepasse> getAll() throws DAOException {	
		return null;
	}

	 
	@SuppressWarnings("unchecked")
	public List<SccRepasse> carregaRepassePosPago(String cdEOT,String cdEOTLD, Long cdProduto, Date dtInicial, Date dtFinal,String cdStatusRepasse,boolean holding)
			throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccRepasse.class);
			ProjectionList projectionList = Projections.projectionList();
			criteria.add(Restrictions.eq("produto.cdProdutoCobilling", cdProduto));
			
			if ((cdStatusRepasse != null) && (cdStatusRepasse.equals(BasicDAO.GET_ALL_STRING)))
				{
				criteria.add(Restrictions.eq("cdStatusRepasse", cdStatusRepasse));				
				}
			
			
			if (cdEOT == null)
				{
				/*Nada a fazer. O usuário não está interessado na operadora Claro.*/
				}
			else if (cdEOT.equals(BasicDAO.GET_ALL_STRING))
				{
				projectionList.add(Projections.groupProperty("cdEotClaro").as("cdEotClaro"));
				}
			else 
				{
				criteria.add(Restrictions.eq("cdEotLd", cdEOTLD));
				if (holding)
					criteria.add(Restrictions.in("cdEotClaro", carregaSomentePKsOperadoras(getOperadoraDAO().pesquisaOperadorasHolding(cdEOT))));
				else
					criteria.add(Restrictions.eq("cdEotClaro", cdEOT));
				}
									
			criteria.add(Restrictions.between("dtInicialRepasse", dtInicial, dtFinal));
			projectionList.add(Projections.groupProperty("cdItemRepasse").as("cdItemRepasse"));
			projectionList.add(Projections.groupProperty("nuRepasse").as("nuRepasse"));
			projectionList.add(Projections.groupProperty("sccTipoContrato").as("sccTipoContrato"));
			projectionList.add(Projections.groupProperty("cdStatusRepasse").as("cdStatusRepasse"));
			projectionList.add(Projections.sum("qtCdrs").as("qtCdrs"));
			projectionList.add(Projections.sum("qtDuracaoTarifada").as("qtDuracaoTarifada"));
			projectionList.add(Projections.sum("vlLiquidoRepasse").as("vlLiquidoRepasse"));	
			projectionList.add(Projections.sum("vlLiquidoItemRepasse").as("vlLiquidoItemRepasse"));
			projectionList.add(Projections.sum("vlPis").as("vlPis"));
			projectionList.add(Projections.sum("vlCofins").as("vlCofins"));
			projectionList.add(Projections.sum("vlIcms").as("vlIcms"));
			projectionList.add(Projections.sum("vlIss").as("vlIss"));			
			projectionList.add(Projections.sum("vlBrutoItemRepasse").as("vlBrutoItemRepasse"));
			projectionList.add(Projections.sum("vlBrutoRepasse").as("vlBrutoRepasse"));		
			
			criteria.setProjection(projectionList);
			ResultTransformer resultTransformer = Transformers.aliasToBean(SccRepasse.class);
			criteria.setResultTransformer(resultTransformer);			
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccRepasseDAO.carregaDemonstrativoHolding");
			}		
	}

	public SccOperadoraDAO getOperadoraDAO() {
		return operadoraDAO;
	}

	public void setOperadoraDAO(SccOperadoraDAO operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}

	
	/**
	 * Método helper para extrair somente as pk´s das operadoras. 
	 * Como o relacionamento entre SccRepasse e SccOperadora não  está mapeado , para que a pesquisa
	 * usando IN funcione é necessário forçar um casting.
	 * @param operadoras
	 * @return
	 */
	private List<String> carregaSomentePKsOperadoras(List<SccOperadora> operadoras)
	{
		List<String> pks = new ArrayList<String>();
		if (operadoras == null)
			return null;
		for (int i=0;i<operadoras.size();i++)
			{
			pks.add(operadoras.get(i).getCdEot());
			}
	return pks;
	}

	 
	@SuppressWarnings("unchecked")
	public List<SccRepasse> carregaItensRepasse(Long nuRepasse) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccRepasse.class);
			ProjectionList projectionList = Projections.projectionList();
			
			criteria.add(Restrictions.or(Restrictions.ne("cdStatusRepasse", "N"), Restrictions.isNull("cdStatusRepasse")));
			criteria.add(Restrictions.eq("nuRepasse", nuRepasse));
			projectionList.add(Projections.groupProperty("cdItemRepasse").as("cdItemRepasse"));
			projectionList.add(Projections.groupProperty("nuRepasse").as("nuRepasse"));
			projectionList.add(Projections.groupProperty("sccTipoContrato").as("sccTipoContrato"));
			projectionList.add(Projections.groupProperty("cdStatusRepasse").as("cdStatusRepasse"));
			projectionList.add(Projections.groupProperty("cdEotLd").as("cdEotLd"));			
			projectionList.add(Projections.groupProperty("dtInicialRepasse").as("dtInicialRepasse"));
			projectionList.add(Projections.groupProperty("nuQuinzena").as("nuQuinzena"));
			projectionList.add(Projections.groupProperty("produto").as("produto"));			
			projectionList.add(Projections.groupProperty("cdEotClaro").as("cdEotClaro"));			
			projectionList.add(Projections.groupProperty("dtFimRepasse").as("dtFimRepasse"));			
			projectionList.add(Projections.sum("qtCdrs").as("qtCdrs"));
			projectionList.add(Projections.sum("qtDuracaoTarifada").as("qtDuracaoTarifada"));
			projectionList.add(Projections.sum("vlLiquidoRepasse").as("vlLiquidoRepasse"));			
			projectionList.add(Projections.sum("vlPis").as("vlPis"));
			projectionList.add(Projections.sum("vlCofins").as("vlCofins"));
			projectionList.add(Projections.sum("vlIcms").as("vlIcms"));
			projectionList.add(Projections.sum("vlIss").as("vlIss"));			
			projectionList.add(Projections.sum("vlBrutoItemRepasse").as("vlBrutoItemRepasse"));
			projectionList.add(Projections.sum("vlLiquidoItemRepasse").as("vlLiquidoItemRepasse"));
			projectionList.add(Projections.sum("vlBrutoRepasse").as("vlBrutoRepasse"));
			criteria.setProjection(projectionList);
			ResultTransformer resultTransformer = Transformers.aliasToBean(SccRepasse.class);
			criteria.setResultTransformer(resultTransformer);			
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccRepasseDAO.carregaDemonstrativoHolding");
			}		
	}

	 
	public void removeItemRepasse(Long cdItemRepasse, Long nuRepasse) throws DAOException {
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery("DELETE  SccRepasse where nuRepasse = :nuRepasse AND cdItemRepasse = :cdItemRepasse");
			query.setLong("nuRepasse", nuRepasse);
			query.setLong("cdItemRepasse", cdItemRepasse);
			query.executeUpdate();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccRepasse.removeItemRepasse");
			}
		
	}

	 
	public void atualizaStatusRepasse(Long nuRepasse, String novoStatus) throws DAOException {
		try {
			Query query = getSessionFactory().getCurrentSession().getNamedQuery("SccRepasse.UPDATE_BY_NUM_REPASSE"); 
			query.setLong("nuRepasse",nuRepasse );
			query.setString("cdStatusRepasse", novoStatus);
			query.executeUpdate();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
		
	}

	 
	public List<LongEntity> pesquisaRepassesPosPago(String cdEOTLD,String cdEOTClaro, Long cdProdutoCobilling, String cdStatus,Date dataInicial, Date dataFinal) throws DAOException {
		try {
			NativeSQLViewMapper<LongEntity> view = new NativeSQLViewMapper<LongEntity>(getSessionFactory().getCurrentSession(), SccRepasseDAONativeSQL.pesquisaNumRepasse, LongEntity.class);
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING)))
				{
				view.appendSQL(" AND CD_EOT_LD = :cdEOTLD");
				view.addArgument("cdEOTLD", cdEOTLD);
				}			
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)))
				{
				view.appendSQL(" AND CD_EOT_CLARO = :cdEOTClaro");
				view.addArgument("cdEOTClaro", cdEOTClaro);
				}	
			if ((cdStatus != null) && (!cdStatus.equals(BasicDAO.GET_ALL_STRING)))
				{
				view.appendSQL(" AND CD_STATUS_REPASSE = :cdStatus");
				view.addArgument("cdStatus", cdStatus);
				}
			if ((cdProdutoCobilling != null) && (!cdProdutoCobilling.equals(BasicDAO.GET_ALL)))
				{
				view.appendSQL(" AND CD_PRODUTO_COBILLING = :cdProdutoCobilling");
				view.addArgument("cdProdutoCobilling", cdProdutoCobilling);
				}			
			view.addArgument("dataInicial", dataInicial);			
			view.addArgument("dataFinal", dataFinal);
			view.addResultMap("value", Long.class);
			return view.execute();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	 
	public List<RelContabilView> geraRelatorioContabil(String cdEOTLD,String cdEOTClaro, String cdMotivoRejeicao, Date dataInicial,Date dataFinal, boolean holding) throws DAOException {
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<RelContabilView> mapper = new NativeSQLViewMapper<RelContabilView>(session, RelContabilViewSQL.SQL, RelContabilView.class);
			mapper.addResultMap("cdEOTLD", String.class);
			mapper.addResultMap("cdEOTClaro", String.class);
			mapper.addResultMap("dataFechamento", String.class);
			mapper.addResultMap("descricao", String.class);
			mapper.addResultMap("cdContabil", String.class);
			mapper.addResultMap("qtCdrs", Long.class);
			mapper.addResultMap("vlLiquido", Double.class);
			mapper.addResultMap("vlBruto", Double.class);
			mapper.addArgument("dataInicial", dataInicial);
			mapper.addArgument("dataFinal", dataFinal);
			
			if ((cdEOTLD != null) && (!cdEOTLD.equals(GET_ALL_STRING)))
				{
				mapper.addArgument("cdEOTLD", cdEOTLD, RelContabilViewSQL.FILTRO_EOT_LD);				
				}
			
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(GET_ALL_STRING)))
				{
				if (holding)
					mapper.addArgument("cdEOTClaro", cdEOTClaro, RelContabilViewSQL.FILTRO_EOT_CLARO_HOLDING);
				else
					mapper.addArgument("cdEOTClaro", cdEOTClaro, RelContabilViewSQL.FILTRO_EOT_CLARO);
				}
			
			if ((cdMotivoRejeicao != null) && (!cdMotivoRejeicao.equals(GET_ALL_STRING)))
				{
				mapper.addArgument("cdMotivoRejeicao", cdMotivoRejeicao, RelContabilViewSQL.FILTRO_MOTIVO_REJEICAO);
				}			
			mapper.setProjections(RelContabilViewSQL.PROJECTIONS);
			return mapper.execute();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	
	

	private NativeSQLViewMapper<RelPrestacaoServicoView> montarCamposQuery(Session session){
		
		return new NativeSQLViewMapper<RelPrestacaoServicoView>(session, RelPrestacaoServicoSQL.SQL, RelPrestacaoServicoView.class);
		
	}
	
	@SuppressWarnings("unused")
	private NativeSQLViewMapper<RelPrestacaoServicoView> montarCamposQueryServicoPre(Session session){
		
		return new NativeSQLViewMapper<RelPrestacaoServicoView>(session, RelPrestacaoServicoSQL.SQL_PRE, RelPrestacaoServicoView.class);
	}
	
	@SuppressWarnings("rawtypes")
	private void montarWhereCdEOTClaro(NativeSQLViewMapper mapper, String cdEOTClaro) throws DAOException{
		if (cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("cdEOTClaro", cdEOTClaro, RelPrestacaoServicoSQL.CD_EOT_CLARO);
		}
	}
		
			
	@SuppressWarnings("rawtypes")
	private void montarWhereCdProdutoCobilling(NativeSQLViewMapper mapper, Long cdProdutoCobilling) throws DAOException{
		// Produto Arrecadado
		if (cdProdutoCobilling.equals(1L)){
			mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling, RelPrestacaoServicoSQL.CD_PRODUTO_COBILLING_IN);
		// Demais Produtos
		}else{
			mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling, RelPrestacaoServicoSQL.CD_PRODUTO_COBILLING_NOT_IN);
		}
	}
	
	@Override
	public List<RelPrestacaoServicoView> gerarRelatorioPrestacaoServicoPos(
			String cdEOTClaro, String cdEOTLd, Long cdProdutoCobilling,
			Date dataInicial, Date dataFinal) throws DAOException {
		
		
		List<RelPrestacaoServicoView> listRelPrestServico = null;
		
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<RelPrestacaoServicoView> mapper = montarCamposQuery(session);
			mapper.addArgument("dataInicial", dataInicial, RelPrestacaoServicoSQL.DT_INICIAL_REPASSE);
			mapper.addArgument("dataFinal", dataFinal, RelPrestacaoServicoSQL.DT_FIM_REPASSE);
			
			montarWhereCdEOTClaro(mapper, cdEOTClaro);
			
			montarWhereCdProdutoCobilling(mapper, cdProdutoCobilling);
			
			if (cdEOTLd != null && !cdEOTLd.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTLd", cdEOTLd, RelPrestacaoServicoSQL.CD_EOT_LD);
			}
			
			mapper.addResultMap("dsOperadoraClaro", String.class);
			mapper.addResultMap("embratel", Double.class);
			mapper.addResultMap("intelig", Double.class);
			mapper.addResultMap("brasil_telecom", Double.class);
			mapper.addResultMap("telefonica", Double.class);
			mapper.addResultMap("tnl", Double.class);
			mapper.addResultMap("gvt", Double.class);
			mapper.addResultMap("sercontel", Double.class);
			mapper.addResultMap("tim", Double.class);
			mapper.addResultMap("ctbc", Double.class);
			mapper.addResultMap("telemar", Double.class);
			mapper.addResultMap("ipCorp", Double.class);
			mapper.addResultMap("nexus", Double.class);
			mapper.setProjections(RelPrestacaoServicoSQL.PROJECTIONS);	
			listRelPrestServico =  mapper.execute();
			
		} catch (Exception e) { 
			throw new DAOException(e.getMessage(), e);
		}
		
		return listRelPrestServico;
		
	}


	@Override
	public List<RelPrestacaoServicoView> gerarRelatorioPrestacaoServicoPre(	String cdEOTClaro, String cdEOTLd, Long cdProdutoPrepago, String dataFechamento)	throws DAOException {
		
		List<RelPrestacaoServicoView> listRelPrestServico = null;
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<RelPrestacaoServicoView> mapper = montarCamposQueryServicoPre(session);
			mapper.addArgument("dataFechamento", dataFechamento, RelPrestacaoServicoSQL.DT_FECHAMENTO);
			
			if (cdEOTLd != null && !cdEOTLd.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTLd", cdEOTLd, RelPrestacaoServicoSQL.CD_EOT_LD_PRE);
			}
			
			if (cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", cdEOTClaro, RelPrestacaoServicoSQL.CD_EOT_CLARO_PRE);
			}
			
			if (cdProdutoPrepago != null && cdProdutoPrepago.longValue() != -1L) {
				mapper.addArgument("cdProdutoPrepago", cdProdutoPrepago, RelPrestacaoServicoSQL.CD_PRODUTO_PREPAGO);
			}
			
			mapper.addResultMap("dsOperadoraClaro", String.class);
			mapper.addResultMap("embratel", Double.class);
			mapper.addResultMap("intelig", Double.class);
			mapper.addResultMap("brasil_telecom", Double.class);
			mapper.addResultMap("telefonica", Double.class);
			mapper.addResultMap("tnl", Double.class);
			mapper.addResultMap("gvt", Double.class);
			mapper.addResultMap("sercontel", Double.class);
			mapper.addResultMap("tim", Double.class);
			mapper.addResultMap("ctbc", Double.class);
			mapper.addResultMap("telemar", Double.class);
			mapper.addResultMap("ipCorp", Double.class);
			mapper.addResultMap("nexus", Double.class);
			mapper.setProjections(RelPrestacaoServicoSQL.PROJECTIONS);	
			listRelPrestServico =  mapper.execute();
			
		} catch (Exception e) { 
			throw new DAOException(e.getMessage(), e);
		}
		
		return listRelPrestServico;

	}
	
	
	

}
