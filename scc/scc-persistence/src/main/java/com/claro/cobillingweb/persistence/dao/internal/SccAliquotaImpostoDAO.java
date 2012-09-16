package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAliquotaImposto;

public interface SccAliquotaImpostoDAO extends BasicDAO<SccAliquotaImposto>{

	public List<SccAliquotaImposto> pesquisaAliquotas(String noImposto,String inPlataforma) throws DAOException;
	
}
