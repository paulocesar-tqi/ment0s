package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccRelatorioChamadasRefaturamentoView;

/**
 * @author 92038883
 *
 */

public interface SccRelatorioChamadasRefaturamentoService {
	
	List<SccRelatorioChamadasRefaturamentoView> gerarRelatorioChamadasRefaturamento(Date dtInicioPeriodo, Date dtFimPeriodo, String cdEOTLD, String cdEOTClaro, String cdRefaturamento) throws ServiceException, DAOException;

}
