package com.claro.sccweb.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.constants.StatusCdrEnum;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoSumarizadoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccCdrCoblDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoSumarizado;
import com.claro.cobillingweb.persistence.filtro.SccFiltroRelPerdaFaturamento;
import com.claro.cobillingweb.persistence.view.RelCDRPrePagoView;
import com.claro.cobillingweb.persistence.view.SomatorioRelCDRPrePagoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.ControleRemessaService;
import com.claro.sccweb.vo.PerdaFaturamentoVO;

@Service
public class ControleRemessaServiceImpl extends AbstractService implements ControleRemessaService {
	
	@Autowired
	private SccArquivoSumarizadoDAO arquivoSumarizadoDAO;
	
	private SccCdrCoblDAO cdrCoblDAO;
	
	private PerdaFaturamentoVO oldVO = null;
	
/*	private Map<PerdaFaturamentoVO, Date> mapItem        = new LinkedHashMap<PerdaFaturamentoVO, Date>();
	private Map<Date, PerdaFaturamentoVO> mapItemInicial = new LinkedHashMap<Date, PerdaFaturamentoVO>();
	private Map<Date, PerdaFaturamentoVO> mapItemFinal   = new LinkedHashMap<Date, PerdaFaturamentoVO>();
*/	
	 
	public List<SccArquivoSumarizado> pesquisaSumarioPorCiclo(Long aaCiclo,Long mmCiclo, Long cdCiclo, String cdEOTLD, String cdEOTClaro,Long cdProdutoCobilling,Long cdTipoArquivo,boolean holding) throws DAOException {
		return getArquivoSumarizadoDAO().pesquisaSumarioPorCiclo(aaCiclo, mmCiclo, cdCiclo, cdEOTLD, cdEOTClaro, cdProdutoCobilling,cdTipoArquivo,holding);
	}

	public List<RelCDRPrePagoView> pesquisaCDRsPrePago(String cdEOTClaro,String cdEOTLD, String tipoPeriodo, Date dataInicial,Date dataFinal, boolean holding) throws DAOException {		
		return getCdrCoblDAO().pesquisaCDRsPrePago(cdEOTClaro, cdEOTLD, tipoPeriodo, dataInicial, dataFinal, holding);
	}
	 
	public SomatorioRelCDRPrePagoView geraSomatorioCDRs(Date dataInicial,Date dataFinal,String tipoPeriodo) throws DAOException {
		return getCdrCoblDAO().geraSomatorioCDRs(dataInicial, dataFinal, tipoPeriodo);		
	}
	 
	public List<SccArquivoSumarizado>  geraSumarizadoPeriodo(String cdEOTClaro,String cdEOTLD, Date dataInicial, Date dataFinal, boolean holding) throws DAOException {		
		return getArquivoSumarizadoDAO().geraSumarizadoPeriodo(cdEOTClaro, cdEOTLD, dataInicial, dataFinal, holding);
	}

	@Override
	public List<SccArquivoSumarizado> pesquisaSumarioPorEvento(String cdEOTClaro, String cdEOTLD, Date dataInicial, Date dataFinal, Long cdProdutoCobilling, Long[] statusCdr, boolean holding, boolean tipoPeriodo) throws DAOException {
		return getArquivoSumarizadoDAO().pesquisarPorEvento(cdEOTClaro, cdEOTLD, dataInicial, dataFinal, cdProdutoCobilling, statusCdr, holding, tipoPeriodo);
	}

