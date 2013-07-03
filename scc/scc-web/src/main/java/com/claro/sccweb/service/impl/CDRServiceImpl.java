package com.claro.sccweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoSumarizadoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccCdrCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.filtro.SccCdrCobillingFiltro;
import com.claro.sccweb.controller.graficos.distribuicao.ItemGraficoDistribuicao;
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
	  private static final long MULTIPLICAR100 = 100;
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
	
	public List<SccArquivoSumarizado> findSumarizadoPeriodo(String cdEOTClaro,String cdEOTLD, Date dataInicial, Date dataFinal, Long produto, boolean holding) throws DAOException, ServiceException {
		
		return (List<SccArquivoSumarizado>) this.arquivoSumarizadoDAO.findSumarizadoPeriodo(cdEOTClaro, cdEOTLD, dataInicial, dataFinal, produto, holding);
		
	}
	
	public List<SccArquivoSumarizado> findSumarizadoByPeriodoAgrupado(String cdEOTClaro,String cdEOTLD, Date dataInicial, Date dataFinal, Long produto, boolean holding) throws DAOException, ServiceException{
		
		return (List<SccArquivoSumarizado>) this.arquivoSumarizadoDAO.findSumarizadoByPeriodoAgrupado(cdEOTClaro, cdEOTLD, dataInicial, dataFinal, produto, holding);
		
		 
	}
	
	
	public List<SccArquivoSumarizado> gerarMetrica(List<SccArquivoSumarizado> lstSumarizado, Long totalRejeitado) throws ServiceException{
		
		List<SccArquivoSumarizado> lstAgrupada = new ArrayList<SccArquivoSumarizado>();
		
		if(lstSumarizado != null && lstSumarizado.size() > 0){
			SccArquivoSumarizado entity = gerarSumarizadoByPeriodo(lstSumarizado);
			lstAgrupada.add(entity);
			for (SccArquivoSumarizado entidade : lstSumarizado) {
				
				if(entidade.getCdStatusCdr() == -1){
					entidade.setMetrica(efetuarCalculo(entidade.getQtCdrs(), 0l, entity.getQtCdrs()));
				}else{
					entidade.setMetrica(efetuarCalculo(entidade.getQtCdrs(), totalRejeitado, entity.getQtCdrs()));
				}
				lstAgrupada.add(entidade);
			}
			
		}
		
		return lstAgrupada;
		
	}
	
	private Double efetuarCalculo(Long qtCdrs, Long totalRejeitado, Long totalGeral){
		
		Double retorno = qtCdrs.doubleValue()/(totalGeral.doubleValue() - totalRejeitado);
		return retorno * MULTIPLICAR100;
			
	}
		@Override
	public SccArquivoSumarizado gerarBackLog(List<SccArquivoSumarizado>lstSumarizado){
		
		Long totalCdr = 0l;
		Double vlrLiquidoChamada = 0.0;
		Double vlrBrutoChamada = 0.0;
		Double qtdDuracaoReal = 0.0;
		Double qtdChamadaTarifada = 0.0;
		for (SccArquivoSumarizado sccArquivoSumarizado : lstSumarizado) {
			if(isBackLog(sccArquivoSumarizado)){
			
				totalCdr = totalCdr + sccArquivoSumarizado.getQtCdrs();
				vlrLiquidoChamada = vlrLiquidoChamada + sccArquivoSumarizado.getVlLiquidoChamada();
				vlrBrutoChamada = vlrBrutoChamada + sccArquivoSumarizado.getVlBrutoChamada();
				qtdDuracaoReal = qtdDuracaoReal + sccArquivoSumarizado.getQtDuracaoReal();
				qtdChamadaTarifada = qtdChamadaTarifada + sccArquivoSumarizado.getQtDuracaoTarifada();
			}
		}
		
		SccArquivoSumarizado entidade = new SccArquivoSumarizado();
		entidade.setCdStatusCdr(-1L);
		entidade.setDsStatusCdr("BackLog");
		entidade.setQtCdrs(totalCdr);
		entidade.setVlLiquidoChamada(vlrLiquidoChamada);
		entidade.setVlBrutoChamada(vlrBrutoChamada);
		entidade.setQtDuracaoReal(qtdDuracaoReal);
		entidade.setQtDuracaoTarifada(qtdChamadaTarifada);
		
		return entidade;
	}
	
	private Boolean isBackLog(SccArquivoSumarizado entidade){
		
		Boolean retorno = false;
		
		switch (entidade.getCdStatusCdr().intValue()) {			
			case CDRSTATUS_ENCAMINHADO_ESB:
			case CDRSTATUS_ENCAMINHADO_MOB:
			case CDRSTATUS_ALOCADO_ESB:
			case CDRSTATUS_ALOCADO_MOB:
			case CDRSTATUS_A_RECICLAR_ESB:
			case CDRSTATUS_A_RECICLAR_MOB:
				retorno = true;
				break;
		}
		
		return retorno;

	}
	
	
	public SccArquivoSumarizado gerarSumarizadoByPeriodo(List<SccArquivoSumarizado> lstSumarizado){
		
		Long totalCdr = 0l;
		Double vlrLiquidoChamada = 0.0;
		Double vlrBrutoChamada = 0.0;
		Double qtdDuracaoReal = 0.0;
		Double qtdChamadaTarifada = 0.0;
		for (SccArquivoSumarizado sccArquivoSumarizado : lstSumarizado) {
			if(sccArquivoSumarizado.getCdStatusCdr() != -1){
				totalCdr = totalCdr + sccArquivoSumarizado.getQtCdrs();
				vlrLiquidoChamada = vlrLiquidoChamada + sccArquivoSumarizado.getVlLiquidoChamada();
				vlrBrutoChamada = vlrBrutoChamada + sccArquivoSumarizado.getVlBrutoChamada();
				qtdDuracaoReal = qtdDuracaoReal + sccArquivoSumarizado.getQtDuracaoReal();
				qtdChamadaTarifada = qtdChamadaTarifada + sccArquivoSumarizado.getQtDuracaoTarifada();
			}
		}
		
		SccArquivoSumarizado entidade = new SccArquivoSumarizado();
		entidade.setCdStatusCdr(0L);
		entidade.setDsStatusCdr("Aceitos");
		entidade.setQtCdrs(totalCdr);
		entidade.setVlLiquidoChamada(vlrLiquidoChamada);
		entidade.setVlBrutoChamada(vlrBrutoChamada);
		entidade.setQtDuracaoReal(qtdDuracaoReal);
		entidade.setQtDuracaoTarifada(qtdChamadaTarifada);
		entidade.setMetrica(100.0);
		return entidade;
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
		if (rows != null) {
			for (SccArquivoSumarizado sccArquivoSumarizado : rows) {
				total = total + sccArquivoSumarizado.getQtCdrs();
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
	
	public List<SccArquivoSumarizado> gerarAlocados(List<SccArquivoSumarizado> lstSumarizado) throws ServiceException{
		
		List<SccArquivoSumarizado> lst = new ArrayList<SccArquivoSumarizado>();
		for (SccArquivoSumarizado entidade : lstSumarizado) {
			if(isAlocado(entidade)){
				lst.add(entidade);
			}
			
		}
		return lst;
	}
	
	
		
		
	
	
	public List<SccArquivoSumarizado> gerarFaturado(List<SccArquivoSumarizado> lstSumarizado) throws ServiceException{

		List<SccArquivoSumarizado> lst = new ArrayList<SccArquivoSumarizado>();
		Long totalGeral = 0l;
		Long totalFaturado = 0l;
		for (int i = 0; i < lstSumarizado.size(); i++) {
			if(lstSumarizado.get(i).getCdStatusCdr() > 0 ){
				totalGeral = totalGeral + lstSumarizado.get(i).getQtCdrs();
			}
			if(isFaturado(lstSumarizado.get(i))){
				totalFaturado = totalFaturado + lstSumarizado.get(i).getQtCdrs();
			}
			
		}
		SccArquivoSumarizado faturado = new SccArquivoSumarizado();
		faturado.setCdStatusCdr(21l);
		faturado.setDsStatusCdr("Faturados");
		faturado.setQtCdrs(totalFaturado);
		faturado.setMetrica((totalFaturado.doubleValue()/totalGeral.doubleValue()) * MULTIPLICAR100);
		lst.add(faturado);
		return lst;

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
	
	public Long calcularTotalAlocados(List<SccArquivoSumarizado>lstSumarizado){
		
		Long totalAlocado = 0L;
		for (SccArquivoSumarizado entidade : lstSumarizado) {
		
			if(entidade.getCdStatusCdr().intValue() == CDRSTATUS_ALOCADO_ESB || entidade.getCdStatusCdr().intValue() == CDRSTATUS_ALOCADO_MOB){
				totalAlocado = totalAlocado + entidade.getQtCdrs();
			}
			
		}
		return totalAlocado;
	}
	
	public Long calcularTotalFaturado(List<SccArquivoSumarizado> lstSumarizado) {
		Long totalFaturado  = 0l;
		
		for (SccArquivoSumarizado entidade : lstSumarizado) {
			if(entidade.getCdStatusCdr().intValue() == CDRSTATUS_FATURADO_ESB || entidade.getCdStatusCdr().intValue() == CDRSTATUS_FATURADO_MOB){
				totalFaturado = totalFaturado + entidade.getQtCdrs();
			}
			
			
		}
		return totalFaturado;
	}
	
	public Long calcularTotalEncaminhado(List<SccArquivoSumarizado> lstSumarizado){
		
		Long totalEncaminhado = 0L;
		
		for (SccArquivoSumarizado entidade : lstSumarizado) {
			if(entidade.getCdStatusCdr().intValue() == CDRSTATUS_ENCAMINHADO_ESB || entidade.getCdStatusCdr().intValue() == CDRSTATUS_ENCAMINHADO_MOB){
				totalEncaminhado = totalEncaminhado + entidade.getQtCdrs();
			}
			
		}
		
		return totalEncaminhado;
	}
	
	public Long calcularTotais(List<SccArquivoSumarizado> lstSumarizado){

		Long total = 0L;
		
		for (SccArquivoSumarizado entidade : lstSumarizado) {
			if(isAlocado(entidade) || isEncaminhado(entidade) || isFaturado(entidade)){
				total = total + entidade.getQtCdrs();
			}
		}
		return total;
	}
	
	private Boolean isAlocado(SccArquivoSumarizado entidade){
		Boolean retorno = false;
		if(entidade.getCdStatusCdr().intValue() == CDRSTATUS_ALOCADO_ESB || entidade.getCdStatusCdr().intValue() == CDRSTATUS_ALOCADO_MOB){
			retorno = true;
		}
		return retorno;
	}
	
	private Boolean isEncaminhado(SccArquivoSumarizado entidade){
		Boolean retorno = false;
		if(entidade.getCdStatusCdr().intValue() == CDRSTATUS_ENCAMINHADO_ESB || entidade.getCdStatusCdr().intValue() == CDRSTATUS_ENCAMINHADO_MOB){
			retorno = true;
		}
		return retorno;
		
	}
	
	private Boolean isFaturado(SccArquivoSumarizado entidade){
		Boolean retorno = false;
		if(entidade.getCdStatusCdr().intValue() == CDRSTATUS_FATURADO_ESB || entidade.getCdStatusCdr().intValue() == CDRSTATUS_FATURADO_MOB){
			retorno = true;
		}
		return retorno;
	}
	
	@Override
	public List<ItemGraficoDistribuicao>  gerarDadosGrafico(List<SccArquivoSumarizado> lstSumarizado, Long total){

		Map<String, List<ItemGraficoDistribuicao>> mapa = new HashMap<String, List<ItemGraficoDistribuicao>>();
		
		String key;
		for (int i = 0; i < lstSumarizado.size(); i++) {
			ItemGraficoDistribuicao item = gerarItem(lstSumarizado.get(i), total) ;
			
			if(item != null){
				key = item.getDescricao();
				if (mapa.containsKey(key))	{
					mapa.get(key).add(item);
				}else{
					mapa.put(key, new ArrayList<ItemGraficoDistribuicao>());
					mapa.get(key).add(item);
				}
			}
		}

		return mostrarDadosGrafico(mapa);
	}
	
	private List<ItemGraficoDistribuicao> mostrarDadosGrafico(Map<String, List<ItemGraficoDistribuicao>> mapa){
		
		List<ItemGraficoDistribuicao> lst = new ArrayList<ItemGraficoDistribuicao>();
		Iterator<String> itr = mapa.keySet().iterator();
		while (itr.hasNext()){
			ItemGraficoDistribuicao itemGrafico = new ItemGraficoDistribuicao();
			List<ItemGraficoDistribuicao> lstItems = mapa.get(itr.next());
			for (ItemGraficoDistribuicao itemGraficoDistribuicao : lstItems) {
				lst.add(itemGraficoDistribuicao);
				
			}
		}
		return lst;
	}
	
	
	
	private ItemGraficoDistribuicao gerarItem(SccArquivoSumarizado entidade, Long total){
		
		ItemGraficoDistribuicao itemGrafico = null;
		if(isAlocado(entidade)){
			itemGrafico =  new ItemGraficoDistribuicao();
			itemGrafico.setAaCiclo(entidade.getAaCiclo());
			itemGrafico.setMmCiclo(entidade.getMmCiclo());
			itemGrafico.setDescricao(entidade.getMmCiclo()+"/"+entidade.getAaCiclo());
			itemGrafico.setCdStatusCdr(500L);
			Double totalAlocado =  ((entidade.getQtCdrs().doubleValue()/total.doubleValue()) * MULTIPLICAR100);
			itemGrafico.setValorAlocado(totalAlocado);
			itemGrafico.setValorEncaminhado(0D);
			itemGrafico.setValorFaturado(0D);
			
		}
		if(isFaturado(entidade)){
			itemGrafico =  new ItemGraficoDistribuicao();
			itemGrafico.setAaCiclo(entidade.getAaCiclo());
			itemGrafico.setMmCiclo(entidade.getMmCiclo());
			itemGrafico.setDescricao(entidade.getMmCiclo()+"/"+entidade.getAaCiclo());
			itemGrafico.setCdStatusCdr(502L);
			Double totalFaturado =  ((entidade.getQtCdrs().doubleValue() /total.doubleValue()) * MULTIPLICAR100);
			itemGrafico.setValorFaturado(totalFaturado);
			itemGrafico.setValorEncaminhado(0D);
			itemGrafico.setValorAlocado(0D);
			
		}
		
		if(isEncaminhado(entidade)){
			itemGrafico =  new ItemGraficoDistribuicao();
			itemGrafico.setAaCiclo(entidade.getAaCiclo());
			itemGrafico.setMmCiclo(entidade.getMmCiclo());
			itemGrafico.setDescricao(entidade.getMmCiclo()+"/"+entidade.getAaCiclo());
			itemGrafico.setCdStatusCdr(501L);
			Double totalEncaminhado =  ((entidade.getQtCdrs().doubleValue()/total.doubleValue()) * MULTIPLICAR100);
			itemGrafico.setValorEncaminhado(totalEncaminhado);
			itemGrafico.setValorAlocado(0D);
			itemGrafico.setValorFaturado(0D);
			
		}
		
		return itemGrafico;

		
	}
	
	public List<ItemGraficoDistribuicao> gerarItensGrafico(List<SccArquivoSumarizado> lstSumarizado, Long total){
		
		List<ItemGraficoDistribuicao> lista = new ArrayList<ItemGraficoDistribuicao>();
		for (SccArquivoSumarizado entidade : lstSumarizado) {
			
			ItemGraficoDistribuicao itemGrafico = null;
			if(isAlocado(entidade)){
				itemGrafico =  new ItemGraficoDistribuicao();
				itemGrafico.setAaCiclo(entidade.getAaCiclo());
				itemGrafico.setMmCiclo(entidade.getMmCiclo());
				itemGrafico.setDescricao(entidade.getMmCiclo()+"/"+entidade.getAaCiclo());
				itemGrafico.setCdStatusCdr(500L);
				Double totalAlocado =  ((entidade.getQtCdrs().doubleValue()/total.doubleValue()) * MULTIPLICAR100);
				itemGrafico.setValorAlocado(totalAlocado);
				itemGrafico.setValorEncaminhado(0D);
				itemGrafico.setValorFaturado(0D);
				
			}
			if(isFaturado(entidade)){
				itemGrafico =  new ItemGraficoDistribuicao();
				itemGrafico.setAaCiclo(entidade.getAaCiclo());
				itemGrafico.setMmCiclo(entidade.getMmCiclo());
				itemGrafico.setDescricao(entidade.getMmCiclo()+"/"+entidade.getAaCiclo());
				itemGrafico.setCdStatusCdr(502L);
				Double totalFaturado =  ((entidade.getQtCdrs().doubleValue() /total.doubleValue()) * MULTIPLICAR100);
				itemGrafico.setValorFaturado(totalFaturado);
				itemGrafico.setValorEncaminhado(0D);
				itemGrafico.setValorAlocado(0D);
				
			}
			
			if(isEncaminhado(entidade)){
				itemGrafico =  new ItemGraficoDistribuicao();
				itemGrafico.setAaCiclo(entidade.getAaCiclo());
				itemGrafico.setMmCiclo(entidade.getMmCiclo());
				itemGrafico.setDescricao(entidade.getMmCiclo()+"/"+entidade.getAaCiclo());
				itemGrafico.setCdStatusCdr(501L);
				Double totalEncaminhado =  ((entidade.getQtCdrs().doubleValue()/total.doubleValue()) * MULTIPLICAR100);
				itemGrafico.setValorEncaminhado(totalEncaminhado);
				itemGrafico.setValorAlocado(0D);
				itemGrafico.setValorFaturado(0D);
				
			}
			
			if(itemGrafico != null ){
				lista.add(itemGrafico);
			}
		}
		
		return lista;
		
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
			@SuppressWarnings("unused")
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
	
	public static void main(final String[] args) {
		
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("3", "Vagner");
		mapa.put("8", "jose");
		mapa.put("1", "Manoel");
		mapa.put("0", "Xuxa");
		System.out.println(mapa);
	}

	
}
