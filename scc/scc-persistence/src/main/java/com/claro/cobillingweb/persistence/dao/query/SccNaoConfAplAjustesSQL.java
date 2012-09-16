package com.claro.cobillingweb.persistence.dao.query;

public class SccNaoConfAplAjustesSQL {
	
	public static final String SQL = "Select ARQ.CD_EOT_LD, ARQ.CD_EOT_CLARO, OP.SG_UF UF, RS.DT_EVENTO DATAAJUSTE,  "+
									        "SUM(RS.VL_AJUSTE) VALORAJUSTE  "+
									 "FROM SCC_ARQUIVO_COBILLING ARQ, SCC_OPERADORA OP, SCC_RETORNO_SUMARIZADO RS "+
									 "WHERE ARQ.SQ_ARQUIVO_ORIGEM = 0 "+
									 "  AND ARQ.CD_TIPO_ARQUIVO = 40 "+
									 "  AND ARQ.CD_EOT_CLARO = OP.CD_EOT "+
									 "  AND OP.CD_TIPO_SERVICO = 'M' "+
									 "  AND ARQ.SQ_ARQUIVO = RS.SQ_ARQUIVO "+
									 "  AND RS.CD_MOTIVO_REJEICAO NOT IN (304,404,508) ";
	
	public static final String FILTRO_DT_INICIO =  "  AND ARQ.DT_PROC_EXTERNA >= :dtInicio ";
	
	public static final String FILTRO_DT_FIM = " AND ARQ.DT_PROC_EXTERNA <= :dtFim ";
	
	public static final String FILTRO_CD_EOT_LD = " AND ARQ.CD_EOT_LD = :cdEOTLD ";
	
	public static final String FILTRO_CD_EOT_CLARO = "AND ARQ.CD_EOT_CLARO = :cdEOTClaro";
	
	public static final String PROJECTION = " GROUP BY ARQ.CD_EOT_LD, ARQ.CD_EOT_CLARO, OP.SG_UF, RS.DT_EVENTO ";

	
	
	
	
									 

}
