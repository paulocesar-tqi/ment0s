/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author Paulo César - Sysmap
 *
 */
public class SccRelatorioConciliacaoDAONativeSQL {
	
	public static final String SQL = "SELECT LC.DT_LANCAMENTO_CONTABIL as dataLancamento, " +
										"LC.CD_CSP as codCsp, " + 
										"LC.CD_LOCAL_NEGOCIO as localNegocio, " +
										"LC.CD_EMPRESA_CONTABIL as empresaContabil, " +
										"AC.CD_CONTA_CREDITO as contaCredito, " +
										"AC.CD_CONTA_DEBITO as contaDebito, " +
										"LC.CD_CENTRO as codCentro, " +
										"LC.TX_HISTORICO_ATIVIDADE as descricao, " + 
										"TRUNC(ARQ.DT_PROC_CLARO) as dataProcessamento, " +
										"SUM(LC.VL_CONTABILIZADO) as valorBruto " +
										"FROM   SCC_ARQUIVO_COBILLING ARQ, " + 
										"SCC_LANCAMENTO_CONTABIL LC, " +
										"SCC_ATIVIDADE_CONTABIL AC " +
										"WHERE  ARQ.CD_TIPO_ARQUIVO = 240 " +
										"AND ARQ.SQ_ARQUIVO = LC.SQ_ARQUIVO " +
										"AND LC.CD_TIPO_LANCAMENTO_CONTABIL IN ( 50, 31 ) " + 
										"AND AC.SQ_ATIVIDADE = LC.SQ_ATIVIDADE ";

	public static final String GROUP_BY = "GROUP  BY LC.DT_LANCAMENTO_CONTABIL, " + 
											"LC.CD_CSP, " +
											"LC.CD_LOCAL_NEGOCIO, " +
											"LC.CD_EMPRESA_CONTABIL, " +
											"AC.CD_CONTA_CREDITO, " +
											"AC.CD_CONTA_DEBITO, " +
											"LC.CD_CONTA_CONTABIL, " +
											"LC.CD_CENTRO, " +
											"LC.TX_HISTORICO_ATIVIDADE, " + 
											"TRUNC(ARQ.DT_PROC_CLARO) " +
											"ORDER  BY LC.DT_LANCAMENTO_CONTABIL";
	
	public static final String FILTRO_OPERADORA_EXTERNA = "AND ARQ.CD_EOT_LD = :operadoraExterna ";
	
	public static final String FILTRO_OPERADORA_CLARO = "AND ARQ.CD_EOT_CLARO = :operadoraClaro ";
	
	public static final String FILTRO_INICIO = "AND LC.DT_LANCAMENTO_CONTABIL >= :dataInicio ";
	
	public static final String FILTRO_FIM = "AND LC.DT_LANCAMENTO_CONTABIL <= :dataFim ";
	
}
