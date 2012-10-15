package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPreRelatorioEventosDAO;
import com.claro.cobillingweb.persistence.dao.query.SccPreRelatorioEventosDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccPreRelatorioEventosView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92038883
 *
 */

public class SccPreRelatorioEventosDAOImpl extends HibernateBasicDAOImpl<SccPreRelatorioEventosView>
implements SccPreRelatorioEventosDAO {

@Override
public List<SccPreRelatorioEventosView> getAll() throws DAOException {

return null;
}

@Override
public List<SccPreRelatorioEventosView> gerarPreRelatorioEventos(Date dtInicioEvento, Date dtFimEvento, 
		String cdEOTLD, String cdEOTClaro, String tpStatus) throws DAOException {

List<SccPreRelatorioEventosView> list = null;
try {
	Session session = getSessionFactory().getCurrentSession();
	NativeSQLViewMapper<SccPreRelatorioEventosView> mapper = new NativeSQLViewMapper<SccPreRelatorioEventosView>(session, SccPreRelatorioEventosDAONativeSQL.SQL, SccPreRelatorioEventosView.class);
	mapper.addArgument("dtInicioEvento", dtInicioEvento);
	mapper.addArgument("dtFimEvento", dtFimEvento);
	
	if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
		mapper.addArgument("cdEOTClaro", cdEOTClaro, SccPreRelatorioEventosDAONativeSQL.FILTRO_CDEOTCLARO);
	}
	
	if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
		mapper.addArgument("cdEOTLD", cdEOTLD, SccPreRelatorioEventosDAONativeSQL.FILTRO_CDEOTLD);
	}
	
	if(tpStatus != null && !tpStatus.equals(BasicDAO.GET_ALL_STRING)){
		mapper.addArgument("tpStatus", tpStatus, SccPreRelatorioEventosDAONativeSQL.FILTRO_STATUS_CDR);
	}
	
	mapper.setProjections(SccPreRelatorioEventosDAONativeSQL.SQL_GROUP);
	
	mapper.addResultMap("dtReferencia", Date.class);
	mapper.addResultMap("dsOperadoraClaro",String.class);
	mapper.addResultMap("dsOperadoraExterna", String.class);
	mapper.addResultMap("statusChamada", String.class);
	mapper.addResultMap("motivoRejeicao", String.class);
	mapper.addResultMap("dsDefeito", String.class);
	mapper.addResultMap("quantidadeCdrs", Double.class);
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
