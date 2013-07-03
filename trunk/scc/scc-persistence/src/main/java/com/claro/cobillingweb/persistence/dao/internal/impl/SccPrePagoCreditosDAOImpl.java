/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPrePagoCreditosDAO;
import com.claro.cobillingweb.persistence.dao.query.SccPrePagoCreditosDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccPrePagoCreditosView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccPrePagoCreditosDAOImpl extends HibernateBasicDAOImpl<SccPrePagoCreditosView>
		implements SccPrePagoCreditosDAO {

	@Override
	public List<SccPrePagoCreditosView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccPrePagoCreditosView> gerarRelatorioPrePagoCreditos(Date dtInicial, Date dtFinal, String tipoCredito, String cdStatusCredito, String operadoraClaro, String operadoraLD) throws DAOException {
		
		List<SccPrePagoCreditosView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccPrePagoCreditosView> mapper = new NativeSQLViewMapper<SccPrePagoCreditosView>(session, SccPrePagoCreditosDAONativeSQL.SQL, SccPrePagoCreditosView.class);
	
			if(operadoraLD != null && !operadoraLD.equals("")){
				mapper.addArgument("operadoraLD", operadoraLD, SccPrePagoCreditosDAONativeSQL.FILTRO_CD_EOT_LD);
			}
			if(operadoraClaro != null && !operadoraClaro.equals("")){
				mapper.addArgument("operadoraClaro", operadoraClaro, SccPrePagoCreditosDAONativeSQL.FILTRO_CD_EOT_CLARO);
			}
			if(tipoCredito != null && !tipoCredito.equals("")){
				mapper.addArgument("tipoCredito", tipoCredito, SccPrePagoCreditosDAONativeSQL.FILTRO_TP_CREDITO);
			}
			if(cdStatusCredito != null && !cdStatusCredito.equals("")){
				mapper.addArgument("cdStatusCredito", cdStatusCredito, SccPrePagoCreditosDAONativeSQL.FILTRO_CD_STATUS_CREDITO);
			}
			mapper.addArgument("dtInicial", dtInicial);
			mapper.addArgument("dtFinal", dtFinal);

			mapper.addResultMap("operadoraLD",String.class);
			mapper.addResultMap("operadoraClaro",String.class);
			mapper.addResultMap("sqArquivo",Integer.class);
			mapper.addResultMap("noArquivo",String.class);
			mapper.addResultMap("tpCredito",String.class);
			mapper.addResultMap("cdStatus",String.class);
			mapper.addResultMap("dsCredito",String.class);
			mapper.addResultMap("dsStatus",String.class);
			mapper.addResultMap("vlCredito",Double.class);
			mapper.addResultMap("dtCredito",Date.class);
			mapper.addResultMap("sqCredito",Integer.class);
			mapper.addResultMap("numeroA",String.class);
			mapper.addResultMap("quantidade",Integer.class);
			mapper.addResultMap("minutosQueimados",Integer.class);
			mapper.addResultMap("minutosAjustados",Integer.class);
			
			mapper.appendSQL(SccPrePagoCreditosDAONativeSQL.SQL_GROUP);
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
