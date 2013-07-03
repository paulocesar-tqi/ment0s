/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernate;
import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;

/**
 * @author 93046251
 *
 */
public interface SccGrupoCobillingDAO extends FwjBaseDaoHibernate<SccGrupoCobilling, String> {
	
	//List<SccGrupoCobilling> findAll() throws DAOException;
	
	//SccGrupoCobilling findById(Long id)throws DAOException;
	
	//void delete(SccGrupoCobilling entity) throws DAOException;
	
	//void update(SccGrupoCobilling entity) throws DAOException;
	
	void updateGrupo(SccGrupoCobilling entity) throws DAOException;
	void deleteGrupo(Long sqGrupo) throws DAOException;
	List<SccGrupoCobilling> pesquisarBySeqGrupo(Long seqGrupo)
			throws DAOException;
	
	//void saveGrupo(SccGrupoCobilling entity) throws DAOException;

}
