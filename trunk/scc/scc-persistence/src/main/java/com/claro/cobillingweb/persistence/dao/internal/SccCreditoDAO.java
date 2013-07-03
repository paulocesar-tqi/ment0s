package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccCredito;
import com.claro.cobillingweb.persistence.view.RelCreditosPrePagoView;
import com.claro.cobillingweb.persistence.view.RelDetalheCreditoPrePagoView;

public interface SccCreditoDAO extends BasicDAO<SccCredito> {

	/**
	 * Gera o relatório de créditos de pré-pago.
	 * @param cdEOTLD
	 * @param cdEOTClaro
	 * @param tipoCredito
	 * @param statusCredito
	 * @param dtInicio
	 * @return
	 * @throws DAOException
	 */
	public List<RelCreditosPrePagoView> carregaRelatorioCreditos(String cdEOTLD,String cdEOTClaro,String tipoCredito,String statusCredito,Date dtInicio,Date dtFinal)
	throws DAOException;
	
	/**
	 * Carrega detalhes de um crédito (agrupamento de chamadas).
	 * @param seqArquivoCredito Identificado do arquivo de crédito.
	 * @param seqCredito Sequência do crédito.
	 * @return
	 * @throws DAOException
	 */
	public List<RelDetalheCreditoPrePagoView> carregaDetalhesCredito(Long seqArquivoCredito,Long seqCredito) throws DAOException;
	
	public Integer carregaRelatorioCreditosSize(String cdEOTLD, String cdEOTClaro, String tipoCredito,String statusCredito, Date dtInicio,Date dtFinal) throws DAOException;
	
}
