package com.claro.cobillingweb.persistence.dao.external;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.external.ControlConnectFile;
import com.claro.cobillingweb.persistence.filtro.SccFiltroControleArquivo;

public interface ControlConnectFileDAO extends BasicDAO<ControlConnectFile>{
	
	/**
	 * Realiza pesquisa nos arquivos do CONNECT usando todos os filtros disponíveis.
	 * @param form
	 * @return
	 * @throws DAOException
	 */
	public List<ControlConnectFile> pesquisaPorFiltros(SccFiltroControleArquivo filtro) throws DAOException;
	
}
