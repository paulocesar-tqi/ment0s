package com.claro.cobillingweb.persistence.dao.query;

import com.claro.cobillingweb.persistence.dao.internal.impl.SccCreditoDAOImpl;

/**
 * SQL Nativo para o DAO {@link SccCreditoDAOImpl}.
 *
 */
public class SccCreditoDAONativeSQL {

	public static final String carregaRelatorioCreditosSQL = " SELECT CD_EOT_LD,CD_EOT_CLARO,SQ_ARQUIVO,NO_ARQUIVO, "+  
                     "TP_CREDITO,CD_STATUS,DS_CREDITO,DS_STATUS,VL_CREDITO,DT_CREDITO,SQ_CREDITO,NU_MSISDN_ORIGEM, "+  
                     "QUANTIDADE,MINUTOS_QUEIMADOS,MINUTOS_AJUSTADOS,ROWNUM AS LINHA "+ 
                     "FROM (SELECT NVL(C.CD_EOT_LD, '000') AS CD_EOT_LD, "+  
                     "NVL(C.CD_EOT_CLARO, '000') AS CD_EOT_CLARO,C.SQ_ARQUIVO_CREDITO AS SQ_ARQUIVO,ARQ.NO_ARQUIVO, "+  
                     "NVL(C.TP_CREDITO, ' ') AS TP_CREDITO,NVL(C.CD_STATUS_CREDITO, ' ') AS CD_STATUS,CDORG.DS_DOMINIO AS DS_CREDITO, "+  
                     "STCRD.DS_DOMINIO AS DS_STATUS,NVL(C.VL_CREDITO, 0) AS VL_CREDITO,C.DT_CREDITO,C.SQ_CREDITO AS SQ_CREDITO, "+  
                     "C.NU_MSISDN_ORIGEM,COUNT(1) QUANTIDADE,SUM(CC.HR_DURACAO_REAL) * 60 AS MINUTOS_QUEIMADOS,SUM(CC.MI_DURACAO_TARIFADA) AS MINUTOS_AJUSTADOS "+  
                     "FROM SCC_CREDITO C,SCC_ARQUIVO_CREDITO ARQ,(SELECT CD_DOMINIO, TP_DOMINIO, DS_DOMINIO FROM SCC_PRE_DOMINIO "+  
                     "WHERE TP_DOMINIO = 'CDORG') CDORG, (SELECT CD_DOMINIO, TP_DOMINIO, DS_DOMINIO FROM SCC_PRE_DOMINIO "+  
                     "WHERE TP_DOMINIO = 'STCRD') STCRD,SCC_OPERADORA LD,SCC_OPERADORA CLARO,SCC_CDR_COBL CC,SCC_CDR_CREDITO CR "+  
                     "WHERE CC.DT_PROC_EXTERNA >= ADD_MONTHS(:dtCredito, -1) AND CC.SQ_CREDITO = CR.SQ_CREDITO AND CC.NU_CDR = CR.NU_CDR "+  
                     " AND CR.SQ_CREDITO = C.SQ_CREDITO " +
                     "AND C.DT_CREDITO BETWEEN  :dtInicio AND :dtFinal AND C.SQ_ARQUIVO_CREDITO = ARQ.SQ_ARQUIVO_CREDITO "+  
                     " AND C.CD_EOT_LD = LD.CD_EOT AND LD.CD_TIPO_SERVICO = 'C' AND C.CD_EOT_CLARO = CLARO.CD_EOT AND CLARO.CD_TIPO_SERVICO = 'M' ";
	
	
	public static final String carregaRelatorioCreditosProjections = " GROUP BY C.CD_EOT_LD,"+
                                "C.CD_EOT_CLARO, "+ 
                                "C.SQ_ARQUIVO_CREDITO, "+  
                                "ARQ.NO_ARQUIVO, "+  
                                "NVL(C.TP_CREDITO, ' '), "+  
                                "NVL(C.CD_STATUS_CREDITO, ' '), "+  
                                "CDORG.DS_DOMINIO, "+  
                                "STCRD.DS_DOMINIO, "+  
                                "NVL(C.VL_CREDITO, 0), "+  
                                "C.DT_CREDITO, "+  
                                "C.SQ_CREDITO, "+  
                                "C.NU_MSISDN_ORIGEM) ";
	
	public static final String carregaDetalhesCreditoSQL = "SELECT ARQ.NO_ARQUIVO , COBL.NU_MSISDN_ORIGEM,COBL.NU_TELEFONE_DESTINO,COBL.DT_CHAMADA, "+
						"CDR.HR_INICIO_CHAMADA , CDR.NU_CDR,c.sq_credito,C.DT_CREDITO,C.VL_CREDITO,COBL.MI_DURACAO_TARIFADA, "+
						"COBL.VL_BRUTO_CHAMADA "+
						"FROM SCC_CDR_CREDITO CDR , "+
						"SCC_CDR_COBL COBL, "+
						"SCC_CREDITO C, "+
						"SCC_ARQUIVO_CREDITO ARQ "+
						"WHERE "+  
						"CDR.NU_CDR = COBL.NU_CDR "+
						"AND C.SQ_ARQUIVO_CREDITO = :seqArqCredito "+
						"AND C.SQ_ARQUIVO_CREDITO = ARQ.SQ_ARQUIVO_CREDITO "+
						"AND CDR.SQ_CREDITO = :seqCredito "+
						"AND CDR.SQ_CREDITO = C.SQ_CREDITO "+
						"AND CDR.SQ_CREDITO = COBL.SQ_CREDITO(+) "+
						"ORDER BY C.NU_MSISDN_ORIGEM,DT_CHAMADA,HR_INICIO_CHAMADA";
}
