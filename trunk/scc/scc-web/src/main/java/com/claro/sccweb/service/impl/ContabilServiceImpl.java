package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccAtividadeContabilDAO;
import com.claro.cobillingweb.persistence.entity.SccAtividadeContabil;
import com.claro.cobillingweb.persistence.entity.SccContaContabil;
import com.claro.cobillingweb.persistence.entity.SccDominioContabil;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.ContabilService;

public class ContabilServiceImpl extends AbstractService implements ContabilService {

	private SccAtividadeContabilDAO atividadeContabilDAO;
	
	 
	public List<SccDominioContabil> getAllDominioContabil() throws DAOException {
		return getAtividadeContabilDAO().getAllDominioContabil();
	}

	 
	public List<SccContaContabil> getAllContaContabil() throws DAOException {		
		return getAtividadeContabilDAO().getAllContaContabil();
	}

	 
	@Transactional
	public void update(SccAtividadeContabil entity) throws DAOException {
		getAtividadeContabilDAO().update(entity);
		
	}

	 
	@Transactional
	public void create(SccAtividadeContabil entity) throws DAOException {
		getAtividadeContabilDAO().create(entity);
		
	}

	 
	@Transactional
	public void delete(SccAtividadeContabil entity) throws DAOException {
		getAtividadeContabilDAO().delete(entity);
		
	}

	public SccAtividadeContabilDAO getAtividadeContabilDAO() {
		return atividadeContabilDAO;
	}

	public void setAtividadeContabilDAO(SccAtividadeContabilDAO atividadeContabilDAO) {
		this.atividadeContabilDAO = atividadeContabilDAO;
	}

	 	
	public List<SccAtividadeContabil> getAllAtividadeContabil() throws DAOException {
		return getAtividadeContabilDAO().getAll();
	}

	
	
}
