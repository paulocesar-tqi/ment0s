package com.claro.sccweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccPreFechamentoDAO;
import com.claro.cobillingweb.persistence.entity.SccPreFechamento;
import com.claro.cobillingweb.persistence.service.ServiceException;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccPreFechamentoService;

@Service
public class SccPreFechamentoServiceImpl extends AbstractService implements	SccPreFechamentoService {

	@Autowired
	private SccPreFechamentoDAO preFechamentoDAO;
	
	@Transactional
	@Override
	public void updateEntity(SccPreFechamento fechamento) throws ServiceException, DAOException{
		
		this.preFechamentoDAO.updateEntity(fechamento);
		
	}

	public void setPreFechamentoDAO(SccPreFechamentoDAO preFechamentoDAO) {
		this.preFechamentoDAO = preFechamentoDAO;
	}
	
	
	
	
}
