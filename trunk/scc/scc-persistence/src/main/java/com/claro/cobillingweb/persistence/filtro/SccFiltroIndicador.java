package com.claro.cobillingweb.persistence.filtro;

import java.io.Serializable;

public class SccFiltroIndicador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5733242179463433072L;
	
	
	private String cdTipoContrato;
	
	private String idIndicador;
	
	private String tipo;

	public String getCdTipoContrato() {
		return cdTipoContrato;
	}

	public void setCdTipoContrato(String cdTipoContrato) {
		this.cdTipoContrato = cdTipoContrato;
	}

	public String getIdIndicador() {
		return idIndicador;
	}

	public void setIdIndicador(String idIndicador) {
		this.idIndicador = idIndicador;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
	
	

}
