package com.claro.cobillingweb.persistence.dao.query;

public class SccAcordoParcelamentoSQL {
	
	public static final String SQL = "SELECT "+
									"	T4.CD_CSP as OPERADORA_LD, "+
									"	T4.CD_EOT_CLARO as OPERADORA_CLARO, "+
									"	T1.NU_ACORDO_PARCELAMENTO as COD_ACORDO, "+
									"	T1.DT_CARGA as DATA_ACORDO, "+
									"	T2.VL_TOTAL_ACORDADO as VALOR_ACORDADO, "+			
									"	COUNT(T3.NU_ACORDO_PARCELAMENTO) as PARCELAS, "+
									"	T1.NU_FATURA as NUMERO_FATURA, "+
									"	T3.NU_CONTA as NUMERO_CONTA, "+
									"	SUM(T3.VL_PARCELA_OPERADORA_LD) as VL_PARCELA_OPERADORA, "+
									"			DECODE(T2.CD_STATUS_ACORDO_PARC, 'N', 'Novo', 'H', 'Honrado', 'C', 'Fechado', 'A', 'Cancelado', 'B', 'Quebrado', 'E', 'Expirado', 'X', 'Outros',T2.CD_STATUS_ACORDO_PARC ) STATUS "+				
									" FROM	"+
									"	SCC_ACORDO_PARC_FATURA_LD T1, "+
									"	SCC_ACORDO_PARCELAMENTO T2, "+
									"	SCC_PARCELA T3, "+
									"	SCC_FATURA_LD T4 "+
									" WHERE "+
									"	T1.NU_CONTA = T2.NU_CONTA "+
									" AND	T1.NU_ACORDO_PARCELAMENTO = T2.NU_ACORDO_PARCELAMENTO "+
									" AND	T1.NU_CONTA = T2.NU_CONTA "+
									" AND 	T1.NU_ACORDO_PARCELAMENTO = T3.NU_ACORDO_PARCELAMENTO "+
									" AND	T1.NU_CONTA = T3.NU_CONTA "+
									" AND	T4.NU_FATURA = T1.NU_FATURA "+
									" AND	T4.NU_NF = T1.NU_NF "+
									" AND	T4.CD_CSP = T1.CD_CSP "+
									" AND	T2.DT_ACORDO_PARCELAMENTO >= :dtInicial "+
									" AND	T2.DT_ACORDO_PARCELAMENTO <= :dtFinal ";	
	
	
	public static final String FILTRO_CSP = "AND T4.CD_CSP = :cdCsp ";
	
	public static final String FILTRO_EOTCLARO = "AND	T4.CD_EOT_CLARO = :cdEOTClaro ";
	
	public static final String FILTRO_STATUS_ACORDO = "AND	T2.CD_STATUS_ACORDO_PARC = :status ";
	
	public static final String FILTRO_NUMERO_ACORDO = "AND	T1.NU_ACORDO_PARCELAMENTO = :numeroAcordo";
	
	public static final String FILTRO_NUMERO_CONTA = "AND	T1.NU_CONTA = :numeroConta ";
	
	public static final String PROJECTIONS = " GROUP BY	T4.CD_CSP, 	T4.CD_EOT_CLARO, T3.NU_CONTA, T1.NU_ACORDO_PARCELAMENTO, "+
											 "	T1.DT_CARGA, T2.CD_STATUS_ACORDO_PARC, 	T2.VL_TOTAL_ACORDADO, 	T1.NU_FATURA ";		
	
