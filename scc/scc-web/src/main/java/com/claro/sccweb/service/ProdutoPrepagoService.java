package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.entity.SccTipoEvento;

public interface ProdutoPrepagoService {

	public List<SccProdutoPrepago> getAll() throws DAOException;
	
	public void create(SccProdutoPrepago entity) throws DAOException;
	public void update(SccProdutoPrepago entity) throws DAOException;
	public void delete(SccProdutoPrepago entity) throws DAOException;
	public List<SccPacotePrepago> pesquisaPacotes(Long cdPacotePrepago,String noPacotePrepago,String cdProdutoPrepago) throws DAOException;
	
	public void create(SccPacotePrepago entity) throws DAOException;
	public void update(SccPacotePrepago entity) throws DAOException;
	public void delete(SccPacotePrepago entity) throws DAOException;
	
	public void create(SccTipoEvento entity) throws DAOException;
	public void update(SccTipoEvento entity) throws DAOException;
	public void delete(SccTipoEvento entity) throws DAOException;
	public List<SccTipoEvento> getAllTipoEvento() throws DAOException;
	public Integer proxSequence()throws DAOException;
	
}
