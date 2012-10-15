/**
 * 
 */
package com.claro.sccweb.service;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.SccAcordoParcelamentoView;

/**
 * @author 93046251
 *
 */
public interface SccAcordoParcelamentoService {
	
	Collection<SccAcordoParcelamentoView> findByFilter(SccFiltro filtro) throws ServiceException, DAOException; 
	
	Collection<SccAcordoParcelamentoView> findByFilterRelAcompanhamento(SccFiltro filtro) throws ServiceException, DAOException;

}
