/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;

/**
 * @author 93046251
 *
 */
public interface SccGrupoCobillingDAO extends BasicDAO<SccGrupoCobilling> {
	
	List<SccGrupoCobilling> findAll() throws DAOException;
	
	SccGrupoCobilling findById(Long id)throws DAOException;
	
	void delete(SccGrupoCobilling entity) throws DAOException;
	
	void update(SccGrupoCobilling entity) throws DAOException;

}
