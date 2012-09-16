package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccContratoCobillingDAO;
import com.claro.cobillingweb.persistence.dao.query.SccContratoCobillingDAONativeSQL;
import com.claro.cobillingweb.persistence.entity.SccContratoCobilling;
import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccContratoCobillingDAOImpl extends HibernateBasicDAOImpl<SccContratoCobilling> implements SccContratoCobillingDAO{
	
	public List<SccContratoCobilling> getAll() throws DAOException {
		return null;
	}
	
	public List<SccContratoCobillingSearchView> pesquisaContratosOperadoras(String cdEotClaro, String cdEotLD) throws DAOException {
		try {
			NativeSQLViewMapper<SccContratoCobillingSearchView> mapper = new NativeSQLViewMapper<SccContratoCobillingSearchView>(getSessionFactory().getCurrentSession(), SccContratoCobillingDAONativeSQL.PESQUISA_OPERADORAS_SQL, SccContratoCobillingSearchView.class);
			if ((cdEotClaro != null) && (!cdEotClaro.equals(BasicDAO.GET_ALL_STRING))) {
				mapper.addArgument("cdEotClaro", cdEotClaro);
				mapper.appendSQL(" AND CC.CD_EOT_CLARO = :cdEotClaro ");
			}
			if ((cdEotLD != null) && (!cdEotLD.equals(BasicDAO.GET_ALL_STRING))) {
				mapper.addArgument("cdEotLD", cdEotLD);
				mapper.appendSQL(" AND CC.CD_EOT_LD = :cdEotLD ");
			}
			mapper.setProjections(SccContratoCobillingDAONativeSQL.PESQUISA_OPERADORAS_PROJECTIONS);
			mapper.addResultMap("dsOperadoraLD",String.class);
			mapper.addResultMap("dsOperadoraClaro",String.class);
			mapper.addResultMap("cdEOTLD",String.class);
			mapper.addResultMap("cdEOTClaro",String.class);
			mapper.addResultMap("dtInicioContrato",Date.class);
			mapper.addResultMap("dtFinalContrato",Date.class);
			mapper.addResultMap("cdTipoContrato",String.class);
			mapper.addResultMap("dsCriterioCusto",String.class);
			mapper.addResultMap("valorAssociadoCriterioCusto",Double.class);
			mapper.addResultMap("dsPeriodoBase",String.class);
			mapper.addResultMap("valorCPMF",Double.class);
			mapper.addResultMap("valorISS",Double.class);
			mapper.addResultMap("qtRepasses",Long.class);
			mapper.addResultMap("vlCriterioCustoLiquido",Double.class);			
			return mapper.execute();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
	}
	
}
