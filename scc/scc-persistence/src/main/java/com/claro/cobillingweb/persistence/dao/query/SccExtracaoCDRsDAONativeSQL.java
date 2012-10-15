/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92031709
 *
 */
public class SccExtracaoCDRsDAONativeSQL {
	
	public static final String SQL = "SELECT /*+PARALLEL(CDR,12)*/" +
											 " CDR.NU_MSISDN_ORIGEM AS numeroDeA, "+
											 " CDR.NU_TELEFONE_DESTINO AS numeroDeB, "+
											 " CDR.MI_DURACAO_TARIFADA AS duracaoTarifada, "+
											 " CDR.DT_CHAMADA AS dtChamada, "+
											 " NVL((SELECT STATUS.DS_STATUS_CDR "+
											 "  FROM SCC_STATUS_CDR STATUS "+
											 "  WHERE STATUS.CD_STATUS_CDR = CDR.CD_STATUS_CDR),' ') AS statusChamada, "+
											 " NVL((SELECT REJ.DS_MOTIVO_REJEICAO "+
											 "  FROM SCC_MOTIVO_REJEICAO REJ "+
											 "  WHERE REJ.CD_MOTIVO_REJEICAO = CDR.CD_MOTIVO_REJEICAO),' ') AS motivoRejeicao "+											 											 
									 " FROM "+
											 " SCC_ARQUIVO_COBILLING ARQ, SCC_CDR_COBILLING CDR "+
									 " WHERE  CDR.SQ_ARQUIVO_RETORNO = ARQ.SQ_ARQUIVO "+
									 "   AND  CDR.DT_CHAMADA >= :dtChamadaInicial "+
									   " AND  CDR.DT_CHAMADA <= :dtChamadaFinal "+
									   " AND  ARQ.CD_TIPO_ARQUIVO IN (70, 95) ";
									 	
	public static final String FILTRO_MSISDN_ORIGEM = " AND  CDR.NU_MSISDN_ORIGEM = :nuMsisdnOrigem ";
	
	public static final String SQL_GROUP = " ORDER BY CDR.DT_CHAMADA ";
						
}
