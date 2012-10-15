package com.claro.cobillingweb.persistence.constants;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;


public class CobillingConstants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4957996162041579010L;
	
	
	
	/*
     * INFO ResourceBundle names
     */
	
	/**
     * Resource Bundle for SIM
     */
    public static final Boolean LIMITADO = Boolean.TRUE;
    /**
     * Resource Bundle for NAO
     */
    public static final Boolean ILIMITADO = Boolean.FALSE;
	/**
     * Resource Bundle for SIM
     */
    public static final String SIM = "SIM";
    /**
     * Resource Bundle for NAO
     */
    public static final String NAO = "NAO";
	/**
     * Resource Bundle for S - SIM
     */
    public static final String S = "S";
    /**
     * Resource Bundle for N - NAO
     */
    public static final String N = "N";
	/**
     * Resource Bundle for Fase
     */
    public static final String FASE = "fase";
	/**
     * Resource Bundle for SCP
     */
    public static final String SCP = "SCP";
    /**
     * Resource Bundle for SDP
     */
    public static final String SDP = "SDP";
    
    /**
     * Constant for Faixas Indicadores
     */
    public static final String MAIOR_QUE_45_DIAS = "> 45 DIAS";
    public static final String MAIOR_QUE_60_DIAS = "> 60 DIAS";
    public static final String MAIOR_QUE_80_DIAS = "> 80 DIAS";
    
	/**
     * Resource Bundle for FTP
     */
    public static final String FTP = "ftp.config";
    public static final String FTP_BALANCE = "ftp.configbalance";
    public static final String FTP_PC = "ftp.configpc";
    public static final String FTP_BALANCEPC = "ftp.configbalancepc";
	/**
     * Resource Bundle for S - Sintetico
     */
    public static final String SINTETICO = "S";
	/**
     * Resource Bundle for S - Sintetico
     */
    public static final String PRESTACAOSERVICO = "S";
    /**
     * Resource Bundle for incomplete date message
     */
    public static final String BUNDLE_DATE_INCOMPLETE = "date.incomplete";
    /**
     * Resource Bundle for invalid day message
     */
    public static final String BUNDLE_DATE_INVALID_DAY = "date.invalid.day";
    /**
     * Resource Bundle for invalid month message
     */
    public static final String BUNDLE_DATE_INVALID_MONTH = "date.invalid.month";
    /**
     * Resource Bundle for invalid year message
     */
    public static final String BUNDLE_DATE_INVALID_YEAR = "date.invalid.year";
    /**
     * Resource Bundle for no field inserted
     */
    public static final String BUNDLE_NO_FIELD = "nofield.error";

    /**
     * Resource Bundle for error parameter null
     */
    public static final String BUNDLE_ERROR_PARAMETER_NULL = "parameter.null.error";
    /**
     * Repasse Status
     */
    public static final String BUNDLE_FORWARDSTATUS_NOTCONFIRMED = "forwardstatus.notconfirmed";
    public static final String BUNDLE_FORWARDSTATUS_CONFIRMED = "forwardstatus.confirmed";
    public static final String BUNDLE_FORWARDSTATUS_CANCELED = "forwardstatus.cancelled";
    public static final String BUNDLE_FORWARDSTATUS_ALL = "forwardstatus.all";
     /**
     * Repasse TZLD
     */
    public static final String BUNDLE_FORWARDSTATUS_TZLD = "forwardstatus.tzld"; 
    
    /**
     * Classificacao Questionamento 
     */
    public static final String LIST_ASSINANTE_CODE = "A";
    public static final String LIST_REGRA_CODE = "R";
    public static final String LIST_PARAMETRO_CODE = "P";
    public static final String LIST_LOGICA_CODE = "L";

    public static final String LIST_ASSINANTE = " IN ('104','105','109','143') ";
    public static final String LIST_REGRA = " IN ('121','124','125','126','127','128','129','130','132','133','134','135','136','137','138','140','141','150') ";
    public static final String LIST_PARAMETRO = " IN ('100','101','102','103','116','117','119','120','122','131','144','146') ";
    public static final String LIST_LOGICA = " IN ('145') ";
    
    /**
     * Forma Pagamento Constants
     */
    public static final int FP_TODAS				 = 1;
    public static final int FP_CRITERIO_CASH	 	 = 0;
    public static final int FP_CRITERIO_ENC_CONTAS 	 = 4;
    public static final int FP_CRITERIO_8614		 = 3;
    public static final int FP_CRITERIO_DEP_BANCARIO = 2;
    
    /**
     * Tipo Contrato
     */
    public static final String CD_TIPO_CONTRATO_F = "F";
    public static final String CD_TIPO_CONTRATO_A = "A";
    public static final String CD_TIPO_CONTRATO_P = "P";
    public static final String CD_TIPO_CONTRATO_PREPAGO = "P";
    public static final String CD_TIPO_CONTRATO_CONTINGENCIA = "C";
     public static final String CD_TIPO_CONTRATO_TZLD = "Z";
    
     
     /**
      * OS 60924 - Eduardo Chagas
      * Constant for Relatorio de Pagamento - opcao status pagamento
      */
     public static final String PAGAMENTO_PRE_PAGO = "P";
     public static final String PAGAMENTO_POS_PAGO = "R";
     
     public static final String CD_STATUS_PGT_CONFIRMADO = "C";
     public static final String CD_STATUS_PGT_NAO_CONFIRMADO = "n";
     public static final String CD_STATUS_PGT_PENDENTE = "P";

    /**
     * Constant for Relatorio de demonstrativo de repasse - opcao contigencia
     */
    public static final String CD_CODIGO_CONTIGENCIA_NAO_EXISTENTE = "X";
    public static final String CD_TOTAL_DO_VALOR_ARRECADADO = "47";
    public static final String CD_VALOR_ARRECADADO_CORRENTE_M = "48";
    public static final String CD_VALOR_ARRECADADO_CORRENTE_M1 = "49";
    public static final String CD_VALOR_ARRECADADO_CORRENTE_M2 = "50";
    public static final String CD_VALOR_ARRECADADO_CORRENTE_M3 = "51";
	public static final String CD_VALOR_ARRECADADO_CORRENTE_M4 = "52";
	public static final String CD_VALOR_ARRECADADO_CORRENTE_M5 = "53";
	public static final String CD_VALOR_ARRECADADO_CORRENTE_MM = "54";
	public static final String CD_JUROS_MULTAS_CORRENTE = "55";
	public static final String CD_VALOR_ARRECADADO_APOS_90_DIAS_M = "56";
	public static final String CD_VALOR_ARRECADADO_APOS_90_DIAS_M1 = "57";
	public static final String CD_VALOR_ARRECADADO_APOS_90_DIAS_M2 = "58";
	public static final String CD_VALOR_ARRECADADO_APOS_90_DIAS_M3 = "59";
	public static final String CD_VALOR_ARRECADADO_APOS_90_DIAS_M4 = "60";
	public static final String CD_VALOR_ARRECADADO_APOS_90_DIAS_M5 = "61";
	public static final String CD_VALOR_ARRECADADO_APOS_90_DIAS_MM = "62";
	public static final String CD_VALOR_ARRECADADO_DE_PARCELAMENTOS = "63";
	public static final String CD_TOTAL_DEDUCOES = "64";
	public static final String CD_CONTESTACOES_DE_CLIENTES_APOS_REPASSE_M = "65";
	public static final String CD_CONTESTACOES_DE_CLIENTES_APOS_REPASSE_M1 = "66";
	public static final String CD_CONTESTACOES_DE_CLIENTES_APOS_REPASSE_M2 = "67";
	public static final String CD_CONTESTACOES_DE_CLIENTES_APOS_REPASSE_M3 = "68";
	public static final String CD_CONTESTACOES_DE_CLIENTES_APOS_REPASSE_M4 = "69";
	public static final String CD_CONTESTACOES_DE_CLIENTES_APOS_REPASSE_M5 = "70";
	public static final String CD_CONTESTACOES_DE_CLIENTES_APOS_REPASSE_MM = "71";
	public static final String CD_REVERSOES_DE_PAGAMENTO_APOS_REPASSE_M = "72";
	public static final String CD_REVERSOES_DE_PAGAMENTO_APOS_REPASSE_M1 = "73";
	public static final String CD_REVERSOES_DE_PAGAMENTO_APOS_REPASSE_M2 = "74";
	public static final String CD_REVERSOES_DE_PAGAMENTO_APOS_REPASSE_M3 = "75";
	public static final String CD_REVERSOES_DE_PAGAMENTO_APOS_REPASSE_M4 = "76";
	public static final String CD_REVERSOES_DE_PAGAMENTO_APOS_REPASSE_M5 = "77";
	public static final String CD_REVERSOES_DE_PAGAMENTO_APOS_REPASSE_MM = "78";
	public static final String CD_REMUNERACAO_DO_SERVICO_OBJETO_DO_CONTRATO = "79";
	public static final String CD_DETALHAMENTO_DAS_CHAMADAS_FATURADAS = "80";

    /*
     * INFO Form values
     */
    /**
     * Errors in forms
     */
    public static final String FORM_ERRORS = "formerrors";
    
    /*
     * INFO Values
     */
    /**
     * Success value
     */
    public static final String SUCCESS  = "success";
    /**
     * Fail value
     */
    public static final String FAIL = "fail";
    /**
     * TO LOAD Value
     */
    public static final String TO_LOAD = "TOLOAD";
    /**
     * ALFA Value
     */
    public static final String ALFA = "ALFA";
    /**
     * ReturnFile (Return=Retorno)
     */
    public static final String RETURNFILE = "RET";
    /**
     * ReturnFile (Return=Retorno)
     */
    public static final String RETURNQUESTIONMENTFILE = "QRT";
    /**
     * CheckingFile (Checking=Fiscal)
     */
    public static final String CHECKINGFILE = "FIS";
    /**
     * CheckingFile (Checking=Fraude)
     */
    public static final String CHARGINGFILE = "COB";
    /**
     * ChargingFile (Charging=Cobrança)
     */    
    public static final String CHECKFRAUDE = "checkfraude";
    /**
     * CheckingFile (Checking=MultaJuros)
     */
    public static final String CHECKMULTAJUROS = "checkmulta";
    /**
     * CheckingFile (Checking=Batimento)
     */
    public static final String CHECKBATIMENTO = "checkbatimento";
    /**
     * CheckingFile (Checking=Lotes)
     */
    public static final String CHECKLOTES = "checklotes";
    /**
     * CheckingFile (Checking=Estorno)
     */
    public static final String CHECKESTORNO = "checkEstorno";
    /**
     * LeadTime (Return=Retorno)
     */
    public static final String LEADTIME = "leadtime";
    /**
     * Financial (Return=Retorno)
     */
    public static final String FINANCIAL = "financial";
    /**
     * AceitoFile (Aceito=Remessa)
     */
    public static final String RECEIVEDFILE = "REM";
   
    public static final String RECEIVEDFILEFAKE = "REF";
    /**
     * AceitoFile (Aceito=Remessa)
     */
    public static final String RECEIVEDQUESTIONMENTFILE = "QRM";
    /**
     * Ok Status
     */
    public static final String OK_STATUS = "ok";
    /**
     * Nok Status
     */
    public static final String NOK_STATUS = "nok";
    /**
     * No Protocol Status
     */
    public static final String NOPROTOCOL_STATUS = "noprotocol";
    /**
     * Info Status
     */
    public static final String INFO_STATUS = "info";
    /**
     * Summary Status
     */
    public static final String SUMMARY_STATUS = "summary";
    /**
     * Status Confirmado
     */
    public static final String FORWARDSTATUS_CONFIRMED = "C";
    /**
     * Status Não Confirmado
     */
    public static final String FORWARDSTATUS_NOTCONFIRMED = " ";
    /**
     * Status All
     */
    public static final String FORWARDSTATUS_ALL = "A";
    /**
     * Status Cancelado
     */
    public static final String FORWARDSTATUS_CANCELED = "N";
    /**
     * Operadora Claro
     */
    public static final String CD_OPERADORA_CLARO = "claro";
    /**
     * Operadora LD
     */
    public static final String CD_OPERADORA_EXTERNA = "ld";
    /**
     * Sq Questionamento
     */
    public static final String SQ_QUESTIONAMENTO = "sqQuestionamento";
    /**
     * Cd Status Questionamento
     */
    public static final String CD_STATUS_QUESTIONAMENTO = "cdStatusQuestionamento";
    /**
     * Cd Acao
     */
    public static final String CD_ACAO = "cdAcao";
    
    /*
     * INFO Sizes
     */
    /**
     * Qty of digits of Encaminhado Report Request
     */
    public static final int SIZE_OF_ENCAMINHADO_REPORT_REQUEST_NO = 22;
    /**
     * Qty of chars of Closing Request Parameter
     */
    public static final int SIZE_OF_CLOSING_REQUEST_PARAMETER = 30;
    /**
     * Qty of digits of QuestionamentoApuracao
     */
    public static final int SIZE_OF_QUESTIONAMENTOAPURACAO_NO = 16;
    /**
     * Max of elements of a list
     */
    public static final int MAX_LIST_SIZE = 65535;    
    
    /*
     * INFO Parameters
     */
    /**
     * Qty referent to ENSEMBLE System 
     */
    public static final int ESB = 1;
    /**
     * Qty referent to MOBILE System 
     */
    public static final int MOB = 2;
    public static final String MOBILE = "mobile";
    public static final String FULL = "full";
    /**
     * Qty referent to Other Systems 
     */
    public static final int PPC = 3;
    
    /**
     * PARAMETER NAME ENCAMINHADO REPORT REQUEST
     */
    public static final String PARAMETER_ENCAMINHADO_REQUEST = "ACUMULADORPEND"; 
    /**
     * PARAMETER NAME CLOSING REQUEST FECHAMENTO
     */
    public static final String PARAMETER_CLOSING_REQUEST_FATURADO = "FECHAMENTO";
    /**
     * PARAMETER NAME CLOSING REQUEST CONTINGENCIA
     */
    public static final String PARAMETER_CLOSING_REQUEST_CONTINGENCIA = "CONTINGENCIA";
    /**
     * PARAMETER NAME CLOSING REQUEST ARRECADACAO
     */
    public static final String PARAMETER_CLOSING_REQUEST_ARRECADACAO = "ARRECADACAO";

    /**
     * PARAMETER NAME CONCATENADOR ASTERISCO
     */
    public static final String CONCATENADOR = " - ";

    /**
     * PARAMETER NAME CLOSING REQUEST
     */
    public static final String PARAMETER_PREPAGO_FECHAMENTO = "FECHAMENTO_PRE";
    
    /**
     * OS Plugin Fase 2
     */
    public static final String DESABILITAR_REPASSE_PRODUTO = "P";
    public static final String DESABILITAR_REPASSE_EVENTO = "E";
    public static final String CD_PRODUTO_PADRAO_PREPAGO = "0";
    
    /**
     * Username
     */
    public static final String SESSION_USER = "user";
    
    public static final String LGN_ADMIN = "Admin";
    public static final String LGN_USER = "User";
    public static final String LGN_OPER = "Operator";
    public static final String LGN_USERFORWARD = "UserForward";
    public static final String LGN_USERTAXES = "UserTaxes";
    public static final String SESSION_ISADMIN = "isAdmin";
    public static final String SESSION_ISUSER = "isUser";
    public static final String SESSION_ISOPER = "isOperator";
    public static final String SESSION_ISUSERFORWARD = "isUserForward";
    public static final String SESSION_ISUSERTAXES = "isUserTaxes";

    public static final int CDRSTATUS_TODOS           	= -1;
    public static final int CDRSTATUS_TODOS_BACKLOG	  	= -2;    
    public static final int CDRSTATUS_BACKLOG         	= -3;
    public static final int CDRSTATUS_ENCAMINHADO     	= -5;
    public static final int CDRSTATUS_REJEITADO       	= -10;
    public static final int CDRSTATUS_REJEITADO_C2   	= -11;
    public static final int CDRSTATUS_ALOCADO     	  	= -15;
    public static final int CDRSTATUS_FATURADO        	= -20;
	public static final int CDRSTATUS_FATURADO_FINAL    = -21;
	public static final int CDRSTATUS_FATURADO_INICIAL  = -22;
    public static final int CDRSTATUS_CONTESTADO      	= -25;
    public static final int CDRSTATUS_A_RECICLAR      	= -30;
    public static final int CDRSTATUS_PERDIDO         	= -40;
    public static final int CDRSTATUS_EXCLUIDO         	= -50;
    public static final int CDRSTATUS_EXCLUIDO_X2   	= -51;
    public static final int CDRSTATUS_A_RECUPERAR      	= -60;
    public static final int CDRSTATUS_PPC             	= -70;
    
    public static final int CDRSTATUS_INDEFINIDO       	= 0;
    public static final int CDRSTATUS_ENCAMINHADO_ESB   = 5;
    public static final int CDRSTATUS_ENCAMINHADO_MOB   = 6;
    public static final int CDRSTATUS_REJEITADO_C1   	= 10;
    public static final int CDRSTATUS_REJEITADO_C2_ESB  = 11;
    public static final int CDRSTATUS_REJEITADO_C2_MOB  = 12;
    public static final int CDRSTATUS_ALOCADO_ESB  		= 15;
    public static final int CDRSTATUS_ALOCADO_MOB  		= 16;
    public static final int CDRSTATUS_FATURADO_ESB      = 20;
    public static final int CDRSTATUS_FATURADO_MOB      = 21;
    public static final int CDRSTATUS_EM_PARCELAMENTO   = 22;
    public static final int CDRSTATUS_RETARIF           = 23;
    public static final int CDRSTATUS_CONTESTADO_ESB    = 25;
    public static final int CDRSTATUS_CONTESTADO_MOB    = 26;
    public static final int CDRSTATUS_REVERSAO        	= 27;
    public static final int CDRSTATUS_CONTESTADO_MOB_ARRECADADA = 28;
    public static final int CDRSTATUS_CONTESTADO_MOB_REPASSADA  = 29;
    public static final int CDRSTATUS_A_RECICLAR_ESB    = 30;
    public static final int CDRSTATUS_A_RECICLAR_MOB    = 31;
    public static final int CDRSTATUS_PERDIDO_PPC       = 40;
    public static final int CDRSTATUS_PERDIDO_ESB       = 41;
    public static final int CDRSTATUS_PERDIDO_MOB       = 42;
    public static final int CDRSTATUS_EXCLUIDO_X1       = 50;
    public static final int CDRSTATUS_EXCLUIDO_X2_ESB   = 51;
    public static final int CDRSTATUS_EXCLUIDO_X2_MOB   = 52;
    public static final int CDRSTATUS_EXCLUIDO_MOB_CONTESTADO = 53;
    public static final int CDRSTATUS_A_RECUPERAR_ESB   = 61;
    public static final int CDRSTATUS_A_RECUPERAR_MOB   = 62;
    public static final int CDRSTATUS_ARRECADADA      = 70;
    public static final int CDRSTATUS_EXPURGADA       = 71;
    public static final int CDRSTATUS_REVERSAO_PGTO   = 72;
    public static final int CDRSTATUS_INADIMPLENTE    = 73;
    public static final int CDRSTATUS_REPASSADA       = 80;
    public static final int CDRSTATUS_PARCELADA       = 85;
    public static final int CDRSTATUS_ALTERACAO_VCTO  = 90;

    public static final Vector vCdrStatusFull = new Vector(Arrays.asList( new String[][] {
    		{""+CDRSTATUS_TODOS, "Aceito", "Todos", "23"},
    		{""+CDRSTATUS_RETARIF, "Re-tarifado", "Re-tarifado", "22"},
    		{""+CDRSTATUS_REJEITADO, "Rejeitado", "Rejeitado", "21"},
    		{""+CDRSTATUS_ENCAMINHADO, "Encaminhado", "Encaminhado", "20"},
    		{""+CDRSTATUS_A_RECUPERAR, "A Recuperar", "A Recuperar", "19"},
    		{""+CDRSTATUS_A_RECICLAR, "A Reciclar", "A Reciclar", "18"},
    		{""+CDRSTATUS_ALOCADO, "Alocado", "Alocado", "17"},
    		{""+CDRSTATUS_FATURADO, "Faturado", "Faturado", "16"},
    		{""+CDRSTATUS_CONTESTADO, "Contestado", "Contestado", "15"},
    	    {""+CDRSTATUS_CONTESTADO_MOB_ARRECADADA, "Contestado Arrecadado", "Contestado Arrecadado", "14"},
    	    {""+CDRSTATUS_CONTESTADO_MOB_REPASSADA, "Contestado Repassado", "Contestado Repassado", "13"},
    		{""+CDRSTATUS_REVERSAO, "Reversão", "Reversão", "12"},
    		{""+CDRSTATUS_EXCLUIDO, "Excluído", "Excluído", "11"},
    	    {""+CDRSTATUS_EXCLUIDO_MOB_CONTESTADO, "Excluído Contestado", "Excluído Contestado", "10"},
    		{""+CDRSTATUS_PERDIDO, "Perdido", "Perdido", "9"},
    	    {""+CDRSTATUS_ARRECADADA, "Arrecadado", "Arrecadado", "8"},
    	    {""+CDRSTATUS_EXPURGADA, "Expurgado", "Expurgado", "7"},
    	    {""+CDRSTATUS_REVERSAO_PGTO, "Reversão Pagamento", "Reversão Pagamento", "6"},
    	    {""+CDRSTATUS_INADIMPLENTE, "Inadimplente", "Inadimplente", "5"},
    	    {""+CDRSTATUS_REPASSADA, "Repassada", "Repassada", "4"},
    	    {""+CDRSTATUS_PARCELADA, "Arrecadado via Parcelamento", "Arrecadado via Parcelamento", "3"},
    	    {""+CDRSTATUS_ALTERACAO_VCTO, "Alteração Vencimento", "Alteração Vencimento", "2"},
    		{""+CDRSTATUS_EM_PARCELAMENTO, "Em Parcelamento", "Em Parcelamento", "1"}
	} ) );
	 
    public static final String[] buscaConfigCdrStatusFull(String codigo) {
	    String[] tCdrStatusFull = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vCdrStatusFull.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vCdrStatusFull.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) { 
	    		tCdrStatusFull = tTemp;
	    		break;
	    	}
	    }
	    return tCdrStatusFull;
    }
    
    public static final Vector vCdrStatusAll = new Vector(Arrays.asList( new String[][] { 
       		{""+CDRSTATUS_ENCAMINHADO, "Encaminhado"},
       		{""+CDRSTATUS_ENCAMINHADO_ESB, "Encaminhado ESB"},
       		{""+CDRSTATUS_ENCAMINHADO_MOB, "Encaminhado MOB"},
       		{""+CDRSTATUS_REJEITADO, "Rejeitado"},
       		{""+CDRSTATUS_REJEITADO_C1, "Rejeitado C1"},
       		{""+CDRSTATUS_REJEITADO_C2, "Rejeitado C2"},
       		{""+CDRSTATUS_ALOCADO, "Alocado"},
       		{""+CDRSTATUS_ALOCADO_ESB, "Alocado ESB"},
       		{""+CDRSTATUS_ALOCADO_MOB, "Alocado MOB"},
       		{""+CDRSTATUS_FATURADO, "Faturado"},
       		{""+CDRSTATUS_FATURADO_ESB, "Faturado ESB"},
       		{""+CDRSTATUS_FATURADO_MOB, "Faturado MOB"},
       		{""+CDRSTATUS_CONTESTADO, "Contestado"},
       		{""+CDRSTATUS_CONTESTADO_ESB, "Contestado ESB"},
       		{""+CDRSTATUS_CONTESTADO_MOB, "Contestado MOB"},
       		{""+CDRSTATUS_REVERSAO, "Reversão Contestado"},
       		{""+CDRSTATUS_A_RECICLAR, "A Reciclar"},
       		{""+CDRSTATUS_A_RECICLAR_ESB, "A Reciclar ESB"},
       		{""+CDRSTATUS_A_RECICLAR_MOB, "A Reciclar MOB"},
       		{""+CDRSTATUS_A_RECUPERAR, "A Recuperar"},
       		{""+CDRSTATUS_A_RECUPERAR_ESB, "A Recuperar ESB"},
       		{""+CDRSTATUS_A_RECUPERAR_MOB, "A Recuperar MOB"},
       		{""+CDRSTATUS_PERDIDO, "Perdido"},
       		{""+CDRSTATUS_PERDIDO_ESB, "Perdido ESB"},
       		{""+CDRSTATUS_PERDIDO_MOB, "Perdido MOB"},
       		{""+CDRSTATUS_PERDIDO_PPC, "Perdido PPC"},
       		{""+CDRSTATUS_EXCLUIDO, "Excluido"},
       		{""+CDRSTATUS_EXCLUIDO_X1, "Excluido X1"},
       		{""+CDRSTATUS_EXCLUIDO_X2, "Excluido X2"}
    } ) );

	public static final String CDRSUBSTATUS_FATURADO_INICIAL	= "FI";
	public static final String CDRSUBSTATUS_FATURADO_FINAL    	= "FF";
    public static final String CDRSUBSTATUS_REJEITADO_C1 		= "C1";
    public static final String CDRSUBSTATUS_REJEITADO_C2 		= "C2";
    public static final String CDRSUBSTATUS_EXCLUIDO_X1 		= "X1";
    public static final String CDRSUBSTATUS_EXCLUIDO_X2 		= "X2";
    public static final String CDRSUBSTATUS_EXCLUIDO_X3 		= "X3";
    public static final String CDRSUBSTATUS_EXCLUIDO_X4 		= "X4";
    public static final String CDRSUBSTATUS_PERDIDO_P1 			= "P1";
    public static final String CDRSUBSTATUS_PERDIDO_P2 			= "P2";
    public static final String CDRSUBSTATUS_PERDIDO_P3 			= "P3";
    public static final String CDRSUBSTATUS_PERDIDO_P4 			= "P4";
    public static final String CDRSUBSTATUS_PERDIDO_P5 			= "P5";
    public static final String CDRSUBSTATUS_PERDIDO_P6 			= "P6";
    public static final String CDRSUBSTATUS_PERDIDO_P7 			= "P7";
    
    public static final Vector vCdrSubStatus = new Vector(Arrays.asList( new String[][] { 
       		{CDRSUBSTATUS_REJEITADO_C1, "C1"},
       		{CDRSUBSTATUS_REJEITADO_C2, "C2"},
       		{CDRSUBSTATUS_EXCLUIDO_X1, "X1"},
       		{CDRSUBSTATUS_EXCLUIDO_X2, "X2"},
       		{CDRSUBSTATUS_EXCLUIDO_X3, "X3"},
       		{CDRSUBSTATUS_EXCLUIDO_X4, "X4"},
       		{CDRSUBSTATUS_PERDIDO_P1, "P1"},
       		{CDRSUBSTATUS_PERDIDO_P2, "P2 Descartada"},
       		{CDRSUBSTATUS_PERDIDO_P3, "P3 Retirada"},
       		{CDRSUBSTATUS_PERDIDO_P4, "P4 Abortada"},
       		{CDRSUBSTATUS_PERDIDO_P5, "P5 N-Reciclada"},
       		{CDRSUBSTATUS_PERDIDO_P6, "P6 N-Incluída"},
       		{CDRSUBSTATUS_PERDIDO_P7, "P7 NID"}
    } ) );

    public static final String[] buscaConfigCdrSubStatus(String codigo) {
	    String[] tCdrSubStatus = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vCdrSubStatus.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vCdrSubStatus.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) { 
	    		tCdrSubStatus = tTemp;
	    		break;
	    	}
	    }
	    return tCdrSubStatus;
    }
    
    public static final Vector vLeadTime = new Vector(Arrays.asList( new String[][] { 
       		{""+CDRSTATUS_FATURADO, "Faturado", "01","30","31","60","61","90"},
       		{""+CDRSTATUS_REJEITADO, "Rejeitado C1+C2", "01","07","08","10","11","30"},
       		{""+CDRSTATUS_REJEITADO_C1, "Rejeitado C1", "01","03","04","06","07","30"},
       		{""+CDRSTATUS_REJEITADO_C2, "Rejeitado C2", "01","10","11","20","21","30"},
       		{""+CDRSTATUS_EXCLUIDO, "Excluido X1+X2", "01","10","11","30","31","90"},
       		{""+CDRSTATUS_EXCLUIDO_X1, "Excluido X1", "01","03","04","06","07","90"},
       		{""+CDRSTATUS_EXCLUIDO_X2, "Excluido X2", "01","10","11","20","21","90"},
       		{""+CDRSTATUS_CONTESTADO, "Contestado", "01","03","04","10","11","365"}
    } ) );
    
    public static String getLabelFaixaLeadTime(String status, int escala) {
    	String[] tSTATUS = buscaStatusLeadTime(status);
    	String faixa = "";
    	if (escala==1)
    		faixa = tSTATUS[2]+"-"+tSTATUS[3];
    	if (escala==2)
    		faixa = tSTATUS[4]+"-"+tSTATUS[5];
    	if (escala==3)
    		faixa = (tSTATUS[7].equalsIgnoreCase("365")?">= "+tSTATUS[6]:tSTATUS[6]+"-"+tSTATUS[7]);
        return faixa;
    }
    
    public static int getIndexFaixaLeadTime(String status, int qtDias) {
    	String[] tSTATUS = buscaStatusLeadTime(status);
    	int faixa = 0;
    	if ((qtDias>=0)&&(qtDias<=Integer.parseInt(tSTATUS[3])))
    		faixa = 1;
    	if ((qtDias>=Integer.parseInt(tSTATUS[4]))&&(qtDias<=Integer.parseInt(tSTATUS[5])))
    		faixa = 2;
	    if ((qtDias>=Integer.parseInt(tSTATUS[6]))&&(qtDias<=Integer.parseInt(tSTATUS[7]))) 
	    	faixa = 3;
    	if (qtDias>=Integer.parseInt(tSTATUS[7])&&tSTATUS[7].equalsIgnoreCase("365")) 
    		faixa = 3;
        return faixa;
    }
    
    public static final String[] buscaStatusLeadTime(String status) {
	    String[] tSTATUS = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vLeadTime.size();i++) {
	    	tTemp = (String[]) CobillingConstants.vLeadTime.elementAt(i);
	    	if (status.equalsIgnoreCase(tTemp[0])) {
	    		tSTATUS = tTemp;
	    		break;
	    	}
	    }
	    if (tSTATUS == null) {
	    	status = (status!=null&&status.length()>0?((Integer.parseInt(status)<0?"-"+status:status)):"");
		    for(int i=0;i<CobillingConstants.vLeadTime.size();i++) {
		    	tTemp = (String[]) CobillingConstants.vLeadTime.elementAt(i);
		    	if (status.equalsIgnoreCase(tTemp[0])) {
		    		tSTATUS = tTemp;
		    		break;
		    	}
		    }
	    	
	    }
	    return tSTATUS;
    }

    public static final Vector vFinancial = new Vector(Arrays.asList( new String[][] { 
       		{""+CDRSTATUS_REJEITADO, "Rejeitado C1-C2"},
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
	    for(int i=0;i<CobillingConstants.vFinancial.size();i++) {
	    	tTemp = (String[]) CobillingConstants.vFinancial.elementAt(i);
	    	if (status.equalsIgnoreCase(tTemp[0])) {
	    		tSTATUS = tTemp;
	    		break;
	    	}
	    }
	    if (tSTATUS == null) {
	    	status = (status!=null&&status.length()>0?((Integer.parseInt(status)<0?"-"+status:status)):"");
		    for(int i=0;i<CobillingConstants.vFinancial.size();i++) {
		    	tTemp = (String[]) CobillingConstants.vFinancial.elementAt(i);
		    	if (status.equalsIgnoreCase(tTemp[0])) {
		    		tSTATUS = tTemp;
		    		break;
		    	}
		    }
	    }
	    return tSTATUS;
    }

    // Itens CoBilling Repasse = BFI
    public static final double PERCENTUALM2 = 0.6;
    public static final double PERCENTUALM3 = 0.4;

    public static final int SORT_BFI_REPASSE = 1;
    public static final int SORT_BFI_VLCRecebidasM2 = 2;
    public static final int SORT_BFI_VCRejeicaoM2 = 3;
    public static final int SORT_BFI_VCFaturaveisIM2 = 4;
    public static final int SORT_BFI_DescontoCM2 = 5;
    public static final int SORT_BFI_VLCRecebidasM3 = 6;
    public static final int SORT_BFI_VCRejeicaoM3 = 7;
    public static final int SORT_BFI_VCFaturaveisIM3 = 8;
    public static final int SORT_BFI_DescontoCM3 = 9;
    public static final int SORT_BFI_VFICalculado = 10;
    public static final int SORT_BFI_VFRepassar = 11;
    public static final int SORT_BFI_DEDUCOES = 12;
    public static final int SORT_BFI_DetalhamentoCF = 13;
    public static final int SORT_BFI_RSOContrato = 14;
    public static final int SORT_BFI_TDeducoes = 15;
    public static final int SORT_BFI_AJUSTES = 16;
    public static final int SORT_BFI_ACFOLD = 17;
    public static final int SORT_BFI_ACFOClaro = 18;
    public static final int SORT_BFI_TAjustes = 19;
    public static final int SORT_BFI_PENALIDADES = 20;
    public static final int SORT_BFI_PRDCOLD = 21;
    public static final int SORT_BFI_PRDCOClaro = 22;
    public static final int SORT_BFI_MJAPCOLD = 23;
    public static final int SORT_BFI_MJAPCOClaro = 24;
    public static final int SORT_BFI_TPenalidades = 25;
    public static final int SORT_BFI_ValorRepasse1234 = 26;
    public static final int SORT_BFI_CPMFValorRepasse = 27;
    public static final int SORT_BFI_ValorRepassar56 = 28;
    
    public static final int CD_BFI_REPASSE = 200;
    public static final int CD_BFI_VLCRecebidasM2 = 14;
    public static final int CD_BFI_VCRejeicaoM2 = 15;
    public static final int CD_BFI_VCFaturaveisIM2 = 16;
    public static final int CD_BFI_DescontoCM2 = 24;
    public static final int CD_BFI_VLCRecebidasM3 = 17;
    public static final int CD_BFI_VCRejeicaoM3 = 18;
    public static final int CD_BFI_VCFaturaveisIM3 = 19;
    public static final int CD_BFI_DescontoCM3 = 21;
    public static final int CD_BFI_VFICalculado = 20;
    public static final int CD_BFI_VFRepassar = 201;
    public static final int CD_BFI_DEDUCOES = 202;
    public static final int CD_BFI_DetalhamentoCF = 23;   
    public static final int CD_BFI_RSOContrato = 22;
    public static final int CD_BFI_TDeducoes = 203;
    public static final int CD_BFI_AJUSTES = 204;
    public static final int CD_BFI_ACFOLD = 9;
    public static final int CD_BFI_ACFOClaro = 8;
    public static final int CD_BFI_TAjustes = 205;
    public static final int CD_BFI_PENALIDADES = 206;    
    public static final int CD_BFI_PRDCOLD = 11;
    public static final int CD_BFI_PRDCOClaro = 10;
    public static final int CD_BFI_MJAPCOLD = 13;
    public static final int CD_BFI_MJAPCOClaro = 12;
    public static final int CD_BFI_TPenalidades = 207;
    public static final int CD_BFI_ValorRepasse1234 = 208;
    public static final int CD_BFI_CPMFValorRepasse = 6;
    public static final int CD_BFI_ValorRepassar56 = 209;
    
    public static final Vector vBFI = new Vector(Arrays.asList( new String[][] { 
   		{""+CD_BFI_REPASSE, ""+SORT_BFI_REPASSE, "1-VALOR BRUTO A REPASSAR"},
    	{""+CD_BFI_VLCRecebidasM2, ""+SORT_BFI_VLCRecebidasM2, "  1.1 VALOR TOTAL CHAMADAS ACEITAS (M-2)"},
        {""+CD_BFI_VCRejeicaoM2, ""+SORT_BFI_VCRejeicaoM2, "  1.2 VALOR CHAMADAS - REJEIÇÃO (M-2)"},
        {""+CD_BFI_VCFaturaveisIM2, ""+SORT_BFI_VCFaturaveisIM2, "  1.3 VALOR CHAMADAS FATURÁVEIS INICIAIS (M-2)"},
        {""+CD_BFI_DescontoCM2, ""+SORT_BFI_DescontoCM2, "  1.4 DESCONTO CONTRATUAL DE M-2 "},
        {""+CD_BFI_VLCRecebidasM3, ""+SORT_BFI_VLCRecebidasM3, "  1.5 VALOR TOTAL CHAMADAS ACEITAS (M-3)"}, 
        {""+CD_BFI_VCRejeicaoM3, ""+SORT_BFI_VCRejeicaoM3, "  1.6 VALOR CHAMADAS - REJEIÇÃO (M-3)"},
        {""+CD_BFI_VCFaturaveisIM3, ""+SORT_BFI_VCFaturaveisIM3, "  1.7 VALOR CHAMADAS FATURÁVEIS INICIAIS (M-3)"},
        {""+CD_BFI_DescontoCM3, ""+SORT_BFI_DescontoCM3, "  1.8 DESCONTO CONTRATUAL DE M-3 "},
        {""+CD_BFI_VFICalculado, ""+SORT_BFI_VFICalculado, "  1.9 VALOR FATURÁVEL INICIAL"},
        {""+CD_BFI_VFRepassar, ""+SORT_BFI_VFRepassar, "1-TOTAL DO VALOR BRUTO A REPASSAR"},
		{""+CD_BFI_DEDUCOES, ""+SORT_BFI_DEDUCOES, "2-DEDUÇÕES"},
        {""+CD_BFI_DetalhamentoCF, ""+SORT_BFI_DetalhamentoCF, "  2.1 DETALHAMENTO DAS CHAMADAS FATURADAS (M-1)"},
        {""+CD_BFI_RSOContrato, ""+SORT_BFI_RSOContrato, "  2.2 REMUNERAÇÃO DO SERVIÇO OBJETO DO CONTRATO (M-1)"},
        {""+CD_BFI_TDeducoes, ""+SORT_BFI_TDeducoes, "2-TOTAL DAS DEDUÇÕES"},
		{""+CD_BFI_AJUSTES, ""+SORT_BFI_AJUSTES, "3-AJUSTES"},
        {""+CD_BFI_ACFOLD, ""+SORT_BFI_ACFOLD, "  3.1 ACERTO DE CONCILIAÇÃO A FAVOR DA OPERADORA LD"},
        {""+CD_BFI_ACFOClaro, ""+SORT_BFI_ACFOClaro, "  3.2 ACERTO DE CONCILIAÇÃO A FAVOR DA CLARO"},
        {""+CD_BFI_TAjustes, ""+SORT_BFI_TAjustes, "3-TOTAL DOS AJUSTES"},
		{""+CD_BFI_PENALIDADES, ""+SORT_BFI_PENALIDADES, "4-PENALIDADES"},
        {""+CD_BFI_PRDCOLD, ""+SORT_BFI_PRDCOLD, "  4.1 PENALIDADE POR REJEIÇÕES DEVIDAS CONTRA OPERADORA LD (M-1)"},
        {""+CD_BFI_PRDCOClaro, ""+SORT_BFI_PRDCOClaro, "  4.2 PENALIDADE POR REJEIÇÕES INDEVIDAS CONTRA CLARO (M-1)"},
        {""+CD_BFI_MJAPCOLD, ""+SORT_BFI_MJAPCOLD, "  4.3 MULTA/JUROS POR ATRASO NO PAGAMENTO CONTRA OPERADORA LD (M-1)"},
        {""+CD_BFI_MJAPCOClaro, ""+SORT_BFI_MJAPCOClaro, "  4.4 MULTA/JUROS POR ATRASO NO REPASSE CONTRA CLARO (M-1)"},
        {""+CD_BFI_TPenalidades, ""+SORT_BFI_TPenalidades, "4-TOTAL DAS PENALIDADES"},
        {""+CD_BFI_ValorRepasse1234, ""+SORT_BFI_ValorRepasse1234, "5-BASE DE CÁLCULO (1-2+3+4)"},
        {""+CD_BFI_CPMFValorRepasse, ""+SORT_BFI_CPMFValorRepasse, "6-CPMF A DESCONTAR DO REPASSE (BASE DE CÁLCULO)"},
        {""+CD_BFI_ValorRepassar56, ""+SORT_BFI_ValorRepassar56, "7-VALOR LÍQUIDO A REPASSAR (5-6)"}
    } ) );

    // Itens CoBilling Pre Pago Repasse = PPI
    public static final int SORT_PPI_TTLVLRApurado = 1;
    public static final int SORT_PPI_VlrChmAprMesAnterior = 2;
    public static final int SORT_PPI_VlrChmAprOtrMeses = 3;
    public static final int SORT_PPI_TTLDeducoes = 4;
    public static final int SORT_PPI_SrvPrestado = 5;
    public static final int SORT_PPI_CrdAutorizados = 6;
    public static final int SORT_PPI_CrdAnl226 = 7;
    public static final int SORT_PPI_TTLPenalidades = 8;
    public static final int SORT_PPI_PnlCntClaro = 9;
    public static final int SORT_PPI_MltJrsCntClaro = 10;
    public static final int SORT_PPI_MltJrsCntLD = 11;
    public static final int SORT_PPI_TTLAcertos = 12;
    public static final int SORT_PPI_AcrCntClaro = 13;
    public static final int SORT_PPI_AcrCntLD = 14;
    public static final int SORT_PPI_CPMFDescontar = 15;
    public static final int SORT_PPI_ICMSDescontar = 16;
    public static final int SORT_PPI_ICMSRepassar = 17;
    public static final int SORT_PPI_VLRFNLRepassar = 18;

    public static final int CD_PPI_CancelarRepasse = -1;
    public static final int CD_PPI_ConfirmarRepasse = 0;
    public static final int CD_PPI_TTLVLRApurado = 1;
    public static final int CD_PPI_VlrChmAprMesAnterior = 2;
    public static final int CD_PPI_VlrChmAprOtrMeses = 3;
    public static final int CD_PPI_TTLDeducoes = 4;
    public static final int CD_PPI_SrvPrestado = 5;
    public static final int CD_PPI_CrdAutorizados = 6;
    public static final int CD_PPI_CrdAnl226 = 7;
    public static final int CD_PPI_TTLPenalidades = 8;
    public static final int CD_PPI_PnlCntClaro = 9;
    public static final int CD_PPI_MltJrsCntClaro = 10;
    public static final int CD_PPI_MltJrsCntLD = 11;
    public static final int CD_PPI_TTLAcertos = 12;
    public static final int CD_PPI_AcrCntClaro = 13;
    public static final int CD_PPI_AcrCntLD = 14;
    public static final int CD_PPI_CPMFDescontar = 15;
    public static final int CD_PPI_ICMSDescontar = 16;
    public static final int CD_PPI_ICMSRepassar = 17;
    public static final int CD_PPI_VLRFNLRepassar = 18;
    
    public static final Vector vPPI = new Vector(Arrays.asList( new String[][] { 
       		{""+CD_PPI_VlrChmAprMesAnterior, ""+SORT_PPI_VlrChmAprMesAnterior, "  1.1 VALOR CHAMADAS APURADAS MÊS ANTERIOR"},
       		{""+CD_PPI_VlrChmAprOtrMeses, ""+SORT_PPI_VlrChmAprOtrMeses, "  1.2 VALOR CHAMADAS APURADAS OUTROS MESES"},
       		{""+CD_PPI_TTLVLRApurado, ""+SORT_PPI_TTLVLRApurado, "1-TOTAL DO VALOR APURADO"},
       		{""+CD_PPI_SrvPrestado, ""+SORT_PPI_SrvPrestado, "  2.1 SERVIÇO PRESTADO (UTILIZAÇÃO DA PLATAFORMA)"},
       		{""+CD_PPI_CrdAutorizados, ""+SORT_PPI_CrdAutorizados, "  2.2 CRÉDITOS AUTORIZADOS PELA PRESTADORA LD"},
       		{""+CD_PPI_CrdAnl226, ""+SORT_PPI_CrdAnl226, "  2.3 CRÉDITOS - ANATEL 226"},
       		{""+CD_PPI_TTLDeducoes, ""+SORT_PPI_TTLDeducoes, "2-TOTAL DAS DEDUÇÕES"},
       		{""+CD_PPI_PnlCntClaro, ""+SORT_PPI_PnlCntClaro, "  3.1 PENALIDADE RELATIVA A MINUTOS PERDIDOS CONTRA CLARO"},
       		{""+CD_PPI_MltJrsCntClaro, ""+SORT_PPI_MltJrsCntClaro, "  3.2 MULTAS/JUROS POR ATRASO DE PAGAMENTO CONTRA CLARO"},
       		{""+CD_PPI_MltJrsCntLD, ""+SORT_PPI_MltJrsCntLD, "  3.3 MULTAS/JUROS POR ATRASO DE PAGAMENTO CONTRA PRESTADORA LD"},
       		{""+CD_PPI_TTLPenalidades, ""+SORT_PPI_TTLPenalidades, "3-TOTAL DAS PENALIDADES"},
       		{""+CD_PPI_AcrCntClaro, ""+SORT_PPI_AcrCntClaro, "  4.1 ACERTO DE CONCILIAÇÃO CONTRA CLARO"},
       		{""+CD_PPI_AcrCntLD, ""+SORT_PPI_AcrCntLD, "  4.2 ACERTO DE CONCILIAÇÃO CONTRA PRESTADORA LD"},
       		{""+CD_PPI_TTLAcertos, ""+SORT_PPI_TTLAcertos, "4-TOTAL DOS ACERTOS"},
       		{""+CD_PPI_CPMFDescontar, ""+SORT_PPI_CPMFDescontar, "5-CPMF A DESCONTAR (BASE DE CÁLCULO: 1 ± 2 ± 3 ± 4)"},
       		{""+CD_PPI_ICMSDescontar, ""+SORT_PPI_ICMSDescontar, "6-ICMS A DESCONTAR (Repasse Próximo Mês)"},
       		{""+CD_PPI_ICMSRepassar, ""+SORT_PPI_ICMSRepassar, "7-ICMS A REPASSAR (Descontado Mês Anterior)"},
       		{""+CD_PPI_VLRFNLRepassar, ""+SORT_PPI_VLRFNLRepassar, "8-VALOR FINAL A REPASSAR"}
    } ) );

    // Itens CoBilling Repasse Arrecadado = ICRA
    public static final int SORT_ICRA_ARRECADADO = 1;
    public static final int SORT_ICRA_VlrArrCorrente = 2;
    public static final int SORT_ICRA_JurMulCorrente = 3;
    public static final int SORT_ICRA_VlrArrPos90d = 4;
    public static final int SORT_ICRA_JurMulPos90d = 5;
    public static final int SORT_ICRA_VlrArrParcelamentos = 6;
    public static final int SORT_ICRA_DEDUCOES = 7;
    public static final int SORT_ICRA_ContCliAposRepasse = 8;
    public static final int SORT_ICRA_RvsPgtoAposRepasse = 9;
    public static final int SORT_ICRA_RmnSrvObjContrato = 10;
    public static final int SORT_ICRA_DetChmFaturadas = 11;
    public static final int SORT_ICRA_RmnSrvExtAdmCartoes = 12;
    public static final int SORT_ICRA_RmnSrvExtCobranca = 13;
    public static final int SORT_ICRA_ActConcContrClaro = 14;
    public static final int SORT_ICRA_ActConcContrLD = 15;
    public static final int SORT_ICRA_ACERTOS = 16;
    public static final int SORT_ICRA_PRIContraClaro = 17;
    public static final int SORT_ICRA_PRDContraLD = 18;
    public static final int SORT_ICRA_PCPContraClaro = 19;
    public static final int SORT_ICRA_PPDGContraClaro_SLA = 20;
    public static final int SORT_ICRA_JMAAtrasoRepasseClaro = 21;
    public static final int SORT_ICRA_JMAPagamentoLD = 22;
    public static final int SORT_ICRA_PENALIDADES = 23;
    public static final int SORT_ICRA_ValorRepasse1234 = 24;
    public static final int SORT_ICRA_CPMFValorRepasse = 25;
    public static final int SORT_ICRA_ValorRepassar56 = 26;
    
    public static final int SORT_ICRA_VlrArrCorrenteM1 = 27;
    public static final int SORT_ICRA_VlrArrCorrenteM2 = 28;
    public static final int SORT_ICRA_VlrArrCorrenteM3 = 29;
    public static final int SORT_ICRA_VlrArrCorrenteM4 = 30;
    public static final int SORT_ICRA_VlrArrCorrenteM5 = 31;
    public static final int SORT_ICRA_VlrArrCorrenteM6 = 32;
    
    public static final int SORT_ICRA_VlrArrPos90dM1 = 33;
    public static final int SORT_ICRA_VlrArrPos90dM2 = 34;
    public static final int SORT_ICRA_VlrArrPos90dM3 = 35;
    public static final int SORT_ICRA_VlrArrPos90dM4 = 36;
    public static final int SORT_ICRA_VlrArrPos90dM5 = 37;
    public static final int SORT_ICRA_VlrArrPos90dM6 = 38;
    
    public static final int SORT_ICRA_ContCliAposRepasseM1 = 39;
    public static final int SORT_ICRA_ContCliAposRepasseM2 = 40;
    public static final int SORT_ICRA_ContCliAposRepasseM3 = 41;
    public static final int SORT_ICRA_ContCliAposRepasseM4 = 42;
    public static final int SORT_ICRA_ContCliAposRepasseM5 = 43;
    public static final int SORT_ICRA_ContCliAposRepasseM6 = 44;
    
    public static final int SORT_ICRA_RvsPgtoAposRepasseM1 = 45;
    public static final int SORT_ICRA_RvsPgtoAposRepasseM2 = 46;
    public static final int SORT_ICRA_RvsPgtoAposRepasseM3 = 47;
    public static final int SORT_ICRA_RvsPgtoAposRepasseM4 = 48;
    public static final int SORT_ICRA_RvsPgtoAposRepasseM5 = 49;
    public static final int SORT_ICRA_RvsPgtoAposRepasseM6 = 50;

    public static final int CD_ICRA_ARRECADADO = 29;
    public static final int CD_ICRA_VlrArrCorrente = 30;
    public static final int CD_ICRA_JurMulCorrente = 31;
    public static final int CD_ICRA_VlrArrPos90d = 32;
    public static final int CD_ICRA_JurMulPos90d = 33;
    public static final int CD_ICRA_VlrArrParcelamentos = 34;
    public static final int CD_ICRA_DEDUCOES = 28;
    public static final int CD_ICRA_ContCliAposRepasse = 35;
    public static final int CD_ICRA_RvsPgtoAposRepasse = 36;
    public static final int CD_ICRA_RmnSrvObjContrato = 22;
    public static final int CD_ICRA_DetChmFaturadas = 37;
    public static final int CD_ICRA_RmnSrvExtAdmCartoes = 204;
    public static final int CD_ICRA_RmnSrvExtCobranca = 205;
    public static final int CD_ICRA_ACERTOS = 300;
    public static final int CD_ICRA_ActConcContrClaro = 9;
    public static final int CD_ICRA_ActConcContrLD = 8;
    public static final int CD_ICRA_PENALIDADES = 41;
    public static final int CD_ICRA_PRIContraClaro = 10;
    public static final int CD_ICRA_PRDContraLD = 11;
    public static final int CD_ICRA_PCPContraClaro = 42;
    public static final int CD_ICRA_PPDGContraClaro_SLA = 43;
    public static final int CD_ICRA_JMAAtrasoRepasseClaro = 12;
    public static final int CD_ICRA_JMAPagamentoLD = 13;
    public static final int CD_ICRA_ValorRepasse1234 = 500;
    public static final int CD_ICRA_CPMFValorRepasse = 600;
    public static final int CD_ICRA_ValorRepassar56 = 700;

    // Contingência.
    
    //VALOR ARRECADADO CORRENTE -  intervalo de M[1..6]
    public static final int CD_ICRA_VlrArrCorrenteM1 = 48;
    public static final int CD_ICRA_VlrArrCorrenteM2 = 49;
    public static final int CD_ICRA_VlrArrCorrenteM3 = 50;
    public static final int CD_ICRA_VlrArrCorrenteM4 = 51;
    public static final int CD_ICRA_VlrArrCorrenteM5 = 52;
    public static final int CD_ICRA_VlrArrCorrenteM6 = 53;
    //VALOR ARRECADADO APÓS 90 DIAS - intervalo de M[1..6]
    public static final int CD_ICRA_VlrArrPos90dM1 = 56;
    public static final int CD_ICRA_VlrArrPos90dM2 = 57;
    public static final int CD_ICRA_VlrArrPos90dM3 = 58;
    public static final int CD_ICRA_VlrArrPos90dM4 = 59;
    public static final int CD_ICRA_VlrArrPos90dM5 = 60;
    public static final int CD_ICRA_VlrArrPos90dM6 = 61;
    //CONTESTAÇÕES DE CLIENTES APÓS REPASSE - intervalo de M[1..6]
    public static final int CD_ICRA_ContCliAposRepasseM1 = 65;
    public static final int CD_ICRA_ContCliAposRepasseM2 = 66;
    public static final int CD_ICRA_ContCliAposRepasseM3 = 67;
    public static final int CD_ICRA_ContCliAposRepasseM4 = 68;
    public static final int CD_ICRA_ContCliAposRepasseM5 = 69;
    public static final int CD_ICRA_ContCliAposRepasseM6 = 70;
    //REVERSÕES DE PAGAMENTO APÓS REPASSE - intervalo de M[1..6]
    public static final int CD_ICRA_RvsPgtoAposRepasseM1 = 72;
    public static final int CD_ICRA_RvsPgtoAposRepasseM2 = 73;
    public static final int CD_ICRA_RvsPgtoAposRepasseM3 = 74;
    public static final int CD_ICRA_RvsPgtoAposRepasseM4 = 75;
    public static final int CD_ICRA_RvsPgtoAposRepasseM5 = 76;
    public static final int CD_ICRA_RvsPgtoAposRepasseM6 = 77;
    
    public static final Vector vICRA = new Vector(Arrays.asList( new String[][] {
   		{""+CD_ICRA_ARRECADADO, ""+SORT_ICRA_ARRECADADO, "1- TOTAL DO VALOR ARRECADADO"},
    	{""+CD_ICRA_VlrArrCorrente, ""+SORT_ICRA_VlrArrCorrente, "  1.1 VALOR ARRECADADO CORRENTE"},
    	{""+CD_ICRA_VlrArrCorrenteM1, ""+SORT_ICRA_VlrArrCorrenteM1, "  1.1.1 VALOR ARRECADADO CORRENTE M-1"},
    	{""+CD_ICRA_VlrArrCorrenteM2, ""+SORT_ICRA_VlrArrCorrenteM2, "  1.1.2 VALOR ARRECADADO CORRENTE M-2"},
    	{""+CD_ICRA_VlrArrCorrenteM3, ""+SORT_ICRA_VlrArrCorrenteM3, "  1.1.3 VALOR ARRECADADO CORRENTE M-3"},
    	{""+CD_ICRA_VlrArrCorrenteM4, ""+SORT_ICRA_VlrArrCorrenteM4, "  1.1.4 VALOR ARRECADADO CORRENTE M-4"},
    	{""+CD_ICRA_VlrArrCorrenteM5, ""+SORT_ICRA_VlrArrCorrenteM5, "  1.1.5 VALOR ARRECADADO CORRENTE M-5"},
    	{""+CD_ICRA_VlrArrCorrenteM6, ""+SORT_ICRA_VlrArrCorrenteM6, "  1.1.6 VALOR ARRECADADO CORRENTE MAIOR QUE M-5"},
        {""+CD_ICRA_JurMulCorrente, ""+SORT_ICRA_JurMulCorrente, "  1.2 JUROS+MULTAS CORRENTE"},
        {""+CD_ICRA_VlrArrPos90d, ""+SORT_ICRA_VlrArrPos90d, "  1.3 VALOR ARRECADADO APÓS EXPURGO"},
        {""+CD_ICRA_VlrArrPos90dM1, ""+SORT_ICRA_VlrArrPos90dM1, "  1.3.1 VALOR ARRECADADO APÓS 90 DIAS M-1"},
        {""+CD_ICRA_VlrArrPos90dM2, ""+SORT_ICRA_VlrArrPos90dM2, "  1.3.2 VALOR ARRECADADO APÓS 90 DIAS M-2"},
        {""+CD_ICRA_VlrArrPos90dM3, ""+SORT_ICRA_VlrArrPos90dM3, "  1.3.3 VALOR ARRECADADO APÓS 90 DIAS M-3"},
        {""+CD_ICRA_VlrArrPos90dM4, ""+SORT_ICRA_VlrArrPos90dM4, "  1.3.4 VALOR ARRECADADO APÓS 90 DIAS M-4"},
        {""+CD_ICRA_VlrArrPos90dM5, ""+SORT_ICRA_VlrArrPos90dM5, "  1.3.5 VALOR ARRECADADO APÓS 90 DIAS M-5"},
        {""+CD_ICRA_VlrArrPos90dM6, ""+SORT_ICRA_VlrArrPos90dM6, "  1.3.6 VALOR ARRECADADO APÓS 90 DIAS MAIOR QUE M-5"},
        {""+CD_ICRA_JurMulPos90d, ""+SORT_ICRA_JurMulPos90d, "  1.4 JUROS+MULTAS APÓS 90 DIAS"},
        {""+CD_ICRA_VlrArrParcelamentos, ""+SORT_ICRA_VlrArrParcelamentos, "  1.5 VALOR ARRECADADO DE PARCELAMENTOS"},
        {""+CD_ICRA_DEDUCOES, ""+SORT_ICRA_DEDUCOES, "2- TOTAL DAS DEDUÇÕES"},
        {""+CD_ICRA_ContCliAposRepasse, ""+SORT_ICRA_ContCliAposRepasse, "  2.1 CONTESTAÇÕES DE CLIENTES APÓS REPASSE"},
        {""+CD_ICRA_ContCliAposRepasseM1, ""+SORT_ICRA_ContCliAposRepasseM1, "  2.1.1 CONTESTAÇÕES DE CLIENTES APÓS REPASSE - M-1"},
        {""+CD_ICRA_ContCliAposRepasseM2, ""+SORT_ICRA_ContCliAposRepasseM2, "  2.1.2 CONTESTAÇÕES DE CLIENTES APÓS REPASSE - M-2"},
        {""+CD_ICRA_ContCliAposRepasseM3, ""+SORT_ICRA_ContCliAposRepasseM3, "  2.1.3 CONTESTAÇÕES DE CLIENTES APÓS REPASSE - M-3"},
        {""+CD_ICRA_ContCliAposRepasseM4, ""+SORT_ICRA_ContCliAposRepasseM4, "  2.1.4 CONTESTAÇÕES DE CLIENTES APÓS REPASSE - M-4"},
        {""+CD_ICRA_ContCliAposRepasseM5, ""+SORT_ICRA_ContCliAposRepasseM5, "  2.1.5 CONTESTAÇÕES DE CLIENTES APÓS REPASSE - M-5"},
        {""+CD_ICRA_ContCliAposRepasseM6, ""+SORT_ICRA_ContCliAposRepasseM6, "  2.1.6 CONTESTAÇÕES DE CLIENTES APÓS REPASSE - MAIOR QUE M-5"},
        {""+CD_ICRA_RvsPgtoAposRepasse, ""+SORT_ICRA_RvsPgtoAposRepasse, "  2.2 REVERSÕES DE PAGAMENTO APÓS REPASSE"},
        {""+CD_ICRA_RvsPgtoAposRepasseM1, ""+SORT_ICRA_RvsPgtoAposRepasseM1, "  2.2.1 REVERSÕES DE PAGAMENTO APÓS REPASSE - M-1"},
        {""+CD_ICRA_RvsPgtoAposRepasseM2, ""+SORT_ICRA_RvsPgtoAposRepasseM2, "  2.2.2 REVERSÕES DE PAGAMENTO APÓS REPASSE - M-2"},
        {""+CD_ICRA_RvsPgtoAposRepasseM3, ""+SORT_ICRA_RvsPgtoAposRepasseM3, "  2.2.3 REVERSÕES DE PAGAMENTO APÓS REPASSE - M-3"},
        {""+CD_ICRA_RvsPgtoAposRepasseM4, ""+SORT_ICRA_RvsPgtoAposRepasseM4, "  2.2.4 REVERSÕES DE PAGAMENTO APÓS REPASSE - M-4"},
        {""+CD_ICRA_RvsPgtoAposRepasseM5, ""+SORT_ICRA_RvsPgtoAposRepasseM5, "  2.2.5 REVERSÕES DE PAGAMENTO APÓS REPASSE - M-5"},
        {""+CD_ICRA_RvsPgtoAposRepasseM6, ""+SORT_ICRA_RvsPgtoAposRepasseM6, "  2.2.6 REVERSÕES DE PAGAMENTO APÓS REPASSE - MAIOR QUE M-5"},
        {""+CD_ICRA_RmnSrvObjContrato, ""+SORT_ICRA_RmnSrvObjContrato, "  2.3 REMUNERAÇÃO DO SERVIÇO OBJETO DO CONTRATO"},
        {""+CD_ICRA_DetChmFaturadas, ""+SORT_ICRA_DetChmFaturadas, "  2.3.1 DETALHAMENTO DAS CHAMADAS FATURADAS"},
		{""+CD_ICRA_RmnSrvExtAdmCartoes, ""+SORT_ICRA_RmnSrvExtAdmCartoes, "  2.4 REMUNERAÇÃO DO SERVIÇO EXTRA DE ADM. DE CARTÕES (OPCIONAL)"},
        {""+CD_ICRA_RmnSrvExtCobranca, ""+SORT_ICRA_RmnSrvExtCobranca, "  2.5 REMUNERAÇÃO DO SERVIÇO EXTRA DE COBRANÇA ESPECIAL (OPCIONAL)"},
        {""+CD_ICRA_ACERTOS, ""+SORT_ICRA_ACERTOS, "3- TOTAL DOS ACERTOS"},
        {""+CD_ICRA_ActConcContrClaro, ""+SORT_ICRA_ActConcContrClaro, "  3.1 ACERTO DE CONCILIAÇÃO CONTRA CLARO"},
		{""+CD_ICRA_ActConcContrLD, ""+SORT_ICRA_ActConcContrLD, "  3.2 ACERTO DE CONCILIAÇÃO CONTRA PRESTADORA LD"},
        {""+CD_ICRA_PENALIDADES, ""+SORT_ICRA_PENALIDADES, "4-TOTAL DAS PENALIDADES"},
        {""+CD_ICRA_PRIContraClaro, ""+SORT_ICRA_PRIContraClaro, "  4.1 PENALIDADE POR REJEIÇÕES INDEVIDAS CONTRA CLARO"},
        {""+CD_ICRA_PRDContraLD, ""+SORT_ICRA_PRDContraLD, "  4.2 PENALIDADE POR REJEIÇÕES DEVIDAS CONTRA PRESTADORA LD"},
		{""+CD_ICRA_PCPContraClaro, ""+SORT_ICRA_PCPContraClaro, "  4.3 PENALIDADE POR CHAMADA PERDIDA CONTRA CLARO"},
        {""+CD_ICRA_PPDGContraClaro_SLA, ""+SORT_ICRA_PPDGContraClaro_SLA, "  4.4 PENALIDADE POR DESEMPENHO GERAL CONTRA CLARO (SLA)"},
        {""+CD_ICRA_JMAAtrasoRepasseClaro, ""+SORT_ICRA_JMAAtrasoRepasseClaro, "  4.5 MULTA/JUROS POR ATRASO NO REPASSE CONTRA CLARO"},
        {""+CD_ICRA_JMAPagamentoLD, ""+SORT_ICRA_JMAPagamentoLD, "  4.6 MULTA/JUROS POR ATRASO NO PAGAMENTO CONTRA PRESTADORA LD"},
        {""+CD_ICRA_ValorRepasse1234, ""+SORT_ICRA_ValorRepasse1234, "5- BASE DE CÁLCULO DO REPASSE (1 - 2 + 3 + 4 )"},
        {""+CD_ICRA_CPMFValorRepasse, ""+SORT_ICRA_CPMFValorRepasse, "6 - CPMF A DESCONTAR DO REPASSE"},
        {""+CD_ICRA_ValorRepassar56, ""+SORT_ICRA_ValorRepassar56, "7 - TOTAL DO VALOR A REPASSAR (5 -6)"}
    } ) );

    // Itens CoBilling Saldo de Lote = SL
    public static final int SORT_SL_TtlChmAceitas = 1;
    public static final int SORT_SL_RjtCrtInicial = 2;
    public static final int SORT_SL_RjtFaturamento = 3;
    public static final int SORT_SL_ChmFaturada = 4;
    public static final int SORT_SL_ChmArrecadada = 5;
    public static final int SORT_SL_RvsPagamento = 6;
    public static final int SORT_SL_ChmRepassada = 7;
    public static final int SORT_SL_ChmCntNaoArrecadada = 8;
    public static final int SORT_SL_ChmCntArrecadada = 9;
    public static final int SORT_SL_ChmCntRepassada = 10;
    public static final int SORT_SL_InadMais90d = 11;
    public static final int SORT_SL_InadMais180d = 12;
    public static final int SORT_SL_EmParcelamento = 13;
    public static final int SORT_SL_Parcelamento = 14;
    public static final int SORT_SL_FtrCancelada = 15;
    public static final int SORT_SL_AltVencimento = 16;
    public static final int SORT_SL_TtlChmRetornadas = 17;
    public static final int SORT_SL_TtlChmActNaoRetornadas = 18;

    public static final char CD_SL_TtlChmAceitas = 'X';
    public static final char CD_SL_RjtCrtInicial = 'C';
    public static final char CD_SL_RjtFaturamento = 'E';
    public static final char CD_SL_ChmRepassada = 'R';
    public static final char CD_SL_ChmFaturada = 'F';
    public static final char CD_SL_ChmArrecadada = 'A';
    public static final char CD_SL_RvsPagamento = 'D';
    public static final char CD_SL_ChmCntNaoArrecadada = 'J';
    public static final char CD_SL_ChmCntArrecadada = 'K';
    public static final char CD_SL_ChmCntRepassada = 'L';
    public static final char CD_SL_InadMais90d = 'H';
    public static final char CD_SL_InadMais180d = 'I';
    public static final char CD_SL_EmParcelamento = 'W';
    public static final char CD_SL_Parcelamento = 'P';
    public static final char CD_SL_FtrCancelada = 'B';
    public static final char CD_SL_AltVencimento = 'V';
    public static final char CD_SL_TtlChmRetornadas = 'Y';
    public static final char CD_SL_TtlChmActNaoRetornadas = 'Z';
    

    public static final Vector vSL = new Vector(Arrays.asList( new String[][] {
       		{""+CD_SL_TtlChmAceitas, 			""+SORT_SL_TtlChmAceitas, 			"TOTAL DE CHAMADAS ACEITAS"},
       		{""+CD_SL_RjtCrtInicial, 			""+SORT_SL_RjtCrtInicial, 			"  C  REJEITADO NA CRITICA INICIAL"},
       		{""+CD_SL_RjtFaturamento, 			""+SORT_SL_RjtFaturamento, 			"  E  REJEITADO NO FATURAMENTO"},
       		{""+CD_SL_ChmFaturada, 				""+SORT_SL_ChmFaturada, 			"  F  CHAMADA FATURADA"},
       		{""+CD_SL_ChmArrecadada, 			""+SORT_SL_ChmArrecadada, 			"  A  CHAMADA ARRECADADA"},
       		{""+CD_SL_RvsPagamento, 			""+SORT_SL_RvsPagamento, 			"  D  REVERSAO DE PAGAMENTO"},
       		{""+CD_SL_ChmRepassada, 			""+SORT_SL_ChmRepassada, 			"  R  CHAMADA REPASSADA"},
       		{""+CD_SL_ChmCntNaoArrecadada, 		""+SORT_SL_ChmCntNaoArrecadada, 	"  J  CHAMADA CONTESTADA NÃO ARRECADADA"},
       		{""+CD_SL_ChmCntArrecadada, 		""+SORT_SL_ChmCntArrecadada, 		"  K  CHAMADA CONTESTADA ARRECADADA"},
       		{""+CD_SL_ChmCntRepassada, 			""+SORT_SL_ChmCntRepassada, 		"  L  CHAMADA CONTESTADA REPASSADA"},
       		{""+CD_SL_InadMais90d, 				""+SORT_SL_InadMais90d, 			"  H  INADIMPLENTE HÁ MAIS DE 90 DIAS"},
       		{""+CD_SL_InadMais180d, 			""+SORT_SL_InadMais180d, 			"  I  INADIMPLENTE HÁ MAIS DE 180 DIAS"},
       		{""+CD_SL_EmParcelamento, 			""+SORT_SL_EmParcelamento, 			"  W  EM PARCELAMENTO"},
       		{""+CD_SL_Parcelamento, 			""+SORT_SL_Parcelamento, 			"  P  ARRECADADO VIA PARCELAMENTO"},
       		{""+CD_SL_FtrCancelada, 			""+SORT_SL_FtrCancelada, 			"  B  FATURA CANCELADA"},
       		{""+CD_SL_AltVencimento, 			""+SORT_SL_AltVencimento, 			"  V  ALTERAÇÃO DE VENCIMENTO"},
       		{""+CD_SL_TtlChmRetornadas, 		""+SORT_SL_TtlChmRetornadas, 		"TOTAL DE CHAMADAS RETORNADAS"},
       		{""+CD_SL_TtlChmActNaoRetornadas, 	""+SORT_SL_TtlChmActNaoRetornadas, 	"TOTAL DE CHAMADAS ACEITAS NÃO RETORNADAS (SALDO)"}
    } ) );

    public static final Vector vSLAssinatura = new Vector(Arrays.asList( new String[][] {
       		{""+CD_SL_TtlChmAceitas, 			""+SORT_SL_TtlChmAceitas, 			"TOTAL DE ASSINATURAS ENVIADAS(FATURADAS) "},
       		{""+CD_SL_ChmFaturada, 				""+SORT_SL_ChmFaturada, 			"  F  ASSINATURA FATURADA"},
       		{""+CD_SL_ChmArrecadada, 			""+SORT_SL_ChmArrecadada, 			"  A  ASSINATURA ARRECADADA"},
       		{""+CD_SL_RvsPagamento, 			""+SORT_SL_RvsPagamento, 			"  D  REVERSAO DE PAGAMENTO"},
       		{""+CD_SL_ChmRepassada, 			""+SORT_SL_ChmRepassada, 			"  R  ASSINATURA REPASSADA"},
       		{""+CD_SL_ChmCntNaoArrecadada, 		""+SORT_SL_ChmCntNaoArrecadada, 	"  J  ASSINATURA CONTESTADA NÃO ARRECADADA"},
       		{""+CD_SL_ChmCntArrecadada, 		""+SORT_SL_ChmCntArrecadada, 		"  K  ASSINATURA CONTESTADA ARRECADADA"},
       		{""+CD_SL_ChmCntRepassada, 			""+SORT_SL_ChmCntRepassada, 		"  L  ASSINATURA CONTESTADA REPASSADA"},
       		{""+CD_SL_InadMais90d, 				""+SORT_SL_InadMais90d, 			"  H  INADIMPLENTE HÁ MAIS DE 90 DIAS"},
       		{""+CD_SL_InadMais180d, 			""+SORT_SL_InadMais180d, 			"  I  INADIMPLENTE HÁ MAIS DE 180 DIAS"},
       		{""+CD_SL_EmParcelamento, 			""+SORT_SL_EmParcelamento, 			"  W  EM PARCELAMENTO"},
       		{""+CD_SL_Parcelamento, 			""+SORT_SL_Parcelamento, 			"  P  PARCELAMENTO"},
       		{""+CD_SL_FtrCancelada, 			""+SORT_SL_FtrCancelada, 			"  B  FATURA CANCELADA"},
       		{""+CD_SL_AltVencimento, 			""+SORT_SL_AltVencimento, 			"  V  ALTERAÇÃO DE VENCIMENTO"},
       		{""+CD_SL_TtlChmRetornadas, 		""+SORT_SL_TtlChmRetornadas, 		"TOTAL DE ASSINATURA RETORNADAS"},
       		
    } ) );

    
    public static final String[] buscaConfigSaldoLote(char codigo,String cdTipoArquivo) {
	    String[] tRELATORIO = null;
	    String[] tTemp = null;
	   
	    if(cdTipoArquivo.equals("5") || cdTipoArquivo.equals("555")  ){
	    for(int i=0;i<CobillingConstants.vSL.size();i++)
	    {
	    	tTemp = (String[]) CobillingConstants.vSL.elementAt(i);
	    	if (codigo == tTemp[0].charAt(0)) {
	    		tRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tRELATORIO;
	    }else{
	    	for(int i=0;i<CobillingConstants.vSLAssinatura.size();i++)
		    {
		    	tTemp = (String[]) CobillingConstants.vSLAssinatura.elementAt(i);
		    	if (codigo == tTemp[0].charAt(0)) {
		    		tRELATORIO = tTemp;
		    		break;
		    	}
		    }	
	    	return tRELATORIO;
	    }
	   }

    /**
     * Max of elements of a list
     */
    public static final int MAX_ITENS_FINANCEIROS = 48+1;
    
    // Relatorios CAP, CAR, Perdas e Fraudes, Saldo de Lote, Consolidado
    public static final Vector vRELATORIOS = new Vector(Arrays.asList( new String[][] { 
       		{""+1, "R1", "R1 CAP OPERADORAS POS PAGO",	"24"	},
       		{""+2, "R2", "R2 CAR CLIENTES POS PAGO",	"41"	},
       		{""+3, "R3", "R3 QUESTIONAMENTOS POS PAGO",	"24"	},
       		{""+4, "R4", "R4 DISPUTAS POS PAGO",		"10"	},
       		{""+5, "R5", "R5 PERDAS E FRAUDES POS PAGO","23"	},
       		{""+6, "R6", "R6 SALDO DE LOTE POS PAGO",	"48"	},
       		{""+7, "R7", "R7 CONSOLIDADO POS PAGO",		"35"	},
       		{""+8, "R1P", "R1 CAP OPERADORAS PRE PAGO",	"24"	},
       		{""+9, "R2P", "R2 CONTESTACAO PRE PAGO",	"14"	},
       		{""+10, "R3P", "R3 DISPUTA PRE PAGO",		"09"	},
       		{""+11, "R4P", "R4 PERDAS E FRAUDES PRE PAGO","23"	},
       		{""+12, "R5P", "R5 CONSOLIDADO PRE PAGO",	"27"	}
    } ) );

    public static final String[] buscaConfigRelatorio(String codigo) {
	    String[] tRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vRELATORIOS.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vRELATORIOS.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[1])) {
	    		tRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tRELATORIO;
    }

    public static final Vector vR1CAPOPERADORAS = new Vector(Arrays.asList( new String[][] { 
       		{"01", "R1", "(a) Aceito                          	", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"02", "R1", "(b) Rejeitado (período)             	", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"03", "R1", "(c) Rejeitado (períodos anteriores) 	", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"04", "R1", "(d) Rejeitado 30d (período anterior) 	", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"05", "R1", "    % Aceito (d/a)                  	", "PE_RETENCAO",		 "00", "" },
       		{"06", "R1", "(e) Faturável Inicial               	", "VL_BRUTO_CHAMADA",	 "01", "" },
       		{"07", "R1", "(f) Desconto Contratual             	", "VL_BRUTO_CHAMADA",	 "00", "" },
       		{"08", "R1", "(g) Repasse                         	", "VL_BRUTO_CHAMADA",	 "00", "" },
       		{"09", "R1", "    Parcela 60% M-2                 	", "VL_BRUTO_CHAMADA",	 "01", "" },
       		{"10", "R1", "    Parcela 40% M-3                 	", "VL_BRUTO_CHAMADA",	 "00", "" },
       		{"11", "R1", "(h) Valor Bruto a Repassar          	", "VL_BRUTO_CHAMADA",	 "00", "" },
       		{"12", "R1", "    Total Deduções                  	", "VL_BRUTO_CHAMADA",	 "01", "" },
       		{"13", "R1", "    Total Acertos                   	", "VL_BRUTO_CHAMADA",	 "00", "" },
       		{"14", "R1", "    Total Penalidades               	", "VL_BRUTO_CHAMADA",	 "00", "" },
       		{"15", "R1", "    CPMF a Descontar                	", "VL_BRUTO_CHAMADA",	 "00", "" },
       		{"16", "R1", "(i) Total a Descontar               	", "VL_BRUTO_CHAMADA",	 "00", "" },
       		{"17", "R1", "(j) Valor Líquido a Repassar(h+i)   	", "VL_BRUTO_CHAMADA",	 "01", "" },
       		{"18", "R1", "(l) Repasse Liquidado               	", "VL_BRUTO_CHAMADA",	 "01", "" },
       		{"19", "R1", "    Saldo a Repassar                	", "VL_BRUTO_CHAMADA",	 "00", "" },
       		{"20", "R1", "(m) Penalidade por Atraso contra Claro", "VL_BRUTO_CHAMADA",	 "01", "" },
       		{"21", "R1", "(n) Penalidade por Atraso contra LD 	", "VL_BRUTO_CHAMADA",	 "00", "" },
       		{"22", "R1", "(o) Total Penalidade por Atraso     	", "VL_BRUTO_CHAMADA",	 "00", "" },
       		{"23", "R1", "(p) Provisão CAP Operadoras         	", "VL_BRUTO_CHAMADA",	 "01", "" },
       		{"24", "R1", "(q) Saldo CAP Operadoras            	", "VL_BRUTO_CHAMADA",	 "01", "" }
    } ) );
    
    public static final String[] buscaConfigItensR1CAPOPERADORAS(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vR1CAPOPERADORAS.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vR1CAPOPERADORAS.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) { 
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final Vector vR2CARCLIENTES = new Vector(Arrays.asList( new String[][] { 
       		{"01", "R2", "    Quantidade Chamadas Faturadas   	  ", "QT_CDRS",			   "00", "" },
       		{"02", "R2", "(a) Receita Serviços Líquida        	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"03", "R2", "(b) Impostos s/ Receita Serviços    	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"04", "R2", "(c) Receita Serviços Bruta          	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"05", "R2", "(d) Receita LD A Faturar Bruta      	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"06", "R2", "(e) Receita LD Faturada Líquida     	  ", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"07", "R2", "(f) Impostos s/ Receita LD Faturada 	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"08", "R2", "(g) Receita LD Faturada Bruta       	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"09", "R2", "    Faturado Bruto Retorno          	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"10", "R2", "    Faturado Bruto Fiscal           	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"11", "R2", "    Batimento Billing x Retorno     	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"12", "R2", "    Batimento Billing x Fiscal      	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"13", "R2", "(h) Ajustes Concedidos              	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"14", "R2", "    % Receita LD Faturada Bruta     	  ", "PE_RETENCAO",		   "00", "" },
       		{"15", "R2", "(i) Receita LD Faturada Bruta ex-Ajustes", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"16", "R2", "(j) Fraude Pós-Faturamento           	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"17", "R2", "    % Receita LD Faturada Bruta     	  ", "PE_RETENCAO",		   "00", "" },
       		{"18", "R2", "    Arrecadado M                    	  ", "VL_BRUTO_CHAMADA",   "02", "Arrecadação (Estimativa)           " },
       		{"19", "R2", "    Arrecadado M-1                  	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"20", "R2", "    Arrecadado M-2                      ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"21", "R2", "    Arrecadado M-3                  	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"22", "R2", "    Arrecadado > M-3                	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"23", "R2", "(l) Receita LD Arrecadada Bruta     	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"24", "R2", "(m) Saldo a Receber                 	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"25", "R2", "(n) Inadimplência (Estimativa)      	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"26", "R2", "    % Receita LD Faturada Bruta         ", "PE_RETENCAO",		   "00", "" },
       		{"27", "R2", "(o) Provisão CAR Clientes (Unbilled)	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"28", "R2", "(p) Saldo CAR Clientes              	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"29", "R2", "    A vencer                        	  ", "VL_BRUTO_CHAMADA",   "02", "Mapto. Saldo a Receber (Estimativa)" },
       		{"30", "R2", "    Vencido 1-30 d                  	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"31", "R2", "    Vencido 31-60 d                 	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"32", "R2", "    Vencido 61-90 d                     ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"33", "R2", "    Vencido 91-120 d                	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"34", "R2", "    Vencido > 120 d                 	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"35", "R2", "    Estimativa Arrecadacao Vencido  	  ", "PE_RETENCAO",		   "02", "% Arrecadado/Faturado (Cfe Cobrança Claro)" },
       		{"36", "R2", "    Estimativa Arrecadacao Vencido M-1  ", "PE_RETENCAO",		   "00", "" },
       		{"37", "R2", "    Estimativa Arrecadacao Vencido M-2  ", "PE_RETENCAO",		   "00", "" },
       		{"38", "R2", "    Estimativa Arrecadacao Vencido M-3  ", "PE_RETENCAO",		   "00", "" },
       		{"39", "R2", "    Estimativa Arrecadacao Vencido > M-3", "PE_RETENCAO",		   "00", "" },
       		{"40", "R2", "    Total Estimativa Arrecadacao Vencido", "PE_RETENCAO",		   "00", "" },
       		{"41", "R2", "    % Inadimplencia Total               ", "PE_RETENCAO",   	   "00", "" }
    } ) );
    
    public static final String[] buscaConfigItensR2CARCLIENTES(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vR2CARCLIENTES.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vR2CARCLIENTES.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final Vector vR3QUESTIONAMENTO = new Vector(Arrays.asList( new String[][] { 
       		{"01", "R3", "    Qte Ch Rejeitadas                   ", "QT_CDRS",			   "00", "" },
       		{"02", "R3", "(a) Rejeitado                       	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"03", "R3", "    Qte Ch Questionadas                 ", "QT_CDRS",			   "01", "" },
       		{"04", "R3", "(b) Questionado                     	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"05", "R3", "    Qte Ch Confirmadas              	  ", "QT_CDRS",			   "01", "" },
       		{"06", "R3", "(c) Confirmado                      	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"07", "R3", "    Qte Ch Confirmadas Procedente   	  ", "QT_CDRS",			   "01", "" },
       		{"08", "R3", "    Min Ch Confirmadas Procedente   	  ", "QT_CDRS",			   "00", "" },
       		{"09", "R3", "(d) Confirmado Procedente           	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"10", "R3", "    Qte Ch Faturáveis Incluídas     	  ", "QT_CDRS",			   "01", "" },
       		{"11", "R3", "    Min Ch Faturáveis Incluídas     	  ", "QT_CDRS",			   "00", "" },
       		{"12", "R3", "(e) Rec. LD Fat. Líq. (outros períodos) ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"13", "R3", "    Qte Ch Perdida N-Incluída       	  ", "QT_CDRS",			   "01", "" },
       		{"14", "R3", "    Min Ch Perdida N-Incluída       	  ", "QT_CDRS",			   "00", "" },
       		{"15", "R3", "(f) Perda P6 Ch N-Incluída              ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"16", "R3", "(g) Acerto de Inclusão a favor LD    	  ", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"17", "R3", "(h) Penalidade por Rejeições Indevidas  ", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"18", "R3", "(i) Penalidade por Rejeições Devidas	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"19", "R3", "(j) Total Penalidade por Rejeições  	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"20", "R3", "    Qte Ch N-Confirmadas                ", "QT_CDRS",			   "01", "" },
       		{"21", "R3", "    Min Ch N-Confirmadas            	  ", "QT_CDRS",			   "00", "" },
       		{"22", "R3", "(l) Saldo Questionamentos Iniciados 	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"23", "R3", "(m) Penalidade Potencial (Valor Máximo) ", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"24", "R3", "(n) Provisão p/ Penalidade (Question.)  ", "VL_LIQUIDO_CHAMADA", "01", "" }
    } ) );
    
    public static final String[] buscaConfigItensR3QUESTIONAMENTO(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vR3QUESTIONAMENTO.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vR3QUESTIONAMENTO.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final String CD_CLARO = new String("CLARO");
    public static final String CD_OPERADORA_LD = new String("OPERADORA_LD");

    public static final Vector vPENALIDADE_CLARO = new Vector(Arrays.asList( new String[][] {
    		{"01", "", "PENALIDADES CLARO", "CLARO"},
    		{"02", "", "Rejeição por Falha", "CLARO"},
    		{"03", "104", "Assinante A não é da operadora contratada", "CLARO"},
    		{"04", "144", "Código de Área de A é inválido", "CLARO"},
    		{"05", "146", "Código de Área de B é inválido", "CLARO"},
    		{"06", "145", "Chamada não apropriável à Filial", "CLARO"},
    		{"07", "106", "Irregularidade no cadastro de assinantes", "CLARO"},
    		{"08", "105", "Assinante A inválido", "CLARO"},
    		{"09", "121", "Assinante B inválido", "CLARO"},
    		{"10", "100", "Código de natureza inválido", "CLARO"},
    		{"11", "102", "EOT da prestadora de origem inválido", "CLARO"},
    		{"12", "103", "EOT da prestadora de destino inválido", "CLARO"},
    		{"13", "117", "CNL inválido assinante A", "CLARO"},
    		{"14", "122", "CNL inválido assinante B", "CLARO"},
    		{"15", "120", "Código do País inválido", "CLARO"},
    		{"16", "131", "Código nacional da área visitada inválido", "CLARO"},
    		{"17", "101", "Natureza conflitante com o tipo de terminal de origem", "CLARO"},
    		{"18", "118", "CNL conflitante assinante A", "CLARO"},
    		{"19", "123", "CNL conflitante assinante B", "CLARO"},
    		{"20", "124", "Data da chamada inválida", "CLARO"},
    		{"21", "147", "Data anterior a data início da operação", "CLARO"},
    		{"22", "126", "Hora início da chamada inválida", "CLARO"},
    		{"23", "127", "Duração real da chamada inválida", "CLARO"},
    		{"24", "128", "Duração tarifada da chamada inválida", "CLARO"},
    		{"25", "129", "Grupo Horário inválido", "CLARO"},
    		{"26", "130", "Degrau inválido", "CLARO"},
    		{"27", "132", "Valor inválido", "CLARO"},
    		{"28", "142", "Código nacional da área visitada conflitante c/ código de natureza", "CLARO"},
    		{"29", "119", "CSP inválido", "CLARO"},
    		{"30", "149", "Cenário não tratado por este processo", "CLARO"},
    		{"31", "148", "Faixa de serviço especial não cadastrada", "CLARO"},
    		{"32", "138", "Tipo de registro inválido", "CLARO"},
    		{"33", "133", "Código de refaturamento inválido", "CLARO"},
    		{"34", "137", "Chamada não existe para refaturar", "CLARO"},
    		{"35", "136", "Chamada não contestada para refaturar", "CLARO"},
    		{"36", "140", "Chamada Local", "CLARO"},
    		{"37", "141", "Chamada duplicada", "CLARO"},
    		{"38", "", "Subtotal", "CLARO"},
    		{"39", "", "Rejeição por Regra", "CLARO"},
    		{"40", "109", "Terminal Pré-pago", "CLARO"},
    		{"41", "143", "Telefone com retenção de impostos", "CLARO"},
    		{"42", "125", "Decurso de prazo contratual", "CLARO"},
    		{"43", "150", "Decurso de prazo regulatório ", "CLARO"},
    		{"44", "134", "Retorno de apuração fora do prazo", "CLARO"},
    		{"45", "135", "Chamada contestada enviada acima do nr. de vezes permitido", "CLARO"},
    		{"46", "107", "Terminal bloqueado / suspenso no momento das chamadas", "CLARO"},
    		{"47", "", "Subtotal", "CLARO"},
    		{"48", "", "Total PENALIDADES CLARO", "CLARO"},
    		{"49", "372", "Número A MSRN", "LD"},
    		{"50", "110", "Terminal da própria Operadora Contratada", "CLARO"}
    } ) );

	public static final Vector vPENALIDADE_LD = new Vector(Arrays.asList( new String[][] {
    		{"01", "", "PENALIDADES PRESTADORA LD", "LD"},
    		{"02", "", "Rejeição por Falha", "LD"},
    		{"03", "104", "Assinante A não é da operadora contratada", "LD"},
    		{"04", "144", "Código de Área de A é inválido", "LD"},
    		{"05", "146", "Código de Área de B é inválido", "LD"},
    		{"06", "145", "Chamada não apropriável à Filial", "LD"},
    		{"07", "106", "Irregularidade no cadastro de assinantes", "LD"},
    		{"08", "105", "Assinante A inválido", "LD"},
    		{"09", "121", "Assinante B inválido", "LD"},
    		{"10", "100", "Código de natureza inválido", "LD"},
    		{"11", "102", "EOT da prestadora de origem inválido", "LD"},
    		{"12", "103", "EOT da prestadora de destino inválido", "LD"},
    		{"13", "117", "CNL inválido assinante A", "LD"},
    		{"14", "122", "CNL inválido assinante B", "LD"},
    		{"15", "120", "Código do País inválido", "LD"},
    		{"16", "131", "Código nacional da área visitada inválido", "LD"},
    		{"17", "101", "Natureza conflitante com o tipo de terminal de origem", "LD"},
    		{"18", "118", "CNL conflitante assinante A", "LD"},
    		{"19", "123", "CNL conflitante assinante B", "LD"},
    		{"20", "124", "Data da chamada inválida", "LD"},
    		{"21", "147", "Data anterior a data início da operação", "LD"},
    		{"22", "126", "Hora início da chamada inválida", "LD"},
    		{"23", "127", "Duração real da chamada inválida", "LD"},
    		{"24", "128", "Duração tarifada da chamada inválida", "LD"},
    		{"25", "129", "Grupo Horário inválido", "LD"},
    		{"26", "130", "Degrau inválido", "LD"},
    		{"27", "132", "Valor inválido", "LD"},
    		{"28", "142", "Código nacional da área visitada conflitante c/ código de natureza", "LD"},
    		{"29", "119", "CSP inválido", "LD"},
    		{"30", "149", "Cenário não tratado por este processo", "LD"},
    		{"31", "148", "Faixa de serviço especial não cadastrada", "LD"},
    		{"32", "138", "Tipo de registro inválido", "LD"},
    		{"33", "133", "Código de refaturamento inválido", "LD"},
    		{"34", "137", "Chamada não existe para refaturar", "LD"},
    		{"35", "136", "Chamada não contestada para refaturar", "LD"},
    		{"36", "140", "Chamada Local", "LD"},
    		{"37", "141", "Chamada duplicada", "LD"},
    		{"38", "", "Subtotal", "LD"},
    		{"39", "", "Rejeição por Regra", "LD"},
    		{"40", "109", "Terminal Pré-pago", "LD"},
    		{"41", "143", "Telefone com retenção de impostos", "LD"},
    		{"42", "125", "Decurso de prazo contratual", "LD"},
    		{"43", "150", "Decurso de prazo regulatório ", "LD"},
    		{"44", "134", "Retorno de apuração fora do prazo", "LD"},
    		{"45", "135", "Chamada contestada enviada acima do nr. de vezes permitido", "LD"},
    		{"46", "107", "Terminal bloqueado / suspenso no momento das chamadas", "LD"},
    		{"47", "", "Subtotal", "LD"},
    		{"48", "", "Total PENALIDADES PRESTADORA LD", "LD"},
    		{"49", "372", "Número A MSRN", "LD"},
    		{"50", "110", "Terminal da própria Operadora Contratada", "LD"},
    } ) );

    public static final Vector vPENALIDADE_NAO_APLICAVEL = new Vector(Arrays.asList( new String[][] {
    		{"01", "", "PENALIDADE NÃo APLICÁVEL", "Q"},
    		{"02", "", "Rejeição por Regra", "Q"},
    		{"40", "109", "Terminal Pré-pago", "Q"},
    		{"50", "110", "Terminal da própria Operadora Contratada", "Q"},
    		{"41", "143", "Telefone com retenção de impostos", "Q"},
    		{"43", "150", "Decurso de prazo regulatório ", "Q"},
    		{"51", "373", "Cliente licitado", "Q"},
    		{"05", "", "Subtotal", "Q"},
    		{"06", "", "Total p/ penalidades não aplicáveis", "Q"}
    } ) );

	
	
    public static final String[] buscaConfigItensPENALIDADE_CLARO(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vPENALIDADE_CLARO.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vPENALIDADE_CLARO.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }
    
    public static final String[] buscaConfigItensPENALIDADE_LD(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vPENALIDADE_LD.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vPENALIDADE_LD.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final String[] buscaConfigItensPENALIDADE_NAO_APLICAVEL(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vPENALIDADE_NAO_APLICAVEL.size();i++)
	    {
	    	tTemp = (String[]) CobillingConstants.vPENALIDADE_NAO_APLICAVEL.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }    
    
    public static final String[] buscaConfigItens(String codigo, String inResultadoAnalise) {    	
    	if( "P".equalsIgnoreCase( inResultadoAnalise ) ){
    		return buscaConfigItensPENALIDADE_CLARO(codigo);
    	}else if( "N".equalsIgnoreCase( inResultadoAnalise ) ){
    		return buscaConfigItensPENALIDADE_LD(codigo);
    	}
    	return buscaConfigItensPENALIDADE_NAO_APLICAVEL(codigo);    	     	
    }    
    
    public static final Vector vR4DISPUTA = new Vector(Arrays.asList( new String[][] {
       		{"01", "R4", "(a) Valor Contestado                    ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"02", "R4", "(b) Valor Proposto                  	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"03", "R4", "(c) Valor Acordado                  	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"04", "R4", "(d) Dif. Acordo x Contestado        	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"05", "R4", "(e) Dif. Acordo x Proposto          	  ", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"06", "R4", "(f) Acerto de Conciliação a favor CLARO ", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"07", "R4", "(g) Acerto de Conciliação a favor LD	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"08", "R4", "(h) Total Acerto de Conciliação     	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"09", "R4", "(i) Saldo Disputas Iniciadas        	  ", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"10", "R4", "(j) Provisão p/ Acerto (Disputas)   	  ", "VL_LIQUIDO_CHAMADA", "00", "" }
    } ) );
    
    public static final String[] buscaConfigItensR4DISPUTA(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vR4DISPUTA.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vR4DISPUTA.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final Vector vR5PERDASFRAUDES = new Vector(Arrays.asList( new String[][] { 
       		{"01", "R5", "(a) P1 Perdida Inicial              	  ", "VL_LIQUIDO_CHAMADA",	"00", "" },
       		{"02", "R5", "(b) P2 Descartada                   	  ", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"03", "R5", "(c) P3 Retirada                     	  ", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"04", "R5", "(d) Perdas P1-P3 (Rejeição)         	  ", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"05", "R5", "    % Receita LD Faturável Bruta    	  ", "PE_RETENCAO", 		"00", "" },
       		{"06", "R5", "(e) P4 Abortada   	              	  ", "VL_BRUTO_CHAMADA", 	"01", "" },
       		{"07", "R5", "(f) P5 N-Reciclada                  	  ", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"08", "R5", "(g) P6 N-Incluída                   	  ", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"09", "R5", "(h) P7 Perda N-ID                   	  ", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"10", "R5", "(i) Perdas P4-P7 (Faturamento)       	  ", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"11", "R5", "    % Receita LD Faturável Bruta    	  ", "PE_RETENCAO", 		"00", "" },
       		{"12", "R5", "(j) Perdas de Processamento (d+i)    	  ", "VL_BRUTO_CHAMADA", 	"01", "" },
       		{"13", "R5", "    % Receita LD Faturável Bruta    	  ", "PE_RETENCAO", 		"00", "" },
       		{"14", "R5", "(l) X1 Excluído Inicial             	  ", "VL_LIQUIDO_CHAMADA", 	"01", "" },
       		{"15", "R5", "(m) X2 Excluído Billing                 ", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"16", "R5", "(n) Fraude Pré-Faturamento           	  ", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"17", "R5", "    % Receita LD Faturável Bruta    	  ", "PE_RETENCAO", 		"00", "" },
       		{"18", "R5", "(o) X3 Excluído a Vencer            	  ", "VL_BRUTO_CHAMADA", 	"01", "" },
       		{"19", "R5", "(p) X4 Excluído Vencido             	  ", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"20", "R5", "(q) Fraude Pós-Faturamento              ", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"21", "R5", "    % Receita LD Faturável Bruta    	  ", "PE_RETENCAO", 		"00", "" },
       		{"22", "R5", "(r) Fraudes (n+q)                   	  ", "VL_BRUTO_CHAMADA", 	"01", "" },
       		{"23", "R5", "    % Receita LD Faturável Bruta    	  ", "PE_RETENCAO", 		"00", "" }
    } ) );
    
    public static final String[] buscaConfigItensR5PERDASFRAUDES(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vR5PERDASFRAUDES.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vR5PERDASFRAUDES.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final Vector vR6SALDODELOTE = new Vector(Arrays.asList( new String[][] { 
       		{"01", "R6", "(a) Aceito                          	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"02", "R6", "    C1 Critica Inicial              	  ", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"03", "R6", "    C2 Critica Billing              	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"04", "R6", "(b) Rejeitado                       	  ", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"05", "R6", "    % Aceito (b/a)                  	  ", "PE_RETENCAO",        "00", "" },
       		{"06", "R6", "(c) Faturável Inicial               	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"07", "R6", "(d) Faturável Incluído              	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"08", "R6", "(e) Faturável (c+d)                 	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"09", "R6", "    X1 Excluído Inicial             	  ", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"10", "R6", "    X2 Excluído Billing              	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"11", "R6", "(f) Fraude Pré-Faturamento          	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"12", "R6", "    % Faturável (f/e)                	  ", "PE_RETENCAO",        "00", "" },
       		{"13", "R6", "    P1 Inicial                      	  ", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"14", "R6", "    P2 Descartada                   	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"15", "R6", "    P3 Retirada                         ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"16", "R6", "    P4 Abortada                   	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"17", "R6", "    P5 N-Reciclada                   	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"18", "R6", "    P6 N-Incluída                   	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"19", "R6", "    P7 Perda N-ID                   	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"20", "R6", "(g) Perdas de Processamento             ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"21", "R6", "    % Faturável (g/e)               	  ", "PE_RETENCAO",        "00", "" },
       		{"22", "R6", "    Encaminhada                     	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"23", "R6", "    Incluída                        	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"24", "R6", "    Alocada                         	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"25", "R6", "    A Reciclar                      	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"26", "R6", "    A Recuperar                     	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"27", "R6", "(h) A Faturar                       	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"28", "R6", "    Faturado Encaminhado            	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"29", "R6", "    Faturado Incluído               	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"30", "R6", "    Faturado RR                     	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"31", "R6", "(i) Faturado                        	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"32", "R6", "(j) Faturável Final                 	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"33", "R6", "(l) Perda Contratual D+90d (i-j)    	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"34", "R6", "    % Faturável Final (l/j)         	  ", "PE_RETENCAO",        "00", "" },
       		{"35", "R6", "(m) Ajustes Concedidos              	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"36", "R6", "    % Faturado (m/i)                	  ", "PE_RETENCAO",        "00", "" },
       		{"37", "R6", "    X3 Excluído a Vencer            	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"38", "R6", "    X4 Excluído Vencido             	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"39", "R6", "(n) Fraude Pós-Faturamento          	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"40", "R6", "    % Faturado (n/i)                	  ", "PE_RETENCAO",        "00", "" },
       		{"41", "R6", "(o) Arrecadado                      	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"42", "R6", "(p) A Receber                       	  ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"43", "R6", "(q) Inadimplência (Estimativa)      	  ", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"44", "R6", "    % Faturado (q/i)                	  ", "PE_RETENCAO",        "00", "" },
       		{"45", "R6", "(r) Perdas+Fraudes+Inad.(g+f+n-q)       ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"46", "R6", "    % Faturável (r/e)                	  ", "PE_RETENCAO",        "00", "" },
       		{"47", "R6", "    Dif. N-Localizada D+90d             ", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"48", "R6", "    Dif. N-Localizada D+180d         	  ", "VL_BRUTO_CHAMADA",   "00", "" }
    } ) );
    
    public static final String[] buscaConfigItensR6SALDODELOTE(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vR6SALDODELOTE.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vR6SALDODELOTE.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) { 
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final Vector vR7CONSOLIDADO = new Vector(Arrays.asList( new String[][] { 
       		{"01", "R7", "    Receita LD Faturável Líquida (Período)		", "VL_LIQUIDO_CHAMADA", "02", "P&L                      " },
       		{"02", "R7", "    Receita LD Faturável Líquida (outros períodos)", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"03", "R7", "    Impostos s/ Receita LD Faturável				", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"04", "R7", "(a) Receita LD Faturável Bruta      	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"05", "R7", "(b) Repasse Devido                  	  			", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"06", "R7", "(c) Desconto Claro                  	  			", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"07", "R7", "    Receita Serviços Líquida        	  			", "VL_LIQUIDO_CHAMADA", "01", "" },
       		{"08", "R7", "    Impostos s/ Receita Serviços    	  			", "VL_LIQUIDO_CHAMADA", "00", "" },
       		{"09", "R7", "(d) Receita Serviços Bruta          	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"10", "R7", "(e) Penalidade por Rejeições Devidas  			", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"11", "R7", "(f) Acertos contra LD               	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"12", "R7", "(g) Recebível Total Líquido (c+d-e-f) 	  		", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"13", "R7", "(h) Ajustes Concedidos			           	  	", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"14", "R7", "    Fraudes                            			", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"15", "R7", "    Inadimplência (Estimativa)          			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"16", "R7", "    Perdas de Processamento          	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"17", "R7", "    Penalidade por Rejeições Indevidas 			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"18", "R7", "(i) Custo Operacional               	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"19", "R7", "(j) Acertos contra Claro            	  			", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"20", "R7", "(l) Custo Total (h+i+j)                 			", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"21", "R7", "(m) Resultado (g+l)                 	  			", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"22", "R7", "    Receita LD A Faturar Bruta      	  			", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"23", "R7", "    Receita LD Faturada Bruta       	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"24", "R7", "    Receita LD Arrecadada Bruta     	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"25", "R7", "    Repasse Liquidado               	  			", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"26", "R7", "    Penalidade por Atraso contra Claro 			", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"27", "R7", "    Penalidade por Atraso contra LD 	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"28", "R7", "    Provisão CAP Operadoras         	  			", "VL_BRUTO_CHAMADA",   "02", "BS                      " },
       		{"29", "R7", "    Provisão CAR Clientes           	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"30", "R7", "    Provisão p/ Penalidade (Questionamentos)		", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"31", "R7", "    Provisão p/ Acerto (Disputas)   	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"32", "R7", "    Saldo CAP Operadoras            	  			", "VL_BRUTO_CHAMADA",   "01", "" },
       		{"33", "R7", "    Saldo CAR Clientes              	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"34", "R7", "    Saldo Questionamentos Iniciados 	  			", "VL_BRUTO_CHAMADA",   "00", "" },
       		{"35", "R7", "    Saldo Disputas Iniciadas        	  			", "VL_BRUTO_CHAMADA",   "00", "" }
    } ) );
    
    public static final String[] buscaConfigItensR7CONSOLIDADO(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vR7CONSOLIDADO.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vR7CONSOLIDADO.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final Vector vF1PRECAPOPERADORAS = new Vector(Arrays.asList( new String[][] { 
       		{"01", "R1P", "    Total Chamadas Apuradas                ", "QT_CDRS", 		 "00", "" },
       		{"02", "R1P", "    Total de Créditos Queimados         	  ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"03", "R1P", "    Total de Créditos Inválidos         	  ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"04", "R1P", "    Total Cursado ACB                   	  ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"05", "R1P", "    Apurado Mês Anterior                	  ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"06", "R1P", "    Apurado Outros Meses                   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"07", "R1P", "(a) Valor Bruto Apurado		              ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"25", "R1P", "    ICMS Não Repassado                	  ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"08", "R1P", "    Receita Serviço Bruta               	  ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"09", "R1P", "    Créditos Autorizados                   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"10", "R1P", "    Créditos Anatel 226                    ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"11", "R1P", "(b) Total Deduções                         ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"12", "R1P", "(c) Total das Penalidades               	  ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"13", "R1P", "(d) Total Acertos                       	  ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"14", "R1P", "(e) CPMF a Descontar                    	  ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"15", "R1P", "(f) ICMS a Descontar                    	  ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"16", "R1P", "(g) ICMS a Repassar                        ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"17", "R1P", "(h) Valor Final a Repassar (a+b+c+d+e+f+g) ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"18", "R1P", "(i) Repasse Liquidado                   	  ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"19", "R1P", "(j) Saldo a Repassar                       ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"20", "R1P", "    Multas / Juros Atraso Pgto contra Claro", "VL_BRUTO_CHAMADA", "01", "" },
       		{"21", "R1P", "    Multas / Juros Atraso Pgto contra LD   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"22", "R1P", "(l) Total Multas / Juros Atraso Pgto    	  ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"23", "R1P", "(m) Provisão CAP Operadoras             	  ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"24", "R1P", "(n) Saldo CAP Operadoras                	  ", "VL_BRUTO_CHAMADA", "01", "" }
    } ) );
    
    public static final String[] buscaConfigItensF1PRECAPOPERADORAS(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vF1PRECAPOPERADORAS.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vF1PRECAPOPERADORAS.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final Vector vF2PRECONTESTAR = new Vector(Arrays.asList( new String[][] { 
       		{"01", "R2P", "(a) Valor do Pleito                         ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"02", "R2P", "(b) Valor Proposto                      	   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"03", "R2P", "    Valor Ch Perdidas                   	   ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"04", "R2P", "    Dif ch Erro de Tarifa               	   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"05", "R2P", "(c) Valor Defeito                       	   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"06", "R2P", "    Penalidade Min Perdidos contra Claro    ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"07", "R2P", "    Acerto de Conciliação contra Claro  	   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"08", "R2P", "    Acerto de Conciliação contra LD     	   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"09", "R2P", "(d) Valor Acordado                          ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"10", "R2P", "(e) Dif (Acordado - Pleito)                 ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"11", "R2P", "(f) Dif (Acordado - Proposto)               ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"12", "R2P", "(g) Dif (Acordado - Defeito)                ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"13", "R2P", "(h) Saldo Penalidade Potenciais         	   ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"14", "R2P", "(i) Provisão para Penalidades (Min Perdidos)", "VL_BRUTO_CHAMADA", "01", "" }
    } ) );
    
    public static final String[] buscaConfigItensF2PRECONTESTAR(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vF2PRECONTESTAR.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vF2PRECONTESTAR.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final Vector vF3PREDISPUTAR = new Vector(Arrays.asList( new String[][] { 
       		{"01", "R3P", "(a) Valor do Pleito                   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"02", "R3P", "(b) Valor Proposto                    ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"03", "R3P", "    Acerto de Conciliação contra Claro", "VL_BRUTO_CHAMADA", "01", "" },
       		{"04", "R3P", "    Acerto de Conciliação contra LD   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"05", "R3P", "(c) Valor Acordado                    ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"06", "R3P", "(d) Dif (Acordado - Pleito)           ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"07", "R3P", "(e) Dif (Acordado - Proposto)         ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"08", "R3P", "(f) Saldo Disputas Iniciadas          ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"09", "R3P", "(g) Provisão para Acertos (Disputas)  ", "VL_BRUTO_CHAMADA", "01", "" }
    } ) );
    
    public static final String[] buscaConfigItensF3PREDISPUTAR(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vF3PREDISPUTAR.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vF3PREDISPUTAR.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final Vector vF4PERDASFRAUDES = new Vector(Arrays.asList( new String[][] { 
       		{"01", "R4P", "    Qte ch Cursadas Conciliadas                     				", "QT_CDRS",			"00", "" },
       		{"02", "R4P", "    Qte ch Defeito                                  				", "QT_CDRS", 			"00", "" },
       		{"03", "R4P", "    Qte ch Perdidas                                 				", "QT_CDRS", 			"00", "" },
       		{"04", "R4P", "    Minutos Cursados Conciliados                    				", "VL_BRUTO_CHAMADA", 	"01", "" },
       		{"05", "R4P", "    Minutos Perdidos                                				", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"06", "R4P", "    % Perda Contratual                                           ", "PE_RETENCAO", 		"00", "" },
       		{"07", "R4P", "(a) Valor ch Cursadas Conciliadas                   				", "VL_BRUTO_CHAMADA", 	"01", "" },
       		{"08", "R4P", "(b) Valor Defeito                                   				", "VL_BRUTO_CHAMADA", 	"00", "" },
       		{"09", "R4P", "    % Defeito                                       				", "PE_RETENCAO", 		"00", "" },
       		{"10", "R4P", "    P1 Perda Erro Tarifa                            				", "VL_BRUTO_CHAMADA",	"01", "" },
       		{"11", "R4P", "    P2 Perda Não Registrada                         				", "VL_BRUTO_CHAMADA",	"00", "" },
       		{"12", "R4P", "    P3 Perda Reg com Defeito \"Duração\" e \"Valor\"    			", "VL_BRUTO_CHAMADA",	"00", "" },
       		{"13", "R4P", "    P4 Perda Reg com outros Defeitos                             ", "VL_BRUTO_CHAMADA",	"00", "" },
       		{"14", "R4P", "(c) Perdas resp. LD                                 				", "VL_BRUTO_CHAMADA",	"00", "" },
       		{"15", "R4P", "(d) Fraude resp. LD                                 				", "VL_BRUTO_CHAMADA",	"01", "" },
       		{"16", "R4P", "(e) Perdas e Fraude resp. LD (c+d)                  				", "VL_BRUTO_CHAMADA",	"01", "" },
       		{"17", "R4P", "    % Perda                                         				", "PE_RETENCAO", 		"00", "" },
       		{"18", "R4P", "    P2 Penalidade Não Registrada                    				", "VL_BRUTO_CHAMADA",	"01", "" },
       		{"19", "R4P", "    P3 Penalidade Reg com Defeito \"Duração\" e \"Valor\"		", "VL_BRUTO_CHAMADA",	"00", "" },
       		{"20", "R4P", "    P4 Penalidade Reg com outros Defeitos                        ", "VL_BRUTO_CHAMADA",	"00", "" },
       		{"21", "R4P", "(f) Penalidade Min Perdidos por Falhas              				", "VL_BRUTO_CHAMADA",	"00", "" },
       		{"22", "R4P", "(g) Penalidade Min Perdidos por Fraude              				", "VL_BRUTO_CHAMADA",	"01", "" },
       		{"23", "R4P", "(h) Penalidades Min Perdidos (f+g)                  				", "VL_BRUTO_CHAMADA",	"01", "" }
    } ) );
    
    public static final String[] buscaConfigItensF4PERDASFRAUDES(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vF4PERDASFRAUDES.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vF4PERDASFRAUDES.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final Vector vF5CONSOLIDADO = new Vector(Arrays.asList( new String[][] { 
       		{"01", "R5P", "(a) Total Créditos Queimados                ", "VL_BRUTO_CHAMADA", "02", "P&L                      " },
       		{"02", "R5P", "(b) Valor Bruto Apurado                     ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"03", "R5P", "(c) ICMS não Repassado                      ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"04", "R5P", "(d) Diferença Apuração Créditos             ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"05", "R5P", "(e) Créditos Anatel 226	             	   ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"06", "R5P", "(f) Créditos Autorizados                    ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"07", "R5P", "(g) Diferença Ajuste Créditos        	   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"08", "R5P", "(h) Receita Serviços Líquida    	  		   ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"09", "R5P", "(i) Acertos contra LD          	  		   ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"10", "R5P", "(j) Recebível Total Líquido (d+g+h+i)   	   ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"11", "R5P", "(l) Penalidades por Minutos Perdidos        ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"12", "R5P", "(m) Acertos contra Claro 	  			   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"13", "R5P", "(n) Custo Total (l+m)			           ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"14", "R5P", "(o) Resultado (j-n)                         ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"15", "R5P", "    Valor Final a Repassar          		   ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"16", "R5P", "    Repasse Liquidado          	  		   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"17", "R5P", "    Perdas resp. LD 						   ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"18", "R5P", "    Fraude resp. LD              	  	   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"19", "R5P", "    Perdas e Fraude resp. LD                ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"20", "R5P", "    Penalidade por Atraso contra Claro      ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"21", "R5P", "    Penalidade por Atraso contra LD         ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"22", "R5P", "    Provisão CAP Operadoras         	       ", "VL_BRUTO_CHAMADA", "02", "BS                      " },
       		{"23", "R5P", "    Provisão para Penalidades (Min Perdidos)", "VL_BRUTO_CHAMADA", "00", "" },
       		{"24", "R5P", "    Provisão para Acertos (Disputas)		   ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"25", "R5P", "    Saldo CAP Operadoras            	  	   ", "VL_BRUTO_CHAMADA", "01", "" },
       		{"26", "R5P", "    Saldo Penalidades Potenciais            ", "VL_BRUTO_CHAMADA", "00", "" },
       		{"27", "R5P", "    Saldo Disputas Iniciadas        	  	   ", "VL_BRUTO_CHAMADA", "00", "" }
    } ) );
    
    public static final String[] buscaConfigItensF5CONSOLIDADO(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vF5CONSOLIDADO.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vF5CONSOLIDADO.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIO = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIO;
    }

    public static final Vector vITENSRELATORIORR = new Vector(Arrays.asList( new String[][] { 
	   		{"01", "RR", "Fator de Imposto e CS           	", "PE_FATOR_IMPOSTO",   "00", "" },
			{"02", "RR", "Desconto Contratual             	", "PE_RETENCAO",        "00", "" }
	} ) );

    public static final String[] buscaConfigItensRelatorioRR(String codigo) {
    	codigo = (codigo.length()>1?codigo:"0"+codigo);
	    String[] tITENSRELATORIORR = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vITENSRELATORIORR.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vITENSRELATORIORR.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) {
	    		tITENSRELATORIORR = tTemp;
	    		break;
	    	}
	    }
	    return tITENSRELATORIORR;
    }

    // Relatorio de Batimento Fiscal Constants
    public static final int CD_FISCAL_MOBILE = 10;
    public static final int CD_FISCAL_AMERICEL = 20;
    public static final int CD_FISCAL_ATL = 30;
    public static final int CD_FISCAL_BCP = 40;
    public static final int CD_FISCAL_BSE = 50;
    public static final int CD_FISCAL_TELET = 60;
    public static final int CD_FISCAL_TESS = 70;
        
    public static final Vector vFISCAL = new Vector(Arrays.asList( new String[][] { 
    		{""+CD_FISCAL_MOBILE, 	"Mobile", 	"CB_MB_C.",		"ds/mobile", 	"MOBILE",	".S02.", ""},
			{""+CD_FISCAL_AMERICEL, "Americel",	"CB_AM_C.",		"ds/americel",	"ENSEMBLE",	".S01.", "063"},
			{""+CD_FISCAL_ATL, 		"ATL",		"CB_ATL_C.",	"ds/atl",		"ENSEMBLE",	".S01.", "018"},
			{""+CD_FISCAL_BCP, 		"BCP",		"CB_BCP_C.",	"ds/bcp",		"ENSEMBLE",	".S01.", "014"},
			{""+CD_FISCAL_BSE, 		"BSE",		"CB_BSE_C.",	"ds/bse",		"ENSEMBLE",	".S01.", "022"},
			{""+CD_FISCAL_TELET, 	"Telet",	"CB_TLT_C.",	"ds/telet",		"ENSEMBLE",	".S01.", "006"}, 
			{""+CD_FISCAL_TESS, 	"Tess",		"CB_TS_C.",		"ds/tess",		"ENSEMBLE",	".S01.", "017"}
    } ) );
    
    public static final String[] vFISCAL_MOBILE = 
		new String[] {""+CD_FISCAL_MOBILE, 	"Mobile", "CB_MB_C.", "ds.cobillinggui", "MOBILE", ".S02.", ""};    

    public static final String[] buscaConfigFiscalBatimento(String empresa, String companyType) {
	    String[] tFISCAL = null;
	    String[] tTemp = null;
	    
	    if ( !"".equals(companyType) && companyType.equals("full") ) {
	    
	    	for(int i=0;i<CobillingConstants.vFISCAL.size();i++)
	    	{
	    		tTemp = (String[]) CobillingConstants.vFISCAL.elementAt(i);
	    		if (empresa.equalsIgnoreCase(tTemp[0])) {
	    			tFISCAL = tTemp;
	    			break;
	    		}
	    	}	    	
	    }else{
	    	tFISCAL = vFISCAL_MOBILE;
	    }
	    return tFISCAL;
    }    
    
    public static final String[] buscaConfigFiscal(String empresa) {
	    String[] tFISCAL = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vFISCAL.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vFISCAL.elementAt(i);
	    	if (empresa.equalsIgnoreCase(tTemp[0])) {
	    		tFISCAL = tTemp;
	    		break;
	    	}
	    }
	    return tFISCAL;
    }
    
    
    public static final String CD_STATUS_QUESTIONAMENTO_Q_REGISTRO_LD = "RGS";
    public static final String CD_STATUS_QUESTIONAMENTO_Q_PREPARACAO = "PRP";
    public static final String CD_STATUS_QUESTIONAMENTO_Q_ANALISE = "ANL";
    public static final String CD_STATUS_QUESTIONAMENTO_Q_APURACAO = "APR";
    public static final String CD_STATUS_QUESTIONAMENTO_Q_CONFIRMACAO = "CNF";
    public static final String CD_STATUS_QUESTIONAMENTO_Q_REGISTRO_CLARO = "RGC";
    public static final String CD_STATUS_QUESTIONAMENTO_Q_INCLUSAO = "INC";
    public static final String CD_STATUS_QUESTIONAMENTO_Q_CORRECAO = "CRR";
    public static final String CD_STATUS_QUESTIONAMENTO_Q_REPASSE = "RPS";
	
    public static final Vector vPROCESSO_QUESTIONAMENTO = new Vector(Arrays.asList( new String[][] { 
    		{CD_STATUS_QUESTIONAMENTO_Q_REGISTRO_LD, 	"Q_REGISTRO_LD", 	"Registro LD",		"1"},
			{CD_STATUS_QUESTIONAMENTO_Q_PREPARACAO, 		"Q_PREPARACAO",		"Preparação",		"2"},
			{CD_STATUS_QUESTIONAMENTO_Q_ANALISE, 		"Q_ANALISE",		"Análise",			"3"},
			{CD_STATUS_QUESTIONAMENTO_Q_CORRECAO, 		"Q_CORRECAO",		"Correção",			"4"},
			{CD_STATUS_QUESTIONAMENTO_Q_APURACAO, 		"Q_APURACAO",		"Apuração",			"5"},
			{CD_STATUS_QUESTIONAMENTO_Q_CONFIRMACAO, 	"Q_CONFIRMACAO",	"Confirmação",		"6"},
			{CD_STATUS_QUESTIONAMENTO_Q_REGISTRO_CLARO,	"Q_REGISTRO_CLARO",	"Registro Claro",	"7"},
			{CD_STATUS_QUESTIONAMENTO_Q_INCLUSAO, 		"Q_INCLUSAO",		"Inclusão",			"8"},
			{CD_STATUS_QUESTIONAMENTO_Q_REPASSE, 		"Q_REPASSE",		"Repasse",			"9"}
    } ) );

    public static final String[] buscaConfigProcessoQuestionamento(String processo) 
    {
	    String[] tPROCESSO_QUESTIONAMENTO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vPROCESSO_QUESTIONAMENTO.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vPROCESSO_QUESTIONAMENTO.elementAt(i);
	    	if (processo.equalsIgnoreCase(tTemp[0])) 
	    	{
	    		tPROCESSO_QUESTIONAMENTO = tTemp;
	    		break;
	    	}
	    }
	    return tPROCESSO_QUESTIONAMENTO;
    }
    			
    public static final String CD_STATUS_QUESTIONAMENTO_D_REGISTRO = "RGS";
    public static final String CD_STATUS_QUESTIONAMENTO_D_ANALISE = "ANL";
    public static final String CD_STATUS_QUESTIONAMENTO_D_RESPOSTA = "RSP";
    public static final String CD_STATUS_QUESTIONAMENTO_D_CONCILIACAO = "CNC";
    public static final String CD_STATUS_QUESTIONAMENTO_D_REPASSE = "RPS";
    public static final String CD_STATUS_QUESTIONAMENTO_D_TERMINO = "TRM";
	
    public static final Vector vPROCESSO_DISPUTA = new Vector(Arrays.asList( new String[][] { 
    		{CD_STATUS_QUESTIONAMENTO_D_REGISTRO, 		"D_REGISTRO", 		"Registro",			"1"},
			{CD_STATUS_QUESTIONAMENTO_D_ANALISE, 		"D_ANALISE",		"Análise",			"2"},
			{CD_STATUS_QUESTIONAMENTO_D_RESPOSTA, 		"D_RESPOSTA",		"Resposta",			"3"},
			{CD_STATUS_QUESTIONAMENTO_D_CONCILIACAO, 	"D_CONCILIACAO",	"Conciliação",		"4"},
			{CD_STATUS_QUESTIONAMENTO_D_REPASSE, 		"D_REPASSE",		"Repasse",			"5"},
			{CD_STATUS_QUESTIONAMENTO_D_TERMINO, 		"D_TERMINO",		"Término",			"6"}
    } ) );

    public static final String[] buscaConfigProcessoDisputa(String processo) 
    {
	    String[] tPROCESSO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vPROCESSO_DISPUTA.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vPROCESSO_DISPUTA.elementAt(i);
	    	if (processo.equalsIgnoreCase(tTemp[0])) 
	    	{
	    		tPROCESSO = tTemp;
	    		break;
	    	}
	    }
	    return tPROCESSO;
    }
    			
    public static final String CD_STATUS_CONTESTACAO_CP_REGISTRO = "1";
    public static final String CD_STATUS_CONTESTACAO_CP_ANALISE = "2";
    public static final String CD_STATUS_CONTESTACAO_CP_CONCILIACAO = "3";
    public static final String CD_STATUS_CONTESTACAO_CP_REPASSE = "4";

    public static final Vector vPROCESSO_PRECONTESTAR = new Vector(Arrays.asList( new String[][] { 
    		{CD_STATUS_CONTESTACAO_CP_REGISTRO, 	"CP_REGISTRO", 		"Registro",			"1"},
			{CD_STATUS_CONTESTACAO_CP_ANALISE, 		"CP_ANALISE",		"Análise",			"2"},
			{CD_STATUS_CONTESTACAO_CP_CONCILIACAO, 	"CP_CONCILIACAO",	"Conciliação",		"3"},
			{CD_STATUS_CONTESTACAO_CP_REPASSE,		"CP_REPASSE",		"Repasse",			"4"}
    } ) );

    public static final String[] buscaConfigProcessoPreContestar(String processo) 
    {
	    String[] tPROCESSO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vPROCESSO_PRECONTESTAR.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vPROCESSO_PRECONTESTAR.elementAt(i);
	    	if (processo.equalsIgnoreCase(tTemp[0])) 
	    	{
	    		tPROCESSO = tTemp;
	    		break;
	    	}
	    }
	    return tPROCESSO;
    }
    			
    public static final Vector vRISCO = new Vector(Arrays.asList( new String[][] { 
    		{"L", 	"Remota"},
			{"B", 	"Possível"},
			{"M", 	"Provável"}
    } ) );

    public static final String[] buscaConfigRiscoContestacao(String tipo) 
    {
	    String[] tRISCO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vRISCO.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vRISCO.elementAt(i);
	    	if (tipo.equalsIgnoreCase(tTemp[0])) 
	    	{
	    		tRISCO = tTemp;
	    		break;
	    	}
	    }
	    return tRISCO;
    }
    			
    public static final String CD_STATUS_DISPUTA_PREPAGO_DP_REGISTRO = "1";
    public static final String CD_STATUS_DISPUTA_PREPAGO_DP_ANALISE = "2";
    public static final String CD_STATUS_DISPUTA_PREPAGO_DP_CONCILIACAO = "3";
    public static final String CD_STATUS_DISPUTA_PREPAGO_DP_REPASSE = "4";
    
    public static final Vector vPROCESSO_PREDISPUTAR = new Vector(Arrays.asList( new String[][] { 
    		{CD_STATUS_DISPUTA_PREPAGO_DP_REGISTRO, 	"DP_REGISTRO", 		"Registro",			"1"},
			{CD_STATUS_DISPUTA_PREPAGO_DP_ANALISE, 		"DP_ANALISE",		"Análise",			"2"},
			{CD_STATUS_DISPUTA_PREPAGO_DP_CONCILIACAO, 	"DP_CONCILIACAO",	"Conciliação",		"3"},
			{CD_STATUS_DISPUTA_PREPAGO_DP_REPASSE, 		"DP_REPASSE",		"Repasse",			"4"}
    } ) );

    public static final String[] buscaConfigProcessoPreDisputar(String processo) 
    {
	    String[] tPROCESSO = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vPROCESSO_PREDISPUTAR.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vPROCESSO_PREDISPUTAR.elementAt(i);
	    	if (processo.equalsIgnoreCase(tTemp[0])) 
	    	{
	    		tPROCESSO = tTemp;
	    		break;
	    	}
	    }
	    return tPROCESSO;
    }
    			
    public static final Vector vTABELASFISCAL = new Vector(Arrays.asList( new String[][] { 
			{"BILL_CTN_LD",""},
			{"CYCLE_CONTROL",""},
			{"COMPANY_LD",""},
			{"SCC_EOT",""},
			{"SCC_FISCAL_NF",""},
			{"SCC_ARQUIVO_COBILLING",""}
        } ) );

    // Relatorios com Abas
    public static final int RL_SEM_ABAS = 0;
    public static final int RL_REPASSE = 10;   
    public static final int RL_REPASSE_PREPAGO = 20;
    public static final int RL_CONFIRMACAO_REPASSE_PRE = 21;
    public static final int RL_CRITICAS_XLS_GENERATOR = 22;
    public static final int RL_SINTETICO_PREPAGO = 23;    
    public static final int RL_PRERESUMOAPURACAO = 26;
    public static final int RL_CONTINGENCIA_FISCAL = 30;
    public static final int RL_BATIMENTO_FISCAL = 40;
    public static final int RL_FINANCEIROS = 50;
    public static final int RL_HEADER_RESULTS = 60;
    public static final int RL_HEADER_RESULTSDETAIL = 70;
    public static final int RL_INDICADOR_PREBSC = 80;
    public static final int RL_INDICADOR_KPISPI = 85;
    public static final int RL_EXTRACAO_CDR = 101;    
    public static final int RL_ALOCADAS_MOBILE = 102;    
    public static final int RL_EVENTOS_PREPAGO = 90;    
    public static final int RL_EVENTOS_POSPAGO = 95;   
    public static final int RL_COM_ABAS = 100;
    public static final int RL_PRESTACAO_SERVICOS = 110;
    public static final int RL_ALOCADAS_MOBILE_SEM_PROCESSAMENTO = 103;   
    public static final int RL_JUROS_MULTAS = 104;   
    public static final int RL_TXT = 1000;
    public static final int RL_TXT_COLLECTION = 1100;
    public static final int RL_TXT_COLLECTION_CDR = 1200;
    public static final int RL_TXT_COLLECTION_CRITICAS = 1300;
    public static final int RL_TXT_COLLECTION_REPASSE_PRE = 1400;
    public static final int RL_TXT_COLLECTION_CHAMADAS_CREDITO = 1500;
    public static final int RL_CSV_COLLECTION_CRITICAS = 1600;
    public static final int RL_CSV_COLLECTION_CREDITOS_PRE_PAGO = 1700;
    public static final int RL_CSV_COLLECTION_CHAMADAS_CREDITO = 1800;
    
    public static final int DBRETRIES = 5;
    public static final int PAGESIZE = 10;
    
    // Encaminhado Constants
    public static final int ENCAMINHADOITEM_UNDEFINED       = -1;
    public static final int ENCAMINHADOITEM_LESS_THAN_30    = 0;
    public static final int ENCAMINHADOITEM_BETWEEN_30_45   = 1;
    public static final int ENCAMINHADOITEM_BETWEEN_45_60   = 2;
    public static final int ENCAMINHADOITEM_MORE_THAN_60    = 3;
    
    public static String getEncaminhadoPeriod(int period) {
        String msg = "Indefinido";
        switch (period) {
            case ENCAMINHADOITEM_LESS_THAN_30:
                msg = "Ate 30 dias";
                break;
            case ENCAMINHADOITEM_BETWEEN_30_45:
                msg = "De 31 a 45 dias";
                break;
            case ENCAMINHADOITEM_BETWEEN_45_60:
                msg = "De 46 a 60 dias";
                break;
            case ENCAMINHADOITEM_MORE_THAN_60:
                msg = "Maior que 60 dias";
                break;
        }
        return msg;
    }

	public static final String HOLDING_NAME_SEPARATOR = "-";
	
	public static final String EMPTY = "";

	
    // Indicador Constants
	public static final Integer BSC_AGING_SIMPLES = new Integer(1);
	public static final Integer BSC_AGING_DUPLO = new Integer(2);
	
    public static final String BSC_PERIODICIDADE = "P";
    public static final String BSC_PERIODICIDADE_DIARIA = "DIARIA";
    public static final String BSC_PERIODICIDADE_MENSAL = "MENSAL";
    public static final String BSC_PERIODICIDADE_ANUAL = "ANUAL";
    
    public static final String BSC_TIPO_INDICADOR = "T";
    public static final String BSC_TIPO_INDICADOR_QUANTIDADE = "QUANTIDADE";
    public static final String BSC_TIPO_INDICADOR_MINUTO = "MINUTO";
    public static final String BSC_TIPO_INDICADOR_VALOR = "VALOR";
    public static final String BSC_TIPO_INDICADOR_PERCENTUAL = "PERCENTUAL";
    
    public static final String BSC_STATUS	= "S";
    public static final String BSC_STATUS_INDICADOR_ATIVO	= "ATIVO";
    public static final String BSC_STATUS_INDICADOR_SUSPENSO = "SUSPENSO";
    public static final String BSC_STATUS_INDICADOR_DMR = "DMR";

    public static final String BSC_FORMATO_INDICADOR = "F";
    public static final String BSC_FORMATO_INDICADOR_QUANTIDADE = "QUANTIDADE";
    public static final String BSC_FORMATO_INDICADOR_MINUTO = "MINUTO";
    public static final String BSC_FORMATO_INDICADOR_VALOR = "VALOR";
    public static final String BSC_FORMATO_INDICADOR_PERIODO = "PERIODO";
    public static final String BSC_FORMATO_INDICADOR_HORARIO = "HORARIO";
    public static final String BSC_FORMATO_INDICADOR_PERDAS = "PERDA";
    public static final String BSC_FORMATO_INDICADOR_FALHASQTD = "FALHASQTD";
    public static final String BSC_FORMATO_INDICADOR_FALHASMIN = "FALHASMIN";
    public static final String BSC_FORMATO_INDICADOR_SINTETICO = "SINTETICO";
    public static final String BSC_FORMATO_INDICADOR_PRAZOQTD = "PRAZOQTD";

    public static final String BSC_TIPO_CONTRATO = "C";
    public static final String BSC_TIPO_CONTRATO_F = "Pós-Pago Faturado";
    public static final String BSC_TIPO_CONTRATO_P = "Pré-Pago";
    public static final String BSC_TIPO_CONTRATO_A = "Pós-Pago Arrecadado";

    public static final Vector vIndicadorClassificacao = new Vector(Arrays.asList( new String[][] {
    		{""+BSC_PERIODICIDADE_DIARIA, 			BSC_PERIODICIDADE_DIARIA, 			""+BSC_PERIODICIDADE, 		""},
    		{""+BSC_PERIODICIDADE_MENSAL, 			BSC_PERIODICIDADE_MENSAL, 			""+BSC_PERIODICIDADE, 		""},
    		{""+BSC_PERIODICIDADE_ANUAL, 			BSC_PERIODICIDADE_ANUAL, 			""+BSC_PERIODICIDADE, 		""},
    		{""+BSC_TIPO_INDICADOR_QUANTIDADE, 		BSC_TIPO_INDICADOR_QUANTIDADE, 		""+BSC_TIPO_INDICADOR, 		""},
    		{""+BSC_TIPO_INDICADOR_MINUTO, 			BSC_TIPO_INDICADOR_MINUTO, 			""+BSC_TIPO_INDICADOR, 		""},
    		{""+BSC_TIPO_INDICADOR_VALOR, 			BSC_TIPO_INDICADOR_VALOR, 			""+BSC_TIPO_INDICADOR, 		""},
    		{""+BSC_TIPO_INDICADOR_PERCENTUAL, 		BSC_TIPO_INDICADOR_PERCENTUAL, 		""+BSC_TIPO_INDICADOR, 		""},
    		{""+BSC_STATUS_INDICADOR_ATIVO, 		BSC_STATUS_INDICADOR_ATIVO, 		""+BSC_STATUS, 				""},
    		{""+BSC_STATUS_INDICADOR_SUSPENSO,		BSC_STATUS_INDICADOR_SUSPENSO, 		""+BSC_STATUS, 				""},
    		{""+BSC_STATUS_INDICADOR_DMR, 			BSC_STATUS_INDICADOR_DMR, 			""+BSC_STATUS, 				""},
    		{""+BSC_FORMATO_INDICADOR_QUANTIDADE, 	BSC_FORMATO_INDICADOR_QUANTIDADE, 	""+BSC_FORMATO_INDICADOR, 	""},
    		{""+BSC_FORMATO_INDICADOR_MINUTO, 		BSC_FORMATO_INDICADOR_MINUTO, 		""+BSC_FORMATO_INDICADOR, 	""},
    		{""+BSC_FORMATO_INDICADOR_VALOR, 		BSC_FORMATO_INDICADOR_VALOR, 		""+BSC_FORMATO_INDICADOR, 	""},
    		{""+BSC_FORMATO_INDICADOR_HORARIO, 		BSC_FORMATO_INDICADOR_HORARIO, 		""+BSC_FORMATO_INDICADOR, 	""},
    		{""+BSC_FORMATO_INDICADOR_PERIODO, 		BSC_FORMATO_INDICADOR_PERIODO, 		""+BSC_FORMATO_INDICADOR, 	""},
    		{""+BSC_FORMATO_INDICADOR_PERDAS, 		BSC_FORMATO_INDICADOR_PERDAS, 		""+BSC_FORMATO_INDICADOR,	""},
    		{""+BSC_FORMATO_INDICADOR_FALHASQTD, 	BSC_FORMATO_INDICADOR_FALHASQTD,	""+BSC_FORMATO_INDICADOR, 	""},
    		{""+BSC_FORMATO_INDICADOR_FALHASMIN, 	BSC_FORMATO_INDICADOR_FALHASMIN,	""+BSC_FORMATO_INDICADOR, 	""},
    		{""+BSC_FORMATO_INDICADOR_SINTETICO, 	BSC_FORMATO_INDICADOR_SINTETICO,	""+BSC_FORMATO_INDICADOR, 	""},
    		{""+BSC_FORMATO_INDICADOR_PRAZOQTD, 	BSC_FORMATO_INDICADOR_PRAZOQTD,		""+BSC_FORMATO_INDICADOR, 	""},
    		{""+CD_TIPO_CONTRATO_F, 				BSC_TIPO_CONTRATO_F,				""+BSC_TIPO_CONTRATO, 		""},
    		{""+CD_TIPO_CONTRATO_P, 				BSC_TIPO_CONTRATO_P,				""+BSC_TIPO_CONTRATO, 		""}
	} ) );
	 
    public static final String[] buscaConfigIndicadorClassificacao(String codigo, String tipo) {
	    String[] tIndicadorClassificacao = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vIndicadorClassificacao.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vIndicadorClassificacao.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0]) && tipo.equalsIgnoreCase(tTemp[2])) { 
	    		tIndicadorClassificacao = tTemp;
	    		break;
	    	}
	    }
	    return tIndicadorClassificacao;
    }
    
    public static final String GSM = "GSM";
    public static final String TDMA_BCP = "TDMA_BCP";
    public static final String TDMA_TSS = "TDMA_TSS";
    public static final String TDMA_ATL = "TDMA_ATL";
    public static final String TDMA_BSE = "TDMA_BSE";
    public static final String TDMA_AML = "TDMA_AML";
    public static final String TDMA_TLT = "TDMA_TLT";
    
    public static final Vector vPlataforma = new Vector(Arrays.asList( new String[][] { 
    		{"1",	GSM, 		"GSM"},
    		{"2",	TDMA_BCP,	"TDMA BCP"},
    		{"3",	TDMA_TSS,	"TDMA TESS"},
    		{"4",	TDMA_ATL,	"TDMA ATL"},
    		{"5",	TDMA_BSE,	"TDMA BSE"},
    		{"6",	TDMA_AML,	"TDMA AMERICEL"},
    		{"7",	TDMA_TLT,	"TDMA TELET"}
	} ) );
	 
    public static final String[] buscaConfigPlataforma(String codigo) {
	    String[] tPlataforma = null;
	    String[] tTemp = null;
	    for(int i=0;i<CobillingConstants.vPlataforma.size();i++) 
	    {
	    	tTemp = (String[]) CobillingConstants.vPlataforma.elementAt(i);
	    	if (codigo.equalsIgnoreCase(tTemp[0])) { 
	    		tPlataforma = tTemp;
	    		break;
	    	}
	    }
	    return tPlataforma;
    }

    // Arquivos Batimento Constants
    public static final String BATIMENTO_REMESSA		= "REMESSA";
    public static final String BATIMENTO_RETORNO		= "RETORNO";
    public static final String BATIMENTO_EXCLUSAO		= "EXCLUSAO";
    public static final String BATIMENTO_FISCAL	  		= "FISCAL";
    public static final String BATIMENTO_QUESTIONAMENTO	= "QUESTIONAMENTO";
    public static final String BATIMENTO_CONFIRMACAO  	= "CONFIRMACAO";

    public static final String ERRO_GERAL = "erro.geral";
    public static final String DATA_FINAL_MAIOR_INICAL = "data.final.maior";
    public static final String PERIODO_OBRIGATORIO = "periodo.obrigatorio";
    public static final String UMA_DATA_OBRIGATORIA = "uma.data.obrigatoria";
    public static final String UM_PRODUTO = "um.produto";
    public static final String UMA_OPERADORA = "uma.operadora";
    public static final String ANO_APURACAO = "ano.apuracao";
    public static final String ANO_REFERENCIA = "ano.referencia";
    public static final String ANO = "ano.invalido";
    public static final String DATA_INVALIDA = "data.invalida";
    public static final String ANO_OBRIGATORIO = "ano.obrigatorio";
    public static final String ANO_REFERENCIA_OBRIGATORIO = "ano.obrigatorioReferencia";
    public static final String DATA_INICIAL_INVALIDA = "data.inicial.invalida";
    public static final String DATA_FINAL_INVALIDA = "data.final.invalida";
    public static final String DATA_FINAL_INVALIDA_BR = "data.final.invalida.br";

	public static final String ARQUIVO_BATIMENTO_CD_REMESSA = "1";
	public static final String ARQUIVO_BATIMENTO_CD_RETORNO = "2";

	public static final String NUMERO_ACORDO_NUMERICO = "numero.acordo.numerico";
	public static final String NUMERO_CONTA_NUMERICO = "numero.conta.numerico";
    //chave do estorno de débito
    public static final String ESTORNO_DEBITO = "120";

    //chave do estorno de débito
    public static final String ARQUIVOS_FISCAIS = "75,80";


    public static final String REF_DATE = "refdate";


    public static final String ID_FATURADO = "1";
    public static final String ID_ARRECADADO = "2";


    // RELATORIO NAO CONFORMIDADE FATURAMENTO
    public static final String MES_ANO_OBRIGATORIOS = "mes.ano.obrigatorios";

    public static final String CICLO_ANO_OBRIGATORIOS = "ciclo.ano.obrigatorios";

    public static final String CICLO_MES_OBRIGATORIOS = "ciclo.mes.obrigatorios";

    public static final String AVANCAR = "avancar";
    public static final String ERRORS_INVALID = "errors.invalid";

	public static final String KEY_AFATURAR_FATURADO = "1";

	public static final String KEY_ACEITE_REJEITADO = "2";

	public static final String OPERADORA_LD_OBRIGATORIO = "operadora.ld.obrigatorio";
	public static final String OPERADORA_CLARO_OBRIGATORIO = "operadora.claro.obrigatorio";
	public static final String QUINZENA_OBRIGATORIO = "quinzena.obrigatorio";
	public static final String MES_OBRIGATORIO = "mes.obrigatorio";
	public static final String ANO_REPASSE_OBRIGATORIO = "ano.repasse.obrigatorio";
	public static final String ANO_NUMERICO = "ano.numerico";
	public static final String MESSAGE = "message";
	
	public static final String TIPO_PARAM_PROCESSO_REPASSE = "REPASSE";
	
	 // Relatórios
    public static final String CRITICA_INICIAL = "1";
    public static final String ASSEUXADOS = "2";
    public static final String ENCAMINHADAS_MOBILE = "3";
    public static final String CICLOS_PROCESSADOS = "4";
    public static final String CICLOS_NAO_PROCESSADOS = "5";
	

}
