/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92031709
 *
 */
public class SccContingenciaFiscalDAONativeSQL {
	
	public static final String SQL = "SELECT " +
											 " CAST('MOBILE' AS VARCHAR(6)) AS nomeEmpresa, "+
											 " CAST(RFC.CD_CSP AS VARCHAR(2)) AS cdCSP, "+
											 " CAST(ARQ.SG_UF AS VARCHAR(2)) AS sgUF, "+
											 " SUM(RFC.VL_TOTAL_NF) AS valorFaturado, "+
											 " SUM(RFC.VL_BASE_CALCULO_ICMS) AS valorBaseCalculo, "+
											 " SUM(RFC.VL_ICMS) AS valorICMS, "+
											 " SUM(RFC.VL_OPERACOES_ISENTAS) AS valorInsencao, "+
											 " RFC.NU_INICIAL_NF AS nfInicial, "+
											 " RFC.NU_FINAL_NF AS nfFinal, "+
											 " RFC.NO_RAZAO_SOCIAL_LD AS razaoSocial, "+
											 " RFC.NU_CNPJ_LD AS numeroCNPJ, "+
											 " RFC.NU_INSC_ESTADUAL_LD AS inscricaoEstadual, "+
											 " RFC.NU_SERIE_NF AS serieNF "+											 
									 " FROM "+
											 " SCC_REL_FISCAL_CONTINGENCIA RFC, SCC_ARQUIVO_COBILLING ARQ "+
									 " WHERE  RFC.SQ_ARQUIVO = ARQ.SQ_ARQUIVO "+
									   " AND  ARQ.CD_TIPO_ARQUIVO = 800 "+
									   " AND  RFC.AM_PERIODO_REFERENCIA = :dtReferencia";									   						 
									 	
	public static final String FILTRO_CD_CSP = " AND RFC.CD_CSP = :cdCSP ";
	
	public static final String SQL_GROUP = " GROUP BY RFC.NU_CNPJ_LD, "+
													" RFC.CD_CSP, "+
													" ARQ.SG_UF, "+
													" RFC.NU_INICIAL_NF, "+
													" RFC.NU_FINAL_NF, "+
													" RFC.NO_RAZAO_SOCIAL_LD, "+
													" RFC.NU_CNPJ_LD, "+
													" RFC.NU_INSC_ESTADUAL_LD, "+
													" RFC.NU_SERIE_NF ";						
}
