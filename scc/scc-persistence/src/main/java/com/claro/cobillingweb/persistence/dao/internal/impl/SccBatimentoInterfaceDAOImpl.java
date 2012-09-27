/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccBatimentoInterfaceDAO;
import com.claro.cobillingweb.persistence.dao.query.SccBatimentoInterfaceDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccBatimentoInterfaceView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 93046251
 *
 */
public class SccBatimentoInterfaceDAOImpl extends HibernateBasicDAOImpl<SccBatimentoInterfaceView>
		implements SccBatimentoInterfaceDAO {

	@Override
	public List<SccBatimentoInterfaceView> getAll() throws DAOException {
		
		return null;
	}

	
	
	@Override
	public List<SccBatimentoInterfaceView> listarBatimentoInterface(Date dtInicio, Date dtFim, String cdEOTLD, String cdEOTClaro, String tpArquivo) throws DAOException {
		
		List<SccBatimentoInterfaceView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccBatimentoInterfaceView> mapper = new NativeSQLViewMapper<SccBatimentoInterfaceView>(session, SccBatimentoInterfaceDAONativeSQL.SQL, SccBatimentoInterfaceView.class);
			if(dtInicio != null) {
				mapper.addArgument("dtInicio", dtInicio, SccBatimentoInterfaceDAONativeSQL.FILTRO_DT_INICIO);
			}
			
			if(dtFim != null) {
				mapper.addArgument("dtFim", dtFim, SccBatimentoInterfaceDAONativeSQL.FILTRO_DT_FIM);
			}
			
			if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", cdEOTClaro, SccBatimentoInterfaceDAONativeSQL.FILTRO_CDEOTCLARO);
			}
			
			if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTLD", cdEOTLD, SccBatimentoInterfaceDAONativeSQL.FILTRO_CDEOTLD);
			}

			if(tpArquivo != null && !tpArquivo.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("tpArquivo", tpArquivo, SccBatimentoInterfaceDAONativeSQL.FILTRO_TIPO_ARQUIVO);
			}
			
			//Claro
			mapper.addResultMap("nomeArquivo", String.class);
			mapper.addResultMap("operadoraLD", Long.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("dataMovimentacao", Date.class);
			mapper.addResultMap("dataTransferencia", Date.class);
			mapper.addResultMap("quantidadeRegistrosMobile", Long.class);
			mapper.addResultMap("dataProcessamento", Date.class);
			mapper.addResultMap("quantidadeRegistrosScc", Long.class);
			
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}

	
}
