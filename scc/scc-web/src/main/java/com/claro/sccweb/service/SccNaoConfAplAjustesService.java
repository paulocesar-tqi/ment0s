/**
 * 
 */
package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.AjustesView;

/**
 * @author 93046251
 *
 */
public interface SccNaoConfAplAjustesService {
	
	public List<AjustesView> gerarRelatorioNaoConfAplAjuste(SccFiltro filtro)throws ServiceException, DAOException;
	
	

}
