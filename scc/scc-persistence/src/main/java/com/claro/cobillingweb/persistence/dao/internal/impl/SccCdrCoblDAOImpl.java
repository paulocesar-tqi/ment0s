package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccCdrCoblDAO;
import com.claro.cobillingweb.persistence.dao.query.GeraSomatorioCDRsSQL;
import com.claro.cobillingweb.persistence.dao.query.PesquisaCDRsPrePagoSQL;
import com.claro.cobillingweb.persistence.entity.SccCdrCobl;
import com.claro.cobillingweb.persistence.view.RelCDRPrePagoView;
import com.claro.cobillingweb.persistence.view.SomatorioRelCDRPrePagoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccCdrCoblDAOImpl extends HibernateBasicDAOImpl<SccCdrCobl> implements SccCdrCoblDAO {

	 
	public Serializable create(SccCdrCobl entity) throws DAOException {
		throw new UnsupportedOperationException();
	}
	
	 
	public void delete(SccCdrCobl entity) throws DAOException {	
		throw new UnsupportedOperationException();
	}
	
	 
	public void update(SccCdrCobl entity) throws DAOException {
		throw new UnsupportedOperationException();
	}
	
	 
	@SuppressWarnings("rawtypes")
	public SccCdrCobl getByPk(Serializable pk, Class entityClazz) throws DAOException {
		throw new UnsupportedOperationException();
	}
	
	 
	public List<SccCdrCobl> getAll() throws DAOException {
		throw new UnsupportedOperationException();
	}

	 
	public List<RelCDRPrePagoView> pesquisaCDRsPrePago(String cdEOTClaro,String cdEOTLD, String tipoPeriodo, Date dataInicial, Date dataFinal,boolean holding) throws DAOException {
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<RelCDRPrePagoView> mapper = new NativeSQLViewMapper<RelCDRPrePagoView>(session, PesquisaCDRsPrePagoSQL.SQL, RelCDRPrePagoView.class);
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("uf", String.class);
			mapper.addResultMap("dataChamada", Date.class);
			mapper.addResultMap("dataCarga", Date.class);
			mapper.addResultMap("dataFechamento", Date.class);			
			
			mapper.addResultMap("cnAssinante", String.class);
			mapper.addResultMap("origemChamada", String.class);
			mapper.addResultMap("operadoraOrigem", String.class);
			
			mapper.addResultMap("codigoDefeito", String.class);
			mapper.addResultMap("grupoHorario", String.class);
			mapper.addResultMap("tipoChamada", String.class);
			mapper.addResultMap("statusRegistro", String.class);
			
			if (tipoPeriodo.equalsIgnoreCase("C"))
				mapper.appendSQL(PesquisaCDRsPrePagoSQL.FILTRO_DT_CHAMADA);
			else if (tipoPeriodo.equalsIgnoreCase("F"))
				mapper.appendSQL(PesquisaCDRsPrePagoSQL.FILTRO_DT_FECHAMENTO);
			else if (tipoPeriodo.equalsIgnoreCase("A"))
				mapper.appendSQL(PesquisaCDRsPrePagoSQL.FILTRO_DT_APURACAO);
			
			if ((cdEOTLD != null) && (!cdEOTLD.equals(GET_ALL_STRING)))
				mapper.addArgument("cdEOTLD", cdEOTLD, PesquisaCDRsPrePagoSQL.FILTRO_OPERADORA_LD);
			
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(GET_ALL_STRING)))
				{
				if (holding)
					mapper.addArgument("cdEOTClaro", cdEOTLD, PesquisaCDRsPrePagoSQL.FILTRO_HOLDING_CLARO);
				else
					mapper.addArgument("cdEOTClaro", cdEOTLD, PesquisaCDRsPrePagoSQL.FILTRO_OPERADORA_CLARO);
				}
			mapper.addArgument("dtInicial", dataInicial);
			mapper.addArgument("dtFinal", dataFinal);
			return mapper.execute();			
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}	
	}

	 
	public SomatorioRelCDRPrePagoView geraSomatorioCDRs(Date dataInicial,Date dataFinal,String tipoPeriodo) throws DAOException {
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SomatorioRelCDRPrePagoView> mapper = new NativeSQLViewMapper<SomatorioRelCDRPrePagoView>(session, GeraSomatorioCDRsSQL.SQL, SomatorioRelCDRPrePagoView.class);
			mapper.addResultMap("quantidadeCDRs", Long.class);
			mapper.addResultMap("minutosReais", Double.class);
			mapper.addResultMap("minutosTarifados", Double.class);
			mapper.addResultMap("valorBruto", Double.class);
			
			if (tipoPeriodo.equalsIgnoreCase("C"))
				mapper.appendSQL(PesquisaCDRsPrePagoSQL.FILTRO_DT_CHAMADA);
			else if (tipoPeriodo.equalsIgnoreCase("F"))
				mapper.appendSQL(PesquisaCDRsPrePagoSQL.FILTRO_DT_FECHAMENTO);
			else if (tipoPeriodo.equalsIgnoreCase("A"))
				mapper.appendSQL(PesquisaCDRsPrePagoSQL.FILTRO_DT_APURACAO);
			
			mapper.addArgument("dtInicial", dataInicial);
			mapper.addArgument("dtFinal", dataFinal);
			
			List<SomatorioRelCDRPrePagoView> rows = mapper.execute();
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

	
	
}
