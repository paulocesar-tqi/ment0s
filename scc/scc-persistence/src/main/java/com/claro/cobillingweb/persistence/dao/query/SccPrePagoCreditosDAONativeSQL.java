/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92031709
 *
 */
public class SccPrePagoCreditosDAONativeSQL {
	
	public static final String SQL = "SELECT " +
												 " (select ds_operadora from scc_operadora where cd_eot = VW.CD_EOT_LD) AS operadoraLD, " +
												 " (select ds_operadora from scc_operadora where cd_eot = VW.CD_EOT_CLARO) AS operadoraClaro, " +
												 " VW.SQ_ARQUIVO AS sqArquivo, " +
												 " VW.NO_ARQUIVO AS noArquivo, " +
												 " CAST(VW.TP_CREDITO AS VARCHAR2(1)) AS tpCredito, " +
												 " CAST(VW.CD_STATUS AS VARCHAR2(1)) AS cdStatus, " +
												 " VW.DS_CREDITO AS dsCredito, " +
												 " VW.DS_STATUS AS dsStatus, " +
												 " VW.VL_CREDITO AS vlCredito, " +
												 " VW.DT_CREDITO AS dtCredito, " +
												 " VW.SQ_CREDITO AS sqCredito, " +
												 " VW.NU_MSISDN_ORIGEM AS numeroA, " +
												 " VW.QUANTIDADE AS quantidade, " +
												 " VW.MINUTOS_QUEIMADOS AS minutosQueimados, " +
												 " VW.MINUTOS_AJUSTADOS AS minutosAjustados, " +
												 " ROWNUM AS linha " +
									 " FROM ( "+
										 		 " SELECT NVL(C.CD_EOT_LD, '000')       AS CD_EOT_LD, "+
									 		     " NVL(C.CD_EOT_CLARO, '000')    AS CD_EOT_CLARO, " +
									 		     " C.SQ_ARQUIVO_CREDITO          AS SQ_ARQUIVO, " +
									 		     " ARQ.NO_ARQUIVO, " +
									 		     " NVL(C.TP_CREDITO, ' ')        AS TP_CREDITO, " +
									 		     " NVL(C.CD_STATUS_CREDITO, ' ') AS CD_STATUS, " +
									 		     " CDORG.DS_DOMINIO              AS DS_CREDITO, " +
									 		     " STCRD.DS_DOMINIO              AS DS_STATUS, " +
									 		     " NVL(C.VL_CREDITO, 0)          AS VL_CREDITO, " +
									 		     " C.DT_CREDITO, " +
									 		     " C.SQ_CREDITO                  AS SQ_CREDITO, " +
									 		     " C.NU_MSISDN_ORIGEM, " +
									 		     " COUNT(1)                      QUANTIDADE, " +
									 		     " SUM(CC.HR_DURACAO_REAL) * 60  AS MINUTOS_QUEIMADOS, " +
									 		     " SUM(CC.MI_DURACAO_TARIFADA)   AS MINUTOS_AJUSTADOS " +									 		     
									 " FROM "+
											  " SCC_CREDITO C, " +
											  " SCC_ARQUIVO_CREDITO ARQ, " +
											  " (SELECT CD_DOMINIO, TP_DOMINIO, DS_DOMINIO FROM SCC_PRE_DOMINIO WHERE  TP_DOMINIO = 'CDORG') CDORG, " +
											  " (SELECT CD_DOMINIO, TP_DOMINIO, DS_DOMINIO FROM SCC_PRE_DOMINIO WHERE  TP_DOMINIO = 'STCRD') STCRD, " +
											  " SCC_OPERADORA LD, " +
											  " SCC_OPERADORA CLARO, " +
											  " SCC_CDR_COBL CC, " +
											  " SCC_CDR_CREDITO CR " +
									  " WHERE 	CC.DT_PROC_EXTERNA >= ADD_MONTHS(:dtInicial, -1) " +
									  " 	AND CC.SQ_CREDITO = CR.SQ_CREDITO " +
									  "		AND CC.NU_CDR = CR.NU_CDR " +
									  "		AND CR.SQ_CREDITO = C.SQ_CREDITO " +
									  "     AND C.DT_CREDITO >= :dtInicial " +
									  "		AND C.DT_CREDITO < :dtFinal + 1 " +
									  "		AND C.SQ_ARQUIVO_CREDITO = ARQ.SQ_ARQUIVO_CREDITO " +
									  "		AND C.CD_EOT_LD = LD.CD_EOT " +
									  "		AND LD.CD_TIPO_SERVICO = 'C' " +
									  "		AND C.CD_EOT_CLARO = CLARO.CD_EOT " +
									  "		AND CLARO.CD_TIPO_SERVICO = 'M' " +
									  "		AND C.TP_CREDITO = CDORG.CD_DOMINIO " +
									  "		AND C.CD_STATUS_CREDITO = STCRD.CD_DOMINIO ";

	public static final String FILTRO_CD_EOT_LD = " AND C.CD_EOT_LD = :operadoraLD ";
	
	public static final String FILTRO_CD_EOT_CLARO = " AND C.CD_EOT_CLARO = :operadoraClaro ";
	
	public static final String FILTRO_TP_CREDITO = " AND C.TP_CREDITO = :tipoCredito ";
	
	public static final String FILTRO_CD_STATUS_CREDITO = " AND C.CD_STATUS_CREDITO = :cdStatusCredito ";
	
	public static final String SQL_GROUP = " GROUP BY NVL(C.CD_EOT_LD, '000'), " +
										   " NVL(C.CD_EOT_CLARO, '000'), " +
										   " C.SQ_ARQUIVO_CREDITO, " +
										   " ARQ.NO_ARQUIVO, " +
										   " NVL(C.TP_CREDITO, ' '), " +
										   " NVL(C.CD_STATUS_CREDITO, ' '), " +
										   " CDORG.DS_DOMINIO, " +
										   " STCRD.DS_DOMINIO, " +
										   " NVL(C.VL_CREDITO, 0), " +
										   " C.DT_CREDITO, " +
										   " C.SQ_CREDITO, " +
										   " C.NU_MSISDN_ORIGEM) VW ";						
}
