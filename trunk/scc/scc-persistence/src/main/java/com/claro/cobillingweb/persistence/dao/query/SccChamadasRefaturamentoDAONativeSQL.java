/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92031709
 *
 */
public class SccChamadasRefaturamentoDAONativeSQL {
	
	public static final String SQL_INICIO = "SELECT " +
												 " TO_CHAR(SUM.DT_PROC_EXTERNA, 'MM/YYYY') AS mesReferencia, "+
												 " ARQ.CD_EOT_LD AS operadoraLD, "+
												 " ARQ.CD_EOT_CLARO AS operadoraClaro, "+
												 " CAST(SUM.CD_REFATURAMENTO AS VARCHAR2(2)) AS tipoRefaturamento, ";
					
	public static final String SQL_FAT = 		 " DECODE(SUM.CD_STATUS_CDR, 15, 'A faturar', "+
												 " DECODE(SUM.CD_STATUS_CDR, 16, 'A faturar', 'Faturada')) AS statusChamada, ";
										 
	public static final String SQL_REJ = 		 " DECODE(SUM.CD_STATUS_CDR, 10, 'Rejeitada', "+
										 		 " DECODE(SUM.CD_STATUS_CDR, 11, 'Rejeitada', "+
										 		 " DECODE(SUM.CD_STATUS_CDR, 12, 'Rejeitada', 'Aceite'))) as statusChamada, ";
	
	public static final String SQL_FIM = 		 " SUM(SUM.QT_CDRS) AS quantidade, "+
										 		 " SUM(SUM.QT_DURACAO_REAL) AS minutagem, "+
										 		 " SUM(SUM.VL_BRUTO_CHAMADA) AS totalBruto, "+
										 		 " SUM(SUM.VL_LIQUIDO_CHAMADA) AS totalLiquido "+
											" FROM "+
										 		 " SCC_ARQUIVO_COBILLING ARQ, SCC_ARQUIVO_SUMARIZADO SUM "+
											" WHERE ARQ.SQ_ARQUIVO_ORIGEM = 0 "+
											  " AND ARQ.CD_TIPO_ARQUIVO = 5 "+
											  " AND ARQ.SQ_ARQUIVO = SUM.SQ_ARQUIVO "+
											  " AND TRUNC(ARQ.DT_PROC_EXTERNA) >= :dtInicial "+
											  " AND TRUNC(ARQ.DT_PROC_EXTERNA) <= :dtFinal ";

	public static final String FILTRO_CD_EOT_LD = " AND ARQ.CD_EOT_LD = :operadoraLD ";
	
	public static final String FILTRO_CD_EOT_CLARO = " AND ARQ.CD_EOT_CLARO = :operadoraClaro ";
	
	public static final String FILTRO_CD_REFATURAMENTO = " AND SUM.CD_REFATURAMENTO = :cdRefaturamento ";
	
	public static final String FILTRO_CD_STATUS_CDR = " AND   SUM.CD_STATUS_CDR in (15, 16, 20, 21) ";
	
	public static final String SQL_GROUP = " GROUP BY TO_CHAR(SUM.DT_PROC_EXTERNA, 'MM/YYYY'), "+
										   " ARQ.CD_EOT_LD, "+
										   " ARQ.CD_EOT_CLARO, "+
										   " SUM.CD_REFATURAMENTO, ";
	
	public static final String SQL_GROUP_FAT = " DECODE(SUM.CD_STATUS_CDR, 15, 'A faturar', "+
											   " DECODE(SUM.CD_STATUS_CDR, 16, 'A faturar', 'Faturada')) ";
	
	public static final String SQL_GROUP_REJ = " DECODE(SUM.CD_STATUS_CDR, 10, 'Rejeitada', "+
											   " DECODE(SUM.CD_STATUS_CDR, 11, 'Rejeitada', "+
											   " DECODE(SUM.CD_STATUS_CDR, 12, 'Rejeitada', 'Aceite'))) ";

						
}
