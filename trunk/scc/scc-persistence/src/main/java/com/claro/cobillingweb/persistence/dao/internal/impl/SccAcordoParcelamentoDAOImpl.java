/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccAcordoParcelamentoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccAcordoParcelamentoSQL;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.filtro.SccFiltroParcelamento;
import com.claro.cobillingweb.persistence.utils.DateUtils;
import com.claro.cobillingweb.persistence.view.SccAcordoParcelamentoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 93046251
 *
 */
public class SccAcordoParcelamentoDAOImpl extends HibernateBasicDAOImpl<SccAcordoParcelamentoView>
		implements SccAcordoParcelamentoDAO {

	@Override
	public List<SccAcordoParcelamentoView> getAll() throws DAOException {
		
		return null;
	}
	
	

	@Override
	public Collection<SccAcordoParcelamentoView> findByFilterSintetico(
			SccFiltroParcelamento filtro) throws DAOException {
		
		
		List<SccAcordoParcelamentoView> list = null;
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccAcordoParcelamentoView> mapper = new NativeSQLViewMapper<SccAcordoParcelamentoView>(session, SccAcordoParcelamentoSQL.SQL_SINTETICO, SccAcordoParcelamentoView.class); 
			mapper.addArgument("dtInicial", DateUtils.lowDateTime(filtro.getDataInicialPeriodo()));
			mapper.addArgument("dtFinal", DateUtils.highDateTime2( filtro.getDataFinalPeriodo()));
			if(StringUtils.isNotEmpty(filtro.getOperadoraClaro()) && !filtro.getOperadoraClaro().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", filtro.getOperadoraClaro(), SccAcordoParcelamentoSQL.FILTRO_EOTCLARO);
			}
			
			if(StringUtils.isNotEmpty(filtro.getCdCsp()) && !filtro.getCdCsp().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdCsp", filtro.getCdCsp(), SccAcordoParcelamentoSQL.FILTRO_CSP);
			}
			
			if(StringUtils.isNotEmpty(filtro.getStatus()) && !filtro.getStatus().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("status", filtro.getStatus(), SccAcordoParcelamentoSQL.FILTRO_STATUS_ACORDO);
			}
			
			mapper.setProjections(SccAcordoParcelamentoSQL.PROJECTIONS_SINTETICO);
			
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("dataAcordo", Date.class);
			mapper.addResultMap("status", String.class);
			mapper.addResultMap("qtdParcelas", Long.class);
			mapper.addResultMap("vlParcelaOperadora", Double.class);
			mapper.addResultMap("valorAcordado", Double.class);
			
			list = (List<SccAcordoParcelamentoView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;

	}



	@Override
	public Collection<SccAcordoParcelamentoView> findByFilterAnalitico(SccFiltroParcelamento filtro) throws DAOException {
		
		
		List<SccAcordoParcelamentoView> list = null;
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccAcordoParcelamentoView> mapper = new NativeSQLViewMapper<SccAcordoParcelamentoView>(session, SccAcordoParcelamentoSQL.SQL, SccAcordoParcelamentoView.class); 
			mapper.addArgument("dtInicial", filtro.getDataInicialPeriodo());
			mapper.addArgument("dtFinal", filtro.getDataFinalPeriodo());
			if(StringUtils.isNotEmpty(filtro.getOperadoraClaro()) && !filtro.getOperadoraClaro().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", filtro.getOperadoraClaro(), SccAcordoParcelamentoSQL.FILTRO_EOTCLARO);
			}
			
			if(StringUtils.isNotEmpty(filtro.getCdCsp()) && !filtro.getCdCsp().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdCsp", filtro.getCdCsp(), SccAcordoParcelamentoSQL.FILTRO_CSP);
			}
			
			if(StringUtils.isNotEmpty(filtro.getStatus()) && !filtro.getStatus().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("status", filtro.getStatus(), SccAcordoParcelamentoSQL.FILTRO_STATUS_ACORDO);
			}
			
			if(filtro.getNumeroAcordo() != null){
				mapper.addArgument("numeroAcordo", filtro.getNumeroAcordo(), SccAcordoParcelamentoSQL.FILTRO_NUMERO_ACORDO);
			}
			
			if(filtro.getNumeroConta() != null){
				mapper.addArgument("numeroConta", filtro.getNumeroConta(), SccAcordoParcelamentoSQL.FILTRO_NUMERO_CONTA);
			}
			
			mapper.setProjections(SccAcordoParcelamentoSQL.PROJECTIONS);
			
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("codAcordo", Double.class);
			mapper.addResultMap("dataAcordo", Date.class);
			mapper.addResultMap("valorAcordado", Double.class);
			mapper.addResultMap("NuAcordoParcelamento", Long.class);
			mapper.addResultMap("nuFatura", String.class);
			mapper.addResultMap("numConta", Long.class);
			mapper.addResultMap("vlParcelaOperadora", Double.class);			
			mapper.addResultMap("status", String.class);
			//mapper.addResultMap("qtdParcelas", Double.class);			
			
			list = (List<SccAcordoParcelamentoView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;

	}



	@Override
	public Collection<SccAcordoParcelamentoView> findByFilterRelAcompanhamentoAnalitico(
			SccFiltro filtro) throws DAOException {
		
		List<SccAcordoParcelamentoView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccAcordoParcelamentoView> mapper = new NativeSQLViewMapper<SccAcordoParcelamentoView>(session, SccAcordoParcelamentoSQL.SQL_ACOMPANHAMENTO, SccAcordoParcelamentoView.class); 
			mapper.addArgument("dtInicial", filtro.getDataInicialPeriodo());
			mapper.addArgument("dtFinal", filtro.getDataFinalPeriodo());
			if(StringUtils.isNotEmpty(filtro.getOperadoraClaro()) && !filtro.getOperadoraClaro().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", filtro.getOperadoraClaro(), SccAcordoParcelamentoSQL.FILTRO_EOTCLARO_ACOMPANHAMENTO);
			}
			
			if(StringUtils.isNotEmpty(filtro.getCdCsp()) && !filtro.getCdCsp().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdCsp", filtro.getCdCsp(), SccAcordoParcelamentoSQL.FILTRO_CSP_ACOMPANHAMENTO);
			}
			
			if(StringUtils.isNotEmpty(filtro.getStatus()) && !filtro.getStatus().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("status", filtro.getStatus(), SccAcordoParcelamentoSQL.FILTRO_STATUS_PARCELA_ACOMPANHAMENTO);
			}
			
			if(filtro.getNumeroAcordo() != null){
				mapper.addArgument("numeroAcordo", filtro.getNumeroAcordo(), SccAcordoParcelamentoSQL.FILTRO_NUMERO_ACORDO_ACOMPANHAMENTO);
			}
			
			if(filtro.getNumeroConta() != null){
				mapper.addArgument("numeroConta", filtro.getNumeroConta(), SccAcordoParcelamentoSQL.FILTRO_NUMERO_CONTA_ACOMPANHAMENTO);
			}
			
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("codAcordo", Double.class);
			mapper.addResultMap("NuAcordoParcelamento", Long.class);
			mapper.addResultMap("nuParcela", Long.class);
			mapper.addResultMap("valorAcordado", Double.class);
			mapper.addResultMap("status", String.class);
			mapper.addResultMap("numConta", Long.class);
			mapper.addResultMap("vlParcelaOperadora", Double.class);			
			
			list = (List<SccAcordoParcelamentoView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;
	}



	@Override
	public Collection<SccAcordoParcelamentoView> findByFilterRelAcompanhamentoSintetico(
			SccFiltro filtro) throws DAOException {
		
		List<SccAcordoParcelamentoView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccAcordoParcelamentoView> mapper = new NativeSQLViewMapper<SccAcordoParcelamentoView>(session, SccAcordoParcelamentoSQL.SQL_ACOMPANHAMENTO_SINTETICO, SccAcordoParcelamentoView.class); 
			mapper.addArgument("dtInicial", filtro.getDataInicialPeriodo());
			mapper.addArgument("dtFinal", filtro.getDataFinalPeriodo());
			if(StringUtils.isNotEmpty(filtro.getOperadoraClaro()) && !filtro.getOperadoraClaro().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", filtro.getOperadoraClaro(), SccAcordoParcelamentoSQL.FILTRO_EOTCLARO_ACOMPANHAMENTO);
			}
			
			if(StringUtils.isNotEmpty(filtro.getCdCsp()) && !filtro.getCdCsp().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdCsp", filtro.getCdCsp(), SccAcordoParcelamentoSQL.FILTRO_CSP_ACOMPANHAMENTO);
			}
			
			if(StringUtils.isNotEmpty(filtro.getStatus()) && !filtro.getStatus().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("status", filtro.getStatus(), SccAcordoParcelamentoSQL.FILTRO_STATUS_PARCELA_ACOMPANHAMENTO);
			}
			
			if(filtro.getNumeroAcordo() != null){
				mapper.addArgument("numeroAcordo", filtro.getNumeroAcordo(), SccAcordoParcelamentoSQL.FILTRO_NUMERO_ACORDO_ACOMPANHAMENTO);
			}
			
			if(filtro.getNumeroConta() != null){
				mapper.addArgument("numeroConta", filtro.getNumeroConta(), SccAcordoParcelamentoSQL.FILTRO_NUMERO_CONTA_ACOMPANHAMENTO);
			}
			
			mapper.setProjections(SccAcordoParcelamentoSQL.PROJECTIONS_ACOMPANHAMENTO);
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("status", String.class);
			mapper.addResultMap("qtdParcelas", Long.class);
			mapper.addResultMap("valorAcordado", Double.class);
			
			list = (List<SccAcordoParcelamentoView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;

	}
	
	


}
