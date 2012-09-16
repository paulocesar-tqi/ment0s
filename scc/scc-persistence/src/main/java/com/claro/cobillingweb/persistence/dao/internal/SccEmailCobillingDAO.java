/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;

/**
 * @author 93046251
 *
 */

public interface SccEmailCobillingDAO extends BasicDAO<SccEmailCobilling> {
	
	List<SccEmailCobilling> findAll() throws DAOException;
	
	SccEmailCobilling findById(Long id)throws DAOException;
	
	void delete(SccEmailCobilling entity) throws DAOException;
	
	void update(SccEmailCobilling entity) throws DAOException;


}
