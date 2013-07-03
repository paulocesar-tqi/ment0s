package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92038883
 *
 */

public class SccRelatorioQuestionamentoResultadoDAONativeSQL {
		
		public static final String SQL = "SELECT " +
				   "RQ.SQ_QUESTIONAMENTO, " +
			       "RQ.SQ_ARQUIVO, " +
			       "RQ.CD_EOT_LD, " +
			       "RQ.CD_EOT_CLARO, " +
                   "TO_CHAR(RQ.DT_PROTOCOLO_LD, 'DD/MM/YYYY'), " +
	               "TO_CHAR(RQ.DT_PROTOCOLO_CLARO, 'DD/MM/YYYY'), " +
	               "TO_CHAR(RQ.DT_CORRECAO, 'DD/MM/YYYY'), " +
	               "TO_CHAR(RQ.DT_ANALISE, 'DD/MM/YYYY'), " +
			       "RQ.VL_POTENCIAL_LD, " +
			       "RQ.VL_POTENCIAL_CLARO, " +
			       "RQ.VL_PENALIDADE_LD, " +
			       "RQ.VL_PENALIDADE_CLARO, " +
			       "RQ.PE_PROCEDENTE, " +
			       "RQ.PE_SEM_ANALISE, " +
			       "RQ.QT_CDRS, " +
			       "RQ.MI_TARIFADOS, " +
			       "TO_CHAR(RQ.DT_REPASSE, 'DD/MM/YYYY'), "+
			       "RQ.DT_CRIACAO, " +
			       "RQ.DT_ATUALIZACAO, "+
			       "RQ.CD_USUARIO_MANUT, " +
			       "AC.NO_ARQUIVO " +
			       "FROM SCC_RESULTADO_QUESTIONAMENTO RQ, " +
			       "(SELECT SQ_ARQUIVO, NO_ARQUIVO " +
			       "FROM SCC_ARQUIVO_COBILLING) AC " +
			       "WHERE RQ.SQ_ARQUIVO = AC.SQ_ARQUIVO ";

	public static final String FILTRO_SQQUESTIONAMENTO = "AND RQ.SQ_QUESTIONAMENTO = :sqQuestionamento ";
			       
	public static final String FILTRO_CDEOTLD = "AND RQ.CD_EOT_LD = :cdEOTLD ";
				
	public static final String FILTRO_STATUS_CDR =" AND RQ.CD_USUARIO_MANUT = :tpStatus ";	
	
}
