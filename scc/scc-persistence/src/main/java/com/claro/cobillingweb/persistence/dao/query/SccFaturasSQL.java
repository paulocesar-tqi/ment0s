/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 93046251
 *
 */
public class SccFaturasSQL {

	
	public static final String SQL =" SELECT "+
									" 	FATURA.CD_EOT_CLARO AS EOT_CLARO, "+
									" 	FATURA.CD_CSP AS CSP, "+
									" 	OP.CD_EOT AS OPERADORA_LD, "+
									" 	FATURA.SG_UF AS UF, "+
									" 	FATURA.CD_CICLO, "+
									"   FATURA.MM_CICLO, "+
									"	FATURA.AA_CICLO, "+
									" 	FATURA.NU_FATURA AS NUMERO_FATURA, "+
									" 	FATURA.DT_EMISSAO_NF AS DATA_EMISSAO, "+
									" 	FATURA.DT_VENCIMENTO_FATURA AS DATA_VENCIMENTO, "+
									" 	NVL(FATURA.VL_ORIGINAL_FATURA, 0) AS VALOR_ORIGINAL, "+
									" 	NVL(FATURA.VL_FATURA,0) AS VALOR, "+
									" 	NVL(FATURA.VL_ICMS,0) AS VL_ICMS, "+
									" 	FATURA.CD_STATUS_FATURA AS STATUS, "+
									" 	DECODE(FATURA.FG_EXPURGO, 'Y' , 'Expurgo', "+
									" 	DECODE(FATURA.FG_DESMEMBRAMENTO, 'Y' , 'Desmembramento', "+
									" 	DECODE(FATURA.FG_FRAUDE, 'Y', 'Fraude', "+
								
									" 	DECODE(FATURA.FG_ACORDO_PAGAMENTO, 'Y', 'Acordo Pagamento'))),' ') AS SITUACAO_EVENTO, "+
									" 	TRUNC(SYSDATE) - FATURA.DT_VENCIMENTO_FATURA AS AGING,"+
									" 	NVL(FATURA.VL_TOTAL_AJUSTES,0) AS AJUSTE "+
									" FROM "+
									" 	SCC_FATURA_LD FATURA, SCC_OPERADORA OP "+
									" WHERE "+
									" 	FATURA.CD_CSP = OP.CD_CSP "+
									" 	AND OP.CD_TIPO_SERVICO = 'C' ";
	
	public static final String FILTRO_CSP = "AND FATURA.CD_CSP = :cdCsp ";
	
	public static final String FILTRO_EOTCLARO = "AND FATURA.CD_EOT_CLARO = :cdEOTClaro ";
	
	public static final String FILTRO_DT_CARGA ="AND FATURA.DT_CARGA >= :dtCarga";
	
	public static final String FILTRO_DT_EMISSAO = "AND FATURA.DT_EMISSAO_NF >= :dtEmissao  ";
	
	public static final String FILTRO_DT_VENCIMENTO_FATURA ="AND FATURA.DT_VENCIMENTO_FATURA >=  :dtVencimento ";
	
	public static final String FILTRO_DT_FIM_CARGA ="AND FATURA.DT_CARGA <= :dtFimCarga ";
	
	public static final String FILTRO_DT_FIM_EMISSAO = "AND FATURA.DT_EMISSAO_NF <= :dtFimEmissao ";
	
	public static final String FILTRO_DT_FIM_VENCIMENTO_FATURA = "AND FATURA.DT_VENCIMENTO_FATURA <= :dtFimVencimento ";
	
	public static final String FILTRO_STATUS_FATURA = "AND FATURA.CD_STATUS_FATURA = :status ";
	
	public static final String FILTRO_STATUS_FATURA_ALL = "AND FATURA.CD_STATUS_FATURA IN ('C', 'O')";
	
	public static final String FILTRO_NUMERO_FATURA = "AND FATURA.NU_FATURA = :numeroFatura ";
	
	/* Relatorio de Juros e Multas*/
	
