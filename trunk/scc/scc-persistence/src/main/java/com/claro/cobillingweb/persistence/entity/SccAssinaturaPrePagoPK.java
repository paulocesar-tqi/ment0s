package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SccAssinaturaPrePagoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long sqAssinaturaPrepago;
	private Date dtProcExterna;

	public SccAssinaturaPrePagoPK() {
	}

	@Column(name = "SQ_ASSINATURA_PREPAGO")
	public long getSqAssinaturaPrepago() {
		return sqAssinaturaPrepago;
	}

	public void setSqAssinaturaPrepago(long sqAssinaturaPrepago) {
		this.sqAssinaturaPrepago = sqAssinaturaPrepago;
	}

	@Column(name = "DT_PROC_EXTERNA")
	public Date getDtProcExterna() {
		return dtProcExterna;
	}

	public void setDtProcExterna(Date dtProcExterna) {
		this.dtProcExterna = dtProcExterna;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccAssinaturaPrePagoPK)) {
			return false;
		}
		SccAssinaturaPrePagoPK castOther = (SccAssinaturaPrePagoPK) other;
		return this.sqAssinaturaPrepago == castOther.sqAssinaturaPrepago
				&& this.dtProcExterna.equals(castOther.dtProcExterna);

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + new Long(this.sqAssinaturaPrepago).hashCode();
		hash = hash * prime + this.dtProcExterna.hashCode();

		return hash;
	}

}
