/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;



import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicadorPK;
import com.claro.cobillingweb.persistence.entity.SccIndicador;
import com.claro.cobillingweb.persistence.filtro.SccFiltroIndicador;

/**
 * @author vagner.souza
 *
 */
public interface SccIndicadorDAO extends BasicDAO<SccAgingIndicador>{
	
	Collection<SccAgingIndicador> pesquisaByFiltro(SccFiltroIndicador filtro) throws DAOException;
	
	SccAgingIndicador getSccAgingIndicadorByPk(SccAgingIndicadorPK id) throws DAOException;
	
	Collection<SccIndicador> gerarCombo(SccFiltroIndicador filtro) throws DAOException;
	
	SccAgingIndicador getSccAgingIndicador(String idIndicador, Long sqAgingIndicador) throws DAOException;
	
	void excluirAgingIndicador(String value) throws DAOException;
 
	void excluirByParam(String idIndicador, Long sqAgingIndicador) throws DAOException;

	Collection<SccIndicador> montarComboBox(String dsPeriodicidade, String tipo)throws DAOException;

	SccIndicador findById(String cdIndicador) throws DAOException;
	
}
