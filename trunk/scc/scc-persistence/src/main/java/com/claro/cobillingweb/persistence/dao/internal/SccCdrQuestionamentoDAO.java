/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccCdrQuestionamento;

/**
 * @author vagner.souza
 *
 */
public interface SccCdrQuestionamentoDAO extends BasicDAO<SccCdrQuestionamento>{
	
	void updateAnaliseChamadas(SccCdrQuestionamento entidade) throws DAOException;

	List<SccCdrQuestionamento> listAll() throws DAOException;

}
