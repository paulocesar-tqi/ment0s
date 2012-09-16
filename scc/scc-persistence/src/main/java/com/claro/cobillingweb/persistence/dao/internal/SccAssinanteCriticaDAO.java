package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAssinanteCritica;

public interface SccAssinanteCriticaDAO extends BasicDAO<SccAssinanteCritica> {

	public List<SccAssinanteCritica> pesquisaCritica(String cdCritica) throws DAOException;
	
}
