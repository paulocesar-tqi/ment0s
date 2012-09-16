package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccCreditoDAO;
import com.claro.cobillingweb.persistence.view.RelCreditosPrePagoView;
import com.claro.cobillingweb.persistence.view.RelDetalheCreditoPrePagoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.CreditosPrePagoService;

public class CreditosPrePagoServiceImpl extends AbstractService implements CreditosPrePagoService {

	private SccCreditoDAO creditoDAO;
	
	 
	public List<RelCreditosPrePagoView> carregaRelatorioCreditos(String cdEOTLD, String cdEOTClaro, String tipoCredito,String statusCredito, Date dtInicio,Date dtFinal,int tamanhoPagina,int pagina) 
			throws DAOException {
		return getCreditoDAO().carregaRelatorioCreditos(cdEOTLD, cdEOTClaro, tipoCredito, statusCredito, dtInicio,dtFinal,tamanhoPagina,pagina);
	}

	public SccCreditoDAO getCreditoDAO() {
		return creditoDAO;
	}

	public void setCreditoDAO(SccCreditoDAO creditoDAO) {
		this.creditoDAO = creditoDAO;
	}

	 
	public List<RelDetalheCreditoPrePagoView> carregaDetalhesCredito(Long seqArquivoCredito, Long seqCredito) throws DAOException {
		return getCreditoDAO().carregaDetalhesCredito(seqArquivoCredito, seqCredito);
		
	}

	 
	public Integer carregaRelatorioCreditosSize(String cdEOTLD, String cdEOTClaro, String tipoCredito,String statusCredito, Date dtInicio, Date dtFinal) throws DAOException {		
		return getCreditoDAO().carregaRelatorioCreditosSize(cdEOTLD, cdEOTClaro, tipoCredito, statusCredito, dtInicio, dtFinal);
	}

	
	
	
}