	@Override
	public Collection<PerdaFaturamentoVO> gerarRelatorioPerdaFaturamento(SccFiltroRelPerdaFaturamento filtro) throws DAOException {
		
//		Map<PerdaFaturamentoVO, PerdaFaturamentoVO> mapa = new LinkedHashMap<PerdaFaturamentoVO, PerdaFaturamentoVO>();
//		Collection<PerdaFaturamentoVO> list = new ArrayList<PerdaFaturamentoVO>();
		
//		this.oldVO = null;
		
//		Collection<SccArquivoSumarizado> colecao = this.arquivoSumarizadoDAO.gerarRelatorioPerdaFaturamento(filtro);
		
/*		for (SccArquivoSumarizado sccArquivoSumarizado : colecao) {
			
			gerarItem(sccArquivoSumarizado, mapa, oldVO);
			
		}
		
*/
//		gerarItem(colecao, list, oldVO);
		//return list;
		//return  new ArrayList<PerdaFaturamentoVO>(mapa.values());
		
		Collection<PerdaFaturamentoVO>  colecao = gerarItem(gerarRelatorioPerdaFat(filtro));
		return colecao;
		
		
	}
	
	
	
	private Collection<SccArquivoSumarizado> gerarRelatorioPerdaFat(SccFiltroRelPerdaFaturamento filtro) throws DAOException {
		
		Collection<SccArquivoSumarizado> colecao = null;
		
		colecao = this.arquivoSumarizadoDAO.gerarRelatorioPerdaFaturamento(filtro);
		
		return colecao;
	}

    private Collection<PerdaFaturamentoVO> gerarItem(Collection<SccArquivoSumarizado> colecao){
    	
    	
    	return metodoTeste(colecao);
    }
	
	
/*	private void  gerarItem(Collection<SccArquivoSumarizado> colecao){
		
		for(SccArquivoSumarizado entity : colecao) {
			int status = getUnificaStatusFinancial(entity.getCdStatusCdr().intValue());
	    	if(!StatusCdrEnum.CDRSTATUS_INDEFINIDO.equals(status)){
	    		if(entity.getQtCdrs() != null && entity.getQtCdrs() > 0){
	    			geralVO = new PerdaFaturamentoVO();
	    			
	    			geralVO =  getPerdaFaturamentoVO(entity, geralVO);
	    			geralVO.setStatus(entity.getCdSubStatusCdr());
	    			geralVO.setFileType(StatusCdrEnum.findById(status).getDescricao());
    				mapa.add(oldVO);


	    		}
	    	}
		}
		
	}
*/	
/*	private void gerarItemAll(SccArquivoSumarizado entity){
		int status = getUnificaStatusFinancial(entity.getCdStatusCdr().intValue());
    	if(!StatusCdrEnum.CDRSTATUS_INDEFINIDO.equals(status)){
    		if(entity.getQtCdrs() != null && entity.getQtCdrs() > 0){
    			
    			PerdaFaturamentoVO geralVO = getPerdaFaturamentoVO(entity);
    			
    			geralVO.setCdStatusCdr(status);
    			geralVO.setStatus(entity.getCdSubStatusCdr());
    			geralVO.setFileType(StatusCdrEnum.findById(status).getDescricao());
    			
    			mapItem.put(geralVO, geralVO.getDtProcExterna());
    		}
    	}
	}
*/	
/*	private void gerarItemInicial(SccArquivoSumarizado entity, int status){
		
    	if(!isStatusInicial(status)){
    		PerdaFaturamentoVO itemInicial = getPerdaFaturamentoVO(entity);
    		itemInicial.setFileType(StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getDescricao());
    		itemInicial.setCdStatusCdr(StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getValor());
    		mapItemInicial.put(itemInicial.getDtProcExterna(),itemInicial );

    	}
	}
*/	
/*	private void gearItemFinal(SccArquivoSumarizado entity, int status, Map<PerdaFaturamentoVO, Date> mapItemInicial){
		
		if(!isStatusFinal(status) || !isStatusInicial(status)){
			PerdaFaturamentoVO itemFinal = getPerdaFaturamentoVO(entity);
			
			if(mapItemInicial.containsKey(itemFinal.getDtProcExterna())){
				PerdaFaturamentoVO item = mapItemInicial.
			}
			itemFinal.setFileType(StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getDescricao());
			itemFinal.setCdStatusCdr(StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getValor());
			mapItemFinal.put(itemFinal, itemFinal.getDtProcExterna());
		}
		
	}
*/	
    		
