package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

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
			if ((cdProdutoCobilling != null)&& (!cdProdutoCobilling.equals(BasicDAO.GET_ALL))) {	
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling,DemonstrativoSaldosLotesSQL.FILTRO_PRODUTO);
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
	
}
