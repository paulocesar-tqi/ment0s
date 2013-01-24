package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.DisponibilizacaoPacotePrePagoView;
import com.claro.sccweb.controller.util.BasicStringItem;

public interface SccAssinaturaPreService {

	public List<BasicStringItem> findPacotesAssinatura() throws ServiceException, DAOException;

	public List<DisponibilizacaoPacotePrePagoView> pesquisarDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote, Integer qtdMinutos, 
			Date dtInicio, Date dtFim) throws ServiceException, DAOException;
	public DisponibilizacaoPacotePrePagoView pesquisarSumarioDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote, Integer qtdMinutos, 
			Date dtInicioProcExterna, Date dtFimProcExterna) throws ServiceException, DAOException;
}
