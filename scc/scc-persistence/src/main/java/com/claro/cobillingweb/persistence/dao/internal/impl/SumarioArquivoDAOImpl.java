package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SumarioArquivoDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.cobillingweb.persistence.view.SumarioCDRView;

public class SumarioArquivoDAOImpl extends HibernateBasicDAOImpl<List<Object[]>> implements SumarioArquivoDAO{

	 
	public List<List<Object[]>> getAll() throws DAOException {		
		return null;
	}

	 
	public List<Object[]> getByPk(Serializable pk, Class entityClazz) throws DAOException {
		throw new DAOException("Operação não suportada.");
	}

	 
	public void delete(List<Object[]> entity) throws DAOException {
		throw new DAOException("Operação não suportada.");
	}

	 
	public Serializable create(List<Object[]> entity) throws DAOException {
		throw new DAOException("Operação não suportada.");
	}

	 
	public void update(List<Object[]> entity) throws DAOException {
		throw new DAOException("Operação não suportada.");
	}

	 
	public List<Object[]> carregaSumarioXStatusCdr(Long seqArquivo)	throws DAOException {		
		try {
			Query query = getSessionFactory().getCurrentSession().createSQLQuery(" SELECT st.ds_status_cdr,fs.cd_status_cdr,SUM(fs.qt_cdrs),SUM(fs.vl_liquido_chamada) "+ 
                                 " FROM SCC_ARQUIVO_SUMARIZADO  fs , SCC_STATUS_CDR st   WHERE fs.SQ_ARQUIVO="+seqArquivo+      
                                 " AND st.cd_status_cdr = fs.cd_status_cdr  GROUP BY fs.cd_status_cdr,st.ds_status_cdr "+
                                 " ORDER BY fs.cd_status_cdr ASC");
			return query.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SumarioArquivoDAO.carregaSumarioXStatusCdr");
			}
		
	}

	 
	public List<Object[]> geraSumarioRemessaPosXDataXStatus(SccArquivoCobilling sccArquivoCobilling, Long cdStatus) throws DAOException {
		try {
			Query query = getSessionFactory().getCurrentSession().createSQLQuery("SELECT " +
					"CD_CICLO , MM_CICLO ,  AA_CICLO , COUNT(*) FROM SCC_CDR_COBILLING  WHERE " +
					"SQ_ARQUIVO_REMESSA = ? AND CD_STATUS_CDR = ? GROUP BY (CD_CICLO , MM_CICLO ,  AA_CICLO)");
			query.setLong(0, sccArquivoCobilling.getSqArquivo());
			query.setLong(1, cdStatus);
			
			return query.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(),"SumarioArquivoDAO.geraSumarioRemessaPosXDataXStatus");
			}
	}

	
	
	

	 
	public List<SccCdrCobilling> gerarListaCDRsSumario(Long cdSeqArquivo,Long cdStatus, Long cdCiclo, Long mmCiclo, Long aaCiclo,int page,int pageSize) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccCdrCobilling.class);
			criteria.add(Restrictions.eq("sqArquivoRemessa", cdSeqArquivo));
			criteria.add(Restrictions.eq("statusCdr.cdStatusCdr", cdStatus));
			criteria.add(Restrictions.eq("mmCiclo", mmCiclo));
			criteria.add(Restrictions.eq("aaCiclo", aaCiclo));
			criteria.add(Restrictions.eq("cdCiclo", cdCiclo));
			criteria.setMaxResults(pageSize);
			criteria.setFirstResult(page*pageSize);
			return criteria.list();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), "SumarioArquivoDAO.gerarListaCDRsSumario");
			}
	}

	 
	public int getCountListaCDRsSumario(Long cdSeqArquivo, Long cdStatus,Long cdCiclo, Long mmCiclo, Long aaCiclo) throws DAOException {
		try {
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("SELECT COUNT(*) FROM SCC_CDR_COBILLING WHERE SQ_ARQUIVO_REMESSA = ?" +
					" AND CD_STATUS_CDR = ? AND CD_CICLO = ? and MM_CICLO = ? AND AA_CICLO = ?"); 
		
			query.setLong(0,cdSeqArquivo);
			query.setLong(1, cdStatus);
			query.setLong(2, cdCiclo);
			query.setLong(3, mmCiclo);
			query.setLong(4, aaCiclo);
					
		return ((BigDecimal)query.list().get(0)).intValue();
		} catch (Exception e)
		{
		throw new DAOException(e.getMessage(), "getCountListaCDRsSumario.gerarListaCDRsSumario");
		}
	}



	
}
