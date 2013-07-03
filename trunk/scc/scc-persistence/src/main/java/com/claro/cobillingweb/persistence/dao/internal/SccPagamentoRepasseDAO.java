package com.claro.cobillingweb.persistence.dao.internal;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernate;
import com.claro.cobillingweb.persistence.entity.SccPagamentoRepasse;
import com.claro.cobillingweb.persistence.service.ServiceException;

public interface SccPagamentoRepasseDAO extends FwjBaseDaoHibernate<SccPagamentoRepasse, String>{

	/**
	 * Pesquisa o pagamento associado ao repasse.
	 * @param nuRepasse Identificador do repasse.
	 * @return
	 * @throws DAOException
	 */
	public SccPagamentoRepasse getPagamentoByRepasse(Long nuRepasse,String cdEOTClaro,String cdEOTLD) throws DAOException;

	void updateEntity(SccPagamentoRepasse entity) throws DAOException,
			ServiceException;

	void updatePagamentoRepasse(Long nuRepasse, String status)
			throws DAOException, ServiceException;
	
}
