package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccNaoConfAplBatimentoEstornoDebitoDAO;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.BatimentoEstornoDebitoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccNaoConfAplBatimentoEstornoDebitoService;
import com.claro.sccweb.service.ServiceException;

@Service
public class SccNaoConfAplBatimentoEstornoDebitoServiceImpl extends
		AbstractService implements SccNaoConfAplBatimentoEstornoDebitoService {
	
	@Autowired
	private SccNaoConfAplBatimentoEstornoDebitoDAO sccNaoConfAplBatimentoEstornoDebitoDAO;

	@Override
	public List<BatimentoEstornoDebitoView> gerarRelatorioBatimentoEstornoDebito(SccFiltro filtro) throws ServiceException, DAOException {
		
		return this.sccNaoConfAplBatimentoEstornoDebitoDAO.gerarRelatorioNaoConfBatimentoEstornoDebito(filtro);
	}

	public void setSccNaoConfAplBatimentoEstornoDebitoDAO(SccNaoConfAplBatimentoEstornoDebitoDAO sccNaoConfAplBatimentoEstornoDebitoDAO) {
		this.sccNaoConfAplBatimentoEstornoDebitoDAO = sccNaoConfAplBatimentoEstornoDebitoDAO;
	}
	
	
	

}
