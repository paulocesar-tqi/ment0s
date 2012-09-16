package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SccArquivoPrePagoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private long sqArquivo;
	private long sqArquivoControlePre;

    public SccArquivoPrePagoPK() {
    }

	@Column(name="SQ_ARQUIVO")
	public long getSqArquivo() {
		return this.sqArquivo;
	}
	public void setSqArquivo(long sqArquivo) {
		this.sqArquivo = sqArquivo;
	}

	@Column(name="SQ_ARQUIVO_CONTROLE_PRE")
	public long getSqArquivoControlePre() {
		return this.sqArquivoControlePre;
	}
	public void setSqArquivoControlePre(long sqArquivoControlePre) {
		this.sqArquivoControlePre = sqArquivoControlePre;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SccArquivoPrePagoPK)) {
			return false;
		}
		SccArquivoPrePagoPK castOther = (SccArquivoPrePagoPK)other;
		return 
			(this.sqArquivo == castOther.sqArquivo)
			&& (this.sqArquivoControlePre == castOther.sqArquivoControlePre);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.sqArquivo ^ (this.sqArquivo >>> 32)));
		hash = hash * prime + ((int) (this.sqArquivoControlePre ^ (this.sqArquivoControlePre >>> 32)));
		
		return hash;
    }
	
}
