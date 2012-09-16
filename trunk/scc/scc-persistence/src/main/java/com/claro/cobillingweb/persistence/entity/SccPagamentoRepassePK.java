package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class SccPagamentoRepassePK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cdEotLd;
	private String cdEotHolding;
	private java.util.Date dtReferencia;
	private String cdTipoContrato;
	private long nuRepasse;

    public SccPagamentoRepassePK() {
    }

	@Column(name="CD_EOT_LD")
	public String getCdEotLd() {
		return this.cdEotLd;
	}
	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	@Column(name="CD_EOT_HOLDING")
	public String getCdEotHolding() {
		return this.cdEotHolding;
	}
	public void setCdEotHolding(String cdEotHolding) {
		this.cdEotHolding = cdEotHolding;
	}

    @Temporal( TemporalType.DATE)
	@Column(name="DT_REFERENCIA")
	public java.util.Date getDtReferencia() {
		return this.dtReferencia;
	}
	public void setDtReferencia(java.util.Date dtReferencia) {
		this.dtReferencia = dtReferencia;
	}

	@Column(name="CD_TIPO_CONTRATO")
	public String getCdTipoContrato() {
		return this.cdTipoContrato;
	}
	public void setCdTipoContrato(String cdTipoContrato) {
		this.cdTipoContrato = cdTipoContrato;
	}

	@Column(name="NU_REPASSE")
	public long getNuRepasse() {
		return this.nuRepasse;
	}
	public void setNuRepasse(long nuRepasse) {
		this.nuRepasse = nuRepasse;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccPagamentoRepassePK)) {
			return false;
		}
		SccPagamentoRepassePK castOther = (SccPagamentoRepassePK)other;
		return 
			this.cdEotLd.equals(castOther.cdEotLd)
			&& this.cdEotHolding.equals(castOther.cdEotHolding)
			&& this.dtReferencia.equals(castOther.dtReferencia)
			&& this.cdTipoContrato.equals(castOther.cdTipoContrato)
			&& (this.nuRepasse == castOther.nuRepasse);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdEotLd.hashCode();
		hash = hash * prime + this.cdEotHolding.hashCode();
		hash = hash * prime + this.dtReferencia.hashCode();
		hash = hash * prime + this.cdTipoContrato.hashCode();
		hash = hash * prime + ((int) (this.nuRepasse ^ (this.nuRepasse >>> 32)));
		
		return hash;
    }
	
}
