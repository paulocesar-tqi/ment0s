package com.claro.cobillingweb.persistence.dao.internal;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccCtlParalizacaoPlat;

public interface SccCtlParalizacaoPlatDAO extends BasicDAO<SccCtlParalizacaoPlat> {

	public List<SccCtlParalizacaoPlat> pesquisaParalizacoes(SccCtlParalizacaoPlat filtro,Date dataInicial,Date dataFinal) throws DAOException;
	
}
