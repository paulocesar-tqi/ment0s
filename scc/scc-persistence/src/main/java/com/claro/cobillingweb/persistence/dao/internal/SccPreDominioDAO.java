package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccPreDominio;

public interface SccPreDominioDAO extends BasicDAO<SccPreDominio> {

	public List<SccPreDominio> pesquisaPorTipoDominio(String tipo) throws DAOException;
	
	public List<SccPreDominio> getTipos() throws DAOException;
	
}
