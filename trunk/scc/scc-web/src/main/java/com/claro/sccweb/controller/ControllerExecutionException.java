package com.claro.sccweb.controller;

/**
 * Exception que indica que o Controller encontrou um erro em seus parâmetros de
 * entrada ou na lógica esperada de execução.
 *
 */
public class ControllerExecutionException extends Exception {

	private String controllerName;
	
	public ControllerExecutionException() {
		super();	
	}

	public ControllerExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerExecutionException(String controllerName,String message) {
		super(message);
	}
	
	public ControllerExecutionException(String message) {
		super(message);
	}

	public ControllerExecutionException(Throwable cause) {
		super(cause);
	}

	public String getControllerName() {
		return controllerName;
	}

	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	
	
}
