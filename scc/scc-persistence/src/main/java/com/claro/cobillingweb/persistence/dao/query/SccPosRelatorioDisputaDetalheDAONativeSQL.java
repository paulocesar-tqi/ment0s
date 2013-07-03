/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92038883
 *
 */
public class SccPosRelatorioDisputaDetalheDAONativeSQL {
	
	public static final String SQL = "SELECT " +
			"CC.SQ_DISPUTA, " +
	        "CC.CD_EOT_LD, " +
	        "CC.CD_STATUS_DISPUTA, " +
	        "CC.IN_RISCO_DISPUTA, " +
	        "CC.DT_CRIACAO, " +
	        "CC.DT_ATUALIZACAO, " +
	        "CC.CD_USUARIO_MANUT " +
	        "FROM SCC_DISPUTA CC, SCC_DISPUTA_DETALHE DD " +
	        "WHERE CC.SQ_DISPUTA = DD.SQ_DISPUTA(+) " +
	        "AND TO_CHAR(NVL(CC.DT_EVENTO,SYSDATE),'YYYYMM') = :dtReferencia";
									   						 
									 	
	public static final String FILTRO_CD_CSP = " AND CC.CD_EOT_LD = :cdEotLD ";
	
					
}
