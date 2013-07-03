/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.CadastroCriticaPrebillingDAO;
import com.claro.cobillingweb.persistence.entity.SccCriticaPrebilling;
import com.claro.cobillingweb.persistence.service.ServiceException;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.CadastroCriticaPrebillingService;


/**
 * @author vagner.souza
 *
 */
@Service
public class CadastroCriticaPrebillingServiceImpl extends AbstractService implements CadastroCriticaPrebillingService {
	
	@Autowired
	private CadastroCriticaPrebillingDAO cadastroCriticaPrebillingDAO;

	@Override
	public SccCriticaPrebilling getSccCriticaPrebillingByPk(String id)
			throws ServiceException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SccCriticaPrebilling entidade) throws DAOException, ServiceException {
		
		this.cadastroCriticaPrebillingDAO.update(entidade);
		
	}

	@Override
	public void delete(SccCriticaPrebilling entidade) throws DAOException, ServiceException {
		
		this.cadastroCriticaPrebillingDAO.delete(entidade);
		
	}

	@Override
	public void saveOrUpdate(SccCriticaPrebilling entidade) throws DAOException, ServiceException {
		
		this.cadastroCriticaPrebillingDAO.saveOrUpdate(entidade);
		
	}

	@Override
	public Collection<SccCriticaPrebilling> gerarLista()
			throws ServiceException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SccCriticaPrebilling getSccCriticaPrebilling(String id)
			throws ServiceException, DAOException {
		
		return this.cadastroCriticaPrebillingDAO.findById(id);
	}

	@Override
	@Transactional
	public void excluirCriticaPrebilling(String id) throws ServiceException,
			DAOException {
		this.cadastroCriticaPrebillingDAO.deleteCritica(id);
		
	}
	
	@Override
	public SccCriticaPrebilling findById(String id)throws ServiceException, DAOException{
		
		return this.cadastroCriticaPrebillingDAO.findById(id);
		
		
	}


	@Override
	public List<SccCriticaPrebilling> findAll() {
		
		return (List<SccCriticaPrebilling>) this.cadastroCriticaPrebillingDAO.findAll();
	}

	public void setCadastroCriticaPrebillingDAO(
			CadastroCriticaPrebillingDAO cadastroCriticaPrebillingDAO) {
		this.cadastroCriticaPrebillingDAO = cadastroCriticaPrebillingDAO;
	}
	
	
	

}
