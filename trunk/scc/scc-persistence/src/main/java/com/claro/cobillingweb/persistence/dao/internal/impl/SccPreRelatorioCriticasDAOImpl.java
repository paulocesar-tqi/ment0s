package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPreRelatorioCriticasDAO;
import com.claro.cobillingweb.persistence.dao.query.SccPreRelatorioCriticasDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccPreRelatorioCriticasView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92038883
 *
 */

public class SccPreRelatorioCriticasDAOImpl extends HibernateBasicDAOImpl<SccPreRelatorioCriticasView>
implements SccPreRelatorioCriticasDAO {

@Override
public List<SccPreRelatorioCriticasView> getAll() throws DAOException {

return null;
}

@Override
public List<SccPreRelatorioCriticasView> gerarPreRelatorioCriticas(Date dtInicioEvento, Date dtFimEvento, 
		String cdEOTLD, String cdEOTClaro, String cdStatus, String cdMotivoRejeicao, String cdDefeito) throws DAOException {

List<SccPreRelatorioCriticasView> list = null;
try {
	Session session = getSessionFactory().getCurrentSession();
	NativeSQLViewMapper<SccPreRelatorioCriticasView> mapper = new NativeSQLViewMapper<SccPreRelatorioCriticasView>(session, SccPreRelatorioCriticasDAONativeSQL.SQL, SccPreRelatorioCriticasView.class);
	mapper.addArgument("dtInicioEvento", dtInicioEvento);
	mapper.addArgument("dtFimEvento", dtFimEvento);
	
	if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
		mapper.addArgument("cdEOTClaro", cdEOTClaro, SccPreRelatorioCriticasDAONativeSQL.FILTRO_CDEOTCLARO);
	}
	
	if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
		mapper.addArgument("cdEOTLD", cdEOTLD, SccPreRelatorioCriticasDAONativeSQL.FILTRO_CDEOTLD);
	}
	
	if(cdStatus != null && !cdStatus.equals(BasicDAO.GET_ALL_STRING)){
		mapper.addArgument("cdStatus", cdStatus, SccPreRelatorioCriticasDAONativeSQL.FILTRO_STATUS_CDR);
	}
	
	if(cdMotivoRejeicao != null && !cdMotivoRejeicao.equals(BasicDAO.GET_ALL_STRING)){
		mapper.addArgument("cdMotivoRejeicao", cdMotivoRejeicao, SccPreRelatorioCriticasDAONativeSQL.FILTRO_MOTIVO_REJEICAO);
	}
	
	if(cdDefeito != null && !cdDefeito.equals(BasicDAO.GET_ALL_STRING)){
		mapper.addArgument("cdDefeito", cdDefeito, SccPreRelatorioCriticasDAONativeSQL.FILTRO_CD_DEFEITO);
	}
	
	mapper.addResultMap("dtChamada", Date.class);
	mapper.addResultMap("statusChamada",String.class);
	mapper.addResultMap("motivoRejeicao", String.class);
	mapper.addResultMap("cdDefeito",String.class);
	mapper.addResultMap("dsOperadoraClaro",String.class);
	mapper.addResultMap("dsOperadoraExterna", String.class);
	mapper.addResultMap("csp", String.class);
	mapper.addResultMap("nroA", String.class);
	mapper.addResultMap("nroB", String.class);
	mapper.addResultMap("cdPais", String.class);
	mapper.addResultMap("cnAreaVisitada", String.class);
	mapper.addResultMap("tipoChamada", String.class);
	mapper.addResultMap("duracaoReal", Double.class);
	mapper.addResultMap("duracaoTarifada", Double.class);
	mapper.addResultMap("valorBruto", Double.class);

	
	list = mapper.execute();
} catch(Exception e) {
	throw new DAOException(e.getMessage(), e);
}
return list;
}


}
