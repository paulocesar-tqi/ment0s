/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernate;
import com.claro.cobillingweb.persistence.entity.SccDadosBancario;

/**
 * @author 93046251
 *
 */
public interface CadastroDadosBancariosDAO extends FwjBaseDaoHibernate<SccDadosBancario, String> {
	
	//List<SccGrupoCobilling> findAll() throws DAOException;
	
	SccDadosBancario findById(String cdEotLd, String nuBanco, String nuAgencia)throws DAOException;
	
	//void delete(SccGrupoCobilling entity) throws DAOException;
	
	//void update(SccGrupoCobilling entity) throws DAOException;
	
	//void updateCritica(SccCriticaPrebilling entity) throws DAOException;
	void deleteBancario(String cdEotLd, String nuBanco, String nuAgencia) throws DAOException;

	List<SccDadosBancario> listAll() throws DAOException;
	
	//void saveGrupo(SccGrupoCobilling entity) throws DAOException;

}
