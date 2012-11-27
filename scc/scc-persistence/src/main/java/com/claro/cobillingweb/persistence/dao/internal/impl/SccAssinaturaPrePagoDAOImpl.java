package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccAssinaturaPrePagoDAO;
import com.claro.cobillingweb.persistence.entity.SccAssinaturaPrePago;
import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;

public class SccAssinaturaPrePagoDAOImpl extends HibernateBasicDAOImpl<SccAssinaturaPrePago> implements
		SccAssinaturaPrePagoDAO {

	@Override
	public List<SccAssinaturaPrePago> getAll() throws DAOException {
		throw new UnsupportedOperationException("operação não disponível");
	}

	@Override
	public List<SccPacotePrepago> findPacotesAssinatura() throws DAOException {
		try {
			String hql = "SELECT DISTINCT pacotePrepago FROM SccAssinaturaPrePago";
			
			Query q = getSessionFactory().getCurrentSession().createQuery(hql);
			
			List<SccPacotePrepago> lst = q.list();
			
			return lst;			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	@Override
	public List<SccAssinaturaPrePago> pesquisarDisponibilidade(String cdEOTClaro, String cdEOTLD, Long cdPacote,
			Date dtInicio, Date dtFim) throws DAOException {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccAssinaturaPrePago.class);

		if (cdEOTLD != null && !cdEOTLD.equals(GET_ALL_STRING))
			criteria.add(Restrictions.eq("operadoraLD.cdEot", cdEOTLD));
		if (cdEOTClaro != null && !cdEOTClaro.equals(GET_ALL_STRING))
			criteria.add(Restrictions.eq("operadoraClaro.cdEot", cdEOTClaro));

		if (cdPacote != null && !cdPacote.equals(GET_ALL))
			criteria.add(Restrictions.eq("pacotePrepago.cdPacotePrepago", cdPacote));

		if (dtInicio != null)
			criteria.add(Restrictions.ge("dtInicioFranquia", dtInicio));

		if (dtFim != null)
			criteria.add(Restrictions.le("dtInicioFranquia", dtFim));

		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.sum("qtdCdr").as("qtdCdr"));
		projectionList.add(Projections.sum("qtTarifaFranquia").as("qtTarifaFranquia"));
		projectionList.add(Projections.sum("hrDuracaoReal").as("hrDuracaoReal"));
		projectionList.add(Projections.sum("vlBrutoPacote").as("vlBrutoPacote"));
		projectionList.add(Projections.count("nuTelefone").as("sqArquivo"));

		projectionList.add(Projections.groupProperty("dtInicioFranquia").as("dtInicioFranquia"));
		projectionList.add(Projections.groupProperty("operadoraLD").as("operadoraLD"));
		projectionList.add(Projections.groupProperty("operadoraClaro").as("operadoraClaro"));
		projectionList.add(Projections.groupProperty("pacotePrepago").as("pacotePrepago"));
		projectionList.add(Projections.groupProperty("nuTelefone").as("nuTelefone"));
		projectionList.add(Projections.groupProperty("qtMinutosAdquiridos").as("qtMinutosAdquiridos"));
		projectionList.add(Projections.groupProperty("produtoPrepago").as("produtoPrepago"));
		projectionList.add(Projections.groupProperty("statusAssinatura").as("statusAssinatura"));

		criteria.setProjection(projectionList);
		criteria.addOrder(Order.asc("dtInicioFranquia"));
		ResultTransformer resultTransformer = Transformers.aliasToBean(SccAssinaturaPrePago.class);
		criteria.setResultTransformer(resultTransformer);
		return criteria.list();
	}
}
