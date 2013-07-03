package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioFinanceiroDAO;
import com.claro.cobillingweb.persistence.view.SccFinanceiroView;

public class SccRelatorioFinanceiroDAOImp extends HibernateBasicDAOImpl<SccFinanceiroView> implements SccRelatorioFinanceiroDAO {

	@Override
	public List<SccFinanceiroView> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


}
