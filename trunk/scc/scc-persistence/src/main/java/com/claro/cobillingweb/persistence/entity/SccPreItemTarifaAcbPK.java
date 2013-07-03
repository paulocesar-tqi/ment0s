package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SccPreItemTarifaAcbPK implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long  sqPreTarifaAcb;
	private String sgUf;

    public SccPreItemTarifaAcbPK() {
    }

	@Column(name="SQ_PRE_TARIFA_ACB")
	public Long getSqPreTarifaAcb() {
		return sqPreTarifaAcb;
	}

	public void setSqPreTarifaAcb(Long sqPreTarifaAcb) {
		this.sqPreTarifaAcb = sqPreTarifaAcb;
	}
	
	//@ManyToOne
	//@JoinColumn(name="SQ_PRE_TARIFA_ACB")
	

	@Column(name="SG_UF")
	public String getSgUf() {
		return this.sgUf;
	}
	public void setSgUf(String sgUf) {
		this.sgUf = sgUf;
	}

/*	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccPreItemTarifaAcbPK)) {
			return false;
		}
		SccPreItemTarifaAcbPK castOther = (SccPreItemTarifaAcbPK)other;
		return 
			(this.sqPreTarifaAcb == castOther.sqPreTarifaAcb)
			&& this.sgUf.equals(castOther.sgUf);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sqPreTarifaAcb ^ (this.sqPreTarifaAcb >>> 32)));
		hash = hash * prime + this.sgUf.hashCode();
		
		return hash;
    }*/
	
}
