/**
 * 
 */
package com.claro.sccweb.service;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccContestacaoPrePago;
import com.claro.cobillingweb.persistence.filtro.SccFiltroContestacaoPrePago;

/**
 * @author vagner.souza
 *
 */
public interface SccContestacaoPrePagoService {
	
	Collection<SccContestacaoPrePago> pesquisarByFiltro(SccFiltroContestacaoPrePago filtro) throws ServiceException, DAOException;

	SccContestacaoPrePago findBySqContestacaoPrePago(Long sqContestacaoPrePago)	throws ServiceException,DAOException;
	
	void update(SccContestacaoPrePago entity)throws DAOException;
	
	SccContestacaoPrePago salvarEntity(SccContestacaoPrePago entity) throws ServiceException, DAOException;
	
	void delete(SccContestacaoPrePago entity) throws DAOException;
	
	void saveOrUpdate(SccContestacaoPrePago entity) throws DAOException;
}
