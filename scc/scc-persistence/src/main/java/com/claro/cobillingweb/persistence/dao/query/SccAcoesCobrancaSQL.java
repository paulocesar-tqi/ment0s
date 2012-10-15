package com.claro.cobillingweb.persistence.dao.query;

public class SccAcoesCobrancaSQL {
	
	public static final String SQL = 	"SELECT "+
									 	"			FC.NU_CONTA, "+
									 	"			FC.NU_TELEFONE, "+
										"			FC.NU_FATURA, "+
										"			F.VL_FATURA, "+
										"			F.DT_EMISSAO_NF, "+
										"			F.DT_VENCIMENTO_FATURA, "+
										"			FC.DT_CARTA_COBRANCA, "+
										"			A1.NO_ARQUIVO, "+
										"			A1.DT_CONNECT, "+
										"			FC.DT_BLOQUEIO_OPERADORA_LD, "+
										"			A2.NO_ARQUIVO, "+
										"			A2.DT_CONNECT, "+
										"			FC.DT_PAGAMENTO, "+
										"			A3.NO_ARQUIVO, "+
										"			A3.DT_CONNECT "+
							
										" FROM 	SCC_FRAUDE_COBRANCA FC, "+	
										" 		SCC_FATURA_LD F,  "+	
										" 		SCC_ARQUIVO_COBILLING A1, "+ 
										" 		SCC_ARQUIVO_COBILLING A2, "+
										" 		SCC_ARQUIVO_COBILLING A3 "+ 	
										" WHERE   FC.NU_FATURA = F.NU_FATURA "+
										" 	  AND A1.SQ_ARQUIVO = FC.SQ_ARQUIVO "+  
										" 	  AND FC.SQ_ARQUIVO_BLOQUEIO = A2.SQ_ARQUIVO(+) "+
										"     AND FC.SQ_ARQUIVO_PAGAMENTO = A3.SQ_ARQUIVO(+) ";	
	
	public static final String FILTRO_DT_INICIO = " AND FC.DT_CARTA_COBRANCA >= :dtInicio ";
	
	public static final String FILTRO_DT_FINAL  = " AND FC.DT_CARTA_COBRANCA <= :dtFim ";
	
	public static final String FILTRO_CSP = "AND FATURA.CD_CSP = :cdCsp ";
			

}
