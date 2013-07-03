/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccEncaminhadoMobileDAO;
import com.claro.cobillingweb.persistence.dao.query.SccEncaminhadoMobileDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccEncaminhadoMobileView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccEncaminhadoMobileDAOImpl extends HibernateBasicDAOImpl<SccEncaminhadoMobileView>
		implements SccEncaminhadoMobileDAO {

	@Override
	public List<SccEncaminhadoMobileView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccEncaminhadoMobileView> gerarRelatorioEncaminhadoMobile(Date dtInicial, Date dtFinal, String noArquivoGerado) throws DAOException {
		
		List<SccEncaminhadoMobileView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccEncaminhadoMobileView> mapper = new NativeSQLViewMapper<SccEncaminhadoMobileView>(session, SccEncaminhadoMobileDAONativeSQL.SQL, SccEncaminhadoMobileView.class);
			
			if(noArquivoGerado != null && !noArquivoGerado.equals("")){
				mapper.addArgument("noArquivoGerado", noArquivoGerado, SccEncaminhadoMobileDAONativeSQL.FILTRO_NO_ARQUIVO_GERADO);
			}			
			
			mapper.addArgument("dtInicial", dtInicial);
			mapper.addArgument("dtFinal", dtFinal);

			mapper.addResultMap("dtRelatorio", Date.class);
			mapper.addResultMap("noArquivoReferencia", String.class);
			mapper.addResultMap("qtChamadas", Integer.class);
			mapper.addResultMap("qtMinutoTarifados", Double.class);
			mapper.addResultMap("vlLiquido", Double.class);
			mapper.addResultMap("nuItem", Integer.class);
			mapper.addResultMap("sqRelatorioSumarizado", Integer.class);
			mapper.addResultMap("dtProcClaro", Date.class);
						
			mapper.setProjections(SccEncaminhadoMobileDAONativeSQL.SQL_GROUP);
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
