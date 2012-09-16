package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * A chave para não inserir requisições duplicadas é:
 * cdProcesso = REPASSE 
 * nmProcess =CD EOT DA LD (TAMANHO 6) + PERIODO NO FORMATO MMYYYY + CD EOT CLARO (TAMANHO 6) + CD PRODUTO (TAMANHO 6).
 */

@Embeddable
public class SccParamProcessoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cdProcesso;
	private String nmParametro;

    public SccParamProcessoPK() {
    }

   
	@Column(name="CD_PROCESSO")
	public String getCdProcesso() {
		return this.cdProcesso;
	}
	public void setCdProcesso(String cdProcesso) {
		this.cdProcesso = cdProcesso;
	}

	@Column(name="NM_PARAMETRO")
	public String getNmParametro() {
		return this.nmParametro;
	}
	public void setNmParametro(String nmParametro) {
		this.nmParametro = nmParametro;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccParamProcessoPK)) {
			return false;
		}
		SccParamProcessoPK castOther = (SccParamProcessoPK)other;
		return 
			this.cdProcesso.equals(castOther.cdProcesso)
			&& this.nmParametro.equals(castOther.nmParametro);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdProcesso.hashCode();
		hash = hash * prime + this.nmParametro.hashCode();
		
		return hash;
    }
	
	
}
