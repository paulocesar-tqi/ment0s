package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92038883
 *
 */

public class SccPreChamadasCreditoDAONativeSQL {
		
		public static final String SQL = "Select " +
    			"AC.NO_ARQUIVO AS ARQUIVO_CREDITO, " +
                "(SELECT DS_OPERADORA FROM SCC_OPERADORA " +
                "WHERE CD_EOT = C.CD_EOT_CLARO ) AS OP_CLARO, " +
                "(SELECT DS_OPERADORA FROM SCC_OPERADORA " +
                " WHERE CD_EOT = C.CD_EOT_LD) AS OP_EXTERNA, " +
                "CAST(O.CD_CSP AS VARCHAR2(2)) AS CSP, "+
        		"C.CD_STATUS_CREDITO AS STATUS_CREDITO, " +
        		"C.DT_CREDITO AS DATA_CREDITO, " +
        		"NVL(C.VL_CREDITO,0) AS VL_CREDITO, " +
        		"CCO.NU_CDR, " +
        		"CCO.SQ_CREDITO AS CODIGO_CREDITO, " +
        		"CCO.NU_MSISDN_ORIGEM AS NRO_A, " +
        		"CCO.NU_TELEFONE_DESTINO AS NRO_B, " +
        		"CCO.DT_CHAMADA, " +
        		"CCO.HR_INICIO_CHAMADA, " +
        		"CCO.MI_DURACAO_TARIFADA, " +
        		"CCO.VL_BRUTO_CHAMADA, " +
        		"ROWNUM AS LINHA \n" +
        		"FROM SCC_ARQUIVO_CREDITO AC, " +
        		"SCC_CREDITO C, " +
        		"SCC_CDR_COBL CCO, " +
        		"SCC_OPERADORA O, " +
        		"SCC_CDR_CREDITO CC " +
        		"WHERE CCO.DT_PROC_EXTERNA >= ADD_MONTHS(TO_DATE(:dtInicioCredito,'DD/MM/YYYY'),-1) " +
        		"AND CCO.NU_CDR = CC.NU_CDR " +
        		"AND CC.SQ_CREDITO = C.SQ_CREDITO " +
        		"AND O.CD_EOT = C.CD_EOT_LD " +
        		"AND AC.SQ_ARQUIVO_CREDITO = C.SQ_ARQUIVO_CREDITO " +
        		"AND C.DT_CREDITO >= :dtInicioCredito " +
            	"AND C.DT_CREDITO <= :dtFimCredito ";

	public static final String FILTRO_CDEOTLD = "AND C.CD_EOT_LD = :cdEOTLD ";
				
	public static final String FILTRO_CDEOTCLARO = "AND C.CD_EOT_CLARO = :cdEOTClaro ";
				
	public static final String FILTRO_STATUS_CDR =" AND C.CD_STATUS_CREDITO = :tpStatusCredito ";		


}
