package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="SCC_ATIVIDADE_CONTABIL_SQ01" , sequenceName="SCC_ATIVIDADE_CONTABIL_SQ01")
@Table(name="SCC_ATIVIDADE_CONTABIL")
public class SccAtividadeContabil {
	
	private static final long serialVersionUID = 1L;
	private Long sqAtividade;
	private String cdUsuario;
	private String dsAtividadeContabil;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtExpiracao;
	private String txHistoricoAtividade;
	private SccContaContabil contaCredito;
	private SccContaContabil contaDebito;
	private SccDominioContabil dominioContabil;
	private SccOperadora operadoraLD;
	
	public SccAtividadeContabil() {
	}
	
	@Id
	@Column(name="SQ_ATIVIDADE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_ATIVIDADE_CONTABIL_SQ01")
	public Long getSqAtividade() {
		return this.sqAtividade;
	}
	
	public void setSqAtividade(Long sqAtividade) {
		this.sqAtividade = sqAtividade;
	}
	
	@Column(name="CD_USUARIO")
	public String getCdUsuario() {
		return this.cdUsuario;
	}
	
	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}
	
	@Column(name="DS_ATIVIDADE_CONTABIL")
	public String getDsAtividadeContabil() {
		return this.dsAtividadeContabil;
	}

	public void setDsAtividadeContabil(String dsAtividadeContabil) {
		this.dsAtividadeContabil = dsAtividadeContabil;
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
	@Column(name="DT_EXPIRACAO")
	public Date getDtExpiracao() {
		return this.dtExpiracao;
	}

	public void setDtExpiracao(Date dtExpiracao) {
		this.dtExpiracao = dtExpiracao;
	}


	@Column(name="TX_HISTORICO_ATIVIDADE")
	public String getTxHistoricoAtividade() {
		return this.txHistoricoAtividade;
	}

	public void setTxHistoricoAtividade(String txHistoricoAtividade) {
		this.txHistoricoAtividade = txHistoricoAtividade;
	}



	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_CONTA_CREDITO")
	public SccContaContabil getContaCredito() {
		return this.contaCredito;
	}

	public void setContaCredito(SccContaContabil contaCredito) {
		this.contaCredito = contaCredito;
	}



	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_CONTA_DEBITO")
	public SccContaContabil getContaDebito() {
		return this.contaDebito;
	}

	public void setContaDebito(SccContaContabil contaDebito) {
		this.contaDebito = contaDebito;
	}


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_DOMINIO_CONTABIL")
	public SccDominioContabil getDominioContabil() {
		return dominioContabil;
	}


	public void setDominioContabil(SccDominioContabil dominioContabil) {
		this.dominioContabil = dominioContabil;
	}


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CD_EOT_LD")
	public SccOperadora getOperadoraLD() {
		return operadoraLD;
	}


	public void setOperadoraLD(SccOperadora operadoraLD) {
		this.operadoraLD = operadoraLD;
	}




}
