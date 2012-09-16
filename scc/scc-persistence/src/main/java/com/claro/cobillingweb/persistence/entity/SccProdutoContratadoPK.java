package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SccProdutoContratadoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private long cdContratoCobilling;
	private long cdProdutoCobilling;

    public SccProdutoContratadoPK() {
    }

    
	@Column(name="CD_CONTRATO_COBILLING")
	public long getCdContratoCobilling() {
		return this.cdContratoCobilling;
	}
	public void setCdContratoCobilling(long cdContratoCobilling) {
		this.cdContratoCobilling = cdContratoCobilling;
	}

	@Column(name="CD_PRODUTO_COBILLING")
	public long getCdProdutoCobilling() {
		return this.cdProdutoCobilling;
	}
	public void setCdProdutoCobilling(long cdProdutoCobilling) {
		this.cdProdutoCobilling = cdProdutoCobilling;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccProdutoContratadoPK)) {
			return false;
		}
		SccProdutoContratadoPK castOther = (SccProdutoContratadoPK)other;
		return 
			(this.cdContratoCobilling == castOther.cdContratoCobilling)
			&& (this.cdProdutoCobilling == castOther.cdProdutoCobilling);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.cdContratoCobilling ^ (this.cdContratoCobilling >>> 32)));
		hash = hash * prime + ((int) (this.cdProdutoCobilling ^ (this.cdProdutoCobilling >>> 32)));
		
		return hash;
    }
	
	
}
