package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccRelatorioConciliacaoView;

public interface SccRelatorioConciliacaoService {
	
	List<SccRelatorioConciliacaoView> search(String operadoraClaro, String operadoraExterna, Date dataInicio, Date dataFim) throws ServiceException, DAOException;

}
