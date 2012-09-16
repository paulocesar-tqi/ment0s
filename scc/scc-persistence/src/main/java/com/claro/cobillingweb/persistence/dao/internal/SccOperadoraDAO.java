package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;

public interface SccOperadoraDAO extends BasicDAO<SccOperadora> {

	
	/*
	 * SELECT * FROM SCC_OPERADORA WHERE CD_TIPO_SERVICO = 'C' ORDER BY DS_OPERADORA;
	 */
	public List<SccOperadora> pesquisaOperadorasExternas() throws DAOException;
	
	/*
	 * SELECT * FROM SCC_OPERADORA WHERE CD_TIPO_SERVICO = 'M' AND CD_EOT_HOLDING = CD_EOT
	 */
	public List<SccOperadora> pesquisaHoldingClaro() throws DAOException;
	
	/*
	 * SELECT * FROM SCC_OPERADORA WHERE CD_TIPO_SERVICO = 'M'
	 */
	public List<SccOperadora> pequisaOperadorasClaro() throws DAOException;
	
	public List<SccOperadora> pequisaOperadorasClaroComM() throws DAOException;
	
	/**
	 * Pesquisa a lista de operadoras que fazem parte da holding.
	 * @param cdEOT
	 * @return
	 * @throws Exception
	 */
	public List<SccOperadora> pesquisaOperadorasHolding(String cdEOT) throws DAOException;
	
	public List<SccOperadora> getAllCSP() throws DAOException;
	
	
}
