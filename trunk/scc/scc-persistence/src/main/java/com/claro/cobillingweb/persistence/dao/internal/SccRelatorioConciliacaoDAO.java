/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccRelatorioConciliacaoView;

public interface SccRelatorioConciliacaoDAO extends BasicDAO<SccRelatorioConciliacaoView> {
	
	List<SccRelatorioConciliacaoView> search(String operadoraClaro, String operadoraExterna, Date dataInicio, Date dataFim) throws DAOException;
	
}
