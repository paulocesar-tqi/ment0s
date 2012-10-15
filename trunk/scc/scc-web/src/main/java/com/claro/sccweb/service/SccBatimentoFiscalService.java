package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccBatimentoFiscalView;

public interface SccBatimentoFiscalService {
	
	
	List<SccBatimentoFiscalView> gerarRelatorioBatimentoFiscal(Integer mesCiclo, Integer anoCiclo, Integer cdCiclo, String cdCSP) throws ServiceException, DAOException;

}
