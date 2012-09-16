package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="SCC_PARAM_PROCESSO")
public class SccParamProcesso implements Serializable {

	private static final long serialVersionUID = 1L;
	private SccParamProcessoPK id;
	private String cdTipoParametro;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String txValorParametro;
	
	private String noTipoParametro;

    public SccParamProcesso() {
    }


	@EmbeddedId
	public SccParamProcessoPK getId() {
		return this.id;
	}

	public void setId(SccParamProcessoPK id) {
		this.id = id;
	}
	

	@Column(name="CD_TIPO_PARAMETRO")
	public String getCdTipoParametro() {
		return this.cdTipoParametro;
	}

	public void setCdTipoParametro(String cdTipoParametro) {
		this.cdTipoParametro = cdTipoParametro;
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


	@Column(name="TX_VALOR_PARAMETRO")
	public String getTxValorParametro() {
		return this.txValorParametro;
	}

	public void setTxValorParametro(String txValorParametro) {
		this.txValorParametro = txValorParametro;
	}

	@Transient
	public String getNoTipoParametro() {
		return noTipoParametro;
	}


	public void setNoTipoParametro(String noTipoParametro) {
		this.noTipoParametro = noTipoParametro;
	}
	
	
	
}
