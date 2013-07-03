/**
 * 
 */
package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Transient;

/**
 * @author rodvagne
 *
 */
public abstract class FwjBaseEntidade implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6363680553052516406L;
	
	
	private int metodo = 0;


	@Transient
	public int getMetodo() {
		return metodo;
	}


	public void setMetodo(int metodo) {
		this.metodo = metodo;
	}
	
	

}
