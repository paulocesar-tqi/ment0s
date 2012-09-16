package com.claro.cobillingweb.persistence.dao.query;

public class GeraRelatorioEventosSQL {

	public static final String SQL = "SELECT AC.CD_EOT_CLARO, "+
						"AC.CD_EOT_LD,  "+
						"TO_CHAR(RS.DT_EVENTO, 'dd/mm/yyyy') AS DATA_EVENTO, "+  
						"TO_CHAR(RS.DT_PROC_EXTERNA_REM, 'dd/mm/yyyy') AS DATA_REFERENCIA, "+  
						"SUM(NVL(RS.QT_CDRS, 0)) AS SUM_QT_CHAMADAS, "+
						"SUM(NVL(RS.QT_DURACAO_TARIFADA, 0)) AS SUM_QT_DURACAO_TARIFADA, "+
						"SUM(NVL(RS.VL_LIQUIDO_CHAMADA, 0)) AS SUM_VL_LIQUIDO_CHAMADA, "+
						"SUM(NVL(RS.VL_BRUTO_CHAMADA, 0)) AS SUM_VL_BRUTO_CHAMADA, "+
						"SC.DS_STATUS_CDR AS EVENTO, "+
						"RS.CD_SUB_STATUS_CDR || ' - ' || RS.CD_MOTIVO_REJEICAO || ' - ' || RS.CD_ERRO_RECICLAGEM  AS DS_INFORMACAO_ADICIONAL "+
						"FROM SCC_RETORNO_SUMARIZADO RS,  "+
						"SCC_STATUS_CDR         SC,  "+
						"SCC_ARQUIVO_COBILLING  AC  "+
						"WHERE AC.SQ_ARQUIVO = RS.SQ_ARQUIVO_REMESSA "+ 
						"AND SC.CD_STATUS_CDR = RS.CD_STATUS_CDR ";

	public static final String PROJECTIONS = "GROUP BY AC.CD_EOT_LD, "+ 
											"AC.CD_EOT_CLARO,  "+
											"RS.DT_PROC_EXTERNA_REM, "+ 
											"RS.DT_EVENTO,  "+
											"SC.DS_STATUS_CDR, "+ 
											"RS.CD_MOTIVO_REJEICAO, "+ 
											"RS.CD_ERRO_RECICLAGEM,  "+
											"RS.CD_SUB_STATUS_CDR ";
	
	public static final String FILTRO_PERIODO = " AND RS.DT_EVENTO  BETWEEN :dataInicial AND :dataFinal";
	public static final String FILTRO_OPERADORA_CLARO = " AND AC.CD_EOT_CLARO = :cdEOTClaro ";
	public static final String FILTRO_OPERADORA_EXTERNA = " AND AC.CD_EOT_LD  = :cdEOTLD ";
	public static final String FILTRO_STATUS = " AND RS.CD_STATUS_CDR = :cdCdrStatus ";
	
	
	
}
