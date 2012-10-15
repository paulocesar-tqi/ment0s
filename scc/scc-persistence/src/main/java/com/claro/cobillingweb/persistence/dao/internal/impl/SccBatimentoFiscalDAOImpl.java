/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccBatimentoFiscalDAO;
import com.claro.cobillingweb.persistence.dao.query.SccBatimentoFiscalDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccBatimentoFiscalView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccBatimentoFiscalDAOImpl extends HibernateBasicDAOImpl<SccBatimentoFiscalView>
		implements SccBatimentoFiscalDAO {

	@Override
	public List<SccBatimentoFiscalView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccBatimentoFiscalView> gerarRelatorioBatimentoFiscal(Integer mesCiclo, Integer anoCiclo, Integer cdCiclo, String cdCSP) throws DAOException {
		
		List<SccBatimentoFiscalView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccBatimentoFiscalView> mapper = new NativeSQLViewMapper<SccBatimentoFiscalView>(session, SccBatimentoFiscalDAONativeSQL.SQL, SccBatimentoFiscalView.class);
			
			if(cdCSP != null && !cdCSP.equals("")){
				mapper.addArgument("cdCSP", cdCSP, SccBatimentoFiscalDAONativeSQL.FILTRO_CD_CSP);
			}			
			if(cdCiclo != null && !cdCiclo.equals("")){
				mapper.addArgument("cdCiclo", cdCiclo, SccBatimentoFiscalDAONativeSQL.FILTRO_CD_CICLO);
			}
			
			mapper.addArgument("mesCiclo", mesCiclo);
			mapper.addArgument("anoCiclo", anoCiclo);
			
			mapper.addResultMap("cdCSP", String.class);
			mapper.addResultMap("nomeOperadora", String.class);
			mapper.addResultMap("nomeEmpresa", String.class);
			mapper.addResultMap("cdEOTClaro", String.class);
			mapper.addResultMap("sgUF", String.class);
			mapper.addResultMap("noArquivo", String.class);
			mapper.addResultMap("cdStatusArquivo", String.class);
			mapper.addResultMap("cdCiclo", Integer.class);
			mapper.addResultMap("vlTotalNF", Double.class);
			mapper.addResultMap("dtConnect", Date.class);
			
			mapper.setProjections(SccBatimentoFiscalDAONativeSQL.SQL_ORDER);
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
