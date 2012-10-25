package com.claro.cobillingweb.persistence.dao.query;

public class SccBatimentoWruppDAONativeSQL {

	public static final String SQL = "SELECT " +
											"	sysdate as data, " +
											"	(SELECT decode(TABLE_NAME, TABLE_NAME, '001', '111') FROM ALL_TABLES WHERE ROWNUM = 1) as cdEOTClaro,"+
											"	(SELECT decode(TABLE_NAME, TABLE_NAME, '300', '111') FROM ALL_TABLES WHERE ROWNUM = 1) as cdEOTLD,"+
											"	10	as qtdChamadasWrupp,"+
											"  	13.45 as vlrBrutoWrupp,"+
											"  	7 as qtdChamadasScc,"+
											"  	10.22	as vlrBrutoScc"+
									" FROM 	DUAL " +
									" WHERE 1 = 1 ";										
										
	
	
	public static final String FILTRO_MES = "AND :mes = :mes ";
	
	public static final String FILTRO_ANO = "AND :ano = :ano ";
	
	public static final String FILTRO_CDEOTLD = "AND :cdEOTLD = :cdEOTLD ";
	
	public static final String FILTRO_CDEOTCLARO = "AND :cdEOTClaro = :cdEOTClaro ";	
								
}
