/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccAssexuadosDAO;
import com.claro.cobillingweb.persistence.dao.query.SccAssexuadosDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccAssexuadosView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccAssexuadosDAOImpl extends HibernateBasicDAOImpl<SccAssexuadosView>
		implements SccAssexuadosDAO {

	@Override
	public List<SccAssexuadosView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccAssexuadosView> gerarRelatorioAssexuados(Date dtInicial, Date dtFinal, String noArquivoGerado) throws DAOException {
		
		List<SccAssexuadosView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccAssexuadosView> mapper = new NativeSQLViewMapper<SccAssexuadosView>(session, SccAssexuadosDAONativeSQL.SQL, SccAssexuadosView.class);
			
			if(noArquivoGerado != null && !noArquivoGerado.equals("")){
				mapper.addArgument("noArquivoGerado", noArquivoGerado, SccAssexuadosDAONativeSQL.FILTRO_NO_ARQUIVO_GERADO);
			}			
			
			mapper.addArgument("dtInicial", dtInicial);
			mapper.addArgument("dtFinal", dtFinal);

			mapper.addResultMap("dtRelatorio", Date.class);
			mapper.addResultMap("numeroDeA", String.class);
			mapper.addResultMap("qtChamadas", Integer.class);
			mapper.addResultMap("qtMinutoTarifados", Integer.class);
			mapper.addResultMap("vlLiquido", Double.class);
			mapper.addResultMap("cdErroReciclagem", String.class);
			mapper.addResultMap("nuItem", Integer.class);
			mapper.addResultMap("sqRelatorioSumarizado", Integer.class);
			
			mapper.setProjections(SccAssexuadosDAONativeSQL.SQL_GROUP);
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
