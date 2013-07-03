package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Collection;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;
import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoResultadoView;

/**
 * @author 92038883
 *
 */

public interface SccRelatorioQuestionamentoResultadoDAO {
	
	List<SccRelatorioQuestionamentoResultadoView> gerarRelatorioQuestionamentoResultado(String sqQuestionamento, String cdEOTLD, String tpStatus) throws DAOException;
	
	Collection<SccQuestionamentoView> gerarCombo()throws DAOException;

}
