package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccArquivoCredito;

public interface SccArquivoCreditoDAO extends BasicDAO<SccArquivoCredito> {

	public List<SccArquivoCredito> pesquisaArquivosCredito(String origem,String status,Date dataInicial,Date dataFinal) throws DAOException;
	
}
