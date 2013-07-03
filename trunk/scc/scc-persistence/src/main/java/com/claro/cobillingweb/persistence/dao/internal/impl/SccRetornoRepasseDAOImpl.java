package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccRetornoRepasseDAO;
import com.claro.cobillingweb.persistence.view.SccRetornoRepasseView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

public class SccRetornoRepasseDAOImpl extends HibernateBasicDAOImpl<List<Object[]>> implements SccRetornoRepasseDAO{

	 
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

	 
	public List<SccRetornoRepasseView> pesquisaRetornoRepasse(String cdEOTClaro, String cdEOTLD, Long cdProdutoCobilling, Date dtInicialRepasse, Date  dtFinalRepasse) throws DAOException {
		List<SccRetornoRepasseView> list = null;

		String sql = " SELECT CAST(B.CD_CSP AS VARCHAR2(2)) as csp,   " +
				     "  B.DS_OPERADORA as operadoraLD,   " +
				     "  CAST(C.SG_UF AS VARCHAR2(2)) as uf,   " +
				     "  DECODE(REP.CD_STATUS_REPASSE, 'C', 'Confirmado', 'N', 'Cancelado', 'E', 'Nulo',REP.CD_STATUS_REPASSE) as status, " +
				     "  A.MM_CICLO || '/' || A.AA_CICLO as mes, " +
				     "  SUM(REP.VL_BRUTO_REPASSE) as valor,   " +
				     "  REP.NU_REPASSE as numRepasse,   " +
				     "  A.NO_ARQUIVO as arquivo   " +
			"	FROM   SCC_RELATORIO_RETORNO_REPASSE A,   " +
			"	       SCC_OPERADORA B,   " +
			"	       SCC_OPERADORA C,   " +
			"	       SCC_REPASSE REP   " +
			"	WHERE  A.CD_EOT_LD = B.CD_EOT   " +
			"	AND   A.CD_EOT_CLARO = C.CD_EOT   " +
			"	AND   A.DT_INICIAL_REPASSE = REP.DT_INICIAL_REPASSE   " +
			"	AND   A.CD_EOT_LD = REP.CD_EOT_LD   " +
			"	AND   A.CD_EOT_CLARO = REP.CD_EOT_CLARO   " +
			"	AND   A.CD_PRODUTO_COBILLING = REP.CD_PRODUTO_COBILLING   " +
			"	AND   A.NU_QUINZENA = REP.NU_QUINZENA   " +
			"	AND	  A.CD_COMPONENTE_PRODUTO = REP.CD_COMPONENTE_PRODUTO	"+
			"   AND   REP.CD_ITEM_REPASSE IN (30,32,34,100)  ";
		
		
		String filtroDataInicialRepasse = "	AND   A.DT_INICIAL_REPASSE >= TRUNC(:dtInicialRepasse) ";
		String filtroDataFinalRepasse = " AND   A.DT_INICIAL_REPASSE < TRUNC(:dtFinalRepasse) + 1 ";
		String filtroCdProdutoCobilling = "	AND   A.CD_PRODUTO_COBILLING =  :cdProdutoCobilling  ";
		String filtroCdEOTLD = " AND   A.CD_EOT_LD = :cdEOTLD  ";
		String filtroCdEOTClaro = "	AND   A.CD_EOT_CLARO = :cdEOTClaro  ";
		
		String projections = "	GROUP BY B.CD_CSP,   " +
					"	       B.DS_OPERADORA,   " +
					"	       C.SG_UF,   " +
					"	       REP.CD_STATUS_REPASSE,    " +
					"	       A.MM_CICLO || '/' || A.AA_CICLO, " +
					"	       REP.NU_REPASSE,   " +
					"	       A.NO_ARQUIVO   ";
	
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccRetornoRepasseView> mapper = new NativeSQLViewMapper<SccRetornoRepasseView>(session, sql, SccRetornoRepasseView.class);
			if(dtInicialRepasse != null && dtFinalRepasse != null) {
				mapper.addArgument("dtInicialRepasse", dtInicialRepasse, filtroDataInicialRepasse);
				mapper.addArgument("dtFinalRepasse", dtFinalRepasse, filtroDataFinalRepasse);
			}
			
			if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", cdEOTClaro, filtroCdEOTClaro);
			}
			
