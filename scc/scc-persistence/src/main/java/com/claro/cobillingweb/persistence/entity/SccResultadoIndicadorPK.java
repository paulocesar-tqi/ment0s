package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the SCC_RESULTADO_INDICADOR database table.
 * 
 */
@Embeddable
public class SccResultadoIndicadorPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CD_INDICADOR")
	private String cdIndicador;

	@Column(name="DT_REFERENCIA")
	private String dtReferencia;

	@Column(name="CD_EOT_LD")
	private String cdEotLd;

	@Column(name="CD_REGIONAL")
	private String cdRegional;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_PERIODO")
	private Date dtPeriodo;

	@Column(name="SQ_AGING_INDICADOR")
	private Long sqAgingIndicador;

    public SccResultadoIndicadorPK() {
    }
	public String getCdIndicador() {
		return this.cdIndicador;
	}
	public void setCdIndicador(String cdIndicador) {
		this.cdIndicador = cdIndicador;
	}
	public String getDtReferencia() {
		return this.dtReferencia;
	}
	public void setDtReferencia(String dtReferencia) {
		this.dtReferencia = dtReferencia;
	}
	public String getCdEotLd() {
		return this.cdEotLd;
	}
	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}
	public String getCdRegional() {
		return this.cdRegional;
	}
	public void setCdRegional(String cdRegional) {
		this.cdRegional = cdRegional;
	}
	public java.util.Date getDtPeriodo() {
		return this.dtPeriodo;
	}
	public void setDtPeriodo(java.util.Date dtPeriodo) {
		this.dtPeriodo = dtPeriodo;
	}
	public Long getSqAgingIndicador() {
		return this.sqAgingIndicador;
	}
	public void setSqAgingIndicador(Long sqAgingIndicador) {
		this.sqAgingIndicador = sqAgingIndicador;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccResultadoIndicadorPK)) {
			return false;
		}
		SccResultadoIndicadorPK castOther = (SccResultadoIndicadorPK)other;
		return 
			this.cdIndicador.equals(castOther.cdIndicador)
			&& this.dtReferencia.equals(castOther.dtReferencia)
			&& this.cdEotLd.equals(castOther.cdEotLd)
			&& this.cdRegional.equals(castOther.cdRegional)
			&& this.dtPeriodo.equals(castOther.dtPeriodo)
			&& (this.sqAgingIndicador == castOther.sqAgingIndicador);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdIndicador.hashCode();
		hash = hash * prime + this.dtReferencia.hashCode();
		hash = hash * prime + this.cdEotLd.hashCode();
		hash = hash * prime + this.cdRegional.hashCode();
		hash = hash * prime + this.dtPeriodo.hashCode();
		hash = hash * prime + ((int) (this.sqAgingIndicador ^ (this.sqAgingIndicador >>> 32)));
		
		return hash;
    }
}