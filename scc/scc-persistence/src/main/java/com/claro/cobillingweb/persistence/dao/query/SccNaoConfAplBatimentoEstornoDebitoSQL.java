/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 93046251
 *
 */
public class SccNaoConfAplBatimentoEstornoDebitoSQL {
	
	public static final String SQL = "SELECT "+
										"    B.CD_EOT_LD as operadoraLd, "+
										"    B.CD_EOT_CLARO as operadoraClaro, "+
										"    O.SG_UF as UF, "+
										"    B.CD_CICLO as ciclo, "+
										"    B.NU_NF as notaFiscal, "+
										"    B.VL_ESTORNO as estornoDebitos, "+
										"    B.VL_CHAMADAS_AJUSTADAS as totalChamadas, "+
										"    B.VL_DIFERENCA as diferencaAjustado, "+
										"    B.VL_ICMS_ESTORNO as estornoCreditoICMS, "+
										"    B.VL_ICMS_CHAMADAS_AJUSTADAS as totalCreditoICMS, "+
										"    B.VL_DIFERENCA_ICMS as diferencaICMS  "+
									 "FROM  "+
										"    SCC_BATIMENTO_AJUSTE_ESTORNO B, "+
										"    SCC_OPERADORA O "+
									 "WHERE B.CD_EOT_CLARO = O.CD_EOT "+
										"AND O.CD_TIPO_SERVICO = 'M'"+
										"AND B.AA_REFERENCIA = :aaReferencia";
	
	
	public static final String FILTRO_CD_EOT_LT = "and B.CD_EOT_LD = :cdEotLd ";
	
	public static final String FILTRO_CD_EOT_CLARO = "AND B.CD_EOT_CLARO = :cdEotClaro";
	
	public static final String FILTRO_MM_REFERENCIA = "AND B.MM_REFERENCIA = :mmReferencia";
	
}
