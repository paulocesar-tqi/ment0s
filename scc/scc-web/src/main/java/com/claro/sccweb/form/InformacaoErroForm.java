package com.claro.sccweb.form;

/**
 * Form bean usado para mostrar informações de erros de sistem.
 *
 */
public class InformacaoErroForm {

	private String mensagem;
	
	private String stackTrace;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	
	
	
}
