package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;
import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;

public interface SccProdutoCobillingDAO extends BasicDAO<SccProdutoCobilling>{

	/**
	 * Pesquisa produtos considerando somente a operadora LD.
	 * @param cdEOT
	 * @return
	 * @throws DAOException
	 */
	public List<SccProdutoCobilling> pesquisaProdutosOperadoraLD(String cdEOT) throws DAOException;
	
	public List<SccComposicaoProduto> carregaComposicaoProduto(Long cdProdutoCobilling,Long cdItemCobilling) throws DAOException;
	
	/**
	 * Pesquisa produtos considerando a operadora LD e a operadora Claro.
	 * @param cdEOTLD
	 * @param cdEOTClaro
	 * @return
	 * @throws DAOException
	 */
	public List<SccProdutoCobilling> pesquisaProdutosOperadoras(String cdEOTLD,String cdEOTClaro) throws DAOException;
	
	
	public List<SccComposicaoProduto> carregaComposicaoProduto(Long cdProdutoCobilling) throws DAOException;
	
	List<SccProdutoCobilling> pesquisaProdutosOperadoraLDTodas() throws DAOException;
	
}
