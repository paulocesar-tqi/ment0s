package com.claro.cobillingweb.persistence.dao.internal;

/**
 * @author 92038883
 *
 */

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccRelatorioChamadasRefaturamentoView;

public interface SccRelatorioChamadasRefaturamentoDAO {

	List<SccRelatorioChamadasRefaturamentoView> gerarRelatorioChamadasRefaturamento(Date dtInicioPeriodo, Date dtFimPeriodo, String cdEOTLD, String cdEOTClaro, String cdRefaturamento) throws DAOException;
	
}
