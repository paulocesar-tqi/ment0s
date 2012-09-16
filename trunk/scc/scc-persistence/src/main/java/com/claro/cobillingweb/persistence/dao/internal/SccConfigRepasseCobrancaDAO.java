package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccConfigRepasseCobranca;
import com.claro.cobillingweb.persistence.entity.SccProdutoContratadoPK;

public interface SccConfigRepasseCobrancaDAO extends BasicDAO<SccConfigRepasseCobranca>{

	public List<SccConfigRepasseCobranca> carregaConfiguracaoRepasse(SccProdutoContratadoPK pk) throws DAOException;
	
}
