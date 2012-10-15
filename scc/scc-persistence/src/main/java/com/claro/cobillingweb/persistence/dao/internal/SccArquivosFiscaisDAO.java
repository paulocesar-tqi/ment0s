package com.claro.cobillingweb.persistence.dao.internal;



import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccArquivosFiscaisView;

public interface SccArquivosFiscaisDAO extends BasicDAO<SccArquivosFiscaisView> {
	
	List<SccArquivosFiscaisView> gerarRelatorioArquivosFiscais(String sgUF, String cdStatusArquivo, String cdCSP, Integer cdCiclo, Integer mesCiclo, Integer anoCiclo) throws DAOException;

}
