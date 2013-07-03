package com.claro.cobillingweb.persistence.dao.query;

public class ControleRemessaPorCicloSQL {
	
	public static final String SQL = "SELECT S.CD_EOT_LD , S.CD_EOT_CLARO , S.AA_CICLO , S.MM_CICLO , S.CD_CICLO ,SUM(S.VL_LIQUIDO_CHAMADA), "+
			"SUM(S.VL_BRUTO_CHAMADA),SUM(S.VL_FATURADO), SUM(S.VL_FATURADO_LIQUIDO) , SUM(S.QT_CDRS) "+
			"FROM SCC_ARQUIVO_SUMARIZADO S , SCC_ARQUIVO_COBILLING ARQ , SCC_COMPOSICAO_PRODUTO C , "+
			"SCC_PRODUTO_COBILLING P "+
			"WHERE ARQ.SQ_ARQUIVO = S.SQ_ARQUIVO "+
			"AND S.CD_COMPONENTE_PRODUTO = C.CD_COMPONENTE_PRODUTO "+
			"AND C.CD_PRODUTO_COBILLING = P.CD_PRODUTO_COBILLING ";
	
	public static final String PROJECTIONS =  "GROUP BY S.AA_CICLO , S.MM_CICLO , S.CD_CICLO , S.CD_EOT_LD , S.CD_EOT_CLARO";
	
	public static final String FILTRO_AA_CICLO = "AND S.AA_CICLO = :aaCiclo";	
	
	public static final String FILTRO_MM_CICLO = "AND S.MM_CICLO = :mmCiclo";
	
	public static final String FILTRO_CD_CICLO = "AND S.CD_CICLO = :cdCiclo";
	
	public static final String FILTRO_EOT_LD = "AND ARQ.CD_EOT_LD = :cdEOTLD";
	
	public static final String FILTRO_EOT_CLARO = "AND S.CD_EOT_CLARO = :cdEOTClaro";	
	
	public static final String FILTRO_TIPO_ARQUIVO = "AND ARQ.CD_TIPO_ARQUIVO = :cdTipoArquivo";	
	
	public static final String FILTRO_EOT_CLARO_HOLDING = "AND S.CD_EOT_CLARO IN (SELECT CD_EOT FROM SCC_OPERADORA WHERE CD_EOT_HOLDING = :cdEOTClaro)";
	
	public static final String FILTRO_PRODUTO = "AND P.CD_PRODUTO_COBILLING = :cdProdutoCobilling";
	
	 
	public static final String SQL_PERDA_FATURAMENTO =  "SELECT (select ds_operadora from scc_operadora where cd_eot = ARQ.CD_EOT_LD), "+
														"(select ds_operadora from scc_operadora where cd_eot = ARQ.CD_EOT_CLARO), "+
														"		TRUNC(ARQ.DT_PROC_EXTERNA), "+
														"		SU.CD_STATUS_CDR, "+
														"		SU.CD_SUB_STATUS_CDR,"+
														"		SUM(SU.VL_LIQUIDO_CHAMADA),"+
														"		SUM(SU.VL_BRUTO_CHAMADA), "+
														"		SUM(SU.QT_CDRS)"+
														"  FROM SCC_ARQUIVO_COBILLING ARQ, SCC_ARQUIVO_SUMARIZADO SU "+
														" WHERE ARQ.SQ_ARQUIVO = SU.SQ_ARQUIVO "+
														"   AND ARQ.SQ_ARQUIVO_ORIGEM = 0 "+
														"   AND ARQ.CD_TIPO_ARQUIVO = 5 "+
														"   AND TRUNC(ARQ.DT_PROC_EXTERNA) >= :dataInicial"+
														"   AND TRUNC(ARQ.DT_PROC_EXTERNA) <=  :dataFinal";
	
	public static final String FILTRO_EOT_CLARO_REL_PF =  "AND ARQ.CD_EOT_CLARO = :cdEOTClaro ";
	public static final String FILTRO_EOT_LT_REL_PF = "AND ARQ.CD_EOT_LD = :cdEOTLD";
	
	public static final String PROJECTIONS_REL_PF ="  GROUP BY ARQ.CD_EOT_LD," +
											"		ARQ.CD_EOT_CLARO, " +
											"		TRUNC(ARQ.DT_PROC_EXTERNA), " +
											"		SU.CD_STATUS_CDR, " +
											"		SU.CD_SUB_STATUS_CDR "+
											"		ORDER BY TRUNC(ARQ.DT_PROC_EXTERNA), " +
											"				ARQ.CD_EOT_LD, ARQ.CD_EOT_CLARO, SUM(SU.VL_LIQUIDO_CHAMADA)";


	
}
