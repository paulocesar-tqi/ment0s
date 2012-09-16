package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAtividadeContabil;
import com.claro.cobillingweb.persistence.entity.SccContaContabil;
import com.claro.cobillingweb.persistence.entity.SccDominioContabil;

public interface SccAtividadeContabilDAO extends BasicDAO<SccAtividadeContabil>{

	public List<SccDominioContabil> getAllDominioContabil() throws DAOException;
	public List<SccContaContabil> getAllContaContabil() throws DAOException;
	
	
	
}
