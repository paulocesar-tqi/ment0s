/**
 * 
 */
package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author vagner.souza
 *
 */
/**
 * The primary key class for the SCC_DISPUTA_DETALHE_PRE_PAGO database table.
 * 
 */
@Embeddable
public class SccDisputaDetalhePrePagoPK implements Serializable {
	
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SQ_DISPUTA_PRE_PAGO")
	private long sqDisputaPrePago;

	@Column(name="CD_EOT_CLARO")
	private String cdEotClaro;

    public SccDisputaDetalhePrePagoPK() {
    }
	public long getSqDisputaPrePago() {
		return this.sqDisputaPrePago;
	}
	public void setSqDisputaPrePago(long sqDisputaPrePago) {
		this.sqDisputaPrePago = sqDisputaPrePago;
	}
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
		if (!(other instanceof SccDisputaDetalhePrePagoPK)) {
			return false;
		}
		SccDisputaDetalhePrePagoPK castOther = (SccDisputaDetalhePrePagoPK)other;
		return 
			(this.sqDisputaPrePago == castOther.sqDisputaPrePago)
			&& this.cdEotClaro.equals(castOther.cdEotClaro);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sqDisputaPrePago ^ (this.sqDisputaPrePago >>> 32)));
		hash = hash * prime + this.cdEotClaro.hashCode();
		
		return hash;
    }


}
