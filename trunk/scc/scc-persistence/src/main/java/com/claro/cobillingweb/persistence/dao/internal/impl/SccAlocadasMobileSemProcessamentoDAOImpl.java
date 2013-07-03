/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccAlocadasMobileSemProcessamentoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccAlocadasMobileSemProcessamentoDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccAlocadasMobileSemProcessamentoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccAlocadasMobileSemProcessamentoDAOImpl extends HibernateBasicDAOImpl<SccAlocadasMobileSemProcessamentoView>
		implements SccAlocadasMobileSemProcessamentoDAO {

	@Override
	public List<SccAlocadasMobileSemProcessamentoView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccAlocadasMobileSemProcessamentoView> gerarRelatorioAlocadasMobileSemProcessamento(Date dtInicial, Date dtFinal, String noArquivoGerado) throws DAOException {
		
		List<SccAlocadasMobileSemProcessamentoView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccAlocadasMobileSemProcessamentoView> mapper = new NativeSQLViewMapper<SccAlocadasMobileSemProcessamentoView>(session, SccAlocadasMobileSemProcessamentoDAONativeSQL.SQL, SccAlocadasMobileSemProcessamentoView.class);
			
			if(noArquivoGerado != null && !noArquivoGerado.equals("")){
				mapper.addArgument("noArquivoGerado", noArquivoGerado, SccAlocadasMobileSemProcessamentoDAONativeSQL.FILTRO_NO_ARQUIVO_GERADO);
			}			
			
			mapper.addArgument("dtInicial", dtInicial);
			mapper.addArgument("dtFinal", dtFinal);
			 
			mapper.addResultMap("noArquivoReferencia", String.class);
			mapper.addResultMap("dtRelatorio", Date.class);
			mapper.addResultMap("cdCiclo", Integer.class);
			mapper.addResultMap("qtChamadas", Integer.class);
			mapper.addResultMap("qtMinutoTarifados", Double.class);
			mapper.addResultMap("vlLiquido", Double.class);
			mapper.addResultMap("dtReferencia", Date.class);
			
			mapper.setProjections(SccAlocadasMobileSemProcessamentoDAONativeSQL.SQL_GROUP);
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
