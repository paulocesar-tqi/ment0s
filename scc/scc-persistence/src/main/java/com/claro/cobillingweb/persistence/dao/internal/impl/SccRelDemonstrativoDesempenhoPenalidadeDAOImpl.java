/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccRelDemonstrativoDesempenhoPenalidadeDAO;
import com.claro.cobillingweb.persistence.dao.query.SccRelDemonstrativoDesempenhoPenalidadeDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccRelDemonstrativoDesempenhoPenalidadeView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92038883
 *
 */
public class SccRelDemonstrativoDesempenhoPenalidadeDAOImpl extends HibernateBasicDAOImpl<SccRelDemonstrativoDesempenhoPenalidadeView>
		implements SccRelDemonstrativoDesempenhoPenalidadeDAO {

	@Override
	public List<SccRelDemonstrativoDesempenhoPenalidadeView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccRelDemonstrativoDesempenhoPenalidadeView> gerarRelDemonstrativoDesempenhoPenalidade(String dtReferencia, String cdCSP) throws DAOException {
		
		List<SccRelDemonstrativoDesempenhoPenalidadeView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccRelDemonstrativoDesempenhoPenalidadeView> mapper = new NativeSQLViewMapper<SccRelDemonstrativoDesempenhoPenalidadeView>(session, SccRelDemonstrativoDesempenhoPenalidadeDAONativeSQL.SQL, SccRelDemonstrativoDesempenhoPenalidadeView.class);
			
			/*if(cdCSP != null && !cdCSP.equals("")){
				mapper.addArgument("cdCSP", cdCSP, SccRelDemonstrativoDesempenhoPenalidadeDAONativeSQL.FILTRO_CD_CSP);
			}*/			
			
			mapper.addArgument("cdCSP", cdCSP);
			mapper.addArgument("dtReferencia", dtReferencia);

			mapper.addResultMap("indicador", String.class);
			mapper.addResultMap("descIndicador", String.class);
			mapper.addResultMap("respIndicador", String.class);
			mapper.addResultMap("vlOrigem", Double.class);
			mapper.addResultMap("metaIndicador", Double.class);
			mapper.addResultMap("pesoIndicador", Double.class);


			
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
