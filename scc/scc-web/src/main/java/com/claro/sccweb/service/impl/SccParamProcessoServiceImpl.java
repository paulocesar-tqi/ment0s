package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccParamProcessoDAO;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccParamProcessoService;
import com.claro.sccweb.service.ServiceException;

public class SccParamProcessoServiceImpl extends AbstractService implements
		SccParamProcessoService {
	
	private SccParamProcessoDAO sccParamProcessoDAO;
	
	

	@Override
	public List<SccParamProcesso> getAll() throws DAOException,
			ServiceException {
		return sccParamProcessoDAO.getAll();
	}
	
	public List<String> listarProcessos()throws DAOException,
	ServiceException{
		
		return sccParamProcessoDAO.listarProcesso();
		
	}

	@Override
	public List<SccParamProcesso> pesquisaProcessoParametros(
			String codProcesso, Date dtInicio, Date dtFim) throws DAOException,
			ServiceException {
		
		if (codProcesso != null && codProcesso.equals("Todos")) {
			return sccParamProcessoDAO.pesquisaProcessoParametrosByData(dtInicio, dtFim);
		}
		return sccParamProcessoDAO.pesquisaProcessoParametros(codProcesso, dtInicio, dtFim);
	}

	public SccParamProcessoDAO getSccParamProcessoDAO() {
		return sccParamProcessoDAO;
	}

	public void setSccParamProcessoDAO(SccParamProcessoDAO sccParamProcessoDAO) {
		this.sccParamProcessoDAO = sccParamProcessoDAO;
	}

}
