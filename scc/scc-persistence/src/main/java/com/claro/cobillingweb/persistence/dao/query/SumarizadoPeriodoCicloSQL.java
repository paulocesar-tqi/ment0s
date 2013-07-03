package com.claro.cobillingweb.persistence.dao.query;

public class SumarizadoPeriodoCicloSQL {

	public static final String SQL = " SELECT S.AA_CICLO  , S.MM_CICLO, "+
			" S.CD_STATUS_CDR,SUM(S.QT_CDRS),SUM(S.VL_LIQUIDO_CHAMADA),SUM(S.VL_BRUTO_CHAMADA),SUM(S.QT_DURACAO_REAL), "+ 
            " SUM(S.QT_DURACAO_TARIFADA) , ST.DS_STATUS_CDR FROM SCC_ARQUIVO_SUMARIZADO S , SCC_STATUS_CDR ST WHERE S.DT_PROC_EXTERNA BETWEEN :dataInicial AND :dataFinal AND "+
			" ST.CD_STATUS_CDR = S.CD_STATUS_CDR ";


public static final String FILTRO_EOT_CLARO = " AND S.CD_EOT_CLARO = :cdEOTClaro ";

public static final String FILTRO_EOT_CLARO_HOLDING = " AND S.CD_EOT_CLARO IN (SELECT CD_EOT FROM SCC_OPERADORA WHERE CD_EOT_HOLDING =  :cdEOTClaro) ";

public static final String FILTRO_EOT_LD  = " AND S.CD_EOT_LD = :cdEOTLD ";

public static final String FILTRO_PRODUTO = " AND ((CP.CD_PRODUTO_COBILLING = :cdProdutoCobilling) AND (CP.CD_COMPONENTE_PRODUTO NOT IN (3,5,7,10,13,21,24))) ";

public static final String FILTRO_PRODUTO_1 = " AND ((CP.CD_PRODUTO_COBILLING = :cdProdutoCobilling) OR (CP.CD_COMPONENTE_PRODUTO IN (3,5,7,10,13,21,24))) ";

public static final String PROJECTIONS = "GROUP BY  S.CD_STATUS_CDR , ST.DS_STATUS_CDR , S.AA_CICLO , S.MM_CICLO "+
             					" ORDER BY   S.AA_CICLO , S.MM_CICLO ,S.CD_STATUS_CDR";

public static final String PROJECTIONS_EVOLUCAO_STATUS =   "GROUP BY TO_CHAR(A.DT_PROC_EXTERNA,'MM/YYYY'), S.CD_STATUS_CDR, ST.DS_STATUS_CDR "+ 
														    "ORDER BY TO_CHAR(A.DT_PROC_EXTERNA,'MM/YYYY'),  S.CD_STATUS_CDR ";


public static final String SQL_EVOLUCAO_STATUS = 
												"SELECT s.CD_STATUS_CDR,SUM(s.QT_CDRS),SUM(s.VL_LIQUIDO_CHAMADA),SUM(s.VL_BRUTO_CHAMADA), " +
												"TO_CHAR(A.DT_PROC_EXTERNA,'MM/YYYY') as MESANO, ST.DS_STATUS_CDR   "+
												"FROM SCC_ARQUIVO_COBILLING A, " +
												"SCC_ARQUIVO_SUMARIZADO S, " +
												"SCC_STATUS_CDR ST ";
												 
//												"WHERE A.SQ_ARQUIVO_ORIGEM = 0   "+
//												"AND A.CD_TIPO_ARQUIVO in (5,555)  "+ 
//												"AND  ST.CD_STATUS_CDR = S.CD_STATUS_CDR  "+
//												"AND S.DT_PROC_EXTERNA BETWEEN :dataInicial AND :dataFinal "+
//												"AND A.SQ_ARQUIVO = S.SQ_ARQUIVO_ORIGINAL  AND S.QT_CDRS <> 0  ";


}
