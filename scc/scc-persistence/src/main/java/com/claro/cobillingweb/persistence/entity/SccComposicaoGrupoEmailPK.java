package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SCC_COMPOSICAO_GRUPO_EMAIL database table.
 * 
 */
@Embeddable
public class SccComposicaoGrupoEmailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SQ_GRUPO")
	private long sqGrupo;

	@Column(name="SQ_EMAIL")
	private long sqEmail;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_INICIO_VIGENCIA")
	private java.util.Date dtInicioVigencia;

	public SccComposicaoGrupoEmailPK() {
	}
	public long getSqGrupo() {
		return this.sqGrupo;
	}
	public void setSqGrupo(long sqGrupo) {
		this.sqGrupo = sqGrupo;
	}
	public long getSqEmail() {
		return this.sqEmail;
	}
	public void setSqEmail(long sqEmail) {
		this.sqEmail = sqEmail;
	}
	public java.util.Date getDtInicioVigencia() {
		return this.dtInicioVigencia;
	}
	public void setDtInicioVigencia(java.util.Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccComposicaoGrupoEmailPK)) {
			return false;
		}
		SccComposicaoGrupoEmailPK castOther = (SccComposicaoGrupoEmailPK)other;
		return 
			(this.sqGrupo == castOther.sqGrupo)
			&& (this.sqEmail == castOther.sqEmail)
			&& this.dtInicioVigencia.equals(castOther.dtInicioVigencia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sqGrupo ^ (this.sqGrupo >>> 32)));
		hash = hash * prime + ((int) (this.sqEmail ^ (this.sqEmail >>> 32)));
		hash = hash * prime + this.dtInicioVigencia.hashCode();
		
		return hash;
	}
}