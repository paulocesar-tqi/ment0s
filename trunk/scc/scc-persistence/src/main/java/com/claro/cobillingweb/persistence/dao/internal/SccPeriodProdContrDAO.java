package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPeriodProdContr;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPK;

public interface SccPeriodProdContrDAO extends BasicDAO<SccPeriodProdContr>{

	public List<SccPeriodProdContr> carregaPeriodicidadesContrato(SccProdutoContratadoPK pk) throws DAOException;
	
}
