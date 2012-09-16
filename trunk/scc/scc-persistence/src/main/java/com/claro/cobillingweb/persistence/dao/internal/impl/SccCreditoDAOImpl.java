package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccCreditoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccCreditoDAONativeSQL;
import com.claro.cobillingweb.persistence.dao.query.SccCreditoSizeDAONativeSQL;
import com.claro.cobillingweb.persistence.entity.SccCredito;
import com.claro.cobillingweb.persistence.view.RelCreditosPrePagoView;
import com.claro.cobillingweb.persistence.view.RelDetalheCreditoPrePagoView;
import com.claro.cobillingweb.persistence.view.mapper.IntegerEntity;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccCreditoDAOImpl extends HibernateBasicDAOImpl<SccCredito> implements SccCreditoDAO {

	 
	public List<SccCredito> getAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public List<RelCreditosPrePagoView> carregaRelatorioCreditos(String cdEOTLD, String cdEOTClaro, String tipoCredito,String statusCredito, Date dtInicio,Date dtFinal,int tamanhoPagina,int pagina)
			throws DAOException {
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<RelCreditosPrePagoView> viewMapper = new NativeSQLViewMapper<RelCreditosPrePagoView>(session, SccCreditoDAONativeSQL.carregaRelatorioCreditosSQL, RelCreditosPrePagoView.class);
			viewMapper.setProjections(SccCreditoDAONativeSQL.carregaRelatorioCreditosProjections);
			viewMapper.addArgument("dtInicio", dtInicio);
			viewMapper.addArgument("dtFinal", dtFinal);
			viewMapper.addArgument("dtCredito", dtInicio);			
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)))
				{
				viewMapper.appendSQL("AND C.CD_EOT_CLARO = :cdEOTClaro");
				viewMapper.addArgument("cdEOTClaro", cdEOTClaro);
				}
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING)))
				{
				viewMapper.appendSQL("AND C.CD_EOT_LD = :cdEOTLD");
				viewMapper.addArgument("cdEOTLD", cdEOTLD);
				}
			if ((statusCredito != null) && (!statusCredito.equals(BasicDAO.GET_ALL_STRING)))
				{
				viewMapper.appendSQL("AND C.CD_STATUS_CREDITO = :statusCredito");
				viewMapper.appendSQL("AND C.CD_STATUS_CREDITO = STCRD.CD_DOMINIO ");
				viewMapper.addArgument("statusCredito", statusCredito);
				}
			else
				{
				viewMapper.appendSQL("AND C.CD_STATUS_CREDITO = STCRD.CD_DOMINIO(+)");
				}
			
			if ((tipoCredito != null) && (!tipoCredito.equals(BasicDAO.GET_ALL_STRING)))
				{
				viewMapper.appendSQL("AND C.TP_CREDITO = :tipoCredito");
				viewMapper.appendSQL("AND C.TP_CREDITO = CDORG.CD_DOMINIO");
				viewMapper.addArgument("tipoCredito", tipoCredito);
				}
			else
				{
				viewMapper.appendSQL("AND C.TP_CREDITO = CDORG.CD_DOMINIO(+)");
				}
			viewMapper.addResultMap("cdEOTLD", String.class);
			viewMapper.addResultMap("cdEOTClaro", String.class);
			viewMapper.addResultMap("sqArquivo", Long.class);
			viewMapper.addResultMap("noArquivo", String.class);
			viewMapper.addResultMap("tipoCredito", String.class);
			viewMapper.addResultMap("cdStatus", String.class);
			viewMapper.addResultMap("dsCredito", String.class);
			viewMapper.addResultMap("dsStatus", String.class);
			viewMapper.addResultMap("vlrCredito", Double.class);
			viewMapper.addResultMap("dtCredito", Date.class);
			viewMapper.addResultMap("sqCredito", Long.class);
			viewMapper.addResultMap("telefone", String.class);
			viewMapper.addResultMap("quantidade", Double.class);
			viewMapper.addResultMap("minutosQueimados", Double.class);
			viewMapper.addResultMap("minutosAjustados", Double.class);
			viewMapper.setPageNumber(pagina);
			viewMapper.setRowsPerPage(tamanhoPagina);			
			return viewMapper.execute();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

	 
	public List<RelDetalheCreditoPrePagoView> carregaDetalhesCredito(Long seqArquivoCredito, Long seqCredito) throws DAOException {
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<RelDetalheCreditoPrePagoView> viewMapper = new NativeSQLViewMapper<RelDetalheCreditoPrePagoView>(session,SccCreditoDAONativeSQL.carregaDetalhesCreditoSQL,RelDetalheCreditoPrePagoView.class);
			viewMapper.addArgument("seqArqCredito", seqArquivoCredito);
			viewMapper.addArgument("seqCredito",seqCredito);
			viewMapper.addResultMap("nomeArquivo", String.class);
			viewMapper.addResultMap("numeroOrigem", String.class);
			viewMapper.addResultMap("numeroDestino", String.class);
			viewMapper.addResultMap("dataChamada", Date.class);
			viewMapper.addResultMap("horaInicioChamada", Long.class);
			viewMapper.addResultMap("nuCDR", Long.class);
			viewMapper.addResultMap("seqCredito", Long.class);
			viewMapper.addResultMap("dataCredito", Date.class);
			viewMapper.addResultMap("valorCredito", Double.class);
			viewMapper.addResultMap("duracaoTarifada", Double.class);
			viewMapper.addResultMap("valorBruto", Double.class);
			return viewMapper.execute();
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

	 
	public Integer carregaRelatorioCreditosSize(String cdEOTLD, String cdEOTClaro, String tipoCredito,String statusCredito, Date dtInicio,Date dtFinal) throws DAOException {
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<IntegerEntity> viewMapper = new NativeSQLViewMapper<IntegerEntity>(session, SccCreditoSizeDAONativeSQL.carregaRelatorioCreditosSQL, IntegerEntity.class);
			viewMapper.setProjections(SccCreditoSizeDAONativeSQL.carregaRelatorioCreditosProjections);
			viewMapper.addArgument("dtInicio", dtInicio);
			viewMapper.addArgument("dtFinal", dtFinal);
			viewMapper.addArgument("dtCredito", dtInicio);			
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)))
				{
				viewMapper.appendSQL("AND C.CD_EOT_CLARO = :cdEOTClaro");
				viewMapper.addArgument("cdEOTClaro", cdEOTClaro);
				}
			if ((cdEOTLD != null) && (!cdEOTLD.equals(BasicDAO.GET_ALL_STRING)))
				{
				viewMapper.appendSQL("AND C.CD_EOT_LD = :cdEOTLD");
				viewMapper.addArgument("cdEOTLD", cdEOTLD);
				}
			if ((statusCredito != null) && (!statusCredito.equals(BasicDAO.GET_ALL_STRING)))
				{
				viewMapper.appendSQL("AND C.CD_STATUS_CREDITO = :statusCredito");
				viewMapper.appendSQL("AND C.CD_STATUS_CREDITO = STCRD.CD_DOMINIO ");
				viewMapper.addArgument("statusCredito", statusCredito);
				}
			else
				{
				viewMapper.appendSQL("AND C.CD_STATUS_CREDITO = STCRD.CD_DOMINIO(+)");
				}
			
			if ((tipoCredito != null) && (!tipoCredito.equals(BasicDAO.GET_ALL_STRING)))
				{
				viewMapper.appendSQL("AND C.TP_CREDITO = :tipoCredito");
				viewMapper.appendSQL("AND C.TP_CREDITO = CDORG.CD_DOMINIO");
				viewMapper.addArgument("tipoCredito", tipoCredito);
				}
			else
				{
				viewMapper.appendSQL("AND C.TP_CREDITO = CDORG.CD_DOMINIO(+)");
				}
			viewMapper.addResultMap("value", Integer.class);
					
			List<IntegerEntity> rows = viewMapper.execute();
			if ((rows != null) && (rows.size() > 0))
				{
				return rows.get(0).getValue();
				}
			else
				{
				return null;
				}
		} catch (Exception e)
			{
			throw new DAOException(e.getMessage(), e);
			}
	}

	
	
}
