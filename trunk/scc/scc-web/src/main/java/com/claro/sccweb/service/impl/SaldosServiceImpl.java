package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.claro.cobillingweb.persistence.constants.MotivoEventoConstants;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoSumarizadoDAO;
import com.claro.cobillingweb.persistence.view.DemonstrativoSaldosLotesView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SaldosService;
import com.claro.sccweb.service.ServiceException;
import com.claro.sccweb.service.data.saldos.DemonstrativoSaldoEvento;
import com.claro.sccweb.service.data.saldos.DemonstrativoSaldoRejeicao;

public class SaldosServiceImpl extends AbstractService implements SaldosService {

	private SccArquivoSumarizadoDAO arquivoSumarizadoDAO;
	
	public Map<String, DemonstrativoSaldoEvento> geraDemonstrativoSaldo(String cdEOTClaro,String cdEOTLD, Long cdProdutoCobilling, Long cdTipoArquivo,Date dataInicial, Date dataFinal) 
			throws DAOException,ServiceException {
		Map<String, DemonstrativoSaldoEvento> tmpMap = new HashMap<String, DemonstrativoSaldoEvento>();
		Long totalCDRs = 0L;
		Double totalValor = 0.0;
		Double totalMinutos = 0.0;
		try {
			String key = null;
			List<DemonstrativoSaldosLotesView> rows = getArquivoSumarizadoDAO().geraDemonstrativoSaldosLotes(cdEOTClaro, cdEOTLD, cdProdutoCobilling, cdTipoArquivo, dataInicial, dataFinal);
			
			if (rows != null) {
				for (DemonstrativoSaldosLotesView row : rows) {
					
					key = row.getCdMotivoEvento();
					totalCDRs = totalCDRs+row.getQtCdrs();
					totalValor = totalValor+row.getValor();
					totalMinutos = totalMinutos+row.getQtMinutos();
					
					if (!tmpMap.containsKey(key)) {
						DemonstrativoSaldoEvento item = new DemonstrativoSaldoEvento();
						item.setCdMotivoEvento(row.getCdMotivoEvento());
						item.setDsMotivoEvento(row.getDsMotivoEvento());						
						tmpMap.put(key, item);
					}
					
					tmpMap.get(key).addCDRs(row.getQtCdrs());
					tmpMap.get(key).addMinutos(row.getQtMinutos());
					tmpMap.get(key).addValor(row.getValor());
					
					if (key.equals(MotivoEventoConstants.CHAMADA_CONTESTADA_NAO_ARRECADADA) || key.equals(MotivoEventoConstants.REJEITADO_NA_CRITICA_INICIAL) ||  key.equals(MotivoEventoConstants.REJEITADO_NO_FATURAMENTO)) {
						DemonstrativoSaldoRejeicao rejeicao = new DemonstrativoSaldoRejeicao();
						rejeicao.setCdMotivoRejeicao(row.getCdMotivoRejeicao());
						rejeicao.setDsMotivoRejeicao(row.getDsMotivoRejeicao());
						rejeicao.setQtCdrs(row.getQtCdrs());
						rejeicao.setQtMinutos(row.getQtMinutos());
						rejeicao.setValor(row.getValor());
						tmpMap.get(key).addItemRejeicao(rejeicao);
					}
					
				}
			}
			
			Iterator<String> itr = tmpMap.keySet().iterator();
			while (itr.hasNext()) {
				key = itr.next();
				double _cdr = tmpMap.get(key).getQtCdrs();
				tmpMap.get(key).setPercentualCDRs((_cdr/totalCDRs)*100);
				tmpMap.get(key).setPercentualMinutos((tmpMap.get(key).getQtMinutos()/totalMinutos)*100);
				tmpMap.get(key).setPercentualValor((tmpMap.get(key).getValor()/totalValor)*100);
				if ((tmpMap.get(key).getDetalhesRejeicao().size() > 0)) {
					for (int r=0;r<tmpMap.get(key).getDetalhesRejeicao().size();r++) {
						tmpMap.get(key).getDetalhesRejeicao().get(r).setPercentualCDRs((tmpMap.get(key).getDetalhesRejeicao().get(r).getQtCdrs()/_cdr)*100);
						tmpMap.get(key).getDetalhesRejeicao().get(r).setPercentualMinutos((tmpMap.get(key).getDetalhesRejeicao().get(r).getQtMinutos()/totalMinutos)*100);
						tmpMap.get(key).getDetalhesRejeicao().get(r).setPercentualValor((tmpMap.get(key).getDetalhesRejeicao().get(r).getValor()/totalValor)*100);
					}
				}
			}
			
			return tmpMap;
		} catch (DAOException daoEx) {
			throw daoEx;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public SccArquivoSumarizadoDAO getArquivoSumarizadoDAO() {
		return arquivoSumarizadoDAO;
	}

	public void setArquivoSumarizadoDAO(SccArquivoSumarizadoDAO arquivoSumarizadoDAO) {
		this.arquivoSumarizadoDAO = arquivoSumarizadoDAO;
	}

	
	
}
