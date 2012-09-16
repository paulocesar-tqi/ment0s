package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_PERIOD_PROD_CONTR")
public class SccPeriodProdContr implements Serializable {

	private static final long serialVersionUID = 1L;
	private SccPeriodProdContrPK id;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private SccPeriodicidadeRepasse sccPeriodicidadeRepasse;
	private SccProdutoContratado sccProdutoContratado;
	

    public SccPeriodProdContr() {
    }


	@EmbeddedId
	public SccPeriodProdContrPK getId() {
		return this.id;
	}

	public void setId(SccPeriodProdContrPK id) {
		this.id = id;
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


	//bi-directional many-to-one association to SccPeriodicidadeRepasse
    @ManyToOne
	@JoinColumn(name="CD_PERIODICIDADE_REPASSE",insertable=false,updatable=false)
	public SccPeriodicidadeRepasse getSccPeriodicidadeRepasse() {
		return this.sccPeriodicidadeRepasse;
	}

	public void setSccPeriodicidadeRepasse(SccPeriodicidadeRepasse sccPeriodicidadeRepasse) {
		this.sccPeriodicidadeRepasse = sccPeriodicidadeRepasse;
	}
	

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CD_CONTRATO_COBILLING", referencedColumnName="CD_CONTRATO_COBILLING",insertable=false,updatable=false),
		@JoinColumn(name="CD_PRODUTO_COBILLING", referencedColumnName="CD_PRODUTO_COBILLING",insertable=false,updatable=false)
		})
	public SccProdutoContratado getSccProdutoContratado() {
		return this.sccProdutoContratado;
	}

	public void setSccProdutoContratado(SccProdutoContratado sccProdutoContratado) {
		this.sccProdutoContratado = sccProdutoContratado;
	}
	
}
