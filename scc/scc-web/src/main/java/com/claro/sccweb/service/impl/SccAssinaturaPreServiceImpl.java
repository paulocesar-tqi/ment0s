package com.claro.sccweb.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccAssinaturaPrePagoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.entity.SccAssinaturaPrePago;
import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.SccAssinaturaPreService;
import com.claro.sccweb.service.ServiceException;

@Service
public class SccAssinaturaPreServiceImpl extends AbstractService implements SccAssinaturaPreService {

	@Autowired
	private SccAssinaturaPrePagoDAO sccAssinaturaPrePagoDAO;
	@Autowired
	private SccOperadoraDAO sccOperadoraDAO;

	@Override
	public List<SccPacotePrepago> findPacotesAssinatura() throws ServiceException, DAOException {
		return getSccAssinaturaPrePagoDAO().findPacotesAssinatura();
	}

	@Override
	public List<SccAssinaturaPrePago> pesquisarDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote,
			Date dtInicio, Date dtFim) throws ServiceException, DAOException {
		return getSccAssinaturaPrePagoDAO().pesquisarDisponibilidade(cdEOTClaro, cdEOTLD, cdPacote, dtInicio, dtFim);
	}

	public SccAssinaturaPrePagoDAO getSccAssinaturaPrePagoDAO() {
		return sccAssinaturaPrePagoDAO;
	}

	public void setSccAssinaturaPrePagoDAO(SccAssinaturaPrePagoDAO sccAssinaturaPrePagoDAO) {
		this.sccAssinaturaPrePagoDAO = sccAssinaturaPrePagoDAO;
	}

	public SccOperadoraDAO getSccOperadoraDAO() {
		return sccOperadoraDAO;
	}

	public void setSccOperadoraDAO(SccOperadoraDAO sccOperadoraDAO) {
		this.sccOperadoraDAO = sccOperadoraDAO;
	}

}
