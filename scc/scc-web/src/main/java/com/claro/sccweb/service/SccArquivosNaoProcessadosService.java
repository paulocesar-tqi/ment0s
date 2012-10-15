package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccArquivosNaoProcessadosView;

public interface SccArquivosNaoProcessadosService {
	
	
	List<SccArquivosNaoProcessadosView> gerarRelatorioArquivosNaoProcessados(Date dtInicial, Date dtFinal, String noArquivoGerado) throws ServiceException, DAOException;

}
