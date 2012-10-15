package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccContingenciaFiscalView;

public interface SccContingenciaFiscalService {
	
	
	List<SccContingenciaFiscalView> gerarRelatorioContingenciaFiscal(String mesRelatorio, String anoRelatorio, String cdCSP) throws ServiceException, DAOException;

}
