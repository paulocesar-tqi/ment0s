package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccBatimentoWruppDAO;
import com.claro.cobillingweb.persistence.dao.query.SccBatimentoWruppDAONativeSQL;
import com.claro.cobillingweb.persistence.view.BatimentoWruppPrePagoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccBatimentoWruppDAOImpl extends HibernateBasicDAOImpl<BatimentoWruppPrePagoView> implements SccBatimentoWruppDAO {

	@Override
	public List<BatimentoWruppPrePagoView> getAll() throws DAOException {
		return null;
	}

	@Override
	public List<BatimentoWruppPrePagoView> listarBatimentos(String cdEOTLD,
			String cdEOTClaro, Date dataInicio, Date dataFim) throws DAOException {

		List<BatimentoWruppPrePagoView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<BatimentoWruppPrePagoView> mapper = new NativeSQLViewMapper<BatimentoWruppPrePagoView>(session, SccBatimentoWruppDAONativeSQL.SQL, BatimentoWruppPrePagoView.class);
			
			if(cdEOTClaro != null && cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
				cdEOTClaro = null;
			}
			mapper.addArgument("cdEOTClaro", cdEOTClaro);
			
			if(cdEOTLD != null && cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
				cdEOTLD = null;
			}
			mapper.addArgument("cdEOTLD", cdEOTLD);
			
			mapper.addArgument("dtInicio", dataInicio);
			mapper.addArgument("dtFim", dataFim);
			
			mapper.addResultMap("data", Date.class);
			mapper.addResultMap("cdEOTClaro", String.class);
			mapper.addResultMap("cdEOTLD", String.class);
			mapper.addResultMap("qtdChamadasWrupp", Long.class);
			mapper.addResultMap("vlrBrutoWrupp", Double.class);
			mapper.addResultMap("qtdChamadasScc", Long.class);
			mapper.addResultMap("vlrBrutoScc", Double.class);

			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}

}
