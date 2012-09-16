package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_CENTRO")
public class SccCentro {

	private static final long serialVersionUID = 1L;
	private SccCentroPK id;
	private String cdUsuario;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String tpCentro;

    public SccCentro() {
    }


	@EmbeddedId
	public SccCentroPK getId() {
		return this.id;
	}

	public void setId(SccCentroPK id) {
		this.id = id;
	}
	

	@Column(name="CD_USUARIO")
	public String getCdUsuario() {
		return this.cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	public Date getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}


	@Column(name="TP_CENTRO")
	public String getTpCentro() {
		return this.tpCentro;
	}

	public void setTpCentro(String tpCentro) {
		this.tpCentro = tpCentro;
	}
	
}
