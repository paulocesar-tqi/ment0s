package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioQuestionamentoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccRelatorioQuestionamentoDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

	/**
	 * @author 92038883
	 *
	 */

public class SccRelatorioQuestionamentoDAOImpl extends HibernateBasicDAOImpl<SccRelatorioQuestionamentoView>
	implements SccRelatorioQuestionamentoDAO {

	@Override
	public List<SccRelatorioQuestionamentoView> getAll() throws DAOException {

	return null;
	}

	@Override
	public List<SccRelatorioQuestionamentoView> gerarRelatorioQuestionamento(String cdEOTLD, String tpStatus) throws DAOException {

	List<SccRelatorioQuestionamentoView> list = null;
	try {
		Session session = getSessionFactory().getCurrentSession();
		NativeSQLViewMapper<SccRelatorioQuestionamentoView> mapper = new NativeSQLViewMapper<SccRelatorioQuestionamentoView>(session, SccRelatorioQuestionamentoDAONativeSQL.SQL, SccRelatorioQuestionamentoView.class);
		
		if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("cdEOTLD", cdEOTLD, SccRelatorioQuestionamentoDAONativeSQL.FILTRO_CDEOTLD);
		}
		
		if(tpStatus != null && !tpStatus.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("tpStatus", tpStatus, SccRelatorioQuestionamentoDAONativeSQL.FILTRO_STATUS_CDR);
		}
		
		mapper.setProjections(SccRelatorioQuestionamentoDAONativeSQL.SQL_GROUP);
		
		mapper.addResultMap("sqQuestionamento", Long.class);
		mapper.addResultMap("cdEotLD", String.class);
		mapper.addResultMap("cdStatusQuestionamento",String.class);
		mapper.addResultMap("dtCredito", Date.class);
		mapper.addResultMap("dtProtocoloClaro", String.class);
		mapper.addResultMap("dtProtocoloLD", String.class);
		mapper.addResultMap("dtAnalise", String.class);
		mapper.addResultMap("dtCorrecao", String.class);
		mapper.addResultMap("txComentarioClaro", String.class);
		mapper.addResultMap("txComentarioLD", String.class);
		mapper.addResultMap("txAnalise", String.class);
		mapper.addResultMap("txCorrecao", String.class);
		mapper.addResultMap("txMotivosRejeicao", String.class);
		mapper.addResultMap("txArquivos", String.class);
		mapper.addResultMap("cdIdentificacaoCartaClaro", String.class);
		mapper.addResultMap("cdIdentificacaoCartaLD", String.class);
		mapper.addResultMap("noResponsavelClaro", String.class);
		mapper.addResultMap("noResponsavelLD", String.class);
		mapper.addResultMap("cdProcesso", String.class);
		mapper.addResultMap("criacaoDt", Date.class);
		mapper.addResultMap("atualizacaoDt", String.class);
		mapper.addResultMap("usuarioManutCd", String.class);
		mapper.addResultMap("miDuracaoTarifada", Double.class);
		mapper.addResultMap("vlLiquidoChamada", Double.class);
		mapper.addResultMap("vlBrutoChamada", Double.class);
		mapper.addResultMap("vlPotencialMulta", Double.class);
		
				
		list = mapper.execute();
	} catch(Exception e) {
		throw new DAOException(e.getMessage(), e);
	}
	return list;
	}
	
	
}
