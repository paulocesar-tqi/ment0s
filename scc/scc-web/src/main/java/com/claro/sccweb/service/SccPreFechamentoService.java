package com.claro.sccweb.service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPreFechamento;
import com.claro.cobillingweb.persistence.service.ServiceException;

public interface SccPreFechamentoService {

	void updateEntity(SccPreFechamento fechamento) throws ServiceException,
			DAOException;

}
