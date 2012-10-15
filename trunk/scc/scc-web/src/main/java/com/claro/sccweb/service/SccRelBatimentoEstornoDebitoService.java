package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccRelBatimentoEstornoDebitoView;

public interface SccRelBatimentoEstornoDebitoService {
	
	List<SccRelBatimentoEstornoDebitoView> gerarRelBatimentoEstornoDebito(String cdEOTLD, String cdEOTClaro, String cdStatusArquivo, Integer mmCiclo, Integer aaCiclo) throws ServiceException, DAOException;

}
