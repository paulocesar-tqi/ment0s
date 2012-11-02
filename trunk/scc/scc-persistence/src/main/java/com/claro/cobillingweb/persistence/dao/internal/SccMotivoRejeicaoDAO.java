package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;

public interface SccMotivoRejeicaoDAO extends BasicDAO<SccMotivoRejeicao> {

	public List<SccMotivoRejeicao> getAllConfigPenalidade() throws DAOException;	
	
}
