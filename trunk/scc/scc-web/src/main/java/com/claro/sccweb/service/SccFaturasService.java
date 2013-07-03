/**
 * 
 */
package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.filtro.SccFiltroAgingFaturas;
import com.claro.cobillingweb.persistence.filtro.SccFiltroFaturas;
import com.claro.cobillingweb.persistence.view.SccAgingFaturasView;
import com.claro.cobillingweb.persistence.view.SccFaturaView;

/**
 * @author 93046251
 *
 */
public interface SccFaturasService {
	
	List<SccFaturaView> gerarRelatorioFaturas(SccFiltroFaturas filtro)throws ServiceException, DAOException;
	
	List<SccFaturaView> gerarRelatorioJurosMultas(SccFiltro filtro)	throws ServiceException, DAOException;
	
	List<SccFaturaView> gerarComboCiclo() throws ServiceException, DAOException;
	
	List<SccAgingFaturasView> gerarRelatorioAgingFaturas(SccFiltroAgingFaturas filtro) throws ServiceException, DAOException;

}
