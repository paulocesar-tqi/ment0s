/**
 * 
 */
package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.SccAcoesCobrancaView;

/**
 * @author 93046251
 *
 */
public interface SccAcoesCobrancaService {
	
	List<SccAcoesCobrancaView> gerarRelatorioControleAcoesCobranca(SccFiltro filtro) throws ServiceException, DAOException;

}
