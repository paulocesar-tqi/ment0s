package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="SCC_SERVICO_ADICIONAL")
public class SccServicoAdicional {

	private static final long serialVersionUID = 1L;
	private String cdCharge;
	private String cdEotLd;
	private String cdTipoCharge;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private SccProdutoCobilling sccProdutoCobilling;

    public SccServicoAdicional() {
    }


	@Id
	@Column(name="CD_CHARGE")
	public String getCdCharge() {
		return this.cdCharge;
	}

	public void setCdCharge(String cdCharge) {
		this.cdCharge = cdCharge;
	}


	@Column(name="CD_EOT_LD")
	public String getCdEotLd() {
		return this.cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}


	@Column(name="CD_TIPO_CHARGE")
	public String getCdTipoCharge() {
		return this.cdTipoCharge;
	}

	public void setCdTipoCharge(String cdTipoCharge) {
		this.cdTipoCharge = cdTipoCharge;
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


	//bi-directional many-to-one association to SccProdutoCobilling
    @ManyToOne
	@JoinColumn(name="CD_PRODUTO_COBILLING")
	public SccProdutoCobilling getSccProdutoCobilling() {
		return this.sccProdutoCobilling;
	}

	public void setSccProdutoCobilling(SccProdutoCobilling sccProdutoCobilling) {
		this.sccProdutoCobilling = sccProdutoCobilling;
	}
	
}
