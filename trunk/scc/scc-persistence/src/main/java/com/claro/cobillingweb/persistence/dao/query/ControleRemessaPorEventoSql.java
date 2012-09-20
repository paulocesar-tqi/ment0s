package com.claro.cobillingweb.persistence.dao.query;

public class ControleRemessaPorEventoSql {
	
	
	public static final String SQL = "SELECT f.DT_PROC_CLARO,  f.CD_EOT_CLARO,f.CD_EOT_LD, " +
											"o.DS_OPERADORA, OP.DS_OPERADORA AS DS_OPERADORA_LD, fs.CD_STATUS_CDR, " +
									        "p.NO_PRODUTO_COBILLING, SC.DS_STATUS_CDR, fs.CD_CICLO ,fs.MM_CICLO, "+
									        "fs.AA_CICLO, sum(fs.QT_CDRS), "+
									        "sum(fs.VL_LIQUIDO_CHAMADA), sum(fs.VL_BRUTO_CHAMADA), "+
									        "sum(fs.QT_DURACAO_REAL),sum(fs.QT_DURACAO_TARIFADA) "+
									        "from scc_arquivo_sumarizado fs ,scc_arquivo_cobilling f ,  scc_operadora o, "+
									             "scc_composicao_produto cp ,scc_produto_cobilling p , " +
									             "SCC_STATUS_CDR SC, (SELECT CD_EOT, DS_OPERADORA FROM scc_operadora ) OP "+
									        "where fs.CD_COMPONENTE_PRODUTO = cp.CD_COMPONENTE_PRODUTO "+ 
									          "AND cp.CD_PRODUTO_COBILLING = p.CD_PRODUTO_COBILLING "+
									          "AND f.SQ_ARQUIVO = fs.SQ_ARQUIVO_ORIGINAL "+
									          "AND SC.CD_STATUS_CDR = fs.CD_STATUS_CDR "+
									          "AND fs.Cd_Eot_Claro = o.CD_EOT "+
									          "AND fs.Cd_Eot_Ld = OP.CD_EOT "+			
									          "AND f.CD_TIPO_ARQUIVO in (5,555) "+ 
									          "AND f.SQ_ARQUIVO_ORIGEM = 0 "+ 
									          "AND fs.QT_CDRS <>0 ";
	
	public static final String SQL_DT_REF = "SELECT f.DT_PROC_EXTERNA,  f.CD_EOT_CLARO,f.CD_EOT_LD, " +
													"o.DS_OPERADORA, OP.DS_OPERADORA AS DS_OPERADORA_LD, fs.CD_STATUS_CDR, " +
													"p.NO_PRODUTO_COBILLING, SC.DS_STATUS_CDR, fs.CD_CICLO ,fs.MM_CICLO, "+
													"fs.AA_CICLO, sum(fs.QT_CDRS), "+
													"sum(fs.VL_LIQUIDO_CHAMADA), sum(fs.VL_BRUTO_CHAMADA), "+
													"sum(fs.QT_DURACAO_REAL),sum(fs.QT_DURACAO_TARIFADA) "+
											"from scc_arquivo_sumarizado fs ,scc_arquivo_cobilling f ,  scc_operadora o, "+
												 "scc_composicao_produto cp ,scc_produto_cobilling p , " +
												 "SCC_STATUS_CDR SC, (SELECT CD_EOT, DS_OPERADORA FROM scc_operadora ) OP "+
											"where fs.CD_COMPONENTE_PRODUTO = cp.CD_COMPONENTE_PRODUTO "+ 
												 "AND cp.CD_PRODUTO_COBILLING = p.CD_PRODUTO_COBILLING "+
												 "AND f.SQ_ARQUIVO = fs.SQ_ARQUIVO_ORIGINAL "+
												 "AND SC.CD_STATUS_CDR = fs.CD_STATUS_CDR "+
												 "AND fs.Cd_Eot_Claro = o.CD_EOT "+
												 "AND fs.Cd_Eot_Ld = OP.CD_EOT "+			
												 "AND f.CD_TIPO_ARQUIVO in (5,555) "+ 
												 "AND f.SQ_ARQUIVO_ORIGEM = 0 "+ 
												 "AND fs.QT_CDRS <>0 ";
	
	public static final String PROJECTION = "GROUP BY  f.DT_PROC_CLARO,f.CD_EOT_CLARO,f.CD_EOT_LD, o.DS_OPERADORA, OP.DS_OPERADORA, "+
													  "fs.CD_STATUS_CDR, fs.CD_SUB_STATUS_CDR, fs.CD_MOTIVO_REJEICAO, "+
													  "p.NO_PRODUTO_COBILLING,  SC.DS_STATUS_CDR,  "+
													  "fs.CD_CICLO,fs.MM_CICLO,fs.AA_CICLO, fs.CD_ERRO_RECICLAGEM "+
										    "ORDER BY f.DT_PROC_CLARO,f.CD_EOT_CLARO,f.CD_EOT_LD, "+
												     "fs.CD_STATUS_CDR, "+   
												     "fs.CD_CICLO ,fs.MM_CICLO,fs.AA_CICLO,p.NO_PRODUTO_COBILLING";
	
	
	public static final String PROJECTION_DT_REF = "GROUP BY  f.DT_PROC_EXTERNA,f.CD_EOT_CLARO,f.CD_EOT_LD, o.DS_OPERADORA, OP.DS_OPERADORA, "+
															 "fs.CD_STATUS_CDR, fs.CD_SUB_STATUS_CDR, fs.CD_MOTIVO_REJEICAO, "+
															 "p.NO_PRODUTO_COBILLING,  SC.DS_STATUS_CDR,  "+
															 "fs.CD_CICLO,fs.MM_CICLO,fs.AA_CICLO, fs.CD_ERRO_RECICLAGEM "+
										           "ORDER BY f.DT_PROC_EXTERNA,f.CD_EOT_CLARO,f.CD_EOT_LD, "+
										     		        "fs.CD_STATUS_CDR, "+   
										     		        "fs.CD_CICLO ,fs.MM_CICLO,fs.AA_CICLO,p.NO_PRODUTO_COBILLING";

	
	public static final String FILTRO_EOT_CLARO = "AND fs.CD_EOT_CLARO = :cdEOTClaro";
	
	public static final String FILTRO_EOT_LD = "AND fs.CD_EOT_LD = :cdEOTLD";
	
	public static final String FILTRO_PRODUTO = "AND P.CD_PRODUTO_COBILLING = :cdProdutoCobilling";
	
	public static final String FILTRO_EOT_CLARO_HOLDING = "AND S.CD_EOT_CLARO IN (SELECT CD_EOT FROM SCC_OPERADORA WHERE CD_EOT_HOLDING = :cdEOTClaro)";
	
	public static final String FILTRO_DT_PROC_CLARO = "AND f.DT_PROC_CLARO >= :dataInicial ";
	
	public static final String FILTRO_DT_PROC_CLARO_FIM = "AND f.DT_PROC_CLARO <= :dataFinal ";
	
	public static final String FILTRO_DT_PROC_EXTERNA = "AND f.DT_PROC_EXTERNA >= :dataInicial ";
	
	public static final String FILTRO_DT_PROC_EXTERNA_FIM = "AND f.DT_PROC_EXTERNA <= :dataFinal "; 
	
	public static final String FILTRO_STATUS_CDR = "AND fs.CD_STATUS_CDR IN (:statusCdr) ";
	
}
