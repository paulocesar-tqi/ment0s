package com.claro.sccweb.service;

import java.util.Collection;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccCdrQuestionamento;
import com.claro.cobillingweb.persistence.filtro.SccFiltroQuestionamento;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoArquivoView;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoPenalidadeView;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;

public interface SccQuestionamentoService {
	
	Collection<SccQuestionamentoView> gerarCombo() throws ServiceException, DAOException;
	
	Collection<SccQuestionamentoView> gerarRelatorioQuestionamentoFinanceiro(SccFiltroQuestionamento filtro) throws ServiceException, DAOException;
	
	List<SccQuestionamentoPenalidadeView> gerarRelatorioQuestionamentoPenalidade(SccFiltroQuestionamento filtro) throws ServiceException;
	
	Collection<SccQuestionamentoView> gerarComboQuestionamento() throws ServiceException, DAOException;
	
	Collection<SccQuestionamentoView> gerarComboArquivo() throws ServiceException, DAOException;
	
	Collection<SccQuestionamentoArquivoView> gerarRelatorioQuestionamentoArquivo(SccFiltroQuestionamento filtro) throws ServiceException, DAOException;
	
	Collection<SccCdrQuestionamento> pesquisarAnaliseChamadas(Long sqQuestionamento, Long sqRemessaQuestionamento) throws ServiceException, DAOException;
	
	void updateAnaliseChamadas(Collection<SccCdrQuestionamento> listCdrQuestionamento)throws ServiceException, DAOException;

	List<SccCdrQuestionamento> listAll() throws ServiceException, DAOException;


}
