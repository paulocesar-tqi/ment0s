package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;

public interface SccPacotePrepagoDAO extends BasicDAO<SccPacotePrepago> {

	public List<SccPacotePrepago> pesquisaPacotes(Long cdPacotePrepago,String noPacotePrepago,String cdProdutoPrepago) throws DAOException;
	
}
