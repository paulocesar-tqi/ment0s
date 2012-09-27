package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccBatimentoInterfaceView;

public interface SccBatimentoInterfaceService {
	
	
	List<SccBatimentoInterfaceView> listarBatimentoInterface(Date dtInicio, Date dtFim, String cdEOTLD, String cdEOTClaro, String tpArquivo) throws ServiceException, DAOException;

}
