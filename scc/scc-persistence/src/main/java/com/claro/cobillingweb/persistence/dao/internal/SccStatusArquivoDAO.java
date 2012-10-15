package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;

public interface SccStatusArquivoDAO extends BasicDAO<SccStatusArquivo> {
	
	List<SccStatusArquivo> findByStatus() throws DAOException;
	
}
