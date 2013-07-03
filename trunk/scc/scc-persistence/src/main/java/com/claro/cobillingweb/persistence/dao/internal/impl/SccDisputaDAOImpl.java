/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccDisputaDAO;
import com.claro.cobillingweb.persistence.entity.SccDisputa;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputa;

/**
 * @author vagner.souza
 *
 */
public class SccDisputaDAOImpl extends HibernateBasicDAOImpl<SccDisputa> implements	SccDisputaDAO {
	
	private static final String OPERADORALD = "C"; 

	@Override
	public List<SccDisputa> getAll() throws DAOException {
		
		return null;
	}

	
	@Override
	public Collection<SccDisputa> pesquisarDisputaByFiltro(	SccFiltroDisputa filtro) throws DAOException {
		
/*		Collection<SccDisputa> listDisputa = null;
		
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHql(filtro));
			query.setDate("dtinicial", filtro.montarDataInicial());
			query.setDate("dtfinal", filtro.montarDataFinal());
			
			if(filtro.getCdEOTLD() != null && !filtro.getCdEOTLD().equalsIgnoreCase(CobillingConstants.TODOS)){
				query.setString("cdEot", filtro.getCdEOTLD());
			}
			
			listDisputa =  query.list();
			
		} catch (Exception e){
			throw new DAOException(e.getMessage(), "SccRepasseDAO.carregaDemonstrativoHolding");
		}		
		
		return listDisputa;
*/	
		return pesquisarDiputa(filtro);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<SccDisputa> pesquisarDiputa(SccFiltroDisputa filtro) throws DAOException{
		
		Collection<SccDisputa> listDisputa = null;
		
		try {
			
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccDisputa.class);
			criteria.createAlias("sccOperadora", "sccOperadora");
			criteria.createAlias("sccDisputaDetalhes", "sccDisputaDetalhes");

			criteria.add(Restrictions.ge("dtEvento", filtro.montarDataInicial()));
			criteria.add(Restrictions.le("dtEvento", filtro.montarDataFinal()));
			criteria.add(Restrictions.eq("sccOperadora.cdTipoServico", OPERADORALD));
			
			if(!filtro.getCdEOTLD().equals(CobillingConstants.TODOS)){
				criteria.add(Restrictions.eq("sccOperadora.cdEot", filtro.getCdEOTLD()));
			}

			listDisputa = (Collection<SccDisputa>)criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccRepasseDAO.carregaDemonstrativoHolding");
		}
		
		return listDisputa;
		
		
	}
	
