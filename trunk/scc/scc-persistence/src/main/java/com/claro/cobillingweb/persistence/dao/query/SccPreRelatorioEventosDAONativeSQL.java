package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92038883
 *
 */

public class SccPreRelatorioEventosDAONativeSQL {
	
	public static final String SQL = "Select " +
			"VW.DT_REFERENCIA, "+
			"VW.OP_CLARO, "+
			"VW.OP_EXTERNA, "+
			"VW.STATUS_CHAMADA, "+
			"VW.MOTIVO_REJEICAO, "+
			"VW.DEFEITO, "+
			"SUM(VW.QT_CDRS) AS QUANTIDADE, "+
			"SUM(VW.DURACAO_REAL) AS DURACAO_REAL, "+
			"SUM(VW.DURACAO_TARIFADA) AS DURACAO_TARIFADA, "+
			"SUM(VW.VALOR_BRUTO) AS VALOR_BRUTO "+
			"FROM (SELECT CC.DT_PROC_EXTERNA AS DT_REFERENCIA, "+
			"(SELECT DS_OPERADORA "+
			"FROM SCC_OPERADORA "+
			"WHERE CD_EOT = CC.CD_EOT_LD) AS OP_EXTERNA, "+
			"(SELECT DS_OPERADORA "+
			"FROM SCC_OPERADORA "+
			"WHERE CD_EOT = CC.CD_EOT_CLARO) AS OP_CLARO, "+
			"(SELECT PD.DS_DOMINIO "+
			"FROM SCC_PRE_DOMINIO PD "+
			"WHERE PD.TP_DOMINIO = 'STCHM' "+
			"AND PD.CD_DOMINIO = TO_CHAR(CC.CD_STATUS_CDR)) AS STATUS_CHAMADA, "+
			"(SELECT PD.CD_DOMINIO ||' - '|| PD.DS_DOMINIO "+
			"FROM SCC_PRE_DOMINIO PD "+
			"WHERE PD.TP_DOMINIO = 'MTREJ' "+
			"AND PD.CD_DOMINIO = TO_CHAR(CD_MOTIVO_REJEICAO)) AS MOTIVO_REJEICAO, "+
			"(SELECT PD.CD_DOMINIO || ' - ' || PD.DS_DOMINIO "+
			"FROM SCC_PRE_DOMINIO PD "+
			"WHERE PD.TP_DOMINIO = 'DEFCH' "+
			"AND PD.CD_DOMINIO = TO_CHAR(CC.CD_DEFEITO)) AS DEFEITO, "+
			"CC.QT_DURACAO_REAL DURACAO_REAL, "+
			"CC.QT_DURACAO_TARIFADA DURACAO_TARIFADA, "+
			"CC.VL_BRUTO_CHAMADA VALOR_BRUTO, "+
			"CC.QT_CDRS "+
			"FROM SCC_ARQ_SUM_COBL CC "+
			"WHERE CC.DT_PROC_EXTERNA >= :dtInicioEvento "+
			"AND CC.DT_PROC_EXTERNA <= :dtFimEvento ";

public static final String FILTRO_CDEOTLD = "AND CC.CD_EOT_LD = :cdEOTLD ";
			
public static final String FILTRO_CDEOTCLARO = "AND CC.CD_EOT_CLARO = :cdEOTClaro ";
			
public static final String FILTRO_STATUS_CDR =" AND CC.CD_STATUS_CDR = :tpStatus ";		
			


public static final String SQL_GROUP = ") VW "+
			"GROUP BY VW.DT_REFERENCIA, "+
			"VW.OP_CLARO, "+
			"VW.OP_EXTERNA, "+
			"VW.STATUS_CHAMADA, "+
			"VW.MOTIVO_REJEICAO, "+
			"VW.DEFEITO";

}
