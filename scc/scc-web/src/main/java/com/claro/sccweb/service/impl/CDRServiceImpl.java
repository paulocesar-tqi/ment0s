package com.claro.sccweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoSumarizadoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccCdrCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.filtro.SccCdrCobillingFiltro;
import com.claro.sccweb.decorator.rownum.entity.SccArquivoSumarizadoDecorator;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.CDRService;
import com.claro.sccweb.service.ServiceException;

public class CDRServiceImpl extends AbstractService implements CDRService {

	SccCdrCobillingDAO cdrCobillingDAO;
	SccArquivoSumarizadoDAO arquivoSumarizadoDAO;
	
	  public static final int CDRSTATUS_REJEITADO_C1   	= 10;
	  public static final int CDRSTATUS_REJEITADO_C2_ESB  = 11;
	  public static final int CDRSTATUS_REJEITADO_C2_MOB  = 12;
	  
	  /*Status de backlog.*/
	  public static final int CDRSTATUS_ENCAMINHADO_ESB   = 5;
	  public static final int CDRSTATUS_ENCAMINHADO_MOB   = 6;
	  public static final int CDRSTATUS_ALOCADO_ESB  		= 15;
	  public static final int CDRSTATUS_ALOCADO_MOB  		= 16;
	  public static final int CDRSTATUS_A_RECICLAR_ESB    = 30;
	  public static final int CDRSTATUS_A_RECICLAR_MOB    = 31;
	  
	  public static final int CDRSTATUS_FATURADO_MOB      = 21;
	  public static final int CDRSTATUS_FATURADO_ESB      = 20;
	  /*Final status de backlog.*/
	
	public List<SccCdrCobilling> pesquisaCDRs(SccCdrCobillingFiltro filtro) throws ServiceException, DAOException {
		return getCdrCobillingDAO().pesquisaCDRs(filtro);
	}

	public SccCdrCobillingDAO getCdrCobillingDAO() {
		return cdrCobillingDAO;
	}

	public void setCdrCobillingDAO(SccCdrCobillingDAO cdrCobillingDAO) {
		this.cdrCobillingDAO = cdrCobillingDAO;
	}

	public List<SccCdrCobilling> pesquisaArquivoPorStatus(Long seqArquivo,Long cdStatus) throws ServiceException, DAOException {
		return getCdrCobillingDAO().pesquisaArquivoPorStatus(seqArquivo, cdStatus) ;
	}

	public List<Object[]> pesquisaMatrizListaCDRsPorStatus(Long seqArquivo,Long cdStatus) throws ServiceException, DAOException {
		return getCdrCobillingDAO().pesquisaMatrizListaCDRsPorStatus(seqArquivo, cdStatus);
	}

	public List<Object[]> agruparCDRsArquivoPorDataChamada(Long seqArquivo,Long cdStatus) throws ServiceException, DAOException {
		return null;
	}

	public List<SccArquivoSumarizado> geraSumarizadoPeriodo(String cdEOTClaro,String cdEOTLD, Date dataInicial, Date dataFinal, boolean holding) throws DAOException {
		return getArquivoSumarizadoDAO().geraSumarizadoPeriodo(cdEOTClaro, cdEOTLD, dataInicial, dataFinal, holding);
	}

	public SccArquivoSumarizadoDAO getArquivoSumarizadoDAO() {
		return arquivoSumarizadoDAO;
	}

	public void setArquivoSumarizadoDAO(SccArquivoSumarizadoDAO arquivoSumarizadoDAO) {
		this.arquivoSumarizadoDAO = arquivoSumarizadoDAO;
	}

	public Long calculaTotalRejeitados(List<SccArquivoSumarizado> rows) throws ServiceException {
		Long total = 0L;
		if (rows != null)
			{
			for (SccArquivoSumarizado sccArquivoSumarizado : rows) {
				switch (sccArquivoSumarizado.getCdStatusCdr().intValue()) {
				case CDRSTATUS_REJEITADO_C1:
				case CDRSTATUS_REJEITADO_C2_ESB:
				case CDRSTATUS_REJEITADO_C2_MOB:
					total = total+sccArquivoSumarizado.getQtCdrs();
					break;
				default:
					break;
				}
			}
			}
		return total;
	}

	public Long calculaTotal(List<SccArquivoSumarizado> rows) throws ServiceException {
		Long total = 0L;
		if (rows != null)
			{
			for (SccArquivoSumarizado sccArquivoSumarizado : rows) {
				total = total+sccArquivoSumarizado.getQtCdrs();
			}
			}
		return total;
	}

