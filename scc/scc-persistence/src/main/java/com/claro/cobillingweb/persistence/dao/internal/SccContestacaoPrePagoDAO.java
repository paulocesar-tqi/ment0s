/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccContestacaoPrePago;
import com.claro.cobillingweb.persistence.filtro.SccFiltroContestacaoPrePago;

/**
 * @author vagner.souza
 *
 */
public interface SccContestacaoPrePagoDAO extends BasicDAO<SccContestacaoPrePago>{
	
	Collection<SccContestacaoPrePago> pesquisarByFiltro(SccFiltroContestacaoPrePago filtro) throws DAOException;
	
	SccContestacaoPrePago findBySqContestacaoPrePago(Long sqContestacaoPrePago)	throws DAOException;
	
}
