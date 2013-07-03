/**
 * 
 */
package com.claro.sccweb.service;

import java.util.Collection;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccDadosBancario;
import com.claro.cobillingweb.persistence.service.ServiceException;

/**
 * @author vagner.souza
 *
 */
public interface CadastroDadosBancariosService {
	
	SccDadosBancario getSccDadosBancarioByPk(String cdEotLd, String nuBanco, String nuAgencia) throws  ServiceException, DAOException;

	void update(SccDadosBancario entidade) throws DAOException, ServiceException;
	
	void delete(SccDadosBancario entidade) throws DAOException, ServiceException;
	
	void saveOrUpdate(SccDadosBancario entidade) throws DAOException, ServiceException;
	
	Collection<SccDadosBancario> gerarLista() throws ServiceException, DAOException;
	
	SccDadosBancario getSccDadosBancario(String cdEotLd, String nuBanco, String nuAgencia) throws ServiceException, DAOException;
	
	void excluirDadosBancario(String cdEotLd, String nuBanco, String nuAgencia) throws ServiceException, DAOException;
	
	
	
	List<SccDadosBancario> findAll() throws DAOException;

	SccDadosBancario findById(String cdEotLd, String nuBanco, String nuAgencia) throws ServiceException,
			DAOException;
		
}
