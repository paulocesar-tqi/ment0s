package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.FwjBaseDaoHibernateImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccResultadoIndicadoresDAO;
import com.claro.cobillingweb.persistence.dao.query.SccResultadoIndicadorSQL;
import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.cobillingweb.persistence.entity.SccResultadoIndicador;
import com.claro.cobillingweb.persistence.utils.DateUtils;
import com.claro.cobillingweb.persistence.view.SccAgingIndicadorView;
import com.claro.cobillingweb.persistence.view.SccResultadoIndicadorView;
import com.claro.cobillingweb.persistence.view.mapper.NativeSQLViewMapper;

@Repository
public class SccResultadoIndicadoresDAOImpl extends FwjBaseDaoHibernateImpl<SccResultadoIndicador, String> implements SccResultadoIndicadoresDAO {
	
	private String gerarHql(){
		StringBuilder sb = new StringBuilder();
		sb.append("select ai from SccAgingIndicador ai ");
		//sb.append("join fetch ai.sccIndicador ind ");
		//sb.append("left join ai.id.cdIndicador res ");
		sb.append("where ai.id.cdIndicador = :cdIndicador ");
		sb.append("order by ai.vlMinimoAging asc ");
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SccAgingIndicador> gerarListaAging(String cdIndicador) throws DAOException{
		
		List<SccAgingIndicador> list = null;
		try {
			
			Query query = getSessionFactory().getCurrentSession().createQuery(gerarHql());
			query.setParameter("cdIndicador", cdIndicador);
			list = (List<SccAgingIndicador>) query.list();
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
		
	}
	
	@Override
	public List<SccAgingIndicadorView> gerarPreList2(String cdIndicador) throws DAOException{
		
		List<SccAgingIndicadorView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccAgingIndicadorView> mapper = new NativeSQLViewMapper<SccAgingIndicadorView>(session, SccResultadoIndicadorSQL.SQL_RESULTADO_PRE_LIST, SccAgingIndicadorView.class);
			mapper.addArgument("cdIndicador", cdIndicador);
			mapper.addResultMap("cdIndicador", String.class);
			mapper.addResultMap("sqAgingIndicador", Long.class);
			mapper.addResultMap("vlMinimoAging", BigDecimal.class);
			mapper.addResultMap("vlMaximoAging", BigDecimal.class);
			mapper.addResultMap("apagar", BigDecimal.class);
			mapper.addResultMap("tipoContrato", String.class);
			
			list = (List<SccAgingIndicadorView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return list;
		
	}
	
	@Override
	public List<SccResultadoIndicadorView> pesquisaByFiltro(String cdEotLd, String cdEotClaro, String cdIndicador, boolean tipo, Date dataInicial, Date dataFinal) throws DAOException{
		
		List<SccResultadoIndicadorView> list = null;
		try {
			
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccResultadoIndicadorView> mapper = new NativeSQLViewMapper<SccResultadoIndicadorView>(session, SccResultadoIndicadorSQL.SQL_RESULTADO, SccResultadoIndicadorView.class);
			mapper.addArgument("cdIndicador", cdIndicador);
			mapper.addArgument("dataInicial", DateUtils.lowDateTime(dataInicial));
			mapper.addArgument("dataFinal", DateUtils.highDateTime2(dataFinal));

			if(!cdEotClaro.equals("*")){
				mapper.addArgument("cdEotClaro", cdEotClaro, SccResultadoIndicadorSQL.CD_EOTCLARO_RESULT);
			}
			if(!cdEotLd.equals("*")){
				mapper.addArgument("cdEotLd", cdEotLd, SccResultadoIndicadorSQL.CD_EOTLD_RESULT);
			}
			
			if(tipo){
				mapper.appendSQL(SccResultadoIndicadorSQL.PARAMETRO_PRE);
			}else{
				mapper.appendSQL(SccResultadoIndicadorSQL.PARAMETRO_POS);
			}
			mapper.appendSQL(SccResultadoIndicadorSQL.GROUPBY_RESULT);
			
			mapper.addResultMap("cdIndicador", String.class);
			mapper.addResultMap("dtReferenciaStr", String.class);
			mapper.addResultMap("sqAgingIndicador", Long.class);
			mapper.addResultMap("cdEotLd", String.class);
			mapper.addResultMap("cdRegional", String.class);
			mapper.addResultMap("dtPeriodo", Date.class);
			mapper.addResultMap("dtCarga", Date.class);
			mapper.addResultMap("aging", Long.class);
			mapper.addResultMap("vlIndicador", BigDecimal.class);
			mapper.addResultMap("vlOrigemIndicador1", BigDecimal.class);
			mapper.addResultMap("vlOrigemIndicador2", BigDecimal.class);
			list = (List<SccResultadoIndicadorView>) mapper.execute();
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
	}
	
	@Override
	public SccResultadoIndicadorView gerarResultadoIndicador(String cdEotLd, String cdEotClaro, String cdIndicador, Long vlMinimo, Long vlMaximo, Date dataInicial, Date dataFinal) throws DAOException{
		
		List<SccResultadoIndicadorView> list = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccResultadoIndicadorView> mapper = new NativeSQLViewMapper<SccResultadoIndicadorView>(session, SccResultadoIndicadorSQL.SQL, SccResultadoIndicadorView.class);
			mapper.addArgument("dataInicial", DateUtils.lowDateTime(dataInicial));
			mapper.addArgument("dataFinal", DateUtils.highDateTime2(dataFinal));
			if(StringUtils.isNotEmpty(cdEotLd) && !cdEotLd.equals("*")){
				mapper.addArgument("cdEotLd", cdEotLd, SccResultadoIndicadorSQL.CD_EOTLD);
				//mapper.appendSQL("AND     SUBSTR(DEST_FILE,10,3) = :cdEotLd");
			}
			
			if(StringUtils.isNotEmpty(cdEotClaro) && !cdEotClaro.equals("*")){
				mapper.addArgument("cdEotClaro", cdEotClaro, SccResultadoIndicadorSQL.CD_EOTCLARO);
				//mapper.appendSQL("AND     SUBSTR(DEST_FILE,7,3) = :cdEotClaro");
				
			}
			
			mapper.addArgument("vlMinimo", vlMinimo, SccResultadoIndicadorSQL.VL_MINIMO);
			mapper.addArgument("vlMaximo", vlMaximo, SccResultadoIndicadorSQL.VL_MAXIMO);
			mapper.addArgument("cdIndicador", cdIndicador, SccResultadoIndicadorSQL.CD_INDICADOR);
			
			mapper.addResultMap("vlIndicador", BigDecimal.class);
			mapper.addResultMap("vlOrigemIndicador1", BigDecimal.class);
			mapper.addResultMap("vlOrigemIndicador2", BigDecimal.class);
			mapper.addResultMap("dtPeriodo", Date.class);
			mapper.addResultMap("dtCarga", Date.class);
			mapper.addResultMap("qtdAging", Long.class);
			
			list = (List<SccResultadoIndicadorView>) mapper.execute();	
			
			
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list.get(0);

	}
	
	@Override
	public List<SccResultadoIndicadorView> gerarList(String cdEotLd, String cdEotClaro, Date dataInicial, Date dataFinal, Long vlMinimo, Long vlMaximo, String cdIndicador) throws DAOException{
		
		List<SccResultadoIndicadorView> list = null;
		try{
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccResultadoIndicadorView> mapper = new NativeSQLViewMapper<SccResultadoIndicadorView>(session, SccResultadoIndicadorSQL.SQL, SccResultadoIndicadorView.class);
			mapper.addArgument("dataInicial", DateUtils.lowDateTime(dataInicial));
			mapper.addArgument("dataFinal", DateUtils.highDateTime2(dataFinal));
			if(StringUtils.isNotEmpty(cdEotLd) && !cdEotLd.equals("*")){
				mapper.addArgument("cdEotLd", cdEotLd, SccResultadoIndicadorSQL.CD_EOTLD);
				//mapper.appendSQL("AND     SUBSTR(DEST_FILE,10,3) = :cdEotLd");
			}
			
			if(StringUtils.isNotEmpty(cdEotClaro) && !cdEotClaro.equals("*")){
				mapper.addArgument("cdEotClaro", cdEotClaro, SccResultadoIndicadorSQL.CD_EOTCLARO);
				//mapper.appendSQL("AND     SUBSTR(DEST_FILE,7,3) = :cdEotClaro");
				
			}
			mapper.addArgument("vlMinimo", vlMinimo, SccResultadoIndicadorSQL.VL_MINIMO);
			mapper.addArgument("vlMaximo", vlMaximo, SccResultadoIndicadorSQL.VL_MAXIMO);
            //mapper.appendSQL("AND TRUNC(NVL(COBL_DATE,SYSDATE)- STOP_DATE) >= :vlMinimo ");
            //mapper.appendSQL("AND TRUNC(NVL(COBL_DATE,SYSDATE)- STOP_DATE) <= :vlMaximo) RI, (SELECT COUNT(1) REGISTROS FROM SCC_AGING_INDICADOR ");
            mapper.appendSQL("WHERE CD_INDICADOR='KPI-010-2') AI ");
            
			mapper.addResultMap("vlIndicador", BigDecimal.class);
			mapper.addResultMap("vlOrigemIndicador1", BigDecimal.class);
			mapper.addResultMap("vlOrigemIndicador2", BigDecimal.class);
			mapper.addResultMap("dtPeriodo", Date.class);
			mapper.addResultMap("dataCarga", Date.class);
			mapper.addResultMap("qtdAging", Long.class);			
			list = (List<SccResultadoIndicadorView>) mapper.execute();	
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}

		return list;
	}
	
	@Override
	public List<SccResultadoIndicadorView> gerarListaFinal(String cdEotLd, String cdEotClaro, Date dataInicial, Date dataFinal) throws DAOException{
		List<SccResultadoIndicadorView> list = null;
		try{
			Session session = getSessionFactory().getCurrentSession();
			NativeSQLViewMapper<SccResultadoIndicadorView> mapper = new NativeSQLViewMapper<SccResultadoIndicadorView>(session, SccResultadoIndicadorSQL.SQL_LIST, SccResultadoIndicadorView.class);
			mapper.addArgument("dataInicial", DateUtils.lowDateTime(dataInicial));
			mapper.addArgument("dataFinal", DateUtils.highDateTime2(dataFinal));

			if(StringUtils.isNotEmpty(cdEotLd) && !cdEotLd.equals("*")){
				
				mapper.appendSQL("AND     SUBSTR(DEST_FILE,7,3) = "+cdEotLd);
			}
			
			if(StringUtils.isNotEmpty(cdEotClaro) && !cdEotClaro.equals("*")){
				
				mapper.appendSQL("AND     SUBSTR(DEST_FILE,7,3) = " +cdEotClaro);
				
			}
			mapper.appendSQL("ORDER BY CD_EOT_LD, CD_EOT_CLARO");
			
			mapper.addResultMap("cdEotClaro", String.class);
			mapper.addResultMap("cdEotLd", String.class);
			mapper.addResultMap("destFile", String.class);
			mapper.addResultMap("exitCode", Long.class);
			mapper.addResultMap("dtAtual", Date.class);
			mapper.addResultMap("stopDate", Date.class);
			mapper.addResultMap("coblDate", Date.class);
			
			list = (List<SccResultadoIndicadorView>) mapper.execute();

		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return list;
		
	}
	

}
