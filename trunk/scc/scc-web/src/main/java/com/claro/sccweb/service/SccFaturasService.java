/**
 * 
 */
package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.SccAgingFaturasView;
import com.claro.cobillingweb.persistence.view.SccFaturaView;

/**
 * @author 93046251
 *
 */
public interface SccFaturasService {
	
	List<SccFaturaView> gerarRelatorioFaturas(SccFiltro filtro)throws ServiceException, DAOException;
	
	List<SccFaturaView> gerarRelatorioJurosMultas(SccFiltro filtro)	throws ServiceException, DAOException;
	
	List<SccAgingFaturasView> gerarRelatorioAgingFaturas(SccFiltro filtro) throws ServiceException, DAOException;

}
