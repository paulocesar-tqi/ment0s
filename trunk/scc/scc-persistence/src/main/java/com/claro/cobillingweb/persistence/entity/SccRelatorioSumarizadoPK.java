package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SCC_RELATORIO_SUMARIZADO database table.
 * 
 */
@Embeddable
public class SccRelatorioSumarizadoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SQ_RELATORIO_SUMARIZADO")
	private long sqRelatorioSumarizado;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_RELATORIO")
	private java.util.Date dtRelatorio;

    public SccRelatorioSumarizadoPK() {
    }
	public long getSqRelatorioSumarizado() {
		return this.sqRelatorioSumarizado;
	}
	public void setSqRelatorioSumarizado(long sqRelatorioSumarizado) {
		this.sqRelatorioSumarizado = sqRelatorioSumarizado;
	}
	public java.util.Date getDtRelatorio() {
		return this.dtRelatorio;
	}
	public void setDtRelatorio(java.util.Date dtRelatorio) {
		this.dtRelatorio = dtRelatorio;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccRelatorioSumarizadoPK)) {
			return false;
		}
		SccRelatorioSumarizadoPK castOther = (SccRelatorioSumarizadoPK)other;
		return 
			(this.sqRelatorioSumarizado == castOther.sqRelatorioSumarizado)
			&& this.dtRelatorio.equals(castOther.dtRelatorio);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sqRelatorioSumarizado ^ (this.sqRelatorioSumarizado >>> 32)));
		hash = hash * prime + this.dtRelatorio.hashCode();
		
		return hash;
    }
}