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
	 * Gera o relat�rio de cr�ditos de pr�-pago.
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
	 * Carrega detalhes de um cr�dito (agrupamento de chamadas).
	 * @param seqArquivoCredito Identificado do arquivo de cr�dito.
	 * @param seqCredito Sequ�ncia do cr�dito.
	 * @return
	 * @throws DAOException
	 */
	public List<RelDetalheCreditoPrePagoView> carregaDetalhesCredito(Long seqArquivoCredito,Long seqCredito) throws DAOException;
	
	public Integer carregaRelatorioCreditosSize(String cdEOTLD, String cdEOTClaro, String tipoCredito,String statusCredito, Date dtInicio,Date dtFinal) throws DAOException;
	
}
