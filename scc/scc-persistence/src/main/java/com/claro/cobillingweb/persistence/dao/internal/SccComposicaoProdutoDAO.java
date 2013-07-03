package com.claro.cobillingweb.persistence.dao.internal;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernate;
import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;

public interface SccComposicaoProdutoDAO extends FwjBaseDaoHibernate<SccComposicaoProduto, String> {
	
	SccComposicaoProduto carregarEntidade(Long cdComponente) throws DAOException;
	void deleteEntity(Long cdComponente) throws DAOException;

	
	
}
