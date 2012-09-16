package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_CONTA_CONTABIL")
public class SccContaContabil {

	private static final long serialVersionUID = 1L;
	private Long cdConta;
	private String cdUsuario;
	private String dsConta;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String inStatusConta;
	private String tpCentro;
	
    public SccContaContabil() {
    }
    
	@Id
	@Column(name="CD_CONTA")
	public Long getCdConta() {
		return this.cdConta;
	}
	
	public void setCdConta(Long cdConta) {
		this.cdConta = cdConta;
	}
	
	@Column(name="CD_USUARIO")
	public String getCdUsuario() {
		return this.cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}
	
	@Column(name="DS_CONTA")
	public String getDsConta() {
		return this.dsConta;
	}
	
	public void setDsConta(String dsConta) {
		this.dsConta = dsConta;
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
	
	@Column(name="IN_STATUS_CONTA")
	public String getInStatusConta() {
		return this.inStatusConta;
	}
	
	public void setInStatusConta(String inStatusConta) {
		this.inStatusConta = inStatusConta;
	}
	
	@Column(name="TP_CENTRO")
	public String getTpCentro() {
		return this.tpCentro;
	}
	
	public void setTpCentro(String tpCentro) {
		this.tpCentro = tpCentro;
	}
	
}
