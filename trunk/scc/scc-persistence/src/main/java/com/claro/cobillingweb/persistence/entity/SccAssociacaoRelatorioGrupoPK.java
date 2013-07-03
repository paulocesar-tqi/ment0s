package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The primary key class for the SCC_ASSOCIACAO_RELATORIO_GRUPO database table.
 * 
 */
@Embeddable
public class SccAssociacaoRelatorioGrupoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SQ_RELATORIO")
	private Long sqRelatorio;

	@Column(name="SQ_GRUPO")
	private Long sqGrupo;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_INICIO_VIGENCIA")
	private java.util.Date dtInicioVigencia;

	public SccAssociacaoRelatorioGrupoPK() {
	}
	public Long getSqRelatorio() {
		return this.sqRelatorio;
	}
	public void setSqRelatorio(Long sqRelatorio) {
		this.sqRelatorio = sqRelatorio;
	}
	public Long getSqGrupo() {
		return this.sqGrupo;
	}
	public void setSqGrupo(Long sqGrupo) {
		this.sqGrupo = sqGrupo;
	}
	public Date getDtInicioVigencia() {
		return this.dtInicioVigencia;
	}
	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccAssociacaoRelatorioGrupoPK)) {
			return false;
		}
		SccAssociacaoRelatorioGrupoPK castOther = (SccAssociacaoRelatorioGrupoPK)other;
		return 
			(this.sqRelatorio == castOther.sqRelatorio)
			&& (this.sqGrupo == castOther.sqGrupo)
			&& this.dtInicioVigencia.equals(castOther.dtInicioVigencia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sqRelatorio ^ (this.sqRelatorio >>> 32)));
		hash = hash * prime + ((int) (this.sqGrupo ^ (this.sqGrupo >>> 32)));
		hash = hash * prime + this.dtInicioVigencia.hashCode();
		
		return hash;
	}
}