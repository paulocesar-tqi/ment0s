/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92031709
 *
 */
public class SccAssexuadosDAONativeSQL {
	
	public static final String SQL = "SELECT " +
											 " RI.DT_RELATORIO AS dtRelatorio, "+
											 " RI.NU_MSISDN_ORIGEM AS numeroDeA, "+
											 " RI.QT_REGISTROS AS qtChamadas, "+
											 " RI.QT_DURACAO_TARIFADA AS qtMinutoTarifados, "+
											 " RI.VL_LIQUIDO AS vlLiquido, "+
											 " RI.CD_ERRO_RECICLAGEM AS cdErroReciclagem, "+
											 " RI.NU_ITEM AS nuItem, "+
											 " RI.SQ_RELATORIO_SUMARIZADO AS sqRelatorioSumarizado "+											 
									 " FROM "+
											 " SCC_RELATORIO_SUMARIZADO RS, SCC_RELATORIO_ITEM RI "+
									 " WHERE  RI.DT_RELATORIO = RS.DT_RELATORIO "+
									   " AND  RI.SQ_RELATORIO_SUMARIZADO = RS.SQ_RELATORIO_SUMARIZADO "+
									   " AND  RS.DT_RELATORIO >= :dtInicial "+
									   " AND  RS.DT_RELATORIO <= :dtFinal "+
									   " AND  RS.SQ_RELATORIO = 2";									 
									 	
	public static final String FILTRO_NO_ARQUIVO_GERADO = " AND RS.NO_ARQUIVO_GERADO = :noArquivoGerado ";
	
	public static final String SQL_GROUP = " ORDER BY RI.DT_RELATORIO, RI.VL_LIQUIDO DESC";
						
}
