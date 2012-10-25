package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccBatimentoWruppDAO;
import com.claro.cobillingweb.persistence.view.BatimentoWruppPrePagoView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccBatimentoWruppService;
import com.claro.sccweb.service.ServiceException;

public class SccBatimentoWruppServiceImpl extends AbstractService implements
		SccBatimentoWruppService {

	@Autowired
	private SccBatimentoWruppDAO sccBatimentoWruppDAO;

	@Override
	public List<BatimentoWruppPrePagoView> listarBatimentos(String cdEOTLD,
			String cdEOTClaro, int mes, int ano) throws ServiceException, DAOException {
		return sccBatimentoWruppDAO.listarBatimentos(cdEOTLD, cdEOTClaro, mes,
				ano);
	}

	public SccBatimentoWruppDAO getSccBatimentoWruppDAO() {
		return sccBatimentoWruppDAO;
	}

	public void setSccBatimentoWruppDAO(
			SccBatimentoWruppDAO sccBatimentoWruppDAO) {
		this.sccBatimentoWruppDAO = sccBatimentoWruppDAO;
	}

}
