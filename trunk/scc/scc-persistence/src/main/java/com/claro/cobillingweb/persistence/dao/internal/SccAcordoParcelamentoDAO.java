package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.view.SccAcordoParcelamentoView;

public interface SccAcordoParcelamentoDAO extends BasicDAO<SccAcordoParcelamentoView> {
	
	Collection<SccAcordoParcelamentoView> findByFilterAnalitico(SccFiltro filtro) throws DAOException;
	
	Collection<SccAcordoParcelamentoView> findByFilterSintetico(SccFiltro filtro) throws DAOException;
	
	Collection<SccAcordoParcelamentoView> findByFilterRelAcompanhamentoAnalitico(SccFiltro filtro) throws DAOException;
	
	Collection<SccAcordoParcelamentoView> findByFilterRelAcompanhamentoSintetico(SccFiltro filtro) throws DAOException;

}