	public static final String SQL_SINTETICO = " SELECT  T4.CD_CSP as OPERADORA_LD,  			"+        
											   "	     T4.CD_EOT_CLARO as OPERADORA_CLARO,     "+
											   "         TRUNC(T1.DT_CARGA) as DATA_ACORDO,      "+
											   "		 DECODE(T2.CD_STATUS_ACORDO_PARC, 'N', 'Novo', 'H', 'Honrado', 'C', 'Fechado', 'A', 'Cancelado', 'B', 'Quebrado', 'E', 'Expirado', 'X', 'Outros',T2.CD_STATUS_ACORDO_PARC ) STATUS, "+
											   "		 COUNT(T3.NU_ACORDO_PARCELAMENTO) as PARCELAS,        			"+
											   "		 SUM(T3.VL_PARCELA_OPERADORA_LD) as VL_PARCELA_OPERADORA, 		"+
											   "		 SUM(T3.VL_TOTAL_PARCELA ) as VALOR_ACORDADO 					"+
											   " FROM    SCC_ACORDO_PARC_FATURA_LD T1,     SCC_ACORDO_PARCELAMENTO T2,   "+       
											   "		 SCC_PARCELA T3,    SCC_FATURA_LD T4  							"+
											   " WHERE   T1.NU_CONTA = T2.NU_CONTA   									"+
											   "  AND    T1.NU_ACORDO_PARCELAMENTO = T2.NU_ACORDO_PARCELAMENTO 		"+  
											   "  AND    T1.NU_CONTA = T2.NU_CONTA   								"+
											   "  AND  	 T1.NU_ACORDO_PARCELAMENTO = T3.NU_ACORDO_PARCELAMENTO 		"+  
											   "  AND  	 T1.NU_CONTA = T3.NU_CONTA   								"+
											   "  AND    T4.NU_FATURA = T1.NU_FATURA   								"+
											   "  AND  	 T4.NU_NF = T1.NU_NF  										"+
											   "  AND  	 T4.CD_CSP = T1.CD_CSP   									"+
											   "  AND	 T2.DT_ACORDO_PARCELAMENTO >= :dtInicial "+
											   "  AND 	 T2.DT_ACORDO_PARCELAMENTO <= :dtFinal ";	
	
	public static final String PROJECTIONS_SINTETICO = " GROUP BY  T4.CD_CSP, T4.CD_EOT_CLARO, TRUNC(T1.DT_CARGA), T2.CD_STATUS_ACORDO_PARC ";
	
	public static final String SQL_ACOMPANHAMENTO = " SELECT FATURA.CD_CSP as OPERADORA_LD,"+     
													" 		 FATURA.CD_EOT_CLARO as OPERADORA_CLARO,"+
													"		 PARCELA.NU_ACORDO_PARCELAMENTO as COD_ACORDO, "+			
													" 		 ACORDO.NU_ACORDO_PARCELAMENTO as NUMERO_ACORDO,"+      
													" 		 PARCELA.NU_PARCELA as NUMERO_PARCELA, "+  
													"  		 PARCELA.VL_TOTAL_PARCELA as VALOR, "+
													" 		 DECODE(AC_PARC.CD_STATUS_ACORDO_PARC , 'N', 'Novo', 'C', 'Fechado', 'A', 'Cancelado', 'B', 'Quebrado', 'E', 'Expirado', 'H', 'Honrado', AC_PARC.CD_STATUS_ACORDO_PARC  ) as STATUS,"+
													"	     PARCELA.NU_CONTA as NUMERO_CONTA, "+	
													"	     PARCELA.VL_PARCELA_OPERADORA_LD as VL_PARCELA_OPERADORA "+
													" FROM  SCC_FATURA_LD FATURA,"+   
													" 		SCC_ACORDO_PARC_FATURA_LD ACORDO,"+ 
													" 		SCC_PARCELA PARCELA ,"+
													" 		SCC_ACORDO_PARCELAMENTO AC_PARC"+ 
													" WHERE FATURA.CD_CSP = ACORDO.CD_CSP"+ 
													" 	AND FATURA.NU_NF = ACORDO.NU_NF"+
													" 	AND FATURA.NU_FATURA = ACORDO.NU_FATURA"+
													" 	AND ACORDO.NU_ACORDO_PARCELAMENTO = PARCELA.NU_ACORDO_PARCELAMENTO"+
													" 	AND AC_PARC.NU_ACORDO_PARCELAMENTO = ACORDO.NU_ACORDO_PARCELAMENTO"+ 
													" 	AND ACORDO.NU_CONTA = PARCELA.NU_CONTA"+
													" 	AND ACORDO.NU_CONTA = AC_PARC.NU_CONTA"+ 
													" 	AND ACORDO.CD_CSP = PARCELA.CD_CSP"+  
													"   AND AC_PARC.DT_ACORDO_PARCELAMENTO >= :dtInicial "+
													" 	AND AC_PARC.DT_ACORDO_PARCELAMENTO <= :dtFinal ";  
	
