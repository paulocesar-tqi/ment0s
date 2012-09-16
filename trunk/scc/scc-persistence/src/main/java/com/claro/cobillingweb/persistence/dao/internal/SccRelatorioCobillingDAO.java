/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling;

/**
 * @author 93046251
 *
 */
public interface SccRelatorioCobillingDAO extends BasicDAO<SccRelatorioCobilling> {
	
	List<SccRelatorioCobilling> findAll() throws DAOException;
	
	SccRelatorioCobilling findById(Long id)throws DAOException;
	
	void delete(SccRelatorioCobilling entity) throws DAOException;
	
	void update(SccRelatorioCobilling entity) throws DAOException;


}
