package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAssinaturaPrePago;
import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;
import com.claro.cobillingweb.persistence.view.DisponibilizacaoPacotePrePagoView;

public interface SccAssinaturaPrePagoDAO  extends BasicDAO<SccAssinaturaPrePago> {

	public List<SccPacotePrepago> findPacotesAssinatura() throws DAOException;

	public List<DisponibilizacaoPacotePrePagoView> pesquisarDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote,
			Date dtInicio, Date dtFim) throws DAOException;
	
	public DisponibilizacaoPacotePrePagoView pesquisarSumarioDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote,
			Date dtInicioProcExterna, Date dtFimProcExterna) throws DAOException;
}
