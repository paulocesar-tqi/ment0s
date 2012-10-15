package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.BatimentoEstornoDebitoView;

public interface SccNaoConfAplBatimentoEstornoDebitoDAO extends BasicDAO<BatimentoEstornoDebitoView> {
	
	List<BatimentoEstornoDebitoView> gerarRelatorioNaoConfBatimentoEstornoDebito(SccFiltro filtro)throws DAOException;

}
