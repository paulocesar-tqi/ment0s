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
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioConciliacaoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccRelatorioConciliacaoDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccRelatorioConciliacaoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 93046251
 *
 */
public class SccRelatorioConciliacaoDAOImpl extends HibernateBasicDAOImpl<SccRelatorioConciliacaoView>
		implements SccRelatorioConciliacaoDAO {

	@Override
	public List<SccRelatorioConciliacaoView> getAll() throws DAOException {
		
		return null;
	}
	
	@Override
	public List<SccRelatorioConciliacaoView> search(String operadoraClaro, String operadoraExterna, Date dataInicio, Date dataFim) throws DAOException {
		
		List<SccRelatorioConciliacaoView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccRelatorioConciliacaoView> mapper = new NativeSQLViewMapper<SccRelatorioConciliacaoView>(session, SccRelatorioConciliacaoDAONativeSQL.SQL, SccRelatorioConciliacaoView.class);
			
			if (dataInicio != null) {
				mapper.addArgument("dataInicio", dataInicio, SccRelatorioConciliacaoDAONativeSQL.FILTRO_INICIO);
			}
			
			if (dataFim != null) {
				mapper.addArgument("dataFim", dataFim, SccRelatorioConciliacaoDAONativeSQL.FILTRO_FIM);
			}
			
			if(operadoraClaro != null && !operadoraClaro.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("operadoraClaro", operadoraClaro, SccRelatorioConciliacaoDAONativeSQL.FILTRO_OPERADORA_CLARO);
			}
			
			if(operadoraExterna != null && !operadoraExterna.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("operadoraExterna", operadoraExterna, SccRelatorioConciliacaoDAONativeSQL.FILTRO_OPERADORA_EXTERNA);
			}
			
			mapper.setProjections(SccRelatorioConciliacaoDAONativeSQL.GROUP_BY);
			
			//Claro
			mapper.addResultMap("dataLancamento", Date.class);
			mapper.addResultMap("codCsp", String.class);
			mapper.addResultMap("localNegocio", String.class);
			mapper.addResultMap("empresaContabil", String.class);
			mapper.addResultMap("contaCredito", String.class);
			mapper.addResultMap("contaDebito", String.class);
			mapper.addResultMap("codCentro", String.class);
			mapper.addResultMap("descricao", String.class);
			mapper.addResultMap("dataProcessamento", Date.class);
			mapper.addResultMap("valorBruto", Double.class);
			
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}

	
}
