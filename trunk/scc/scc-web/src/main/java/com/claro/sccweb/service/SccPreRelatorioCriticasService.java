package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccPreRelatorioCriticasView;

/**
 * @author 92038883
 *
 */

public interface SccPreRelatorioCriticasService {

	List<SccPreRelatorioCriticasView> gerarPreRelatorioCriticas(Date dtInicioEvento, Date dtFimEvento, String cdEOTLD, String cdEOTClaro, String cdStatus, String cdMotivoRejeicao, String cdDefeito) throws ServiceException, DAOException;
	
}
