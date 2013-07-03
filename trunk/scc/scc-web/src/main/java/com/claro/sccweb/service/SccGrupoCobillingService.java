/**
 * 
 */
package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;
import com.claro.cobillingweb.persistence.service.ServiceException;

/**
 * @author 93046251
 *
 */
public interface SccGrupoCobillingService {
	
	List<SccGrupoCobilling> findAll()throws DAOException;
	
	SccGrupoCobilling findById(Long id)throws DAOException, ServiceException;
	
	void delete(SccGrupoCobilling entity) throws DAOException, ServiceException;
	
	void update(SccGrupoCobilling entity) throws DAOException, ServiceException;
	
	void create(SccGrupoCobilling entity) throws DAOException, ServiceException;
	
	void updateGrupo(SccGrupoCobilling entity) throws DAOException;
	
	void saveGrupo(SccGrupoCobilling entity) throws DAOException;
	
	void clearGrupo();
	
	void deleteGrupo(Long sqGrupo) throws DAOException;

	List<SccGrupoCobilling> pesquisarBySeqGrupo(Long seqGrupo)
			throws DAOException;

	
	
	

}
