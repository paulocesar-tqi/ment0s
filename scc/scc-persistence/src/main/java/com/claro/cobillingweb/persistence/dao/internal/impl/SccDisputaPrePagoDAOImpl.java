/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.constants.CobillingConstants;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccDisputaPrePagoDAO;
import com.claro.cobillingweb.persistence.entity.SccDisputaPrePago;
import com.claro.cobillingweb.persistence.filtro.SccFiltroDisputaPrePago;
import com.claro.cobillingweb.persistence.view.SccDisputaPrePagoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author vagner.souza
 *
 */
@Repository
public class SccDisputaPrePagoDAOImpl extends HibernateBasicDAOImpl<SccDisputaPrePago>
		implements SccDisputaPrePagoDAO {
	
	
	private static final String STATUSFECHAMENTO = "C";
	@SuppressWarnings("unused")
	private static final String OPERADORALD = "C"; 
	

	@Override
	public SccDisputaPrePago pesquisarBySqDisputaPrePago(Long sqDisputaPrePago) throws DAOException{
		
		SccDisputaPrePago entity = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccDisputaPrePago.class);
			criteria.createAlias("sccOperadora", "sccOperadora");
			criteria.createAlias("sccDisputaDetalhePrePagos", "sccDisputaDetalhePrePagos");

			criteria.add(Restrictions.eq("sqDisputaPrePago", sqDisputaPrePago));
			
			entity = (SccDisputaPrePago) criteria.uniqueResult();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccDisputaPrePagoDAO.editarEntity");
		}
		return entity;
	}
	
	

	@Override
	public List<SccDisputaPrePago> getAll() throws DAOException {
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<SccDisputaPrePago> pesquisarDisputaPrePago(SccFiltroDisputaPrePago filtro ) throws DAOException {
		
		Collection<SccDisputaPrePago> listDisputaPrePago = null;
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHql(filtro));
			query.setDate("dtinicial", filtro.montarDataInicial());
			query.setDate("dtfinal", filtro.montarDataFinal());
			if(StringUtils.isNotEmpty(filtro.getCdEOTLD()) && !filtro.getCdEOTLD().equals(CobillingConstants.TODOS)){
				query.setString("cdEot", filtro.getCdEOTLD());
			}
			
			listDisputaPrePago = (Collection<SccDisputaPrePago>) query.list();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccDisputaPrePagoDAO.pesquisarDisputaPrePago");
		}
		
		return listDisputaPrePago;
		
	}
	
	private String gerarHql(SccFiltroDisputaPrePago filtro){
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("select distinct dpp ");
		hql.append("from SccDisputaPrePago dpp,  SccPreFechamento pf " ) ;
		hql.append("join dpp.sccOperadora as op ");
		hql.append("join dpp.sccDisputaDetalhePrePagos as list ");
		hql.append("where dpp.dtProtocoloLd >= :dtinicial " );
		hql.append(" and  dpp.dtProtocoloLd <= :dtfinal ");
		hql.append(" and op.cdEot in (select o.cdEot from SccOperadora o where o.cdTipoServico = 'C' ) ");

		if(StringUtils.isNotEmpty(filtro.getCdEOTLD()) && !filtro.getCdEOTLD().equals(CobillingConstants.TODOS)){
			hql.append(" and op.cdEot = :cdEot ");
		}
		
		hql.append(" and pf.cdStatusFechamento = 'C'" );
		hql.append(" and pf.cdEotLd = op.cdEot ");
		//hql.append(" and pf.dtInicialFechamento = dpp.dtRepasseDisputa");

		return hql.toString();
		
	}


	@Override
	public Collection<SccDisputaPrePagoView> pesquisarDisputaPrePagoByFiltro(
			SccFiltroDisputaPrePago filtro) throws DAOException {
		
		Collection<SccDisputaPrePagoView> listDisputaPrePago = null;
		
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			
			NativeSQLViewMapper<SccDisputaPrePagoView> mapper = new NativeSQLViewMapper<SccDisputaPrePagoView>(session, gerarSql(filtro), SccDisputaPrePagoView.class);
			
			montarResultMapper(mapper);
			
			listDisputaPrePago = (Collection<SccDisputaPrePagoView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccDisputaPrePagoDAO.pesquisarDisputaPrePagoByFiltro");
		}
		
		return listDisputaPrePago;
	}
	
	
	private void montarResultMapper(NativeSQLViewMapper<SccDisputaPrePagoView> mapper){
		mapper.addResultMap("sqDisputaPrePago", Long.class);
		mapper.addResultMap("cdAnalistaInputCartaClaro", String.class);
		mapper.addResultMap("cdEotLd", String.class);
		mapper.addResultMap("cdIdentificacaoCartaLd", String.class);
		mapper.addResultMap("dtProtocoloLd", Date.class);
		mapper.addResultMap("noResponsavelCartaLd", String.class);
		mapper.addResultMap("dtRepasseDisputa", Date.class);
		mapper.addResultMap("vlPleito", Double.class);
		mapper.addResultMap("dsPleito", String.class);
		mapper.addResultMap("cdTipoDisputa", String.class);
		mapper.addResultMap("dtDisputa", Date.class);
		mapper.addResultMap("dtPrazoDisputa", Date.class);
		mapper.addResultMap("fgDisputaForaDoPrazo", String.class);
		mapper.addResultMap("dtPrazoResposta", Date.class);
		mapper.addResultMap("fgDisputaSemResposta", String.class);
		mapper.addResultMap("cdStatusDisputa", String.class);
		mapper.addResultMap("cdAnalistaInputCartaClaro", String.class);
		mapper.addResultMap("dtInputCartaClaro", Date.class);
		mapper.addResultMap("inRiscoDisputa", String.class);
		mapper.addResultMap("vlPropostoCartaClaro", Double.class);
		mapper.addResultMap("dtProtocoloCartaClaro", Double.class);
		mapper.addResultMap("cdIdentificacaoCartaClaro", String.class);
		mapper.addResultMap("dsRespostaCartaClaro", String.class);
		mapper.addResultMap("vlProvisaoDisputa", Double.class);
		mapper.addResultMap("txAvaliacaoOperacional", String.class);
		mapper.addResultMap("txAvaliacaoJuridica", String.class);
		mapper.addResultMap("txAvaliacaoRegulatoria", String.class);
		mapper.addResultMap("txComentarioAnalise", String.class);
		mapper.addResultMap("cdAnalistaInputRa", String.class);
		mapper.addResultMap("dtInputRa", Date.class);
		mapper.addResultMap("fgRaAnatel", String.class);
		mapper.addResultMap("dtRaAnatel", Date.class);
		mapper.addResultMap("dtPrevTerminoRaAnatel", Date.class);
		mapper.addResultMap("txComentarioRaAnatel", String.class);
		mapper.addResultMap("cdAnalistaInputAcaoJudic", String.class);
		mapper.addResultMap("fgAcaoJudicial", String.class);
		mapper.addResultMap("dtNotificacaoAcaoJudicial", Date.class);
		mapper.addResultMap("dtPrevTerminoAcaoJudicial", Date.class);
		mapper.addResultMap("txComentarioAcaoJudicial", String.class);
		mapper.addResultMap("cdAnalistaInputAcordo", String.class);
		mapper.addResultMap("dtInputAcordo", Date.class);
		mapper.addResultMap("cdIdentificacaoAtaAcordo", String.class);
		mapper.addResultMap("dtAssinaturaAcordo", Date.class);
		mapper.addResultMap("noResponsavelAcordoLd", String.class);
		mapper.addResultMap("noResponsavelAcordoClaro", String.class);
		mapper.addResultMap("vlAcordado", Double.class);
		mapper.addResultMap("dtProgramadaRepasse", Date.class);
		mapper.addResultMap("vlAcertoConciliacaoClaro", Double.class);
		mapper.addResultMap("vlAcertoConciliacaoLd", Double.class);
		mapper.addResultMap("vlDiferencaVlPleito", Double.class);
		mapper.addResultMap("vlDiferencaVlProposto", Double.class);
		mapper.addResultMap("txComentarioConciliacao", String.class);
		mapper.addResultMap("cdAnalistaInputRepasse", String.class);
		mapper.addResultMap("dtLiberacaoRepasse", Date.class);
		mapper.addResultMap("fgRepasseLiberado", String.class);
		mapper.addResultMap("dtRepasseScc", Date.class);
		mapper.addResultMap("txComentarioRepasse", String.class);
		mapper.addResultMap("dtNotificacaoAcaoJudicial", Date.class);
		mapper.addResultMap("qtdDetalhes", Long.class);
		mapper.addResultMap("qtdRepasses", Long.class);

	}
	
	private String gerarSql(SccFiltroDisputaPrePago filtro){

		StringBuilder sql = new StringBuilder();
		
		sql.append("   SELECT CC.SQ_DISPUTA_PRE_PAGO,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_CARTA_LD,    ");
		sql.append("           CC.DT_INPUT_CARTA_LD,    ");
		sql.append("           CC.CD_EOT_LD,    ");
		sql.append("           CC.CD_IDENTIFICACAO_CARTA_LD,    ");
		sql.append("           CC.DT_PROTOCOLO_LD,    ");
		sql.append("           CC.NO_RESPONSAVEL_CARTA_LD,    ");
		sql.append("           CC.DT_REPASSE_DISPUTA,    ");
		sql.append("           CC.VL_PLEITO,    ");
		sql.append("           CC.DS_PLEITO,    ");
		sql.append("           CC.CD_TIPO_DISPUTA,    ");
		sql.append("           CC.DT_DISPUTA,    ");
		sql.append("           CC.DT_PRAZO_DISPUTA,    ");
		sql.append("           CC.FG_DISPUTA_FORA_DO_PRAZO,    ");
		sql.append("           CC.DT_PRAZO_RESPOSTA,    ");
		sql.append("           CC.FG_DISPUTA_SEM_RESPOSTA,    ");
		sql.append("           CC.CD_STATUS_DISPUTA,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_CARTA_CLARO,    ");
		sql.append("           CC.DT_INPUT_CARTA_CLARO,    ");
		sql.append("           CC.IN_RISCO_DISPUTA,    ");
		sql.append("           CC.VL_PROPOSTO_CARTA_CLARO,    ");
		sql.append("           CC.DT_PROTOCOLO_CARTA_CLARO,    ");
		sql.append("           CC.CD_IDENTIFICACAO_CARTA_CLARO,    ");
		sql.append("           CC.DS_RESPOSTA_CARTA_CLARO,    ");
		sql.append("           CC.VL_PROVISAO_DISPUTA,    ");
		sql.append("           CC.TX_AVALIACAO_OPERACIONAL,    ");
		sql.append("           CC.TX_AVALIACAO_JURIDICA,    ");
		sql.append("           CC.TX_AVALIACAO_REGULATORIA,    ");
		sql.append("           CC.TX_COMENTARIO_ANALISE,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_RA,    ");
		sql.append("           CC.DT_INPUT_RA,    ");
		sql.append("           CC.FG_RA_ANATEL,    ");
		sql.append("           CC.DT_RA_ANATEL,    ");
		sql.append("           CC.DT_PREV_TERMINO_RA_ANATEL,    ");
		sql.append("           CC.TX_COMENTARIO_RA_ANATEL,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_ACAO_JUDIC,    ");
		sql.append("           CC.FG_ACAO_JUDICIAL,    ");
		sql.append("           CC.DT_NOTIFICACAO_ACAO_JUDICIAL,    ");
		sql.append("           CC.DT_PREV_TERMINO_ACAO_JUDICIAL,    ");
		sql.append("           CC.TX_COMENTARIO_ACAO_JUDICIAL,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_ACORDO,    ");
		sql.append("           CC.DT_INPUT_ACORDO,    ");
		sql.append("           CC.CD_IDENTIFICACAO_ATA_ACORDO,    ");
		sql.append("           CC.DT_ASSINATURA_ACORDO,    ");
		sql.append("           CC.NO_RESPONSAVEL_ACORDO_LD,    ");
		sql.append("           CC.NO_RESPONSAVEL_ACORDO_CLARO,    ");
		sql.append("           CC.VL_ACORDADO,    ");
		sql.append("           CC.DT_PROGRAMADA_REPASSE,    ");
		sql.append("           CC.VL_ACERTO_CONCILIACAO_CLARO,    ");
		sql.append("           CC.VL_ACERTO_CONCILIACAO_LD,    ");
		sql.append("           CC.VL_DIFERENCA_VL_PLEITO,    ");
		sql.append("           CC.VL_DIFERENCA_VL_PROPOSTO,    ");
		sql.append("           CC.TX_COMENTARIO_CONCILIACAO,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_REPASSE,    ");
		sql.append("           CC.DT_LIBERACAO_REPASSE,    ");
		sql.append("           CC.FG_REPASSE_LIBERADO,    ");
		sql.append("           CC.DT_REPASSE_SCC,    ");
		sql.append("           CC.TX_COMENTARIO_REPASSE,    ");
		sql.append("           CC.DT_NOTIFICACAO_ACAO_JUDICIAL,    ");
		sql.append("           COUNT(DD.SQ_DISPUTA_PRE_PAGO) AS QTDEDETALHES,    ");
		sql.append("           COUNT(PF.SQ_PRE_FECHAMENTO) AS QTDEREPASSES    ");
		sql.append("      FROM SCC_DISPUTA_PRE_PAGO CC,   ");
		sql.append("                   SCC_DISPUTA_DETALHE_PRE_PAGO DD,   ");
		sql.append("                    (SELECT CD_EOT_LD, DT_INICIAL_FECHAMENTO, SQ_PRE_FECHAMENTO   ");
		sql.append("                     FROM SCC_PRE_FECHAMENTO   ");
		sql.append("                     WHERE NVL(CD_STATUS_FECHAMENTO,' ') = "+ STATUSFECHAMENTO +") PF   ");
		sql.append("   WHERE TO_CHAR(CC.DT_PROTOCOLO_LD,'YYYYMM') = "+ filtro.montarData()  );
		sql.append("      AND CC.SQ_DISPUTA_PRE_PAGO = DD.SQ_DISPUTA_PRE_PAGO(+)   ");
		sql.append("      AND CC.CD_EOT_LD = PF.CD_EOT_LD(+)   ");
		sql.append("      AND TO_CHAR(PF.DT_INICIAL_FECHAMENTO(+),'YYYYMM') = TO_CHAR(CC.DT_REPASSE_DISPUTA,'YYYYMM')   ");
		if(!filtro.getCdEOTLD().equalsIgnoreCase(CobillingConstants.TODOS)){
			sql.append("AND CC.CD_EOT_LD = " + filtro.getCdEOTLD());
		}
		sql.append("   GROUP BY CC.SQ_DISPUTA_PRE_PAGO,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_CARTA_LD,    ");
		sql.append("           CC.DT_INPUT_CARTA_LD,    ");
		sql.append("           CC.CD_EOT_LD,    ");
		sql.append("           CC.CD_IDENTIFICACAO_CARTA_LD,    ");
		sql.append("           CC.DT_PROTOCOLO_LD,    ");
		sql.append("           CC.NO_RESPONSAVEL_CARTA_LD,    ");
		sql.append("           CC.DT_REPASSE_DISPUTA,    ");
		sql.append("           CC.VL_PLEITO,    ");
		sql.append("           CC.DS_PLEITO,    ");
		sql.append("           CC.CD_TIPO_DISPUTA,    ");
		sql.append("           CC.DT_DISPUTA,    ");
		sql.append("           CC.DT_PRAZO_DISPUTA,    ");
		sql.append("           CC.FG_DISPUTA_FORA_DO_PRAZO,    ");
		sql.append("           CC.DT_PRAZO_RESPOSTA,    ");
		sql.append("           CC.FG_DISPUTA_SEM_RESPOSTA,    ");
		sql.append("           CC.CD_STATUS_DISPUTA,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_CARTA_CLARO,    ");
		sql.append("           CC.DT_INPUT_CARTA_CLARO,    ");
		sql.append("           CC.IN_RISCO_DISPUTA,    ");
		sql.append("           CC.VL_PROPOSTO_CARTA_CLARO,    ");
		sql.append("           CC.DT_PROTOCOLO_CARTA_CLARO,    ");
		sql.append("           CC.CD_IDENTIFICACAO_CARTA_CLARO,    ");
		sql.append("           CC.DS_RESPOSTA_CARTA_CLARO,    ");
		sql.append("           CC.VL_PROVISAO_DISPUTA,    ");
		sql.append("           CC.TX_AVALIACAO_OPERACIONAL,    ");
		sql.append("           CC.TX_AVALIACAO_JURIDICA,    ");
		sql.append("           CC.TX_AVALIACAO_REGULATORIA,    ");
		sql.append("           CC.TX_COMENTARIO_ANALISE,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_RA,    ");
		sql.append("           CC.DT_INPUT_RA,    ");
		sql.append("           CC.FG_RA_ANATEL,    ");
		sql.append("           CC.DT_RA_ANATEL,    ");
		sql.append("           CC.DT_PREV_TERMINO_RA_ANATEL,    ");
		sql.append("           CC.TX_COMENTARIO_RA_ANATEL,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_ACAO_JUDIC,    ");
		sql.append("           CC.FG_ACAO_JUDICIAL,    ");
		sql.append("           CC.DT_NOTIFICACAO_ACAO_JUDICIAL,    ");
		sql.append("           CC.DT_PREV_TERMINO_ACAO_JUDICIAL,    ");
		sql.append("           CC.TX_COMENTARIO_ACAO_JUDICIAL,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_ACORDO,    ");
		sql.append("           CC.DT_INPUT_ACORDO,    ");
		sql.append("           CC.CD_IDENTIFICACAO_ATA_ACORDO,    ");
		sql.append("           CC.DT_ASSINATURA_ACORDO,    ");
		sql.append("           CC.NO_RESPONSAVEL_ACORDO_LD,    ");
		sql.append("           CC.NO_RESPONSAVEL_ACORDO_CLARO,    ");
		sql.append("           CC.VL_ACORDADO,    ");
		sql.append("           CC.DT_PROGRAMADA_REPASSE,    ");
		sql.append("           CC.VL_ACERTO_CONCILIACAO_CLARO,    ");
		sql.append("           CC.VL_ACERTO_CONCILIACAO_LD,    ");
		sql.append("           CC.VL_DIFERENCA_VL_PLEITO,    ");
		sql.append("           CC.VL_DIFERENCA_VL_PROPOSTO,    ");
		sql.append("           CC.TX_COMENTARIO_CONCILIACAO,    ");
		sql.append("           CC.CD_ANALISTA_INPUT_REPASSE,    ");
		sql.append("           CC.DT_LIBERACAO_REPASSE,    ");
		sql.append("           CC.FG_REPASSE_LIBERADO,    ");
		sql.append("           CC.DT_REPASSE_SCC,    ");
		sql.append("           CC.TX_COMENTARIO_REPASSE    ");		
		
		return sql.toString();
	}


	@Override
	public SccDisputaPrePago findBySqDisputaPrePago(Long sqDisputaPrePago) throws DAOException{
		
		SccDisputaPrePago entity = null;
		
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHql());
			query.setLong("sqDisputaPrePago", sqDisputaPrePago);
			entity = (SccDisputaPrePago) query.uniqueResult();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccDisputaPrePagoDAO.editarEntity");
			
		}
		
		return entity;
	}
	
	
	private String gerarHql(){
		StringBuilder hql = new StringBuilder();
		hql.append("select d from SccDisputaPrePago d ");
		hql.append("inner join fetch d.sccDisputaDetalhePrePagos dd ");
		hql.append("inner join fetch d.sccOperadora o ");
		hql.append("where d.sqDisputaPrePago = :sqDisputaPrePago ");
		return hql.toString();
	}

/*	@Override
	public SccDisputaPrePago findBySqDisputaPrePago(Long sqDisputaPrePago)
			throws DAOException {
		
		SccDisputaPrePago entity = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccDisputaPrePago.class);
			criteria.createAlias("sccOperadora", "sccOperadora");
			criteria.createAlias("sccDisputaDetalhePrePagos", "sccDisputaDetalhePrePagos");

			criteria.add(Restrictions.eq("sqDisputaPrePago", sqDisputaPrePago));
			
			entity = (SccDisputaPrePago) criteria.uniqueResult();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), "SccDisputaPrePagoDAO.editarEntity");
		}
		return entity;

	}

*/	
	

}
