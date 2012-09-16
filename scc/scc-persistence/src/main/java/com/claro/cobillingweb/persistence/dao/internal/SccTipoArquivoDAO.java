package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccTipoArquivo;

public interface SccTipoArquivoDAO extends BasicDAO<SccTipoArquivo> {

	 public List<Long> pesquisaPorTipoBatimento(String cdTipoBatimento) throws DAOException;
	 
	 public List<SccTipoArquivo> pesquisaEntidadePorTipoBatimento(String cdTipoBatimento) throws DAOException;
	
}
