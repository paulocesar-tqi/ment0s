/**
 * 
 */
package com.claro.sccweb.service;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccDisputa;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputa;

/**
 * @author vagner.souza
 *
 */
public interface SccDisputaService {
	
	Collection<SccDisputa> pesquisarDisputaByFiltro(	SccFiltroDisputa filtro) throws ServiceException, DAOException;
	
	void delete(SccDisputa entity) throws DAOException;
	
	SccDisputa pesquisarBySqDisputa(Long sqDisputa) throws ServiceException,DAOException;
	
	void saveOrUpdate(SccDisputa entity) throws ServiceException, DAOException;
	
	void create(SccDisputa entity) throws ServiceException, DAOException;


}
