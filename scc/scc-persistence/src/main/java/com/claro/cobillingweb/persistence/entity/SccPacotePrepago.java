package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SCC_PACOTE_PREPAGO")
public class SccPacotePrepago {

	private static final long serialVersionUID = 1L;
	private long cdPacotePrepago;
	private SccProdutoPrepago produtoPrepago;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtFimVigencia;
	private Date dtInicioVigencia;
	private String noPacotePrepago;

    public SccPacotePrepago() {
    }


	@Id
	@Column(name="CD_PACOTE_PREPAGO")
	public long getCdPacotePrepago() {
		return this.cdPacotePrepago;
	}

	public void setCdPacotePrepago(long cdPacotePrepago) {
		this.cdPacotePrepago = cdPacotePrepago;
	}


	
	
	

	@Column(name="CD_USUARIO_MANUT")
	public String getCdUsuarioManut() {
		return this.cdUsuarioManut;
	}

	


	public void setCdUsuarioManut(String cdUsuarioManut) {
		this.cdUsuarioManut = cdUsuarioManut;
	}


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CD_PRODUTO_PREPAGO")
    public SccProdutoPrepago getProdutoPrepago() {
		return produtoPrepago;
	}


	public void setProdutoPrepago(SccProdutoPrepago produtoPrepago) {
		this.produtoPrepago = produtoPrepago;
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


    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIO_VIGENCIA")
	public Date getDtInicioVigencia() {
		return this.dtInicioVigencia;
	}

	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}


	@Column(name="NO_PACOTE_PREPAGO")
	public String getNoPacotePrepago() {
		return this.noPacotePrepago;
	}

	public void setNoPacotePrepago(String noPacotePrepago) {
		this.noPacotePrepago = noPacotePrepago;
	}
	
}
