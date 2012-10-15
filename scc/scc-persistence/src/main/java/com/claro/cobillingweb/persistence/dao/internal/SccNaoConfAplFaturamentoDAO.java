/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.FaturamentoView;

/**
 * @author 93046251
 *
 */
public interface SccNaoConfAplFaturamentoDAO extends BasicDAO<FaturamentoView> {
	
	List<FaturamentoView> gerarRelatorioNaoConfFaturamento(SccFiltro filtro)throws DAOException;

}
