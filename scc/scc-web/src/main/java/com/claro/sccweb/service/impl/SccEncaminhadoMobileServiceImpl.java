/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccEncaminhadoMobileDAO;
import com.claro.cobillingweb.persistence.view.SccEncaminhadoMobileView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccEncaminhadoMobileService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccEncaminhadoMobileServiceImpl extends AbstractService implements
		SccEncaminhadoMobileService {

	@Autowired
	private SccEncaminhadoMobileDAO sccEncaminhadoMobileDAO;
	
	@Override
	public List<SccEncaminhadoMobileView> gerarRelatorioEncaminhadoMobile(Date dtInicial, Date dtFinal, String noArquivoGerado) throws ServiceException, DAOException {
		
		return sccEncaminhadoMobileDAO.gerarRelatorioEncaminhadoMobile(dtInicial, dtFinal, noArquivoGerado);
	}

	
	public SccEncaminhadoMobileDAO getSccEncaminhadoMobileDAO() {
		return sccEncaminhadoMobileDAO;
	}

	public void setSccEncaminhadoMobileDAO(SccEncaminhadoMobileDAO sccEncaminhadoMobileDAO) {
		this.sccEncaminhadoMobileDAO = sccEncaminhadoMobileDAO;
	}
}
