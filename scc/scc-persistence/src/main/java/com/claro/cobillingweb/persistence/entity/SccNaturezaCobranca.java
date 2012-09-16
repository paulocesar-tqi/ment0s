package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="SCC_NATUREZA_COBRANCA")
public class SccNaturezaCobranca {

	
	private static final long serialVersionUID = 1L;
	private Long cdNaturezaCobranca;
	private String cdUsuarioManut;
	private String dsNaturezaCobranca;
	private String dsTipoCobranca;
	private String dsTipoServicoDestino;
	private String dsTipoServicoOrigem;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String flOrigemEmRoaming;

    public SccNaturezaCobranca() {
    }


	@Id
	@Column(name="CD_NATUREZA_COBRANCA")
	public Long getCdNaturezaCobranca() {
		return this.cdNaturezaCobranca;
	}

	public void setCdNaturezaCobranca(Long cdNaturezaCobranca) {
		this.cdNaturezaCobranca = cdNaturezaCobranca;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}


	@Column(name="DS_NATUREZA_COBRANCA")
	public String getDsNaturezaCobranca() {
		return this.dsNaturezaCobranca;
	}

	public void setDsNaturezaCobranca(String dsNaturezaCobranca) {
		this.dsNaturezaCobranca = dsNaturezaCobranca;
	}


	@Column(name="DS_TIPO_COBRANCA")
	public String getDsTipoCobranca() {
		return this.dsTipoCobranca;
	}

	public void setDsTipoCobranca(String dsTipoCobranca) {
		this.dsTipoCobranca = dsTipoCobranca;
	}


	@Column(name="DS_TIPO_SERVICO_DESTINO")
	public String getDsTipoServicoDestino() {
		return this.dsTipoServicoDestino;
	}

	public void setDsTipoServicoDestino(String dsTipoServicoDestino) {
		this.dsTipoServicoDestino = dsTipoServicoDestino;
	}


	@Column(name="DS_TIPO_SERVICO_ORIGEM")
	public String getDsTipoServicoOrigem() {
		return this.dsTipoServicoOrigem;
	}

	public void setDsTipoServicoOrigem(String dsTipoServicoOrigem) {
		this.dsTipoServicoOrigem = dsTipoServicoOrigem;
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


	@Column(name="FL_ORIGEM_EM_ROAMING")
	public String getFlOrigemEmRoaming() {
		return this.flOrigemEmRoaming;
	}

	public void setFlOrigemEmRoaming(String flOrigemEmRoaming) {
		this.flOrigemEmRoaming = flOrigemEmRoaming;
	}
}
