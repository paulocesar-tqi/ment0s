package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccMemoDAO;
import com.claro.cobillingweb.persistence.entity.SccMemo;

public class SccMemoDAOImpl extends HibernateBasicDAOImpl<SccMemo> implements SccMemoDAO {

	 
	public List<SccMemo> getAll() throws DAOException {

		return null;
	}

	
}
