package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccPrePagoCreditosView;

public interface SccPrePagoCreditosService {
	
	
	List<SccPrePagoCreditosView> gerarRelatorioPrePagoCreditos(Date dtInicial, Date dtFinal, String tipoCredito, String cdStatusCredito, String operadoraClaro, String operadoraLD) throws ServiceException, DAOException;

}
