package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccServicoAdicional;

public interface SccServicoAdicionalDAO extends BasicDAO<SccServicoAdicional>{

	public List<SccServicoAdicional> pesquisaByProduto(Long cdProdutoCobilling) throws DAOException;
	
}
