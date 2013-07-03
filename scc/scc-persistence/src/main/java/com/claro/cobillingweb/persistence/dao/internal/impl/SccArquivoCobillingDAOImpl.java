package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccOperadoraDAO;
import com.claro.cobillingweb.persistence.dao.internal.SccTipoArquivoDAO;
import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.filtro.SCCArquivoCobillingFiltro;
import com.claro.cobillingweb.persistence.filtro.SCCArquivoCobillingFiltroMulti;
import com.claro.cobillingweb.persistence.utils.DateUtils;

public class SccArquivoCobillingDAOImpl extends HibernateBasicDAOImpl<SccArquivoCobilling> implements SccArquivoCobillingDAO  {

	private SccTipoArquivoDAO sccTipoArquivoDAO;

	private SccOperadoraDAO operadoraDAO;


	public List<SccArquivoCobilling> getAll() throws DAOException {		
		return null;
	}




	public List<SccArquivoCobilling> pesquisaArquivos(SCCArquivoCobillingFiltro to) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccArquivoCobilling.class);
			
			/*
			System.out.println("Tipo Arquivo="+to.getTipoOperadora());
			if (to.getTipoOperadora().equals("OP")) {
				criteria.add(Restrictions.eq("cdStatusArquivo.cdStatusArquivo", to.getStatusArquivo()));
			} else if (to.getTipoOperadora().equals("HO")) {
				
			}
			*/
			
			if (!to.getStatusArquivo().equals(BasicDAO.GET_ALL_STRING)) {
				criteria.add(Restrictions.eq("cdStatusArquivo.cdStatusArquivo", to.getStatusArquivo()));
			}
			if (!to.getCdErroProtocolo().equals(BasicDAO.GET_ALL_STRING)) {
				if (to.getCdErroProtocolo().equals("OK")) {
					criteria.add(Restrictions.eq("cdErroProtocolo", "OK"));
				} else if (to.getCdErroProtocolo().equals("NOK")) {					
					criteria.add(Restrictions.and(Restrictions.ne("cdErroProtocolo", "OK"),Restrictions.isNotNull("cdErroProtocolo")));
				} else if (to.getCdErroProtocolo().equals("SP")) {
					criteria.add(Restrictions.isNotNull("cdErroProtocolo"));
				}
			}
			if (!to.getOperadoraClaro().equals(BasicDAO.GET_ALL_STRING)) {
				criteria.add(Restrictions.eq("operadoraClaro.cdEot", to.getOperadoraClaro()));
			}
			if (!to.getOperadoraExterna().equals(BasicDAO.GET_ALL_STRING)) {
				criteria.add(Restrictions.eq("operadoraExterna.cdEot", to.getOperadoraExterna()));
			}
			if (!to.getCdTipoBatimento().equals(BasicDAO.GET_ALL_STRING)) {//FIS
				criteria.add(Restrictions.in("tipoArquivo.cdTipoArquivo", getSccTipoArquivoDAO().pesquisaPorTipoBatimento(to.getCdTipoBatimento())));
			}
			if (to.getTipoPeriodo().equals("REF")) {
				//criteria.add(Restrictions.ge("dtProcExterna", to.getDataInicialPeriodo()));
				//criteria.add(Restrictions.le("dtProcExterna", to.getDataFinalPeriodo()));
				criteria.add(Restrictions.between("dtProcExterna", to.getDataInicialPeriodo(), to.getDataFinalPeriodo()));
				
			} else if (to.getTipoPeriodo().equals("PROC")) {
				//criteria.add(Restrictions.ge("dtProcClaro", to.getDataInicialPeriodo()));
				//criteria.add(Restrictions.le("dtProcClaro", to.getDataFinalPeriodo()));
				criteria.add(Restrictions.between("dtProcClaro", to.getDataInicialPeriodo(), to.getDataFinalPeriodo()));
			}
			criteria.add(Restrictions.ne("cdStatusArquivo.cdStatusArquivo", "OL"));
			criteria.addOrder(Order.asc("noArquivo"));
			criteria.setFetchMode("cdStatusArquivo", FetchMode.JOIN);
			
			return criteria.list();
		} catch (Exception e){
			throw new DAOException(e.getMessage(), "SccArquivoCobillingDAO.pesquisaPorFiltros");
		}
	}
	
	public SccTipoArquivoDAO getSccTipoArquivoDAO() {
		return sccTipoArquivoDAO;
	}

	public void setSccTipoArquivoDAO(SccTipoArquivoDAO sccTipoArquivoDAO) {
		this.sccTipoArquivoDAO = sccTipoArquivoDAO;
	}
	
	public List<SccArquivoCobilling> pesquisaArquivos(SCCArquivoCobillingFiltroMulti to) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccArquivoCobilling.class);			
			if ((to.getStatus() != null) && (to.getStatus().size() > 0))
			{
				criteria.add(Restrictions.in("cdStatusArquivo.cdStatusArquivo", to.getStatus()));
			}


			if (to.getTipoPeriodo().equals("REF"))
			{
				criteria.add(Restrictions.between("dtProcExterna", to.getDataInicialPeriodo(), to.getDataFinalPeriodo()));
			}
			else if (to.getTipoPeriodo().equals("PROC"))
			{
				criteria.add(Restrictions.between("dtProcClaro", to.getDataInicialPeriodo(), to.getDataFinalPeriodo()));
			}

			if ((to.getTiposArquivo() != null) && (to.getTiposArquivo().size() > 0))
			{
				criteria.add(Restrictions.in("tipoArquivo.cdTipoArquivo", to.getTiposArquivo()));
			}

			return criteria.list();
		} catch (Exception e)
		{
			throw new DAOException(e.getMessage(), "SccArquivoCobillingDAO.pesquisaArquivos(Multi)");
		}
	}




	public List<SccArquivoCobilling> pesquisaArquivosRemessa(Long nuRemessa) throws DAOException {
		String SQL = "SELECT SQ_ARQUIVO FROM SCC_ARQUIVO_COBILLING WHERE SQ_ARQUIVO IN (SELECT SQ_ARQUIVO FROM SCC_FECHAMENTO_SUMARIZADO WHERE NU_REPASSE = ? AND CD_STATUS_CDR in (30,40))";
		List<SccArquivoCobilling> resultados = new ArrayList<SccArquivoCobilling>();		
		try {
			SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(SQL);
			query.setLong(0, nuRemessa);
			List<List<Object[]>> pks = query.list();
			if (pks == null)
				return null;
			for (int i=0;i<pks.size();i++)
			{
				SccArquivoCobilling arquivoCobilling = getByPk((Serializable)pks.get(i), SccArquivoCobilling.class);
				resultados.add(arquivoCobilling);
			}			
			return resultados;
		} catch (Exception e)
		{
			throw new DAOException(e.getMessage(), "SccArquivoCobillingDAO.pesquisaArquivosRemessa");
		}
	}




	public SccArquivoCobilling pesquisaArquivoProtocolo(SccArquivoCobilling arquivoCobilling) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccArquivoCobilling.class);
			criteria.add(Restrictions.eq("sqArquivoOrigem", arquivoCobilling.getSqArquivo()));
			criteria.add(Restrictions.or(Restrictions.eq("tipoArquivo.cdTipoArquivo", 60L), Restrictions.eq("tipoArquivo.cdTipoArquivo", 65L)));
			List<SccArquivoCobilling> rows = criteria.list();
			if ((rows != null) && (rows.size() > 0))
			{
				return rows.get(0);
			}
		} catch (Exception e)
		{
			throw new DAOException(e.getMessage(), e);
		}
		return null;
	}




	public List<SccArquivoCobilling> pesquisaArquivosRemessa(String cdEOTClaro,String cdEOTLD, String tipoPeriodo, Date dtInicial, Date dtFinal,String statusArquivo,boolean holding) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccArquivoCobilling.class);
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING))) {
				if (holding) {				 
					criteria.add(Restrictions.in("operadoraClaro", getOperadoraDAO().pesquisaOperadorasHolding(cdEOTClaro)));
				} else {
					criteria.add(Restrictions.eq("operadoraClaro.cdEot", cdEOTClaro));
				}
			}
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING))) {
				criteria.add(Restrictions.eq("operadoraExterna.cdEot", cdEOTLD));
			}
			if (tipoPeriodo.equals("REF")) {
				criteria.add(Restrictions.ge("dtProcExterna", DateUtils.lowDateTime(dtInicial)));
				criteria.add(Restrictions.le("dtProcExterna", DateUtils.highDateTime(dtFinal)));
			} else if (tipoPeriodo.equals("PROC")) {
				criteria.add(Restrictions.ge("dtProcClaro", DateUtils.lowDateTime(dtInicial)));
				criteria.add(Restrictions.le("dtProcClaro", DateUtils.highDateTime(dtFinal)));
			}
			if (!statusArquivo.equals(BasicDAO.GET_ALL_STRING)) {
				if (statusArquivo.equals("OK")) {
					criteria.add(Restrictions.or(Restrictions.eq("cdErroProtocolo", "OK"),Restrictions.isNull("cdErroProtocolo")));
				} else if (statusArquivo.equals("NOK")) {					
					criteria.add(Restrictions.and(Restrictions.ne("cdErroProtocolo", "OK"),Restrictions.isNotNull("cdErroProtocolo")));
				}
			} 
			criteria.add(Restrictions.in("tipoArquivo.cdTipoArquivo", getSccTipoArquivoDAO().pesquisaPorTipoBatimento("REM")));
			criteria.addOrder(Order.asc("dtProcExterna"));

			return criteria.list();
		} catch (Exception e)
		{
			throw new DAOException(e.getMessage(), e);
		}	
	}




	public List<SccArquivoCobilling> pesquisaArquivosRemessaFranquia(String cdEOTClaro, String cdEOTLD, String tipoPeriodo,Date dtInicial, Date dtFinal, String statusArquivo,boolean holding) throws DAOException {

		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccArquivoCobilling.class);
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)))
			{
				if (holding)
				{					 
					criteria.add(Restrictions.in("operadoraClaro.cdEot", getOperadoraDAO().pesquisaOperadorasHolding(cdEOTClaro)));
				}
				else
				{
					criteria.add(Restrictions.eq("operadoraClaro.cdEot", cdEOTClaro));
				}			 
			}
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING)))
			{
				criteria.add(Restrictions.eq("operadoraExterna.cdEot", cdEOTLD));
			}
			if (tipoPeriodo.equals("REF"))
			{
				criteria.add(Restrictions.between("dtProcExterna", dtInicial, dtFinal));
			}
			else if (tipoPeriodo.equals("PROC"))
			{
				criteria.add(Restrictions.between("dtProcClaro", dtInicial, dtFinal));
			}
			if (!statusArquivo.equals(BasicDAO.GET_ALL_STRING))
			{
				if (statusArquivo.equals("OK"))
				{
					criteria.add(Restrictions.or(Restrictions.eq("cdErroProtocolo", "OK"),Restrictions.isNull("cdErroProtocolo")));
				}
				else if (statusArquivo.equals("NOK"))
				{					
					criteria.add(Restrictions.and(Restrictions.ne("cdErroProtocolo", "OK"),Restrictions.isNotNull("cdErroProtocolo")));
				}
			}
			criteria.add(Restrictions.eq("tipoArquivo.cdTipoArquivo", 46L));
			return criteria.list();
		} catch (Exception e)
		{
			throw new DAOException(e.getMessage(), e);
		}	
	}



	public SccOperadoraDAO getOperadoraDAO() {
		return operadoraDAO;
	}



	public void setOperadoraDAO(SccOperadoraDAO operadoraDAO) {
		this.operadoraDAO = operadoraDAO;
	}




	public List<SccArquivoCobilling> pesquisaArquivosRetorno(String cdEOTClaro,String cdEOTLD, Long cdTipoArquivo, String statusArquivo,String sistema, String tipoPeriodo, Date dtInicial, Date dtFinal,boolean holding) throws DAOException {		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccArquivoCobilling.class);
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(GET_ALL_STRING)))
			{
				if (holding)
					criteria.add(Restrictions.in("operadoraClaro.cdEot", getOperadoraDAO().pesquisaOperadorasHolding(cdEOTClaro)));
				else
					criteria.add(Restrictions.eq("operadoraClaro.cdEot", cdEOTClaro));
			}


			if ((cdEOTLD != null) && (!cdEOTLD.equals(GET_ALL_STRING)))
				criteria.add(Restrictions.eq("operadoraExterna.cdEot", cdEOTLD));

			if ((cdTipoArquivo != null) && (!cdTipoArquivo.equals(GET_ALL)))
				criteria.add(Restrictions.eq("tipoArquivo.cdTipoArquivo", cdTipoArquivo));

			if ((statusArquivo != null) && (!statusArquivo.equals(GET_ALL_STRING)))
			{
				if (statusArquivo.equals("OK"))
					criteria.add(Restrictions.in("cdStatusArquivo.cdStatusArquivo",new String[]{"OK","AC","RE","LO"} ));
				else
					criteria.add(Restrictions.eq("cdStatusArquivo.cdStatusArquivo", statusArquivo));
			}
			if ((sistema != null) && (!sistema.equals(GET_ALL_STRING)))
				criteria.add(Restrictions.like("noArquivo", "%"+sistema+"%"));

			if (tipoPeriodo.equals("R"))
				criteria.add(Restrictions.between("dtProcExterna", dtInicial, dtFinal));

			if (tipoPeriodo.equals("P"))
				criteria.add(Restrictions.between("dtProcClaro", dtInicial, dtFinal));

			if (tipoPeriodo.equals("T"))
				criteria.add(Restrictions.between("dtInicioTrafego", dtInicial, dtFinal));

			if (tipoPeriodo.equals("F"))
				criteria.add(Restrictions.between("dtStatusArquivo", dtInicial, dtFinal));

			if (tipoPeriodo.equals("S"))
				criteria.add(Restrictions.between("dtFimTrafego", dtInicial, dtFinal));
			return criteria.list();
		} catch (Exception e)
		{
			throw new DAOException(e.getMessage(), e);
		}		
	}




	public List<SccArquivoCobilling> pesquisaArquivosDataProcessamento(String cdEOTClaro, String cdEOTLD, Date dataInicial, Date dataFinal) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccArquivoCobilling.class);

			if ((cdEOTLD != null) && (!cdEOTLD.equals(GET_ALL_STRING)))
				criteria.add(Restrictions.eq("operadoraExterna.cdEot", cdEOTLD));

			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(GET_ALL_STRING)))
				criteria.add(Restrictions.eq("operadoraClaro.cdEot", cdEOTLD));

			criteria.add(Restrictions.between("dtProcExterna", dataInicial, dataFinal));
			return criteria.list();
		} catch (Exception e)
		{
			throw new DAOException(e.getMessage(), e);
		}		
	}




	public List<SccArquivoCobilling> pesquisaRelatoriosTransicao(Long tiposArquivo, Date dataInicial, Date dataFinal) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccArquivoCobilling.class);
			criteria.add(Restrictions.between("dtInicioTrafego", dataInicial, dataFinal));
			criteria.add(Restrictions.eq("tipoArquivo.cdTipoArquivo", tiposArquivo));
			return criteria.list();
		} catch (Exception e)
		{
			throw new DAOException(e.getMessage(), e);
		}
	}
	
	public SccArquivoCobilling pesquisaRelatorioTransicaoByArquivo(String nomeArquivo) throws DAOException {
		
		SccArquivoCobilling entity = null;
		
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccArquivoCobilling.class);
			criteria.add(Restrictions.eq("noArquivo", nomeArquivo));
			entity = (SccArquivoCobilling) criteria.list().get(0);
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return entity;
		
	}			
			
	@SuppressWarnings("unchecked")
	public List<SccArquivoCobilling> pesquisaArquivosVum(String cdEOTLD, String plataforma, long tipoArquivo, Date dataInicial, Date dataFinal) throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccArquivoCobilling.class);
			
			SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
			//SCC.VUM.pppEOT.IddMMyyyy.FddMMyyyy.PddMMyyyy.txt.parcial
