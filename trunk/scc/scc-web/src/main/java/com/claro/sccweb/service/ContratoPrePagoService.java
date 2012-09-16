package com.claro.sccweb.service;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccContratoCobilling;
import com.claro.cobillingweb.persistence.entity.SccContratoCobillingPK;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPrepago;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;

public interface ContratoPrePagoService {

	public List<SccProdutoPrepago> pesquisaProdutosContratadosEmpresa(String cdEOTClaro,String cdEOTLD,boolean holding) throws DAOException;
	
	public List<SccProdutoContratadoPrepago> pesquisaAcordosPrePagoEmpresa(String cdEOTClaro, String cdEOTLD, String cdProdutoPrepago,boolean holding) throws DAOException;
	
	public SccProdutoPrepago getProdutoById(String cdProdutoPrepago) throws DAOException;
	
	public List<SccContratoCobillingSearchView> pesquisaContratosOperadoras(String cdEotClaro, String cdEotLD) throws DAOException;
	
	public SccContratoCobilling getByPk(SccContratoCobillingPK pk) throws DAOException;
	
	public void create(SccContratoCobilling entity) throws DAOException;
	
	public void update(SccContratoCobilling entity) throws DAOException;
	
	public void delete(SccContratoCobilling entity) throws DAOException;
}
