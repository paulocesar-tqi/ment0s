package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class SccContratoAcordadoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cdEotLd;
	private String cdEotClaro;
	private long cdContratoCobilling;
	private java.util.Date dtInicioVigencia;

    public SccContratoAcordadoPK() {
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

	@Column(name="CD_CONTRATO_COBILLING")
	public long getCdContratoCobilling() {
		return this.cdContratoCobilling;
	}
	public void setCdContratoCobilling(long cdContratoCobilling) {
		this.cdContratoCobilling = cdContratoCobilling;
	}

    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_VIGENCIA")
	public java.util.Date getDtInicioVigencia() {
		return this.dtInicioVigencia;
	}
	public void setDtInicioVigencia(java.util.Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccContratoAcordadoPK)) {
			return false;
		}
		SccContratoAcordadoPK castOther = (SccContratoAcordadoPK)other;
		return 
			this.cdEotLd.equals(castOther.cdEotLd)
			&& this.cdEotClaro.equals(castOther.cdEotClaro)
			&& (this.cdContratoCobilling == castOther.cdContratoCobilling)
			&& this.dtInicioVigencia.equals(castOther.dtInicioVigencia);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdEotLd.hashCode();
		hash = hash * prime + this.cdEotClaro.hashCode();
		hash = hash * prime + ((int) (this.cdContratoCobilling ^ (this.cdContratoCobilling >>> 32)));
		hash = hash * prime + this.dtInicioVigencia.hashCode();
		
		return hash;
    }
	
}
