/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 93046251
 *
 */
public class SccResultadoIndicadorSQL {
	
	public static final String SQL_RESULTADO_PRE_LIST=
									"			SELECT AI.CD_INDICADOR, "+
									"		       AI.SQ_AGING_INDICADOR, "+
									"		       AI.VL_MINIMO_AGING, "+
									"		       AI.VL_MAXIMO_AGING, "+
									"		       NVL(RESULTADO.APAGAR,0), "+
									"		       DECODE(SUBSTR(AI.CD_INDICADOR,0,3),'PRE','P','F') AS TIPO_CONTRATO "+
									"		FROM    SCC_AGING_INDICADOR AI, ( SELECT COUNT(1) AS APAGAR, RI.CD_INDICADOR "+ 
									"		                                  FROM SCC_RESULTADO_INDICADOR RI GROUP BY RI.CD_INDICADOR ) RESULTADO "+
									"		WHERE   AI.CD_INDICADOR = RESULTADO.CD_INDICADOR(+) "+
									"		AND     AI.CD_INDICADOR = :cdIndicador "+
									"		ORDER BY AI.CD_INDICADOR, AI.SQ_AGING_INDICADOR ";
			
	
	public static final String SQL_RESULTADO =
												"   SELECT RI.CD_INDICADOR,  "+
												"          RI.DT_REFERENCIA,  "+
												"   	   RI.SQ_AGING_INDICADOR,  "+
												"          RI.CD_EOT_LD,  "+
												"   	   RI.CD_REGIONAL,  "+
												"          RI.DT_PERIODO AS PERIODO,  "+
												"   	   RI.DT_CARGA AS DT_CARGA,  "+
												"          1 AS AGING,  "+
												"   	   SUM(RI.VL_INDICADOR),  "+
												"          SUM(RI.VL_ORIGEM_INDICADOR_1),  "+
												"   	   SUM(RI.VL_ORIGEM_INDICADOR_2)  "+
												"   FROM    SCC_RESULTADO_INDICADOR RI, SCC_INDICADOR I, (SELECT COUNT(SQ_AGING_INDICADOR) AS SQ_AGING_INDICADOR, CD_INDICADOR  "+
												"                                                         FROM SCC_AGING_INDICADOR GROUP BY CD_INDICADOR) AI "+
												"   WHERE   RI.CD_INDICADOR = I.CD_INDICADOR "+
												"   AND     I.CD_INDICADOR = AI.CD_INDICADOR(+) "+
												"   AND     I.CD_STATUS_INDICADOR = 'ATIVO' "+
												"   AND     RI.CD_INDICADOR = :cdIndicador "+
	 											"   AND     RI.DT_PERIODO >= :dataInicial "+
	 											"   AND     RI.DT_PERIODO <= :dataFinal ";
	public static final String CD_EOTCLARO_RESULT  = " AND RI.CD_INDICADOR = :cdEotClaro ";
	
	public static final String CD_EOTLD_RESULT = " AND     RI.CD_REGIONAL = :cdEotLd ";
	
	public static final String PARAMETRO_PRE = " AND     SUBSTR(RI.CD_INDICADOR,0,3) IN ('PRE') ";
	
	public static final String PARAMETRO_POS =" AND     SUBSTR(RI.CD_INDICADOR,0,3) NOT IN ('PRE') ";
	//public static final String DATAINI = " AND     RI.DT_PERIODO >= :dataInicial ";
	//public static final String DATAFIM = 
	
	public static final String GROUPBY_RESULT =	" GROUP BY        RI.CD_INDICADOR, "+
												" RI.DT_REFERENCIA, RI.SQ_AGING_INDICADOR, RI.CD_EOT_LD, RI.CD_REGIONAL, "+
												" RI.DT_PERIODO,  RI.DT_CARGA, AI.SQ_AGING_INDICADOR "+
												" ORDER BY RI.DT_REFERENCIA ASC, PERIODO, RI.SQ_AGING_INDICADOR ";
			
	
	public static final String SQL = 
									"SELECT RI.REGISTROS VL_INDICADOR, " +
								    "RI.REGISTROS VL_ORIGEM_INDICADOR_1, "+
								    "RI.REGISTROS VL_ORIGEM_INDICADOR_2, "+
								    "SYSDATE DT_PERIODO, "+
								    "SYSDATE DT_CARGA, "+
								    "AI.REGISTROS AS AGING "+
								"FROM (SELECT COUNT(1) REGISTROS "+
								              "FROM CTRL_CONNECT_FILES "+
								              "WHERE (DEST_FILE LIKE 'TCOE%.NC' OR DEST_FILE LIKE 'TCOE%.NC.gz') "+
								             "AND STOP_DATE >= :dataInicial "+
								             "AND STOP_DATE <= :dataFinal "+
								             "AND EXIT_CODE = 0 "+
								             "AND EXIT_DESC = 'Copy step successful.' ";
	
	public static final String CD_EOTLD = "AND     SUBSTR(DEST_FILE,7,3) = :cdEotLd ";
	public static final String CD_EOTCLARO = "AND     SUBSTR(DEST_FILE,10,3) = :cdEotClaro ";
	
	public static final String VL_MINIMO ="AND TRUNC(NVL(COBL_DATE,SYSDATE)- STOP_DATE) >= :vlMinimo ";
	
	public static final String VL_MAXIMO = "AND TRUNC(NVL(COBL_DATE,SYSDATE)- STOP_DATE) <= :vlMaximo) RI, (SELECT COUNT(1) REGISTROS FROM SCC_AGING_INDICADOR ";
	public static final String CD_INDICADOR = "WHERE CD_INDICADOR = :cdIndicador ) AI";
	
	public static final String SQL_LIST =
											"SELECT SUBSTR(DEST_FILE,10,3) AS CD_EOT_CLARO, "+
											"       SUBSTR(DEST_FILE,7,3) AS CD_EOT_LD, "+
											"       DEST_FILE, 0, SYSDATE, SYSDATE, SYSDATE, SYSDATE, SYSDATE, SYSDATE, "+ 
											"       STOP_DATE, COBL_DATE, SYSDATE "+
											"FROM CTRL_CONNECT_FILES "+
											"WHERE (DEST_FILE LIKE 'TCOE%.NC' OR DEST_FILE LIKE 'TCOE%.NC.gz') "+
											"                AND STOP_DATE >= :dataInicial "+
											"                AND STOP_DATE <= :dataFinal "+
											"                AND EXIT_CODE = 0 "+
											"                AND EXIT_DESC = 'Copy step successful.' ";
			


}
