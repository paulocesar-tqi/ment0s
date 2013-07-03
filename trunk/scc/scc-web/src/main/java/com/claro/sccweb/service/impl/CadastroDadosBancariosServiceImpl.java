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
import com.claro.cobillingweb.persistence.dao.internal.CadastroDadosBancariosDAO;
import com.claro.cobillingweb.persistence.entity.SccDadosBancario;
import com.claro.cobillingweb.persistence.service.ServiceException;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.CadastroDadosBancariosService;


/**
 * @author vagner.souza
 *
 */
@Service
public class CadastroDadosBancariosServiceImpl extends AbstractService implements CadastroDadosBancariosService {
	
	@Autowired
	private CadastroDadosBancariosDAO cadastroDadosBancariosDAO;

	@Override
	public SccDadosBancario getSccDadosBancarioByPk(String cdEotLd, String nuBanco, String nuAgencia)
			throws ServiceException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SccDadosBancario entidade) throws DAOException, ServiceException {
		
		this.cadastroDadosBancariosDAO.update(entidade);
		
	}

	@Override
	public void delete(SccDadosBancario entidade) throws DAOException, ServiceException {
		
		this.cadastroDadosBancariosDAO.delete(entidade);
		
	}

	@Override
	public void saveOrUpdate(SccDadosBancario entidade) throws DAOException, ServiceException {
		
		this.cadastroDadosBancariosDAO.saveOrUpdate(entidade);
		
	}

	@Override
	public Collection<SccDadosBancario> gerarLista()
			throws ServiceException, DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SccDadosBancario getSccDadosBancario(String cdEotLd, String nuBanco, String nuAgencia)
			throws ServiceException, DAOException {
		
		return this.cadastroDadosBancariosDAO.findById(cdEotLd, nuBanco, nuAgencia);
	}

	@Override
	@Transactional
	public void excluirDadosBancario(String cdEotLd, String nuBanco, String nuAgencia) throws ServiceException,
			DAOException {
		this.cadastroDadosBancariosDAO.deleteBancario(cdEotLd, nuBanco, nuAgencia);
		
	}
	
	@Override
	public SccDadosBancario findById(String cdEotLd, String nuBanco, String nuAgencia)throws ServiceException, DAOException{
		
		return this.cadastroDadosBancariosDAO.findById(cdEotLd, nuBanco, nuAgencia);
		
		
	}


	@Override
	public List<SccDadosBancario> findAll() throws DAOException {
		
		return (List<SccDadosBancario>) this.cadastroDadosBancariosDAO.listAll();
	}
	

	public void setCadastroDadosBancariosDAO(
			CadastroDadosBancariosDAO cadastroDadosBancariosDAO) {
		this.cadastroDadosBancariosDAO = cadastroDadosBancariosDAO;
	}
	
	
	

}
