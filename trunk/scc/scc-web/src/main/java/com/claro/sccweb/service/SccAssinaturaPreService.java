package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;
import com.claro.cobillingweb.persistence.view.DisponibilizacaoPacotePrePagoView;

public interface SccAssinaturaPreService {

	public List<SccPacotePrepago> findPacotesAssinatura() throws ServiceException, DAOException;

	public List<DisponibilizacaoPacotePrePagoView> pesquisarDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote, Date dtInicio,
			Date dtFim) throws ServiceException, DAOException;
	public DisponibilizacaoPacotePrePagoView pesquisarSumarioDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote,
			Date dtInicioProcExterna, Date dtFimProcExterna) throws ServiceException, DAOException;
}