	public static final String FILTRO_STATUS_ACOMPANHAMENTO = "AND	PARCELA.CD_STATUS_PARCELA = :status ";
	
	public static final String FILTRO_EOTCLARO_ACOMPANHAMENTO= "AND	FATURA.CD_EOT_CLARO = :cdEOTClaro ";
	
	public static final String FILTRO_CSP_ACOMPANHAMENTO = "AND	FATURA.CD_CSP = :cdCsp ";
	
	public static final String FILTRO_NUMERO_ACORDO_ACOMPANHAMENTO = "AND	ACORDO.NU_ACORDO_PARCELAMENTO = :numeroAcordo ";
	
	public static final String FILTRO_NUMERO_CONTA_ACOMPANHAMENTO ="AND	ACORDO.NU_CONTA =  :numeroConta";
	
	public static final String SQL_ACOMPANHAMENTO_SINTETICO =   " SELECT  	FATURA.CD_CSP as OPERADORA_LD,  "+     
																"			FATURA.CD_EOT_CLARO as OPERADORA_CLARO,        "+
																"			DECODE(PARCELA.CD_STATUS_PARCELA, 'N', 'Novo', 'B', 'Faturado', 'C', 'Fechado', 'A', 'Cancelado', 'R', 'Quebrado', 'P', 'Pago', PARCELA.CD_STATUS_PARCELA ) as STATUS, "+
																"			COUNT(PARCELA.NU_PARCELA) as QTD_PARCELA,    "+
																"			sum(PARCELA.VL_TOTAL_PARCELA) as VALOR "+				
																" FROM "+
																"			SCC_FATURA_LD FATURA,  "+   
																"			SCC_ACORDO_PARC_FATURA_LD ACORDO, "+ 
																"			SCC_PARCELA PARCELA, "+
																" 			SCC_ACORDO_PARCELAMENTO AC_PARC"+ 
																" WHERE "+
																"			FATURA.CD_CSP = ACORDO.CD_CSP "+ 
																"		AND FATURA.NU_NF = ACORDO.NU_NF "+ 
																"		AND FATURA.NU_FATURA = ACORDO.NU_FATURA "+ 
																"		AND ACORDO.NU_ACORDO_PARCELAMENTO = PARCELA.NU_ACORDO_PARCELAMENTO "+ 
																"		AND ACORDO.NU_CONTA = PARCELA.NU_CONTA "+ 
																"		AND ACORDO.CD_CSP = PARCELA.CD_CSP "+ 
																" 		AND AC_PARC.NU_ACORDO_PARCELAMENTO = ACORDO.NU_ACORDO_PARCELAMENTO"+
																" 		AND ACORDO.NU_CONTA = AC_PARC.NU_CONTA"+ 
																"		AND AC_PARC.DT_ACORDO_PARCELAMENTO >= :dtInicial "+
																"		AND AC_PARC.DT_ACORDO_PARCELAMENTO <= :dtFinal ";    
	
	public static final String FILTRO_STATUS_PARCELA_ACOMPANHAMENTO = "AND	PARCELA.CD_STATUS_PARCELA = :status ";
	
	public static final String PROJECTIONS_ACOMPANHAMENTO ="Group by FATURA.CD_CSP, CD_EOT_CLARO, PARCELA.CD_STATUS_PARCELA ";



												



			

}
