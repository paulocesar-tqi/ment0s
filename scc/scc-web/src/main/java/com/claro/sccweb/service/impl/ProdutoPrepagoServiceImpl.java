package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccPacotePrepagoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoPrepagoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccTipoEventoDAO;
import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.entity.SccTipoEvento;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.ProdutoPrepagoService;

public class ProdutoPrepagoServiceImpl extends AbstractService implements ProdutoPrepagoService {

	private SccProdutoPrepagoDAO produtoPrepagoDAO;
	private SccPacotePrepagoDAO pacotePrepagoDAO;
	private SccTipoEventoDAO tipoEventoDAO;
	
	
	public SccProdutoPrepago findByPk(String codigo) throws DAOException {
		return getProdutoPrepagoDAO().getByPk(codigo, SccProdutoPrepago.class);
	}
	
	public List<SccProdutoPrepago> getAll() throws DAOException {
		return getProdutoPrepagoDAO().getAll();
	}

	
	@Transactional
	public void create(SccProdutoPrepago entity) throws DAOException {
		Integer proxSeq = proxSequence();
		entity.setCdProdutoPrepago(proxSeq.toString());
		getProdutoPrepagoDAO().create(entity);
		
	}
	 
	@Transactional
	public void update(SccProdutoPrepago entity) throws DAOException {
		getProdutoPrepagoDAO().update(entity);
		
	}

	 
	@Transactional
	public void delete(SccProdutoPrepago entity) throws DAOException {
		getProdutoPrepagoDAO().delete(entity);
		
	}

	public SccProdutoPrepagoDAO getProdutoPrepagoDAO() {
		return produtoPrepagoDAO;
	}

	public void setProdutoPrepagoDAO(SccProdutoPrepagoDAO produtoPrepagoDAO) {
		this.produtoPrepagoDAO = produtoPrepagoDAO;
	}

	 
	public List<SccPacotePrepago> pesquisaPacotes(Long cdPacotePrepago,String noPacotePrepago, String cdProdutoPrepago) throws DAOException {		
		return getPacotePrepagoDAO().pesquisaPacotes(cdPacotePrepago, noPacotePrepago, cdProdutoPrepago);				
	}

	public SccPacotePrepagoDAO getPacotePrepagoDAO() {
		return pacotePrepagoDAO;
	}

	public void setPacotePrepagoDAO(SccPacotePrepagoDAO pacotePrepagoDAO) {
		this.pacotePrepagoDAO = pacotePrepagoDAO;
	}

	 
	@Transactional
	public void create(SccPacotePrepago entity) throws DAOException {
		getPacotePrepagoDAO().create(entity);
		
	}

	 
	@Transactional
	public void update(SccPacotePrepago entity) throws DAOException {
		getPacotePrepagoDAO().update(entity);
		
	}

	 
	@Transactional
	public void delete(SccPacotePrepago entity) throws DAOException {
		getPacotePrepagoDAO().delete(entity);
		
	}

	public SccTipoEventoDAO getTipoEventoDAO() {
		return tipoEventoDAO;
	}

	public void setTipoEventoDAO(SccTipoEventoDAO tipoEventoDAO) {
		this.tipoEventoDAO = tipoEventoDAO;
	}

	 
	@Transactional
	public void create(SccTipoEvento entity) throws DAOException {
		getTipoEventoDAO().create(entity);
		
	}

	 
	@Transactional
	public void update(SccTipoEvento entity) throws DAOException {
		getTipoEventoDAO().update(entity);
		
	}

	 
	@Transactional
	public void delete(SccTipoEvento entity) throws DAOException {
		getTipoEventoDAO().delete(entity);
		
	}

	 
	public List<SccTipoEvento> getAllTipoEvento() throws DAOException {
		return getTipoEventoDAO().getAll();
	}

		public Integer proxSequence()throws DAOException {
		return getProdutoPrepagoDAO().proxSequence();
	}
		
}
