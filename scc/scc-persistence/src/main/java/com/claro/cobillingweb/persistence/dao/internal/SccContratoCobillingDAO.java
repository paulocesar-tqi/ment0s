package com.claro.cobillingweb.persistence.dao.internal;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.entity.SccContratoCobilling;
import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;

public interface SccContratoCobillingDAO extends BasicDAO<SccContratoCobilling>{

	public List<SccContratoCobillingSearchView> pesquisaContratosOperadoras(String cdEotClaro,String cdEotLD) throws DAOException;
	
}
