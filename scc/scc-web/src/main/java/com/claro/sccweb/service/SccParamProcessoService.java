/**
 * 
 */
package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;

/**
 * @author 93046251
 *
 */
public interface SccParamProcessoService {
	
	List<SccParamProcesso> getAll()throws DAOException,ServiceException;
	
	List<SccParamProcesso> pesquisaProcessoParametros(String codProcesso, Date dtInicio, Date dtFim) throws DAOException, ServiceException;
	
	List<String> listarProcessos()throws DAOException, ServiceException;

	List<SccParamProcesso> gerarRelatorios(String idProcesso,
			String valorParametro) throws DAOException;
	
	
	

}
