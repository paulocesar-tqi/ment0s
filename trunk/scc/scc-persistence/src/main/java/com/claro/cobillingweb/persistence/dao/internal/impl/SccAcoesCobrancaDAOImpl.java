package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccAcoesCobrancaDAO;
import com.claro.cobillingweb.persistence.dao.query.SccAcoesCobrancaSQL;
import com.claro.cobillingweb.persistence.filtro.SccFiltroAcoesCobranca;
import com.claro.cobillingweb.persistence.dao.query.SccFaturasSQL;
import com.claro.cobillingweb.persistence.filtro.SccFiltro;
import com.claro.cobillingweb.persistence.utils.DateUtils;
import com.claro.cobillingweb.persistence.view.SccAcoesCobrancaView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

@Repository
public class SccAcoesCobrancaDAOImpl extends HibernateBasicDAOImpl<SccAcoesCobrancaView> implements
		SccAcoesCobrancaDAO {

	@Override
	public List<SccAcoesCobrancaView> getAll() throws DAOException {
		
		return null;
	}

	@Override
	public List<SccAcoesCobrancaView> gerarRelatorioControleAcoesCobranca(
			SccFiltroAcoesCobranca filtro) throws DAOException {
		
		List<SccAcoesCobrancaView> list = null;
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccAcoesCobrancaView> mapper = new NativeSQLViewMapper<SccAcoesCobrancaView>(session, SccAcoesCobrancaSQL.SQL, SccAcoesCobrancaView.class);
			
			
			if(StringUtils.isNotEmpty(filtro.getCdCsp()) && !filtro.getCdCsp().equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdCsp", filtro.getCdCsp(), SccAcoesCobrancaSQL.FILTRO_CSP);
			}
			
			mapper.addArgument("dtInicio", DateUtils.lowDateTime(filtro.getDataInicialPeriodo()), SccAcoesCobrancaSQL.FILTRO_DT_INICIO);
			mapper.addArgument("dtFim", DateUtils.highDateTime2(filtro.getDataFinalPeriodo()), SccAcoesCobrancaSQL.FILTRO_DT_FINAL);
			
			mapper.addResultMap("ban", Long.class);
			mapper.addResultMap("terminal", String.class);
			mapper.addResultMap("faturaLD", String.class);
			mapper.addResultMap("valorFatura", Double.class);
			mapper.addResultMap("dataEmissao", Timestamp.class);
			mapper.addResultMap("dataVencimento", Timestamp.class);
			mapper.addResultMap("dataCarta", Timestamp.class);
			mapper.addResultMap("arquivoCarta", String.class);
			mapper.addResultMap("dataConnectCarta", Timestamp.class);

			mapper.addResultMap("dataBloqueio", Timestamp.class);
			mapper.addResultMap("arquivoBloqueio", String.class);
			mapper.addResultMap("dataConnectBloqueio", Timestamp.class);
			mapper.addResultMap("dataPagamento", Timestamp.class);
			mapper.addResultMap("arquivoPagamento", String.class);
			mapper.addResultMap("dataConnectPagamento", Timestamp.class);
			
			list = (List<SccAcoesCobrancaView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;

	}


	
}
