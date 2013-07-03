package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;

public interface SccParamProcessoDAO extends BasicDAO<SccParamProcesso> {

	List<SccParamProcesso> getAll() throws DAOException;
	public List<SccParamProcesso> pesquisaRepassesProcessados(String idProcesso,int max) throws DAOException;
	
	public List<SccParamProcesso> pesquisaRepassesProcessando(String idProcesso,int max) throws DAOException;
	
	public List<SccParamProcesso> pesquisaRepassesAgendados(String idProcesso) throws DAOException;
	
	public List<SccParamProcesso> pesquisaRepassesProcessados(int max,String cdEOT,Long cdPeriodo,Long mes) throws DAOException;
	
	public Long getProximoValorSequence() throws DAOException;
	
	public void deleteSolicitacaoRepasse(String noRequisicao) throws DAOException;
	
	public void deleteSolicitacaoRepassePre(String noRequisicao) throws DAOException;
	
	List<SccParamProcesso> pesquisaProcessoParametros(String codProcesso, Date dtInicio, Date dtFim)throws DAOException;
	List<SccParamProcesso> pesquisaProcessoParametrosByData(Date dtInicio, Date dtFim) throws DAOException;
	
	public List<SccParamProcesso> listarTodos()throws DAOException;
	List<String> listarProcesso()throws DAOException;
	
	public void deleteParamProcesso(String idProcesso, String noRequisicao) throws DAOException;
	List<SccParamProcesso> pesquisaRepassesAgendadosPre(String idProcesso)
			throws DAOException;
	List<SccParamProcesso> pesquisaRepassesProcessandoPre(String idProcesso,
			int max) throws DAOException;
	List<SccParamProcesso> pesquisaRepassesProcessadosPre(String idProcesso,
			int max) throws DAOException;
	List<SccParamProcesso> gerarRelatorios(String idProcesso,
			String valorParametro) throws DAOException;
	
}
