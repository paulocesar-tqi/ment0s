/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernate;
import com.claro.cobillingweb.persistence.entity.SccAssociacaoRelatorioGrupo;

/**
 * @author 92038883
 *
 */
public interface SccAssociacaoRelatorioGrupoDAO extends
		FwjBaseDaoHibernate<SccAssociacaoRelatorioGrupo, Long> {

	List<SccAssociacaoRelatorioGrupo> buscarGrupoAssociado(Long id)
			throws DAOException;

	SccAssociacaoRelatorioGrupo editEntity(Long sqGrupo, Long sqRelatorio,
			Date dtInicioVigencia) throws DAOException;

	void deleteEntity(Long sqGrupo, Long sqRelatorio, Date dtInicioVigencia)
			throws DAOException;

}
