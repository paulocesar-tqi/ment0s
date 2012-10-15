/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccAlocadasMobileDAO;
import com.claro.cobillingweb.persistence.view.SccAlocadasMobileView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccAlocadasMobileService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccAlocadasMobileServiceImpl extends AbstractService implements
		SccAlocadasMobileService {

	@Autowired
	private SccAlocadasMobileDAO sccAlocadasMobileDAO;
	
	@Override
	public List<SccAlocadasMobileView> gerarRelatorioAlocadasMobile(Date dtInicial, Date dtFinal, String noArquivoGerado) throws ServiceException, DAOException {
		
		return sccAlocadasMobileDAO.gerarRelatorioAlocadasMobile(dtInicial, dtFinal, noArquivoGerado);
	}

	
	public SccAlocadasMobileDAO getSccAlocadasMobileDAO() {
		return sccAlocadasMobileDAO;
	}

	public void setSccAlocadasMobileDAO(SccAlocadasMobileDAO sccAlocadasMobileDAO) {
		this.sccAlocadasMobileDAO = sccAlocadasMobileDAO;
	}
}
