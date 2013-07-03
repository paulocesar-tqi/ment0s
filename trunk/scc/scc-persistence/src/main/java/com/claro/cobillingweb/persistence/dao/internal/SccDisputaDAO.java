/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccDisputa;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputa;

/**
 * @author vagner.souza
 *
 */
public interface SccDisputaDAO extends BasicDAO<SccDisputa> {
	
	Collection<SccDisputa> pesquisarDisputaByFiltro(SccFiltroDisputa filtro) throws DAOException;
	Collection<SccDisputa> pesquisarDiputa(SccFiltroDisputa filtro) throws DAOException;
	SccDisputa pesquisarBySqDisputa(Long sqDisputa) throws DAOException;

}
