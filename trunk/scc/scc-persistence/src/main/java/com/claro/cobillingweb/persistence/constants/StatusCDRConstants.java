package com.claro.cobillingweb.persistence.constants;

import java.util.Arrays;
import java.util.Vector;

public class StatusCDRConstants {
	
	public static final Long CDRSTATUS_INDEFINIDO       	= 0L;
    public static final Long CDRSTATUS_ENCAMINHADO_ESB   = 5L;
    public static final Long CDRSTATUS_ENCAMINHADO_MOB   = 6L;
    public static final Long CDRSTATUS_REJEITADO_C1   	= 10L;
    public static final Long CDRSTATUS_REJEITADO_C2_ESB  = 11L;
    public static final Long CDRSTATUS_REJEITADO_C2_MOB  = 12L;
    public static final Long CDRSTATUS_ALOCADO_ESB  		= 15L;
    public static final Long CDRSTATUS_ALOCADO_MOB  		= 16L;
    public static final Long CDRSTATUS_FATURADO_ESB      = 20L;
    public static final Long CDRSTATUS_FATURADO_MOB      = 21L;
    public static final Long CDRSTATUS_EM_PARCELAMENTO   = 22L;
    public static final Long CDRSTATUS_RETARIF           = 23L;
    public static final Long CDRSTATUS_CONTESTADO_ESB    = 25L;
    public static final Long CDRSTATUS_CONTESTADO_MOB    = 26L;
    public static final Long CDRSTATUS_REVERSAO        	= 27L;
    public static final Long CDRSTATUS_CONTESTADO_MOB_ARRECADADA = 28L;
    public static final Long CDRSTATUS_CONTESTADO_MOB_REPASSADA  = 29L;
    public static final Long CDRSTATUS_A_RECICLAR_ESB    = 30L;
    public static final Long CDRSTATUS_A_RECICLAR_MOB    = 31L;
    public static final Long CDRSTATUS_PERDIDO_PPC       = 40L;
    public static final Long CDRSTATUS_PERDIDO_ESB       = 41L;
    public static final Long CDRSTATUS_PERDIDO_MOB       = 42L;
    public static final Long CDRSTATUS_EXCLUIDO_X1       = 50L;
    public static final Long CDRSTATUS_EXCLUIDO_X2_ESB   = 51L;
    public static final Long CDRSTATUS_EXCLUIDO_X2_MOB   = 52L;
    public static final Long CDRSTATUS_EXCLUIDO_MOB_CONTESTADO = 53L;
    public static final Long CDRSTATUS_A_RECUPERAR_ESB   = 61L;
    public static final Long CDRSTATUS_A_RECUPERAR_MOB   = 62L;
    public static final Long CDRSTATUS_ARRECADADA      = 70L;
    public static final Long CDRSTATUS_EXPURGADA       = 71L;
    public static final Long CDRSTATUS_REVERSAO_PGTO   = 72L;
    public static final Long CDRSTATUS_INADIMPLENTE    = 73L;
    public static final Long CDRSTATUS_REPASSADA       = 80L;
    public static final Long CDRSTATUS_PARCELADA       = 85L;
    public static final Long CDRSTATUS_ALTERACAO_VCTO  = 90L;
    
    public static final int CDRSTATUS_REJEITADO_C2_ESB_2 = 11;
    public static final Long CDRSTATUS_REJEITADO       	= -10L;
    public static final int CDRSTATUS_REJEITADO_C2   	= -11;
    public static final int CDRSTATUS_EXCLUIDO         	= -50;
    public static final int CDRSTATUS_REJEITADO_C1_2   	= 10;
    public static final int CDRSTATUS_EXCLUIDO_X1_2       = 50;
    public static final int CDRSTATUS_EXCLUIDO_X2   	= -51;
    public static final int CDRSTATUS_PERDIDO         	= -40;
    public static final int CDRSTATUS_FATURADO        	= -20;
    public static final int CDRSTATUS_CONTESTADO      	= -25;
    public static final int CDRSTATUS_FATURADO_FINAL    = -21;
	public static final int CDRSTATUS_FATURADO_INICIAL  = -22;
	public static final int CDRSTATUS_REJEITADO_2       = -10;
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vFinancial = new Vector(Arrays.asList( new String[][] { 
       		{""+CDRSTATUS_REJEITADO_2, "Rejeitado C1-C2"},
       		{""+CDRSTATUS_REJEITADO_C1, "Rejeitado C1"},
       		{""+CDRSTATUS_REJEITADO_C2, "Rejeitado C2"},
       		{""+CDRSTATUS_EXCLUIDO, "Excluido X1-X2"},
       		{""+CDRSTATUS_EXCLUIDO_X1, "Excluido X1"},
       		{""+CDRSTATUS_EXCLUIDO_X2, "Excluido X2"},
       		{""+CDRSTATUS_PERDIDO, "Perda P1-P7"},
       		{""+CDRSTATUS_CONTESTADO, "Contestado"},
       		{""+CDRSTATUS_FATURADO, "Faturado"},
       		{""+CDRSTATUS_FATURADO_INICIAL, "Faturado Inicial"},
       		{""+CDRSTATUS_FATURADO_FINAL, "Faturado Final"}
    } ) );

    public static final String[] buscaStatusFinancial(String status) {
	    String[] tSTATUS = null;
	    String[] tTemp = null;
	    for(int i=0;i<StatusCDRConstants.vFinancial.size();i++) {
	    	tTemp = (String[]) StatusCDRConstants.vFinancial.elementAt(i);
	    	if (status.equalsIgnoreCase(tTemp[0])) {
	    		tSTATUS = tTemp;
	    		break;
	    	}
	    }
	    if (tSTATUS == null) {
	    	status = (status!=null&&status.length()>0?((Integer.parseInt(status)<0?"-"+status:status)):"");
		    for(int i=0;i<StatusCDRConstants.vFinancial.size();i++) {
		    	tTemp = (String[]) StatusCDRConstants.vFinancial.elementAt(i);
		    	if (status.equalsIgnoreCase(tTemp[0])) {
		    		tSTATUS = tTemp;
		    		break;
		    	}
		    }
	    }
	    return tSTATUS;
    }
    
    
    
	
}
