package com.claro.sccweb.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.internal.SccContratoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoContratadoPrepagoDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccProdutoPrepagoDAO;
import com.claro.cobillingweb.persistence.entity.SccContratoCobilling;
import com.claro.cobillingweb.persistence.entity.SccContratoCobillingPK;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPrepago;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;
import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.ContratoPrePagoService;

public class ContratoPrePagoServiceImpl extends AbstractService implements ContratoPrePagoService {
	
	private SccProdutoContratadoPrepagoDAO produtoContratadoPrepagoDAO;
	private SccProdutoPrepagoDAO produtoPrepagoDAO;
	private SccContratoCobillingDAO contratoCobillingDAO;
	
	public List<SccProdutoPrepago> pesquisaProdutosContratadosEmpresa(String cdEOTClaro, String cdEOTLD, boolean holding) throws DAOException {
		return getProdutoContratadoPrepagoDAO().pesquisaProdutosContratadosEmpresa(cdEOTClaro, cdEOTLD, holding);
	}
	
	public SccProdutoContratadoPrepagoDAO getProdutoContratadoPrepagoDAO() {
		return produtoContratadoPrepagoDAO;
	}
	
	public void setProdutoContratadoPrepagoDAO(SccProdutoContratadoPrepagoDAO produtoContratadoPrepagoDAO) {
		this.produtoContratadoPrepagoDAO = produtoContratadoPrepagoDAO;
	}
	
	public List<SccProdutoContratadoPrepago> pesquisaAcordosPrePagoEmpresa(String cdEOTClaro, String cdEOTLD, String cdProdutoPrepago,boolean holding) throws DAOException {
		return getProdutoContratadoPrepagoDAO().pesquisaAcordosPrePagoEmpresa(cdEOTClaro, cdEOTLD, cdProdutoPrepago, holding);
	}
	
	public SccProdutoPrepago getProdutoById(String cdProdutoPrepago) throws DAOException {
		return getProdutoPrepagoDAO().getByPk(cdProdutoPrepago, SccProdutoPrepago.class);
	}
	
	public SccProdutoPrepagoDAO getProdutoPrepagoDAO() {
		return produtoPrepagoDAO;
	}
	
	public void setProdutoPrepagoDAO(SccProdutoPrepagoDAO produtoPrepagoDAO) {
		this.produtoPrepagoDAO = produtoPrepagoDAO;
	}
	
	public SccContratoCobillingDAO getContratoCobillingDAO() {
		return contratoCobillingDAO;
	}
	
	public void setContratoCobillingDAO(SccContratoCobillingDAO contratoCobillingDAO) {
		this.contratoCobillingDAO = contratoCobillingDAO;
	}
	
	public List<SccContratoCobillingSearchView> pesquisaContratosOperadoras(String cdEotClaro, String cdEotLD) throws DAOException {
		return getContratoCobillingDAO().pesquisaContratosOperadoras(cdEotClaro, cdEotLD);
	}
	
	public SccContratoCobilling getByPk(SccContratoCobillingPK pk) throws DAOException {
		return getContratoCobillingDAO().getByPk(pk, SccContratoCobilling.class);
	}
	
	@Transactional
	public void create(SccContratoCobilling entity) throws DAOException {
		getContratoCobillingDAO().create(entity);
	}
	
	@Transactional
	public void update(SccContratoCobilling entity) throws DAOException {
		getContratoCobillingDAO().update(entity);
	}
	
	@Transactional
	public void delete(SccContratoCobilling entity) throws DAOException {
		getContratoCobillingDAO().delete(entity);
	}
	
}
