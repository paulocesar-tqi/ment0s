package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="SCC_MOTIVO_REJEICAO")
public class SccMotivoRejeicao {

	private static final long serialVersionUID = 1L;
	private String cdMotivoRejeicao;
	private Integer cdClassificacaoMotivo;
	private String cdUsuarioManut;
	private String dsMotivoRejeicao;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String inTipoClassificacao;
	private String txComentarioMotivo;
	private String txDetalhamentoMotivo;
	

    public SccMotivoRejeicao() {
    }


	@Id
	@Column(name="CD_MOTIVO_REJEICAO")
	public String getCdMotivoRejeicao() {
		return this.cdMotivoRejeicao;
	}

	public void setCdMotivoRejeicao(String cdMotivoRejeicao) {
		this.cdMotivoRejeicao = cdMotivoRejeicao;
	}


	@Column(name="CD_CLASSIFICACAO_MOTIVO")
	public Integer getCdClassificacaoMotivo() {
		return this.cdClassificacaoMotivo;
	}

	public void setCdClassificacaoMotivo(Integer cdClassificacaoMotivo) {
		this.cdClassificacaoMotivo = cdClassificacaoMotivo;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}


	@Column(name="DS_MOTIVO_REJEICAO")
	public String getDsMotivoRejeicao() {
		return this.dsMotivoRejeicao;
	}

	public void setDsMotivoRejeicao(String dsMotivoRejeicao) {
		this.dsMotivoRejeicao = dsMotivoRejeicao;
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


	@Column(name="IN_TIPO_CLASSIFICACAO")
	public String getInTipoClassificacao() {
		return this.inTipoClassificacao;
	}

	public void setInTipoClassificacao(String inTipoClassificacao) {
		this.inTipoClassificacao = inTipoClassificacao;
	}


	@Column(name="TX_COMENTARIO_MOTIVO")
	public String getTxComentarioMotivo() {
		return this.txComentarioMotivo;
	}

	public void setTxComentarioMotivo(String txComentarioMotivo) {
		this.txComentarioMotivo = txComentarioMotivo;
	}


	@Column(name="TX_DETALHAMENTO_MOTIVO")
	public String getTxDetalhamentoMotivo() {
		return this.txDetalhamentoMotivo;
	}

	public void setTxDetalhamentoMotivo(String txDetalhamentoMotivo) {
		this.txDetalhamentoMotivo = txDetalhamentoMotivo;
	}

	@Transient
	public String getDsMotivoRejeicaoLabel() {
		return this.cdMotivoRejeicao + " - " + this.dsMotivoRejeicao;
	}
	public void setDsMotivoRejeicaoLabel(String dsMotivoRejeicaoLabel) {

	}

	
}
