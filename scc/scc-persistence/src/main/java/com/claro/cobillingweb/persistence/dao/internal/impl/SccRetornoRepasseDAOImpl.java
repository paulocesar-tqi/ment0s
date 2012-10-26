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
	
		String sql = " SELECT B.CD_CSP as csp,   " +
				     "  C.DS_OPERADORA as operadoraLD,   " +
				     "  B.SG_UF as uf,   " +
				     "  REP.CD_STATUS_REPASSE as status,   " +
				     "  A.MM_CICLO || '/' || A.AA_CICLO as mes,   " +
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
			"   AND   REP.CD_ITEM_REPASSE IN (30,32,34,100)  ";
		
		
		String filtroDataInicialRepasse = "	AND   A.DT_INICIAL_REPASSE >= TRUNC(:dtInicialRepasse) ";
		String filtroDataFinalRepasse = " AND   A.DT_INICIAL_REPASSE <= TRUNC(:dtFinalRepasse) ";
		String filtroCdProdutoCobilling = "	AND   A.CD_PRODUTO_COBILLING =  :cdProdutoCobilling  ";
		String filtroCdEOTLD = " AND   A.CD_EOT_LD = :cdEOTLD  ";
		String filtroCdEOTClaro = "	AND   A.CD_EOT_CLARO = :cdEOTClaro  ";
		
		String projections = "	GROUP BY B.CD_CSP,   " +
					"	       C.DS_OPERADORA,   " +
					"	       B.SG_UF,   " +
					"	       REP.CD_STATUS_REPASSE,    " +
					"	       A.MM_CICLO || '/' || A.AA_CICLO,   " +
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


	
}
