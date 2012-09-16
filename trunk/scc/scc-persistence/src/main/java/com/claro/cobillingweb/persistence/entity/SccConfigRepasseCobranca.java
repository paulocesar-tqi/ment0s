package com.claro.cobillingweb.persistence.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="SCC_CONFIG_REPASSE_COBR_SQ01" , sequenceName="SCC_CONFIG_REPASSE_COBR_SQ01")
@Table(name="SCC_CONFIG_REPASSE_COBRANCA")
public class SccConfigRepasseCobranca {

	private static final long serialVersionUID = 1L;
	private long cdConfigRepasseCobranca;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private String fgCobrarPrestacaoServico = "N";
	private Long inFormaCobrancaCobilling;
	private Long inFormaPagamento;
	private Long inFormaRepasse;
	private Double vlCalculoCobilling;
	private Double vlCalculoRepasse;
	private SccComposicaoProduto sccComposicaoProduto;
	private SccProdutoContratado sccProdutoContratado;
	private SccStatusCdr statusCdrRepasse;
	private SccStatusCdr statusCdrCobranca;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_CONFIG_REPASSE_COBR_SQ01")
	@Column(name="CD_CONFIG_REPASSE_COBRANCA")
	public long getCdConfigRepasseCobranca() {
		return this.cdConfigRepasseCobranca;
	}

	public void setCdConfigRepasseCobranca(long cdConfigRepasseCobranca) {
		this.cdConfigRepasseCobranca = cdConfigRepasseCobranca;
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


	@Column(name="FG_COBRAR_PRESTACAO_SERVICO")
	public String getFgCobrarPrestacaoServico() {
		return this.fgCobrarPrestacaoServico;
	}

	public void setFgCobrarPrestacaoServico(String fgCobrarPrestacaoServico) {
		this.fgCobrarPrestacaoServico = fgCobrarPrestacaoServico;
	}


	@Column(name="IN_FORMA_COBRANCA_COBILLING")
	public Long getInFormaCobrancaCobilling() {
		return this.inFormaCobrancaCobilling;
	}

	public void setInFormaCobrancaCobilling(Long inFormaCobrancaCobilling) {
		this.inFormaCobrancaCobilling = inFormaCobrancaCobilling;
	}


	@Column(name="IN_FORMA_PAGAMENTO")
	public Long getInFormaPagamento() {
		return this.inFormaPagamento;
	}

	public void setInFormaPagamento(Long inFormaPagamento) {
		this.inFormaPagamento = inFormaPagamento;
	}


	@Column(name="IN_FORMA_REPASSE")
	public Long getInFormaRepasse() {
		return this.inFormaRepasse;
	}

	public void setInFormaRepasse(Long inFormaRepasse) {
		this.inFormaRepasse = inFormaRepasse;
	}


	@Column(name="VL_CALCULO_COBILLING")
	public Double getVlCalculoCobilling() {
		return this.vlCalculoCobilling;
	}

	public void setVlCalculoCobilling(Double vlCalculoCobilling) {
		this.vlCalculoCobilling = vlCalculoCobilling;
	}


	@Column(name="VL_CALCULO_REPASSE")
	public Double getVlCalculoRepasse() {
		return this.vlCalculoRepasse;
	}

	public void setVlCalculoRepasse(Double vlCalculoRepasse) {
		this.vlCalculoRepasse = vlCalculoRepasse;
	}


	//bi-directional many-to-one association to SccComposicaoProduto
    @ManyToOne
	@JoinColumn(name="CD_COMPONENTE_PRODUTO",updatable=false)
	public SccComposicaoProduto getSccComposicaoProduto() {
		return this.sccComposicaoProduto;
	}

	public void setSccComposicaoProduto(SccComposicaoProduto sccComposicaoProduto) {
		this.sccComposicaoProduto = sccComposicaoProduto;
	}
	

	//bi-directional many-to-one association to SccProdutoContratado
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="CD_CONTRATO_COBILLING", referencedColumnName="CD_CONTRATO_COBILLING",updatable=false),
		@JoinColumn(name="CD_PRODUTO_COBILLING", referencedColumnName="CD_PRODUTO_COBILLING",updatable=false)
		})
	public SccProdutoContratado getSccProdutoContratado() {
		return this.sccProdutoContratado;
	}

	public void setSccProdutoContratado(SccProdutoContratado sccProdutoContratado) {
		this.sccProdutoContratado = sccProdutoContratado;
	}
	

	//bi-directional many-to-one association to SccStatusCdr
    @ManyToOne
	@JoinColumn(name="CD_STATUS_CDR_REPASSE")
	public SccStatusCdr getStatusCdrRepasse() {
		return this.statusCdrRepasse;
	}

	public void setStatusCdrRepasse(SccStatusCdr statusCdrRepasse) {
		this.statusCdrRepasse = statusCdrRepasse;
	}
	

	//bi-directional many-to-one association to SccStatusCdr
    @ManyToOne
	@JoinColumn(name="CD_STATUS_CDR_COBRANCA")
	public SccStatusCdr getStatusCdrCobranca() {
		return this.statusCdrCobranca;
	}

	public void setStatusCdrCobranca(SccStatusCdr statusCdrCobranca) {
		this.statusCdrCobranca = statusCdrCobranca;
	}
	
}
