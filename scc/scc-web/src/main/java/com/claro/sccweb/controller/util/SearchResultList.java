package com.claro.sccweb.controller.util;

import java.util.List;



/**
 * Resultados de pesquisa serão sempre carregados nesse bean. 
 * 
 *
 */
public class SearchResultList {

	 private String ticket;
	 
	 private Class resultClassType;
	 
	 
	 private List result;
	 
	 
	 	 
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Class getResultClassType() {
		return resultClassType;
	}

	public void setResultClassType(Class resultClassType) {
		this.resultClassType = resultClassType;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	
	 
	
}
