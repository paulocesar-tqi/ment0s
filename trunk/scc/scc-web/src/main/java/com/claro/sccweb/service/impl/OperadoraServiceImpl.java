package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.OperadoraService;
import com.claro.sccweb.service.ServiceException;

public class OperadoraServiceImpl extends AbstractService implements OperadoraService {

	private SccOperadoraDAO operadoraDAO;
	
	 
	public List<SccOperadora> pesquisaOperarodasHolding(String cdEOT)throws DAOException, ServiceException {
		return getOperadoraDAO().pesquisaOperadorasHolding(cdEOT);		
	}
	
	public List<SccOperadora> pesquisaOperadorasHoldingByCdEot(String cdEOT)throws DAOException, ServiceException {
		return getOperadoraDAO().pesquisaHoldingClaroByCdEotHolding(cdEOT);
	}

	public SccOperadoraDAO getOperadoraDAO() {
		return operadoraDAO;
	}

	public void setOperadoraDAO(SccOperadoraDAO operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}

	 
	public SccOperadora pesquisaOperadoraByPk(String cdEOT) throws DAOException, ServiceException {
		return getOperadoraDAO().getByPk(cdEOT, SccOperadora.class);
	}

	 
	@Transactional
	public void create(SccOperadora entity) throws DAOException {
		getOperadoraDAO().create(entity);
		
	}

	 
	@Transactional
	public void update(SccOperadora entity) throws DAOException {
		getOperadoraDAO().update(entity);
		
	}

	 
	@Transactional
	public void delete(SccOperadora entity) throws DAOException {
		getOperadoraDAO().delete(entity);
	}

	 
	public List<SccOperadora> getAllCSP() throws DAOException {
		return getOperadoraDAO().getAllCSP();
	}

	
	
}
