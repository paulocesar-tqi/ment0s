package com.claro.cobillingweb.persistence.dao.internal;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPagamentoRepasse;

public interface SccPagamentoRepasseDAO extends BasicDAO<SccPagamentoRepasse>{

	/**
	 * Pesquisa o pagamento associado ao repasse.
	 * @param nuRepasse Identificador do repasse.
	 * @return
	 * @throws DAOException
	 */
	public SccPagamentoRepasse getPagamentoByRepasse(Long nuRepasse,String cdEOTClaro,String cdEOTLD) throws DAOException;
	
}
