package com.claro.sccweb.constants;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;


public class CobillingConstants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9079833908924459886L;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static final Vector vRELATORIOS = new Vector(
			Arrays.asList(new String[][] {
					{ "" + 1, "R1", "R1 CAP OPERADORAS POS PAGO", "24" },
					{ "" + 2, "R2", "R2 CAR CLIENTES POS PAGO", "41" },
					{ "" + 3, "R3", "R3 QUESTIONAMENTOS POS PAGO", "24" },
					{ "" + 4, "R4", "R4 DISPUTAS POS PAGO", "10" },
					{ "" + 5, "R5", "R5 PERDAS E FRAUDES POS PAGO", "23" },
					{ "" + 6, "R6", "R6 SALDO DE LOTE POS PAGO", "48" },
					{ "" + 7, "R7", "R7 CONSOLIDADO POS PAGO", "35" },
					{ "" + 8, "R1P", "R1 CAP OPERADORAS PRE PAGO", "24" },
					{ "" + 9, "R2P", "R2 CONTESTACAO PRE PAGO", "14" },
					{ "" + 10, "R3P", "R3 DISPUTA PRE PAGO", "09" },
					{ "" + 11, "R4P", "R4 PERDAS E FRAUDES PRE PAGO", "23" },
					{ "" + 12, "R5P", "R5 CONSOLIDADO PRE PAGO", "27" } }));

	public static final String[] buscaConfigRelatorio(String codigo) {
		String[] tRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vRELATORIOS.size(); i++) {
			tTemp = (String[]) CobillingConstants.vRELATORIOS.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[1])) {
				tRELATORIO = tTemp;
				break;
			}
		}
		return tRELATORIO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vR1CAPOPERADORAS = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "R1", "(a) Aceito                          	",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "02", "R1", "(b) Rejeitado (período)             	",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "03", "R1", "(c) Rejeitado (períodos anteriores) 	",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "04", "R1", "(d) Rejeitado 30d (período anterior) 	",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "05", "R1", "    % Aceito (d/a)                  	",
							"PE_RETENCAO", "00", "" },
					{ "06", "R1", "(e) Faturável Inicial               	",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "07", "R1", "(f) Desconto Contratual             	",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "08", "R1", "(g) Repasse                         	",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "09", "R1", "    Parcela 60% M-2                 	",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "10", "R1", "    Parcela 40% M-3                 	",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "11", "R1", "(h) Valor Bruto a Repassar          	",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "12", "R1", "    Total Deduções                  	",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "13", "R1", "    Total Acertos                   	",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "14", "R1", "    Total Penalidades               	",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "15", "R1", "    CPMF a Descontar                	",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "16", "R1", "(i) Total a Descontar               	",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "17", "R1", "(j) Valor Líquido a Repassar(h+i)   	",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "18", "R1", "(l) Repasse Liquidado               	",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "19", "R1", "    Saldo a Repassar                	",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "20", "R1", "(m) Penalidade por Atraso contra Claro",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "21", "R1", "(n) Penalidade por Atraso contra LD 	",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "22", "R1", "(o) Total Penalidade por Atraso     	",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "23", "R1", "(p) Provisão CAP Operadoras         	",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "24", "R1", "(q) Saldo CAP Operadoras            	",
							"VL_BRUTO_CHAMADA", "01", "" } }));

	public static final String[] buscaConfigItensR1CAPOPERADORAS(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vR1CAPOPERADORAS.size(); i++) {
			tTemp = (String[]) CobillingConstants.vR1CAPOPERADORAS.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vR2CARCLIENTES = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "R2", "    Quantidade Chamadas Faturadas   	  ",
							"QT_CDRS", "00", "" },
					{ "02", "R2", "(a) Receita Serviços Líquida        	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "03", "R2", "(b) Impostos s/ Receita Serviços    	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "04", "R2", "(c) Receita Serviços Bruta          	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "05", "R2", "(d) Receita LD A Faturar Bruta      	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "06", "R2", "(e) Receita LD Faturada Líquida     	  ",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "07", "R2", "(f) Impostos s/ Receita LD Faturada 	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "08", "R2", "(g) Receita LD Faturada Bruta       	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "09", "R2", "    Faturado Bruto Retorno          	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "10", "R2", "    Faturado Bruto Fiscal           	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "11", "R2", "    Batimento Billing x Retorno     	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "12", "R2", "    Batimento Billing x Fiscal      	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "13", "R2", "(h) Ajustes Concedidos              	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "14", "R2", "    % Receita LD Faturada Bruta     	  ",
							"PE_RETENCAO", "00", "" },
					{ "15", "R2", "(i) Receita LD Faturada Bruta ex-Ajustes",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "16", "R2", "(j) Fraude Pós-Faturamento           	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "17", "R2", "    % Receita LD Faturada Bruta     	  ",
							"PE_RETENCAO", "00", "" },
					{ "18", "R2", "    Arrecadado M                    	  ",
							"VL_BRUTO_CHAMADA", "02",
							"Arrecadação (Estimativa)           " },
					{ "19", "R2", "    Arrecadado M-1                  	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "20", "R2", "    Arrecadado M-2                      ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "21", "R2", "    Arrecadado M-3                  	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "22", "R2", "    Arrecadado > M-3                	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "23", "R2", "(l) Receita LD Arrecadada Bruta     	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "24", "R2", "(m) Saldo a Receber                 	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "25", "R2", "(n) Inadimplência (Estimativa)      	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "26", "R2", "    % Receita LD Faturada Bruta         ",
							"PE_RETENCAO", "00", "" },
					{ "27", "R2", "(o) Provisão CAR Clientes (Unbilled)	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "28", "R2", "(p) Saldo CAR Clientes              	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "29", "R2", "    A vencer                        	  ",
							"VL_BRUTO_CHAMADA", "02",
							"Mapto. Saldo a Receber (Estimativa)" },
					{ "30", "R2", "    Vencido 1-30 d                  	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "31", "R2", "    Vencido 31-60 d                 	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "32", "R2", "    Vencido 61-90 d                     ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "33", "R2", "    Vencido 91-120 d                	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "34", "R2", "    Vencido > 120 d                 	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "35", "R2", "    Estimativa Arrecadacao Vencido  	  ",
							"PE_RETENCAO", "02",
							"% Arrecadado/Faturado (Cfe Cobrança Claro)" },
					{ "36", "R2", "    Estimativa Arrecadacao Vencido M-1  ",
							"PE_RETENCAO", "00", "" },
					{ "37", "R2", "    Estimativa Arrecadacao Vencido M-2  ",
							"PE_RETENCAO", "00", "" },
					{ "38", "R2", "    Estimativa Arrecadacao Vencido M-3  ",
							"PE_RETENCAO", "00", "" },
					{ "39", "R2", "    Estimativa Arrecadacao Vencido > M-3",
							"PE_RETENCAO", "00", "" },
					{ "40", "R2", "    Total Estimativa Arrecadacao Vencido",
							"PE_RETENCAO", "00", "" },
					{ "41", "R2", "    % Inadimplencia Total               ",
							"PE_RETENCAO", "00", "" } }));

	public static final String[] buscaConfigItensR2CARCLIENTES(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vR2CARCLIENTES.size(); i++) {
			tTemp = (String[]) CobillingConstants.vR2CARCLIENTES.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vR3QUESTIONAMENTO = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "R3", "    Qte Ch Rejeitadas                   ",
							"QT_CDRS", "00", "" },
					{ "02", "R3", "(a) Rejeitado                       	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "03", "R3", "    Qte Ch Questionadas                 ",
							"QT_CDRS", "01", "" },
					{ "04", "R3", "(b) Questionado                     	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "05", "R3", "    Qte Ch Confirmadas              	  ",
							"QT_CDRS", "01", "" },
					{ "06", "R3", "(c) Confirmado                      	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "07", "R3", "    Qte Ch Confirmadas Procedente   	  ",
							"QT_CDRS", "01", "" },
					{ "08", "R3", "    Min Ch Confirmadas Procedente   	  ",
							"QT_CDRS", "00", "" },
					{ "09", "R3", "(d) Confirmado Procedente           	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "10", "R3", "    Qte Ch Faturáveis Incluídas     	  ",
							"QT_CDRS", "01", "" },
					{ "11", "R3", "    Min Ch Faturáveis Incluídas     	  ",
							"QT_CDRS", "00", "" },
					{ "12", "R3", "(e) Rec. LD Fat. Líq. (outros períodos) ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "13", "R3", "    Qte Ch Perdida N-Incluída       	  ",
							"QT_CDRS", "01", "" },
					{ "14", "R3", "    Min Ch Perdida N-Incluída       	  ",
							"QT_CDRS", "00", "" },
					{ "15", "R3", "(f) Perda P6 Ch N-Incluída              ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "16", "R3", "(g) Acerto de Inclusão a favor LD    	  ",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "17", "R3", "(h) Penalidade por Rejeições Indevidas  ",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "18", "R3", "(i) Penalidade por Rejeições Devidas	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "19", "R3", "(j) Total Penalidade por Rejeições  	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "20", "R3", "    Qte Ch N-Confirmadas                ",
							"QT_CDRS", "01", "" },
					{ "21", "R3", "    Min Ch N-Confirmadas            	  ",
							"QT_CDRS", "00", "" },
					{ "22", "R3", "(l) Saldo Questionamentos Iniciados 	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "23", "R3", "(m) Penalidade Potencial (Valor Máximo) ",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "24", "R3", "(n) Provisão p/ Penalidade (Question.)  ",
							"VL_LIQUIDO_CHAMADA", "01", "" } }));

	public static final String[] buscaConfigItensR3QUESTIONAMENTO(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vR3QUESTIONAMENTO.size(); i++) {
			tTemp = (String[]) CobillingConstants.vR3QUESTIONAMENTO
					.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	public static final String CD_CLARO = new String("CLARO");
	public static final String CD_OPERADORA_LD = new String("OPERADORA_LD");

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vPENALIDADE_CLARO = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "", "PENALIDADES CLARO", "CLARO" },
					{ "02", "", "Rejeição por Falha", "CLARO" },
					{ "03", "104", "Assinante A não é da operadora contratada",
							"CLARO" },
					{ "04", "144", "Código de Área de A é inválido", "CLARO" },
					{ "05", "146", "Código de Área de B é inválido", "CLARO" },
					{ "06", "145", "Chamada não apropriável à Filial", "CLARO" },
					{ "07", "106", "Irregularidade no cadastro de assinantes",
							"CLARO" },
					{ "08", "105", "Assinante A inválido", "CLARO" },
					{ "09", "121", "Assinante B inválido", "CLARO" },
					{ "10", "100", "Código de natureza inválido", "CLARO" },
					{ "11", "102", "EOT da prestadora de origem inválido",
							"CLARO" },
					{ "12", "103", "EOT da prestadora de destino inválido",
							"CLARO" },
					{ "13", "117", "CNL inválido assinante A", "CLARO" },
					{ "14", "122", "CNL inválido assinante B", "CLARO" },
					{ "15", "120", "Código do País inválido", "CLARO" },
					{ "16", "131", "Código nacional da área visitada inválido",
							"CLARO" },
					{
							"17",
							"101",
							"Natureza conflitante com o tipo de terminal de origem",
							"CLARO" },
					{ "18", "118", "CNL conflitante assinante A", "CLARO" },
					{ "19", "123", "CNL conflitante assinante B", "CLARO" },
					{ "20", "124", "Data da chamada inválida", "CLARO" },
					{ "21", "147", "Data anterior a data início da operação",
							"CLARO" },
					{ "22", "126", "Hora início da chamada inválida", "CLARO" },
					{ "23", "127", "Duração real da chamada inválida", "CLARO" },
					{ "24", "128", "Duração tarifada da chamada inválida",
							"CLARO" },
					{ "25", "129", "Grupo Horário inválido", "CLARO" },
					{ "26", "130", "Degrau inválido", "CLARO" },
					{ "27", "132", "Valor inválido", "CLARO" },
					{
							"28",
							"142",
							"Código nacional da área visitada conflitante c/ código de natureza",
							"CLARO" },
					{ "29", "119", "CSP inválido", "CLARO" },
					{ "30", "149", "Cenário não tratado por este processo",
							"CLARO" },
					{ "31", "148", "Faixa de serviço especial não cadastrada",
							"CLARO" },
					{ "32", "138", "Tipo de registro inválido", "CLARO" },
					{ "33", "133", "Código de refaturamento inválido", "CLARO" },
					{ "34", "137", "Chamada não existe para refaturar", "CLARO" },
					{ "35", "136", "Chamada não contestada para refaturar",
							"CLARO" },
					{ "36", "140", "Chamada Local", "CLARO" },
					{ "37", "141", "Chamada duplicada", "CLARO" },
					{ "38", "", "Subtotal", "CLARO" },
					{ "39", "", "Rejeição por Regra", "CLARO" },
					{ "40", "109", "Terminal Pré-pago", "CLARO" },
					{ "41", "143", "Telefone com retenção de impostos", "CLARO" },
					{ "42", "125", "Decurso de prazo contratual", "CLARO" },
					{ "43", "150", "Decurso de prazo regulatório ", "CLARO" },
					{ "44", "134", "Retorno de apuração fora do prazo", "CLARO" },
					{
							"45",
							"135",
							"Chamada contestada enviada acima do nr. de vezes permitido",
							"CLARO" },
					{
							"46",
							"107",
							"Terminal bloqueado / suspenso no momento das chamadas",
							"CLARO" },
					{ "47", "", "Subtotal", "CLARO" },
					{ "48", "", "Total PENALIDADES CLARO", "CLARO" },
					{ "49", "372", "Número A MSRN", "LD" },
					{ "50", "110", "Terminal da própria Operadora Contratada",
							"CLARO" } }));

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vPENALIDADE_LD = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "", "PENALIDADES PRESTADORA LD", "LD" },
					{ "02", "", "Rejeição por Falha", "LD" },
					{ "03", "104", "Assinante A não é da operadora contratada",
							"LD" },
					{ "04", "144", "Código de Área de A é inválido", "LD" },
					{ "05", "146", "Código de Área de B é inválido", "LD" },
					{ "06", "145", "Chamada não apropriável à Filial", "LD" },
					{ "07", "106", "Irregularidade no cadastro de assinantes",
							"LD" },
					{ "08", "105", "Assinante A inválido", "LD" },
					{ "09", "121", "Assinante B inválido", "LD" },
					{ "10", "100", "Código de natureza inválido", "LD" },
					{ "11", "102", "EOT da prestadora de origem inválido", "LD" },
					{ "12", "103", "EOT da prestadora de destino inválido",
							"LD" },
					{ "13", "117", "CNL inválido assinante A", "LD" },
					{ "14", "122", "CNL inválido assinante B", "LD" },
					{ "15", "120", "Código do País inválido", "LD" },
					{ "16", "131", "Código nacional da área visitada inválido",
							"LD" },
					{
							"17",
							"101",
							"Natureza conflitante com o tipo de terminal de origem",
							"LD" },
					{ "18", "118", "CNL conflitante assinante A", "LD" },
					{ "19", "123", "CNL conflitante assinante B", "LD" },
					{ "20", "124", "Data da chamada inválida", "LD" },
					{ "21", "147", "Data anterior a data início da operação",
							"LD" },
					{ "22", "126", "Hora início da chamada inválida", "LD" },
					{ "23", "127", "Duração real da chamada inválida", "LD" },
					{ "24", "128", "Duração tarifada da chamada inválida", "LD" },
					{ "25", "129", "Grupo Horário inválido", "LD" },
					{ "26", "130", "Degrau inválido", "LD" },
					{ "27", "132", "Valor inválido", "LD" },
					{
							"28",
							"142",
							"Código nacional da área visitada conflitante c/ código de natureza",
							"LD" },
					{ "29", "119", "CSP inválido", "LD" },
					{ "30", "149", "Cenário não tratado por este processo",
							"LD" },
					{ "31", "148", "Faixa de serviço especial não cadastrada",
							"LD" },
					{ "32", "138", "Tipo de registro inválido", "LD" },
					{ "33", "133", "Código de refaturamento inválido", "LD" },
					{ "34", "137", "Chamada não existe para refaturar", "LD" },
					{ "35", "136", "Chamada não contestada para refaturar",
							"LD" },
					{ "36", "140", "Chamada Local", "LD" },
					{ "37", "141", "Chamada duplicada", "LD" },
					{ "38", "", "Subtotal", "LD" },
					{ "39", "", "Rejeição por Regra", "LD" },
					{ "40", "109", "Terminal Pré-pago", "LD" },
					{ "41", "143", "Telefone com retenção de impostos", "LD" },
					{ "42", "125", "Decurso de prazo contratual", "LD" },
					{ "43", "150", "Decurso de prazo regulatório ", "LD" },
					{ "44", "134", "Retorno de apuração fora do prazo", "LD" },
					{
							"45",
							"135",
							"Chamada contestada enviada acima do nr. de vezes permitido",
							"LD" },
					{
							"46",
							"107",
							"Terminal bloqueado / suspenso no momento das chamadas",
							"LD" },
					{ "47", "", "Subtotal", "LD" },
					{ "48", "", "Total PENALIDADES PRESTADORA LD", "LD" },
					{ "49", "372", "Número A MSRN", "LD" },
					{ "50", "110", "Terminal da própria Operadora Contratada",
							"LD" }, }));

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vPENALIDADE_NAO_APLICAVEL = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "", "PENALIDADE NÃo APLICÁVEL", "Q" },
					{ "02", "", "Rejeição por Regra", "Q" },
					{ "40", "109", "Terminal Pré-pago", "Q" },
					{ "50", "110", "Terminal da própria Operadora Contratada",
							"Q" },
					{ "41", "143", "Telefone com retenção de impostos", "Q" },
					{ "43", "150", "Decurso de prazo regulatório ", "Q" },
					{ "51", "373", "Cliente licitado", "Q" },
					{ "05", "", "Subtotal", "Q" },
					{ "06", "", "Total p/ penalidades não aplicáveis", "Q" } }));

	public static final String[] buscaConfigItensPENALIDADE_CLARO(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vPENALIDADE_CLARO.size(); i++) {
			tTemp = (String[]) CobillingConstants.vPENALIDADE_CLARO
					.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	public static final String[] buscaConfigItensPENALIDADE_LD(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vPENALIDADE_LD.size(); i++) {
			tTemp = (String[]) CobillingConstants.vPENALIDADE_LD.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	public static final String[] buscaConfigItensPENALIDADE_NAO_APLICAVEL(
			String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vPENALIDADE_NAO_APLICAVEL.size(); i++) {
			tTemp = (String[]) CobillingConstants.vPENALIDADE_NAO_APLICAVEL
					.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	public static final String[] buscaConfigItens(String codigo,
			String inResultadoAnalise) {
		if ("P".equalsIgnoreCase(inResultadoAnalise)) {
			return buscaConfigItensPENALIDADE_CLARO(codigo);
		} else if ("N".equalsIgnoreCase(inResultadoAnalise)) {
			return buscaConfigItensPENALIDADE_LD(codigo);
		}
		return buscaConfigItensPENALIDADE_NAO_APLICAVEL(codigo);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vR4DISPUTA = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "R4", "(a) Valor Contestado                    ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "02", "R4", "(b) Valor Proposto                  	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "03", "R4", "(c) Valor Acordado                  	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "04", "R4", "(d) Dif. Acordo x Contestado        	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "05", "R4", "(e) Dif. Acordo x Proposto          	  ",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "06", "R4", "(f) Acerto de Conciliação a favor CLARO ",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "07", "R4", "(g) Acerto de Conciliação a favor LD	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "08", "R4", "(h) Total Acerto de Conciliação     	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "09", "R4", "(i) Saldo Disputas Iniciadas        	  ",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "10", "R4", "(j) Provisão p/ Acerto (Disputas)   	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" } }));

	public static final String[] buscaConfigItensR4DISPUTA(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vR4DISPUTA.size(); i++) {
			tTemp = (String[]) CobillingConstants.vR4DISPUTA.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vR5PERDASFRAUDES = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "R5", "(a) P1 Perdida Inicial              	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "02", "R5", "(b) P2 Descartada                   	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "03", "R5", "(c) P3 Retirada                     	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "04", "R5", "(d) Perdas P1-P3 (Rejeição)         	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "05", "R5", "    % Receita LD Faturável Bruta    	  ",
							"PE_RETENCAO", "00", "" },
					{ "06", "R5", "(e) P4 Abortada   	              	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "07", "R5", "(f) P5 N-Reciclada                  	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "08", "R5", "(g) P6 N-Incluída                   	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "09", "R5", "(h) P7 Perda N-ID                   	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "10", "R5", "(i) Perdas P4-P7 (Faturamento)       	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "11", "R5", "    % Receita LD Faturável Bruta    	  ",
							"PE_RETENCAO", "00", "" },
					{ "12", "R5", "(j) Perdas de Processamento (d+i)    	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "13", "R5", "    % Receita LD Faturável Bruta    	  ",
							"PE_RETENCAO", "00", "" },
					{ "14", "R5", "(l) X1 Excluído Inicial             	  ",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "15", "R5", "(m) X2 Excluído Billing                 ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "16", "R5", "(n) Fraude Pré-Faturamento           	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "17", "R5", "    % Receita LD Faturável Bruta    	  ",
							"PE_RETENCAO", "00", "" },
					{ "18", "R5", "(o) X3 Excluído a Vencer            	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "19", "R5", "(p) X4 Excluído Vencido             	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "20", "R5", "(q) Fraude Pós-Faturamento              ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "21", "R5", "    % Receita LD Faturável Bruta    	  ",
							"PE_RETENCAO", "00", "" },
					{ "22", "R5", "(r) Fraudes (n+q)                   	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "23", "R5", "    % Receita LD Faturável Bruta    	  ",
							"PE_RETENCAO", "00", "" } }));

	public static final String[] buscaConfigItensR5PERDASFRAUDES(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vR5PERDASFRAUDES.size(); i++) {
			tTemp = (String[]) CobillingConstants.vR5PERDASFRAUDES.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vR6SALDODELOTE = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "R6", "(a) Aceito                          	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "02", "R6", "    C1 Critica Inicial              	  ",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "03", "R6", "    C2 Critica Billing              	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "04", "R6", "(b) Rejeitado                       	  ",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "05", "R6", "    % Aceito (b/a)                  	  ",
							"PE_RETENCAO", "00", "" },
					{ "06", "R6", "(c) Faturável Inicial               	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "07", "R6", "(d) Faturável Incluído              	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "08", "R6", "(e) Faturável (c+d)                 	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "09", "R6", "    X1 Excluído Inicial             	  ",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "10", "R6", "    X2 Excluído Billing              	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "11", "R6", "(f) Fraude Pré-Faturamento          	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "12", "R6", "    % Faturável (f/e)                	  ",
							"PE_RETENCAO", "00", "" },
					{ "13", "R6", "    P1 Inicial                      	  ",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "14", "R6", "    P2 Descartada                   	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "15", "R6", "    P3 Retirada                         ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "16", "R6", "    P4 Abortada                   	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "17", "R6", "    P5 N-Reciclada                   	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "18", "R6", "    P6 N-Incluída                   	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "19", "R6", "    P7 Perda N-ID                   	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "20", "R6", "(g) Perdas de Processamento             ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "21", "R6", "    % Faturável (g/e)               	  ",
							"PE_RETENCAO", "00", "" },
					{ "22", "R6", "    Encaminhada                     	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "23", "R6", "    Incluída                        	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "24", "R6", "    Alocada                         	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "25", "R6", "    A Reciclar                      	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "26", "R6", "    A Recuperar                     	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "27", "R6", "(h) A Faturar                       	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "28", "R6", "    Faturado Encaminhado            	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "29", "R6", "    Faturado Incluído               	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "30", "R6", "    Faturado RR                     	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "31", "R6", "(i) Faturado                        	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "32", "R6", "(j) Faturável Final                 	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "33", "R6", "(l) Perda Contratual D+90d (i-j)    	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "34", "R6", "    % Faturável Final (l/j)         	  ",
							"PE_RETENCAO", "00", "" },
					{ "35", "R6", "(m) Ajustes Concedidos              	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "36", "R6", "    % Faturado (m/i)                	  ",
							"PE_RETENCAO", "00", "" },
					{ "37", "R6", "    X3 Excluído a Vencer            	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "38", "R6", "    X4 Excluído Vencido             	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "39", "R6", "(n) Fraude Pós-Faturamento          	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "40", "R6", "    % Faturado (n/i)                	  ",
							"PE_RETENCAO", "00", "" },
					{ "41", "R6", "(o) Arrecadado                      	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "42", "R6", "(p) A Receber                       	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "43", "R6", "(q) Inadimplência (Estimativa)      	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "44", "R6", "    % Faturado (q/i)                	  ",
							"PE_RETENCAO", "00", "" },
					{ "45", "R6", "(r) Perdas+Fraudes+Inad.(g+f+n-q)       ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "46", "R6", "    % Faturável (r/e)                	  ",
							"PE_RETENCAO", "00", "" },
					{ "47", "R6", "    Dif. N-Localizada D+90d             ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "48", "R6", "    Dif. N-Localizada D+180d         	  ",
							"VL_BRUTO_CHAMADA", "00", "" } }));

	public static final String[] buscaConfigItensR6SALDODELOTE(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vR6SALDODELOTE.size(); i++) {
			tTemp = (String[]) CobillingConstants.vR6SALDODELOTE.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vR7CONSOLIDADO = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "R7",
							"    Receita LD Faturável Líquida (Período)		",
							"VL_LIQUIDO_CHAMADA", "02",
							"P&L                      " },
					{
							"02",
							"R7",
							"    Receita LD Faturável Líquida (outros períodos)",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "03", "R7", "    Impostos s/ Receita LD Faturável				",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "04", "R7", "(a) Receita LD Faturável Bruta      	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "05", "R7", "(b) Repasse Devido                  	  			",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "06", "R7", "(c) Desconto Claro                  	  			",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "07", "R7", "    Receita Serviços Líquida        	  			",
							"VL_LIQUIDO_CHAMADA", "01", "" },
					{ "08", "R7", "    Impostos s/ Receita Serviços    	  			",
							"VL_LIQUIDO_CHAMADA", "00", "" },
					{ "09", "R7", "(d) Receita Serviços Bruta          	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "10", "R7", "(e) Penalidade por Rejeições Devidas  			",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "11", "R7", "(f) Acertos contra LD               	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "12", "R7",
							"(g) Recebível Total Líquido (c+d-e-f) 	  		",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "13", "R7", "(h) Ajustes Concedidos			           	  	",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "14", "R7", "    Fraudes                            			",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "15", "R7",
							"    Inadimplência (Estimativa)          			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "16", "R7",
							"    Perdas de Processamento          	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "17", "R7", "    Penalidade por Rejeições Indevidas 			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "18", "R7", "(i) Custo Operacional               	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "19", "R7", "(j) Acertos contra Claro            	  			",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "20", "R7",
							"(l) Custo Total (h+i+j)                 			",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "21", "R7", "(m) Resultado (g+l)                 	  			",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "22", "R7", "    Receita LD A Faturar Bruta      	  			",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "23", "R7", "    Receita LD Faturada Bruta       	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "24", "R7", "    Receita LD Arrecadada Bruta     	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "25", "R7", "    Repasse Liquidado               	  			",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "26", "R7", "    Penalidade por Atraso contra Claro 			",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "27", "R7", "    Penalidade por Atraso contra LD 	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "28", "R7", "    Provisão CAP Operadoras         	  			",
							"VL_BRUTO_CHAMADA", "02",
							"BS                      " },
					{ "29", "R7", "    Provisão CAR Clientes           	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "30", "R7",
							"    Provisão p/ Penalidade (Questionamentos)		",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "31", "R7", "    Provisão p/ Acerto (Disputas)   	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "32", "R7", "    Saldo CAP Operadoras            	  			",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "33", "R7", "    Saldo CAR Clientes              	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "34", "R7", "    Saldo Questionamentos Iniciados 	  			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "35", "R7", "    Saldo Disputas Iniciadas        	  			",
							"VL_BRUTO_CHAMADA", "00", "" } }));

	public static final String[] buscaConfigItensR7CONSOLIDADO(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vR7CONSOLIDADO.size(); i++) {
			tTemp = (String[]) CobillingConstants.vR7CONSOLIDADO.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vF1PRECAPOPERADORAS = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "R1P",
							"    Total Chamadas Apuradas                ",
							"QT_CDRS", "00", "" },
					{ "02", "R1P",
							"    Total de Créditos Queimados         	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "03", "R1P",
							"    Total de Créditos Inválidos         	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "04", "R1P",
							"    Total Cursado ACB                   	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "05", "R1P",
							"    Apurado Mês Anterior                	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "06", "R1P",
							"    Apurado Outros Meses                   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "07", "R1P", "(a) Valor Bruto Apurado		              ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "25", "R1P", "    ICMS Não Repassado                	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "08", "R1P",
							"    Receita Serviço Bruta               	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "09", "R1P",
							"    Créditos Autorizados                   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "10", "R1P",
							"    Créditos Anatel 226                    ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "11", "R1P",
							"(b) Total Deduções                         ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "12", "R1P",
							"(c) Total das Penalidades               	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "13", "R1P",
							"(d) Total Acertos                       	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "14", "R1P",
							"(e) CPMF a Descontar                    	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "15", "R1P",
							"(f) ICMS a Descontar                    	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "16", "R1P",
							"(g) ICMS a Repassar                        ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "17", "R1P",
							"(h) Valor Final a Repassar (a+b+c+d+e+f+g) ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "18", "R1P",
							"(i) Repasse Liquidado                   	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "19", "R1P",
							"(j) Saldo a Repassar                       ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "20", "R1P",
							"    Multas / Juros Atraso Pgto contra Claro",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "21", "R1P",
							"    Multas / Juros Atraso Pgto contra LD   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "22", "R1P",
							"(l) Total Multas / Juros Atraso Pgto    	  ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "23", "R1P",
							"(m) Provisão CAP Operadoras             	  ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "24", "R1P",
							"(n) Saldo CAP Operadoras                	  ",
							"VL_BRUTO_CHAMADA", "01", "" } }));

	public static final String[] buscaConfigItensF1PRECAPOPERADORAS(
			String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vF1PRECAPOPERADORAS.size(); i++) {
			tTemp = (String[]) CobillingConstants.vF1PRECAPOPERADORAS
					.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vF2PRECONTESTAR = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "R2P",
							"(a) Valor do Pleito                         ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "02", "R2P",
							"(b) Valor Proposto                      	   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "03", "R2P",
							"    Valor Ch Perdidas                   	   ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "04", "R2P",
							"    Dif ch Erro de Tarifa               	   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "05", "R2P",
							"(c) Valor Defeito                       	   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "06", "R2P",
							"    Penalidade Min Perdidos contra Claro    ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "07", "R2P",
							"    Acerto de Conciliação contra Claro  	   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "08", "R2P",
							"    Acerto de Conciliação contra LD     	   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "09", "R2P",
							"(d) Valor Acordado                          ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "10", "R2P",
							"(e) Dif (Acordado - Pleito)                 ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "11", "R2P",
							"(f) Dif (Acordado - Proposto)               ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "12", "R2P",
							"(g) Dif (Acordado - Defeito)                ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "13", "R2P",
							"(h) Saldo Penalidade Potenciais         	   ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "14", "R2P",
							"(i) Provisão para Penalidades (Min Perdidos)",
							"VL_BRUTO_CHAMADA", "01", "" } }));

	public static final String[] buscaConfigItensF2PRECONTESTAR(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vF2PRECONTESTAR.size(); i++) {
			tTemp = (String[]) CobillingConstants.vF2PRECONTESTAR.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vF3PREDISPUTAR = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "R3P", "(a) Valor do Pleito                   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "02", "R3P", "(b) Valor Proposto                    ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "03", "R3P", "    Acerto de Conciliação contra Claro",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "04", "R3P", "    Acerto de Conciliação contra LD   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "05", "R3P", "(c) Valor Acordado                    ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "06", "R3P", "(d) Dif (Acordado - Pleito)           ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "07", "R3P", "(e) Dif (Acordado - Proposto)         ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "08", "R3P", "(f) Saldo Disputas Iniciadas          ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "09", "R3P", "(g) Provisão para Acertos (Disputas)  ",
							"VL_BRUTO_CHAMADA", "01", "" } }));

	public static final String[] buscaConfigItensF3PREDISPUTAR(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vF3PREDISPUTAR.size(); i++) {
			tTemp = (String[]) CobillingConstants.vF3PREDISPUTAR.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vF4PERDASFRAUDES = new Vector(
			Arrays.asList(new String[][] {
					{
							"01",
							"R4P",
							"    Qte ch Cursadas Conciliadas                     				",
							"QT_CDRS", "00", "" },
					{
							"02",
							"R4P",
							"    Qte ch Defeito                                  				",
							"QT_CDRS", "00", "" },
					{
							"03",
							"R4P",
							"    Qte ch Perdidas                                 				",
							"QT_CDRS", "00", "" },
					{
							"04",
							"R4P",
							"    Minutos Cursados Conciliados                    				",
							"VL_BRUTO_CHAMADA", "01", "" },
					{
							"05",
							"R4P",
							"    Minutos Perdidos                                				",
							"VL_BRUTO_CHAMADA", "00", "" },
					{
							"06",
							"R4P",
							"    % Perda Contratual                                           ",
							"PE_RETENCAO", "00", "" },
					{
							"07",
							"R4P",
							"(a) Valor ch Cursadas Conciliadas                   				",
							"VL_BRUTO_CHAMADA", "01", "" },
					{
							"08",
							"R4P",
							"(b) Valor Defeito                                   				",
							"VL_BRUTO_CHAMADA", "00", "" },
					{
							"09",
							"R4P",
							"    % Defeito                                       				",
							"PE_RETENCAO", "00", "" },
					{
							"10",
							"R4P",
							"    P1 Perda Erro Tarifa                            				",
							"VL_BRUTO_CHAMADA", "01", "" },
					{
							"11",
							"R4P",
							"    P2 Perda Não Registrada                         				",
							"VL_BRUTO_CHAMADA", "00", "" },
					{
							"12",
							"R4P",
							"    P3 Perda Reg com Defeito \"Duração\" e \"Valor\"    			",
							"VL_BRUTO_CHAMADA", "00", "" },
					{
							"13",
							"R4P",
							"    P4 Perda Reg com outros Defeitos                             ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{
							"14",
							"R4P",
							"(c) Perdas resp. LD                                 				",
							"VL_BRUTO_CHAMADA", "00", "" },
					{
							"15",
							"R4P",
							"(d) Fraude resp. LD                                 				",
							"VL_BRUTO_CHAMADA", "01", "" },
					{
							"16",
							"R4P",
							"(e) Perdas e Fraude resp. LD (c+d)                  				",
							"VL_BRUTO_CHAMADA", "01", "" },
					{
							"17",
							"R4P",
							"    % Perda                                         				",
							"PE_RETENCAO", "00", "" },
					{
							"18",
							"R4P",
							"    P2 Penalidade Não Registrada                    				",
							"VL_BRUTO_CHAMADA", "01", "" },
					{
							"19",
							"R4P",
							"    P3 Penalidade Reg com Defeito \"Duração\" e \"Valor\"		",
							"VL_BRUTO_CHAMADA", "00", "" },
					{
							"20",
							"R4P",
							"    P4 Penalidade Reg com outros Defeitos                        ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{
							"21",
							"R4P",
							"(f) Penalidade Min Perdidos por Falhas              				",
							"VL_BRUTO_CHAMADA", "00", "" },
					{
							"22",
							"R4P",
							"(g) Penalidade Min Perdidos por Fraude              				",
							"VL_BRUTO_CHAMADA", "01", "" },
					{
							"23",
							"R4P",
							"(h) Penalidades Min Perdidos (f+g)                  				",
							"VL_BRUTO_CHAMADA", "01", "" } }));

	public static final String[] buscaConfigItensF4PERDASFRAUDES(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vF4PERDASFRAUDES.size(); i++) {
			tTemp = (String[]) CobillingConstants.vF4PERDASFRAUDES.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vF5CONSOLIDADO = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "R5P",
							"(a) Total Créditos Queimados                ",
							"VL_BRUTO_CHAMADA", "02",
							"P&L                      " },
					{ "02", "R5P",
							"(b) Valor Bruto Apurado                     ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "03", "R5P",
							"(c) ICMS não Repassado                      ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "04", "R5P",
							"(d) Diferença Apuração Créditos             ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "05", "R5P", "(e) Créditos Anatel 226	             	   ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "06", "R5P",
							"(f) Créditos Autorizados                    ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "07", "R5P", "(g) Diferença Ajuste Créditos        	   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "08", "R5P", "(h) Receita Serviços Líquida    	  		   ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "09", "R5P", "(i) Acertos contra LD          	  		   ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "10", "R5P",
							"(j) Recebível Total Líquido (d+g+h+i)   	   ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "11", "R5P",
							"(l) Penalidades por Minutos Perdidos        ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "12", "R5P", "(m) Acertos contra Claro 	  			   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "13", "R5P", "(n) Custo Total (l+m)			           ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "14", "R5P",
							"(o) Resultado (j-n)                         ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "15", "R5P", "    Valor Final a Repassar          		   ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "16", "R5P", "    Repasse Liquidado          	  		   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "17", "R5P", "    Perdas resp. LD 						   ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "18", "R5P", "    Fraude resp. LD              	  	   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "19", "R5P",
							"    Perdas e Fraude resp. LD                ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "20", "R5P",
							"    Penalidade por Atraso contra Claro      ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "21", "R5P",
							"    Penalidade por Atraso contra LD         ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "22", "R5P",
							"    Provisão CAP Operadoras         	       ",
							"VL_BRUTO_CHAMADA", "02",
							"BS                      " },
					{ "23", "R5P",
							"    Provisão para Penalidades (Min Perdidos)",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "24", "R5P", "    Provisão para Acertos (Disputas)		   ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "25", "R5P",
							"    Saldo CAP Operadoras            	  	   ",
							"VL_BRUTO_CHAMADA", "01", "" },
					{ "26", "R5P",
							"    Saldo Penalidades Potenciais            ",
							"VL_BRUTO_CHAMADA", "00", "" },
					{ "27", "R5P",
							"    Saldo Disputas Iniciadas        	  	   ",
							"VL_BRUTO_CHAMADA", "00", "" } }));

	public static final String[] buscaConfigItensF5CONSOLIDADO(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIO = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vF5CONSOLIDADO.size(); i++) {
			tTemp = (String[]) CobillingConstants.vF5CONSOLIDADO.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIO = tTemp;
				break;
			}
		}
		return tITENSRELATORIO;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Vector vITENSRELATORIORR = new Vector(
			Arrays.asList(new String[][] {
					{ "01", "RR", "Fator de Imposto e CS           	",
							"PE_FATOR_IMPOSTO", "00", "" },
					{ "02", "RR", "Desconto Contratual             	",
							"PE_RETENCAO", "00", "" } }));

	public static final String[] buscaConfigItensRelatorioRR(String codigo) {
		codigo = (codigo.length() > 1 ? codigo : "0" + codigo);
		String[] tITENSRELATORIORR = null;
		String[] tTemp = null;
		for (int i = 0; i < CobillingConstants.vITENSRELATORIORR.size(); i++) {
			tTemp = (String[]) CobillingConstants.vITENSRELATORIORR
					.elementAt(i);
			if (codigo.equalsIgnoreCase(tTemp[0])) {
				tITENSRELATORIORR = tTemp;
				break;
			}
		}
		return tITENSRELATORIORR;
	}

}
