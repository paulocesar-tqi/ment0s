/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccConfirmacaoRepasseDAO;
import com.claro.cobillingweb.persistence.dao.query.SccConfirmacaoRepasseDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccConfirmacaoRepasseView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccConfirmacaoRepasseDAOImpl extends HibernateBasicDAOImpl<SccConfirmacaoRepasseView>
		implements SccConfirmacaoRepasseDAO {

	@Override
	public List<SccConfirmacaoRepasseView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccConfirmacaoRepasseView> gerarRelatorioConfirmacaoRepasse(String dtFechamento, String cdEOTLD, String cdStatusRepasse) throws DAOException {
		
		List<SccConfirmacaoRepasseView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccConfirmacaoRepasseView> mapper = new NativeSQLViewMapper<SccConfirmacaoRepasseView>(session, SccConfirmacaoRepasseDAONativeSQL.SQL, SccConfirmacaoRepasseView.class);
			
			if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTLD", cdEOTLD, SccConfirmacaoRepasseDAONativeSQL.FILTRO_CDEOTLD);
			}
			if(cdStatusRepasse != null && !cdStatusRepasse.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdStatusRepasse", cdStatusRepasse, SccConfirmacaoRepasseDAONativeSQL.FILTRO_CD_STATUS_REPASSE);
			}			
			
			mapper.addArgument("dtFechamento", dtFechamento);
			
			mapper.addResultMap("anoMesRepasse", String.class);
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("operadoraClaro", String.class);
			mapper.addResultMap("valorRepasse", Double.class);
			mapper.addResultMap("statusRepasse", String.class);
			
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
