package com.claro.cobillingweb.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_ALIQUOTA_IMPOSTO")
public class SccAliquotaImposto {

	private static final long serialVersionUID = 1L;
	private SccAliquotaImpostoPK id;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtFimVigencia;
	private Double pcAliquotaImposto;

    public SccAliquotaImposto() {
    }


	@EmbeddedId
	public SccAliquotaImpostoPK getId() {
		return this.id;
	}

	public void setId(SccAliquotaImpostoPK id) {
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


    @Temporal( TemporalType.DATE)
	@Column(name="DT_FIM_VIGENCIA")
	public Date getDtFimVigencia() {
		return this.dtFimVigencia;
	}

	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}


	@Column(name="PC_ALIQUOTA_IMPOSTO")
	public Double getPcAliquotaImposto() {
		return this.pcAliquotaImposto;
	}

	public void setPcAliquotaImposto(Double pcAliquotaImposto) {
		this.pcAliquotaImposto = pcAliquotaImposto;
	}
	
}
