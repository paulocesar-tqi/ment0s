/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccPosRelatorioDisputaDetalheDAO;
import com.claro.cobillingweb.persistence.dao.query.SccPosRelatorioDisputaDetalheDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccPosRelatorioDisputaDetalheView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

/**
 * @author 92038883
 *
 */
public class SccPosRelatorioDisputaDetalheDAOImpl extends HibernateBasicDAOImpl<SccPosRelatorioDisputaDetalheView>
		implements SccPosRelatorioDisputaDetalheDAO {

	@Override
	public List<SccPosRelatorioDisputaDetalheView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccPosRelatorioDisputaDetalheView> gerarPosRelatorioDisputaDetalhe(String dtReferencia, String cdEotLD) throws DAOException {
		
		List<SccPosRelatorioDisputaDetalheView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccPosRelatorioDisputaDetalheView> mapper = new NativeSQLViewMapper<SccPosRelatorioDisputaDetalheView>(session, SccPosRelatorioDisputaDetalheDAONativeSQL.SQL, SccPosRelatorioDisputaDetalheView.class);
			
			if(cdEotLD != null && !cdEotLD.equals("")){
				mapper.addArgument("cdEotLD", cdEotLD, SccPosRelatorioDisputaDetalheDAONativeSQL.FILTRO_CD_CSP);
			}			
			
			mapper.addArgument("dtReferencia", dtReferencia);
			
			mapper.addResultMap("sqDisputa", Long.class);
			mapper.addResultMap("cdEotLD", String.class);
			mapper.addResultMap("cdStatusDisputa", String.class);
			mapper.addResultMap("inRiscoDisputa", String.class);
			mapper.addResultMap("dtCriacao", Date.class);
			mapper.addResultMap("dtAtualizacao", Date.class);
			mapper.addResultMap("cdUsuarioManut", String.class);

			
						 
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
}
