/**
 * 
 */
package com.claro.sccweb.service;

import java.util.Collection;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccCriticaPrebilling;
import com.claro.cobillingweb.persistence.service.ServiceException;

/**
 * @author vagner.souza
 *
 */
public interface CadastroCriticaPrebillingService {
	
	SccCriticaPrebilling getSccCriticaPrebillingByPk(String id) throws  ServiceException, DAOException;

	void update(SccCriticaPrebilling entidade) throws DAOException, ServiceException;
	
	void delete(SccCriticaPrebilling entidade) throws DAOException, ServiceException;
	
	void saveOrUpdate(SccCriticaPrebilling entidade) throws DAOException, ServiceException;
	
	Collection<SccCriticaPrebilling> gerarLista() throws ServiceException, DAOException;
	
	SccCriticaPrebilling getSccCriticaPrebilling(String id) throws ServiceException, DAOException;
	
	void excluirCriticaPrebilling(String id) throws ServiceException, DAOException;
	
	
	
	List<SccCriticaPrebilling> findAll();

	SccCriticaPrebilling findById(String id) throws ServiceException,
			DAOException;
		
}
