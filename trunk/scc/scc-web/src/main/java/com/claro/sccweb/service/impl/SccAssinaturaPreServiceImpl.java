package com.claro.sccweb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccAssinaturaPrePagoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.entity.SccAssinaturaPrePago;
import com.claro.cobillingweb.persistence.view.DisponibilizacaoPacotePrePagoView;
import com.claro.sccweb.controller.util.BasicStringItem;
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
	public List<BasicStringItem> findPacotesAssinatura() throws ServiceException, DAOException {
		List<BasicStringItem> lst = new ArrayList<BasicStringItem>();
		
		List<SccAssinaturaPrePago> lstAssinatura = getSccAssinaturaPrePagoDAO().findPacotesAssinatura();
		for (SccAssinaturaPrePago ass : lstAssinatura) {
			BasicStringItem bs = new BasicStringItem(ass.getPacotePrepago().getCdPacotePrepago() + "_" + ass.getQtMinutosAdquiridos().intValue(), 
					ass.getPacotePrepago().getNoPacotePrepago() + " " + ass.getQtMinutosAdquiridos().intValue() + " min.");
			lst.add(bs);
		}
		return lst;
	}

	@Override
	public List<DisponibilizacaoPacotePrePagoView> pesquisarDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote, Integer qtdMinutos,
			Date dtInicio, Date dtFim) throws ServiceException, DAOException {
		return getSccAssinaturaPrePagoDAO().pesquisarDisponibilidade(cdEOTClaro, cdEOTLD, cdPacote, qtdMinutos, dtInicio, dtFim);
	}
	
	@Override
	public DisponibilizacaoPacotePrePagoView pesquisarSumarioDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote, Integer qtdMinutos, 
			Date dtInicioProcExterna, Date dtFimProcExterna)
			throws ServiceException, DAOException {
		return getSccAssinaturaPrePagoDAO().pesquisarSumarioDisponibilidade(cdEOTClaro, cdEOTLD, cdPacote, qtdMinutos, dtInicioProcExterna, dtFimProcExterna);
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
