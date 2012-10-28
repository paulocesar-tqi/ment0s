package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccRelatorioAlarmeOperacionalDAO;
import com.claro.cobillingweb.persistence.view.RelAlarmeOperacionalView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccRelatorioAlarmeOperacionalDAOImpl extends HibernateBasicDAOImpl<List<Object[]>> implements SccRelatorioAlarmeOperacionalDAO{

	 
	public List<List<Object[]>> getAll() throws DAOException {		
		return null;
	}

	 
	public List<Object[]> getByPk(Serializable pk, Class entityClazz) throws DAOException {
		throw new DAOException("Operação não suportada.");
	}

	 
	public void delete(List<Object[]> entity) throws DAOException {
		throw new DAOException("Operação não suportada.");
	}

	 
	public Serializable create(List<Object[]> entity) throws DAOException {
		throw new DAOException("Operação não suportada.");
	}

	 
	public void update(List<Object[]> entity) throws DAOException {
		throw new DAOException("Operação não suportada.");
	}

	 
	public List<RelAlarmeOperacionalView> listarAlarmeOperacional(Date dtInicio, Date dtFim, String nomeArquivo) throws DAOException {
		List<RelAlarmeOperacionalView> list = null;

		String sql = "	SELECT RI.NU_MSISDN_ORIGEM    AS numA,   " +
					"       RI.QT_REGISTROS        AS qtdeChamadas,   " + 
					"       RI.QT_DURACAO_TARIFADA AS qtdeMinutosTarifados,    " +
					"       RI.VL_LIQUIDO          AS valorTotalChamadas,    " +
					"       RI.NU_FATURA           AS nroFatura,    " +
					"       RI.NU_NF               AS nroNf,   " +
					"       OP.DS_OPERADORA        AS nomeOperadoraLD,   " + 
					"       RI.DT_CHAMADA          AS dataReferencia    " +
					" FROM   SCC_RELATORIO_SUMARIZADO RS,    " +
					"       SCC_RELATORIO_ITEM RI,   " +
					"       SCC_OPERADORA OP   " +
					" WHERE  RI.DT_RELATORIO = RS.DT_RELATORIO    " +
					"       AND RI.SQ_RELATORIO_SUMARIZADO = RS.SQ_RELATORIO_SUMARIZADO   " +
					"       AND RI.CD_EOT_LD = OP.CD_EOT    ";	
		
		String filtroDataInicial = "	AND RS.DT_RELATORIO >= TRUNC(:dtInicial) ";
		String filtroDataFinal = " AND RS.DT_RELATORIO < TRUNC(:dtFinal) + 1 ";
		String filtroRelatorio = "	AND   RS.SQ_RELATORIO =  :nomeArquivo  ";
		
		String projections = " ORDER  BY RI.DT_RELATORIO, " + 
		          			 " RI.DT_PROC_CLARO ";
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<RelAlarmeOperacionalView> mapper = new NativeSQLViewMapper<RelAlarmeOperacionalView>(session, sql, RelAlarmeOperacionalView.class);
			if(dtInicio != null && dtFim != null) {
				mapper.addArgument("dtInicial", dtInicio, filtroDataInicial);
				mapper.addArgument("dtFinal", dtFim, filtroDataFinal);
			}
			
			if(nomeArquivo != null){
				mapper.addArgument("nomeArquivo", nomeArquivo, filtroRelatorio);
			}
			
			mapper.addResultMap("numA", String.class);
			mapper.addResultMap("qtdeChamadas", Long.class);
			mapper.addResultMap("qtdeMinutosTarifados", Long.class);
			mapper.addResultMap("valorTotalChamadas", Double.class);
			mapper.addResultMap("nroFatura", String.class);
			mapper.addResultMap("nroNf", Long.class);
			mapper.addResultMap("nomeOperadoraLD", String.class);
			mapper.addResultMap("dataReferencia", Date.class);

			mapper.setProjections(projections);
			
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
		
	}


	
}
