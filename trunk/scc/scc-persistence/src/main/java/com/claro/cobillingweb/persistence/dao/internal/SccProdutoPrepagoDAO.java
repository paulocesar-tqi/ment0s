package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;

public interface SccProdutoPrepagoDAO extends BasicDAO<SccProdutoPrepago> {

	/**
	 * Pesquisa produtos pré-pago acordados entre operadoras.
	 * @param cdEOTClaro
	 * @param cdEOTLD
	 * @return
	 * @throws DAOException
	 */
	public List<SccProdutoPrepago> pesquisaProdutosOperadoras(String cdEOTClaro,String cdEOTLD) throws DAOException;
	public Integer proxSequence() throws DAOException;
}