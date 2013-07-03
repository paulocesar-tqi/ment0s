package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@SequenceGenerator(name="SCC_COMPOSICAO_PRODUTO_SQ01" , sequenceName="SCC_COMPOSICAO_PRODUTO_SQ01")
@Table(name="SCC_COMPOSICAO_PRODUTO")
public class SccComposicaoProduto extends FwjBaseEntidade {

	private static final long serialVersionUID = 1L;
	private long cdComponenteProduto;
	private String cdMotivoRetarifacao;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private SccItemCobilling sccItemCobilling;
	private SccProdutoCobilling sccProdutoCobilling;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_COMPOSICAO_PRODUTO_SQ01")
	@Column(name="CD_COMPONENTE_PRODUTO")
	public long getCdComponenteProduto() {
		return this.cdComponenteProduto;
	}

	public void setCdComponenteProduto(long cdComponenteProduto) {
		this.cdComponenteProduto = cdComponenteProduto;
	}


	@Column(name="CD_MOTIVO_RETARIFACAO")
	public String getCdMotivoRetarifacao() {
		return this.cdMotivoRetarifacao;
	}

	public void setCdMotivoRetarifacao(String cdMotivoRetarifacao) {
		this.cdMotivoRetarifacao = cdMotivoRetarifacao;
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
	
    @ManyToOne
	@JoinColumn(name="CD_ITEM_COBILLING")
	public SccItemCobilling getSccItemCobilling() {
		return this.sccItemCobilling;
	}

	public void setSccItemCobilling(SccItemCobilling sccItemCobilling) {
		this.sccItemCobilling = sccItemCobilling;
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
