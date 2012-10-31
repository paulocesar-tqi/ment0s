package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_PENALIDADE_POR_REJEICAO")
public class SccPenalidadePorRejeicao {

	private static final long serialVersionUID = 1L;
	private SccPenalidadePorRejeicaoPK id;
	private Double vlPenalidade;
	private String fgCobrarPenalidade;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private SccOperadora operadoraLD;
	private SccMotivoRejeicao motivoRejeicao;


	public SccPenalidadePorRejeicao() {
    }


	@EmbeddedId
	public SccPenalidadePorRejeicaoPK getId() {
		return this.id;
	}

	public void setId(SccPenalidadePorRejeicaoPK id) {
		this.id = id;
	}
	
	@Column(name="VL_PENALIDADE")
	public Double getVlPenalidade() {
		return vlPenalidade;
	}
	public void setVlPenalidade(Double vlPenalidade) {
		this.vlPenalidade = vlPenalidade;
	}

	@Column(name="FG_COBRAR_PENALIDADE")
	public String getFgCobrarPenalidade() {
		return fgCobrarPenalidade;
	}
	public void setFgCobrarPenalidade(String fgCobrarPenalidade) {
		this.fgCobrarPenalidade = fgCobrarPenalidade;
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
	
    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_EOT_LD",updatable=false,insertable=false)
    public SccOperadora getOperadoraLD() {
		return operadoraLD;
	}
	public void setOperadoraLD(SccOperadora operadoraLD) {
		this.operadoraLD = operadoraLD;
	}

    @ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_MOTIVO_REJEICAO",updatable=false,insertable=false)
	public SccMotivoRejeicao getMotivoRejeicao() {
		return motivoRejeicao;
	}
	public void setMotivoRejeicao(SccMotivoRejeicao motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}

}
