package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltroAcoesCobranca;
import com.claro.cobillingweb.persistence.view.SccAcoesCobrancaView;

public interface SccAcoesCobrancaDAO extends BasicDAO<SccAcoesCobrancaView> {
	
	List<SccAcoesCobrancaView> gerarRelatorioControleAcoesCobranca(SccFiltroAcoesCobranca filtro) throws DAOException;

}
