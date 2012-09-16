package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class SccAliquotaImpostoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private String noImposto;
	private String inTipoServico;
	private java.util.Date dtInicioVigencia;
	private String sgUf;
	private String inPlataformaTarifacao;

    public SccAliquotaImpostoPK() {
    }

	@Column(name="NO_IMPOSTO")
	public String getNoImposto() {
		return this.noImposto;
	}
	public void setNoImposto(String noImposto) {
		this.noImposto = noImposto;
	}

	@Column(name="IN_TIPO_SERVICO")
	public String getInTipoServico() {
		return this.inTipoServico;
	}
	public void setInTipoServico(String inTipoServico) {
		this.inTipoServico = inTipoServico;
	}

    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_VIGENCIA")
	public java.util.Date getDtInicioVigencia() {
		return this.dtInicioVigencia;
	}
	public void setDtInicioVigencia(java.util.Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	@Column(name="SG_UF")
	public String getSgUf() {
		return this.sgUf;
	}
	public void setSgUf(String sgUf) {
		this.sgUf = sgUf;
	}

	@Column(name="IN_PLATAFORMA_TARIFACAO")
	public String getInPlataformaTarifacao() {
		return this.inPlataformaTarifacao;
	}
	public void setInPlataformaTarifacao(String inPlataformaTarifacao) {
		this.inPlataformaTarifacao = inPlataformaTarifacao;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccAliquotaImpostoPK)) {
			return false;
		}
		SccAliquotaImpostoPK castOther = (SccAliquotaImpostoPK)other;
		return 
			this.noImposto.equals(castOther.noImposto)
			&& this.inTipoServico.equals(castOther.inTipoServico)
			&& this.dtInicioVigencia.equals(castOther.dtInicioVigencia)
			&& this.sgUf.equals(castOther.sgUf)
			&& this.inPlataformaTarifacao.equals(castOther.inPlataformaTarifacao);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.noImposto.hashCode();
		hash = hash * prime + this.inTipoServico.hashCode();
		hash = hash * prime + this.dtInicioVigencia.hashCode();
		hash = hash * prime + this.sgUf.hashCode();
		hash = hash * prime + this.inPlataformaTarifacao.hashCode();
		
		return hash;
    }
	
}
