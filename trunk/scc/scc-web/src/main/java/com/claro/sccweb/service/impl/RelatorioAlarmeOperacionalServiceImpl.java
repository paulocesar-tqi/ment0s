/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioAlarmeOperacionalDAO;
import com.claro.cobillingweb.persistence.view.RelAlarmeOperacionalView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.RelatorioAlarmeOperacionalService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 93046251
 *
 */
@Service
public class RelatorioAlarmeOperacionalServiceImpl extends AbstractService implements
		RelatorioAlarmeOperacionalService {

	@Autowired
	private SccRelatorioAlarmeOperacionalDAO sccRelatorioAlarmeOperacionalDAO;
	
	@Override
	public List<RelAlarmeOperacionalView> listarAlarmeOperacional(Date dtInicio, Date dtFim, String nomeArquivo) throws ServiceException, DAOException {
		
		return sccRelatorioAlarmeOperacionalDAO.listarAlarmeOperacional(dtInicio, dtFim, nomeArquivo);
	}

	public SccRelatorioAlarmeOperacionalDAO getSccRelatorioAlarmeOperacionalDAO() {
		return sccRelatorioAlarmeOperacionalDAO;
	}

	public void setSccRelatorioAlarmeOperacionalDAO(
			SccRelatorioAlarmeOperacionalDAO sccRelatorioAlarmeOperacionalDAO) {
		this.sccRelatorioAlarmeOperacionalDAO = sccRelatorioAlarmeOperacionalDAO;
	}
	
}
