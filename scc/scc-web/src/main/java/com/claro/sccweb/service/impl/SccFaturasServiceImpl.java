package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccFaturasDAO;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.filtro.SccFiltroAgingFaturas;
import com.claro.cobillingweb.persistence.filtro.SccFiltroFaturas;
import com.claro.cobillingweb.persistence.view.SccAgingFaturasView;
import com.claro.cobillingweb.persistence.view.SccFaturaView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccFaturasService;
import com.claro.sccweb.service.ServiceException;

@Service
public class SccFaturasServiceImpl extends AbstractService implements
		SccFaturasService {

	@Autowired
	private SccFaturasDAO sccFaturasDAO;
	
	@Override
	public List<SccFaturaView> gerarRelatorioFaturas(SccFiltroFaturas filtro) throws ServiceException, DAOException {
		
		return this.sccFaturasDAO.gerarRelatorioFaturas(filtro);
	}

	@Override
	public List<SccFaturaView> gerarRelatorioJurosMultas(SccFiltro filtro) throws ServiceException, DAOException {
		
		return this.sccFaturasDAO.gerarRelatorioJurosMultas(filtro);
	}
	
	public List<SccFaturaView> gerarComboCiclo() throws ServiceException, DAOException {
		
		return this.sccFaturasDAO.gerarComboCiclo();
		
	}
	
	

	@Override
	public List<SccAgingFaturasView> gerarRelatorioAgingFaturas(SccFiltroAgingFaturas filtro) throws ServiceException, DAOException {

		return this.sccFaturasDAO.gerarRelatorioAgingFaturas(filtro);
	}

	public void setSccFaturasDAO(SccFaturasDAO sccFaturasDAO) {
		this.sccFaturasDAO = sccFaturasDAO;
	}
	
	

}
