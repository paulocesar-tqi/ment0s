package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccPreChamadasCreditoView;

/**
 * @author 92038883
 *
 */

public interface SccPreChamadasCreditoDAO {
	
	List<SccPreChamadasCreditoView> gerarPreChamadasCredito(Date dtInicioCredito, Date dtFimCredito, String cdEOTLD, String cdEOTClaro, String tpStatusCredito) throws DAOException;

}
