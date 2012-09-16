package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SccDadosBancarioPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cdEotLd;
	private String nuBanco;
	private String nuAgencia;

    public SccDadosBancarioPK() {
    }

	@Column(name="CD_EOT_LD")
	public String getCdEotLd() {
		return this.cdEotLd;
	}
	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	@Column(name="NU_BANCO")
	public String getNuBanco() {
		return this.nuBanco;
	}
	public void setNuBanco(String nuBanco) {
		this.nuBanco = nuBanco;
	}

	@Column(name="NU_AGENCIA")
	public String getNuAgencia() {
		return this.nuAgencia;
	}
	public void setNuAgencia(String nuAgencia) {
		this.nuAgencia = nuAgencia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccDadosBancarioPK)) {
			return false;
		}
		SccDadosBancarioPK castOther = (SccDadosBancarioPK)other;
		return 
			this.cdEotLd.equals(castOther.cdEotLd)
			&& this.nuBanco.equals(castOther.nuBanco)
			&& this.nuAgencia.equals(castOther.nuAgencia);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdEotLd.hashCode();
		hash = hash * prime + this.nuBanco.hashCode();
		hash = hash * prime + this.nuAgencia.hashCode();
		
		return hash;
    }
	
}
