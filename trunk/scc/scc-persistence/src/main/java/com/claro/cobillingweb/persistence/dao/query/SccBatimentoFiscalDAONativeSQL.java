/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92031709
 *
 */
public class SccBatimentoFiscalDAONativeSQL {
	
	public static final String SQL = "SELECT " +
											 " CAST(OP.CD_CSP AS VARCHAR(2)) AS cdCSP, "+
											 " OP.DS_OPERADORA AS nomeOperadora, "+
											 " CAST('MOBILE' AS VARCHAR(6)) AS nomeEmpresa, "+
											 " ARQ.CD_EOT_CLARO AS cdEOTClaro, "+
											 " CAST(ARQ.SG_UF AS VARCHAR(2)) AS sgUF, "+
											 " ARQ.NO_ARQUIVO AS noArquivo, "+
											 " CAST(ARQ.CD_STATUS_ARQUIVO AS VARCHAR(2)) AS cdStatusArquivo, "+
											 " ARQ.CD_CICLO AS cdCiclo, "+
											 " ARQ.VL_TOTAL_NF AS vlTotalNF, "+
											 " ARQ.DT_CONNECT AS dtConnect "+											 
									 " FROM "+
											 " SCC_ARQUIVO_COBILLING ARQ, SCC_OPERADORA OP "+
									 " WHERE  ARQ.SQ_ARQUIVO_ORIGEM = 0 "+
									   " AND  ARQ.CD_TIPO_ARQUIVO = 80 "+
									   " AND  ARQ.MM_CICLO = :mesCiclo "+
									   " AND  ARQ.AA_CICLO = :anoCiclo "+
									   " AND  ARQ.CD_EOT_LD = OP.CD_EOT ";
									 	
	public static final String FILTRO_CD_CSP = " AND OP.CD_CSP = :cdCSP ";
	
	public static final String FILTRO_CD_CICLO = " AND ARQ.CD_CICLO = :cdCiclo ";
	
	public static final String SQL_ORDER = " ORDER BY OP.DS_OPERADORA, ARQ.SG_UF ";
	
}
