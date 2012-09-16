package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SccAssinanteCriticaPK implements Serializable {

	private String cdArea;
	private String nuPrefixo;
	private String nuSufixoInicial;
	private String nuSufixoFinal;
	private String cdCsp;

    public SccAssinanteCriticaPK() {
    }

	@Column(name="CD_AREA")
	public String getCdArea() {
		return this.cdArea;
	}
	public void setCdArea(String cdArea) {
		this.cdArea = cdArea;
	}

	@Column(name="NU_PREFIXO")
	public String getNuPrefixo() {
		return this.nuPrefixo;
	}
	public void setNuPrefixo(String nuPrefixo) {
		this.nuPrefixo = nuPrefixo;
	}

	@Column(name="NU_SUFIXO_INICIAL")
	public String getNuSufixoInicial() {
		return this.nuSufixoInicial;
	}
	public void setNuSufixoInicial(String nuSufixoInicial) {
		this.nuSufixoInicial = nuSufixoInicial;
	}

	@Column(name="NU_SUFIXO_FINAL")
	public String getNuSufixoFinal() {
		return this.nuSufixoFinal;
	}
	public void setNuSufixoFinal(String nuSufixoFinal) {
		this.nuSufixoFinal = nuSufixoFinal;
	}

	@Column(name="CD_CSP")
	public String getCdCsp() {
		return this.cdCsp;
	}
	public void setCdCsp(String cdCsp) {
		this.cdCsp = cdCsp;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccAssinanteCriticaPK)) {
			return false;
		}
		SccAssinanteCriticaPK castOther = (SccAssinanteCriticaPK)other;
		return 
			this.cdArea.equals(castOther.cdArea)
			&& this.nuPrefixo.equals(castOther.nuPrefixo)
			&& this.nuSufixoInicial.equals(castOther.nuSufixoInicial)
			&& this.nuSufixoFinal.equals(castOther.nuSufixoFinal)
			&& this.cdCsp.equals(castOther.cdCsp);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdArea.hashCode();
		hash = hash * prime + this.nuPrefixo.hashCode();
		hash = hash * prime + this.nuSufixoInicial.hashCode();
		hash = hash * prime + this.nuSufixoFinal.hashCode();
		hash = hash * prime + this.cdCsp.hashCode();
		
		return hash;
    }	
	
}
