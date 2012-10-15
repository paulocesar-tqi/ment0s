package com.claro.sccweb.controller.util;

import java.util.List;



/**
 * Resultados de pesquisa serão sempre carregados nesse bean. 
 * 
 *
 */
public class SearchResultList {

	 private String ticket;
	 
	 @SuppressWarnings("rawtypes")
	private Class resultClassType;
	 
	 
	 @SuppressWarnings("rawtypes")
	private List result;
	 
	 
	 	 
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	@SuppressWarnings("rawtypes")
	public Class getResultClassType() {
		return resultClassType;
	}

	@SuppressWarnings("rawtypes")
	public void setResultClassType(Class resultClassType) {
		this.resultClassType = resultClassType;
	}

	@SuppressWarnings("rawtypes")
	public List getResult() {
		return result;
	}

	@SuppressWarnings("rawtypes")
	public void setResult(List result) {
		this.result = result;
	}

	
	 
	
}
