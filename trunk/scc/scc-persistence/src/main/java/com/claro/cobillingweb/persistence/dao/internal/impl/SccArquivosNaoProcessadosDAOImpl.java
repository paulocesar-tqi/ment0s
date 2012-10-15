/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivosNaoProcessadosDAO;
import com.claro.cobillingweb.persistence.dao.query.SccArquivosNaoProcessadosDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccArquivosNaoProcessadosView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccArquivosNaoProcessadosDAOImpl extends HibernateBasicDAOImpl<SccArquivosNaoProcessadosView>
		implements SccArquivosNaoProcessadosDAO {

	@Override
	public List<SccArquivosNaoProcessadosView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccArquivosNaoProcessadosView> gerarRelatorioArquivosNaoProcessados(Date dtInicial, Date dtFinal, String noArquivoGerado) throws DAOException {
		
		List<SccArquivosNaoProcessadosView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccArquivosNaoProcessadosView> mapper = new NativeSQLViewMapper<SccArquivosNaoProcessadosView>(session, SccArquivosNaoProcessadosDAONativeSQL.SQL, SccArquivosNaoProcessadosView.class);
			
			if(noArquivoGerado != null && !noArquivoGerado.equals("")){
				mapper.addArgument("noArquivoGerado", noArquivoGerado, SccArquivosNaoProcessadosDAONativeSQL.FILTRO_NO_ARQUIVO_GERADO);
			}			
			
			mapper.addArgument("dtInicial", dtInicial);
			mapper.addArgument("dtFinal", dtFinal);
			 
			mapper.addResultMap("dtRelatorio", Date.class);
			mapper.addResultMap("noArquivoRetorno", String.class);
			mapper.addResultMap("qtChamadas", Integer.class);
			mapper.addResultMap("qtMinutoTarifados", Integer.class);
			mapper.addResultMap("vlLiquido", Double.class);
			mapper.addResultMap("dtRecebimento", Date.class);
			
			mapper.setProjections(SccArquivosNaoProcessadosDAONativeSQL.SQL_GROUP);
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
