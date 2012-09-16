package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;

public interface SccContratoCoblDAO extends BasicDAO<SccContratoCobl> {

	public List<SccContratoCobl> pesquisaPorNome(String dsContrato) throws DAOException; 
	
	public List<SccProdutoContratado> pesquisaProdutosContratados(Long cdContratoCobilling) throws DAOException;
	
	
}
