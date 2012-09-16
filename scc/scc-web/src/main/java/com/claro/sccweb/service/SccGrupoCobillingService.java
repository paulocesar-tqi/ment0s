/**
 * 
 */
package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;

/**
 * @author 93046251
 *
 */
public interface SccGrupoCobillingService {
	
	List<SccGrupoCobilling> findAll()throws DAOException;
	
	SccGrupoCobilling findById(Long id)throws DAOException;
	
	void delete(SccGrupoCobilling entity) throws DAOException;
	
	void update(SccGrupoCobilling entity) throws DAOException;
	
	void create(SccGrupoCobilling entity) throws DAOException;

	
	
	

}
