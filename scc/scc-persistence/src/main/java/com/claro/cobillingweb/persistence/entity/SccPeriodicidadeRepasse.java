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
@Table(name="SCC_PERIODICIDADE_REPASSE")
public class SccPeriodicidadeRepasse implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long cdPeriodicidadeRepasse;
	private String cdUsuarioManut;
	private Long ddFimPeriodoRepasse;
	private Long ddInicioPeriodoRepasse;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String noPeriodicidadeRepasse;
	

    public SccPeriodicidadeRepasse() {
    }


	@Id
	@Column(name="CD_PERIODICIDADE_REPASSE")
	public Long getCdPeriodicidadeRepasse() {
		return this.cdPeriodicidadeRepasse;
	}

	public void setCdPeriodicidadeRepasse(Long cdPeriodicidadeRepasse) {
		this.cdPeriodicidadeRepasse = cdPeriodicidadeRepasse;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}


	@Column(name="DD_FIM_PERIODO_REPASSE")
	public Long getDdFimPeriodoRepasse() {
		return this.ddFimPeriodoRepasse;
	}

	public void setDdFimPeriodoRepasse(Long ddFimPeriodoRepasse) {
		this.ddFimPeriodoRepasse = ddFimPeriodoRepasse;
	}


	@Column(name="DD_INICIO_PERIODO_REPASSE")
	public Long getDdInicioPeriodoRepasse() {
		return this.ddInicioPeriodoRepasse;
	}

	public void setDdInicioPeriodoRepasse(Long ddInicioPeriodoRepasse) {
		this.ddInicioPeriodoRepasse = ddInicioPeriodoRepasse;
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


	@Column(name="NO_PERIODICIDADE_REPASSE")
	public String getNoPeriodicidadeRepasse() {
		return this.noPeriodicidadeRepasse;
	}

	public void setNoPeriodicidadeRepasse(String noPeriodicidadeRepasse) {
		this.noPeriodicidadeRepasse = noPeriodicidadeRepasse;
	}


	
}
