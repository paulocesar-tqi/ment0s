package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
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
import com.claro.cobillingweb.persistence.dao.internal.SccPreFechamentoDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreFechamento;
import com.claro.cobillingweb.persistence.view.RelApuracaoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;
import com.claro.cobillingweb.persistence.view.RelSinteticoServicoPrePagoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccPreFechamentoDAOImpl extends HibernateBasicDAOImpl<SccPreFechamento> implements SccPreFechamentoDAO {

	private SccOperadoraDAO operadoraDAO;
	 
	public List<SccPreFechamento> getAll() throws DAOException {		
		return null;
	}
	 
	@SuppressWarnings("unchecked")
	public List<SccPreFechamento> pesquisaRepassesPreOperadora(String cdEOTLD,String cdEOTClaro,String cdProdutoPrepago, String statusRepasse, Date dtInicial,Date dtFinal,boolean holding) throws DAOException {
		
		List<SccPreFechamento> list = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPreFechamento.class);
			criteria.add(Restrictions.eq("cdEotLd", cdEOTLD));
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {				
					if (holding) {
						criteria.add(Restrictions.in("cdEotClaro", getOperadoraDAO().pesquisaOperadorasHolding(cdEOTClaro)));
					} else {
						criteria.add(Restrictions.eq("cdEotClaro", cdEOTClaro));
					}
			}
			if ((statusRepasse != null) && (!statusRepasse.equals(BasicDAO.GET_ALL_STRING))) {
				criteria.add(Restrictions.eq("cdStatusFechamento", statusRepasse));
			}
			if (cdProdutoPrepago != null) {
				criteria.add(Restrictions.eq("cdProdutoPrepago", cdProdutoPrepago));
			}
			criteria.add(Restrictions.eq("dtInicialFechamento", dtInicial));
			criteria.add(Restrictions.eq("dtFimFechamento", dtFinal));
			list = (List<SccPreFechamento>) criteria.list();
		} catch (Exception e) { 
			throw new DAOException(e.getMessage(), "SccPreFechamentoDAO.pesquisaRepassesPre"); 
		}
		
		return list;
	}
	
	public SccOperadoraDAO getOperadoraDAO() {
		return operadoraDAO;
	}
	
	public void setOperadoraDAO(SccOperadoraDAO operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}
	
	@SuppressWarnings("unchecked")
	public List<SccPreFechamento> pesquisaRepassesPreHolding(String cdEOTLD,String cdEOTClaro, String cdProdutoPrepago, Date dtInicial, Date dtFinal) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPreFechamento.class);
			criteria.add(Restrictions.eq("cdEotLd", cdEOTLD));			
			criteria.add(Restrictions.in("cdEotClaro", sccOperadora2String(getOperadoraDAO().pesquisaOperadorasHolding(cdEOTClaro))));
			criteria.add(Restrictions.eq("cdProdutoPrepago", cdProdutoPrepago));
			criteria.add(Restrictions.eq("dtInicialFechamento", dtInicial));
			criteria.add(Restrictions.eq("dtFimFechamento", dtFinal));
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.sum("qtCdrs").as("qtCdrs"));
			projectionList.add(Projections.sum("qtCdrs226").as("qtCdrs226"));
			projectionList.add(Projections.sum("qtCdrsOm").as("qtCdrsOm"));
			projectionList.add(Projections.sum("qtDuracaoReal").as("qtDuracaoReal"));
			projectionList.add(Projections.sum("qtDuracaoRealOm").as("qtDuracaoRealOm"));
			projectionList.add(Projections.sum("qtDuracaoTarifada").as("qtDuracaoTarifada"));
			projectionList.add(Projections.sum("qtDuracaoTarifada226").as("qtDuracaoTarifada226"));
			projectionList.add(Projections.sum("qtDuracaoTarifadaOm").as("qtDuracaoTarifadaOm"));
			
			projectionList.add(Projections.sum("vlAcertoClaro").as("vlAcertoClaro"));
			projectionList.add(Projections.sum("vlAcertoLd").as("vlAcertoLd"));
			projectionList.add(Projections.sum("vlBrutoChamada").as("vlBrutoChamada"));
			projectionList.add(Projections.sum("vlBrutoChamadaOm").as("vlBrutoChamadaOm"));
			projectionList.add(Projections.sum("vlCofins").as("vlCofins"));
			
			projectionList.add(Projections.sum("vlCofinsOm").as("vlCofinsOm"));
			projectionList.add(Projections.sum("vlCpmf").as("vlCpmf"));
			projectionList.add(Projections.sum("vlCredito226").as("vlCredito226"));
			projectionList.add(Projections.sum("vlBrutoChamadaOm").as("vlBrutoChamadaOm"));
			projectionList.add(Projections.sum("vlCreditoAut").as("vlCreditoAut"));
			
			projectionList.add(Projections.sum("vlFinalRepassar").as("vlFinalRepassar"));
			projectionList.add(Projections.sum("vlIcms").as("vlIcms"));
			projectionList.add(Projections.sum("vlIcms226").as("vlIcms226"));
			projectionList.add(Projections.sum("vlIcmsAnt").as("vlIcmsAnt"));
			projectionList.add(Projections.sum("vlIcmsOm").as("vlIcmsOm"));
			
			projectionList.add(Projections.sum("vlLiquido226").as("vlLiquido226"));
			projectionList.add(Projections.sum("vlLiquidoChamada").as("vlLiquidoChamada"));
			projectionList.add(Projections.sum("vlLiquidoChamadaOm").as("vlLiquidoChamadaOm"));
			projectionList.add(Projections.sum("vlMultasClaro").as("vlMultasClaro"));
			projectionList.add(Projections.sum("vlMultasLd").as("vlMultasLd"));
			
			
			projectionList.add(Projections.sum("vlPenalMinPerd").as("vlPenalMinPerd"));
			projectionList.add(Projections.sum("vlPis").as("vlPis"));
			projectionList.add(Projections.sum("vlPisCofins226").as("vlPisCofins226"));
			projectionList.add(Projections.sum("vlPisOm").as("vlPisOm"));
			projectionList.add(Projections.sum("vlServPrestBruto").as("vlServPrestBruto"));
			
			projectionList.add(Projections.sum("vlServPrestCofins").as("vlServPrestCofins"));
			projectionList.add(Projections.sum("vlServPrestIss").as("vlServPrestIss"));
			projectionList.add(Projections.sum("vlServPrestPis").as("vlServPrestPis"));
			projectionList.add(Projections.groupProperty("flRepassaIcms").as("flRepassaIcms"));
			criteria.setProjection(projectionList);
			ResultTransformer resultTransformer = Transformers.aliasToBean(SccPreFechamento.class);			
			criteria.setResultTransformer(resultTransformer);				
			return criteria.list();
		} catch (Exception e) { throw new DAOException("SccPreFechamentoDAO.pesquisaRepassesPreHolding",e); }		
	}
	
	public List<String> sccOperadora2String(List<SccOperadora> lista) {
		List<String> pks = new ArrayList<String>();
		for (int i=0;i<lista.size();i++) {
			pks.add(lista.get(i).getCdEot());
		}
		return pks;
	}
	
	/*item.setVlLiquido(item.getVlBruto()-item.getVlIcmsADescontar()-item.getVlIcmsRepassar()-item.getVlPis()-item.getVlCofins());*/
	public List<RelSinteticoFechamentoPrePagoView> geraRelatorioSintetico(String cdProduto,String cdEOTLD, String cdEOTClaro, String cdStatusFechamento,Date dataInicial, Date dataFinal) throws DAOException {
		String sql = "SELECT O.DS_OPERADORA,  O.CD_EOT_HOLDING, "+ 
				" I.CD_EOT_ROAMING, "+
                   " O.CD_EOT, "+
				" F.CD_EOT_LD, "+
				"  CAST(O.SG_UF AS VARCHAR2(2)) AS SG_UF, "+
				"  SUBSTR(I.MM_TRAFEGO,3,4) || ' / ' || SUBSTR(I.MM_TRAFEGO,0,2) AS TRAFEGO,"+ 
				"  CAST(I.CN_ASSINANTEA AS VARCHAR2(3)) AS CN_ASSINANTEA, "+
				"  CAST(I.CD_AREA_ORIGEM AS VARCHAR2(3)) AS CD_AREA_ORIGEM, "+				
				"  D.DS_DOMINIO AS TIPO, "+ 
				"  E.DS_DOMINIO AS PERIODO,"+ 
				"  NVL(SUM(I.QT_CDRS),0) AS QT_CDRS,"+ 
				"  NVL(SUM(I.QT_DURACAO_TARIFADA),0) AS QT_DURACAO_TARIFADA,"+ 
				"  NVL(SUM(I.VL_PIS),0) AS VL_PIS, "+
				"  NVL(SUM(I.VL_COFINS),0) AS VL_COFINS,"+ 
				"  NVL(SUM(DECODE(F.FL_REPASSA_ICMS,'S',I.VL_ICMS,0)),0) AS VL_ICMSAREPASSAR,"+ 
				"  NVL(SUM(DECODE(F.FL_REPASSA_ICMS,'N',I.VL_ICMS,0)),0) AS VL_ICMSNAOREPASSADO,"+ 
				"  NVL(SUM(I.VL_BRUTO_CHAMADA),0) AS VL_BRUTO_CHAMADA "+
				" FROM SCC_PRE_FECHAMENTO F, "+
				"  SCC_PRE_ITEM_FECHAMENTO I, "+
				"  SCC_OPERADORA O, "+
				" (SELECT CD_DOMINIO, TP_DOMINIO, DS_DOMINIO FROM SCC_PRE_DOMINIO WHERE 'TPCHM' = TP_DOMINIO ) D,"+ 
				" (SELECT CD_DOMINIO, TP_DOMINIO, DS_DOMINIO FROM SCC_PRE_DOMINIO WHERE 'PRCHM' = TP_DOMINIO ) E "+
				" WHERE O.CD_EOT=F.CD_EOT_CLARO "+
				" AND I.SQ_PRE_FECHAMENTO = F.SQ_PRE_FECHAMENTO"+ 
				" AND I.CD_TIPO_CHAMADA = D.CD_DOMINIO(+) "+
				" AND I.CD_PERIODO_CHAMADA = E.CD_DOMINIO(+) ";
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<RelSinteticoFechamentoPrePagoView> sinteticoView = new NativeSQLViewMapper<RelSinteticoFechamentoPrePagoView>(session, sql, RelSinteticoFechamentoPrePagoView.class);
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {
				sinteticoView.appendSQL(" AND F.CD_EOT_LD = :cdEOTLD ");
				sinteticoView.addArgument("cdEOTLD", cdEOTLD);
			}
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {
				sinteticoView.appendSQL(" AND F.CD_EOT_CLARO = :cdEOTClaro ");
				sinteticoView.addArgument("cdEOTClaro", cdEOTClaro);
			}
			if ((cdStatusFechamento != null) && (!cdStatusFechamento.equals(BasicDAO.GET_ALL_STRING))) {
				sinteticoView.appendSQL(" AND F.CD_STATUS_FECHAMENTO = :cdStatusFechamento ");
				sinteticoView.addArgument("cdStatusFechamento", cdStatusFechamento);
			}
			if ((cdProduto != null) && (!cdProduto.equals(BasicDAO.GET_ALL_STRING))) {
				sinteticoView.appendSQL(" AND F.CD_PRODUTO_PREPAGO = :cdProduto ");
				sinteticoView.addArgument("cdProduto", cdProduto);
			}
			
			sinteticoView.appendSQL(" AND TRUNC(F.DT_INICIAL_FECHAMENTO) = :dataInicial ");
			sinteticoView.addArgument("dataInicial", dataInicial);
			sinteticoView.appendSQL(" AND TRUNC(F.DT_FIM_FECHAMENTO) =  :dataFinal ");
			sinteticoView.addArgument("dataFinal", dataFinal);			
			
			sinteticoView.appendSQL("GROUP BY "+  
     		       " O.CD_EOT_HOLDING,"+ 
					" O.CD_EOT,  "+
					" F.CD_EOT_LD,  "+
					" SG_UF ,  "+
					" I.MM_TRAFEGO, "+ 
					" CN_ASSINANTEA, "+ 
					" CD_AREA_ORIGEM,  "+
					" I.CD_EOT_ROAMING,  "+
					" I.CD_TIPO_CHAMADA,  "+
					" I.CD_PERIODO_CHAMADA,  "+
					" D.DS_DOMINIO,  "+
					" E.DS_DOMINIO,  "+
					"O.DS_OPERADORA "+
					" ORDER BY 1,2,4,5,6,7,8,9,10  ");		
			sinteticoView.addResultMap("operadoraClaro", String.class);
			sinteticoView.addResultMap("cdEOTHolding", String.class);
			sinteticoView.addResultMap("cdEotOrigem", String.class);
			sinteticoView.addResultMap("cdEOTClaro", String.class);
			sinteticoView.addResultMap("cdEOTLD", String.class);			
			sinteticoView.addResultMap("uf", String.class);			
			sinteticoView.addResultMap("trafego", String.class);	
			sinteticoView.addResultMap("cnAssinante", String.class);
			sinteticoView.addResultMap("cnOrigem", String.class);			
			sinteticoView.addResultMap("tipo", String.class);
			sinteticoView.addResultMap("periodo", String.class);
			sinteticoView.addResultMap("chamadas", Long.class);
			sinteticoView.addResultMap("minutos", Double.class);
			sinteticoView.addResultMap("valorPis", Double.class);
			sinteticoView.addResultMap("valorCofins", Double.class);
			sinteticoView.addResultMap("icmsRepassar", Double.class);
			sinteticoView.addResultMap("icmsNaoRepassado", Double.class);
			sinteticoView.addResultMap("valorBruto", Double.class);			
			return sinteticoView.execute();
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }
	}
	
	public List<RelSinteticoServicoPrePagoView> geraRelatorioSinteticoServico(String cdProduto,String cdEOTLD, String cdEOTClaro, String cdStatusFechamento,Date dataInicial, Date dataFinal) throws DAOException {
		String sql = "SELECT NVL(SUM(F.QT_CDRS),0) AS QT_CDRS, "+
                   " NVL(SUM(F.QT_CDRS_OM),0) AS QT_CDRS_OM,  "+
                   " NVL(SUM(F.VL_SERV_PREST_PIS),0) AS VL_SERV_PREST_PIS, "+ 
                   "  NVL(SUM(F.VL_SERV_PREST_COFINS),0) AS VL_SERV_PREST_COFINS, "+ 
                   "  NVL(SUM(F.VL_SERV_PREST_BRUTO),0) AS VL_SERV_PREST_BRUTO,  "+
                   "  NVL(SUM(F.VL_SERV_PREST_ISS),0) AS VL_SERV_PREST_ISS  "+				 
                   " FROM SCC_PRE_FECHAMENTO F,  "+
                   "  SCC_OPERADORA O  "+
                   " WHERE O.CD_EOT=F.CD_EOT_CLARO "+ 
                   " AND F.SQ_PRE_FECHAMENTO IN (SELECT I.SQ_PRE_FECHAMENTO FROM SCC_PRE_ITEM_FECHAMENTO I, SCC_PRE_DOMINIO D, SCC_PRE_DOMINIO E  "+  
                   "            WHERE I.CD_TIPO_CHAMADA = D.CD_DOMINIO(+)   "+
                   "              AND D.TP_DOMINIO = 'TPCHM'   "+
                   "              AND I.CD_PERIODO_CHAMADA = E.CD_DOMINIO(+) "+ 
                   "              AND E.TP_DOMINIO = 'PRCHM' )  ";
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<RelSinteticoServicoPrePagoView> sinteticoView = new NativeSQLViewMapper<RelSinteticoServicoPrePagoView>(session, sql, RelSinteticoServicoPrePagoView.class);
			
			
			sinteticoView.appendSQL("AND TRUNC(F.DT_INICIAL_FECHAMENTO) = :dataInicial ");
			sinteticoView.addArgument("dataInicial", dataInicial);
			
			sinteticoView.appendSQL("AND TRUNC(F.DT_FIM_FECHAMENTO) = :dataFinal ");
			sinteticoView.addArgument("dataFinal", dataFinal);
			
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {
				sinteticoView.appendSQL("AND F.CD_EOT_LD = :cdEOTLD ");
				sinteticoView.addArgument("cdEOTLD", cdEOTLD);
			}
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {
				sinteticoView.appendSQL("AND F.CD_EOT_CLARO = :cdEOTClaro ");
				sinteticoView.addArgument("cdEOTClaro", cdEOTClaro);
			}
			if ((cdStatusFechamento != null) && (!cdStatusFechamento.equals(BasicDAO.GET_ALL_STRING))) {
				sinteticoView.appendSQL("AND F.CD_STATUS_FECHAMENTO = :cdStatusFechamento ");
				sinteticoView.addArgument("cdStatusFechamento", cdStatusFechamento);
			}
			if ((cdProduto != null) && (!cdProduto.equals(BasicDAO.GET_ALL_STRING))) {
				sinteticoView.appendSQL("AND F.CD_PRODUTO_PREPAGO = :cdProduto ");
				sinteticoView.addArgument("cdProduto", cdProduto);
			}
			
			sinteticoView.addResultMap("qtCDR",Long.class);
		    sinteticoView.addResultMap("qtCDROM",Long.class);
			sinteticoView.addResultMap("valorPis",Double.class);
			sinteticoView.addResultMap("valorCofins",Double.class);
			sinteticoView.addResultMap("valorBruto",Double.class);
			sinteticoView.addResultMap("valorISS",Double.class);
			return sinteticoView.execute();
		} catch (Exception e) { throw new DAOException(e.getMessage(),e); }
	}
	
	public List<RelApuracaoFechamentoPrePagoView> geraRelatorioApuracao(String cdProduto, String cdEOTLD, String cdEOTClaro,String cdStatusFechamento, Date dataInicial, Date dataFinal) throws DAOException {
		String sql = "SELECT spf.SQ_PRE_FECHAMENTO, "+
                     " spf.CD_EOT_LD,   "+
                     " spf.DT_INICIAL_FECHAMENTO, "+  
                     " spf.DT_FIM_FECHAMENTO, "+  
                     " spf.CD_EOT_CLARO,   "+
                     " spf.DT_FECHAMENTO,   "+
                     " spf.CD_STATUS_FECHAMENTO, "+ 
                     " spf.SQ_PEDIDO,   "+
                     " NVL(spf.QT_CDRS,0) AS QT_CDRS, "+  
                     " NVL(spf.VL_LIQUIDO_CHAMADA,0) VL_LIQUIDO_CHAMADA, "+ 
                     " NVL(spf.VL_BRUTO_CHAMADA,0) VL_BRUTO_CHAMADA,   "+
                     " NVL(spf.QT_DURACAO_REAL,0) QT_DURACAO_REAL,   "+
                     " NVL(spf.QT_DURACAO_TARIFADA,0) QT_DURACAO_TARIFADA, "+  
                     " NVL(spf.VL_ICMS,0) VL_ICMS,   "+
                     " NVL(spf.VL_PIS,0) VL_PIS,   "+
                     " NVL(spf.VL_COFINS,0) VL_COFINS, "+  
                     " NVL(spf.QT_CDRS_OM,0) AS QT_CDRS_OM, "+  
                     " NVL(spf.VL_LIQUIDO_CHAMADA_OM,0) VL_LIQUIDO_CHAMADA_OM, "+  
                     " NVL(spf.VL_BRUTO_CHAMADA_OM,0) VL_BRUTO_CHAMADA_OM,   "+
                     " NVL(spf.QT_DURACAO_REAL_OM,0) QT_DURACAO_REAL_OM,   "+
                     " NVL(spf.QT_DURACAO_TARIFADA_OM,0) QT_DURACAO_TARIFADA_OM, "+  
                     " NVL(spf.VL_ICMS_OM,0) VL_ICMS_OM,   "+
                     " NVL(spf.VL_PIS_OM,0) VL_PIS_OM,   "+
                     " NVL(spf.VL_COFINS_OM,0) VL_COFINS_OM, "+  
                     " NVL(spf.VL_SERV_PREST_BRUTO,0) VL_SERV_PREST_BRUTO, "+  
                     " NVL(spf.VL_SERV_PREST_PIS,0) VL_SERV_PREST_PIS,   "+
                     " NVL(spf.VL_SERV_PREST_COFINS,0) VL_SERV_PREST_COFINS, "+  
                     " NVL(spf.VL_SERV_PREST_ISS,0) VL_SERV_PREST_ISS,   "+
                     " NVL(spf.VL_CREDITO_AUT,0) VL_CREDITO_AUT,   "+
                     " NVL(spf.QT_CDRS_226,0) AS QT_CDRS_226,   "+
                     " NVL(spf.QT_DURACAO_TARIFADA_226,0) QT_DURACAO_TARIFADA_226, "+  
                     " NVL(spf.VL_CREDITO_226,0) VL_CREDITO_226,   "+
                     " NVL(spf.VL_PENAL_MIN_PERD,0) VL_PENAL_MIN_PERD, "+  
                     " NVL(spf.VL_MULTAS_CLARO,0) VL_MULTAS_CLARO,   "+
                     " NVL(spf.VL_MULTAS_LD,0) VL_MULTAS_LD,   "+
                     " NVL(spf.VL_ACERTO_CLARO,0) VL_ACERTO_CLARO, "+  
                     " NVL(spf.VL_ACERTO_LD,0) VL_ACERTO_LD,   "+
                     " NVL(spf.VL_ICMS_ANT,0) VL_ICMS_ANT,   "+
                     " NVL(spf.VL_CPMF,0) VL_CPMF,   "+
                     " spf.FL_REPASSA_CPMF,   "+
                     " spf.FL_REPASSA_ICMS,   "+
                     " NVL(VL_LIQUIDO_226,0) VL_LIQUIDO_226, "+  
                     " NVL(VL_PIS_COFINS_226,0) VL_PIS_COFINS_226, "+  
                     " NVL(VL_ICMS_226,0) VL_ICMS_226,   "+
                     " (SELECT SO.DS_OPERADORA FROM SCC_OPERADORA SO WHERE CD_EOT IN (SELECT O.CD_EOT_HOLDING FROM SCC_OPERADORA O WHERE O.CD_EOT = CD_EOT_CLARO)), "+ 
                     " NVL(DS_CRITERIO_CUSTO,'POR CHAMADA') CRITERIO_CUSTO,   "+
                     " (SELECT CAST(O.SG_UF AS VARCHAR2(2)) FROM SCC_OPERADORA O WHERE O.CD_EOT = CD_EOT_CLARO) SG_UF_CLARO  "+                    
                  " FROM SCC_PRE_FECHAMENTO spf, "+
				  "       scc_pagamento_repasse spr "+
//				  "       scc_pagamento_repasse spr, "+
//				  "       scc_operadora oper  "+
				  " WHERE spf.sq_pre_fechamento = spr.nu_repasse(+) ";
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<RelApuracaoFechamentoPrePagoView> viewMapper = new NativeSQLViewMapper<RelApuracaoFechamentoPrePagoView>(session, sql, RelApuracaoFechamentoPrePagoView.class);
			
			viewMapper.appendSQL("AND TRUNC(spf.DT_INICIAL_FECHAMENTO) = :dataInicial ");
			viewMapper.addArgument("dataInicial", dataInicial);
			viewMapper.appendSQL("AND TRUNC(spf.DT_FIM_FECHAMENTO) = :dataFinal ");
			viewMapper.addArgument("dataFinal", dataFinal);
			
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {
				viewMapper.appendSQL("AND spf.CD_EOT_LD = :cdEOTLD ");
				viewMapper.addArgument("cdEOTLD", cdEOTLD);
			}
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {
				viewMapper.appendSQL("AND CD_EOT_CLARO = :cdEOTClaro ");
				viewMapper.addArgument("cdEOTClaro", cdEOTClaro);
			}
			if ((cdStatusFechamento != null) && (!cdStatusFechamento.equals(BasicDAO.GET_ALL_STRING))) {
				viewMapper.appendSQL("AND CD_STATUS_FECHAMENTO = :cdStatusFechamento ");
				viewMapper.addArgument("cdStatusFechamento", cdStatusFechamento);
			}
			if ((cdProduto != null) && (!cdProduto.equals(BasicDAO.GET_ALL_STRING))) {
				viewMapper.appendSQL("AND spf.CD_PRODUTO_PREPAGO = :cdProduto ");
				viewMapper.addArgument("cdProduto", cdProduto);
			}
			viewMapper.addResultMap("sqPreFechamento",Long.class);
			viewMapper.addResultMap("cdEOTLD",String.class);
			viewMapper.addResultMap("dtInicialFechamento",Date.class);
			viewMapper.addResultMap("dtFinalFechamento",Date.class);
			viewMapper.addResultMap("cdEOTClaro",String.class);
			viewMapper.addResultMap("dtFechamento",Date.class);
			viewMapper.addResultMap("cdStatusFechamento",String.class);
			viewMapper.addResultMap("sqPedido",Long.class);
			viewMapper.addResultMap("qtCdrs",Long.class);
			viewMapper.addResultMap("valor10",Double.class);
			viewMapper.addResultMap("valor11",Double.class);
			viewMapper.addResultMap("valor12",Double.class);
			viewMapper.addResultMap("valor13",Double.class);
			viewMapper.addResultMap("valor14",Double.class);
			viewMapper.addResultMap("valor15",Double.class);
			viewMapper.addResultMap("valor16",Double.class);
			viewMapper.addResultMap("qtCdrsOm",Long.class);
			viewMapper.addResultMap("valor18",Double.class);
			viewMapper.addResultMap("valor19",Double.class);
			viewMapper.addResultMap("valor20",Double.class);
			viewMapper.addResultMap("valor21",Double.class);
			viewMapper.addResultMap("valor22",Double.class);
			viewMapper.addResultMap("valor23",Double.class);
			viewMapper.addResultMap("valor24",Double.class);
			viewMapper.addResultMap("valor25",Double.class);
			viewMapper.addResultMap("valor26",Double.class);
			viewMapper.addResultMap("valor27",Double.class);
			viewMapper.addResultMap("valor28",Double.class);
			viewMapper.addResultMap("valor29",Double.class);
			viewMapper.addResultMap("qtCdrs226",Long.class);
			viewMapper.addResultMap("valor31",Double.class);
			viewMapper.addResultMap("valor32",Double.class);
			viewMapper.addResultMap("valor33",Double.class);
			viewMapper.addResultMap("valor34",Double.class);
			viewMapper.addResultMap("valor35",Double.class);
			viewMapper.addResultMap("valor36",Double.class);
			viewMapper.addResultMap("valor37",Double.class);
			viewMapper.addResultMap("valor38",Double.class);
			viewMapper.addResultMap("valor39",Double.class);
			viewMapper.addResultMap("repasseCPFM",String.class);
			viewMapper.addResultMap("repassaICMS",String.class);
			viewMapper.addResultMap("valor42",Double.class);
			viewMapper.addResultMap("valor43",Double.class);
			viewMapper.addResultMap("valor44",Double.class);
			viewMapper.addResultMap("dsOperadora",String.class);
			viewMapper.addResultMap("criterioCusto",String.class);
			viewMapper.addResultMap("ufClaro",String.class);
			return viewMapper.execute();
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }
	}
}
