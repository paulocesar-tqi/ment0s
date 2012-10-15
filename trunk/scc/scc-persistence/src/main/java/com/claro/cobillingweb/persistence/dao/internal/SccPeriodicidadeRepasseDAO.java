package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;

public interface SccPeriodicidadeRepasseDAO extends BasicDAO<SccPeriodicidadeRepasse>{

	
	/**
	 * Pesquisa as periodicidades para um produto dentro do contrato de uma operadora LD.
	 * @param cdEOT
	 * @param cdContratoAcordado
	 * @return
	 * @throws DAOException
	 */
	public List<SccPeriodicidadeRepasse> pesquisaPeriodicidadeRepasse(String cdEOT,Long cdProduto) throws DAOException;
	
	List<SccPeriodicidadeRepasse> pesquisaPeriodicidadeRepasseByProduto(Long cdProduto) throws DAOException;
	
}
