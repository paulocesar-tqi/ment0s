package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccCriticaPrebilling;
import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;
import com.claro.cobillingweb.persistence.entity.SccStatusCdr;
import com.claro.cobillingweb.persistence.entity.SccTipoArquivo;


/**
 * Serviço para pesquisa de domínios comuns ao sistema. 
 * Exemplos desses domínio : status do arquivo , tipo de arquivo.
 * 
 */
public interface PesquisaDominiosService {

	public List<SccTipoArquivo> getTiposArquivo() throws ServiceException,DAOException;
	
	public List<SccStatusArquivo> getStatusArquivo() throws ServiceException,DAOException;
	
	public List<SccOperadora> pesquisaOperadorasExternas() throws ServiceException,DAOException;
		
	public List<SccOperadora> pesquisaHoldingClaro() throws ServiceException,DAOException;
		
	public List<SccOperadora> pequisaOperadorasClaro() throws ServiceException,DAOException;
	
	public List<SccOperadora> pequisaOperadorasClaroComM() throws ServiceException,DAOException;
	
	public List<SccPreDominio> pesquisaPorTipoDominioPre(String tipo) throws DAOException;
	
	public List<SccStatusCdr> getAllStatus() throws DAOException;
	
	public List<SccMotivoRejeicao> getAllMotivosRejeicao() throws DAOException;
	
	public List<SccCriticaPrebilling> getAllCriticaPreBilling() throws DAOException;
	
	
	
}
