package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_TIPO_CONTRATO")
public class SccTipoContrato implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cdTipoContrato;
	private String cdUsuarioManut;
	private String dsTipoContrato;
	private Date dtAtualizacao;
	private Date dtCriacao;
	

    public SccTipoContrato() {
    }


	@Id
	@Column(name="CD_TIPO_CONTRATO")
	public String getCdTipoContrato() {
		return this.cdTipoContrato;
	}

	public void setCdTipoContrato(String cdTipoContrato) {
		this.cdTipoContrato = cdTipoContrato;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}


	@Column(name="DS_TIPO_CONTRATO")
	public String getDsTipoContrato() {
		return this.dsTipoContrato;
	}

	public void setDsTipoContrato(String dsTipoContrato) {
		this.dsTipoContrato = dsTipoContrato;
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


	
	
}
