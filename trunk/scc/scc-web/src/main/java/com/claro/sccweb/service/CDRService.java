package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.filtro.SccCdrCobillingFiltro;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoSumarizadoDecorator;


/**
 * Serviço para pesquisa de CDRs.
 *
 */
public interface CDRService {

	public List<SccCdrCobilling> pesquisaCDRs(SccCdrCobillingFiltro filtro) throws ServiceException,DAOException;
	
	public List<SccCdrCobilling> pesquisaArquivoPorStatus(Long seqArquivo,Long cdStatus) throws ServiceException,DAOException;
	
	public List<Object[]> pesquisaMatrizListaCDRsPorStatus(Long seqArquivo,Long cdStatus) throws ServiceException,DAOException;
	
	public List<Object[]> agruparCDRsArquivoPorDataChamada(Long seqArquivo,Long cdStatus) throws ServiceException,DAOException;
	
	public List<SccArquivoSumarizado> geraSumarizadoPeriodo(String cdEOTClaro,String cdEOTLD,Date dataInicial,Date dataFinal,boolean holding) throws DAOException;
	
	public List<SccArquivoSumarizado> geraSumarizadoPeriodoCiclo(String cdEOTClaro,String cdEOTLD,Date dataInicial,Date dataFinal,boolean holding) throws DAOException;
	
	public Long calculaTotalRejeitados(List<SccArquivoSumarizado> rows) throws ServiceException;
	
	public Long calculaTotalEncaminhado(List<SccArquivoSumarizado> rows) throws ServiceException;
	
	public Long calculaTotalAlocado(List<SccArquivoSumarizado> rows) throws ServiceException;
	
	public Long calculaTotalFaturado(List<SccArquivoSumarizado> rows) throws ServiceException;
	
	public Long calculaTotal(List<SccArquivoSumarizado> rows) throws ServiceException;
	
	public Map<String, List<SccArquivoSumarizado>> agrupaPorCiclos(List<SccArquivoSumarizado> rows) throws ServiceException;
	
	/**
	 * O sumário de backlog é baseado em alguns status de CDRs. Todos os CDRs da lista que estão em algum desses
	 * status devem ser somados ao sumário de backlog.
	 * Segundo código atual em BillingRepotDAO (linha 210):
	 * 
	 *  case CobillingConstants.CDRSTATUS_ENCAMINHADO_ESB:
	                    case CobillingConstants.CDRSTATUS_ENCAMINHADO_MOB:
	                    case CobillingConstants.CDRSTATUS_ENCAMINHADO:
	                    case CobillingConstants.CDRSTATUS_ALOCADO_ESB:
	                    case CobillingConstants.CDRSTATUS_ALOCADO_MOB:
	                    case CobillingConstants.CDRSTATUS_ALOCADO:
	                    case CobillingConstants.CDRSTATUS_A_RECICLAR_ESB:
	                    case CobillingConstants.CDRSTATUS_A_RECICLAR_MOB:
	                    case CobillingConstants.CDRSTATUS_A_RECICLAR:
	                    	itemBackLog.setStatus(CobillingConstants.CDRSTATUS_BACKLOG);
	                    	reportBackLog.addBillingItem(itemBackLog);
	 * 
	 * @param rows
	 * @return
	 * @throws ServiceException
	 */
	public SccArquivoSumarizadoDecorator geraBacklog(List<SccArquivoSumarizado> rows) throws ServiceException;
	
	public List<SccArquivoSumarizado> agrupaAlocadosPorCiclo(List<SccArquivoSumarizado> rows)  throws ServiceException;
	
	public List<SccArquivoSumarizado> agrupaFaturadosPorCiclo(List<SccArquivoSumarizado> rows) throws ServiceException;
}
