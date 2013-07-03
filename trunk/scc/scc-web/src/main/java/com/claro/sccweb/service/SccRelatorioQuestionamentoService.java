package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoView;

public interface SccRelatorioQuestionamentoService {

	List<SccRelatorioQuestionamentoView> gerarRelatorioQuestionamento(String cdEOTLD, String tpStatus) throws ServiceException, DAOException;
}
