package com.claro.cobillingweb.persistence.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_ARQUIVO_PRE_PAGO")
public class SccArquivoPrePago {

	private static final long serialVersionUID = 1L;
	private SccArquivoPrePagoPK id;
	private Date dtCriacao;
	

    public SccArquivoPrePago() {
    }


	@EmbeddedId
	public SccArquivoPrePagoPK getId() {
		return this.id;
	}

	public void setId(SccArquivoPrePagoPK id) {
		this.id = id;
	}
	

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}


	
}
