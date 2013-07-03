package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccContratoCobilling;
import com.claro.cobillingweb.persistence.entity.SccContratoCobillingPK;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPrepago;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.service.ServiceException;
import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;
import com.claro.cobillingweb.persistence.view.SccProdutoContratadoView;

public interface ContratoPrePagoService {

	public List<SccProdutoPrepago> pesquisaProdutosContratadosEmpresa(String cdEOTClaro,String cdEOTLD,boolean holding) throws DAOException;
	
	public List<SccProdutoContratadoPrepago> pesquisaAcordosPrePagoEmpresa(String cdEOTClaro, String cdEOTLD, String cdProdutoPrepago,boolean holding) throws DAOException;
	
	public SccProdutoPrepago getProdutoById(String cdProdutoPrepago) throws DAOException;
	
	public List<SccContratoCobillingSearchView> pesquisaContratosOperadoras(String cdEotClaro, String cdEotLD) throws DAOException;
	
	public SccContratoCobilling getByPk(SccContratoCobillingPK pk) throws DAOException, ServiceException;
	
	public void create(SccContratoCobilling entity) throws DAOException, ServiceException;
	
	public void update(SccContratoCobilling entity) throws DAOException, ServiceException;
	
	public void delete(SccContratoCobilling entity) throws DAOException, ServiceException;

	List<SccContratoCobillingSearchView> pesquisaContratosOperadorasByFiltro(
			String cdEotClaro, String cdEotLD, String cdTipo)
			throws DAOException;

	SccContratoCobilling findByFiltro(SccContratoCobillingPK pk)
			throws DAOException;

	void excluir(SccContratoCobillingPK pk) throws DAOException,
			ServiceException;

	List<SccContratoCobillingSearchView> pesquisarProdutosContratos(
			String cdEotClaro, String cdEotLD) throws DAOException;

	List<SccProdutoContratadoView> pesquisarProdutoContatatado(
			String cdEotClaro, String cdEotLd, Date dtInicioContrato)
			throws DAOException;
}
