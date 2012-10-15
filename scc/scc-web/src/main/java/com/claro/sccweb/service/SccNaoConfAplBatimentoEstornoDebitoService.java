/**
 * 
 */
package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.BatimentoEstornoDebitoView;

/**
 * @author 93046251
 *
 */
public interface SccNaoConfAplBatimentoEstornoDebitoService {
	
	List<BatimentoEstornoDebitoView> gerarRelatorioBatimentoEstornoDebito(SccFiltro filtro) throws ServiceException, DAOException;

}
