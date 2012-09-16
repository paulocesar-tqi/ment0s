package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.RelCreditosPrePagoView;
import com.claro.cobillingweb.persistence.view.RelDetalheCreditoPrePagoView;

/**
 * Serviço com métodos para pesquisa e edição de créditos de pré-pago.
 *
 */
public interface CreditosPrePagoService {

	public List<RelCreditosPrePagoView> carregaRelatorioCreditos(String cdEOTLD, String cdEOTClaro, String tipoCredito,String statusCredito, Date dtInicio,Date dtFinal,int tamanhoPagina,int pagina) throws DAOException;
	
	public List<RelDetalheCreditoPrePagoView> carregaDetalhesCredito(Long seqArquivoCredito, Long seqCredito) throws DAOException;
	
	public Integer carregaRelatorioCreditosSize(String cdEOTLD, String cdEOTClaro, String tipoCredito,String statusCredito, Date dtInicio,Date dtFinal) throws DAOException;
}