	private PerdaFaturamentoVO getPerdaFaturamentoVO(SccArquivoSumarizado entity){
    	PerdaFaturamentoVO item = new PerdaFaturamentoVO();
    	item.setDtProcExterna(entity.getDtProcExterna());
    	item.setOperadoraClaro(entity.getCdEotClaro());
    	item.setOperadoraLd(entity.getCdEotLd());
    	item.setStatus(entity.getCdSubStatusCdr());
    	item.setQtdCdr(entity.getQtCdrs());
    	item.setValorBruto(entity.getVlBrutoChamada());
    	item.setValorLiquido(entity.getVlLiquidoChamada());

		return item;

	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection<PerdaFaturamentoVO> metodoTeste(Collection<SccArquivoSumarizado> colecao){
		Collection<PerdaFaturamentoVO> list = new ArrayList<PerdaFaturamentoVO>();
		//ArrayList list = new ArrayList();
		HashMap map = new LinkedHashMap();
		for(SccArquivoSumarizado entity : colecao){
			int statusCdr = getUnificaStatusFinancial2(entity.getCdStatusCdr().intValue());
			String subStatusCdr = entity.getCdSubStatusCdr();
			if (statusCdr!=CobillingConstants.CDRSTATUS_INDEFINIDO) {
				if(entity.getQtCdrs() != null && entity.getQtCdrs() > 0){
					PerdaFaturamentoVO itemQualquer = new PerdaFaturamentoVO();
					itemQualquer.setFileType("" + statusCdr);
					itemQualquer.setOperadoraLd(entity.getCdEotLd());
					itemQualquer.setOperadoraClaro(entity.getCdEotClaro());
					itemQualquer.setDtProcExterna(entity.getDtProcExterna());
					itemQualquer.setCdStatusCdr(statusCdr);
					itemQualquer.setStatus(StatusCdrEnum.findById(statusCdr).getDescricao());
					PerdaFaturamentoVO item = (PerdaFaturamentoVO) map.get(itemQualquer);
					if(item == null){
						item = itemQualquer;
						map.put(itemQualquer, itemQualquer);
					}
					item.addQtdCdr(entity.getQtCdrs());
					item.addValorBruto(entity.getVlBrutoChamada());
					item.addValorlLiquido(entity.getVlLiquidoChamada());
					
					 // Faturavel Inicial = valor total das chamadas aceitas menos rejeições C1 e C2
					
                    switch(statusCdr) {
                    	case CobillingConstants.CDRSTATUS_REJEITADO:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C1:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2_ESB:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2_MOB:
                    		break;
                    	default:

							PerdaFaturamentoVO inicialFAQualquer = new PerdaFaturamentoVO();
	                    	inicialFAQualquer.setFileType("" + StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getDescricao());
	                    	inicialFAQualquer.setOperadoraLd(entity.getCdEotLd());
	                    	inicialFAQualquer.setOperadoraClaro(entity.getCdEotClaro());
	                    	inicialFAQualquer.setDtProcExterna(entity.getDtProcExterna());
	                    	inicialFAQualquer.setCdStatusCdr(StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getValor());
	                    	inicialFAQualquer.setStatus(StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getDescricao());
	                    	
	                    	PerdaFaturamentoVO inicialFA = (PerdaFaturamentoVO) map.get(inicialFAQualquer);
	                        if (inicialFA==null) {
	                        	inicialFA = inicialFAQualquer;
	                            map.put(inicialFAQualquer, inicialFAQualquer);
	                        }
	                        inicialFA.addValorlLiquido(entity.getVlLiquidoChamada());
	                        inicialFA.addValorBruto(entity.getVlBrutoChamada());
	                        inicialFA.addQtdCdr(entity.getQtCdrs());
	                        
	                        Map<Date, PerdaFaturamentoVO> mp = new LinkedHashMap<Date, PerdaFaturamentoVO>();
	                        mp.put(inicialFA.getDtProcExterna(), inicialFA);
	                        
	                        if(mp.containsKey(inicialFA.getDtProcExterna())){
	                        	
	                        }
	                        
	                         PerdaFaturamentoVO teste = (PerdaFaturamentoVO) mp.get(inicialFA);

	                   break;    
					}
					
					
                    switch(statusCdr) {
                    	case CobillingConstants.CDRSTATUS_REJEITADO:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C1:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2_ESB:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2_MOB:
                    	case CobillingConstants.CDRSTATUS_EXCLUIDO:
                    	case CobillingConstants.CDRSTATUS_EXCLUIDO_X1:
                    	case CobillingConstants.CDRSTATUS_EXCLUIDO_X2:
                    	case CobillingConstants.CDRSTATUS_EXCLUIDO_X2_ESB:
                    	case CobillingConstants.CDRSTATUS_EXCLUIDO_X2_MOB:
                    		break;
                    	default:

							PerdaFaturamentoVO finalFAQualquer = new PerdaFaturamentoVO();
	                    	finalFAQualquer.setFileType("" + StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getDescricao());
	                    	finalFAQualquer.setOperadoraLd(entity.getCdEotLd());
	                    	finalFAQualquer.setOperadoraClaro(entity.getCdEotClaro());
	                    	finalFAQualquer.setDtProcExterna(entity.getDtProcExterna());
	                    	finalFAQualquer.setCdStatusCdr(StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getValor());
	                    	finalFAQualquer.setStatus(StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getDescricao());
	                    	
	                    	PerdaFaturamentoVO finalFA = (PerdaFaturamentoVO) map.get(finalFAQualquer);
	                        if (finalFA==null) {
	                        	finalFA = finalFAQualquer;
	                            map.put(finalFAQualquer, finalFAQualquer);
	                        }
	                        finalFA.addValorlLiquido(entity.getVlLiquidoChamada());
	                        finalFA.addValorBruto(entity.getVlBrutoChamada());
	                        finalFA.addQtdCdr(entity.getQtCdrs());
						break;
					}
				}
				
			}
		}
        list = new ArrayList(map.values());
        
        //Collections.sort(list);
        
        return list;
	}
	
/*	private Collection<FinanceiroVO> metodoTeste2(Collection<SccArquivoSumarizado> colecao){
		Collection<PerdaFaturamentoVO> list = new ArrayList<PerdaFaturamentoVO>();
		//ArrayList list = new ArrayList();
		HashMap map = new LinkedHashMap();
		for(SccArquivoSumarizado entity : colecao){
			int statusCdr = getUnificaStatusFinancial2(entity.getCdStatusCdr().intValue());
			String subStatusCdr = entity.getCdSubStatusCdr();
			if (statusCdr!=CobillingConstants.CDRSTATUS_INDEFINIDO) {
				if(entity.getQtCdrs() != null && entity.getQtCdrs() > 0){
					PerdaFaturamentoVO itemQualquer = new PerdaFaturamentoVO();
					itemQualquer.setFileType("" + statusCdr);
					itemQualquer.setOperadoraLd(entity.getCdEotLd());
					itemQualquer.setOperadoraClaro(entity.getCdEotClaro());
					itemQualquer.setDtProcExterna(entity.getDtProcExterna());
					itemQualquer.setCdStatusCdr(statusCdr);
					itemQualquer.setStatus(StatusCdrEnum.findById(statusCdr).getDescricao());
					PerdaFaturamentoVO item = (PerdaFaturamentoVO) map.get(itemQualquer);
					if(item == null){
						item = itemQualquer;
						map.put(itemQualquer, itemQualquer);
					}
					item.addQtdCdr(entity.getQtCdrs());
					item.addValorBruto(entity.getVlBrutoChamada());
					item.addValorlLiquido(entity.getVlLiquidoChamada());
					
					 // Faturavel Inicial = valor total das chamadas aceitas menos rejeições C1 e C2
					
                    switch(statusCdr) {
                    	case CobillingConstants.CDRSTATUS_REJEITADO:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C1:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2_ESB:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2_MOB:
                    		break;
                    	default:

							PerdaFaturamentoVO inicialFAQualquer = new PerdaFaturamentoVO();
	                    	inicialFAQualquer.setFileType("" + StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getDescricao());
	                    	inicialFAQualquer.setOperadoraLd(entity.getCdEotLd());
	                    	inicialFAQualquer.setOperadoraClaro(entity.getCdEotClaro());
	                    	inicialFAQualquer.setDtProcExterna(entity.getDtProcExterna());
	                    	inicialFAQualquer.setCdStatusCdr(StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getValor());
	                    	inicialFAQualquer.setStatus(StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getDescricao());
	                    	
	                    	PerdaFaturamentoVO inicialFA = (PerdaFaturamentoVO) map.get(inicialFAQualquer);
	                        if (inicialFA==null) {
	                        	inicialFA = inicialFAQualquer;
	                            map.put(inicialFAQualquer, inicialFAQualquer);
	                        }
	                        inicialFA.addValorlLiquido(entity.getVlLiquidoChamada());
	                        inicialFA.addValorBruto(entity.getVlBrutoChamada());
	                        inicialFA.addQtdCdr(entity.getQtCdrs());
	                   break;    
					}
					
					
                    switch(statusCdr) {
                    	case CobillingConstants.CDRSTATUS_REJEITADO:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C1:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2_ESB:
                    	case CobillingConstants.CDRSTATUS_REJEITADO_C2_MOB:
                    	case CobillingConstants.CDRSTATUS_EXCLUIDO:
                    	case CobillingConstants.CDRSTATUS_EXCLUIDO_X1:
                    	case CobillingConstants.CDRSTATUS_EXCLUIDO_X2:
                    	case CobillingConstants.CDRSTATUS_EXCLUIDO_X2_ESB:
                    	case CobillingConstants.CDRSTATUS_EXCLUIDO_X2_MOB:
                    		break;
                    	default:

							PerdaFaturamentoVO finalFAQualquer = new PerdaFaturamentoVO();
	                    	finalFAQualquer.setFileType("" + StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getDescricao());
	                    	finalFAQualquer.setOperadoraLd(entity.getCdEotLd());
	                    	finalFAQualquer.setOperadoraClaro(entity.getCdEotClaro());
	                    	finalFAQualquer.setDtProcExterna(entity.getDtProcExterna());
	                    	finalFAQualquer.setCdStatusCdr(StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getValor());
	                    	finalFAQualquer.setStatus(StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getDescricao());
	                    	
	                    	PerdaFaturamentoVO finalFA = (PerdaFaturamentoVO) map.get(finalFAQualquer);
	                        if (finalFA==null) {
	                        	finalFA = finalFAQualquer;
	                            map.put(finalFAQualquer, finalFAQualquer);
	                        }
	                        finalFA.addValorlLiquido(entity.getVlLiquidoChamada());
	                        finalFA.addValorBruto(entity.getVlBrutoChamada());
	                        finalFA.addQtdCdr(entity.getQtCdrs());
						break;
					}
				}
				
			}
		}
        list = new ArrayList(map.values());
        
        //Collections.sort(list);
        
        return list;
	}
*/    
	private int getUnificaStatusFinancial(int status) {
    	
    	StatusCdrEnum statusCdr = StatusCdrEnum.findById(status);
    	Integer retorno = 0;
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_REJEITADO_C1)){
    		retorno = StatusCdrEnum.CDRSTATUS_REJEITADO_C1.getValor();
    	}
    	