	public static final String SQL_JUROS_MULTAS = "SELECT "+
												  "			FATURA.CD_CSP AS OPERADORA_LD, "+
												  "			FATURA.SG_UF AS SG_UF, "+
												  "			FATURA.CD_EOT_CLARO AS OPERADORA_CLARO, "+
												  "			FATURA.NU_FATURA AS FATURA_DESTINO, "+
												  "			FATURA.DT_EMISSAO_NF AS DATA_EMISSAO, "+
												  "			FATURA.DT_VENCIMENTO_FATURA AS DATA_VENCIMENTO, "+
												  "			NVL(FATURA.VL_TOTAL_JUROS,0) AS VALOR_JUROS, "+
												  "			NVL(FATURA.VL_TOTAL_MULTAS,0) AS VALOR_MULTA, "+
												  "			(NVL(FATURA.VL_TOTAL_JUROS,0)+NVL(FATURA.VL_TOTAL_MULTAS,0)) AS VALOR_TOTAL "+
												  "FROM SCC_FATURA_LD FATURA "+
												  "WHERE ((NVL(FATURA.VL_TOTAL_JUROS,0)+NVL(FATURA.VL_TOTAL_MULTAS,0))>0) ";
	
	
	public static final String SQL_AGING =  " SELECT "+
											" 			CAST(FATURA.CD_CSP AS VARCHAR2(2)) AS OPERADORA_LD,    "+
											" 			FATURA.CD_EOT_CLARO AS OPERADORA_CLARO,  "+
											" 			NVL(SUM(CASE WHEN FATURA.DT_VENCIMENTO_FATURA >= SYSDATE  "+
											" 			THEN FATURA.VL_ORIGINAL_FATURA ELSE 0 END),0) AS AVENCER, "+
											" 			NVL(SUM(CASE WHEN FATURA.DT_VENCIMENTO_FATURA >= SYSDATE - 10 "+
											" 			AND FATURA.DT_VENCIMENTO_FATURA < SYSDATE  "+
											" 			THEN FATURA.VL_ORIGINAL_FATURA ELSE 0 END),0) AS Vencidas1A10DIAS, "+
											" 			NVL(SUM(CASE WHEN FATURA.DT_VENCIMENTO_FATURA >= SYSDATE - 20 "+
											" 			AND FATURA.DT_VENCIMENTO_FATURA < SYSDATE - 10  "+
											" 			THEN FATURA.VL_ORIGINAL_FATURA ELSE 0 END),0) AS Vencidas11A20DIAS, "+
											" 			NVL(SUM(CASE WHEN FATURA.DT_VENCIMENTO_FATURA >= SYSDATE - 30  "+
											" 			AND FATURA.DT_VENCIMENTO_FATURA < SYSDATE - 20  "+
											" 			THEN FATURA.VL_ORIGINAL_FATURA ELSE 0 END),0) AS Vencidas21A30DIAS, "+
											"       	NVL(SUM(CASE WHEN FATURA.DT_VENCIMENTO_FATURA >= SYSDATE - 60  "+
											" 			AND FATURA.DT_VENCIMENTO_FATURA < SYSDATE - 30  "+
											" 			THEN FATURA.VL_ORIGINAL_FATURA ELSE 0 END),0) AS Vencidas31A60DIAS, "+
											" 			NVL(SUM(CASE WHEN FATURA.DT_VENCIMENTO_FATURA >= SYSDATE - 90  "+
											" 			AND FATURA.DT_VENCIMENTO_FATURA < SYSDATE - 60  "+
											" 			THEN FATURA.VL_ORIGINAL_FATURA ELSE 0 END),0) AS Vencidas61A90DIAS, "+
											"			NVL(SUM(CASE WHEN FATURA.DT_VENCIMENTO_FATURA < SYSDATE-90  "+
											" 			THEN FATURA.VL_ORIGINAL_FATURA ELSE 0 END),0) AS VENCIDAS_90DIAS  "+
											" FROM SCC_FATURA_LD FATURA "+
											" WHERE FATURA.CD_STATUS_FATURA = 'O'";
	
	
	public static final String PROJECTIONS = " GROUP BY  FATURA.CD_CSP,  FATURA.CD_EOT_CLARO ";

										
	
	
			
	
}
