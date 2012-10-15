package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccPreRelatorioEventosView;

/**
 * @author 92038883
 *
 */

public interface SccPreRelatorioEventosService {

	List<SccPreRelatorioEventosView> gerarPreRelatorioEventos(Date dtInicioEvento, Date dtFimEvento, String cdEOTLD, String cdEOTClaro, String tpStatus) throws ServiceException, DAOException;
	
}
