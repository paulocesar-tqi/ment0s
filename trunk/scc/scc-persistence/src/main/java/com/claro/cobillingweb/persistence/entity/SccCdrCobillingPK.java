package com.claro.cobillingweb.persistence.entity;


import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SCC_CDR_COBILLING database table.
 * 
 */
@Embeddable
public class SccCdrCobillingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String nuMsisdnOrigem;
	private String nuTelefoneDestino;
	private java.util.Date dtChamada;
	private String hrInicioChamada;
	private String cdRefaturamento;

    public SccCdrCobillingPK() {
    }

	@Column(name="NU_MSISDN_ORIGEM")
	public String getNuMsisdnOrigem() {
		return this.nuMsisdnOrigem;
	}
	public void setNuMsisdnOrigem(String nuMsisdnOrigem) {
		this.nuMsisdnOrigem = nuMsisdnOrigem;
	}

	@Column(name="NU_TELEFONE_DESTINO")
	public String getNuTelefoneDestino() {
		return this.nuTelefoneDestino;
	}
	public void setNuTelefoneDestino(String nuTelefoneDestino) {
		this.nuTelefoneDestino = nuTelefoneDestino;
	}

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CHAMADA")
	public java.util.Date getDtChamada() {
		return this.dtChamada;
	}
	public void setDtChamada(java.util.Date dtChamada) {
		this.dtChamada = dtChamada;
	}

	@Column(name="HR_INICIO_CHAMADA")
	public String getHrInicioChamada() {
		return this.hrInicioChamada;
	}
	public void setHrInicioChamada(String hrInicioChamada) {
		this.hrInicioChamada = hrInicioChamada;
	}

	@Column(name="CD_REFATURAMENTO")
	public String getCdRefaturamento() {
		return this.cdRefaturamento;
	}
	public void setCdRefaturamento(String cdRefaturamento) {
		this.cdRefaturamento = cdRefaturamento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccCdrCobillingPK)) {
			return false;
		}
		SccCdrCobillingPK castOther = (SccCdrCobillingPK)other;
		return 
			this.nuMsisdnOrigem.equals(castOther.nuMsisdnOrigem)
			&& this.nuTelefoneDestino.equals(castOther.nuTelefoneDestino)
			&& this.dtChamada.equals(castOther.dtChamada)
			&& (this.hrInicioChamada == castOther.hrInicioChamada)
			&& this.cdRefaturamento.equals(castOther.cdRefaturamento);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nuMsisdnOrigem.hashCode();
		hash = hash * prime + this.nuTelefoneDestino.hashCode();
		hash = hash * prime + this.dtChamada.hashCode();		
		hash = hash * prime + this.cdRefaturamento.hashCode();
		
		return hash;
    }
}