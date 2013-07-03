package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoView;

/**
 * @author 92038883
 *
 */

public interface SccRelatorioQuestionamentoDAO {
	
	List<SccRelatorioQuestionamentoView> gerarRelatorioQuestionamento(String cdEOTLD, String tpStatus) throws DAOException;

}
