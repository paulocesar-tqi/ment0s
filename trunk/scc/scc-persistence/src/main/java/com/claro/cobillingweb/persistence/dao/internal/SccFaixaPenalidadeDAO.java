package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernate;
import com.claro.cobillingweb.persistence.entity.SccFaixaPenalidade;

public interface SccFaixaPenalidadeDAO extends FwjBaseDaoHibernate<SccFaixaPenalidade, String> {
	
	List<SccFaixaPenalidade> pesquisarPorTipo(String tipo) throws DAOException;
	
	void deleteEntity(Long cdFaixaPenalidade) throws DAOException;
	
}
