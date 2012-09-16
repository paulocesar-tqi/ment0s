package com.claro.cobillingweb.persistence.dao.query;

public class SumarizadoPeriodoSQL {

	public static final String SQL = " SELECT "+
						" S.CD_STATUS_CDR,SUM(S.QT_CDRS),SUM(S.VL_LIQUIDO_CHAMADA),SUM(S.VL_BRUTO_CHAMADA),SUM(S.QT_DURACAO_REAL), "+ 
                        " SUM(S.QT_DURACAO_TARIFADA) , ST.DS_STATUS_CDR FROM SCC_ARQUIVO_SUMARIZADO S , SCC_STATUS_CDR ST WHERE S.DT_PROC_EXTERNA BETWEEN :dataInicial AND :dataFinal AND "+
						" ST.CD_STATUS_CDR = S.CD_STATUS_CDR ";
	
	
	public static final String FILTRO_EOT_CLARO = " AND S.CD_EOT_CLARO = :cdEOTClaro ";
	
	public static final String FILTRO_EOT_CLARO_HOLDING = " AND S.CD_EOT_CLARO IN (SELECT CD_EOT FROM SCC_OPERADORA WHERE CD_EOT_HOLDING =  :cdEOTClaro) ";
	
	public static final String FILTRO_EOT_LD  = " AND S.CD_EOT_LD = :cdEOTLD ";
	
	public static final String PROJECTIONS = "GROUP BY  S.CD_STATUS_CDR , ST.DS_STATUS_CDR "+
                         					" ORDER BY  S.CD_STATUS_CDR";
}
