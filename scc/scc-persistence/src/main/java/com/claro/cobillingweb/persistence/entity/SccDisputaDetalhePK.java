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
@Embeddable
public class SccDisputaDetalhePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5238490164032395212L;
	
	
	@Column(name="SQ_DISPUTA")
	private Long sqDisputa;

	@Column(name="CD_EOT_CLARO")
	private String cdEotClaro;

    public SccDisputaDetalhePK() {
    }
	public Long getSqDisputa() {
		return this.sqDisputa;
	}
	public void setSqDisputa(Long sqDisputa) {
		this.sqDisputa = sqDisputa;
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
		if (!(other instanceof SccDisputaDetalhePK)) {
			return false;
		}
		SccDisputaDetalhePK castOther = (SccDisputaDetalhePK)other;
		return 
			(this.sqDisputa == castOther.sqDisputa)
			&& this.cdEotClaro.equals(castOther.cdEotClaro);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sqDisputa ^ (this.sqDisputa >>> 32)));
		hash = hash * prime + this.cdEotClaro.hashCode();
		
		return hash;
    }
	

}
