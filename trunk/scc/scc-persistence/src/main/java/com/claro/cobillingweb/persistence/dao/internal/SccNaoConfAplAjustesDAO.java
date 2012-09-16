/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.AjustesView;

/**
 * @author 93046251
 *
 */
public interface SccNaoConfAplAjustesDAO extends BasicDAO<AjustesView> {
	
	
	List<AjustesView> gerarRelatorioNaoConfAplAjuste(SccFiltro filtro) throws DAOException;

}
