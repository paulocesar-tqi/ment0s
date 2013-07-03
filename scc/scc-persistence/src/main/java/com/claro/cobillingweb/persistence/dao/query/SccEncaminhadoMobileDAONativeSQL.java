/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92031709
 *
 */
public class SccEncaminhadoMobileDAONativeSQL {
	
	public static final String SQL = "SELECT " +
											 " RI.DT_RELATORIO AS dtRelatorio, "+
											 " RI.NO_ARQUIVO_REFERENCIA   AS noArquivoReferencia, "+
											 " RI.QT_REGISTROS AS qtChamadas, "+
											 " RI.QT_DURACAO_TARIFADA AS qtMinutoTarifados, "+
											 " RI.VL_LIQUIDO AS vlLiquido, "+
											 " RI.NU_ITEM AS nuItem, "+
											 " RI.SQ_RELATORIO_SUMARIZADO AS sqRelatorioSumarizado, "+
											 " RI.DT_PROC_CLARO AS dtProcClaro "+
									 " FROM "+
											 " SCC_RELATORIO_SUMARIZADO RS, SCC_RELATORIO_ITEM RI "+
									 " WHERE  RI.DT_RELATORIO = RS.DT_RELATORIO "+
									   " AND  RI.SQ_RELATORIO_SUMARIZADO = RS.SQ_RELATORIO_SUMARIZADO "+
									   " AND  RS.DT_RELATORIO >= :dtInicial "+
									   " AND  RS.DT_RELATORIO <= :dtFinal "+
									   " AND  RS.SQ_RELATORIO = 3";									 
									 	
	public static final String FILTRO_NO_ARQUIVO_GERADO = " AND RI.DT_RELATORIO = :noArquivoGerado ";
	
	public static final String SQL_GROUP = " ORDER BY RI.DT_RELATORIO ";
						
}
