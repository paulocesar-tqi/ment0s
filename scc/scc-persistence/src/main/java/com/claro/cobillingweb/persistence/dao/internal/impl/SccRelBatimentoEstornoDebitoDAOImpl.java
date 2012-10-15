package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccRelBatimentoEstornoDebitoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccRelBatimentoEstornoDebitoDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccRelBatimentoEstornoDebitoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92038883
 *
 */

	
public class SccRelBatimentoEstornoDebitoDAOImpl extends HibernateBasicDAOImpl<SccRelBatimentoEstornoDebitoView>
	implements SccRelBatimentoEstornoDebitoDAO {

	@Override
	public List<SccRelBatimentoEstornoDebitoView> getAll() throws DAOException {

	return null;
	}

	@Override
	public List<SccRelBatimentoEstornoDebitoView> gerarRelBatimentoEstornoDebito(String cdEOTLD, String cdEOTClaro, 
			String cdStatusArquivo, Integer mmCiclo, Integer aaCiclo) throws DAOException {

	List<SccRelBatimentoEstornoDebitoView> list = null;
	try {
		Session session = getSessionFactory().getCurrentSession();
		NativeSQLViewMapper<SccRelBatimentoEstornoDebitoView> mapper = new NativeSQLViewMapper<SccRelBatimentoEstornoDebitoView>(session, SccRelBatimentoEstornoDebitoDAONativeSQL.SQL, SccRelBatimentoEstornoDebitoView.class);
		
		if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("cdEOTClaro", cdEOTClaro, SccRelBatimentoEstornoDebitoDAONativeSQL.FILTRO_CDEOTCLARO);
		}
		
		if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("cdEOTLD", cdEOTLD, SccRelBatimentoEstornoDebitoDAONativeSQL.FILTRO_CDEOTLD);
		}
		
		if(cdStatusArquivo != null && !cdStatusArquivo.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("cdStatusArquivo", cdStatusArquivo, SccRelBatimentoEstornoDebitoDAONativeSQL.FILTRO_STATUS_CDR);
		}
		
		if(mmCiclo != null && !mmCiclo.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("mmCiclo", mmCiclo, SccRelBatimentoEstornoDebitoDAONativeSQL.FILTRO_MES_CICLO);
		}
		
		if(aaCiclo != null && !aaCiclo.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("aaCiclo", aaCiclo, SccRelBatimentoEstornoDebitoDAONativeSQL.FILTRO_ANO_CICLO);
		}
		
		mapper.addResultMap("sqArquivo", Long.class);
		mapper.addResultMap("operadoraLD",String.class);
		mapper.addResultMap("operadoraClaro", String.class);
		mapper.addResultMap("uf", String.class);
		mapper.addResultMap("noArquivo", String.class);
		mapper.addResultMap("dsStatus", Date.class);
		mapper.addResultMap("valorTotalNf", Double.class);
		mapper.addResultMap("valorTotalImpugnado", Double.class);
		mapper.addResultMap("dtConnect", Date.class);
		
		
		list = mapper.execute();
	} catch(Exception e) {
		throw new DAOException(e.getMessage(), e);
	}
	return list;
	}
	
	

}
