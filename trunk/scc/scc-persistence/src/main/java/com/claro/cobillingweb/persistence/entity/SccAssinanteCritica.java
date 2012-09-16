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
@Table(name="SCC_ASSINANTE_CRITICA")
public class SccAssinanteCritica {

	private SccAssinanteCriticaPK id;
	private String cdUsuarioManut;
	private Date dtCriacao;
	private Date dtFimVigencia;
	private Date dtInicioVigencia;
	private SccCriticaPrebilling criticaPreBilling;
	

    public SccAssinanteCritica() {
    }


	@EmbeddedId
	public SccAssinanteCriticaPK getId() {
		return this.id;
	}

	public void setId(SccAssinanteCriticaPK id) {
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
	@Column(name="DT_CRIACAO")
	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_FIM_VIGENCIA")
	public Date getDtFimVigencia() {
		return this.dtFimVigencia;
	}

	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_VIGENCIA")
	public Date getDtInicioVigencia() {
		return this.dtInicioVigencia;
	}

	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_CRITICA")
	public SccCriticaPrebilling getCriticaPreBilling() {
		return criticaPreBilling;
	}


	public void setCriticaPreBilling(SccCriticaPrebilling criticaPreBilling) {
		this.criticaPreBilling = criticaPreBilling;
	}


	
	
}
