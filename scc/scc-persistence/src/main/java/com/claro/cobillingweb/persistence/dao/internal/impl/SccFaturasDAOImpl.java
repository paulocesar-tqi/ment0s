package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccFaturasDAO;
import com.claro.cobillingweb.persistence.dao.query.SccFaturasSQL;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.filtro.SccFiltroAgingFaturas;
import com.claro.cobillingweb.persistence.filtro.SccFiltroFaturas;
import com.claro.cobillingweb.persistence.utils.DateUtils;
import com.claro.cobillingweb.persistence.view.SccAgingFaturasView;
import com.claro.cobillingweb.persistence.view.SccFaturaView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

@Repository
public class SccFaturasDAOImpl extends HibernateBasicDAOImpl<SccFaturaView> implements
		SccFaturasDAO {
	
	private static final String SINTETICO = "S";
	private static final String FATURA = "F";

	@Override
	public List<SccFaturaView> getAll() throws DAOException {
		
		return null;
	}
	

	@Override
	public List<SccFaturaView> gerarRelatorioFaturas(SccFiltroFaturas filtro)
			throws DAOException {
		
		
		List<SccFaturaView> list = null;
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccFaturaView> mapper = null;
			if(filtro.getRelatorioSelecionado().equals(SINTETICO)){
				mapper = new NativeSQLViewMapper<SccFaturaView>(session, SccFaturasSQL.SQL_SINTETICO, SccFaturaView.class);
			}else{
				mapper = new NativeSQLViewMapper<SccFaturaView>(session, SccFaturasSQL.SQL_FATURA, SccFaturaView.class);
			}
			
			if(StringUtils.isNotEmpty(filtro.getOperadoraClaro()) && !filtro.getOperadoraClaro().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", filtro.getOperadoraClaro(), SccFaturasSQL.FILTRO_EOTCLARO);
			}
			
			if(StringUtils.isNotEmpty(filtro.getCdCsp()) && !filtro.getCdCsp().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdCsp", filtro.getCdCsp(), SccFaturasSQL.FILTRO_CSP);
			}
			
			if(StringUtils.isNotEmpty(filtro.getStatusFatura()) && !filtro.getStatusFatura().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("status", filtro.getStatusFatura(), SccFaturasSQL.FILTRO_STATUS_FATURA);
				
			}else{
				mapper.appendSQL("AND FATURA.CD_STATUS_FATURA IN ('C', 'O')");
			}
			
			if(filtro.getTipoData() != null){
				if(filtro.getTipoData().equals(1L)){
					mapper.addArgument("dtEmissao", DateUtils.lowDateTime(filtro.getDataInicialPeriodo()), SccFaturasSQL.FILTRO_DT_EMISSAO);
					mapper.addArgument("dtFimEmissao", DateUtils.highDateTime2(filtro.getDataFinalPeriodo()), SccFaturasSQL.FILTRO_DT_FIM_EMISSAO);
					
				}
				
				if(filtro.getTipoData().equals(2L)){
					mapper.addArgument("dtCarga", DateUtils.lowDateTime(filtro.getDataInicialPeriodo()), SccFaturasSQL.FILTRO_DT_CARGA);
					mapper.addArgument("dtFimCarga", DateUtils.highDateTime2(filtro.getDataFinalPeriodo()), SccFaturasSQL.FILTRO_DT_FIM_CARGA);

				}
				if(filtro.getTipoData().equals(3L)){
					mapper.addArgument("dtVencimento", DateUtils.lowDateTime(filtro.getDataInicialPeriodo()), SccFaturasSQL.FILTRO_DT_VENCIMENTO_FATURA);
					mapper.addArgument("dtFimVencimento", DateUtils.highDateTime2(filtro.getDataFinalPeriodo()), SccFaturasSQL.FILTRO_DT_FIM_VENCIMENTO_FATURA);

				}
				
				if(StringUtils.isNotEmpty(filtro.getNumeroFatura())) {
					mapper.addArgument("numeroFatura", filtro.getNumeroFatura(), SccFaturasSQL.FILTRO_NUMERO_FATURA);
				}

			}
			if(filtro.getRelatorioSelecionado().equals(SINTETICO)){
				mapper.setProjections(SccFaturasSQL.GROUP_BY_SINTETICO);
			}
			mapper.addResultMap("eotClaro", String.class);
			mapper.addResultMap("csp", String.class);
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("uf", String.class);
			mapper.addResultMap("cicloMesAno", String.class);
			//mapper.addResultMap("cdCiclo", Integer.class);
			//mapper.addResultMap("mmCiclo", Integer.class);
			//mapper.addResultMap("aaCiclo", Integer.class);
			if(filtro.getRelatorioSelecionado().equals(FATURA)){
				mapper.addResultMap("numeroFatura", String.class);
			}
			
			mapper.addResultMap("dataEmissao", Timestamp.class);
			mapper.addResultMap("dataVencimentoOriginal", Timestamp.class);
			mapper.addResultMap("dataVencimento", Timestamp.class);
			mapper.addResultMap("valorOriginal", Double.class);
			mapper.addResultMap("valor", Double.class);
			mapper.addResultMap("valorICMS", Double.class);
			mapper.addResultMap("status", String.class);
			
			mapper.addResultMap("situacaoEvento", String.class);
			mapper.addResultMap("aging", Long.class);
			mapper.addResultMap("ajuste", Double.class);
			if(filtro.getRelatorioSelecionado().equals(FATURA)){
				mapper.addResultMap("numeroNotaFiscal", Long.class);
			}
			
			mapper.addResultMap("serie", String.class);
			mapper.addResultMap("subSerie", String.class);
			mapper.addResultMap("totalCreditos", Double.class);
			mapper.addResultMap("totalAjustes", Double.class);
			mapper.addResultMap("valorOfertasLD", Double.class);
			mapper.addResultMap("valorDescontosLD", Double.class);
			mapper.addResultMap("valorCreditosLD", Double.class);
			mapper.addResultMap("valorPago", Double.class);
			mapper.addResultMap("quantidadeEventos", Long.class);
			mapper.addResultMap("juros", Double.class);
			mapper.addResultMap("multas", Double.class);
			
			list = (List<SccFaturaView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;
	}
	
	public List<SccFaturaView> gerarRelatorioJurosMultas(SccFiltro filtro)	throws DAOException {
		
		List<SccFaturaView> list = null;
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccFaturaView> mapper = new NativeSQLViewMapper<SccFaturaView>(session, SccFaturasSQL.SQL_JUROS_MULTAS, SccFaturaView.class);
			
			if(StringUtils.isNotEmpty(filtro.getOperadoraClaro()) && !filtro.getOperadoraClaro().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", filtro.getOperadoraClaro(), SccFaturasSQL.FILTRO_EOTCLARO);
			}
			
			if(StringUtils.isNotEmpty(filtro.getCdCsp()) && !filtro.getCdCsp().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdCsp", filtro.getCdCsp(), SccFaturasSQL.FILTRO_CSP);
			}
			
			mapper.addArgument("dtCarga", filtro.getDataInicialPeriodo(), SccFaturasSQL.FILTRO_DT_CARGA);
			mapper.addArgument("dtFimCarga", filtro.getDataFinalPeriodo(), SccFaturasSQL.FILTRO_DT_FIM_CARGA);

			mapper.addResultMap("csp", String.class);
			mapper.addResultMap("uf", String.class);
			mapper.addResultMap("eotClaro", String.class);
			mapper.addResultMap("numeroFatura", String.class);
			mapper.addResultMap("dataEmissao", Timestamp.class);
			mapper.addResultMap("dataVencimento", Timestamp.class);
			mapper.addResultMap("juros", Double.class);
			mapper.addResultMap("multas", Double.class);
			mapper.addResultMap("valor", Double.class);
			
			list = (List<SccFaturaView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;

	}
	
	@Override
	public List<SccFaturaView> gerarComboCiclo() throws DAOException {
		
		List<SccFaturaView> list = null;
		
		try{
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccFaturaView> mapper = new NativeSQLViewMapper<SccFaturaView>(session, SccFaturasSQL.SQL_CICLO, SccFaturaView.class);
			mapper.addResultMap("cdCiclo", Integer.class);
			mapper.addResultMap("mmCiclo", Integer.class);
			mapper.addResultMap("aaCiclo", Integer.class);
			
			list = (List<SccFaturaView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;

	}
	
	public List<SccAgingFaturasView> gerarRelatorioAgingFaturas(SccFiltroAgingFaturas filtro) throws DAOException{
		
		List<SccAgingFaturasView> list = null;
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccAgingFaturasView> mapper = new NativeSQLViewMapper<SccAgingFaturasView>(session, SccFaturasSQL.SQL_AGING, SccAgingFaturasView.class); 
			
			if(StringUtils.isNotEmpty(filtro.getOperadoraClaro()) && !filtro.getOperadoraClaro().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", filtro.getOperadoraClaro(), SccFaturasSQL.FILTRO_EOTCLARO);
			}
			
			if(StringUtils.isNotEmpty(filtro.getCdCsp()) && !filtro.getCdCsp().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdCsp", filtro.getCdCsp(), SccFaturasSQL.FILTRO_CSP);
			}
			
			mapper.addArgument("dtCarga", DateUtils.lowDateTime(filtro.getDataInicialPeriodo()), SccFaturasSQL.FILTRO_DT_CARGA);
			mapper.addArgument("dtFimCarga", DateUtils.highDateTime2(filtro.getDataFinalPeriodo()), SccFaturasSQL.FILTRO_DT_FIM_CARGA);
			
			mapper.setProjections(SccFaturasSQL.PROJECTIONS);
			
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("vencer", Double.class);
			mapper.addResultMap("valor1a10Dias", Double.class);
			mapper.addResultMap("valor11a20Dias", Double.class);
			mapper.addResultMap("valor21a30Dias", Double.class);
			mapper.addResultMap("valor31a60Dias", Double.class);
			mapper.addResultMap("valor61a90Dias", Double.class);
			mapper.addResultMap("maior90Dias", Double.class);			
			
			list = (List<SccAgingFaturasView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;

	}


}
