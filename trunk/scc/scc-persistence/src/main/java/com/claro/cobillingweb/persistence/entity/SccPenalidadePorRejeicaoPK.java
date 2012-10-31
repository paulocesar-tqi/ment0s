package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SccPenalidadePorRejeicaoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cdEotLd;
	private String cdMotivoRejeicao;

    public SccPenalidadePorRejeicaoPK() {
    }

	@Column(name="CD_EOT_LD")
	public String getCdEotLd() {
		return this.cdEotLd;
	}
	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	@Column(name="CD_MOTIVO_REJEICAO")
	public String getCdMotivoRejeicao() {
		return cdMotivoRejeicao;
	}
	public void setCdMotivoRejeicao(String cdMotivoRejeicao) {
		this.cdMotivoRejeicao = cdMotivoRejeicao;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccPenalidadePorRejeicaoPK)) {
			return false;
		}
		SccPenalidadePorRejeicaoPK castOther = (SccPenalidadePorRejeicaoPK)other;
		return 
			this.cdEotLd.equals(castOther.cdEotLd)
			&& this.cdMotivoRejeicao.equals(castOther.cdMotivoRejeicao);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdEotLd.hashCode();
		hash = hash * prime + this.cdMotivoRejeicao.hashCode();
		
		return hash;
    }
	
}
