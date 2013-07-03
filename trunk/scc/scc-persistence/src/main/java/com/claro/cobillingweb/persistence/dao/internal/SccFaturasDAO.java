/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
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
public interface SccFaturasDAO extends BasicDAO<SccFaturaView> {
	
	List<SccFaturaView> gerarRelatorioFaturas(SccFiltroFaturas filtro) throws DAOException;
	
	List<SccFaturaView> gerarRelatorioJurosMultas(SccFiltro filtro)	throws DAOException;
	
	List<SccFaturaView> gerarComboCiclo() throws DAOException;
	
	List<SccAgingFaturasView> gerarRelatorioAgingFaturas(SccFiltroAgingFaturas filtro) throws DAOException;

}
