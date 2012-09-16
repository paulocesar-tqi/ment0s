package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccParamProcessoDAO;
import com.claro.cobillingweb.persistence.entity.SccParamProcesso;
import com.claro.cobillingweb.persistence.utils.DateUtils;

public class SccParamProcessoDAOImpl extends HibernateBasicDAOImpl<SccParamProcesso> implements SccParamProcessoDAO {

	 
	@SuppressWarnings({ "unchecked" })
	public List<SccParamProcesso> getAll() throws DAOException {		
		List<SccParamProcesso> list = new ArrayList<SccParamProcesso>();
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccParamProcesso.class);
			criteria.setProjection(Projections.distinct(Projections.property("id.cdProcesso")));
			list = (List<SccParamProcesso>) criteria.list();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccParamProcessoDAO.getAll");
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listarProcesso()throws DAOException {
		
		List<String> lstStr = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccParamProcesso.class);
			criteria.setProjection(Projections.distinct(Projections.property("id.cdProcesso")));
			lstStr = (List<String>)criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccParamProcessoDAO.getAll");
		}
		return lstStr;
	}

	 
	@SuppressWarnings("unchecked")
	public List<SccParamProcesso> pesquisaRepassesProcessados(String idProcesso,int max) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccParamProcesso.class);
			criteria.add(Restrictions.eq("id.cdProcesso", idProcesso));
			criteria.add(Restrictions.eq("cdTipoParametro", "LOADED"));
			if (max > 0)
				criteria.setMaxResults(max);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccParamProcessoDAO.pesquisaRepassesAgendados");
			}
	}

	 
	@SuppressWarnings("unchecked")
	public List<SccParamProcesso> pesquisaRepassesProcessando(String idProcesso,int max) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccParamProcesso.class);
			criteria.add(Restrictions.eq("id.cdProcesso", idProcesso));
			criteria.add(Restrictions.eq("cdTipoParametro", "LOADING"));
			if (max > 0)
				criteria.setMaxResults(max);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccParamProcessoDAO.pesquisaRepassesAgendados");
			}
	}

	 
	@SuppressWarnings("unchecked")
	public List<SccParamProcesso> pesquisaRepassesAgendados(String idProcesso) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccParamProcesso.class);
			criteria.add(Restrictions.eq("id.cdProcesso", idProcesso));
			criteria.add(Restrictions.eq("cdTipoParametro", "TOLOAD"));			
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccParamProcessoDAO.pesquisaRepassesAgendados");
			}		
	}

	 
	public List<SccParamProcesso> pesquisaRepassesProcessados(int max,String cdEOT, Long cdPeriodo, Long mes) throws DAOException {
		return null;
	}

	 
	@SuppressWarnings("unchecked")
	public Long getProximoValorSequence() throws DAOException {
		try {
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("SELECT SEQ_SCC_PARAM_PROCESSO.NEXTVAL FROM DUAL");
			List<BigDecimal> sequence = query.list();
			if ((sequence != null) && (sequence.size() > 0))
				return sequence.get(0).longValue();
			else
				throw new DAOException("Sequence SEQ_SCC_PARAM_PROCESSO retornou nula");
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccParamProcessoDAO.getProximoValorSequence");
			}
	}

	 
	public void deleteSolicitacaoRepasse(String noRequisicao) throws DAOException {
		try {
			String deleteHql = "DELETE SccParamProcesso s WHERE s.id.nmParametro = :noRequisicao AND s.cdTipoParametro = 'TOLOAD' AND s.id.cdProcesso = 'REPASSE'";
			Query query = getSessionFactory().getCurrentSession().createQuery(deleteHql);
			query.setString("noRequisicao", noRequisicao);
			query.executeUpdate();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccParamProcessoDAO.deleteSolicitacaoRepasse");
			}
		
	}

	 
	public void deleteSolicitacaoRepassePre(String noRequisicao) throws DAOException {
		try {
			String deleteHql = "DELETE SccParamProcesso s WHERE s.id.nmParametro = :noRequisicao AND s.txValorParametro = 'TOLOAD' AND s.id.cdProcesso = 'FECHAMENTO_PRE'";
			Query query = getSessionFactory().getCurrentSession().createQuery(deleteHql);
			query.setString("noRequisicao", noRequisicao);
			query.executeUpdate();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccParamProcessoDAO.deleteSolicitacaoRepassePre");
			}
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<SccParamProcesso> pesquisaProcessoParametros(
			String codProcesso, Date dtInicio, Date dtFim) throws DAOException {
		List<SccParamProcesso> list = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccParamProcesso.class);
			criteria.add(Restrictions.eq("id.cdProcesso", codProcesso));
			criteria.add(Restrictions.ge("dtAtualizacao", DateUtils.lowDateTime(dtInicio)));
			criteria.add(Restrictions.le("dtAtualizacao", DateUtils.highDateTime(dtFim)));
			criteria.addOrder(Order.asc("dtAtualizacao"));
			list = (List<SccParamProcesso>) criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccParamProcessoDAO.pesquisaProcessoParametros");
		}
		return list;
	}
	

	
	@SuppressWarnings("unchecked")
	public List<SccParamProcesso> pesquisaProcessoParametrosByData(Date dtInicio, Date dtFim) throws DAOException{
		List<SccParamProcesso> list = null;
		try {
			
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccParamProcesso.class);
			criteria.add(Restrictions.ge("dtAtualizacao", DateUtils.lowDateTime(dtInicio)));
			criteria.add(Restrictions.le("dtAtualizacao", DateUtils.highDateTime(dtFim)));
			criteria.addOrder(Order.asc("dtAtualizacao"));
			
			list = (List<SccParamProcesso>) criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccParamProcessoDAO.pesquisaProcessoParametros");
		}
		return list;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SccParamProcesso> listarTodos() throws DAOException {
		List<SccParamProcesso> list = null;
		try {
			String sql = "select distinct pp.id from SccParamProcesso pp";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			list =(List<SccParamProcesso>) query.list();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccParamProcessoDAO.pesquisaProcessoParametros");
		}
		return list;
	}
	
	
}
