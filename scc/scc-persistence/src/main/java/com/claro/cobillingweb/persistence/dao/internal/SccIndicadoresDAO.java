/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccIndicador;
import com.claro.cobillingweb.persistence.filtro.SccFiltroIndicador;

/**
 * @author vagner.souza
 *
 */
public interface SccIndicadoresDAO extends BasicDAO<SccIndicador> {
	
	Collection<SccIndicador> pesquisaByFiltro(SccFiltroIndicador filtro) throws DAOException;
	
	SccIndicador getSccIndicador(String value) throws DAOException;
	
	void excluirByPk(String value) throws DAOException;

}
