package com.claro.cobillingweb.persistence.dao;

/**
 * Exception associada a operacoes de DAO.
 *
 */
public class DAOException extends Exception {

	private String query;
	
	public DAOException() {
		super();		
	}

	public DAOException(String message) {
		
		super(message);
	}

	
	public DAOException(String message,String query) {
		
		super(message);
	}
	
	

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DAOException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	
}
