package com.claro.cobillingweb.persistence.dao.external;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.external.ControlConnectFile;

public interface ControlConnectFileDAO extends BasicDAO<ControlConnectFile>{
	
	/**
	 * Realiza pesquisa nos arquivos do CONNECT usando todos os filtros disponíveis.
	 * @param statusArquivo
	 * @param tipoArquivo
	 * @param nomeArquivo
	 * @param dataInicio
	 * @param dataFinal
	 * @return
	 * @throws DAOException
	 */
	public List<ControlConnectFile> pesquisaPorFiltros(Long statusArquivo,String tipoArquivo,String nomeArquivo,Date dataInicio,Date dataFinal) throws DAOException;
	
}
