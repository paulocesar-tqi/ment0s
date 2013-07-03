package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccComposicaoProdutoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccItemCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccServicoAdicionalDAO;
import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;
import com.claro.cobillingweb.persistence.entity.SccItemCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccServicoAdicional;
import com.claro.cobillingweb.persistence.service.ServiceException;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.ProdutoService;

public class ProdutoServiceImpl extends AbstractService implements ProdutoService {

	@Autowired
	private SccProdutoCobillingDAO produtoCobillingDAO;
	
	@Autowired
	private SccComposicaoProdutoDAO composicaoProdutoDAO;
	private SccItemCobillingDAO itemCobillingDAO;
	private SccServicoAdicionalDAO servicoAdicionalDAO;
	
	 
	public List<SccComposicaoProduto> carregaComposicaoProduto(Long cdProdutoCobilling) throws DAOException {
		return getProdutoCobillingDAO().carregaComposicaoProduto(cdProdutoCobilling);
	}

	public SccProdutoCobillingDAO getProdutoCobillingDAO() {
		return produtoCobillingDAO;
	}

	public void setProdutoCobillingDAO(SccProdutoCobillingDAO produtoCobillingDAO) {
		this.produtoCobillingDAO = produtoCobillingDAO;
	}

	 
	public SccProdutoCobilling getByPk(Long cdProdutoCobilling) throws DAOException {
		return getProdutoCobillingDAO().getByPk(cdProdutoCobilling, SccProdutoCobilling.class);
	}

	 
	public List<SccComposicaoProduto> carregaComposicaoProduto(Long cdProdutoCobilling, Long cdItemCobilling) throws DAOException {
		return getProdutoCobillingDAO().carregaComposicaoProduto(cdProdutoCobilling, cdItemCobilling);
	}

	 
	@Transactional
	@Override
	public void create(SccComposicaoProduto entity) throws DAOException, ServiceException {
		getComposicaoProdutoDAO().create(entity);
		
	}

	 
	@Transactional
	@Override
	public void update(SccComposicaoProduto entity) throws DAOException, ServiceException {
		getComposicaoProdutoDAO().update(entity);
		
	}

	 
	@Transactional
	@Override
	public void delete(SccComposicaoProduto entity) throws DAOException, ServiceException {
		getComposicaoProdutoDAO().delete(entity);
		
	}
	
	@Transactional
	@Override
	public void deleteEntity(Long cdComponente) throws DAOException, ServiceException{
		this.composicaoProdutoDAO.deleteEntity(cdComponente);
	}

	public SccComposicaoProdutoDAO getComposicaoProdutoDAO() {
		return composicaoProdutoDAO;
	}

	public void setComposicaoProdutoDAO(SccComposicaoProdutoDAO composicaoProdutoDAO) {
		this.composicaoProdutoDAO = composicaoProdutoDAO;
	}

	 
	public List<SccProdutoCobilling> getAll() throws DAOException {
		return getProdutoCobillingDAO().getAll();
	}

	public SccItemCobillingDAO getItemCobillingDAO() {
		return itemCobillingDAO;
	}

	public void setItemCobillingDAO(SccItemCobillingDAO itemCobillingDAO) {
		this.itemCobillingDAO = itemCobillingDAO;
	}

	 
	public List<SccItemCobilling> getAllItem() throws DAOException {
		return getItemCobillingDAO().getAll();
	}

	 
	@Transactional
	public void create(SccProdutoCobilling entity) throws DAOException {
		getProdutoCobillingDAO().create(entity);
		
	}

	 
	@Transactional
	public void update(SccProdutoCobilling entity) throws DAOException {
		getProdutoCobillingDAO().update(entity);
		
	}

	 
	@Transactional
	public void delete(SccProdutoCobilling entity) throws DAOException {
		getProdutoCobillingDAO().delete(entity);
		
	}
	
	@Override
	public SccComposicaoProduto carregarEntidade(Long cdComponente) throws DAOException{
		return this.composicaoProdutoDAO.carregarEntidade(cdComponente);
	}
	
	@Override
	public SccComposicaoProduto findById(Long id)throws DAOException, ServiceException {
		return this.composicaoProdutoDAO.findById(id);
	}

	 
	public List<SccServicoAdicional> pesquisaByProduto(Long cdProdutoCobilling) throws DAOException {
		return getServicoAdicionalDAO().pesquisaByProduto(cdProdutoCobilling);
	}

	 
	@Transactional
	public void create(SccServicoAdicional entity) throws DAOException {
		getServicoAdicionalDAO().create(entity);
		
	}

	 
	@Transactional
	public void update(SccServicoAdicional entity) throws DAOException {
		getServicoAdicionalDAO().update(entity);
		
	}

	 
	@Transactional
	public void delete(SccServicoAdicional entity) throws DAOException {
		getServicoAdicionalDAO().delete(entity);
		
	}

	public SccServicoAdicionalDAO getServicoAdicionalDAO() {
		return servicoAdicionalDAO;
	}

	public void setServicoAdicionalDAO(SccServicoAdicionalDAO servicoAdicionalDAO) {
		this.servicoAdicionalDAO = servicoAdicionalDAO;
	}

	 
	public SccServicoAdicional getChargeCodeByPk(String cdCharge) throws DAOException {
		return getServicoAdicionalDAO().getByPk(cdCharge, SccServicoAdicional.class);
	}

	
	
}
