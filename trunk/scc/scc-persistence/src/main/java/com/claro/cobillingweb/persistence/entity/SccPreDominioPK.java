package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SccPreDominioPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cdDominio;
	private String tpDominio;

    public SccPreDominioPK() {
    }

	@Column(name="CD_DOMINIO")
	public String getCdDominio() {
		return this.cdDominio;
	}
	public void setCdDominio(String cdDominio) {
		this.cdDominio = cdDominio;
	}

	@Column(name="TP_DOMINIO")
	public String getTpDominio() {
		return this.tpDominio;
	}
	public void setTpDominio(String tpDominio) {
		this.tpDominio = tpDominio;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccPreDominioPK)) {
			return false;
		}
		SccPreDominioPK castOther = (SccPreDominioPK)other;
		return 
			this.cdDominio.equals(castOther.cdDominio)
			&& this.tpDominio.equals(castOther.tpDominio);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdDominio.hashCode();
		hash = hash * prime + this.tpDominio.hashCode();
		
		return hash;
    }
	
}
