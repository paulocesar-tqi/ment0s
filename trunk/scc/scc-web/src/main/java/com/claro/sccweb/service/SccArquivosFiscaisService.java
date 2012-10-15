package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccArquivosFiscaisView;

public interface SccArquivosFiscaisService {
	
	
	List<SccArquivosFiscaisView> gerarRelatorioArquivosFiscais(String sgUF, String cdStatusArquivo, String cdCSP, Integer cdCiclo, Integer mesCiclo, Integer anoCiclo) throws ServiceException, DAOException;

}
