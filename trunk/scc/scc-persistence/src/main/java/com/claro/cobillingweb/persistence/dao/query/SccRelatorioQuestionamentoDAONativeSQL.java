package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92038883
 *
 */

public class SccRelatorioQuestionamentoDAONativeSQL {
		
		public static final String SQL = "Select " +
				"CQ.SQ_QUESTIONAMENTO, " +
	            "CQ.CD_EOT_LD, " +
	            "CQ.CD_STATUS_QUESTIONAMENTO, " +
	            "CQ.DT_EVENTO, " +
	            "TO_CHAR(CQ.DT_PROTOCOLO_CLARO, 'DD/MM/YYYY'), " +
            	"TO_CHAR(CQ.DT_PROTOCOLO_LD, 'DD/MM/YYYY'), " +
            	"TO_CHAR(CQ.DT_ANALISE, 'DD/MM/YYYY'), " +
            	"TO_CHAR(CQ.DT_CORRECAO, 'DD/MM/YYYY'), " +
	            "CQ.TX_COMENTARIO_CLARO, " +
	            "CQ.TX_COMENTARIO_LD, " +
	            "CQ.TX_ANALISE, " +
	            "CQ.TX_CORRECAO, " +
	            "CQ.TX_MOTIVOS_REJEICAO, " +
	            "CQ.TX_ARQUIVOS, " +
	            "CQ.CD_IDENTIFICACAO_CARTA_CLARO, " +
	            "CQ.CD_IDENTIFICACAO_CARTA_LD, " +
	            "CQ.NO_RESPONSAVEL_CLARO, " +
	            "CQ.NO_RESPONSAVEL_LD, " +
	            "CQ.CD_PROCESSO, " +
	            "CQ.DT_CRIACAO, " +
	            "TO_CHAR(CQ.DT_ATUALIZACAO, 'DD/MM/YYYY'), "+
	            "CQ.CD_USUARIO_MANUT, " +
	            "NVL(SUM(CDR.MI_DURACAO_TARIFADA),0), " +
	            "NVL(SUM(CDR.VL_LIQUIDO_CHAMADA),0), " +
	            "NVL(SUM(CDR.VL_BRUTO_CHAMADA),0), " +
	            "NVL(SUM(CDR.VL_POTENCIAL_CLARO),0) " +
	            "FROM SCC_CONTROLE_QUESTIONAMENTO CQ, SCC_CDR_QUESTIONAMENTO CDR " +
	            "WHERE CQ.SQ_QUESTIONAMENTO = CDR.SQ_QUESTIONAMENTO ";

	public static final String FILTRO_CDEOTLD = "AND CQ.CD_EOT_LD = :cdEOTLD ";
				
	public static final String FILTRO_STATUS_CDR =" AND CQ.CD_STATUS_QUESTIONAMENTO = :tpStatus ";	
	
	public static final String SQL_GROUP = "GROUP BY CQ.SQ_QUESTIONAMENTO, "+
			"CQ.CD_EOT_LD, "+
			"CQ.CD_STATUS_QUESTIONAMENTO, "+
			"CQ.DT_EVENTO, "+
			"CQ.DT_PROTOCOLO_CLARO, "+
			"CQ.DT_PROTOCOLO_LD, "+
			"CQ.DT_ANALISE, "+
			"CQ.DT_CORRECAO, "+
			"CQ.TX_COMENTARIO_CLARO, "+
			"CQ.TX_COMENTARIO_LD, "+
			"CQ.TX_ANALISE, "+
			"CQ.TX_CORRECAO, "+
			"CQ.TX_MOTIVOS_REJEICAO, "+
			"CQ.TX_ARQUIVOS, "+
			"CQ.CD_IDENTIFICACAO_CARTA_CLARO, "+
			"CQ.CD_IDENTIFICACAO_CARTA_LD, "+
			"CQ.NO_RESPONSAVEL_CLARO, "+
			"CQ.NO_RESPONSAVEL_LD, "+
			"CQ.CD_PROCESSO, "+
			"CQ.DT_CRIACAO, "+
			"CQ.DT_ATUALIZACAO, "+
			"CQ.CD_USUARIO_MANUT "+
			"ORDER BY CQ.DT_EVENTO ";
}
