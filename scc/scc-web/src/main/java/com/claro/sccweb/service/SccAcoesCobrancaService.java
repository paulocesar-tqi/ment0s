/**
 * 
 */
package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltroAcoesCobranca;
import com.claro.cobillingweb.persistence.view.SccAcoesCobrancaView;

/**
 * @author 93046251
 *
 */
public interface SccAcoesCobrancaService {
	
	List<SccAcoesCobrancaView> gerarRelatorioControleAcoesCobranca(SccFiltroAcoesCobranca filtro) throws ServiceException, DAOException;

}
