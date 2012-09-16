package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccFaixaPenalidade;

public interface SccFaixaPenalidadeDAO extends BasicDAO<SccFaixaPenalidade> {

	public List<SccFaixaPenalidade> pesquisarPorTipo(String tipo) throws DAOException;
	
}
