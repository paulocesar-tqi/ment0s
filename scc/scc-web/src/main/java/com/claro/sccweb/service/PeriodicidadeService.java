package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;

/**
 * Serviços de cálculo de periodicidade e validação.
 *
 */
public interface PeriodicidadeService {

	/**
	 * Calcula a data real dentro de uma periodicidade para o mês e ano informados.
	 * @param cdPeriodicidadeRepasse
	 * @param mesReferencia
	 * @param ano
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public Date calculaDataInicialPeriodo(Long cdPeriodicidadeRepasse,Long mesReferencia,Long ano) throws DAOException,ServiceException;
	
	/**
	 * Calcula a data real dentro de uma periodicidade para o mês e ano informados.
	 * @param cdPeriodicidadeRepasse
	 * @param mesReferencia
	 * @param ano
	 * @return
	 * @throws DAOException
	 * @throws ServiceException
	 */
	public Date calculaDataFinalPeriodo(Long cdPeriodicidadeRepasse,Long mesReferencia,Long ano) throws DAOException,ServiceException;
	
	public SccPeriodicidadeRepasse getByPk(Long cdPeriodicidadeRepasse) throws DAOException;
	
	public List<SccPeriodicidadeRepasse> getAll() throws DAOException;
	
}
