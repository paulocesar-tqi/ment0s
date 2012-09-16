package com.claro.cobillingweb.persistence.dao.query;

public class RelPrestacaoServicoSQL {
	
	public static final String SQL = "SELECT O2.DS_OPERADORA AS OPERADORA_CLARO, " +
											 "SUM(CASE WHEN O.CD_EOT = '001' THEN R.VL_BRUTO_ITEM_REPASSE "+
									 		 "ELSE  0         END) AS EMBRATEL, "+
									 		 "SUM(CASE WHEN O.CD_EOT = '201' THEN R.VL_BRUTO_ITEM_REPASSE "+
									 		 "ELSE  0         END) AS INTELIG, "+
									 		 "SUM(CASE WHEN O.CD_EOT = '061' THEN R.VL_BRUTO_ITEM_REPASSE "+
									 		 "ELSE  0         END) AS BRASIL_TELECOM,"+
									 		 "SUM(CASE WHEN O.CD_EOT = '211' THEN R.VL_BRUTO_ITEM_REPASSE "+
									 		 "ELSE  0         END) AS TELEFONICA, "+
									 		 "SUM(CASE WHEN O.CD_EOT = '301' THEN R.VL_BRUTO_ITEM_REPASSE "+
									 		 "ELSE  0         END) AS TNL, "+
									         "SUM(CASE WHEN O.CD_EOT = '141' THEN R.VL_BRUTO_ITEM_REPASSE "+
											 "ELSE  0         END) AS GVT, "+
					                         "SUM(CASE WHEN O.CD_EOT = '341' THEN R.VL_BRUTO_ITEM_REPASSE "+
											 "ELSE  0         END) AS SERCOMTEL, "+
									         "SUM(CASE WHEN O.CD_EOT = '401' THEN R.VL_BRUTO_ITEM_REPASSE "+
											 "ELSE  0         END) AS TIM, "+
					                         "SUM(CASE WHEN O.CD_EOT = '012' THEN R.VL_BRUTO_ITEM_REPASSE "+
											 "ELSE  0         END) AS CTBC, "+
									         "SUM(CASE WHEN O.CD_EOT = '231' THEN R.VL_BRUTO_ITEM_REPASSE "+
											 "ELSE  0         END) AS TELEMAR, "+
											 "SUM(CASE WHEN O.CD_EOT = '911' THEN R.VL_BRUTO_ITEM_REPASSE "+
											 "ELSE  0         END) AS IPCORP, "+
					                         "SUM(CASE WHEN O.CD_EOT = '777' THEN R.VL_BRUTO_ITEM_REPASSE "+
											 "ELSE  0         END) AS NEXUS "+ 
							         "FROM SCC_REPASSE R, SCC_OPERADORA O, SCC_OPERADORA O2, "+
									 	  "SCC_COMPOSICAO_PRODUTO CP, SCC_PRODUTO_COBILLING P "+
							         "WHERE R.CD_COMPONENTE_PRODUTO = CP.CD_COMPONENTE_PRODUTO "+      
							         	 "AND CP.CD_PRODUTO_COBILLING  = P.CD_PRODUTO_COBILLING "+      
							         	 "AND R.CD_EOT_LD = O.CD_EOT "+
							         	 "AND R.CD_EOT_CLARO = O2.CD_EOT "+
							         	 "AND NVL(R.CD_STATUS_REPASSE, ' ') <> 'N' "+      
							         	 "AND R.CD_ITEM_REPASSE = 22 ";
	
	public static final String CD_EOT_LD = "AND R.CD_EOT_LD = :cdEOTLd " ;
	
	public static final String CD_EOT_CLARO = "AND R.CD_EOT_CLARO = :cdEOTClaro ";
	
	public static final String DT_INICIAL_REPASSE = "AND R.DT_INICIAL_REPASSE = :dataInicial ";
	
	public static final String DT_FIM_REPASSE ="AND R.DT_FIM_REPASSE = :dataFinal ";
	
	public static final String CD_PRODUTO_COBILLING_NOT_IN = "AND ((CP.CD_PRODUTO_COBILLING = :cdProdutoCobilling) AND (CP.CD_COMPONENTE_PRODUTO NOT IN (7,3,21,10,5,24,13))) ";
	
	public static final String CD_PRODUTO_COBILLING_IN = "AND ((CP.CD_PRODUTO_COBILLING = :cdProdutoCobilling) OR (CP.CD_COMPONENTE_PRODUTO IN (7,3,21,10,5,24,13))) ";
	
	public static final String PROJECTIONS = "GROUP BY O2.DS_OPERADORA";
	
	public static final String DT_FECHAMENTO = "AND To_char(PF.dt_fechamento, 'MMYYYY') = :dataFechamento ";
	
	
	public static final String SQL_PRE = "SELECT O2.ds_operadora AS OPERADORA_CLARO, " +
												"SUM(CASE WHEN O.cd_eot = '001' THEN PF.vl_serv_prest_bruto "+
												"ELSE 0          END)        AS EMBRATEL, " +
												"SUM(CASE WHEN O.CD_EOT = '201' THEN PF.vl_serv_prest_bruto "+
												"ELSE 0          END) AS INTELIG "+
												"SUM(CASE WHEN O.cd_eot = '061' THEN PF.vl_serv_prest_bruto "+ 
												"ELSE 0          END)        AS BRASIL_TELECOM, "+
											    "SUM(CASE WHEN O.cd_eot = '211' THEN PF.vl_serv_prest_bruto "+ 
											    "ELSE 0          END)        AS TELEFONICA, "+
											    "SUM(CASE WHEN O.cd_eot = '301' THEN PF.vl_serv_prest_bruto "+
											    "ELSE 0          END)        AS TNL, "+
											    "SUM(CASE WHEN O.cd_eot = '141' THEN PF.vl_serv_prest_bruto "+ 
											    "ELSE 0          END)        AS GVT, "+
											    "SUM(CASE WHEN O.cd_eot = '341' THEN PF.vl_serv_prest_bruto "+ 
											    "ELSE 0          END)        AS SERCOMTEL, "+
											    "SUM(CASE WHEN O.cd_eot = '401' THEN PF.vl_serv_prest_bruto "+ 
											    "ELSE 0          END)        AS TIM, "+
											    "SUM(CASE WHEN O.cd_eot = '012' THEN PF.vl_serv_prest_bruto "+ 
											    "ELSE 0          END)        AS CTBC, "+
											    "SUM(CASE WHEN O.cd_eot = '231' THEN PF.vl_serv_prest_bruto "+ 
											    "ELSE 0          END)        AS TELEMAR, "+
											    "SUM(CASE WHEN O.cd_eot = '911' THEN PF.vl_serv_prest_bruto "+ 
											    "ELSE 0          END)        AS IPCORP, "+
											    "SUM(CASE WHEN O.cd_eot = '777' THEN PF.vl_serv_prest_bruto "+ 
											    "ELSE 0          END)        AS NEXUS  "+
											"FROM   scc_pre_fechamento PF,  "+
											    "scc_operadora O,  "+
											    "scc_operadora O2  "+
											"WHERE  O.cd_tipo_servico = 'C' "+ 
											    "AND PF.cd_eot_ld = O.cd_eot "+ 
											    "AND PF.cd_eot_claro = O2.cd_eot "+
											    "AND Nvl(PF.cd_status_fechamento, ' ') <> 'N' ";
    
     


}
