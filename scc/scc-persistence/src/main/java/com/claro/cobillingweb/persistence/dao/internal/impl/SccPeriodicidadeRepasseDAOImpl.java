package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPeriodicidadeRepasseDAO;
import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;
import com.claro.cobillingweb.persistence.entity.SccPeriodProdContr;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;

public class SccPeriodicidadeRepasseDAOImpl extends HibernateBasicDAOImpl<SccPeriodicidadeRepasse> implements SccPeriodicidadeRepasseDAO{

	 
	public List<SccPeriodicidadeRepasse> getAll() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPeriodicidadeRepasse.class);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

	 
	public List<SccPeriodicidadeRepasse> pesquisaPeriodicidadeRepasse(String cdEOT, Long cdProduto) throws DAOException {
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery("SELECT a from SccContratoAcordado a where a.operadoraExterna.cdEot = ?");
			query.setMaxResults(1);
			query.setString(0, cdEOT);
			List<SccContratoAcordado> acordados = query.list();
			if (acordados.size() > 0){
			query = 	getSessionFactory().getCurrentSession().createQuery("SELECT DISTINCT r.sccPeriodicidadeRepasse FROM SccPeriodProdContr r where " +
					"r.id.cdContratoCobilling = ? and r.id.cdProdutoCobilling = ?");
			query.setLong(0, acordados.get(0).getId().getCdContratoCobilling());
			query.setLong(1, cdProduto);
			List<SccPeriodProdContr> perCont = query.list();			
			return query.list();
			}else{
				return  new ArrayList<SccPeriodicidadeRepasse>();
				}			
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccPeriodicidadeRepasseDAO.pesquisaPeriodicidadeRepasse");
			}		
	}

	
}
