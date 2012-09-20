package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccStatusCdrDAO;
import com.claro.cobillingweb.persistence.dao.query.GeraRelatorioEventosSQL;
import com.claro.cobillingweb.persistence.entity.SccStatusCdr;
import com.claro.cobillingweb.persistence.view.RelEventosArquivoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccStatusCdrDAOImpl extends HibernateBasicDAOImpl<SccStatusCdr> implements SccStatusCdrDAO {
	
	public List<SccStatusCdr> getAll() throws DAOException {
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccStatusCdr.class).addOrder(Order.asc("dsStatusCdr"));
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}	
	}
	
	public List<RelEventosArquivoView> geraRelatorioEventos(String cdEOTLD,String cdEOTClaro, Long cdCdrStatus, Date dataInicial,Date dataFinal) throws DAOException {
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<RelEventosArquivoView> mapper = new NativeSQLViewMapper<RelEventosArquivoView>(session, GeraRelatorioEventosSQL.SQL, RelEventosArquivoView.class);
			mapper.appendSQL(GeraRelatorioEventosSQL.FILTRO_PERIODO);
			
			mapper.addArgument("dataInicial", dataInicial);
			mapper.addArgument("dataFinal", dataFinal);
			if ((cdEOTLD != null) && (!cdEOTLD.equals(GET_ALL_STRING))) {
				mapper.addArgument("cdEOTLD", cdEOTLD, GeraRelatorioEventosSQL.FILTRO_OPERADORA_EXTERNA);
			}
			if ((cdEOTClaro != null) && (!cdEOTClaro.equals(GET_ALL_STRING))) {
				mapper.addArgument("cdEOTClaro", cdEOTClaro, GeraRelatorioEventosSQL.FILTRO_OPERADORA_CLARO);
			}
			if ((cdCdrStatus != null) && (!cdCdrStatus.equals(GET_ALL))) {
				mapper.addArgument("cdCdrStatus", cdCdrStatus, GeraRelatorioEventosSQL.FILTRO_STATUS);
			}
			
			mapper.setProjections(GeraRelatorioEventosSQL.PROJECTIONS);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("operadoraExterna", String.class);
			mapper.addResultMap("dataEvento", String.class);
			mapper.addResultMap("dataReferencia", String.class);
			mapper.addResultMap("quantidadeChamadas", Double.class);
			mapper.addResultMap("durcaoTarifada", Double.class);
			mapper.addResultMap("valorLiquido", Double.class);
			mapper.addResultMap("valorBruto", Double.class);
			mapper.addResultMap("evento", String.class);
			mapper.addResultMap("info", String.class);
			return mapper.execute();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}		
	}
	
}
