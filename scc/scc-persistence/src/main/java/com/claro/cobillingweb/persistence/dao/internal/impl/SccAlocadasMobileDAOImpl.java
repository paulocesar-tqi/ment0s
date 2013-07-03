/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccAlocadasMobileDAO;
import com.claro.cobillingweb.persistence.dao.query.SccAlocadasMobileDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccAlocadasMobileView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccAlocadasMobileDAOImpl extends HibernateBasicDAOImpl<SccAlocadasMobileView>
		implements SccAlocadasMobileDAO {

	@Override
	public List<SccAlocadasMobileView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccAlocadasMobileView> gerarRelatorioAlocadasMobile(Date dtInicial, Date dtFinal, String noArquivoGerado) throws DAOException {
		
		List<SccAlocadasMobileView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccAlocadasMobileView> mapper = new NativeSQLViewMapper<SccAlocadasMobileView>(session, SccAlocadasMobileDAONativeSQL.SQL, SccAlocadasMobileView.class);
			
			if(noArquivoGerado != null && !noArquivoGerado.equals("")){
				mapper.addArgument("noArquivoGerado", noArquivoGerado, SccAlocadasMobileDAONativeSQL.FILTRO_NO_ARQUIVO_GERADO);
			}			
			
			mapper.addArgument("dtInicial", dtInicial);
			mapper.addArgument("dtFinal", dtFinal);
			
			mapper.addResultMap("noArquivoReferencia", String.class);
			mapper.addResultMap("dtRelatorio", Date.class);
			mapper.addResultMap("cdCiclo", Integer.class);
			mapper.addResultMap("qtChamadas", Integer.class);
			mapper.addResultMap("qtMinutoTarifados", Double.class);
			mapper.addResultMap("vlLiquido", Double.class);
			mapper.addResultMap("dtChamada", Date.class);
			
			mapper.setProjections(SccAlocadasMobileDAONativeSQL.SQL_GROUP);
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
