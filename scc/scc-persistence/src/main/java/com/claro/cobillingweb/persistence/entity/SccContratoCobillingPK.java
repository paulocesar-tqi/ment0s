package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class SccContratoCobillingPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cdEotLd;
	private String cdEotClaro;
	private java.util.Date dtInicioContrato;
	private String cdTipoContrato;

    public SccContratoCobillingPK() {
    }

	@Column(name="CD_EOT_LD")
	public String getCdEotLd() {
		return this.cdEotLd;
	}
	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	@Column(name="CD_EOT_CLARO")
	public String getCdEotClaro() {
		return this.cdEotClaro;
	}
	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}

    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_CONTRATO")
	public java.util.Date getDtInicioContrato() {
		return this.dtInicioContrato;
	}
	public void setDtInicioContrato(java.util.Date dtInicioContrato) {
		this.dtInicioContrato = dtInicioContrato;
	}

	@Column(name="CD_TIPO_CONTRATO")
	public String getCdTipoContrato() {
		return this.cdTipoContrato;
	}
	public void setCdTipoContrato(String cdTipoContrato) {
		this.cdTipoContrato = cdTipoContrato;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccContratoCobillingPK)) {
			return false;
		}
		SccContratoCobillingPK castOther = (SccContratoCobillingPK)other;
		return 
			this.cdEotLd.equals(castOther.cdEotLd)
			&& this.cdEotClaro.equals(castOther.cdEotClaro)
			&& this.dtInicioContrato.equals(castOther.dtInicioContrato)
			&& this.cdTipoContrato.equals(castOther.cdTipoContrato);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdEotLd.hashCode();
		hash = hash * prime + this.cdEotClaro.hashCode();
		hash = hash * prime + this.dtInicioContrato.hashCode();
		hash = hash * prime + this.cdTipoContrato.hashCode();
		
		return hash;
    }
}
