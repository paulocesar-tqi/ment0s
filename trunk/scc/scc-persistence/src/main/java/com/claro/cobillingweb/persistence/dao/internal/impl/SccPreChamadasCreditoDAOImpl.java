package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPreChamadasCreditoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccPreChamadasCreditoDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccPreChamadasCreditoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

	/**
	 * @author 92038883
	 *
	 */

public class SccPreChamadasCreditoDAOImpl extends HibernateBasicDAOImpl<SccPreChamadasCreditoView>
	implements SccPreChamadasCreditoDAO {

	@Override
	public List<SccPreChamadasCreditoView> getAll() throws DAOException {

	return null;
	}

	@Override
	public List<SccPreChamadasCreditoView> gerarPreChamadasCredito(Date dtInicioCredito, Date dtFimCredito, 
			String cdEOTLD, String cdEOTClaro, String tpStatusCredito) throws DAOException {

	List<SccPreChamadasCreditoView> list = null;
	try {
		Session session = getSessionFactory().getCurrentSession();
		NativeSQLViewMapper<SccPreChamadasCreditoView> mapper = new NativeSQLViewMapper<SccPreChamadasCreditoView>(session, SccPreChamadasCreditoDAONativeSQL.SQL, SccPreChamadasCreditoView.class);
		mapper.addArgument("dtInicioCredito", dtInicioCredito);
		mapper.addArgument("dtFimCredito", dtFimCredito);
		
		if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("cdEOTClaro", cdEOTClaro, SccPreChamadasCreditoDAONativeSQL.FILTRO_CDEOTCLARO);
		}
		
		if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("cdEOTLD", cdEOTLD, SccPreChamadasCreditoDAONativeSQL.FILTRO_CDEOTLD);
		}
		
		if(tpStatusCredito != null && !tpStatusCredito.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("tpStatusCredito", tpStatusCredito, SccPreChamadasCreditoDAONativeSQL.FILTRO_STATUS_CDR);
		}
		
		mapper.addResultMap("arquivoCredito", String.class);
		mapper.addResultMap("dsOperadoraClaro",String.class);
		mapper.addResultMap("dsOperadoraExterna", String.class);
		mapper.addResultMap("cdCsp", String.class);
		mapper.addResultMap("statusCredito", String.class);
		mapper.addResultMap("dtCredito", Date.class);
		mapper.addResultMap("valorCredito", Double.class);
		mapper.addResultMap("nuCDR", Long.class);
		mapper.addResultMap("cdCredito", Integer.class);
		mapper.addResultMap("nuAssA", String.class);
		mapper.addResultMap("nuAssB", String.class);
		mapper.addResultMap("dtChamada", Date.class);
		mapper.addResultMap("inicioChamada", Integer.class);
		mapper.addResultMap("duracaoTarifada", Double.class);
		mapper.addResultMap("valorBrutoChamada", Double.class);
		
		
		list = mapper.execute();
	} catch(Exception e) {
		throw new DAOException(e.getMessage(), e);
	}
	return list;
	}
	
	
}
