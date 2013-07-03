package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioQuestionamentoResultadoDAO;
import com.claro.cobillingweb.persistence.dao.query.SccQuestionamentoSQL;
import com.claro.cobillingweb.persistence.dao.query.SccRelatorioQuestionamentoResultadoDAONativeSQL;
import com.claro.cobillingweb.persistence.view.SccQuestionamentoView;
import com.claro.cobillingweb.persistence.view.SccRelatorioQuestionamentoResultadoView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

	/**
	 * @author 92038883
	 *
	 */

public class SccRelatorioQuestionamentoResultadoDAOImpl extends HibernateBasicDAOImpl<SccRelatorioQuestionamentoResultadoView>
	implements SccRelatorioQuestionamentoResultadoDAO {

	
	@Override
	public Collection<SccQuestionamentoView> gerarCombo() throws DAOException {
		
		Collection<SccQuestionamentoView> colecao = null;
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			
			NativeSQLViewMapper<SccQuestionamentoView> mapper = new NativeSQLViewMapper<SccQuestionamentoView>(session, SccQuestionamentoSQL.SQL_COMBO, SccQuestionamentoView.class);
			
			mapper.addResultMap("sqQuestionamento", Long.class);
			mapper.addResultMap("cdEotLD", String.class);
			mapper.addResultMap("descricaoEotLD", String.class);
			
			colecao = (Collection<SccQuestionamentoView>)mapper.execute();
			
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return colecao;
	}
	
	
	@Override
	public List<SccRelatorioQuestionamentoResultadoView> getAll() throws DAOException {

	return null;
	}

	@Override
	public List<SccRelatorioQuestionamentoResultadoView> gerarRelatorioQuestionamentoResultado(String sqQuestionamento, String cdEOTLD, String tpStatus) throws DAOException {

	List<SccRelatorioQuestionamentoResultadoView> list = null;
	try {
		Session session = getSessionFactory().getCurrentSession();
		NativeSQLViewMapper<SccRelatorioQuestionamentoResultadoView> mapper = new NativeSQLViewMapper<SccRelatorioQuestionamentoResultadoView>(session, SccRelatorioQuestionamentoResultadoDAONativeSQL.SQL, SccRelatorioQuestionamentoResultadoView.class);
		
		if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("cdEOTLD", cdEOTLD, SccRelatorioQuestionamentoResultadoDAONativeSQL.FILTRO_CDEOTLD);
		}
		
		if(tpStatus != null && !tpStatus.equals(BasicDAO.GET_ALL_STRING)){
			mapper.addArgument("tpStatus", tpStatus, SccRelatorioQuestionamentoResultadoDAONativeSQL.FILTRO_STATUS_CDR);
		}
		
		if(sqQuestionamento != null && !sqQuestionamento.equals(BasicDAO.GET_ALL.toString())){
			mapper.addArgument("sqQuestionamento", sqQuestionamento, SccRelatorioQuestionamentoResultadoDAONativeSQL.FILTRO_SQQUESTIONAMENTO);
		}
		
		mapper.addResultMap("sqQuestionamento", Long.class);
		mapper.addResultMap("sqArquivo", Long.class);
		mapper.addResultMap("cdEotLD", String.class);
		mapper.addResultMap("cdEotClaro", String.class);
    	mapper.addResultMap("dtProtocoloLD", String.class);
		mapper.addResultMap("dtProtocoloClaro", String.class);
		mapper.addResultMap("dtCorrecao", String.class);		
		mapper.addResultMap("dtAnalise", String.class);
		mapper.addResultMap("vlPotencialLD", Double.class);
		mapper.addResultMap("vlPotencialClaro", Double.class);
		mapper.addResultMap("vlPenalidadeLD", Double.class);
		mapper.addResultMap("vlPenalidadeClaro", Double.class);
		mapper.addResultMap("peProcedente", Double.class);
		mapper.addResultMap("peSemAnalise", Double.class);
		mapper.addResultMap("qtCdrs", Long.class);
		mapper.addResultMap("miTarifados", Long.class);
		mapper.addResultMap("dtRepasse", String.class);
		mapper.addResultMap("criacaoDt", Date.class);
		mapper.addResultMap("atualizacaoDt", Date.class);
		mapper.addResultMap("usuarioManutCd", String.class);
		mapper.addResultMap("noArquivo", String.class);
				
		list = mapper.execute();
	} catch(Exception e) {
		throw new DAOException(e.getMessage(), e);
	}
	return list;
	}
	
	
}
