package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92038883
 *
 */

public class SccPreRelatorioCriticasDAONativeSQL {
	
	public static final String SQL = "Select " +
            "CC.DT_CHAMADA AS DTCHAMADA, "+
            "(SELECT PD.DS_DOMINIO "+
            "FROM SCC_PRE_DOMINIO PD "+
            "WHERE PD.TP_DOMINIO = 'STCHM' "+
            "AND PD.CD_DOMINIO = TO_CHAR(CC.CD_STATUS_CDR)) AS STATUSCHAMADA, "+
            "(SELECT PD.CD_DOMINIO || ' - ' || PD.DS_DOMINIO "+
            "FROM SCC_PRE_DOMINIO PD "+
            "WHERE PD.TP_DOMINIO = 'MTREJ' "+
            "AND PD.CD_DOMINIO = TO_CHAR(CC.CD_MOTIVO_REJEICAO)) AS MOTIVO_REJEICAO, "+
            "(SELECT PD.CD_DOMINIO || ' - ' || PD.DS_DOMINIO "+
            "FROM SCC_PRE_DOMINIO PD "+
            "WHERE PD.TP_DOMINIO = 'DEFCH' "+
            "AND PD.CD_DOMINIO = TO_CHAR(CC.CD_DEFEITO)) AS CDDEFEITO, "+
            "(SELECT DS_OPERADORA || ' (' || CD_EOT || ')' "+
            "FROM SCC_OPERADORA "+
            "WHERE CD_EOT = CC.CD_EOT_ORIGEM) AS OPERADORACLARO, "+
            "(SELECT DS_OPERADORA || ' (' || CD_EOT || ')' "+
            "FROM SCC_OPERADORA "+
            "WHERE CD_EOT = CC.CD_EOT_DESTINO) AS OPERADORALD, "+
            "CAST((SELECT CD_CSP "+
            "FROM SCC_OPERADORA "+
            "WHERE CD_EOT = CC.CD_EOT_DESTINO) AS VARCHAR(2)) AS CSP, "+
            "CC.NU_MSISDN_ORIGEM AS NROA, "+
            "CC.NU_TELEFONE_DESTINO AS NROB, "+
            "CC.CDS_PAIS_DESTINO AS CDPAIS, "+
            "CC.CD_LOCALIDADE_DESTINO CNAREAVISITADA, "+
            "CC.CD_TIPO_CHAMADA AS TIPOCHAMADA, "+
            "CC.HR_DURACAO_REAL AS DURACAOREAL, "+
            "CC.MI_DURACAO_TARIFADA AS DURACAOTARIFADA, "+
            "CC.VL_BRUTO_CHAMADA AS VALORBRUTO "+
            "FROM SCC_CDR_COBL CC "+
			"WHERE CC.DT_PROC_EXTERNA >= :dtInicioEvento "+
			"AND CC.DT_PROC_EXTERNA < :dtFimEvento ";

public static final String FILTRO_CDEOTLD = "AND CC.CD_EOT_DESTINO = :cdEOTLD ";
			
public static final String FILTRO_CDEOTCLARO = "AND CC.CD_EOT_ORIGEM = :cdEOTClaro ";
			
public static final String FILTRO_STATUS_CDR =" AND CC.CD_STATUS_CDR = :cdStatus ";	

public static final String FILTRO_MOTIVO_REJEICAO =" AND CC.CD_MOTIVO_REJEICAO = :cdMotivoRejeicao ";	

public static final String FILTRO_CD_DEFEITO =" AND CC.CD_DEFEITO = :cdDefeito ";	


}
