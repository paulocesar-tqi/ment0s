/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccNaoConfAplFaturamentoDAO;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.FaturamentoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccNaoConfAplFaturamentoService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 93046251
 *
 */
@Service
public class SccNaoConfAplFaturamentoServiceImpl extends AbstractService
		implements SccNaoConfAplFaturamentoService {
	
	@Autowired
	private SccNaoConfAplFaturamentoDAO sccNaoConfAplFaturamentoDAO;

	@Override
	public List<FaturamentoView> gerarRelatorioNaoConfFaturamento(
			SccFiltro filtro) throws ServiceException, DAOException {

		return sccNaoConfAplFaturamentoDAO.gerarRelatorioNaoConfFaturamento(filtro);
	}

	public void setSccNaoConfAplFaturamentoDAO(
			SccNaoConfAplFaturamentoDAO sccNaoConfAplFaturamentoDAO) {
		this.sccNaoConfAplFaturamentoDAO = sccNaoConfAplFaturamentoDAO;
	}
	
	

}
