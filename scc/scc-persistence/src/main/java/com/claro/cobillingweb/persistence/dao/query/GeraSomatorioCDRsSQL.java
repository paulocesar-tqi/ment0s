package com.claro.cobillingweb.persistence.dao.query;

public class GeraSomatorioCDRsSQL {

	public final static String SQL = "SELECT COUNT(1) AS QUANTIDADE, "+ 
                 "NVL(SUM(A.HR_DURACAO_REAL),0) AS DURACAO_REAL, "+
                 "NVL(SUM(A.MI_DURACAO_TARIFADA),0) AS DURACAO_TARIFADA, "+
                 "NVL(SUM(A.VL_BRUTO_CHAMADA),0) AS VL_BRUTO  "+
                 "FROM SCC_CDR_COBL A, SCC_ARQUIVO_COBILLING F "+ 
                 "WHERE A.SQ_ARQUIVO_REMESSA = F.SQ_ARQUIVO  "+
                 "AND F.CD_TIPO_ARQUIVO IN (200,205)" ;
	
	public static final String FILTRO_DT_CHAMADA = " AND A.DT_CHAMADA BETWEEN :dtInicial AND :dtFinal";
	public static final String FILTRO_DT_APURACAO = " AND A.DT_PROC_EXTERNA BETWEEN :dtInicial AND :dtFinal";
	public static final String FILTRO_DT_FECHAMENTO =" AND A.DT_REPASSE BETWEEN :dtInicial AND :dtFinal";
	
}
