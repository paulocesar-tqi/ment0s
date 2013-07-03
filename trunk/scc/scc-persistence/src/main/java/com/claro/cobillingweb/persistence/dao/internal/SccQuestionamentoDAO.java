/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccCdrQuestionamento;
import com.claro.cobillingweb.persistence.filtro.SccFiltroQuestionamento;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoArquivoView;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoPenalidadeView;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;

/**
 * @author vagner.souza
 *
 */
public interface SccQuestionamentoDAO extends BasicDAO<SccQuestionamentoView>{
	
	Collection<SccQuestionamentoView> gerarCombo()throws DAOException;
	
	Collection<SccQuestionamentoView> gerarRelatorioQuestionamentoFinanceiro(SccFiltroQuestionamento filtro) throws DAOException;
	
	Collection<SccQuestionamentoPenalidadeView> gerarRelatorioQuestionamentoPenalidade(SccFiltroQuestionamento filtro) throws DAOException;
	
	Collection<SccQuestionamentoView> gerarComboQuestionamento() throws DAOException;
	
	Collection<SccQuestionamentoView> gerarComboArquivo() throws DAOException;
	
	Collection<SccQuestionamentoArquivoView> gerarRelatorioQuestionamentoArquivo(SccFiltroQuestionamento filtro) throws DAOException;
	
	Collection<SccCdrQuestionamento> pesquisarAnaliseChamadas(Long sqQuestionamento, Long sqRemessaQuestionamento) throws DAOException;

}
