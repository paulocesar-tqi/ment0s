package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

import com.claro.cobillingweb.persistence.constants.StatusCDRConstants;
import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoSumarizadoDAO;
import com.claro.cobillingweb.persistence.dao.query.ControleRemessaPorCicloSQL;
import com.claro.cobillingweb.persistence.dao.query.ControleRemessaPorEventoSql;
import com.claro.cobillingweb.persistence.dao.query.DemonstrativoSaldosLotesSQL;
import com.claro.cobillingweb.persistence.dao.query.SumarizadoPeriodoCicloSQL;
import com.claro.cobillingweb.persistence.dao.query.SumarizadoPeriodoSQL;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.filtro.SccFiltroRelPerdaFaturamento;
import com.claro.cobillingweb.persistence.utils.DateUtils;
import com.claro.cobillingweb.persistence.view.DemonstrativoSaldosLotesView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccArquivoSumarizadoDAOImpl extends HibernateBasicDAOImpl<SccArquivoSumarizado> implements SccArquivoSumarizadoDAO{
	
	public List<SccArquivoSumarizado> getAll() throws DAOException {
		return null;
	}
	
	public List<SccArquivoSumarizado> pesquisaSumarioPorCiclo(Long aaCiclo, Long mmCiclo, Long cdCiclo, String cdEOTLD, String cdEOTClaro, Long cdProdutoCobilling, Long cdTipoArquivo, boolean holding) throws DAOException {
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccArquivoSumarizado> mapper = new NativeSQLViewMapper<SccArquivoSumarizado>(session, ControleRemessaPorCicloSQL.SQL, SccArquivoSumarizado.class);
			if (aaCiclo != null) {
				mapper.addArgument("aaCiclo", aaCiclo, ControleRemessaPorCicloSQL.FILTRO_AA_CICLO);
			}
			if (mmCiclo != null) {				
				mapper.addArgument("mmCiclo", mmCiclo, ControleRemessaPorCicloSQL.FILTRO_MM_CICLO);
			}
			if (cdCiclo != null) {				
				mapper.addArgument("cdCiclo", cdCiclo, ControleRemessaPorCicloSQL.FILTRO_CD_CICLO);
			}
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {				
				mapper.addArgument("cdEOTLD", cdEOTLD, ControleRemessaPorCicloSQL.FILTRO_EOT_LD);
			}
			if ((cdTipoArquivo != null)	&& (!cdTipoArquivo.equals(BasicDAO.GET_ALL))) {			
				mapper.addArgument("cdTipoArquivo", cdTipoArquivo, ControleRemessaPorCicloSQL.FILTRO_TIPO_ARQUIVO);
			}
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {
				if (holding) {
					mapper.addArgument("cdEOTClaro", cdEOTClaro, ControleRemessaPorCicloSQL.FILTRO_EOT_CLARO_HOLDING);
				} else {
					mapper.addArgument("cdEOTClaro", cdEOTClaro, ControleRemessaPorCicloSQL.FILTRO_EOT_CLARO);
				}
			}
			if ((cdProdutoCobilling != null) && (!cdProdutoCobilling.equals(BasicDAO.GET_ALL))) {			
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling, ControleRemessaPorCicloSQL.FILTRO_PRODUTO);
			}
			mapper.setProjections(ControleRemessaPorCicloSQL.PROJECTIONS);
			mapper.addResultMap("cdEotLd", String.class);
			mapper.addResultMap("cdEotClaro", String.class);
			mapper.addResultMap("aaCiclo", Long.class);
			mapper.addResultMap("mmCiclo", Long.class);
			mapper.addResultMap("cdCiclo", Long.class);			
			mapper.addResultMap("vlLiquidoChamada", Double.class);
			mapper.addResultMap("vlBrutoChamada", Double.class);
			mapper.addResultMap("vlFaturado", Double.class);
			mapper.addResultMap("vlFaturadoLiquido", Double.class);
			mapper.addResultMap("qtCdrs", Long.class);
			return mapper.execute();
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }
	}
	
	private NativeSQLViewMapper<SccArquivoSumarizado> montarCamposQuery(Boolean tipoPeriodo, Session session) {
		if (tipoPeriodo) {
			return new NativeSQLViewMapper<SccArquivoSumarizado>(session, ControleRemessaPorEventoSql.SQL_DT_REF, SccArquivoSumarizado.class );
		}
		return new NativeSQLViewMapper<SccArquivoSumarizado>(session, ControleRemessaPorEventoSql.SQL, SccArquivoSumarizado.class );
	}
	
	private String montarGroupBy(Boolean tipoPeriodo) {
		if(tipoPeriodo) {
			return ControleRemessaPorEventoSql.PROJECTION_DT_REF;
		}
		return ControleRemessaPorEventoSql.PROJECTION;
	}
	
	public List<SccArquivoSumarizado> pesquisarPorEvento(String cdEOTClaro, String cdEOTLD, Date dataInicial, Date dataFinal, Long cdProdutoCobilling, Long[] statusCdr, boolean holding, boolean tipoPeriodo) throws DAOException {
		
		List<SccArquivoSumarizado> listArquivoSumarizado = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			
			
			NativeSQLViewMapper<SccArquivoSumarizado> mapper = montarCamposQuery(tipoPeriodo, session);
			
			if(tipoPeriodo) {
				
				mapper.addArgument("dataInicial", DateUtils.lowDateTime(dataInicial), ControleRemessaPorEventoSql.FILTRO_DT_PROC_EXTERNA);
				mapper.addArgument("dataFinal", DateUtils.highDateTime(dataFinal), ControleRemessaPorEventoSql.FILTRO_DT_PROC_EXTERNA_FIM);
			} else {
				mapper.addArgument("dataInicial", dataInicial, ControleRemessaPorEventoSql.FILTRO_DT_PROC_CLARO);
				mapper.addArgument("dataFinal", dataFinal, ControleRemessaPorEventoSql.FILTRO_DT_PROC_CLARO_FIM);
			}
			
			if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)) {
				if (holding) {
					mapper.addArgument("cdEOTClaro", cdEOTClaro, ControleRemessaPorEventoSql.FILTRO_EOT_CLARO_HOLDING);
				} else {
					mapper.addArgument("cdEOTClaro", cdEOTClaro, ControleRemessaPorEventoSql.FILTRO_EOT_CLARO);
				}
			}
			
			if (cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)) {
				mapper.addArgument("cdEOTLD", cdEOTLD, ControleRemessaPorEventoSql.FILTRO_EOT_LD);
			}
			
			if (cdProdutoCobilling != null && !cdProdutoCobilling.equals(BasicDAO.GET_ALL)) {
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling, ControleRemessaPorEventoSql.FILTRO_PRODUTO);
			}
			
			if (statusCdr != null && statusCdr.length > 0) {
				mapper.addArgument("statusCdr", statusCdr, ControleRemessaPorEventoSql.FILTRO_STATUS_CDR);
			}
			
			mapper.addResultMap("dtProcExterna", Date.class);
			mapper.addResultMap("cdEotClaro", String.class);
			mapper.addResultMap("cdEotLd", String.class);
			mapper.addResultMap("dsOperadoraClaro", String.class);
			mapper.addResultMap("dsOperadoraLd", String.class);
			mapper.addResultMap("cdStatusCdr", Long.class);
			mapper.addResultMap("dsProduto", String.class);
			mapper.addResultMap("dsStatusCdr", String.class);
			mapper.addResultMap("cdCiclo", Long.class);
			mapper.addResultMap("mmCiclo", Long.class);
			mapper.addResultMap("aaCiclo", Long.class);
			mapper.addResultMap("qtCdrs", Long.class);
			mapper.addResultMap("vlLiquidoChamada", Double.class);
			mapper.addResultMap("vlBrutoChamada", Double.class);
			mapper.addResultMap("qtDuracaoReal", Double.class);
			mapper.addResultMap("qtDuracaoTarifada", Double.class);
			mapper.setProjections(montarGroupBy(tipoPeriodo));
			
			
			listArquivoSumarizado = mapper.execute();
			
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return listArquivoSumarizado;
	}
	
	public List<SccArquivoSumarizado> geraSumarizadoPeriodo(String cdEOTClaro,String cdEOTLD, Date dataInicial, Date dataFinal, boolean holding) throws DAOException {
		try {
			Session session = getSessionFactory().getCurrentSession();			
			NativeSQLViewMapper<SccArquivoSumarizado> mapper = new NativeSQLViewMapper<SccArquivoSumarizado>(session, SumarizadoPeriodoSQL.SQL, SccArquivoSumarizado.class);			
			mapper.addResultMap("cdStatusCdr", Long.class);
			mapper.addResultMap("qtCdrs", Long.class);
			mapper.addResultMap("vlLiquidoChamada", Double.class);
			mapper.addResultMap("vlBrutoChamada", Double.class);
			mapper.addResultMap("qtDuracaoReal", Double.class);
			mapper.addResultMap("qtDuracaoTarifada", Double.class);
			mapper.addResultMap("dsStatusCdr", String.class);
			if ((cdEOTClaro != null)&& (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {
				if (holding) {
					mapper.addArgument("cdEOTClaro", cdEOTClaro,SumarizadoPeriodoSQL.FILTRO_EOT_CLARO_HOLDING);
				} else {
					mapper.addArgument("cdEOTClaro", cdEOTClaro,SumarizadoPeriodoSQL.FILTRO_EOT_CLARO);
				}
			}
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {				
				mapper.addArgument("cdEOTLD", cdEOTLD,SumarizadoPeriodoSQL.FILTRO_EOT_LD);
			}	
			mapper.addArgument("dataInicial", dataInicial);
			mapper.addArgument("dataFinal", dataFinal);			
			mapper.setProjections(SumarizadoPeriodoSQL.PROJECTIONS);
			return mapper.execute();
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }
	}
	
	public List<SccArquivoSumarizado> findSumarizadoPeriodo(String cdEOTClaro,String cdEOTLD, Date dataInicial, Date dataFinal, Long produto, boolean holding) throws DAOException{
		
		List<SccArquivoSumarizado> lista = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccArquivoSumarizado> mapper = null;
			if(produto != null && produto.intValue() != -1 && produto.intValue() != 0) {
				if(produto.intValue() == 1 ) //arrecadado
					mapper = new NativeSQLViewMapper<SccArquivoSumarizado>(session, SumarizadoPeriodoSQL.SQL_ARRECADADO, SccArquivoSumarizado.class);
				else // diferente de arrecadado
					mapper = new NativeSQLViewMapper<SccArquivoSumarizado>(session, SumarizadoPeriodoSQL.SQL_DIF_ARRECADADO, SccArquivoSumarizado.class);
				
				mapper.addArgument("produto", produto);
			} else{
				mapper = new NativeSQLViewMapper<SccArquivoSumarizado>(session, SumarizadoPeriodoSQL.SQL2, SccArquivoSumarizado.class);
			}
			
			if ((cdEOTClaro != null)&& (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {
				if (holding) {
					mapper.addArgument("cdEOTClaro", cdEOTClaro,SumarizadoPeriodoSQL.FILTRO_EOT_CLARO_HOLDING);
				} else {
					mapper.addArgument("cdEOTClaro", cdEOTClaro,SumarizadoPeriodoSQL.FILTRO_EOT_CLARO);
				}
			}
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {				
				mapper.addArgument("cdEOTLD", cdEOTLD,SumarizadoPeriodoSQL.FILTRO_EOT_LD);
			}	
			
		
			mapper.addArgument("dataInicial", dataInicial, SumarizadoPeriodoSQL.FILTRO_DATA_INICIO);
			mapper.addArgument("dataFinal", dataFinal, SumarizadoPeriodoSQL.FILTRO_DATA_FIM);			
			mapper.setProjections(SumarizadoPeriodoSQL.PROJECTIONS2);
			gerarResultMap(mapper, 1);
			
			lista = (List<SccArquivoSumarizado>) mapper.execute();
			
		} catch (Exception e) {
			 throw new DAOException(e.getMessage(), e);
		}
		
		return lista;
	}
	
	public List<SccArquivoSumarizado> findSumarizadoByPeriodoAgrupado(String cdEOTClaro,String cdEOTLD, Date dataInicial, Date dataFinal, Long produto, boolean holding) throws DAOException{
		
		List<SccArquivoSumarizado> lista = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccArquivoSumarizado> mapper = null;
			if(produto != null && produto.intValue() != -1 && produto.intValue() != 0) {
				if(produto.intValue() == 1 ) //arrecadado
					mapper = new NativeSQLViewMapper<SccArquivoSumarizado>(session, SumarizadoPeriodoSQL.SQL_ARRECADADO_AGRUPADO, SccArquivoSumarizado.class);
				else // diferente de arrecadado
					mapper = new NativeSQLViewMapper<SccArquivoSumarizado>(session, SumarizadoPeriodoSQL.SQL_DIF_ARRECADADO_AGRUPADO, SccArquivoSumarizado.class);

				mapper.addArgument("produto", produto);
			} else{
				mapper = new NativeSQLViewMapper<SccArquivoSumarizado>(session, SumarizadoPeriodoSQL.SQL_AGRUPADO, SccArquivoSumarizado.class);
			}
			
			if ((cdEOTClaro != null)&& (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {
				if (holding) {
					mapper.addArgument("cdEOTClaro", cdEOTClaro,SumarizadoPeriodoSQL.FILTRO_EOT_CLARO_HOLDING);
				} else {
					mapper.addArgument("cdEOTClaro", cdEOTClaro,SumarizadoPeriodoSQL.FILTRO_EOT_CLARO);
				}
			}
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {				
				mapper.addArgument("cdEOTLD", cdEOTLD,SumarizadoPeriodoSQL.FILTRO_EOT_LD);
			}	
			mapper.addArgument("dataInicial", dataInicial);
			mapper.addArgument("dataFinal", dataFinal);			
			mapper.setProjections(SumarizadoPeriodoSQL.SQL_AGRUPADO2);
			gerarResultMap(mapper,2);
			
			lista = (List<SccArquivoSumarizado>) mapper.execute();
			
		} catch (Exception e) {
			 throw new DAOException(e.getMessage(), e);
		}
		
		return lista;
	}
	
	private void gerarResultMap(NativeSQLViewMapper<SccArquivoSumarizado> mapper, int opc){
		if(opc == 1){
			mapper.addResultMap("aaCiclo", Long.class);
			mapper.addResultMap("mmCiclo", Long.class);
		}
		mapper.addResultMap("cdStatusCdr", Long.class);
		mapper.addResultMap("dsStatusCdr", String.class);
		mapper.addResultMap("qtCdrs", Long.class);
		mapper.addResultMap("vlLiquidoChamada", Double.class);
		mapper.addResultMap("vlBrutoChamada", Double.class);
		mapper.addResultMap("qtDuracaoReal", Double.class);
		mapper.addResultMap("qtDuracaoTarifada", Double.class);
	}
	
	@Override
	public List<SccArquivoSumarizado> geraEvolucaoCDRs(String grpCdr, String cdEOTClaro, String cdEOTLD, Date dataInicial,Date dataFinal, Long cdProdutoCobilling,boolean holding) throws DAOException {
		
		List<SccArquivoSumarizado> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccArquivoSumarizado> mapper = new NativeSQLViewMapper<SccArquivoSumarizado>(session, SumarizadoPeriodoCicloSQL.SQL_EVOLUCAO_STATUS, SccArquivoSumarizado.class);
			if(!cdProdutoCobilling.equals(BasicDAO.GET_ALL)){
				mapper.appendSQL(",SCC_COMPOSICAO_PRODUTO CP , SCC_PRODUTO_COBILLING P  ");
				mapper.appendSQL("WHERE A.SQ_ARQUIVO_ORIGEM = 0  AND A.CD_TIPO_ARQUIVO in (5,555)  ");
				mapper.appendSQL("AND  ST.CD_STATUS_CDR = S.CD_STATUS_CDR ");
				mapper.appendSQL("AND S.CD_COMPONENTE_PRODUTO = CP.CD_COMPONENTE_PRODUTO  ");
				mapper.appendSQL("AND CP.CD_PRODUTO_COBILLING = P.CD_PRODUTO_COBILLING  ");
				mapper.appendSQL("AND A.SQ_ARQUIVO = S.SQ_ARQUIVO_ORIGINAL  AND S.QT_CDRS <> 0  ");
				mapper.appendSQL("AND A.DT_PROC_EXTERNA >= :dataInicial ");
				mapper.appendSQL("AND A.DT_PROC_EXTERNA <= :dataFinal");

			}else{
//				mapper.appendSQL(", SCC_OPERADORA O  ");
				mapper.appendSQL("WHERE A.SQ_ARQUIVO_ORIGEM = 0  ");
				mapper.appendSQL("AND A.CD_TIPO_ARQUIVO in (5,555)  ");
				mapper.appendSQL("AND  ST.CD_STATUS_CDR = S.CD_STATUS_CDR ");
//				mapper.appendSQL("AND S.CD_COMPONENTE_PRODUTO = CP.CD_COMPONENTE_PRODUTO  ");
//				mapper.appendSQL("AND CP.CD_PRODUTO_COBILLING = P.CD_PRODUTO_COBILLING  ");
				mapper.appendSQL("AND A.SQ_ARQUIVO = S.SQ_ARQUIVO_ORIGINAL  AND S.QT_CDRS <> 0  ");
				mapper.appendSQL("AND A.DT_PROC_EXTERNA >= :dataInicial ");
				mapper.appendSQL("AND A.DT_PROC_EXTERNA <= :dataFinal");
				
			}
			
			if(StringUtils.isNotEmpty(grpCdr)) {
				mapper.appendSQL("AND S.CD_STATUS_CDR IN (" + grpCdr + ")");
			}

			mapper.addResultMap("cdStatusCdr", Long.class);
			mapper.addResultMap("qtCdrs", Long.class);
			mapper.addResultMap("vlLiquidoChamada", Double.class);
			mapper.addResultMap("vlBrutoChamada", Double.class);
			mapper.addResultMap("mesAno", String.class);
			mapper.addResultMap("dsStatusCdr", String.class);
						
			if ((cdEOTClaro != null)&& (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {
				if (holding) {
					mapper.addArgument("cdEOTClaro", cdEOTClaro, SumarizadoPeriodoCicloSQL.FILTRO_EOT_CLARO_HOLDING);
				} else {
					mapper.addArgument("cdEOTClaro", cdEOTClaro, SumarizadoPeriodoCicloSQL.FILTRO_EOT_CLARO);
				}
			}	
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {				
				mapper.addArgument("cdEOTLD", cdEOTLD,SumarizadoPeriodoCicloSQL.FILTRO_EOT_LD);
			}
			
			if(!cdProdutoCobilling.equals(1L) && !cdProdutoCobilling.equals(BasicDAO.GET_ALL)){
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling,SumarizadoPeriodoCicloSQL.FILTRO_PRODUTO);
			}else if(cdProdutoCobilling.equals(1L)){
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling,SumarizadoPeriodoCicloSQL.FILTRO_PRODUTO_1);
			}
			
			mapper.addArgument("dataInicial", dataInicial);
			mapper.addArgument("dataFinal", dataFinal);			
			mapper.setProjections(SumarizadoPeriodoCicloSQL.PROJECTIONS_EVOLUCAO_STATUS);
			
			list =(List<SccArquivoSumarizado>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
		
	}
	
	public List<SccArquivoSumarizado> geraSumarizadoPeriodoCiclo(String cdEOTClaro, String cdEOTLD, Date dataInicial,Date dataFinal, boolean holding) throws DAOException {
		try {
			Session session = getSessionFactory().getCurrentSession();			
			NativeSQLViewMapper<SccArquivoSumarizado> mapper = new NativeSQLViewMapper<SccArquivoSumarizado>(session, SumarizadoPeriodoCicloSQL.SQL, SccArquivoSumarizado.class);			
			mapper.addResultMap("aaCiclo", Long.class);
			mapper.addResultMap("mmCiclo", Long.class);
			mapper.addResultMap("cdStatusCdr", Long.class);
			mapper.addResultMap("qtCdrs", Long.class);
			mapper.addResultMap("vlLiquidoChamada", Double.class);
			mapper.addResultMap("vlBrutoChamada", Double.class);
			mapper.addResultMap("qtDuracaoReal", Double.class);
			mapper.addResultMap("qtDuracaoTarifada", Double.class);
			mapper.addResultMap("dsStatusCdr", String.class);
			if ((cdEOTClaro != null)&& (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {
				if (holding) {
					mapper.addArgument("cdEOTClaro", cdEOTClaro, SumarizadoPeriodoCicloSQL.FILTRO_EOT_CLARO_HOLDING);
				} else {
					mapper.addArgument("cdEOTClaro", cdEOTClaro, SumarizadoPeriodoCicloSQL.FILTRO_EOT_CLARO);
				}
			}	
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {				
				mapper.addArgument("cdEOTLD", cdEOTLD,SumarizadoPeriodoCicloSQL.FILTRO_EOT_LD);
			}
			mapper.addArgument("dataInicial", dataInicial);
			mapper.addArgument("dataFinal", dataFinal);			
			mapper.setProjections(SumarizadoPeriodoCicloSQL.PROJECTIONS);
			return mapper.execute();
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }
	}
	
	@Override
	public DemonstrativoSaldosLotesView gerarTotalSaldoLote(String cdEOTClaro, String cdEOTLD, Long cdProdutoCobilling,Long cdTipoArquivo, Date dataInicial, Date dataFinal) throws DAOException {
		
		DemonstrativoSaldosLotesView entity = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<DemonstrativoSaldosLotesView> mapper = new NativeSQLViewMapper<DemonstrativoSaldosLotesView>(session, DemonstrativoSaldosLotesSQL.SQL_TOTAL, DemonstrativoSaldosLotesView.class);
			mapper.addResultMap("cdMotivoEvento"	, String.class);
			mapper.addResultMap("dsMotivoEvento"	, String.class);
			mapper.addResultMap("cdMotivoRejeicao"	, String.class);
			mapper.addResultMap("dsMotivoRejeicao"	, String.class);
			mapper.addResultMap("qtCdrs"			, Long.class);
			mapper.addResultMap("qtMinutos"			, Double.class);
			mapper.addResultMap("valor"				, Double.class);
			if ((cdEOTClaro != null)&& (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {	
				mapper.addArgument("cdEOTClaro", cdEOTClaro,DemonstrativoSaldosLotesSQL.FILTRO_CLARO);
			}
			if ((cdEOTLD != null)&& (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {	
				mapper.addArgument("cdEOTLD", cdEOTLD,DemonstrativoSaldosLotesSQL.FILTRO_LD);
			}
			
			if(!cdProdutoCobilling.equals(1L)){
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling,DemonstrativoSaldosLotesSQL.FILTRO_PRODUTO);
			}else{
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling,DemonstrativoSaldosLotesSQL.FILTRO_PRODUTO_1);
			}
			if ((cdTipoArquivo != null)&& (!cdTipoArquivo.equals(BasicDAO.GET_ALL))) {	
				mapper.addArgument("cdTipoArquivo", cdTipoArquivo,DemonstrativoSaldosLotesSQL.FILTRO_ARQUIVO);
			}
			mapper.addArgument("dataInicial", dataInicial);
			mapper.addArgument("dataFinal", dataFinal);
			
			List<DemonstrativoSaldosLotesView> list = mapper.execute();
			if(list != null && list.size() > 0){
				entity = list.get(0);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return entity;
	}
	
	public List<DemonstrativoSaldosLotesView> geraDemonstrativoSaldosLotes(String cdEOTClaro, String cdEOTLD, Long cdProdutoCobilling,Long cdTipoArquivo, Date dataInicial, Date dataFinal) throws DAOException {
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<DemonstrativoSaldosLotesView> mapper = new NativeSQLViewMapper<DemonstrativoSaldosLotesView>(session, DemonstrativoSaldosLotesSQL.SQL, DemonstrativoSaldosLotesView.class);
			mapper.addResultMap("cdMotivoEvento"	, String.class);
			mapper.addResultMap("dsMotivoEvento"	, String.class);
			mapper.addResultMap("cdMotivoRejeicao"	, String.class);
			mapper.addResultMap("dsMotivoRejeicao"	, String.class);
			mapper.addResultMap("qtCdrs"			, Long.class);
			mapper.addResultMap("qtMinutos"			, Double.class);
			mapper.addResultMap("valor"				, Double.class);
			if ((cdEOTClaro != null)&& (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {	
				mapper.addArgument("cdEOTClaro", cdEOTClaro,DemonstrativoSaldosLotesSQL.FILTRO_CLARO);
			}
			if ((cdEOTLD != null)&& (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {	
				mapper.addArgument("cdEOTLD", cdEOTLD,DemonstrativoSaldosLotesSQL.FILTRO_LD);
			}
			
			if(!cdProdutoCobilling.equals(1L)){
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling,DemonstrativoSaldosLotesSQL.FILTRO_PRODUTO);
			}else{
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling,DemonstrativoSaldosLotesSQL.FILTRO_PRODUTO_1);
			}
			if ((cdTipoArquivo != null)&& (!cdTipoArquivo.equals(BasicDAO.GET_ALL))) {	
				mapper.addArgument("cdTipoArquivo", cdTipoArquivo,DemonstrativoSaldosLotesSQL.FILTRO_ARQUIVO);
			}
			mapper.addArgument("dataInicial", dataInicial);
			mapper.addArgument("dataFinal", dataFinal);
			mapper.setProjections(DemonstrativoSaldosLotesSQL.PROJECTIOS);
			return mapper.execute();
		} catch (Exception e) { throw new DAOException(e.getMessage(), e); }
	}
	
	public Collection<SccArquivoSumarizado> gerarRelatorioPerdaFaturamento(SccFiltroRelPerdaFaturamento filtro) throws DAOException {
		
		Collection<SccArquivoSumarizado> list = null;
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccArquivoSumarizado> mapper = new NativeSQLViewMapper<SccArquivoSumarizado>(session, ControleRemessaPorCicloSQL.SQL_PERDA_FATURAMENTO, SccArquivoSumarizado.class);
			mapper.addArgument("dataInicial", DateUtils.lowDateTime(filtro.getDataInicialPeriodo()));
			mapper.addArgument("dataFinal", DateUtils.highDateTime2(filtro.getDataFinalPeriodo()));

			if(StringUtils.isNotEmpty(filtro.getOperadoraClaro()) && !filtro.getOperadoraClaro().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", filtro.getOperadoraClaro(), ControleRemessaPorCicloSQL.FILTRO_EOT_CLARO_REL_PF);
			}
			
			if(StringUtils.isNotEmpty(filtro.getOperadoraExterna()) && ! filtro.getOperadoraExterna().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTLD", filtro.getOperadoraExterna(), ControleRemessaPorCicloSQL.FILTRO_EOT_LT_REL_PF);
			}
	        if (StringUtils.isNotEmpty(filtro.getEvento()) && !filtro.getEvento().equals(BasicDAO.GET_ALL_STRING)) {
	        	mapper.appendSQL(getBetweenStatus(filtro.getEvento()));
	        	
	        } else {
	        	mapper.appendSQL(getAllStatus());
	        }
	        
	        mapper.setProjections(ControleRemessaPorCicloSQL.PROJECTIONS_REL_PF);
	        
	        mapper.addResultMap("cdEotLd", String.class);
	        mapper.addResultMap("cdEotClaro", String.class);
	        mapper.addResultMap("dtProcExterna", Date.class);
	        mapper.addResultMap("cdStatusCdr", Long.class);
	        mapper.addResultMap("cdSubStatusCdr", String.class);
	        mapper.addResultMap("vlLiquidoChamada", Double.class);
	        mapper.addResultMap("vlBrutoChamada", Double.class);
	        mapper.addResultMap("qtCdrs", Long.class);

	        list = (Collection<SccArquivoSumarizado>)mapper.execute();
	        
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;
	}
	
	
	
    private String getBetweenStatus(String fileType) {
    	StringBuilder sb = new StringBuilder();
    	
        if (StringUtils.isNotEmpty(fileType)) {
        	switch(Integer.parseInt(fileType)) {
	    		case (StatusCDRConstants.CDRSTATUS_REJEITADO_2):
	            	sb.append("\n   AND (SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_REJEITADO_C2_ESB);
	    			sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_REJEITADO_C1);
	        		sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_REJEITADO_C2_MOB);
	                sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'C1'");
	        		sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'C2') -- (4)");
	        	break;
	    		case (StatusCDRConstants.CDRSTATUS_REJEITADO_C1_2):
	                sb.append("\n   AND (SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_REJEITADO_C1);
	                sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'C1') -- (4)");
	        	break;
	    		case (StatusCDRConstants.CDRSTATUS_REJEITADO_C2):
	            	sb.append("\n   AND (SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_REJEITADO_C2_ESB);
	        		sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_REJEITADO_C2_MOB);
	        		sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'C2') -- (4)");
	        	break;
	    		case (StatusCDRConstants.CDRSTATUS_EXCLUIDO):
	            	sb.append("\n   AND (SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_EXCLUIDO_X2_ESB);
	        		sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_EXCLUIDO_X2_MOB);
	                sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_EXCLUIDO_X1);
	                sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'X1'");
	        		sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'X2') -- (4)");
	        	break;
	    		case (StatusCDRConstants.CDRSTATUS_EXCLUIDO_X1_2):
	                sb.append("\n   AND (SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_EXCLUIDO_X1);
	                sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'X1') -- (4)");
	        	break;
	    		case (StatusCDRConstants.CDRSTATUS_EXCLUIDO_X2):
	            	sb.append("\n   AND (SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_EXCLUIDO_X2_ESB);
	        		sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_EXCLUIDO_X2_MOB);
	        		sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'X2') -- (4)");
	        	break;
	    		case (StatusCDRConstants.CDRSTATUS_PERDIDO):
	            	sb.append("\n   AND (SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_PERDIDO_ESB);
	            	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_PERDIDO_MOB);
	            	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P1'");
	            	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P2'");
	            	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P3'");
	            	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P4'");
	            	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P5'");
	            	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P6'");
	            	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P7') -- (4)");
	        	break;
	    		case (StatusCDRConstants.CDRSTATUS_FATURADO):
	            	sb.append("\n   AND (SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_FATURADO_ESB);
	            	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_FATURADO_MOB + ") -- (4)");
	        	break;
	    		case (StatusCDRConstants.CDRSTATUS_CONTESTADO):
	            	sb.append("\n   AND (SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_CONTESTADO_ESB);
	            	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_CONTESTADO_MOB + ") -- (4)");
	        	break;
        	}
        }
        return sb.toString();
    }
    
    private String getAllStatus() {
    	StringBuilder sb = new StringBuilder();
        sb.append("\n   AND ( SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_REJEITADO_C1);
        sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'C1'");
    	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_REJEITADO_C2_ESB);
		sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_REJEITADO_C2_MOB);
		sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'C2'");
        sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_EXCLUIDO_X1);
        sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'X1'");
    	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_EXCLUIDO_X2_ESB);
		sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_EXCLUIDO_X2_MOB);
		sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'X2'");
    	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_PERDIDO_PPC);
    	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_PERDIDO_ESB);
    	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_PERDIDO_MOB);
    	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P1'");
    	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P2'");
    	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P3'");
    	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P4'");
    	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P5'");
    	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P6'");
    	sb.append("\n   OR SU.CD_SUB_STATUS_CDR = 'P7'");
    	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_CONTESTADO_ESB);
    	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_CONTESTADO_MOB);
    	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_FATURADO_ESB);
    	sb.append("\n   OR SU.CD_STATUS_CDR = " + StatusCDRConstants.CDRSTATUS_FATURADO_MOB + " ) ");
        return sb.toString();
    }


	
}
