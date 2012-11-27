package com.claro.cobillingweb.persistence.dao.query;

public class SccBatimentoWruppDAONativeSQL {

	public static final String SQL = "SELECT nvl(WRUPP.DT_CHAMADA, scc.DT_CHAMADA) data," +
			"	nvl(WRUPP.CD_EOT_LD, scc.CD_EOT_LD) cdEOTLD," +
			"	nvl(WRUPP.CD_EOT_CLARO, scc.CD_EOT_CLARO) cdEOTClaro," +
			"	NVL(WRUPP.QT_CDRS,0) qtdChamadasWrupp," +
			"	NVL(WRUPP.VL_BRUTO_CHAMADA,0) vlrBrutoWrupp," +
			"	NVL(SCC.QT_CDRS,0) qtdChamadasScc," +
			"	NVL(SCC.VL_BRUTO_CHAMADA,0) vlrBrutoScc" +
			" FROM   ( SELECT CW.DT_CHAMADA AS DT_CHAMADA, OP.CD_EOT AS CD_EOT_LD, OA.CD_EOT AS CD_EOT_CLARO," +
			"			sum(CW.QT_CHAMADAS) AS QT_CDRS, sum(CW.VL_TOTAL_CHAMADAS) AS VL_BRUTO_CHAMADA" +
			"		FROM   SCC_CONSOLIDADO_CHAMADAS_WRUPP CW, SCC_OPERADORA OP, SCC_OPERADORA_CD_AREA OA " +
			"		WHERE  CW.CD_CSP = OP.CD_CSP	AND   CW.CD_AREA = OA.CD_AREA	AND   OP.CD_TIPO_SERVICO = 'C'" +
			"				AND   OP.CD_EOT = NVL(:cdEOTLD, OP.CD_EOT)	AND   OA.CD_EOT = NVL(:cdEOTClaro, OA.CD_EOT)	" +
			"				AND   (CW.DT_CHAMADA >= :dtInicio)	" +
			"				AND   (CW.DT_CHAMADA <= :dtFim)	" +
			"		GROUP BY CW.DT_CHAMADA, OP.CD_EOT, OA.CD_EOT) WRUPP,	" +
			"		( SELECT AC.DT_CHAMADA AS DT_CHAMADA, AC.CD_EOT_LD AS CD_EOT_LD, AC.CD_EOT_CLARO AS CD_EOT_CLARO," +
			"			SUM(AC.QT_CDRS) AS QT_CDRS, SUM(AC.VL_BRUTO_CHAMADA) AS VL_BRUTO_CHAMADA	" +
			"		FROM   SCC_ARQ_SUM_COBL AC" +
			"		WHERE  AC.DT_CHAMADA >= :dtInicio	AND   AC.DT_CHAMADA <= :dtFim" +
			"				AND   AC.DT_PROC_EXTERNA >= :dtInicio - 90	AND   AC.DT_PROC_EXTERNA <= SYSDATE - 1	" +
			"				AND   AC.CD_EOT_LD = NVL(:cdEOTLD, AC.CD_EOT_LD) AND   AC.CD_EOT_CLARO = NVL(:cdEOTClaro, AC.CD_EOT_CLARO)	" +
			"		GROUP BY AC.DT_CHAMADA, AC.CD_TIPO_CHAMADA, AC.CD_EOT_LD, AC.CD_EOT_CLARO, AC.CD_PERIODO_CHAMADA) SCC	" +
			" WHERE  WRUPP.DT_CHAMADA (+)= SCC.DT_CHAMADA	" +
			"		AND   WRUPP.CD_EOT_LD (+)= SCC.CD_EOT_LD	" +
			"		AND   WRUPP.CD_EOT_CLARO (+)= SCC.CD_EOT_CLARO	" +
			"" +
			" UNION " +
			"" +
			"SELECT nvl(WRUPP.DT_CHAMADA, scc.DT_CHAMADA) data," +
			"	nvl(WRUPP.CD_EOT_LD, scc.CD_EOT_LD) cdEOTLD," +
			"	nvl(WRUPP.CD_EOT_CLARO, scc.CD_EOT_CLARO) cdEOTClaro," +
			"	NVL(WRUPP.QT_CDRS,0) qtdChamadasWrupp," +
			"	NVL(WRUPP.VL_BRUTO_CHAMADA,0) vlrBrutoWrupp," +
			"	NVL(SCC.QT_CDRS,0) qtdChamadasScc," +
			"	NVL(SCC.VL_BRUTO_CHAMADA,0) vlrBrutoScc" +
			" FROM   ( SELECT CW.DT_CHAMADA AS DT_CHAMADA, OP.CD_EOT AS CD_EOT_LD, OA.CD_EOT AS CD_EOT_CLARO," +
			"			sum(CW.QT_CHAMADAS) AS QT_CDRS, sum(CW.VL_TOTAL_CHAMADAS) AS VL_BRUTO_CHAMADA" +
			"		FROM   SCC_CONSOLIDADO_CHAMADAS_WRUPP CW, SCC_OPERADORA OP, SCC_OPERADORA_CD_AREA OA " +
			"		WHERE  CW.CD_CSP = OP.CD_CSP	AND   CW.CD_AREA = OA.CD_AREA	AND   OP.CD_TIPO_SERVICO = 'C'" +
			"				AND   OP.CD_EOT = NVL(:cdEOTLD, OP.CD_EOT)	AND   OA.CD_EOT = NVL(:cdEOTClaro, OA.CD_EOT)	" +
			"				AND   (CW.DT_CHAMADA >= :dtInicio)	" +
			"				AND   (CW.DT_CHAMADA <= :dtFim)	" +
			"		GROUP BY CW.DT_CHAMADA, OP.CD_EOT, OA.CD_EOT) WRUPP,	" +
			"		( SELECT AC.DT_CHAMADA AS DT_CHAMADA, AC.CD_EOT_LD AS CD_EOT_LD, AC.CD_EOT_CLARO AS CD_EOT_CLARO," +
			"			SUM(AC.QT_CDRS) AS QT_CDRS, SUM(AC.VL_BRUTO_CHAMADA) AS VL_BRUTO_CHAMADA	" +
			"		FROM   SCC_ARQ_SUM_COBL AC" +
			"		WHERE  AC.DT_CHAMADA >= :dtInicio	AND   AC.DT_CHAMADA <= :dtFim" +
			"				AND   AC.DT_PROC_EXTERNA >= :dtInicio - 90	AND   AC.DT_PROC_EXTERNA <= SYSDATE - 1	" +
			"				AND   AC.CD_EOT_LD = NVL(:cdEOTLD, AC.CD_EOT_LD) AND   AC.CD_EOT_CLARO = NVL(:cdEOTClaro, AC.CD_EOT_CLARO)	" +
			"		GROUP BY AC.DT_CHAMADA, AC.CD_TIPO_CHAMADA, AC.CD_EOT_LD, AC.CD_EOT_CLARO, AC.CD_PERIODO_CHAMADA) SCC	" +
			" WHERE  WRUPP.DT_CHAMADA = SCC.DT_CHAMADA(+)	" +
			"		AND   WRUPP.CD_EOT_LD = SCC.CD_EOT_LD(+)	" +
			"		AND   WRUPP.CD_EOT_CLARO = SCC.CD_EOT_CLARO(+)	";
	
	public static final String SQL2 = "SELECT " +
											"	sysdate as data, " +
											"	(SELECT decode(TABLE_NAME, TABLE_NAME, '001', '111') FROM ALL_TABLES WHERE ROWNUM = 1) as cdEOTClaro,"+
											"	(SELECT decode(TABLE_NAME, TABLE_NAME, '300', '111') FROM ALL_TABLES WHERE ROWNUM = 1) as cdEOTLD,"+
											"	10	as qtdChamadasWrupp,"+
											"  	13.45 as vlrBrutoWrupp,"+
											"  	7 as qtdChamadasScc,"+
											"  	10.22	as vlrBrutoScc"+
									" FROM 	DUAL " +
									" WHERE 1 = 1 ";										
										

								
}
