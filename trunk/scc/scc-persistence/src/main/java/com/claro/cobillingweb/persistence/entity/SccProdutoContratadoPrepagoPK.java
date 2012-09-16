package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class SccProdutoContratadoPrepagoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cdEotLd;
	private String cdEotClaro;
	private java.util.Date dtInicioContrato;
	private String cdTipoContrato;
	private String cdProdutoPrepago;
	private String cdTipoEvento;

    public SccProdutoContratadoPrepagoPK() {
    }

	@Column(name="CD_EOT_LD", insertable=false,updatable=false)
	public String getCdEotLd() {
		return this.cdEotLd;
	}
	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}

	@Column(name="CD_EOT_CLARO" , insertable=false,updatable=false)
	public String getCdEotClaro() {
		return this.cdEotClaro;
	}
	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}

    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_CONTRATO", insertable=false,updatable=false)
	public java.util.Date getDtInicioContrato() {
		return this.dtInicioContrato;
	}
	public void setDtInicioContrato(java.util.Date dtInicioContrato) {
		this.dtInicioContrato = dtInicioContrato;
	}

	@Column(name="CD_TIPO_CONTRATO" , insertable=false,updatable=false)
	public String getCdTipoContrato() {
		return this.cdTipoContrato;
	}
	public void setCdTipoContrato(String cdTipoContrato) {
		this.cdTipoContrato = cdTipoContrato;
	}

	@Column(name="CD_PRODUTO_PREPAGO" , insertable=false,updatable=false)
	public String getCdProdutoPrepago() {
		return this.cdProdutoPrepago;
	}
	public void setCdProdutoPrepago(String cdProdutoPrepago) {
		this.cdProdutoPrepago = cdProdutoPrepago;
	}

	@Column(name="CD_TIPO_EVENTO" , insertable=false,updatable=false)
	public String getCdTipoEvento() {
		return this.cdTipoEvento;
	}
	public void setCdTipoEvento(String cdTipoEvento) {
		this.cdTipoEvento = cdTipoEvento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccProdutoContratadoPrepagoPK)) {
			return false;
		}
		SccProdutoContratadoPrepagoPK castOther = (SccProdutoContratadoPrepagoPK)other;
		return 
			this.cdEotLd.equals(castOther.cdEotLd)
			&& this.cdEotClaro.equals(castOther.cdEotClaro)
			&& this.dtInicioContrato.equals(castOther.dtInicioContrato)
			&& this.cdTipoContrato.equals(castOther.cdTipoContrato)
			&& this.cdProdutoPrepago.equals(castOther.cdProdutoPrepago)
			&& this.cdTipoEvento.equals(castOther.cdTipoEvento);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdEotLd.hashCode();
		hash = hash * prime + this.cdEotClaro.hashCode();
		hash = hash * prime + this.dtInicioContrato.hashCode();
		hash = hash * prime + this.cdTipoContrato.hashCode();
		hash = hash * prime + this.cdProdutoPrepago.hashCode();
		hash = hash * prime + this.cdTipoEvento.hashCode();
		
		return hash;
    }
}
