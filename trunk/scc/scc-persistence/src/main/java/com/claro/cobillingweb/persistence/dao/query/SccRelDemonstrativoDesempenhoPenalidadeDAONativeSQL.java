/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92038883
 *
 */
public class SccRelDemonstrativoDesempenhoPenalidadeDAONativeSQL {
	
	public static final String SQL = "SELECT " +
			   "IND.cd_indicador AS CODIGO_INDICADOR, " + 
		       "IND.ds_indicador AS DESCRICAO_INDICADOR,  " +
		       "IND.in_respons_informacao AS RESPONSABILIDADE_INDICADOR, " + 
		       "Decode(Nvl(RES.vl_indicador, 0), 0, 1, RES.vl_indicador) AS VALOR_REAL, " + 
		       "Nvl(IND.pc_meta_indicador, 0) AS META_INDICADOR, " +
		       "Nvl(IND.pc_peso_indicador, 0) AS PESO_INDICADOR " + 
		"FROM   (SELECT cd_indicador, cd_eot_ld, " + 
		               "dt_referencia, vl_indicador " +
		        "FROM   scc_resultado_indicador " +
		        "WHERE  1 = 1 " +
		               "AND (cd_eot_ld = :cdCSP " + 
		                      "OR cd_eot_ld = '999' ) " + 
		               "AND dt_referencia =:dtReferencia " + 
		               "AND cd_indicador LIKE 'SLA%') RES, " + 
		       "scc_indicador IND  " +
		"WHERE  IND.cd_indicador LIKE 'SLA%' " + 
		       "AND IND.cd_indicador = RES.cd_indicador (+) " + 
		       "AND (SELECT Count(*)  " +
		            "FROM   scc_resultado_indicador " + 
		            "WHERE  ( cd_eot_ld = :cdCSP  " +
		                      "OR cd_eot_ld = '999' ) " + 
		                   "AND dt_referencia =:dtReferencia " + 
		                   "AND cd_indicador LIKE 'SLA%') > 0  " +
		"ORDER  BY IND.cd_indicador"; 
	
					
}
