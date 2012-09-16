package com.claro.cobillingweb.persistence.dao.query;

public class RelContabilViewSQL {

	public static final String SQL = "SELECT FE.CD_EOT_LD, "+
                      " FE.CD_EOT_CLARO,   "+
                      " TO_CHAR(RE.DT_FECHAMENTO,'MMYYYY') AS FECHAMENTO, "+ 
                      " CC.DS_CODIGO_CONTABIL,   "+
                      " FE.CD_CONTABIL,   "+
                      " SUM(FE.QT_CDRS) AS TTL_CDRS, "+  
                      " SUM(FE.VL_LIQUIDO_CHAMADA) AS TTL_LIQUIDO, "+  
                      " SUM(FE.VL_BRUTO_CHAMADA) AS TTL_BRUTO  "+
                      " FROM  SCC_FECHAMENTO_SUMARIZADO FE,  "+
                      " SCC_REPASSE RE,  "+
                      " SCC_EVENTO_CONTABIL EC, "+ 
                      " SCC_CODIGO_CONTABIL CC  "+
                      " WHERE FE.SQ_PEDIDO = RE.SQ_PEDIDO "+ 
                      " AND   EC.CD_CONTABIL = FE.CD_CONTABIL "+ 
                      " AND   CC.CD_CONTABIL = FE.CD_CONTABIL  "+
                      " AND   RE.CD_STATUS_REPASSE = 'C' "+
					  " AND RE.DT_FECHAMENTO BETWEEN :dataInicial AND :dataFinal ";
	
	public static final String FILTRO_EOT_CLARO = " AND FE.CD_EOT_CLARO = :cdEOTClaro";
	public static final String FILTRO_EOT_CLARO_HOLDING = " AND FE.CD_EOT_CLARO IN (SELECT CD_EOT FROM SCC_OPERADORA WHERE CD_EOT_HOLDING = :cdEOTClaro)";
	public static final String FILTRO_EOT_LD = " AND FE.CD_EOT_LD= :cdEOTLD";
	public static final String FILTRO_MOTIVO_REJEICAO = " AND EC.CD_MOTIVO_REJEICAO= :cdMotivoRejeicao";
	
	
	public static final String PROJECTIONS = "GROUP BY FE.CD_EOT_LD, "+ 
        		      " FE.CD_EOT_CLARO,  "+
					" RE.DT_FECHAMENTO,  "+
					" CC.DS_CODIGO_CONTABIL, "+ 
					" FE.CD_CONTABIL,  "+
					" FE.QT_CDRS ";
	
}