	@SuppressWarnings("unused")
	private String gerarHql(SccFiltroDisputa filtro){
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("select d.sqDisputa, d.sccOperadora, d.cdStatusDisputa, d.dtEvento, d.dtProtocoloClaro, " );
		hql.append("	  d.dtProtocoloLd, d.txComentario, d.txContestacao, d.cdIdentificacaoAtaAcordo, d.cdIdentificacaoCartaLd, ");
		hql.append("     d.noResponsavelAprovacao, d.noResponsavelLd, d.vlContestado, d.dtRepasseContestacao, d.dtPrazoContestacao, ");
		hql.append("     d.dtPrazoResposta, d.dtAnalise, d.inRiscoDisputa, d.txAnalise, d.dtAprovacao, d.noResponsavelAprovacao, ");
		hql.append("     d.vlProposto, d.dtProvisao, d.vlProvisao, d.txResposta, d.txConciliacao, d.dtConflito, d.dtPrazoConflito, ");
		hql.append("     d.dtReclamacaoAnatel, d.dtPrevisaoConclusaoRa, d.dtAcaoJudicial, d.dtPrevisaoConclusaoAj, d.dtAcordo, ");
		hql.append("     d.cdIdentificacaoAtaAcordo, d.vlAcordo, d.qtParcelasAcordo, d.dtPagamentoAcordo, d.vlDiferencaContestado, ");
		hql.append("     d.dtRepasseDisputa, d.vlAcertoConciliacao, d.vlSaldoRepassar, d.dtTermoQuitacao, d.cdIdentificacaoTrmQuitacao, ");
		hql.append("     d.dtTerminoDisputa, d.dtCriacao, d.dtAtualizacao, d.cdUsuarioManut ");
		hql.append("from SccDisputa d " );
		hql.append("join d.sccOperadora as o ");
		hql.append("join d.sccDisputaDetalhes as dd ");
		hql.append("where d.dtEvento >= :dtinicial " );
		hql.append(" and  d.dtEvento <= :dtfinal ");
		if(StringUtils.isNotEmpty(filtro.getCdEOTLD()) && filtro.getCdEOTLD().equals(CobillingConstants.TODOS)){
			hql.append(" and d.sccOperadora.cdEot = (select o.cdEot from SccOperadora o where o.cdTipoServico = " + OPERADORALD + ")");
		}else{
			hql.append(" and d.sccOperadora.cdEot = :cdEot ");
		}
		
		hql.append("group by d.sqDisputa, d.sccOperadora, d.cdStatusDisputa, d.dtEvento, d.dtProtocoloClaro, " );
		hql.append(" d.dtProtocoloLd, d.txComentario, d.txContestacao, d.cdIdentificacaoAtaAcordo, d.cdIdentificacaoCartaLd, ");
		hql.append(" d.noResponsavelAprovacao, d.noResponsavelLd, d.vlContestado, d.dtRepasseContestacao, d.dtPrazoContestacao, ");
		hql.append(" d.dtPrazoResposta, d.dtAnalise, d.inRiscoDisputa, d.txAnalise, d.dtAprovacao, d.noResponsavelAprovacao,  ");
		hql.append(" d.vlProposto, d.dtProvisao, d.vlProvisao, d.txResposta, d.txConciliacao, d.dtConflito, d.dtPrazoConflito,  ");
		hql.append(" d.dtReclamacaoAnatel, d.dtPrevisaoConclusaoRa, d.dtAcaoJudicial, d.dtPrevisaoConclusaoAj, d.dtAcordo,  "); 
		hql.append(" d.cdIdentificacaoAtaAcordo, d.vlAcordo, d.qtParcelasAcordo, d.dtPagamentoAcordo, d.vlDiferencaContestado,  ");
		hql.append(" d.dtRepasseDisputa, d.vlAcertoConciliacao, d.vlSaldoRepassar, d.dtTermoQuitacao, d.cdIdentificacaoTrmQuitacao,  ");
		hql.append(" d.dtTerminoDisputa, d.dtCriacao, d.dtAtualizacao, d.cdUsuarioManut ");

		return hql.toString();
		
	}
	
	private String gerarSql(){
		StringBuilder sql = new StringBuilder();
		sql.append("Select d from SccDisputa d ");
		sql.append("inner join fetch d.sccOperadora as o ");
		sql.append("inner join fetch d.sccDisputaDetalhes as dd ");
		sql.append("where d.sqDisputa = :sqDisputa ");
		return sql.toString();
	}
	
	@Override
	public SccDisputa pesquisarBySqDisputa(Long sqDisputa) throws DAOException{
		
		SccDisputa entity = null;
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarSql());
			query.setLong("sqDisputa", sqDisputa);
			entity = (SccDisputa) query.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccDisputaDAO.editarEntity");
		}
		
		return entity;
	}

/*	
	@Override
	public SccDisputa pesquisarBySqDisputa(Long sqDisputa) throws DAOException{
		
		SccDisputa entity = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccDisputa.class);
			criteria.createAlias("sccOperadora", "sccOperadora");
			criteria.createAlias("sccDisputaDetalhes", "sccDisputaDetalhes");

			criteria.add(Restrictions.eq("sqDisputa", sqDisputa));
			
			entity = (SccDisputa) criteria.uniqueResult();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccDisputaDAO.editarEntity");
		}
		return entity;
	}
	

*/
	
	
	


}
