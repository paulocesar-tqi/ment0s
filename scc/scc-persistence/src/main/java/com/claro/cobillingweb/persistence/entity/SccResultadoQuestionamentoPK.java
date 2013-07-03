package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SCC_RESULTADO_QUESTIONAMENTO database table.
 * 
 */
@Embeddable
public class SccResultadoQuestionamentoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SQ_QUESTIONAMENTO")
	private long sqQuestionamento;

	@Column(name="SQ_ARQUIVO")
	private long sqArquivo;

	@Column(name="CD_EOT_LD")
	private String cdEotLd;

	@Column(name="CD_EOT_CLARO")
	private String cdEotClaro;

    public SccResultadoQuestionamentoPK() {
    }
	public long getSqQuestionamento() {
		return this.sqQuestionamento;
	}
	public void setSqQuestionamento(long sqQuestionamento) {
		this.sqQuestionamento = sqQuestionamento;
	}
	public long getSqArquivo() {
		return this.sqArquivo;
	}
	public void setSqArquivo(long sqArquivo) {
		this.sqArquivo = sqArquivo;
	}
	public String getCdEotLd() {
		return this.cdEotLd;
	}
	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}
	public String getCdEotClaro() {
		return this.cdEotClaro;
	}
	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccResultadoQuestionamentoPK)) {
			return false;
		}
		SccResultadoQuestionamentoPK castOther = (SccResultadoQuestionamentoPK)other;
		return 
			(this.sqQuestionamento == castOther.sqQuestionamento)
			&& (this.sqArquivo == castOther.sqArquivo)
			&& this.cdEotLd.equals(castOther.cdEotLd)
			&& this.cdEotClaro.equals(castOther.cdEotClaro);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sqQuestionamento ^ (this.sqQuestionamento >>> 32)));
		hash = hash * prime + ((int) (this.sqArquivo ^ (this.sqArquivo >>> 32)));
		hash = hash * prime + this.cdEotLd.hashCode();
		hash = hash * prime + this.cdEotClaro.hashCode();
		
		return hash;
    }
}