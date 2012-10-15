/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFinanceiroFiltro;
import com.claro.cobillingweb.persistence.view.SccFinanceiroView;

/**
 * @author 93046251
 *
 */
public interface SccFinanceiroDAO extends BasicDAO<SccFinanceiroView> {
	
	Collection<SccFinanceiroView> findByFiltro(SccFinanceiroFiltro filtro) throws DAOException;
	
	

}
