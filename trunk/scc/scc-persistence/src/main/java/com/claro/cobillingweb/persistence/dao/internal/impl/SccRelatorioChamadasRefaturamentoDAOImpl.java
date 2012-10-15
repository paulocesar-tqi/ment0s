package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioChamadasRefaturamentoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccRelatorioChamadasRefaturamentoDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccRelatorioChamadasRefaturamentoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92038883
 *
 */

public class SccRelatorioChamadasRefaturamentoDAOImpl extends HibernateBasicDAOImpl<SccRelatorioChamadasRefaturamentoView>
	implements SccRelatorioChamadasRefaturamentoDAO {

	@Override
	public List<SccRelatorioChamadasRefaturamentoView> getAll() throws DAOException {

	return null;
	}

	@Override
	public List<SccRelatorioChamadasRefaturamentoView> gerarRelatorioChamadasRefaturamento(Date dtInicioPeriodo, Date dtFimPeriodo, 
			String cdEOTLD, String cdEOTClaro, String cdRefaturamento) throws DAOException {

	List<SccRelatorioChamadasRefaturamentoView> list = null;
	try {
		Session session = getSessionFactory().getCurrentSession();
		NativeSQLViewMapper<SccRelatorioChamadasRefaturamentoView> mapper = new NativeSQLViewMapper<SccRelatorioChamadasRefaturamentoView>(session, SccRelatorioChamadasRefaturamentoDAONativeSQL.SQL, SccRelatorioChamadasRefaturamentoView.class);
		mapper.addArgument("dtInicioPeriodo", dtInicioPeriodo);
		mapper.addArgument("dtFimPeriodo", dtFimPeriodo);
		
		if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("cdEOTClaro", cdEOTClaro, SccRelatorioChamadasRefaturamentoDAONativeSQL.FILTRO_CDEOTCLARO);
		}
		
		if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("cdEOTLD", cdEOTLD, SccRelatorioChamadasRefaturamentoDAONativeSQL.FILTRO_CDEOTLD);
		}
		
		if(cdRefaturamento != null && !cdRefaturamento.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("cdRefaturamento", cdRefaturamento, SccRelatorioChamadasRefaturamentoDAONativeSQL.FILTRO_STATUS_CDR);
		}else{
			mapper.appendSQL(SccRelatorioChamadasRefaturamentoDAONativeSQL.FILTRO_CODIGO_IN);
		}
		
		
		mapper.addResultMap("numeroA", String.class);
		mapper.addResultMap("numeroB",String.class);
		mapper.addResultMap("dataHora", Date.class);
		mapper.addResultMap("valor", Double.class);
		mapper.addResultMap("minutoTarifado", Double.class);
		mapper.addResultMap("codigoCriticaInicial", String.class);
		mapper.addResultMap("arquivoRemessa", Integer.class);
		mapper.addResultMap("arquivoRetorno", Integer.class);
		mapper.addResultMap("arquivoRemessaRefaturamento", Integer.class);
		
		
		list = mapper.execute();
	} catch(Exception e) {
		throw new DAOException(e.getMessage(), e);
	}
	return list;
	}

	
	
}
