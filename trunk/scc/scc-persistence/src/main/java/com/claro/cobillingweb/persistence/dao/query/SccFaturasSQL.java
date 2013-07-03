/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 93046251
 *
 */
public class SccFaturasSQL {

	public static final String SQL_SINTETICO = 
			"SELECT FATURA.cd_eot_claro                      AS EOT_CLARO, "+ 
		    "   CAST(FATURA.cd_csp AS VARCHAR2(2))           AS CSP, "+
		    "   OP.cd_eot                                    AS OPERADORA_LD, "+ 
		    "   CAST(FATURA.sg_uf AS VARCHAR2(2))            AS UF, "+ 
		    "   FATURA.cd_ciclo "+ 
		    "   || '/' "+
		    "   || FATURA.mm_ciclo "+ 
		    "   || '/'  "+
		    "   || FATURA.aa_ciclo                           AS CICLO_MES_ANO, "+
		    "   FATURA.dt_emissao_nf                         AS DATA_EMISSAO, "+
		    "   FATURA.dt_vencimento_orig_fatura             AS VENCIMENTO_ORIGINAL, "+ 
		    "   FATURA.dt_vencimento_fatura                  AS DATA_VENCIMENTO, "+
		    "   Sum(NVL(FATURA.vl_original_fatura,0))        AS VALOR_ORIGINAL, "+ 
		    "   Sum(FATURA.vl_fatura)                        AS VALOR,  "+
		    "   Sum(NVL(FATURA.vl_icms,0))                   AS VL_ICMS, "+ 
		    "   DECODE(FATURA.cd_status_fatura,'O', 'Open', "+
		    "   DECODE(FATURA.cd_status_fatura,'C', 'Closed')) AS STATUS, "+ 
		    "   Decode (FATURA.fg_expurgo, 'Y', 'Expurgo',  "+
		    "                              Decode(FATURA.fg_desmembramento, 'Y', "+ 
		    "                              'Desmembramento', "+ 
		    "   Decode(FATURA.fg_fraude, 'Y', 'Fraude', "+
		    "   Decode(FATURA.fg_acordo_pagamento, 'Y', "+ 
		    "   'Acordo Pagamento',  "+
		    "     ''))))                                     AS SITUACAO_EVENTO, "+ 
		    "   Trunc(sysdate) - FATURA.dt_vencimento_fatura AS AGING, "+
		    "   Sum(NVL(FATURA.vl_total_ajustes,0))                 AS AJUSTE, "+ 
		    "   FATURA.cd_serie_nf                           AS SERIE, "+
		    "   FATURA.cd_sub_serie_nf                       AS SUB_SERIE, "+
		    "   Sum(NVL(FATURA.vl_total_creditos,0))                AS TOTAL_CREDITOS, "+
		    "   Sum(NVL(FATURA.vl_total_ajustes,0))                 AS TOTAL_AJUSTES, "+
		    "   Sum(NVL(FATURA.vl_oferta_ld,0))                     AS VL_OFERTA_LD, "+
		    "   Sum(NVL(FATURA.vl_pagamento_desconto_ld,0))         AS VL_DESCONTO_LD, "+
		    "   Sum(NVL(FATURA.vl_credito_ld,0))                    AS VL_CREDITO_LD, "+
		    "   Sum(NVL(FATURA.vl_total_pago,0))                    AS VL_PAGO, "+
		    "   Sum(NVL(FATURA.qt_total_eventos,0))                 AS QTDE_EVENTOS, "+
		    "   Sum(NVL(FATURA.vl_total_juros,0))                   AS JUROS, "+
		    "   Sum(NVL(FATURA.vl_total_multas,0))                  AS MULTAS "+
			"FROM   scc_fatura_ld FATURA, "+
		    "   scc_operadora OP "+
		    "WHERE  FATURA.cd_csp = OP.cd_csp "+
		    "   AND OP.cd_tipo_servico = 'C' ";
		    //"   AND FATURA.dt_emissao_nf >= '01/01/2012' "+
		    //"   AND FATURA.dt_emissao_nf <= '01/01/2013' "+
		    //"   AND FATURA.cd_status_fatura IN ( 'C', 'O' ) "+
		public static final String GROUP_BY_SINTETICO =
			"GROUP  BY FATURA.cd_eot_claro, "+
		    "      FATURA.cd_csp, "+ 
		    "      OP.cd_eot, "+
		    "      FATURA.sg_uf, "+
		    "      FATURA.cd_ciclo "+ 
		    "      || '/' "+
		    "      || FATURA.mm_ciclo "+ 
		    "      || '/'  "+
		    "      || FATURA.aa_ciclo,  "+
		    "      FATURA.dt_emissao_nf,  "+
		    "      FATURA.dt_vencimento_orig_fatura, "+ 
		    "      FATURA.dt_vencimento_fatura, "+ 
		    "      FATURA.cd_status_fatura, "+
		    "      Decode (FATURA.fg_expurgo, 'Y', 'Expurgo', "+
		    "                                 Decode(FATURA.fg_desmembramento, 'Y', "+ 
		    "                                 'Desmembramento', "+ 
		    "      Decode(FATURA.fg_fraude, 'Y', 'Fraude', "+
		    "      Decode(FATURA.fg_acordo_pagamento, 'Y', "+ 
		    "      'Acordo Pagamento', "+ 
		    "        '')))), "+
		    "      Trunc(sysdate) - FATURA.dt_vencimento_fatura, "+ 
		    "      FATURA.cd_serie_nf, "+
		    "      FATURA.cd_sub_serie_nf ";


			
	public static final String SQL_FATURA = 
	"SELECT FATURA.cd_eot_claro                          AS EOT_CLARO, "+ 
    "       CAST(FATURA.cd_csp AS VARCHAR2(2))           AS CSP, "+
	"       OP.cd_eot                                    AS OPERADORA_LD, "+ 
	"       CAST(FATURA.sg_uf AS VARCHAR2(2))            AS UF, "+ 
	"       FATURA.cd_ciclo "+ 
	"       || '/' "+
	"       || FATURA.mm_ciclo "+ 
	"       || '/' "+
	"       || FATURA.aa_ciclo                           AS CICLO_MES_ANO, "+
	"       FATURA.nu_fatura                             AS NUMERO_FATURA, "+
	"       FATURA.dt_emissao_nf                         AS DATA_EMISSAO, "+
	"       FATURA.dt_vencimento_orig_fatura             AS VENCIMENTO_ORIGINAL, "+ 
	"       FATURA.dt_vencimento_fatura                  AS DATA_VENCIMENTO, "+
	"       NVL(FATURA.vl_original_fatura,0)                    AS VALOR_ORIGINAL, "+ 
	"       NVL(FATURA.vl_fatura,0)                             AS VALOR, "+
	"       NVL(FATURA.vl_icms,0)                               AS VL_ICMS, "+ 
	"       DECODE(FATURA.cd_status_fatura, 'O','Open', "+
	"       DECODE(FATURA.cd_status_fatura,'C', 'Closed'))AS STATUS, "+ 
	"       Decode (FATURA.fg_expurgo, 'Y', 'Expurgo', "+
	"       Decode(FATURA.fg_desmembramento, 'Y', 'Desmembramento', Decode(FATURA.fg_fraude, 'Y', 'Fraude', "+ 
	"       Decode(FATURA.fg_acordo_pagamento, 'Y', 'Acordo Pagamento', '')))) AS SITUACAO_EVENTO, "+ 
	"       Trunc(sysdate) - FATURA.dt_vencimento_fatura AS AGING, "+
	"       NVL(FATURA.vl_total_ajustes,0)                      AS AJUSTE, "+
	"       FATURA.nu_nf                                 AS NUMERO_NOTA_FISCAL, "+ 
	"       FATURA.cd_serie_nf                           AS SERIE, "+
	"       FATURA.cd_sub_serie_nf                       AS SUB_SERIE, "+
	"       NVL(FATURA.vl_total_creditos,0)                     AS TOTAL_CREDITOS, "+
	"       NVL(FATURA.vl_total_ajustes,0)                      AS TOTAL_AJUSTES, "+
	"       NVL(FATURA.vl_oferta_ld,0)                          AS VL_OFERTA_LD, "+
	"       NVL(FATURA.vl_pagamento_desconto_ld,0)              AS VL_DESCONTO_LD, "+
	"       NVL(FATURA.vl_credito_ld,0)                         AS VL_CREDITO_LD, "+ 
	"       NVL(FATURA.vl_total_pago,0)                         AS VL_PAGO, "+
	"       NVL(FATURA.qt_total_eventos,0)                      AS QTDE_EVENTOS, "+ 
	"       NVL(FATURA.vl_total_juros,0)                        AS JUROS, "+
	"       NVL(FATURA.vl_total_multas,0)                       AS MULTAS "+ 
	"FROM   scc_fatura_ld FATURA, scc_operadora OP "+ 
	"WHERE  FATURA.cd_csp = OP.cd_csp "+
	"       AND OP.cd_tipo_servico = 'C' ";
	//"       AND FATURA.nu_fatura = '0000' "+
	//"       AND FATURA.dt_emissao_nf >= '01/01/2012' "+
	//"       AND FATURA.dt_emissao_nf <= '01/01/2013' "+
	//"       AND FATURA.cd_status_fatura IN ( 'C', 'O' )"; 
	
	
	public static final String SQL =" SELECT "+
									" 	FATURA.CD_EOT_CLARO AS EOT_CLARO, "+
									" 	CAST(FATURA.CD_CSP AS VARCHAR2(2)) AS CSP, "+
									" 	OP.CD_EOT AS OPERADORA_LD, "+
									" 	CAST(FATURA.SG_UF AS VARCHAR2(2)) AS UF, "+
								    "   FATURA.cd_ciclo "+ 
								    "   || '/' "+
								    "   || FATURA.mm_ciclo "+ 
								    "   || '/'  "+
								    "   || FATURA.aa_ciclo                           AS CICLO_MES_ANO, "+
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
												  "			CAST(FATURA.CD_CSP AS VARCHAR2(2)) AS OPERADORA_LD, "+
												  "			CAST(FATURA.SG_UF AS VARCHAR2(2)) AS SG_UF, "+
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
	
	public static final String SQL_CICLO = "SELECT DISTINCT CD_CICLO, MM_CICLO, AA_CICLO FROM SCC_FATURA_LD";

										
	
	
			
	
}
