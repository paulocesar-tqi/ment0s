package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccOperadora;


/**
 * Serviço para pesquisa de operadoras.
 *
 */
public interface OperadoraService {

	/**
	 * Lista as operadoras pertencentes a uma holding.
	 * @param cdEOT EOT da holding.
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public List<SccOperadora> pesquisaOperarodasHolding(String cdEOT) throws DAOException,ServiceException;
	
	/**
	 * Carrega uma operadora pelo seu EOT.
	 * @param cdEOT
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public SccOperadora pesquisaOperadoraByPk(String cdEOT) throws DAOException,ServiceException;
	
	public void create(SccOperadora entity) throws DAOException;
	
	public void update(SccOperadora entity) throws DAOException;
	
	public void delete(SccOperadora entity) throws DAOException;
	
	public List<SccOperadora> getAllCSP() throws DAOException ;
	
}
