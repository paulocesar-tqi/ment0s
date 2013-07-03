package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SCC_CONTESTACAO_DETALHE_PRE database table.
 * 
 */
@Embeddable
public class SccContestacaoDetalhePrePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SQ_CONTESTACAO_PRE_PAGO")
	private long sqContestacaoPrePago;

	@Column(name="CD_EOT_CLARO")
	private String cdEotClaro;

    public SccContestacaoDetalhePrePK() {
    }
	public long getSqContestacaoPrePago() {
		return this.sqContestacaoPrePago;
	}
	public void setSqContestacaoPrePago(long sqContestacaoPrePago) {
		this.sqContestacaoPrePago = sqContestacaoPrePago;
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
		if (!(other instanceof SccContestacaoDetalhePrePK)) {
			return false;
		}
		SccContestacaoDetalhePrePK castOther = (SccContestacaoDetalhePrePK)other;
		return 
			(this.sqContestacaoPrePago == castOther.sqContestacaoPrePago)
			&& this.cdEotClaro.equals(castOther.cdEotClaro);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sqContestacaoPrePago ^ (this.sqContestacaoPrePago >>> 32)));
		hash = hash * prime + this.cdEotClaro.hashCode();
		
		return hash;
    }
}