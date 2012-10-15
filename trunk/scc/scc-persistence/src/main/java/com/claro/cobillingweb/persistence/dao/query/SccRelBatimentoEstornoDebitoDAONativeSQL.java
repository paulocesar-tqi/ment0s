package com.claro.cobillingweb.persistence.dao.query;

public class SccRelBatimentoEstornoDebitoDAONativeSQL {
	
	public static final String SQL = "Select " +
			   "ARQ.sq_arquivo, "+ 
		       "ARQ.cd_eot_ld, "+
		       "ARQ.cd_eot_claro, "+ 
		       "OP.no_operadora_ld, "+ 
		       "OP_CLARO.ds_operadora, "+ 
		       "OP.sg_uf, "+
		       "ARQ.no_arquivo, "+ 
		       "ST_ARQ.cd_status_arquivo, "+ 
		       "ST_ARQ.ds_status_arquivo, "+
		       "ARQ.mm_ciclo, "+
		       "ARQ.aa_ciclo, "+
		       "ARQ.vl_total_nf, "+
		       "ARQ.vl_total_impugnado, "+ 
		       "ARQ.dt_connect "+ 
		       "FROM scc_arquivo_cobilling ARQ, "+ 
		       "scc_est_deb_operadora_ld OP, "+
		       "scc_operadora OP_CLARO, "+
		       "scc_status_arquivo ST_ARQ "+
		       "WHERE ARQ.cd_tipo_arquivo = 120 "+ 
		       "AND ARQ.sq_arquivo = OP.sq_arquivo_est_deb "+ 
		       "AND ARQ.vl_total_nf <> 0 "+
		       "AND ARQ.cd_eot_claro = OP_CLARO.cd_eot "+ 
		       "AND ARQ.cd_status_arquivo = ST_ARQ.cd_status_arquivo ";
	
	public static final String FILTRO_CDEOTLD = "AND ARQ.CD_EOT_LD = :cdEOTLD ";
	
	public static final String FILTRO_CDEOTCLARO = "AND ARQ.CD_EOT_CLARO = :cdEOTClaro ";
				
	public static final String FILTRO_STATUS_CDR =" AND ARQ.CD_STATUS_ARQUIVO = :cdStatusArquivo ";
	
	public static final String FILTRO_MES_CICLO =" AND ARQ.MM_CICLO = :mmCiclo ";	
	
	public static final String FILTRO_ANO_CICLO =" AND ARQ.AA_CICLO = :aaCiclo ";	
		       

}
