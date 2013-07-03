/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92031709
 *
 */
public class SccArquivosFiscaisDAONativeSQL {
	
	public static final String SQL = "SELECT /*+PARALLEL(A,8)*/" +
											 " CAST(O2.CD_CSP AS VARCHAR(2)) AS cdCSP, "+
											 " CAST(O1.SG_UF AS VARCHAR(2)) AS sgUF, "+
											 " CAST(ARQ.AA_CICLO AS VARCHAR2(4)) AS anoCiclo, "+
											 " ARQ.MM_CICLO AS mesCiclo, "+
											 " ARQ.CD_CICLO AS codigoCiclo, "+
											 " ARQ.NO_ARQUIVO AS noArquivo, "+
											 " ARQ.NO_DIRETORIO_ARQUIVO AS noDiretorioArquivo, "+
											 " ARQ.DT_STATUS_ARQUIVO AS dtStatusArquivo, "+
											 " CAST(ARQ.CD_STATUS_ARQUIVO AS VARCHAR(2)) AS cdStatusArquivo, "+
											 " ARQ.CD_MOTIVO_REJEICAO_ARQUIVO AS cdMotivoRejeicaoArquivo, "+
											 " ARQ.DT_CONNECT AS dtConnect, "+
											 " SUM(ARQ.QT_REGISTROS) AS qtRegistros, "+
											 " SUM(ARQ.VL_BRUTO_ARQUIVO) AS vlBrutoArquivo, "+
											 " SUM(ARQ.VL_ICMS) AS vlICMS "+
									 " FROM "+
											 " SCC_ARQUIVO_COBILLING ARQ, SCC_OPERADORA O1, SCC_OPERADORA O2 "+
									 " WHERE  ARQ.CD_TIPO_ARQUIVO = 80 "+
									   " AND  O1.CD_EOT = ARQ.CD_EOT_CLARO "+
									   " AND  O2.CD_EOT = ARQ.CD_EOT_LD "+
									   " AND  O2.CD_TIPO_SERVICO IN ('C','F') "+
									   " AND  ARQ.AA_CICLO = :anoCiclo ";									 
	
	public static final String FILTRO_SG_UF = " AND  O1.SG_UF = :sgUF ";
	
	public static final String FILTRO_STATUS_ARQUIVO = " AND  ARQ.CD_STATUS_ARQUIVO = :cdStatusArquivo ";
	
	public static final String FILTRO_CD_CSP = " AND  O2.CD_CSP = :cdCSP ";
	
	public static final String FILTRO_CD_CICLO = " AND  ARQ.CD_CICLO = :cdCiclo ";
	
	public static final String FILTRO_MES_CICLO = " AND  ARQ.MM_CICLO = :mesCiclo ";
	
	public static final String SQL_GROUP = " GROUP BY O2.CD_CSP, "+
											   " O1.SG_UF, "+
											   " ARQ.AA_CICLO, "+
											   " ARQ.MM_CICLO, "+
											   " ARQ.CD_CICLO, "+
											   " ARQ.NO_ARQUIVO, "+
											   " ARQ.NO_DIRETORIO_ARQUIVO, "+
											   " ARQ.DT_STATUS_ARQUIVO, "+
											   " ARQ.CD_STATUS_ARQUIVO, "+
											   " ARQ.CD_MOTIVO_REJEICAO_ARQUIVO, "+
											   " ARQ.DT_CONNECT "+
										   " ORDER BY O2.CD_CSP, "+
											   " O1.SG_UF, "+
											   " ARQ.AA_CICLO, "+
											   " ARQ.MM_CICLO, "+
											   " ARQ.CD_CICLO, "+
											   " ARQ.NO_ARQUIVO ";
						
}




