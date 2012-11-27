package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAssinaturaPrePago;
import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;

public interface SccAssinaturaPreService {

	public List<SccPacotePrepago> findPacotesAssinatura() throws ServiceException, DAOException;

	public List<SccAssinaturaPrePago> pesquisarDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote, Date dtInicio,
			Date dtFim) throws ServiceException, DAOException;

}
