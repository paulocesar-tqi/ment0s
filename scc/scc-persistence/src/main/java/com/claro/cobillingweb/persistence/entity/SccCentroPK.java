package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class SccCentroPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cdCentro;
	private String cdEotClaro;

    public SccCentroPK() {
    }

	@Column(name="CD_CENTRO")
	public String getCdCentro() {
		return this.cdCentro;
	}
	public void setCdCentro(String cdCentro) {
		this.cdCentro = cdCentro;
	}

	@Column(name="CD_EOT_CLARO")
	public String getCdEotClaro() {
		return this.cdEotClaro;
	}
	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccCentroPK)) {
			return false;
		}
		SccCentroPK castOther = (SccCentroPK)other;
		return 
			this.cdCentro.equals(castOther.cdCentro)
			&& this.cdEotClaro.equals(castOther.cdEotClaro);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdCentro.hashCode();
		hash = hash * prime + this.cdEotClaro.hashCode();
		
		return hash;
    }
	
}
