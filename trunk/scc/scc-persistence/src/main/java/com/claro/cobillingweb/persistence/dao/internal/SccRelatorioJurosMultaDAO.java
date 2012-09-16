package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccRelatorioJurosMulta;

public interface SccRelatorioJurosMultaDAO extends BasicDAO<SccRelatorioJurosMulta> {

	/**
	 * Carrega os repasses de juros e multas para um dado produto ,par de operadora e data de repasse.
	 * @param cdEOTClaro
	 * @param cdEOT
	 * @param cdProdutoCobilling
	 * @param dtInicialRepasse
	 * @return
	 * @throws DAOException
	 */
	public List<SccRelatorioJurosMulta> pesquisaDemonstrativoJurosMultas(String cdEOTClaro,String cdEOT,Long cdProdutoCobilling,Date dtInicialRepasse) throws DAOException;
	
	/**
	 * Remove juros e multas associados a um repasse.
	 * @param cdEOTClaro
	 * @param cdEOT
	 * @param cdPeriodicidade
	 * @param cdProdutoCobilling
	 * @param dtInicialRepasse
	 * @return Número de registros apagados.
	 * @throws DAOException
	 */
	public int apagaJurosMultasRepasse(String cdEOTClaro, String cdEOT,Long cdPeriodicidade,Long cdProdutoCobilling, Date dtInicialRepasse) throws DAOException;
	
}