//			
//			if (dataInicial != null)
//				criteria.add(Restrictions.eq("dtInicioTrafego", dataInicial));
//			
//			if (dataFinal != null)
//				criteria.add(Restrictions.eq("dtFimTrafego", dataFinal));
//						
			String filtroNome = "SCC.VUM.%s.%s.I%s.F%s.P%%";
			String strPlat = "___";
			String strEot = "___";
			String strDtInicio = "________";
			String strDtFinal = "________";
			
			if (plataforma != null && !plataforma.equals(BasicDAO.GET_ALL_STRING))
				strPlat = plataforma.equals("0000") ? "pos" : "pre";
			
			if (cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)) {
				criteria.add(Restrictions.eq("operadoraExterna.cdEot", cdEOTLD));
				strEot = cdEOTLD;
			}
			
			if (dataInicial != null) {
				strDtInicio = df.format(dataInicial);
			}
			
			if (dataFinal != null) {
				strDtFinal = df.format(dataFinal);
			}
						
			filtroNome = String.format(filtroNome, strPlat, strEot, strDtInicio, strDtFinal);
			
			criteria.add(Restrictions.ilike("noArquivo", filtroNome.toLowerCase()));
			
			criteria.add(Restrictions.eq("tipoArquivo.cdTipoArquivo", tipoArquivo));
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

}
