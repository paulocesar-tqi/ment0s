/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92031709
 *
 */
public class SccConfirmacaoRepasseDAONativeSQL {
	
	public static final String SQL = "SELECT " +
											 " TO_CHAR(PF.DT_FECHAMENTO, 'YYYY/MM') AS anoMesRepasse, "+
											 " (SELECT DS_OPERADORA || ' (' || CD_EOT || ')' "+
											 " FROM   SCC_OPERADORA "+
											 " WHERE  CD_EOT = PF.CD_EOT_LD) AS operadoraLD, "+
											 " (SELECT DS_OPERADORA  || ' (' || CD_EOT || ')' "+
											 " FROM   SCC_OPERADORA "+
											 " WHERE  CD_EOT = PF.CD_EOT_CLARO) AS operadoraClaro, "+
											 " NVL(PF.VL_FINAL_REPASSAR,0) AS valorRepasse, "+
											 " DECODE(PF.CD_STATUS_FECHAMENTO, 'C', 'Confirmado', 'N', 'Cancelado', 'Não Confirmado') AS statusRepasse"+
									 " FROM "+
											 " SCC_PRE_FECHAMENTO PF "+
									 " WHERE  TO_CHAR(PF.DT_FECHAMENTO,'MM/YYYY') = :dtFechamento";
	
	public static final String FILTRO_CDEOTLD = " AND PF.CD_EOT_LD = :cdEOTLD ";
	
	public static final String FILTRO_CD_STATUS_REPASSE = " AND PF.CD_STATUS_FECHAMENTO = :cdStatusRepasse ";
											
}
