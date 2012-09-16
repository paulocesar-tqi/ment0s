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
	
}
