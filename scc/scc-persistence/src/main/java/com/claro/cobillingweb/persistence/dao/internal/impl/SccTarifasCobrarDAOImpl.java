package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernateImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccTarifasCobrarDAO;
import com.claro.cobillingweb.persistence.entity.SccPreItemTarifaAcb;
import com.claro.cobillingweb.persistence.entity.SccPreTarifaAcb;

@Repository
public class SccTarifasCobrarDAOImpl extends FwjBaseDaoHibernateImpl<SccPreTarifaAcb, Serializable> implements SccTarifasCobrarDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SccPreTarifaAcb> findByOperadora(String cdEotLd) throws DAOException{
		
		List<SccPreTarifaAcb> list = null;
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHql());
			query.setParameter("cdEotLd", cdEotLd);
			list = (List<SccPreTarifaAcb>) query.list();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccTarifasCobrarDAO.findByOperadora");
		}
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SccPreItemTarifaAcb> findByIdTarifa(Long idTarifa)throws DAOException{
		
		List<SccPreItemTarifaAcb> list = null;
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHqlItems());
			query.setParameter("sqPreTarifaAcb", idTarifa);
			list = (List<SccPreItemTarifaAcb>) query.list();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccTarifasCobrarDAO.findByIdTarifa");
		}
		return list;
		
		
	}
	
	@Override
	public SccPreTarifaAcb findById(Long idTarifa) throws DAOException{
		
		SccPreTarifaAcb entity = null;
		try{
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHqlTarifa());
			query.setParameter("sqPreTarifaAcb", idTarifa);
			entity = (SccPreTarifaAcb) query.list().get(0);
		}catch(Exception e){
			throw new DAOException(e.getMessage(), "SccTarifasCobrarDAO.findById");
		}
		return entity;
		
	}
	
	private String gerarHqlTarifa(){
		StringBuilder hql = new StringBuilder();
		hql.append("select tc from SccPreTarifaAcb tc ");
		hql.append("join fetch tc.listItems it ");
		hql.append("where tc.sqPreTarifaAcb = :sqPreTarifaAcb ");
		//hql.append("order by tc.nmPlano, tc.dtIniVigencia, tc.cdSoc ");
		return hql.toString();
		
	}
	
	private String gerarHqlItems(){

		StringBuilder hql = new StringBuilder();
		hql.append("select tc.sqPreTarifaAcb from SccPreTarifaAcb tc ");
		hql.append("join fetch tc.listItems it ");
		hql.append("where tc.sqPreTarifaAcb = :sqPreTarifaAcb ");
		//hql.append("order by tc.nmPlano, tc.dtIniVigencia, tc.cdSoc ");
		return hql.toString();
		
	}
	
	private String gerarHql(){
		
		StringBuilder hql = new StringBuilder();
		hql.append("select distinct tc from SccPreTarifaAcb tc ");
		hql.append("where tc.cdEotLd = :cdEotLd ");
		hql.append("order by tc.nmPlano, tc.dtIniVigencia, tc.cdSoc ");
		return hql.toString();
	}


}
