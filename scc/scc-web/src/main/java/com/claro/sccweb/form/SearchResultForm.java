package com.claro.sccweb.form;


/**
 * Formulário (form bean) genérico com ações a serem realizas sobre resultados de pesquisas. 
 *
 */
public class SearchResultForm {

	/**
	 * Resete na pequisa.
	 */
	public static final String COMMAND_RESET = "COMMAND_RESET";
	
	/**
	 *  Voltar à tela anterior.
	 */
	public static final String COMMAND_BACK = "COMMAND_BACK";
	
	/**
	 * Exportar para Excel.
	 */
	public static final String COMMAND_EXCEL = "COMMAND_EXCEL";
	
	
	
	/**
	 * Operação a ser realizada.
	 */
	private String operation;



	



	public String getOperation() {
		return operation;
	}



	public void setOperation(String operation) {
		this.operation = operation;
	}



	public static String getCommandReset() {
		return COMMAND_RESET;
	}



	public static String getCommandBack() {
		return COMMAND_BACK;
	}



	public static String getCommandExcel() {
		return COMMAND_EXCEL;
	}
	
	
	
}
