package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;
import com.claro.cobillingweb.persistence.entity.SccItemCobilling;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.cobillingweb.persistence.entity.SccServicoAdicional;

public interface ProdutoService {

	public List<SccComposicaoProduto> carregaComposicaoProduto(Long cdProdutoCobilling) throws DAOException;
	
	public List<SccComposicaoProduto> carregaComposicaoProduto(Long cdProdutoCobilling,Long cdItemCobilling) throws DAOException;
	
	public SccProdutoCobilling getByPk(Long cdProdutoCobilling) throws DAOException;
	
	public void create(SccComposicaoProduto entity) throws DAOException;
	public void update(SccComposicaoProduto entity) throws DAOException;
	public void delete(SccComposicaoProduto entity) throws DAOException;
	
	public void create(SccProdutoCobilling entity) throws DAOException;
	public void update(SccProdutoCobilling entity) throws DAOException;
	public void delete(SccProdutoCobilling entity) throws DAOException;
	
	public List<SccProdutoCobilling> getAll() throws DAOException;
	public List<SccItemCobilling> getAllItem() throws DAOException;
	
	public List<SccServicoAdicional> pesquisaByProduto(Long cdProdutoCobilling) throws DAOException;
	public SccServicoAdicional getChargeCodeByPk(String cdCharge) throws DAOException;
	
	public void create(SccServicoAdicional entity) throws DAOException;
	public void update(SccServicoAdicional entity) throws DAOException;
	public void delete(SccServicoAdicional entity) throws DAOException;
	
	
}
