package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAssinaturaPrePago;
import com.claro.cobillingweb.persistence.view.DisponibilizacaoPacotePrePagoView;

public interface SccAssinaturaPrePagoDAO  extends BasicDAO<SccAssinaturaPrePago> {

	public List<SccAssinaturaPrePago> findPacotesAssinatura() throws DAOException;

	public List<DisponibilizacaoPacotePrePagoView> pesquisarDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote, Integer qtdMinutos,
			Date dtInicio, Date dtFim) throws DAOException;
	
	public DisponibilizacaoPacotePrePagoView pesquisarSumarioDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote, Integer qtdMinutos,
			Date dtInicioProcExterna, Date dtFimProcExterna) throws DAOException;
}
