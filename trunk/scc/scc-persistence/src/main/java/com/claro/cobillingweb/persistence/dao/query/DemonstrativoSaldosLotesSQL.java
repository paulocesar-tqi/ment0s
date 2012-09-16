package com.claro.cobillingweb.persistence.dao.query;

public class DemonstrativoSaldosLotesSQL {

	public static final String SQL = "SELECT "+
    " ME.CD_MOTIVO_EVENTO as cdMotivoEvento, ME.DS_MOTIVO_EVENTO as dsMotivoEvento, "+
    "                   SU.CD_MOTIVO_REJEICAO as cdMotivoRejeicao,MR.DS_MOTIVO_REJEICAO as dsMotivoRejeicao, "+
    "    NVL(SUM(SU.QT_CDRS), 0) as qtCdrs,   "+
    "    NVL(SUM(SU.QT_DURACAO_TARIFADA), 0) as qtMinutos, "+  
    "    NVL(SUM(SU.VL_BRUTO_CHAMADA), 0) as valor "+
    "    FROM SCC_ARQUIVO_COBILLING  ARQ,  "+
    "    SCC_MOTIVO_REJEICAO MR, "+
    "    SCC_ARQUIVO_SUMARIZADO SU, "+ 
    "    SCC_MOTIVO_EVENTO ME,  "+
    "    SCC_COMPOSICAO_PRODUTO CP "+ 
    "    WHERE ARQ.SQ_ARQUIVO_ORIGEM = 0 "+ 
    "    AND ME.CD_STATUS_CDR = SU.CD_STATUS_CDR "+
    "    AND SU.SQ_ARQUIVO = ARQ.SQ_ARQUIVO "+
    "    AND SU.CD_COMPONENTE_PRODUTO = CP.CD_COMPONENTE_PRODUTO "+
    "    AND ARQ.DT_PROC_EXTERNA BETWEEN :dataInicial AND :dataFinal "+
    "    AND SU.CD_MOTIVO_REJEICAO = MR.CD_MOTIVO_REJEICAO(+) ";
	
	public static final String FILTRO_ARQUIVO = "    AND ARQ.CD_TIPO_ARQUIVO= :cdTipoArquivo ";
	public static final String FILTRO_PRODUTO = " AND CP.CD_PRODUTO_COBILLING = :cdProdutoCobilling ";
	public static final String FILTRO_LD = "    AND ARQ.CD_EOT_LD = :cdEOTLD ";
	public static final String FILTRO_CLARO = "    AND ARQ.CD_EOT_CLARO = :cdEOTClaro ";
	
	public static final String PROJECTIOS = " GROUP BY ME.CD_MOTIVO_EVENTO,ME.DS_MOTIVO_EVENTO,SU.CD_MOTIVO_REJEICAO,MR.DS_MOTIVO_REJEICAO "+
    " order by ME.CD_MOTIVO_EVENTO ";
}
