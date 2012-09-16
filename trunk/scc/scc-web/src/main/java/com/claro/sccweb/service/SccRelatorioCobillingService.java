package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling;

public interface SccRelatorioCobillingService {
	
	List<SccRelatorioCobilling> findAll()throws DAOException;
	
	SccRelatorioCobilling findById(Long id)throws DAOException;
	
	void delete(SccRelatorioCobilling entity) throws DAOException;
	
	void update(SccRelatorioCobilling entity) throws DAOException;
	
	void create(SccRelatorioCobilling entity) throws DAOException;


}
