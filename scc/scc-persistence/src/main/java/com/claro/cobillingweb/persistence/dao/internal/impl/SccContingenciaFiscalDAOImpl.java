/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccContingenciaFiscalDAO;
import com.claro.cobillingweb.persistence.dao.query.SccContingenciaFiscalDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccContingenciaFiscalView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccContingenciaFiscalDAOImpl extends HibernateBasicDAOImpl<SccContingenciaFiscalView>
		implements SccContingenciaFiscalDAO {

	@Override
	public List<SccContingenciaFiscalView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccContingenciaFiscalView> gerarRelatorioContingenciaFiscal(String dtReferencia, String cdCSP) throws DAOException {
		
		List<SccContingenciaFiscalView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccContingenciaFiscalView> mapper = new NativeSQLViewMapper<SccContingenciaFiscalView>(session, SccContingenciaFiscalDAONativeSQL.SQL, SccContingenciaFiscalView.class);
			
			if(cdCSP != null && !cdCSP.equals("")){
				mapper.addArgument("cdCSP", cdCSP, SccContingenciaFiscalDAONativeSQL.FILTRO_CD_CSP);
			}			
			
			mapper.addArgument("dtReferencia", dtReferencia);
			
			mapper.addResultMap("nomeEmpresa", String.class);
			mapper.addResultMap("cdCSP", String.class);
			mapper.addResultMap("sgUF", String.class);
			mapper.addResultMap("valorFaturado", Double.class);
			mapper.addResultMap("valorBaseCalculo", Double.class);
			mapper.addResultMap("valorICMS", Double.class);
			mapper.addResultMap("valorInsencao", Double.class);
			mapper.addResultMap("nfInicial", Integer.class);
			mapper.addResultMap("nfFinal", Integer.class);
			mapper.addResultMap("razaoSocial", String.class);
			mapper.addResultMap("numeroCNPJ", String.class);
			mapper.addResultMap("inscricaoEstadual", String.class);
			mapper.addResultMap("serieNF", String.class);
			
			mapper.setProjections(SccContingenciaFiscalDAONativeSQL.SQL_GROUP);
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
