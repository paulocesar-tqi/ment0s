package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccPosRelatorioDisputaDetalheView;

public interface SccPosRelatorioDisputaDetalheService {
	
	
	List<SccPosRelatorioDisputaDetalheView> gerarPosRelatorioDisputaDetalhe(String mesRelatorio, String anoRelatorio, String cdEotLD) throws ServiceException, DAOException;

}
