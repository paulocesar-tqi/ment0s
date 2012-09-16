package com.claro.cobillingweb.persistence.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the SCC_STATUS_CDR database table.
 * 
 */
@Entity
@Table(name="SCC_STATUS_CDR")
public class SccStatusCdr implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long cdStatusCdr;
	private String cdUsuarioManut;
	private String dsStatusCdr;
	private Date dtAtualizacao;
	private Date dtCriacao;
		

    public SccStatusCdr() {
    }


	@Id
	@Column(name="CD_STATUS_CDR")
	public Long getCdStatusCdr() {
		return this.cdStatusCdr;
	}

	public void setCdStatusCdr(Long cdStatusCdr) {
		this.cdStatusCdr = cdStatusCdr;
	}


	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}


	@Column(name="DS_STATUS_CDR")
	public String getDsStatusCdr() {
		return this.dsStatusCdr;
	}

	public void setDsStatusCdr(String dsStatusCdr) {
		this.dsStatusCdr = dsStatusCdr;
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


	@Transient
	public String getDetalhes(){
		return getCdStatusCdr()+" - "+getDsStatusCdr();
	}
	
	
}