	public SccArquivoSumarizadoDecorator geraBacklog(List<SccArquivoSumarizado> rows) throws ServiceException {
		long totalCdrs = 0;
		long totalRejeitados = 0;
		SccArquivoSumarizado sumario = new SccArquivoSumarizado();
		sumario.setQtCdrs(0L);
		sumario.setVlLiquidoChamada(0.0);
		sumario.setVlBrutoChamada(0.0);
		sumario.setQtDuracaoTarifada(0.0);
		sumario.setDsStatusCdr("Backlog");
		sumario.setCdStatusCdr(0L);
		try {
		for (SccArquivoSumarizado sccArquivoSumarizado : rows) {
			totalCdrs = totalCdrs+sccArquivoSumarizado.getQtCdrs();
			switch (sccArquivoSumarizado.getCdStatusCdr().intValue()) {			
			case CDRSTATUS_ENCAMINHADO_ESB:
			case CDRSTATUS_ENCAMINHADO_MOB:
			case CDRSTATUS_ALOCADO_ESB:
			case CDRSTATUS_ALOCADO_MOB:
			case CDRSTATUS_A_RECICLAR_ESB:
			case CDRSTATUS_A_RECICLAR_MOB:
					 sumario.setQtCdrs(sumario.getQtCdrs()+sccArquivoSumarizado.getQtCdrs());
					 sumario.setVlLiquidoChamada(sumario.getVlLiquidoChamada()+sccArquivoSumarizado.getVlLiquidoChamada());
					 sumario.setVlBrutoChamada(sumario.getVlBrutoChamada()+sccArquivoSumarizado.getVlBrutoChamada());
					 sumario.setQtDuracaoTarifada(sumario.getQtDuracaoTarifada()+sccArquivoSumarizado.getQtDuracaoTarifada());					 
				break;			
    		case CDRSTATUS_REJEITADO_C1:    		
    		case CDRSTATUS_REJEITADO_C2_ESB:
    		case CDRSTATUS_REJEITADO_C2_MOB:
                totalRejeitados++;
			default:
				break;
			}
		}
		SccArquivoSumarizadoDecorator decorator = new SccArquivoSumarizadoDecorator(sumario, totalCdrs, totalRejeitados, rows.size());
		return decorator;
		} catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}		
	}

	public List<SccArquivoSumarizado> geraSumarizadoPeriodoCiclo(String cdEOTClaro, String cdEOTLD, Date dataInicial,Date dataFinal, boolean holding) throws DAOException {		
		return getArquivoSumarizadoDAO().geraSumarizadoPeriodoCiclo(cdEOTClaro, cdEOTLD, dataInicial, dataFinal, holding);
	}

	public Long calculaTotalEncaminhado(List<SccArquivoSumarizado> rows) throws ServiceException {		
		Long total = 0L;
		if (rows != null)
			{
			for (SccArquivoSumarizado sccArquivoSumarizado : rows) {
				switch (sccArquivoSumarizado.getCdStatusCdr().intValue()) {				
				case CDRSTATUS_ENCAMINHADO_ESB:
				case CDRSTATUS_ENCAMINHADO_MOB:
					total = total+sccArquivoSumarizado.getQtCdrs();
					break;
				default:
					break;
				}
			}
			}
		return total;
	}

	public Long calculaTotalAlocado(List<SccArquivoSumarizado> rows) throws ServiceException {
		Long total = 0L;
		if (rows != null)
			{
			for (SccArquivoSumarizado sccArquivoSumarizado : rows) {
				switch (sccArquivoSumarizado.getCdStatusCdr().intValue()) {				
				case CDRSTATUS_ALOCADO_ESB:
				case CDRSTATUS_ALOCADO_MOB:
					total = total+sccArquivoSumarizado.getQtCdrs();
					break;
				default:
					break;
				}
			}
			}
		return total;
	}

	public Long calculaTotalFaturado(List<SccArquivoSumarizado> rows) throws ServiceException {
		Long total = 0L;
		if (rows != null)
			{
			for (SccArquivoSumarizado sccArquivoSumarizado : rows) {
				switch (sccArquivoSumarizado.getCdStatusCdr().intValue()) {				
				case CDRSTATUS_FATURADO_ESB:
				case CDRSTATUS_FATURADO_MOB:
					total = total+sccArquivoSumarizado.getQtCdrs();
					break;
				default:
					break;
				}
			}
			}
		return total;
	}

	public Map<String, List<SccArquivoSumarizado>> agrupaPorCiclos(List<SccArquivoSumarizado> rows) throws ServiceException {
		try {
			String key;
			Map<String, List<SccArquivoSumarizado>> map = new HashMap<String, List<SccArquivoSumarizado>>();
			for (int i=0;i<rows.size();i++)
				{
				SccArquivoSumarizado item = rows.get(i);
				if (item.getAaCiclo() == null)
					key = "0/0";
				else
					key = item.getAaCiclo()+"/"+item.getMmCiclo();
				if (map.containsKey(key))
					{
					map.get(key).add(item);
					}
				else
					{
					map.put(key, new ArrayList<SccArquivoSumarizado>());
					map.get(key).add(item);
					}				
				}
			return map;
		} catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
		
	}

	public List<SccArquivoSumarizado> agrupaAlocadosPorCiclo(List<SccArquivoSumarizado> rows) throws ServiceException {
		try {
			List<SccArquivoSumarizado> resultado = new ArrayList<SccArquivoSumarizado>();
			String key;
			Map<String,SccArquivoSumarizado> map = new HashMap<String, SccArquivoSumarizado>();			
			for (SccArquivoSumarizado sccArquivoSumarizado : rows) {
				if (sccArquivoSumarizado.getAaCiclo() == null)
					key = "0/0";
				else
					key = sccArquivoSumarizado.getAaCiclo()+"/"+sccArquivoSumarizado.getMmCiclo();
				
				switch (sccArquivoSumarizado.getCdStatusCdr().intValue()) {				
				case CDRSTATUS_ALOCADO_ESB:
				case CDRSTATUS_ALOCADO_MOB:
				if (!map.containsKey(key))
					{
					SccArquivoSumarizado sumario = new SccArquivoSumarizado();
					sumario.setQtCdrs(0L);
					sumario.setVlLiquidoChamada(0.0);
					sumario.setVlBrutoChamada(0.0);
					sumario.setQtDuracaoTarifada(0.0);
					sumario.setDsStatusCdr("Alocado");
					sumario.setCdStatusCdr(0L);
					sumario.setAaCiclo(sccArquivoSumarizado.getAaCiclo());
					sumario.setMmCiclo(sccArquivoSumarizado.getMmCiclo());
					map.put(key, sumario);
					}
				map.get(key).setQtCdrs(map.get(key).getQtCdrs()+sccArquivoSumarizado.getQtCdrs());
					break;					
				default:
					break;
				}
			}
			resultado.addAll(map.values());
			return resultado;
		} catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}

	public List<SccArquivoSumarizado> agrupaFaturadosPorCiclo(List<SccArquivoSumarizado> rows) throws ServiceException {
		try {
			List<SccArquivoSumarizado> resultado = new ArrayList<SccArquivoSumarizado>();
			String key;
			Long total = 0L;
			Map<String,SccArquivoSumarizado> map = new HashMap<String, SccArquivoSumarizado>();			
			for (SccArquivoSumarizado sccArquivoSumarizado : rows) {							
				if (sccArquivoSumarizado.getAaCiclo() == null)
					key = "0/0";
				else
					key = sccArquivoSumarizado.getAaCiclo()+"/"+sccArquivoSumarizado.getMmCiclo();				
				switch (sccArquivoSumarizado.getCdStatusCdr().intValue()) {				
				case CDRSTATUS_FATURADO_ESB:
				case CDRSTATUS_FATURADO_MOB:
				if (!map.containsKey(key))
					{
					SccArquivoSumarizado sumario = new SccArquivoSumarizado();
					sumario.setQtCdrs(0L);
					sumario.setVlLiquidoChamada(0.0);
					sumario.setVlBrutoChamada(0.0);
					sumario.setQtDuracaoTarifada(0.0);
					sumario.setDsStatusCdr("Faturado");
					sumario.setCdStatusCdr(0L);
					sumario.setAaCiclo(sccArquivoSumarizado.getAaCiclo());
					sumario.setMmCiclo(sccArquivoSumarizado.getMmCiclo());
					map.put(key, sumario);
					}
				map.get(key).setQtCdrs(map.get(key).getQtCdrs()+sccArquivoSumarizado.getQtCdrs());
					break;					
				default:
					break;
				}
			}
			resultado.addAll(map.values());
			return resultado;
		} catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
	}

	
	
}
