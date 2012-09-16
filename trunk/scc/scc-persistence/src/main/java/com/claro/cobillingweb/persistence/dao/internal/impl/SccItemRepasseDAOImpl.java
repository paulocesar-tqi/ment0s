package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccItemRepasseDAO;
import com.claro.cobillingweb.persistence.entity.SccItemRepasse;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;

public class SccItemRepasseDAOImpl extends HibernateBasicDAOImpl<SccItemRepasse> implements SccItemRepasseDAO {

	 
	public List<SccItemRepasse> getAll() throws DAOException {
		try {
			List<SccStatusArquivo> resultList = new ArrayList<SccStatusArquivo>();			
			Query query = getSessionFactory().getCurrentSession().getNamedQuery("SccItemRepasse.GET_ALL");			
			return query.list();		
			} catch (Exception e)
				{
				throw new DAOException(e.getMessage(),"SccItemRepasse.GET_ALL");
				}
		}




}

	

