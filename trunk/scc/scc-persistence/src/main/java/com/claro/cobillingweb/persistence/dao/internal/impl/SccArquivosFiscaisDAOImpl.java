/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccArquivosFiscaisDAO;
import com.claro.cobillingweb.persistence.dao.query.SccArquivosFiscaisDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccArquivosFiscaisView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccArquivosFiscaisDAOImpl extends HibernateBasicDAOImpl<SccArquivosFiscaisView>
		implements SccArquivosFiscaisDAO {

	@Override
	public List<SccArquivosFiscaisView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccArquivosFiscaisView> gerarRelatorioArquivosFiscais(String sgUF, String cdStatusArquivo, String cdCSP, Integer cdCiclo, Integer mesCiclo, Integer anoCiclo) throws DAOException {
		
		List<SccArquivosFiscaisView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccArquivosFiscaisView> mapper = new NativeSQLViewMapper<SccArquivosFiscaisView>(session, SccArquivosFiscaisDAONativeSQL.SQL, SccArquivosFiscaisView.class);
			
			if(sgUF != null && !sgUF.equals("")){
				mapper.addArgument("sgUF", sgUF, SccArquivosFiscaisDAONativeSQL.FILTRO_SG_UF);
			}			
			if(cdStatusArquivo != null && !cdStatusArquivo.equals("")){
				mapper.addArgument("cdStatusArquivo", cdStatusArquivo , SccArquivosFiscaisDAONativeSQL.FILTRO_STATUS_ARQUIVO);
			}
			if(cdCSP != null && !cdCSP.equals("")){
				mapper.addArgument("cdCSP", cdCSP, SccArquivosFiscaisDAONativeSQL.FILTRO_CD_CSP);
			}
			if(cdCiclo != null && !cdCiclo.equals("")){
				mapper.addArgument("codigoCiclo", cdCiclo , SccArquivosFiscaisDAONativeSQL.FILTRO_CD_CICLO);
			}
			if(mesCiclo != null && !mesCiclo.equals("")){
				mapper.addArgument("mesCiclo", mesCiclo, SccArquivosFiscaisDAONativeSQL.FILTRO_MES_CICLO);
			}
						
			mapper.addArgument("anoCiclo", anoCiclo);
			
			mapper.addResultMap("cdCSP", String.class);
			mapper.addResultMap("sgUF", String.class);
			mapper.addResultMap("anoCiclo", Integer.class);
			mapper.addResultMap("mesCiclo", Integer.class);
			mapper.addResultMap("codigoCiclo", Integer.class);
			mapper.addResultMap("noArquivo", String.class);
			mapper.addResultMap("noDiretorioArquivo", String.class);
			mapper.addResultMap("dtStatusArquivo", Date.class);
			mapper.addResultMap("cdStatusArquivo", String.class);
			mapper.addResultMap("cdMotivoRejeicaoArquivo", String.class);
			mapper.addResultMap("dtConnect", Date.class);
			mapper.addResultMap("qtRegistros", Integer.class);
			mapper.addResultMap("vlBrutoArquivo", Double.class);
			mapper.addResultMap("vlICMS", Double.class);
			
			mapper.setProjections(SccArquivosFiscaisDAONativeSQL.SQL_GROUP);
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
