package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccMapaStatus;

public interface SccMapaStatusDAO extends BasicDAO<SccMapaStatus> {

	public List<SccMapaStatus> pesquisaMapaStatus(Long cdStatusInicial,Long cdStatusFinal) throws DAOException;
	
}
