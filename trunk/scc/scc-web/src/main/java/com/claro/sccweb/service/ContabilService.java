package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAtividadeContabil;
import com.claro.cobillingweb.persistence.entity.SccContaContabil;
import com.claro.cobillingweb.persistence.entity.SccDominioContabil;

public interface ContabilService {
	
	public List<SccDominioContabil> getAllDominioContabil() throws DAOException;
	
	public List<SccContaContabil> getAllContaContabil() throws DAOException;
	
	public List<SccAtividadeContabil> getAllAtividadeContabil() throws DAOException;
	
	public void update(SccAtividadeContabil entity) throws DAOException;
		
	public void create(SccAtividadeContabil entity) throws DAOException;
	   	
	public void delete(SccAtividadeContabil entity) throws DAOException;
	
	

}
