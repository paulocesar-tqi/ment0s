/**
 * 
 */
package com.claro.sccweb.service;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicadorPK;
import com.claro.cobillingweb.persistence.entity.SccIndicador;
import com.claro.cobillingweb.persistence.filtro.SccFiltroIndicador;

/**
 * @author vagner.souza
 *
 */
public interface SccIndicadorService {
	
	Collection<SccAgingIndicador> pesquisaByFiltro(SccFiltroIndicador filtro) throws ServiceException, DAOException;
	
	SccAgingIndicador getSccAgingIndicadorByPk(SccAgingIndicadorPK id) throws  ServiceException, DAOException;

	void update(SccAgingIndicador entity) throws DAOException;
	
	void delete(SccAgingIndicador entity) throws DAOException;
	
	void saveOrUpdate(SccAgingIndicador entity) throws DAOException;
	
	Collection<SccIndicador> gerarCombo(SccFiltroIndicador filtro) throws ServiceException, DAOException;
	
	SccAgingIndicador getSccAgingIndicador(String idIndicador, Long sqAgingIndicador) throws ServiceException, DAOException;
	
	void excluirAgingIndicador(String value) throws ServiceException, DAOException;
	
	Collection<SccIndicador> pesquisaByFiltroIndicador(SccFiltroIndicador filtro) throws ServiceException, DAOException;
	
	void update(SccIndicador entity) throws DAOException;
	
	void delete(SccIndicador entity) throws DAOException;
	
	void saveOrUpdate(SccIndicador entity) throws DAOException;
	
	SccIndicador getSccIndicador(String value) throws ServiceException, DAOException;
	
	void excluirIndicador(String value) throws ServiceException, DAOException;

	SccIndicador getPkIndicador(String pk) throws ServiceException, DAOException;
	
	void excluirByPk(String value) throws ServiceException, DAOException;
	
	void excluirByParam(String idIndicador, Long sqAgingIndicador) throws DAOException;

	Collection<SccIndicador> montarComboBox(String dsPeriodicidade, String tipo)
			throws ServiceException, DAOException;

	SccIndicador findById(String cdIndicador) throws DAOException;
}
