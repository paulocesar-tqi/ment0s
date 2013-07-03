package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the SCC_DISTRIBUICAO_RELATORIO database table.
 * 
 */
@Embeddable
public class SccDistribuicaoRelatorioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SQ_RELATORIO_SUMARIZADO")
	private Long sqRelatorioSumarizado;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_RELATORIO")
	private Date dtRelatorio;

	@Column(name="SQ_GRUPO")
	private Long sqGrupo;

    public SccDistribuicaoRelatorioPK() {
    }
	public Long getSqRelatorioSumarizado() {
		return this.sqRelatorioSumarizado;
	}
	public void setSqRelatorioSumarizado(Long sqRelatorioSumarizado) {
		this.sqRelatorioSumarizado = sqRelatorioSumarizado;
	}
	public Date getDtRelatorio() {
		return this.dtRelatorio;
	}
	public void setDtRelatorio(Date dtRelatorio) {
		this.dtRelatorio = dtRelatorio;
	}
	public Long getSqGrupo() {
		return this.sqGrupo;
	}
	public void setSqGrupo(Long sqGrupo) {
		this.sqGrupo = sqGrupo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccDistribuicaoRelatorioPK)) {
			return false;
		}
		SccDistribuicaoRelatorioPK castOther = (SccDistribuicaoRelatorioPK)other;
		return 
			(this.sqRelatorioSumarizado == castOther.sqRelatorioSumarizado)
			&& this.dtRelatorio.equals(castOther.dtRelatorio)
			&& (this.sqGrupo == castOther.sqGrupo);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sqRelatorioSumarizado ^ (this.sqRelatorioSumarizado >>> 32)));
		hash = hash * prime + this.dtRelatorio.hashCode();
		hash = hash * prime + ((int) (this.sqGrupo ^ (this.sqGrupo >>> 32)));
		
		return hash;
    }
}