/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccDisputaPrePago;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputaPrePago;
import com.claro.cobillingweb.persistence.view.SccDisputaPrePagoView;

/**
 * @author vagner.souza
 *
 */
public interface SccDisputaPrePagoDAO extends BasicDAO<SccDisputaPrePago> {
	
	Collection<SccDisputaPrePagoView> pesquisarDisputaPrePagoByFiltro(SccFiltroDisputaPrePago filtro) throws DAOException;
	
	Collection<SccDisputaPrePago> pesquisarDisputaPrePago(SccFiltroDisputaPrePago filtro ) throws DAOException;
	
	SccDisputaPrePago pesquisarBySqDisputaPrePago(Long sqDisputaPrePago) throws DAOException;
	
	SccDisputaPrePago findBySqDisputaPrePago(Long sqDisputaPrePago) throws DAOException;

}
