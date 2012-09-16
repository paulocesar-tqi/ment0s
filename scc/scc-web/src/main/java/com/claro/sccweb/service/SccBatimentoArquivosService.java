package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccBatimentoArquivosView;

public interface SccBatimentoArquivosService {
	
	
	List<SccBatimentoArquivosView> gerarRelatorioBatimento(Date dtInicioBatimento, Date dtFimBatimento, String cdEOTLD, String cdEOTClaro, String tpArquivo) throws ServiceException, DAOException;

}
