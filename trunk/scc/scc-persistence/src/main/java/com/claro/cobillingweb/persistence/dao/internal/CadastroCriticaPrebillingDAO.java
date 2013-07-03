/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernate;
import com.claro.cobillingweb.persistence.entity.SccCriticaPrebilling;

/**
 * @author 93046251
 *
 */
public interface CadastroCriticaPrebillingDAO extends FwjBaseDaoHibernate<SccCriticaPrebilling, String> {
	
	//List<SccGrupoCobilling> findAll() throws DAOException;
	
	//SccGrupoCobilling findById(Long id)throws DAOException;
	
	//void delete(SccGrupoCobilling entity) throws DAOException;
	
	//void update(SccGrupoCobilling entity) throws DAOException;
	
	//void updateCritica(SccCriticaPrebilling entity) throws DAOException;
	void deleteCritica(String id) throws DAOException;
	
	//void saveGrupo(SccGrupoCobilling entity) throws DAOException;

}
