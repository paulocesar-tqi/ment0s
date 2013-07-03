package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernateImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPagamentoRepasseDAO;
import com.claro.cobillingweb.persistence.entity.SccPagamentoRepasse;
import com.claro.cobillingweb.persistence.service.ServiceException;

@Repository
public class SccPagamentoRepasseDAOImpl extends FwjBaseDaoHibernateImpl<SccPagamentoRepasse, String> implements SccPagamentoRepasseDAO {

	 
	public List<SccPagamentoRepasse> getAll() throws DAOException {
		throw new UnsupportedOperationException();
	}

	 
	@SuppressWarnings("unchecked")
	public SccPagamentoRepasse getPagamentoByRepasse(Long nuRepasse,String cdEOTClaro,String cdEOTLD) throws DAOException {
		SccPagamentoRepasse pagamentoRepasse = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccPagamentoRepasse.class);
			criteria.add(Restrictions.eq("id.nuRepasse", nuRepasse));
			if(cdEOTClaro != null && !cdEOTClaro.equals("*")){
				criteria.add(Restrictions.eq("id.cdEotHolding", cdEOTClaro));
			}
			
			criteria.add(Restrictions.eq("id.cdEotLd", cdEOTLD));
			List<SccPagamentoRepasse> found = criteria.list();
			if ((found != null) && (found.size() > 0))
				{
				pagamentoRepasse = found.get(0);
				}
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
		return pagamentoRepasse;
	}
	
	private String gerarHql(){
		String hql = 	"update SccPagamentoRepasse set fgContabAutomatica = :status where id.cdEotLd = :cdEotLd and id.cdEotHolding = :cdEotHolding and id.dtReferencia = :dtReferencia and id.cdTipoContrato = :cdTipoContrato and id.nuRepasse = :nuRepasse ";
		return hql;
	}
	
	@Override
	public void  updateEntity(SccPagamentoRepasse entity) throws DAOException, ServiceException{
		
		try{
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHql());
			query.setString("status", entity.getFgContabAutomatica());
			query.setString("cdEotLd", entity.getId().getCdEotLd());
			query.setString("cdEotHolding", entity.getId().getCdEotHolding());
			query.setDate("dtReferencia", entity.getId().getDtReferencia());
			query.setString("cdTipoContrato", entity.getId().getCdTipoContrato());
			query.setLong("nuRepasse", entity.getId().getNuRepasse());
			
			query.executeUpdate();
		} catch (Exception e){
		throw new DAOException(e.getMessage(), e);
		}
	}
	
	@Override
	public void updatePagamentoRepasse(Long nuRepasse, String status) throws DAOException, ServiceException{
		
		try{
			
			Query query = getSessionFactory().getCurrentSession().createQuery("update SccPagamentoRepasse set fgContabAutomatica = :status where id.nuRepasse = :nuRepasse ");
			query.setParameter("status", status);
			query.setParameter("nuRepasse", nuRepasse);
			
			query.executeUpdate();
		} catch (Exception e){
		throw new DAOException(e.getMessage(), e);
		}

	}

	
	
}
