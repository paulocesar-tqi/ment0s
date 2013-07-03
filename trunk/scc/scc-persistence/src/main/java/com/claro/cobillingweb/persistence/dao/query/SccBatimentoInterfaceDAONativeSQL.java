/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author Paulo César - Sysmap
 *
 */
public class SccBatimentoInterfaceDAONativeSQL {
	
	public static final String SQL = "Select " +
											" MOBILE.FILE_NAME as nomeArquivo,"+
											"  MOBILE.LD_ID as operadoraLD,"+
											"  MOBILE.MKT_EOT as operadoraClaro,"+
											"  MOBILE.FILE_CREATE_DATE as dataMovimentacao,"+
											"  MOBILE.FILE_TRANSFER_DATE as dataTransferencia,"+
											"  MOBILE.REG_FILE_QUANTITY as quantidadeRegistrosMobile,"+
											"  SCC.DT_PROC_CLARO as dataProcessamento,"+
											"  SCC.QT_REGISTROS as quantidadeRegistrosScc "+
									 " FROM " +
									 		" SCC_ARQUIVO_COBILLING SCC,"+
											" BL8_FTP_SCC_FILE MOBILE"+
									" WHERE "+
											" MOBILE.MKT_EOT           = SCC.CD_EOT_CLARO(+)";										
										
	
	public static final String FILTRO_SEM_SUBSTR = "AND MOBILE.FILE_NAME       = SCC.NO_ARQUIVO_IFC(+) ";
	
	public static final String FILTRO_COM_SUBSTR = "AND SUBSTR(MOBILE.FILE_NAME,1,33)    = SCC.NO_ARQUIVO_IFC(+) ";
	
	public static final String FILTRO_DT_INICIO = "AND TRUNC(MOBILE.FILE_CREATE_DATE) >= :dtInicio ";
	
	public static final String FILTRO_DT_FIM = "AND TRUNC(MOBILE.FILE_CREATE_DATE) <= :dtFim ";
	
	public static final String FILTRO_CDEOTLD = "AND MOBILE.LD_ID = :cdEOTLD ";
	
	public static final String FILTRO_CDEOTCLARO = "AND MOBILE.MKT_EOT = :cdEOTClaro ";
	
	public static final String FILTRO_TIPO_ARQUIVO =" AND UPPER(MOBILE.FILE_NAME) LIKE UPPER(:tpArquivo) ";
	
	
											
											
}
