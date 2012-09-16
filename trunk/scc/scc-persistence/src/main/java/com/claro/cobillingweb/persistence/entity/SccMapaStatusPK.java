package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class SccMapaStatusPK implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long cdStatusDe;
	private Long cdStatusPara;
	private java.util.Date dtInicioVigencia;

    public SccMapaStatusPK() {
    }

	@Column(name="CD_STATUS_DE")
	public Long getCdStatusDe() {
		return this.cdStatusDe;
	}
	public void setCdStatusDe(Long cdStatusDe) {
		this.cdStatusDe = cdStatusDe;
	}

	@Column(name="CD_STATUS_PARA")
	public Long getCdStatusPara() {
		return this.cdStatusPara;
	}
	public void setCdStatusPara(Long cdStatusPara) {
		this.cdStatusPara = cdStatusPara;
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
		if (!(other instanceof SccMapaStatusPK)) {
			return false;
		}
		SccMapaStatusPK castOther = (SccMapaStatusPK)other;
		return 
			(this.cdStatusDe == castOther.cdStatusDe)
			&& (this.cdStatusPara == castOther.cdStatusPara)
			&& this.dtInicioVigencia.equals(castOther.dtInicioVigencia);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.cdStatusDe ^ (this.cdStatusDe >>> 32)));
		hash = hash * prime + ((int) (this.cdStatusPara ^ (this.cdStatusPara >>> 32)));
		hash = hash * prime + this.dtInicioVigencia.hashCode();
		
		return hash;
    }
	
}