    	if((statusCdr.equals(StatusCdrEnum.CDRSTATUS_REJEITADO_C2_ESB)) || (statusCdr.equals(StatusCdrEnum.CDRSTATUS_REJEITADO_C2_MOB))){
    		retorno = StatusCdrEnum.CDRSTATUS_REJEITADO_C2.getValor();
    	}
    	
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_EXCLUIDO_X1)){
    		retorno = StatusCdrEnum.CDRSTATUS_EXCLUIDO_X1.getValor();
    	}
    	
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_EXCLUIDO_X2_ESB) || statusCdr.equals(StatusCdrEnum.CDRSTATUS_EXCLUIDO_X2_ESB)){
    		retorno = StatusCdrEnum.CDRSTATUS_EXCLUIDO_X2.getValor();
    	}
    	
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_PERDIDO_ESB) || statusCdr.equals(StatusCdrEnum.CDRSTATUS_PERDIDO_MOB) ||
    			statusCdr.equals(StatusCdrEnum.CDRSTATUS_PERDIDO_PPC)){
    		retorno = StatusCdrEnum.CDRSTATUS_PERDIDO.getValor();
    	}
    	
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_FATURADO_ESB) || statusCdr.equals(StatusCdrEnum.CDRSTATUS_FATURADO_MOB)){
    		retorno = StatusCdrEnum.CDRSTATUS_FATURADO.getValor();
    	}

    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_CONTESTADO_ESB) || statusCdr.equals(StatusCdrEnum.CDRSTATUS_CONTESTADO_MOB)){
    		retorno = StatusCdrEnum.CDRSTATUS_CONTESTADO.getValor();
    	}

    	return retorno;
 
    }
    
    //private void gerarItem(Collection<SccArquivoSumarizado> colecao, Map<PerdaFaturamentoVO, PerdaFaturamentoVO> mapa, PerdaFaturamentoVO oldVO){
    private void gerarItem(Collection<SccArquivoSumarizado> colecao, Collection<PerdaFaturamentoVO> mapa, PerdaFaturamentoVO oldVO){    	
    	PerdaFaturamentoVO geralVO = null;
    	
		for (SccArquivoSumarizado entity : colecao) {
			
//			gerarItem(sccArquivoSumarizado, mapa, oldVO);

			int status = getUnificaStatusFinancial(entity.getCdStatusCdr().intValue());
    	
    	
	    	if(!StatusCdrEnum.CDRSTATUS_INDEFINIDO.equals(status)){
	    		if(entity.getQtCdrs() != null && entity.getQtCdrs() > 0){
	    			
	    			gerarItemInicial(entity, status, mapa);
	    			geralVO = new PerdaFaturamentoVO();
	    			
	    			geralVO =  getPerdaFaturamentoVO(entity, geralVO);
	    			geralVO.setStatus(entity.getCdSubStatusCdr());
	    			geralVO.setFileType(StatusCdrEnum.findById(status).getDescricao());
    				mapa.add(oldVO);

	    			
	    			
/*	    			PerdaFaturamentoVO itemGeral = (PerdaFaturamentoVO) mapa.get(geralVO);
	    			if(itemGeral == null){
	    				itemGeral = geralVO;
	    				mapa.put(geralVO, geralVO);
	    			}
*/	
	    			
	
	    			
	    		}
	    	}
		}
    	
    }
    
    
    //private void gerarItemInicial(SccArquivoSumarizado entity, int status, Map<PerdaFaturamentoVO, PerdaFaturamentoVO> mapa){
    private void gerarItemInicial(SccArquivoSumarizado entity, int status, Collection<PerdaFaturamentoVO> mapa){	

    	if(!isStatusInicial(status)){
    		if(entity != null){
    			if(oldVO == null){
    				oldVO = getPerdaFaturamentoVO(entity, oldVO);
    				
    			}else if(oldVO.getDtProcExterna().equals(entity.getDtProcExterna())){
    				if(!isStatusFinal(status) || !isStatusInicial(status)){
	    				Long qtdCdr = oldVO.getQtdCdr();
	    				Double vlrBruto = oldVO.getValorBruto();
	    				Double vlrLiquido = oldVO.getValorLiquido();
	    		    	oldVO.setFileType(StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getDescricao());
	    		    	oldVO.setCdStatusCdr(StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getValor());
	    				oldVO.setQtdCdr(qtdCdr + entity.getQtCdrs());
	    				oldVO.setValorBruto(vlrBruto + entity.getVlBrutoChamada());
	    				oldVO.setValorLiquido(vlrLiquido + entity.getVlLiquidoChamada());
    				}
	    		}else{
	    			adicionarMapa(mapa, entity);
	    				
	    		}
    		}
    	}
    		
    	
    	
    }
    
    
    //private void adicionarMapa(Map<PerdaFaturamentoVO, PerdaFaturamentoVO> mapa, SccArquivoSumarizado entity){
    private void adicionarMapa(Collection<PerdaFaturamentoVO> mapa, SccArquivoSumarizado entity){
		PerdaFaturamentoVO itemInicial = oldVO;
		PerdaFaturamentoVO itemFinal = oldVO;

		itemInicial.setFileType(StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getDescricao());
		itemInicial.setCdStatusCdr(StatusCdrEnum.CDRSTATUS_FATURADO_INICIAL.getValor());
		
		itemFinal.setFileType(StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getDescricao());
		itemFinal.setCdStatusCdr(StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getValor());

		//mapa.put(itemInicial, itemInicial);
		//mapa.put(itemFinal, itemFinal);
		mapa.add(itemInicial);
		mapa.add(itemFinal);
		oldVO = getPerdaFaturamentoVO(entity, oldVO);

    }
    private void gerarItemFinal(SccArquivoSumarizado entity, int status, Map<PerdaFaturamentoVO, PerdaFaturamentoVO> mapa, PerdaFaturamentoVO oldVO){
    	
    	if(!isStatusFinal(status) || !isStatusInicial(status)){
    		if(entity != null){
    			if(oldVO == null){
    				oldVO = getPerdaFaturamentoVO(entity, oldVO);
    			}else if(oldVO.getDtProcExterna().equals(entity.getDtProcExterna())){
    				Long qtdCdr = oldVO.getQtdCdr();
    				Double vlrBruto = oldVO.getValorBruto();
    				Double vlrLiquido = oldVO.getValorLiquido();
    				oldVO.setFileType(StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getDescricao());
    				oldVO.setCdStatusCdr(StatusCdrEnum.CDRSTATUS_FATURADO_FINAL.getValor());

    				oldVO.setQtdCdr(qtdCdr + entity.getQtCdrs());
    				oldVO.setValorBruto(vlrBruto + entity.getVlBrutoChamada());
    				oldVO.setValorLiquido(vlrLiquido + entity.getVlLiquidoChamada());
    			}else{
    				mapa.put(oldVO, oldVO);
    				
    			}

    		}
    	}
    	
    }
    
    
    private PerdaFaturamentoVO getPerdaFaturamentoVO(SccArquivoSumarizado entity, PerdaFaturamentoVO oldVO){
    	
    	oldVO = new PerdaFaturamentoVO();
    	oldVO.setDtProcExterna(entity.getDtProcExterna());
    	oldVO.setOperadoraClaro(entity.getCdEotClaro());
    	oldVO.setOperadoraLd(entity.getCdEotLd());
    	oldVO.setStatus(entity.getCdSubStatusCdr());
    	oldVO.setQtdCdr(entity.getQtCdrs());
    	oldVO.setValorBruto(entity.getVlBrutoChamada());
    	oldVO.setValorLiquido(entity.getVlLiquidoChamada());

		return oldVO;
    	
    }
    
    
    
    private Boolean isStatusInicial(int status){
    	
    	Boolean retorno = false;
    	StatusCdrEnum statusCdr = StatusCdrEnum.findById(status);
    	
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_REJEITADO)){
    		retorno = true;
    	}
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_REJEITADO_C1)){
    		retorno = true;
    	}

    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_REJEITADO_C2)){
    		retorno = true;
    	}
    	
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_REJEITADO_C2_ESB)){
    		retorno = true;
    	}
    	
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_REJEITADO_C2_MOB)){
    		retorno = true;
    	}

    	return retorno;

    }
    
    private Boolean isStatusFinal(int status){
    	Boolean retorno = false;
    	StatusCdrEnum statusCdr = StatusCdrEnum.findById(status);
    	
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_EXCLUIDO)){
    		retorno = true;
    	}
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_EXCLUIDO_X1)){
    		retorno = true;
    	}

    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_EXCLUIDO_X2)){
    		retorno = true;
    	}
    	
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_EXCLUIDO_X2_ESB)){
    		retorno = true;
    	}
    	
    	if(statusCdr.equals(StatusCdrEnum.CDRSTATUS_EXCLUIDO_X2_MOB)){
    		retorno = true;
    	}

    	return retorno;

    }
    
	public SccArquivoSumarizadoDAO getArquivoSumarizadoDAO() {
		return arquivoSumarizadoDAO;
	}

	public void setArquivoSumarizadoDAO(SccArquivoSumarizadoDAO arquivoSumarizadoDAO) {
		this.arquivoSumarizadoDAO = arquivoSumarizadoDAO;
	}
	
	public SccCdrCoblDAO getCdrCoblDAO() {
		return cdrCoblDAO;
	}

	public void setCdrCoblDAO(SccCdrCoblDAO cdrCoblDAO) {
		this.cdrCoblDAO = cdrCoblDAO;
	}

	public PerdaFaturamentoVO getOldVO() {
		return oldVO;
	}

	public void setOldVO(PerdaFaturamentoVO oldVO) {
		this.oldVO = oldVO;
	}

    private int getUnificaStatusFinancial2(int status) 
    {
    	switch(status) {
			case (CobillingConstants.CDRSTATUS_REJEITADO_C1):
				return CobillingConstants.CDRSTATUS_REJEITADO_C1;
    		case (CobillingConstants.CDRSTATUS_REJEITADO_C2_ESB):
    		case (CobillingConstants.CDRSTATUS_REJEITADO_C2_MOB):
    			return CobillingConstants.CDRSTATUS_REJEITADO_C2;
    		case (CobillingConstants.CDRSTATUS_EXCLUIDO_X1):
    			return CobillingConstants.CDRSTATUS_EXCLUIDO_X1;
    		case (CobillingConstants.CDRSTATUS_EXCLUIDO_X2_ESB):
    		case (CobillingConstants.CDRSTATUS_EXCLUIDO_X2_MOB):
    			return CobillingConstants.CDRSTATUS_EXCLUIDO_X2;
    		case (CobillingConstants.CDRSTATUS_PERDIDO_ESB):
    		case (CobillingConstants.CDRSTATUS_PERDIDO_MOB):
    		case (CobillingConstants.CDRSTATUS_PERDIDO_PPC):
    			return CobillingConstants.CDRSTATUS_PERDIDO;
    		case (CobillingConstants.CDRSTATUS_FATURADO_ESB):
    		case (CobillingConstants.CDRSTATUS_FATURADO_MOB):
    			return CobillingConstants.CDRSTATUS_FATURADO;
    		case (CobillingConstants.CDRSTATUS_CONTESTADO_ESB):
    		case (CobillingConstants.CDRSTATUS_CONTESTADO_MOB):
    			return CobillingConstants.CDRSTATUS_CONTESTADO;
    		default: 
    			return CobillingConstants.CDRSTATUS_INDEFINIDO;
    	}
    }
	
	
}
