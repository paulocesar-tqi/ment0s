package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccPreChamadasCreditoView;

public interface SccPreChamadasCreditoService {

	List<SccPreChamadasCreditoView> gerarPreChamadasCredito(Date dtInicioCredito, Date dtFimCredito, String cdEOTLD, String cdEOTClaro, String tpStatusCredito) throws ServiceException, DAOException;
}
