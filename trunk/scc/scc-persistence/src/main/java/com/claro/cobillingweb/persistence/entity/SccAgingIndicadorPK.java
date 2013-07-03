package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the SCC_AGING_INDICADOR database table.
 * 
 */
@Embeddable
public class SccAgingIndicadorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	
	@Column(name="SQ_AGING_INDICADOR")
	private Long sqAgingIndicador;

	@Column(name="CD_INDICADOR")
	private String cdIndicador;

    public SccAgingIndicadorPK() {
    }
	public Long getSqAgingIndicador() {
		return this.sqAgingIndicador;
	}
	public void setSqAgingIndicador(Long sqAgingIndicador) {
		this.sqAgingIndicador = sqAgingIndicador;
	}
	public String getCdIndicador() {
		return this.cdIndicador;
	}
	public void setCdIndicador(String cdIndicador) {
		this.cdIndicador = cdIndicador;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccAgingIndicadorPK)) {
			return false;
		}
		SccAgingIndicadorPK castOther = (SccAgingIndicadorPK)other;
		return 
			(this.sqAgingIndicador == castOther.sqAgingIndicador)
			&& this.cdIndicador.equals(castOther.cdIndicador);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sqAgingIndicador ^ (this.sqAgingIndicador >>> 32)));
		hash = hash * prime + this.cdIndicador.hashCode();
		
		return hash;
    }
}