package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccConfirmacaoRepasseView;

public interface SccConfirmacaoRepasseService {
	
	
	List<SccConfirmacaoRepasseView> gerarRelatorioConfirmacaoRepasse(String mesRepasse, String anoRepasse, String cdEOTLD, String cdStatusRepasse) throws ServiceException, DAOException;

}
