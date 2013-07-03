package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_DADOS_BANCARIOS")
public class SccDadosBancario extends FwjBaseEntidade{

	private static final long serialVersionUID = 1L;
	private SccDadosBancarioPK id;
	private String cdTipoBancoParceiro;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String nuContaCorrente;

    public SccDadosBancario() {
    }


	@EmbeddedId
	public SccDadosBancarioPK getId() {
		return this.id;
	}

	public void setId(SccDadosBancarioPK id) {
		this.id = id;
	}
	

	@Column(name="CD_TIPO_BANCO_PARCEIRO")
	public String getCdTipoBancoParceiro() {
		return this.cdTipoBancoParceiro;
	}

	public void setCdTipoBancoParceiro(String cdTipoBancoParceiro) {
		this.cdTipoBancoParceiro = cdTipoBancoParceiro;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
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


	@Column(name="NU_CONTA_CORRENTE")
	public String getNuContaCorrente() {
		return this.nuContaCorrente;
	}

	public void setNuContaCorrente(String nuContaCorrente) {
		this.nuContaCorrente = nuContaCorrente;
	}
	
}
