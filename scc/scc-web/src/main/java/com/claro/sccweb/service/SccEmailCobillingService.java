/**
 * 
 */
package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;

/**
 * @author 93046251
 *
 */
public interface SccEmailCobillingService {
	
	List<SccEmailCobilling> findAll()throws DAOException;
	
	SccEmailCobilling findById(Long id)throws DAOException;
	
	void delete(SccEmailCobilling entity) throws DAOException;
	
	void update(SccEmailCobilling entity) throws DAOException;
	
	void create(SccEmailCobilling entity) throws DAOException;


}
