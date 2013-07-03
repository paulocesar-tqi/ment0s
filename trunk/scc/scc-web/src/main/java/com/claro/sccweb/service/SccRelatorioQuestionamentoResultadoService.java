package com.claro.sccweb.service;

import java.util.Collection;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;
import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoResultadoView;

public interface SccRelatorioQuestionamentoResultadoService {

	List<SccRelatorioQuestionamentoResultadoView> gerarRelatorioQuestionamentoResultado(String sqQuestionamento, String cdEOTLD, String tpStatus) throws ServiceException, DAOException;
	Collection<SccQuestionamentoView> gerarCombo() throws ServiceException, DAOException;
}
