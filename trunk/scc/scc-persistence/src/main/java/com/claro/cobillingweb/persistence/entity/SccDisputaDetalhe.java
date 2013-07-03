/**
 * 
 */
package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author vagner.souza
 *
 */

/**
 * The persistent class for the SCC_DISPUTA_DETALHE database table.
 * 
 */
@Entity
@Table(name="SCC_DISPUTA_DETALHE")
public class SccDisputaDetalhe implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6777675908023900294L;

	
	
	@EmbeddedId
	private SccDisputaDetalhePK id;

	@Column(name="CD_USUARIO_MANUT")
	private String cdUsuarioManut;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_ATUALIZACAO")
	private Date dtAtualizacao;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_CRIACAO")
	private Date dtCriacao;

	@Column(name="VL_ACERTO_CONCILIACAO")
	private Double vlAcertoConciliacao;

	@Column(name="VL_ACORDO")
	private Double vlAcordo;

	@Column(name="VL_CONTESTADO")
	private Double vlContestado;

	@Column(name="VL_DIFERENCA_CONTESTADO")
	private Double vlDiferencaContestado;

	@Column(name="VL_PROPOSTO")
	private Double vlProposto;

	@Column(name="VL_PROVISAO")
	private Double vlProvisao;

	@Column(name="VL_SALDO_REPASSAR")
	private Double vlSaldoRepassar;

	//bi-directional many-to-one association to SccDisputa
    @ManyToOne
	@JoinColumn(name="SQ_DISPUTA", insertable=false, updatable=false)
	private SccDisputa sccDisputa;

	//bi-directional many-to-one association to SccOperadora
    @ManyToOne
	@JoinColumn(name="CD_EOT_CLARO", insertable=false, updatable=false)
	private SccOperadora sccOperadora;

    public SccDisputaDetalhe() {
    }

	public SccDisputaDetalhePK getId() {
		return this.id;
	}

	public void setId(SccDisputaDetalhePK id) {
		this.id = id;
	}

	public String getCdUsuarioManut() {
		return cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}

	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Double getVlAcertoConciliacao() {
		return vlAcertoConciliacao;
	}

	public void setVlAcertoConciliacao(Double vlAcertoConciliacao) {
		this.vlAcertoConciliacao = vlAcertoConciliacao;
	}

	public Double getVlAcordo() {
		return vlAcordo;
	}

	public void setVlAcordo(Double vlAcordo) {
		this.vlAcordo = vlAcordo;
	}

	public Double getVlContestado() {
		return vlContestado;
	}

	public void setVlContestado(Double vlContestado) {
		this.vlContestado = vlContestado;
	}

	public Double getVlDiferencaContestado() {
		return vlDiferencaContestado;
	}

	public void setVlDiferencaContestado(Double vlDiferencaContestado) {
		this.vlDiferencaContestado = vlDiferencaContestado;
	}

	public Double getVlProposto() {
		return vlProposto;
	}

	public void setVlProposto(Double vlProposto) {
		this.vlProposto = vlProposto;
	}

	public Double getVlProvisao() {
		return vlProvisao;
	}

	public void setVlProvisao(Double vlProvisao) {
		this.vlProvisao = vlProvisao;
	}

	public Double getVlSaldoRepassar() {
		return vlSaldoRepassar;
	}

	public void setVlSaldoRepassar(Double vlSaldoRepassar) {
		this.vlSaldoRepassar = vlSaldoRepassar;
	}

	public SccDisputa getSccDisputa() {
		return sccDisputa;
	}

	public void setSccDisputa(SccDisputa sccDisputa) {
		this.sccDisputa = sccDisputa;
	}

	public SccOperadora getSccOperadora() {
		return sccOperadora;
	}

	public void setSccOperadora(SccOperadora sccOperadora) {
		this.sccOperadora = sccOperadora;
	}
	
	

}
