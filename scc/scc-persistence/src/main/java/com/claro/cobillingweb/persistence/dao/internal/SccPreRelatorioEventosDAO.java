package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccPreRelatorioEventosView;

/**
 * @author 92038883
 *
 */

public interface SccPreRelatorioEventosDAO {
	
	List<SccPreRelatorioEventosView> gerarPreRelatorioEventos(Date dtInicioEvento, Date dtFimEvento, String cdEOTLD, String cdEOTClaro, String tpStatus) throws DAOException;

}
