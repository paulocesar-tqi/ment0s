package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
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
@Table(name="SCC_REPASSE_SERVICO_ADICIONAL")
public class SccRepasseServicoAdicional implements Serializable {

	private static final long serialVersionUID = 1L;
	private long sqRepasseServicoAdicional;
	private String cdProcesso;
	private String cdStatusRepasse;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtFimRepasse;
	private Date dtInicialRepasse;
	private SccOperadora operadoraClaro;
	private String eotLd;
	private Long nuQuinzena;
	private Long nuRepasse;
	private Long qtAssinaturas;
	private Long qtCdrs;
	private Double qtDuracaoTarifada;
	private Long sqPedido;
	private Double vlAjuste;
	private Double vlArrInadimplente;
	private Double vlArrecadado;
	private Double vlBrutoRepasse;
	private Double vlContestado;
	private Double vlDescontoConcedido;
	private Double vlFaturado;
	private Double vlIss;
	private Double vlLiquidoRepasse;
	private Double vlReversao;
	private Double vlServPrestado;
	private SccProdutoCobilling sccProdutoCobilling;
	
	@Id
	@Column(name="SQ_REPASSE_SERVICO_ADICIONAL")
	public long getSqRepasseServicoAdicional() {
		return this.sqRepasseServicoAdicional;
	}

	public void setSqRepasseServicoAdicional(long sqRepasseServicoAdicional) {
		this.sqRepasseServicoAdicional = sqRepasseServicoAdicional;
	}


	@Column(name="CD_PROCESSO")
	public String getCdProcesso() {
		return this.cdProcesso;
	}

	public void setCdProcesso(String cdProcesso) {
		this.cdProcesso = cdProcesso;
	}


	@Column(name="CD_STATUS_REPASSE")
	public String getCdStatusRepasse() {
		return this.cdStatusRepasse;
	}

	public void setCdStatusRepasse(String cdStatusRepasse) {
		this.cdStatusRepasse = cdStatusRepasse;
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
	@Column(name="DT_FIM_REPASSE")
	public Date getDtFimRepasse() {
		return this.dtFimRepasse;
	}

	public void setDtFimRepasse(Date dtFimRepasse) {
		this.dtFimRepasse = dtFimRepasse;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIAL_REPASSE")
	public Date getDtInicialRepasse() {
		return this.dtInicialRepasse;
	}

	public void setDtInicialRepasse(Date dtInicialRepasse) {
		this.dtInicialRepasse = dtInicialRepasse;
	}

	
	@ManyToOne
	@JoinColumn(name="EOT_CLARO")
	public SccOperadora getOperadoraClaro() {
		return this.operadoraClaro;
	}

	public void setOperadoraClaro(SccOperadora operadoraClaro) {
		this.operadoraClaro = operadoraClaro;
	}


	@Column(name="EOT_LD")
	public String getEotLd() {
		return this.eotLd;
	}

	public void setEotLd(String eotLd) {
		this.eotLd = eotLd;
	}


	@Column(name="NU_QUINZENA")
	public Long getNuQuinzena() {
		return this.nuQuinzena;
	}

	public void setNuQuinzena(Long nuQuinzena) {
		this.nuQuinzena = nuQuinzena;
	}


	@Column(name="NU_REPASSE")
	public Long getNuRepasse() {
		return this.nuRepasse;
	}

	public void setNuRepasse(Long nuRepasse) {
		this.nuRepasse = nuRepasse;
	}


	@Column(name="QT_ASSINATURAS")
	public Long getQtAssinaturas() {
		return this.qtAssinaturas;
	}

	public void setQtAssinaturas(Long qtAssinaturas) {
		this.qtAssinaturas = qtAssinaturas;
	}


	@Column(name="QT_CDRS")
	public Long getQtCdrs() {
		return this.qtCdrs;
	}

	public void setQtCdrs(Long qtCdrs) {
		this.qtCdrs = qtCdrs;
	}


	@Column(name="QT_DURACAO_TARIFADA")
	public Double getQtDuracaoTarifada() {
		return this.qtDuracaoTarifada;
	}

	public void setQtDuracaoTarifada(Double qtDuracaoTarifada) {
		this.qtDuracaoTarifada = qtDuracaoTarifada;
	}


	@Column(name="SQ_PEDIDO")
	public Long getSqPedido() {
		return this.sqPedido;
	}

	public void setSqPedido(Long sqPedido) {
		this.sqPedido = sqPedido;
	}


	@Column(name="VL_AJUSTE")
	public Double getVlAjuste() {
		return this.vlAjuste;
	}

	public void setVlAjuste(Double vlAjuste) {
		this.vlAjuste = vlAjuste;
	}


	@Column(name="VL_ARR_INADIMPLENTE")
	public Double getVlArrInadimplente() {
		return this.vlArrInadimplente;
	}

	public void setVlArrInadimplente(Double vlArrInadimplente) {
		this.vlArrInadimplente = vlArrInadimplente;
	}


	@Column(name="VL_ARRECADADO")
	public Double getVlArrecadado() {
		return this.vlArrecadado;
	}

	public void setVlArrecadado(Double vlArrecadado) {
		this.vlArrecadado = vlArrecadado;
	}


	@Column(name="VL_BRUTO_REPASSE")
	public Double getVlBrutoRepasse() {
		return this.vlBrutoRepasse;
	}

	public void setVlBrutoRepasse(Double vlBrutoRepasse) {
		this.vlBrutoRepasse = vlBrutoRepasse;
	}


	@Column(name="VL_CONTESTADO")
	public Double getVlContestado() {
		return this.vlContestado;
	}

	public void setVlContestado(Double vlContestado) {
		this.vlContestado = vlContestado;
	}


	@Column(name="VL_DESCONTO_CONCEDIDO")
	public Double getVlDescontoConcedido() {
		return this.vlDescontoConcedido;
	}

	public void setVlDescontoConcedido(Double vlDescontoConcedido) {
		this.vlDescontoConcedido = vlDescontoConcedido;
	}


	@Column(name="VL_FATURADO")
	public Double getVlFaturado() {
		return this.vlFaturado;
	}

	public void setVlFaturado(Double vlFaturado) {
		this.vlFaturado = vlFaturado;
	}


	@Column(name="VL_ISS")
	public Double getVlIss() {
		return this.vlIss;
	}

	public void setVlIss(Double vlIss) {
		this.vlIss = vlIss;
	}


	@Column(name="VL_LIQUIDO_REPASSE")
	public Double getVlLiquidoRepasse() {
		return this.vlLiquidoRepasse;
	}

	public void setVlLiquidoRepasse(Double vlLiquidoRepasse) {
		this.vlLiquidoRepasse = vlLiquidoRepasse;
	}


	@Column(name="VL_REVERSAO")
	public Double getVlReversao() {
		return this.vlReversao;
	}

	public void setVlReversao(Double vlReversao) {
		this.vlReversao = vlReversao;
	}


	@Column(name="VL_SERV_PRESTADO")
	public Double getVlServPrestado() {
		return this.vlServPrestado;
	}

	public void setVlServPrestado(Double vlServPrestado) {
		this.vlServPrestado = vlServPrestado;
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
