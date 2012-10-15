/**
 * 
 */
package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccExtracaoCDRsDAO;
import com.claro.cobillingweb.persistence.view.SccExtracaoCDRsView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccExtracaoCDRsService;
import com.claro.sccweb.service.ServiceException;

/**
 * @author 92031709
 *
 */
@Service
public class SccExtracaoCDRsServiceImpl extends AbstractService implements
		SccExtracaoCDRsService {

	@Autowired
	private SccExtracaoCDRsDAO sccExtracaoCDRsDAO;
	
	@Override
	public List<SccExtracaoCDRsView> gerarRelatorioExtracaoCDRs(Date dtInicial, Date dtFinal, String nuMsisdnOrigem) throws ServiceException, DAOException {
		
		return sccExtracaoCDRsDAO.gerarRelatorioExtracaoCDRs(dtInicial, dtFinal, nuMsisdnOrigem);
	}

	
	public SccExtracaoCDRsDAO getSccExtracaoCDRsDAO() {
		return sccExtracaoCDRsDAO;
	}

	public void setSccExtracaoCDRsDAO(SccExtracaoCDRsDAO sccExtracaoCDRsDAO) {
		this.sccExtracaoCDRsDAO = sccExtracaoCDRsDAO;
	}
}
