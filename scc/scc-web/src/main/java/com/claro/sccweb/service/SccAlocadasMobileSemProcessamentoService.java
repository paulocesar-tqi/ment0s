package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccAlocadasMobileSemProcessamentoView;

public interface SccAlocadasMobileSemProcessamentoService {
	
	
	List<SccAlocadasMobileSemProcessamentoView> gerarRelatorioAlocadasMobileSemProcessamento(Date dtInicial, Date dtFinal, String noArquivoGerado) throws ServiceException, DAOException;

}
