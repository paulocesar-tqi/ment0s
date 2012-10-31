package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicao;

public interface SccPenalidadePorRejeicaoDAO extends BasicDAO<SccPenalidadePorRejeicao> {

	public List<SccPenalidadePorRejeicao> pesquisar(String cdEotLD, String cdMotivoRejeicao) throws DAOException;
	
}
