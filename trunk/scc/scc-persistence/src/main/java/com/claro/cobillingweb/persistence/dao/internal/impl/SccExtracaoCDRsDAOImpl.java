/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccExtracaoCDRsDAO;
import com.claro.cobillingweb.persistence.dao.query.SccExtracaoCDRsDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccExtracaoCDRsView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92031709
 *
 */
public class SccExtracaoCDRsDAOImpl extends HibernateBasicDAOImpl<SccExtracaoCDRsView>
		implements SccExtracaoCDRsDAO {

	@Override
	public List<SccExtracaoCDRsView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccExtracaoCDRsView> gerarRelatorioExtracaoCDRs(Date dtChamadaInicial, Date dtChamadaFinal, String nuMsisdnOrigem) throws DAOException {
		
		List<SccExtracaoCDRsView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccExtracaoCDRsView> mapper = new NativeSQLViewMapper<SccExtracaoCDRsView>(session, SccExtracaoCDRsDAONativeSQL.SQL, SccExtracaoCDRsView.class);
			
			if(nuMsisdnOrigem != null && !nuMsisdnOrigem.equals("")){
				mapper.addArgument("nuMsisdnOrigem", nuMsisdnOrigem, SccExtracaoCDRsDAONativeSQL.FILTRO_MSISDN_ORIGEM);
			}			
			
			mapper.addArgument("dtChamadaInicial", dtChamadaInicial);
			mapper.addArgument("dtChamadaFinal", dtChamadaFinal);

			mapper.addResultMap("numeroDeA", String.class);
			mapper.addResultMap("numeroDeB", String.class);
			mapper.addResultMap("duracaoTarifada", Double.class);
			mapper.addResultMap("dtChamada", Date.class);
			mapper.addResultMap("statusChamada", String.class);
			mapper.addResultMap("motivoRejeicao", String.class);
			
			mapper.setProjections(SccExtracaoCDRsDAONativeSQL.SQL_GROUP);
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