			if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTLD", cdEOTLD, filtroCdEOTLD);
			}

			if(cdProdutoCobilling != null){
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling, filtroCdProdutoCobilling);
			}

			mapper.addResultMap("csp", String.class);
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("uf", String.class);
			mapper.addResultMap("status", String.class);
			mapper.addResultMap("mes", String.class);
			mapper.addResultMap("valor", Double.class);
			mapper.addResultMap("numRepasse", Long.class);
			mapper.addResultMap("arquivo", String.class);

			mapper.setProjections(projections);
			
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
		
	}


	public List<SccRetornoRepasseView> pesquisaRetornoRepasseAss(String cdEOTClaro, String cdEOTLD, Long cdProdutoCobilling, Date dtInicialRepasse, Date  dtFinalRepasse) throws DAOException {
		List<SccRetornoRepasseView> list = null;

		String sql = " SELECT CAST(B.CD_CSP AS VARCHAR2(2)) as csp,   " +
				     "  B.DS_OPERADORA as operadoraLD,   " +
				     "  CAST(C.SG_UF AS VARCHAR2(2)) as uf,   " +
				     "  DECODE(REP.CD_STATUS_REPASSE, 'C', 'Confirmado', 'O', 'Aberto', 'E', 'Nulo', REP.CD_STATUS_REPASSE) as status, " +
				     "  A.MM_CICLO || '/' || A.AA_CICLO as mes, " +
				     "  SUM(REP.VL_BRUTO_REPASSE) as valor,   " +
				     "  REP.NU_REPASSE as numRepasse,   " +
				     "  A.NO_ARQUIVO as arquivo   " +
			"	FROM   SCC_RELATORIO_RETORNO_REPASSE A,   " +
			"	       SCC_OPERADORA B,   " +
			"	       SCC_OPERADORA C,   " +
			"	       SCC_REPASSE_SERVICO_ADICIONAL REP   " +
			"	WHERE  A.CD_EOT_LD = B.CD_EOT   " +
			"	AND   A.CD_EOT_CLARO = C.CD_EOT   " +
			"	AND   A.DT_INICIAL_REPASSE = REP.DT_INICIAL_REPASSE   " +
			"	AND   A.CD_EOT_LD = REP.EOT_LD   " +
			"	AND   A.CD_EOT_CLARO = REP.EOT_CLARO   " +
			"	AND   A.CD_PRODUTO_COBILLING = REP.CD_PRODUTO_COBILLING   " +
			"	AND   A.NU_QUINZENA = REP.NU_QUINZENA   " +
			"   AND A.CD_ITEM_REPASSE = 100  ";		
		
		String filtroDataInicialRepasse = "	AND   A.DT_INICIAL_REPASSE >= TRUNC(:dtInicialRepasse) ";
		String filtroDataFinalRepasse = " AND   A.DT_INICIAL_REPASSE < TRUNC(:dtFinalRepasse) + 1 ";
		String filtroCdProdutoCobilling = "	AND   A.CD_PRODUTO_COBILLING =  :cdProdutoCobilling  ";
		String filtroCdEOTLD = " AND   A.CD_EOT_LD = :cdEOTLD  ";
		String filtroCdEOTClaro = "	AND   A.CD_EOT_CLARO = :cdEOTClaro  ";
		
		String projections = "	GROUP BY B.CD_CSP,   " +
					"	       B.DS_OPERADORA,   " +
					"	       C.SG_UF,   " +
					"	       REP.CD_STATUS_REPASSE,    " +
					"	       A.MM_CICLO || '/' || A.AA_CICLO, " +
					"	       REP.NU_REPASSE,   " +
					"	       A.NO_ARQUIVO   ";
	
		String sqlUnion = " UNION" +
		
				 "	SELECT CAST(B.CD_CSP AS VARCHAR2(2)) as csp,   " +
			     "  B.DS_OPERADORA as operadoraLD,   " +
			     "  CAST(C.SG_UF AS VARCHAR2(2)) as uf,   " +
			     "  DECODE(REP.CD_STATUS_REPASSE, 'C', 'Confirmado', 'N', 'Cancelado', 'E', 'Nulo',REP.CD_STATUS_REPASSE) as status, " +
			     "  A.MM_CICLO || '/' || A.AA_CICLO as mes, " +
			     "  SUM(REP.VL_BRUTO_REPASSE) as valor,   " +
			     "  REP.NU_REPASSE as numRepasse,   " +
			     "  A.NO_ARQUIVO as arquivo   " +
		"	FROM   SCC_RELATORIO_RETORNO_REPASSE A,   " +
		"	       SCC_OPERADORA B,   " +
		"	       SCC_OPERADORA C,   " +
		"	       SCC_REPASSE REP   " +
		"	WHERE  A.CD_EOT_LD = B.CD_EOT   " +
		"	AND   A.CD_EOT_CLARO = C.CD_EOT   " +
		"	AND   A.DT_INICIAL_REPASSE = REP.DT_INICIAL_REPASSE   " +
		"	AND   A.CD_EOT_LD = REP.CD_EOT_LD   " +
		"	AND   A.CD_EOT_CLARO = REP.CD_EOT_CLARO   " +
		"	AND   A.CD_PRODUTO_COBILLING = REP.CD_PRODUTO_COBILLING   " +
		"	AND   A.NU_QUINZENA = REP.NU_QUINZENA   " +
		"	AND	  A.CD_COMPONENTE_PRODUTO = REP.CD_COMPONENTE_PRODUTO	"+
		"   AND   REP.CD_ITEM_REPASSE IN (30,32,34,100)  ";	
	
	String filtroDataInicialRepasseUnion = "	AND   A.DT_INICIAL_REPASSE >= TRUNC(:dtInicialRepasse) ";
	String filtroDataFinalRepasseUnion = " AND   A.DT_INICIAL_REPASSE < TRUNC(:dtFinalRepasse) + 1 ";
	String filtroCdProdutoCobillingUnion = "	AND   A.CD_PRODUTO_COBILLING =  :cdProdutoCobilling  ";
	String filtroCdEOTLDUnion = " AND   A.CD_EOT_LD = :cdEOTLD  ";
	String filtroCdEOTClaroUnion = "	AND   A.CD_EOT_CLARO = :cdEOTClaro  ";
	
	String projectionsUnion = "	GROUP BY B.CD_CSP,   " +
				"	       B.DS_OPERADORA,   " +
				"	       C.SG_UF,   " +
				"	       REP.CD_STATUS_REPASSE,    " +
				"	       A.MM_CICLO || '/' || A.AA_CICLO, " +
				"	       REP.NU_REPASSE,   " +
				"	       A.NO_ARQUIVO   ";
		
		try {
			Session session = getSessionFactory().getCurrentSession();
			
			NativeSQLViewMapper<SccRetornoRepasseView> mapper = new NativeSQLViewMapper<SccRetornoRepasseView>(session, sql, SccRetornoRepasseView.class);
			
			if(dtInicialRepasse != null && dtFinalRepasse != null) {
				mapper.addArgument("dtInicialRepasse", dtInicialRepasse, filtroDataInicialRepasse);
				mapper.addArgument("dtFinalRepasse", dtFinalRepasse, filtroDataFinalRepasse);
			}
			
			if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", cdEOTClaro, filtroCdEOTClaro);
			}
			
			if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTLD", cdEOTLD, filtroCdEOTLD);
			}

			if(cdProdutoCobilling != null){
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling, filtroCdProdutoCobilling);
			}

			mapper.appendSQL(projections);
			mapper.appendSQL(sqlUnion);
			
			if(dtInicialRepasse != null && dtFinalRepasse != null) {
				mapper.addArgument("dtInicialRepasse", dtInicialRepasse, filtroDataInicialRepasseUnion);
				mapper.addArgument("dtFinalRepasse", dtFinalRepasse, filtroDataFinalRepasseUnion);
			}
			
			if(cdEOTClaro != null && !cdEOTClaro.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTClaro", cdEOTClaro, filtroCdEOTClaroUnion);
			}
			
			if(cdEOTLD != null && !cdEOTLD.equals(BasicDAO.GET_ALL_STRING)){
				mapper.addArgument("cdEOTLD", cdEOTLD, filtroCdEOTLDUnion);
			}

			if(cdProdutoCobilling != null){
				mapper.addArgument("cdProdutoCobilling", cdProdutoCobilling, filtroCdProdutoCobillingUnion);
			}
			
			mapper.appendSQL(projectionsUnion);
			
			mapper.addResultMap("csp", String.class);
			mapper.addResultMap("operadoraLD", String.class);
			mapper.addResultMap("uf", String.class);
			mapper.addResultMap("status", String.class);
			mapper.addResultMap("mes", String.class);
			mapper.addResultMap("valor", Double.class);
			mapper.addResultMap("numRepasse", Long.class);
			mapper.addResultMap("arquivo", String.class);
			
			list = mapper.execute();
		} catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
		
	}
	
}
