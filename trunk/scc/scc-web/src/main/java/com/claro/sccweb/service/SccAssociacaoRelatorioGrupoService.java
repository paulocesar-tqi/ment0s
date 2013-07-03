package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccAssociacaoRelatorioGrupo;
import com.claro.cobillingweb.persistence.service.ServiceException;

public interface SccAssociacaoRelatorioGrupoService {

	List<SccAssociacaoRelatorioGrupo> buscarGrupoAssociado(Long id)
			throws ServiceException, DAOException;

	SccAssociacaoRelatorioGrupo editEntity(Long sqGrupo, Long sqRelatorio,
			Date dtInicioVigencia) throws ServiceException, DAOException;

	void salvarEntity(SccAssociacaoRelatorioGrupo entity)
			throws ServiceException, DAOException;

	void deleteEntity(Long sqGrupo, Long sqRelatorio, Date dtInicioVigencia)
			throws ServiceException, DAOException;

}
