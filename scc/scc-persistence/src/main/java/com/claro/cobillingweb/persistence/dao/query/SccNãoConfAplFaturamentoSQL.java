/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 93046251
 *
 */
public class SccNãoConfAplFaturamentoSQL {
	
	public static final String SQL = "SELECT "+
												"FAT.CD_EOT_CLARO AS operadoraClaro, "+
												"CAST(FAT.CD_CSP AS VARCHAR2(2)) AS operadoraLD, "+
											    "CAST(FAT.SG_UF AS VARCHAR2(2)) AS UF, "+
											    "FAT.CD_CICLO,  " +
											    "AC.MM_CICLO,  "+ 
											    "AC.AA_CICLO , "+
											    "FAT.CD_SERIE_NF AS serieNotaFiscal, "+
											    "FAT.CD_SUB_SERIE_NF AS subSerieNotaFiscal, "+
											    "FAT.NU_NF AS numNotaFiscal "+
									 "FROM  "+
									 			"SCC_FATURA_LD FAT, "+
									 			"SCC_ARQUIVO_COBILLING AC "+
									 "WHERE "+
									 			"FAT.SQ_ARQUIVO = AC.SQ_ARQUIVO "+
									 			"AND FAT.IN_ERRO_FATURA = '0' ";

	
	public static final String FILTRO_CSP = "AND FAT.CD_CSP = :cdCSP";
	
	public static final String FILTRO_CD_CICLO = "AND FAT.CD_CICLO = :cdCiclo";
	
	public static final String FILTRO_MM_CICLO = "AND AC.MM_CICLO = :mmCiclo";
    
	public static final String FITLRO_AA_CICLO = "AND AC.AA_CICLO = :aaCiclo ";

}
