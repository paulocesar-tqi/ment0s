package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccAssinaturaPrePagoDAO;
import com.claro.cobillingweb.persistence.dao.query.DisponibilizacaoPacotePrePagoDAONativeSQL;
import com.claro.cobillingweb.persistence.entity.SccAssinaturaPrePago;
import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;
import com.claro.cobillingweb.persistence.view.DisponibilizacaoPacotePrePagoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccAssinaturaPrePagoDAOImpl extends HibernateBasicDAOImpl<SccAssinaturaPrePago> implements
		SccAssinaturaPrePagoDAO {

	@Override
	public List<SccAssinaturaPrePago> getAll() throws DAOException {
		throw new UnsupportedOperationException("operação não disponível");
	}

	@Override
	public List<SccAssinaturaPrePago> findPacotesAssinatura() throws DAOException {
		try {
			String hql = "SELECT DISTINCT a.pacotePrepago, a.qtMinutosAdquiridos FROM SccAssinaturaPrePago a";
			
			Query q = getSessionFactory().getCurrentSession().createQuery(hql);
			
			@SuppressWarnings("unchecked")
			List<Object[]> lst = q.list();
			
			List<SccAssinaturaPrePago> lstRet = new ArrayList<SccAssinaturaPrePago>();
			for (Object[] obj : lst) {
				SccAssinaturaPrePago objAssinatura = new SccAssinaturaPrePago();
				objAssinatura.setPacotePrepago((SccPacotePrepago)obj[0]);
				objAssinatura.setQtMinutosAdquiridos((Double)obj[1]);
				lstRet.add(objAssinatura);
			}
			
			return lstRet;			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	@Override
	public List<DisponibilizacaoPacotePrePagoView> pesquisarDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote, Integer qtdMinutos,
			Date dtInicio, Date dtFim) throws DAOException {
		Session session = getSessionFactory().getCurrentSession();
		NativeSQLViewMapper<DisponibilizacaoPacotePrePagoView> mapper = new NativeSQLViewMapper<DisponibilizacaoPacotePrePagoView>(session, DisponibilizacaoPacotePrePagoDAONativeSQL.SQL, DisponibilizacaoPacotePrePagoView.class);
				
		if (cdEOTLD != null && !cdEOTLD.equals(GET_ALL_STRING))
			mapper.addArgument("cdEOTLD", cdEOTLD, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_OPERADORA_EXTERNA);
		if (cdEOTClaro != null && !cdEOTClaro.equals(GET_ALL_STRING))
			mapper.addArgument("cdEOTClaro", cdEOTClaro, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_OPERADORA_CLARO);
		if (cdPacote != null && !cdPacote.equals(GET_ALL))
			mapper.addArgument("cdPacote", cdPacote, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_PACOTE);
		if (qtdMinutos != null && !qtdMinutos.equals(GET_ALL.intValue()))
			mapper.addArgument("qtdMinutos", qtdMinutos, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_MINUTOS);
		if (dtInicio != null)
			mapper.addArgument("dataInicial", dtInicio, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_DATA_INICIAL);
		if (dtFim != null)
			mapper.addArgument("dataFinal", dtFim, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_DATA_FINAL);

		mapper.addResultMap("dataReferencia", Date.class);
		mapper.addResultMap("operadoraClaro", String.class);
		mapper.addResultMap("operadoraLD", String.class);
		mapper.addResultMap("terminal", String.class);
		mapper.addResultMap("status", String.class);
		mapper.addResultMap("descricaoPacote", String.class);
		mapper.addResultMap("cdProdutoPrepago", String.class);
		mapper.addResultMap("qtdChamadas", Integer.class);
		mapper.addResultMap("qtdPacotes", Integer.class);
		mapper.addResultMap("duracaoReal", Integer.class);
		mapper.addResultMap("qtdConsumida", Double.class);
		mapper.addResultMap("valorBruto", Double.class);
		
		mapper.setProjections(DisponibilizacaoPacotePrePagoDAONativeSQL.PROJECTIONS);
		
		return mapper.execute();
	}
	
	@Override
	public DisponibilizacaoPacotePrePagoView pesquisarSumarioDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote, Integer qtdMinutos,
			Date dtInicioProcExterna, Date dtFimProcExterna) throws DAOException {
		Session session = getSessionFactory().getCurrentSession();
		NativeSQLViewMapper<DisponibilizacaoPacotePrePagoView> mapper = new NativeSQLViewMapper<DisponibilizacaoPacotePrePagoView>(session, DisponibilizacaoPacotePrePagoDAONativeSQL.SQL_TOTAL, DisponibilizacaoPacotePrePagoView.class);
				
		if (cdEOTLD != null && !cdEOTLD.equals(GET_ALL_STRING))
			mapper.addArgument("cdEOTLD", cdEOTLD, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_OPERADORA_EXTERNA);
		if (cdEOTClaro != null && !cdEOTClaro.equals(GET_ALL_STRING))
			mapper.addArgument("cdEOTClaro", cdEOTClaro, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_OPERADORA_CLARO);
		if (cdPacote != null && !cdPacote.equals(GET_ALL))
			mapper.addArgument("cdPacote", cdPacote, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_PACOTE);
		if (qtdMinutos != null && !qtdMinutos.equals(GET_ALL.intValue()))
			mapper.addArgument("qtdMinutos", qtdMinutos, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_MINUTOS);
		if (dtInicioProcExterna != null)
			mapper.addArgument("dataInicialProcExterna", dtInicioProcExterna, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_DATA_INICIAL_PROC_EXTERNA);
		if (dtFimProcExterna != null)
			mapper.addArgument("dataFinalProcExterna", dtFimProcExterna, DisponibilizacaoPacotePrePagoDAONativeSQL.FILTRO_DATA_FINAL_PROC_EXTERNA);

		mapper.addResultMap("qtdChamadas", Integer.class);
		mapper.addResultMap("duracaoReal", Integer.class);
		mapper.addResultMap("qtdConsumida", Double.class);
		
		List<DisponibilizacaoPacotePrePagoView> lst = mapper.execute();
		if (lst.size() > 0) return lst.get(0);
		return null;
	}
}
