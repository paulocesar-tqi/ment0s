package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92038883
 *
 */

public class SccRelatorioChamadasRefaturamentoDAONativeSQL {
	
	public static final String SQL = "Select " +
            "REF1.CD_EOT_CLARO, "+
            "REF1.CD_EOT_LD, "+
            "REF1.CD_REFATURAMENTO, "+
            "REF1.DT_REFERENCIA, "+
            "NU_MSISDN_ORIGEM as numeroA, "+
            "NU_TELEFONE_DESTINO as numeroB, "+
            "DT_CHAMADA as dataHora, "+
            "HR_INICIO_CHAMADA, "+
            "VL_LIQUIDO_CHAMADA as valor, "+
            "VL_BRUTO_CHAMADA, "+
            "MI_DURACAO_TARIFADA as minutoTarifado, "+
            "COB.CD_MOTIVO_REJEICAO as codigoCriticaInicial, "+
            "REF1.SQ_ARQUIVO_ORIGINAL as arquivoRemessa, "+
            "REF1.SQ_ARQUIVO_RETORNO as arquivoRetorno, "+
            "REF1.SQ_ARQUIVO_REFATURAMENTO as arquivoRemessaRefaturamento "+
            "FROM SCC_REFATURAMENTO REF1, "+
            "SCC_ARQUIVO_COBILLING ACO, "+
            "SCC_ARQUIVO_COBILLING ACR, "+
            "SCC_ARQUIVO_COBILLING ACF, "+
            "SCC_CDR_COBILLING COB "+
            "WHERE  "+
            "REF1.SQ_ARQUIVO_ORIGINAL = ACO.SQ_ARQUIVO "+
            "AND REF1.SQ_ARQUIVO_RETORNO = ACR.SQ_ARQUIVO "+
            "AND REF1.SQ_ARQUIVO_REFATURAMENTO = ACF.SQ_ARQUIVO "+
            "AND COB.NU_CDR = REF1.NU_CDR "+
            "AND REF1.DT_REFERENCIA >= :dtInicioPeriodo "+
			"AND REF1.DT_REFERENCIA <= :dtFimPeriodo ";


public static final String FILTRO_CDEOTLD = "AND REF1.CD_EOT_LD = :cdEOTLD ";
			
public static final String FILTRO_CDEOTCLARO = "AND REF1.CD_EOT_CLARO = :cdEOTClaro ";
			
public static final String FILTRO_STATUS_CDR =" AND UPPER(REF1.CD_REFATURAMENTO) = :cdRefaturamento ";	

public static final String FILTRO_CODIGO_IN = " AND UPPER(REF1.CD_REFATURAMENTO) IN ('RC','RR','RD') ";
			

}
