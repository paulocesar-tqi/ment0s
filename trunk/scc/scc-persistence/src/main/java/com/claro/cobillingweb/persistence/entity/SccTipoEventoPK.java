package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SccTipoEventoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String inCategoriaEvento;
	private String cdTipoEvento;

    public SccTipoEventoPK() {
    }

	@Column(name="IN_CATEGORIA_EVENTO")
	public String getInCategoriaEvento() {
		return this.inCategoriaEvento;
	}
	public void setInCategoriaEvento(String inCategoriaEvento) {
		this.inCategoriaEvento = inCategoriaEvento;
	}

	@Column(name="CD_TIPO_EVENTO")
	public String getCdTipoEvento() {
		return this.cdTipoEvento;
	}
	public void setCdTipoEvento(String cdTipoEvento) {
		this.cdTipoEvento = cdTipoEvento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccTipoEventoPK)) {
			return false;
		}
		SccTipoEventoPK castOther = (SccTipoEventoPK)other;
		return 
			this.inCategoriaEvento.equals(castOther.inCategoriaEvento)
			&& this.cdTipoEvento.equals(castOther.cdTipoEvento);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.inCategoriaEvento.hashCode();
		hash = hash * prime + this.cdTipoEvento.hashCode();
		
		return hash;
    }
}
