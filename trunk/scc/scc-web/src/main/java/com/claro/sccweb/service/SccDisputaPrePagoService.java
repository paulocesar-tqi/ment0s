/**
 * 
 */
package com.claro.sccweb.service;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccDisputaPrePago;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputaPrePago;



/**
 * @author vagner.souza
 *
 */
public interface SccDisputaPrePagoService {
	
	Collection<SccDisputaPrePago> pesquisarDisputaPrePago(SccFiltroDisputaPrePago filtro)throws ServiceException, DAOException;
	
	SccDisputaPrePago pesquisarBySqDisputaPrePago(Long sqDisputaPrePago) throws ServiceException, DAOException;
	
	SccDisputaPrePago findBySqDisputaPrePago(Long sqDisputaPrePago) throws ServiceException, DAOException;
	
	void saveOrUpdate(SccDisputaPrePago entity) throws ServiceException, DAOException;
	
	void create(SccDisputaPrePago entity) throws ServiceException, DAOException;

}
