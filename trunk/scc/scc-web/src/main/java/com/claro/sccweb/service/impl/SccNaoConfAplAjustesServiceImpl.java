/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccNaoConfAplAjustesDAO;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.AjustesView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccNaoConfAplAjustesService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 93046251
 *
 */
public class SccNaoConfAplAjustesServiceImpl extends AbstractService implements
		SccNaoConfAplAjustesService {
	
	@Autowired
	private SccNaoConfAplAjustesDAO sccNaoConfAplAjustesDAO;

	@Override
	public List<AjustesView> gerarRelatorioNaoConfAplAjuste(SccFiltro filtro)throws ServiceException, DAOException {
		
		return sccNaoConfAplAjustesDAO.gerarRelatorioNaoConfAplAjuste(filtro);
	}
	

	public SccNaoConfAplAjustesDAO getSccNaoConfAplAjustesDAO() {
		return sccNaoConfAplAjustesDAO;
	}

	public void setSccNaoConfAplAjustesDAO(
			SccNaoConfAplAjustesDAO sccNaoConfAplAjustesDAO) {
		this.sccNaoConfAplAjustesDAO = sccNaoConfAplAjustesDAO;
	}
	
	

}
