package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPrepago;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;

public interface SccProdutoContratadoPrepagoDAO extends BasicDAO<SccProdutoContratadoPrepago> {

	/**
	 * Pesquisa os produtos de prépago que fazem parte de contrato(s) entre operadora/holding Claro e operadora LD.
	 * @param cdEOTClaro Cod. EOT da operadora ou holding Claro.
	 * @param cdEOTLD Cod. EOT da operadora LD.
	 * @param holding Se TRUE e se a operadora Claro informada for uma holding , o resultado levará em conta todas as operadoras sob a holding.
	 * @return Lista de produtos.
	 * @throws DAOException
	 */
	public List<SccProdutoPrepago> pesquisaProdutosContratadosEmpresa(String cdEOTClaro,String cdEOTLD,boolean holding) throws DAOException;
	
	/**
	 * Pesquisa os acordos entre empresas de acordo com o produto.
	 * @param cdEOTClaro
	 * @param cdEOTLD
	 * @param cdProdutoPrepago
	 * @return
	 * @throws DAOException
	 */
	public List<SccProdutoContratadoPrepago> pesquisaAcordosPrePagoEmpresa(String cdEOTClaro,String cdEOTLD,String cdProdutoPrepago,boolean holding) throws DAOException;
	
}
