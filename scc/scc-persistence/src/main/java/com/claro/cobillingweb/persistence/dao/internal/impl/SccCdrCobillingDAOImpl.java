package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccCdrCobillingDAO;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.filtro.SccCdrCobillingFiltro;

public class SccCdrCobillingDAOImpl extends HibernateBasicDAOImpl<SccCdrCobilling> implements SccCdrCobillingDAO {

	 
	public List<SccCdrCobilling> getAll() throws DAOException {
		throw new DAOException("Método não suportado","SccCdrCobillingDAO.getAll");
	}

	 
	@SuppressWarnings("unchecked")
	public List<SccCdrCobilling> pesquisaCDRs(SccCdrCobillingFiltro filtro)	throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccCdrCobilling.class);
			if (filtro.getSeqArquivo() != null)
				criteria.add(Restrictions.eq("sqArquivoRemessa", filtro.getSeqArquivo()));
			if ((filtro.getStatusCDR() != null) && (filtro.getStatusCDR().size() > 0))
				criteria.add(Restrictions.in("cdStatusCdr", filtro.getStatusCDR()));	
			if (filtro.getDataChamada() != null)
				criteria.add(Restrictions.eq("pk.dtChamada", filtro.getDataChamada()));			
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SccCdrCobillingDAO.pesquisaCDRs");
			}
	}

	/*TODO Entender porque quando uso Criteria , todas as queries dessa entidade ficam lentas apesar do SQL gerado ser bom.*/
	@SuppressWarnings("unchecked")
	public List<SccCdrCobilling> pesquisaArquivoPorStatus(Long seqArquivo,Long cdStatus) throws DAOException {
		try {			
			SQLQuery query =  getSessionFactory().getCurrentSession().createSQLQuery("SELECT * FROM SCC_CDR_COBILLING WHERE CD_STATUS_CDR = ? AND SQ_ARQUIVO_REMESSA = ?");
			query.setLong(0, cdStatus);
			query.setLong(1, seqArquivo);
			query.addEntity(SccCdrCobilling.class);
			return query.list();
			
		} catch (Exception e){
			throw new DAOException(e.getMessage(), "SccCdrCobillingDAO.pesquisaArquivoPorStatus");
		}
	}

	 
	@SuppressWarnings("unchecked")
	public List<Object[]> pesquisaMatrizListaCDRsPorStatus(Long seqArquivo,Long cdStatus) throws DAOException {
		Query query = getSessionFactory().getCurrentSession().createSQLQuery("SELECT rej.ds_motivo_rejeicao ,st.ds_status_cdr , cdr.DT_STATUS_CDR , cdr.CD_CSP,"+
				" cdr.NU_MSISDN_ORIGEM,cdr.NU_TELEFONE_DESTINO , CDR.CD_EOT_ORIGEM , CDR.CD_EOT_DESTINO,"+
				" cdr.DT_EVENTO ,cdr.HR_DURACAO_REAL , cdr.VL_LIQUIDO_CHAMADA "+
				" FROM SCC_CDR_COBILLING cdr , SCC_STATUS_CDR st , SCC_MOTIVO_REJEICAO rej "+
				" WHERE cdr.SQ_ARQUIVO_REMESSA = ? and cdr.CD_STATUS_CDR = ? "+
				" and cdr.cd_status_cdr = st.cd_status_cdr "+
				" and (cdr.cd_motivo_rejeicao = rej.cd_motivo_rejeicao OR cdr.cd_motivo_rejeicao is null)");
		query.setLong(0, seqArquivo);
		query.setLong(1, cdStatus);
		return query.list();
	}

	
	
	 
	@SuppressWarnings("unchecked")
	public List<SccCdrCobilling> geraResumoCDRsSemErroArquivo(Long seqArquivo) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccCdrCobilling.class);
			ProjectionList projectionList = Projections.projectionList();
			criteria.add(Restrictions.eq("sqArquivoRemessa", seqArquivo));
			//criteria.add(Restrictions.isNull("cdMotivoRejeicao"));
			projectionList.add(Projections.count("nuCdr").as("nuCdr"));
			projectionList.add(Projections.groupProperty("aaCiclo").as("aaCiclo"));		
			projectionList.add(Projections.groupProperty("mmCiclo").as("mmCiclo"));
			projectionList.add(Projections.groupProperty("cdCiclo").as("cdCiclo"));
			ResultTransformer resultTransformer = Transformers.aliasToBean(SccCdrCobilling.class);
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(resultTransformer);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	 
	@SuppressWarnings("unchecked")
	public List<SccCdrCobilling> geraResumoCDRsComErroArquivo(Long seqArquivo) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccCdrCobilling.class);
			ProjectionList projectionList = Projections.projectionList();
			criteria.add(Restrictions.eq("sqArquivoRemessa", seqArquivo));
			criteria.add(Restrictions.isNotNull("cdMotivoRejeicao"));
			projectionList.add(Projections.groupProperty("cdMotivoRejeicao").as("cdMotivoRejeicao"));
			projectionList.add(Projections.count("nuCdr").as("nuCdr"));
			criteria.setProjection(projectionList);
			ResultTransformer resultTransformer = Transformers.aliasToBean(SccCdrCobilling.class);
			criteria.setResultTransformer(resultTransformer);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	 
	@SuppressWarnings("unchecked")
	public List<SccCdrCobilling> geraResumoCDRsArquivo(Long seqArquivo) throws DAOException {
		List<SccCdrCobilling> list = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccCdrCobilling.class);
			ProjectionList projectionList = Projections.projectionList();
			criteria.add(Restrictions.eq("sqArquivoRemessa", seqArquivo));
			criteria.createAlias("statusCdr", "status", Criteria.LEFT_JOIN);
			criteria.createAlias("arquivoRetorno", "arquivo", Criteria.INNER_JOIN);
			projectionList.add(Projections.groupProperty("statusCdr").as("statusCdr"));
			projectionList.add(Projections.count("nuCdr").as("nuCdr"));
			criteria.setProjection(projectionList);
			ResultTransformer resultTransformer = Transformers.aliasToBean(SccCdrCobilling.class);
			criteria.setResultTransformer(resultTransformer);			
			list = (List<SccCdrCobilling>) criteria.list();
		} catch (Exception e){
			throw new DAOException(e.getMessage(), e);
		}
		return list;
		
	}

	 
	@SuppressWarnings("unchecked")
	public List<SccCdrCobilling> listaCDRsArquivo(Long seqArquivo,SccCdrCobilling filtro, int pagina, int quantidadeRegistros) throws DAOException {
		
		List<SccCdrCobilling> list = null;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccCdrCobilling.class);
			criteria.add(Restrictions.eq("sqArquivoRemessa", seqArquivo));
			criteria.setFetchMode("statusCdr", FetchMode.JOIN);
			criteria.setFetchMode("cdMotivoRejeicao", FetchMode.JOIN);
			
			
			if (filtro.getStatusCdr() != null && filtro.getStatusCdr().getCdStatusCdr() != null) {
				criteria.add(Restrictions.eq("statusCdr.cdStatusCdr",filtro.getStatusCdr().getCdStatusCdr()));
			}
			if (filtro.getCdMotivoRejeicao() != null && filtro.getCdMotivoRejeicao().getCdMotivoRejeicao() != null) {
				criteria.add(Restrictions.eq("cdMotivoRejeicao.cdMotivoRejeicao", filtro.getCdMotivoRejeicao().getCdMotivoRejeicao()));
			}
			if (filtro.getAaCiclo() != null) {
				criteria.add(Restrictions.eq("aaCiclo", filtro.getAaCiclo()));
			}
			if (filtro.getMmCiclo() != null) {
				criteria.add(Restrictions.eq("mmCiclo", filtro.getMmCiclo()));
			}
			if (filtro.getCdCiclo() != null) {
				criteria.add(Restrictions.eq("cdCiclo", filtro.getCdCiclo()));
			}
			if (quantidadeRegistros > 0) {
				criteria.setMaxResults(quantidadeRegistros);
				criteria.setFirstResult(pagina * quantidadeRegistros);
			}
			list = (List<SccCdrCobilling>) criteria.list();
		} catch (Exception e){
			throw new DAOException(e.getMessage(),e);
		}
		
		return list;
	}

	 
	@SuppressWarnings("unchecked")
	public Long contaCDRsArquivo(Long seqArquivo, SccCdrCobilling filtro) throws DAOException {
		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccCdrCobilling.class);
			criteria.add(Restrictions.eq("sqArquivoRemessa", seqArquivo));
			if (filtro.getStatusCdr() != null)
				{
				criteria.add(Restrictions.eq("statusCdr", filtro.getStatusCdr()));
				}
			if (filtro.getCdMotivoRejeicao() != null)
				{
				criteria.add(Restrictions.eq("cdMotivoRejeicao", filtro.getCdMotivoRejeicao()));
				}
			if (filtro.getAaCiclo() != null)
				{
				criteria.add(Restrictions.eq("aaCiclo", filtro.getAaCiclo()));
				}
			if (filtro.getMmCiclo() != null)
				{
				criteria.add(Restrictions.eq("mmCiclo", filtro.getMmCiclo()));
				}
			if (filtro.getCdCiclo() != null)
				{
				criteria.add(Restrictions.eq("cdCiclo", filtro.getCdCiclo()));
				}
			
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.count("nuCdr").as("nuCdr"));
			criteria.setProjection(projectionList);
			ResultTransformer resultTransformer = Transformers.aliasToBean(SccCdrCobilling.class);
			criteria.setResultTransformer(resultTransformer);
			List<SccCdrCobilling> itens = criteria.list();
			if (itens.size() > 0)
				return itens.get(0).getNuCdr();
			else
				return 0L;
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(),e);
			}	
	}

	 
	@SuppressWarnings("unchecked")
	public List<SccCdrCobilling> pesquisaCDRsRejeitados(String cdMotivoRejeicao,String cdEOTClaro , String cdEOTLD,Date dataInicial,Date dataFinal ) throws DAOException {		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccCdrCobilling.class);
			criteria.createAlias("arquivoRetorno", "r");
			if ((cdEOTLD != null) && (!cdEOTLD.equals(GET_ALL_STRING)))
				criteria.add(Restrictions.eq("r.operadoraExterna.cdEot", cdEOTLD));
			
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(GET_ALL_STRING)))
				criteria.add(Restrictions.eq("r.operadoraClaro.cdEot", cdEOTLD));			
			
			
			criteria.add(Restrictions.between("r.dtProcClaro", dataInicial, dataFinal));
			criteria.add(Restrictions.eq("cdMotivoRejeicao.cdMotivoRejeicao", cdMotivoRejeicao));
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}		
	}

	
	
}
