/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccContestacaoPrePagoDAO;
import com.claro.cobillingweb.persistence.entity.SccContestacaoPrePago;
import com.claro.cobillingweb.persistence.filtro.SccFiltroContestacaoPrePago;

/**
 * @author vagner.souza
 *
 */
public class SccContestacaoPrePagoDAOImpl extends HibernateBasicDAOImpl<SccContestacaoPrePago>
		implements SccContestacaoPrePagoDAO {

	@Override
	public List<SccContestacaoPrePago> getAll() throws DAOException {
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<SccContestacaoPrePago> pesquisarByFiltro(	SccFiltroContestacaoPrePago filtro) throws DAOException {
		
		Collection<SccContestacaoPrePago> list = null;
		
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHql(filtro));
			query.setDate("dtinicial", filtro.montarDataInicial());
			query.setDate("dtfinal", filtro.montarDataFinal());
			
			if(StringUtils.isNotEmpty(filtro.getCdEOTLD()) && !filtro.getCdEOTLD().equals(CobillingConstants.TODOS)){
				query.setString("cdEot", filtro.getCdEOTLD());
			}

			list = (Collection<SccContestacaoPrePago>) query.list();
			
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccContestacaoDAO.pesquisarByFiltro");
		}
		
		return list;
	}
	
	private String gerarHqlEdit(Long sqContestacaoPrePago){
		StringBuilder hql = new StringBuilder();
		
		hql.append("select distinct cpp ");
		hql.append("from SccContestacaoPrePago cpp, SccPreFechamento pf  ");
		hql.append("join cpp.sccOperadora as op ");
		hql.append("left join cpp.sccContestacaoDetalhePres as list ");
		hql.append("where cpp.sqContestacaoPrePago >= :sqContestacaoPrePago " );
		return hql.toString();
	}
	
	private String gerarHql(SccFiltroContestacaoPrePago filtro){
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("select distinct cpp ");
		hql.append("from SccContestacaoPrePago cpp, SccPreFechamento pf  " ) ;
		hql.append("join cpp.sccOperadora as op ");
		hql.append("left join cpp.sccContestacaoDetalhePres as list ");
		hql.append("where pf.dtInicialFechamento >= :dtinicial " );
		hql.append(" and  pf.dtInicialFechamento <= :dtfinal ");
		hql.append(" and op.cdTipoServico = 'C' ");
		hql.append(" and  pf.dtInicialFechamento = cpp.dtRepasseContestacao ");
		

		if(StringUtils.isNotEmpty(filtro.getCdEOTLD()) && !filtro.getCdEOTLD().equals(CobillingConstants.TODOS)){
			hql.append(" and op.cdEot = :cdEot ");
		}
		
		hql.append(" and pf.cdStatusFechamento = 'C'" );
		hql.append(" and pf.cdEotLd = op.cdEot ");
		

		return hql.toString();
		
	}
	
	@Override
	public SccContestacaoPrePago findBySqContestacaoPrePago(Long sqContestacaoPrePago)
			throws DAOException {
		
		SccContestacaoPrePago entity = null;
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHqlEdit(sqContestacaoPrePago));
			
			query.setLong("sqContestacaoPrePago", sqContestacaoPrePago);
			
			entity = (SccContestacaoPrePago) query.list().get(0);
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccContestacaoPrePagoDAO.editarEntity");
		}
		return entity;

	}
	




}
