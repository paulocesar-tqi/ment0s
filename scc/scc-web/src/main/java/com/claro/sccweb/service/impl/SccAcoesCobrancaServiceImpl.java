package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccAcoesCobrancaDAO;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.SccAcoesCobrancaView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccAcoesCobrancaService;
import com.claro.sccweb.service.ServiceException;

@Service
public class SccAcoesCobrancaServiceImpl extends AbstractService implements
		SccAcoesCobrancaService {
	
	@Autowired
	private SccAcoesCobrancaDAO sccAcoesCobrancaDAO;

	@Override
	public List<SccAcoesCobrancaView> gerarRelatorioControleAcoesCobranca(
			SccFiltro filtro) throws ServiceException, DAOException {
		
		return this.sccAcoesCobrancaDAO.gerarRelatorioControleAcoesCobranca(filtro);
	}

	public void setSccAcoesCobrancaDAO(SccAcoesCobrancaDAO sccAcoesCobrancaDAO) {
		this.sccAcoesCobrancaDAO = sccAcoesCobrancaDAO;
	}
	
	

}
