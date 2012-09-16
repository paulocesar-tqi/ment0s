/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 93046251
 *
 */
public class SccBatimentoArquivosDAONativeSQL {
	
	public static final String SQL = "Select " +
											" ARQ.DT_CONNECT as dtConnectClaro,"+
											" ARQ.DT_PROC_EXTERNA as dtReferenciaClaro, "+
											" SBA.NO_ARQUIVO as nomeArquivoClaro, "+
											" ARQP.NO_ARQUIVO as dnsProtocoloClaro, "+
											" ARQ.QT_DURACAO_TARIFADA as duracaoTarifadaClaro, "+
											" ARQ.QT_REGISTROS as quantidadeClaro, "+
											" ARQ.VL_LIQUIDO_ARQUIVO as valorLiquidoClaro, "+
											" ARQ.CD_ERRO_PROTOCOLO as erroProtocoloClaro, "+
											" SEP.DS_ERRO_PROTOCOLO as descErroProtocoloClaro, "+
											" SBA.NO_ARQUIVO as nomeArquivoLD, "+
											" NVL(SBA.QT_MINUTOS_TARIFADOS,0) as duracaoTarifadaLD, "+
											" NVL(SBA.QT_REGISTROS,0) as quantidadeLD, "+
											" NVL(SBA.VL_TOTAL_CHAMADAS,0) as valorLiquidoLD, "+
											" SBA.CD_STATUS_BATIMENTO||'-' || ST.DS_STATUS_BATIMENTO as statusLD, "+
											" NVL(ARQ.QT_DURACAO_TARIFADA,0) - NVL(SBA.QT_MINUTOS_TARIFADOS,0) as duracaoTarifadaBat, "+
											" NVL(ARQ.QT_REGISTROS,0) - NVL(SBA.QT_REGISTROS,0) as quantidadeBat, "+
											" NVL(ARQ.VL_LIQUIDO_ARQUIVO,0) - NVL(SBA.VL_TOTAL_CHAMADAS,0) as valorLiquidoBat "+
									 " FROM  "+
											" SCC_BATIMENTO_ARQUIVOS SBA, "+
											" SCC_ARQUIVO_COBILLING ARQ, "+
											" SCC_ARQUIVO_COBILLING ARQN, "+
											" SCC_TIPO_ARQUIVO STA,   "+
											" SCC_ARQUIVO_COBILLING ARQP, "+
											" SCC_ERRO_PROTOCOLO SEP, "+
											" SCC_STATUS_BATIMENTO ST   "+
									 "  WHERE SBA.CD_STATUS_BATIMENTO = ST.CD_STATUS_BATIMENTO (+) "+
										" AND SBA.SQ_ARQUIVO_BATIMENTO = ARQN.SQ_ARQUIVO (+) "+
										" AND SBA.SQ_ARQUIVO_SCC = ARQ.SQ_ARQUIVO (+) "+
										" AND ARQ.SQ_ARQUIVO = ARQP.SQ_ARQUIVO_ORIGEM (+) "+
										" AND ARQP.CD_ERRO_PROTOCOLO = SEP.CD_ERRO_PROTOCOLO (+) "+
										" AND ARQ.CD_TIPO_ARQUIVO = STA.CD_TIPO_ARQUIVO (+) "+
										" AND ARQP.CD_TIPO_ARQUIVO IN (60,65) ";
										
										


	
	
	public static final String FILTRO_DT_INICIO_BATIMENTO = "AND TRUNC(SBA.DT_INICIO_BATIMENTO) >= :dtInicioBatimento ";
	
	public static final String FILTRO_DT_FIM_BATIMENTO = "AND TRUNC(SBA.DT_FIM_BATIMENTO) <= :dtFimBatimento ";
	
	public static final String FILTRO_CDEOTLD = "AND ARQ.CD_EOT_LD = :cdEOTLD ";
	
	public static final String FILTRO_CDEOTCLARO = "AND ARQ.CD_EOT_CLARO = :cdEOTClaro ";
	
	public static final String FILTRO_TIPO_BATIMENTO =" AND UPPER(STA.CD_TIPO_BATIMENTO) = :tpArquivo ";
	
	
											
											
}
