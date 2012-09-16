package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
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

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries({
	@NamedQuery(name="SccRepasse.FIND_BY_NU_REPASSE" , query="SELECT t from SccRepasse t where t.nuRepasse = :nuRepasse"),
	@NamedQuery(name="SccRepasse.UPDATE_BY_NUM_REPASSE" , query="UPDATE SccRepasse set cdStatusRepasse = :cdStatusRepasse where nuRepasse = :nuRepasse")
		})
@Entity
@SequenceGenerator(name="SCC_REPASSE_SQ01" , sequenceName="SCC_REPASSE_SQ01")
@Table(name="SCC_REPASSE")
public class SccRepasse implements Serializable {

	public static final String STATUS_CONFIRMADO = "C";
	public static final String STATUS_CANCELADO = "N";
	public static final String STATUS_NULO = "E";
	
	private static final long serialVersionUID = 1L;
	private Long sqRepasse;
	private Long cdComponenteProduto;
	private String cdEotClaro;
	private String cdEotLd;
	private String cdStatusRepasse;
	private String cdUsuarioManut;
	private Date dtAtualizacao;
	private Date dtCriacao;
	private Date dtFechamento;
	private Date dtFimRepasse;
	private Date dtInicialRepasse;
	private Long nuQuinzena;
	private Long nuRepasse;
	private Long qtCdrs;
	private Double qtDuracaoReal;
	private Double qtDuracaoTarifada;
	private Long qtNf;
	private Long sqPedido;
	private String txObservacao;
	private Double vlBrutoItemRepasse;
	private Double vlBrutoRepasse = 0.0;
	private Double vlCofins;
	private Double vlIcms;
	private Double vlIss;
	private Double vlLiquidoItemRepasse;
	private Double vlLiquidoRepasse;
	private Double vlPis;
	private Long cdItemRepasse;
	private SccProdutoCobilling produto;
	private String sccTipoContrato;

	
	
    public SccRepasse() {
    }


	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SCC_REPASSE_SQ01")
	@Column(name="SQ_REPASSE")	
	public Long getSqRepasse() {
		return this.sqRepasse;
	}

	public void setSqRepasse(Long sqRepasse) {
		this.sqRepasse = sqRepasse;
	}


	@Column(name="CD_COMPONENTE_PRODUTO")
	public Long getCdComponenteProduto() {
		return this.cdComponenteProduto;
	}

	public void setCdComponenteProduto(Long cdComponenteProduto) {
		this.cdComponenteProduto = cdComponenteProduto;
	}


	@Column(name="CD_EOT_CLARO")
	public String getCdEotClaro() {
		return this.cdEotClaro;
	}

	public void setCdEotClaro(String cdEotClaro) {
		this.cdEotClaro = cdEotClaro;
	}


	@Column(name="CD_EOT_LD")
	public String getCdEotLd() {
		return this.cdEotLd;
	}

	public void setCdEotLd(String cdEotLd) {
		this.cdEotLd = cdEotLd;
	}


	@Column(name="CD_STATUS_REPASSE")
	public String getCdStatusRepasse() {
		return this.cdStatusRepasse;
	}

	public void setCdStatusRepasse(String cdStatusRepasse) {
		this.cdStatusRepasse = cdStatusRepasse;
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
	@Column(name="DT_FECHAMENTO")
	public Date getDtFechamento() {
		return this.dtFechamento;
	}

	public void setDtFechamento(Date dtFechamento) {
		this.dtFechamento = dtFechamento;
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


	@Column(name="QT_CDRS")
	public Long getQtCdrs() {
		return this.qtCdrs;
	}

	public void setQtCdrs(Long qtCdrs) {
		this.qtCdrs = qtCdrs;
	}


	@Column(name="QT_DURACAO_REAL")
	public Double getQtDuracaoReal() {
		return this.qtDuracaoReal;
	}

	public void setQtDuracaoReal(Double qtDuracaoReal) {
		this.qtDuracaoReal = qtDuracaoReal;
	}


	@Column(name="QT_DURACAO_TARIFADA")
	public Double getQtDuracaoTarifada() {
		return this.qtDuracaoTarifada;
	}

	public void setQtDuracaoTarifada(Double qtDuracaoTarifada) {
		this.qtDuracaoTarifada = qtDuracaoTarifada;
	}


	@Column(name="QT_NF")
	public Long getQtNf() {
		return this.qtNf;
	}

	public void setQtNf(Long qtNf) {
		this.qtNf = qtNf;
	}


	@Column(name="SQ_PEDIDO")
	public Long getSqPedido() {
		return this.sqPedido;
	}

	public void setSqPedido(Long sqPedido) {
		this.sqPedido = sqPedido;
	}


	@Column(name="TX_OBSERVACAO")
	public String getTxObservacao() {
		return this.txObservacao;
	}

	public void setTxObservacao(String txObservacao) {
		this.txObservacao = txObservacao;
	}


	@Column(name="VL_BRUTO_ITEM_REPASSE")
	public Double getVlBrutoItemRepasse() {
		return this.vlBrutoItemRepasse;
	}

	public void setVlBrutoItemRepasse(Double vlBrutoItemRepasse) {
		this.vlBrutoItemRepasse = vlBrutoItemRepasse;
	}


	@Column(name="VL_BRUTO_REPASSE")
	public Double getVlBrutoRepasse() {
		return this.vlBrutoRepasse;
	}

	public void setVlBrutoRepasse(Double vlBrutoRepasse) {
		this.vlBrutoRepasse = vlBrutoRepasse;
	}


	@Column(name="VL_COFINS")
	public Double getVlCofins() {
		return this.vlCofins;
	}

	public void setVlCofins(Double vlCofins) {
		this.vlCofins = vlCofins;
	}


	@Column(name="VL_ICMS")
	public Double getVlIcms() {
		return this.vlIcms;
	}

	public void setVlIcms(Double vlIcms) {
		this.vlIcms = vlIcms;
	}


	@Column(name="VL_ISS")
	public Double getVlIss() {
		return this.vlIss;
	}

	public void setVlIss(Double vlIss) {
		this.vlIss = vlIss;
	}


	@Column(name="VL_LIQUIDO_ITEM_REPASSE")
	public Double getVlLiquidoItemRepasse() {
		return this.vlLiquidoItemRepasse;
	}

	public void setVlLiquidoItemRepasse(Double vlLiquidoItemRepasse) {
		this.vlLiquidoItemRepasse = vlLiquidoItemRepasse;
	}


	@Column(name="VL_LIQUIDO_REPASSE")
	public Double getVlLiquidoRepasse() {
		return this.vlLiquidoRepasse;
	}

	public void setVlLiquidoRepasse(Double vlLiquidoRepasse) {
		this.vlLiquidoRepasse = vlLiquidoRepasse;
	}


	@Column(name="VL_PIS")
	public Double getVlPis() {
		return this.vlPis;
	}

	public void setVlPis(Double vlPis) {
		this.vlPis = vlPis;
	}


	
	@Column(name="CD_ITEM_REPASSE")
	public Long getCdItemRepasse() {
		return this.cdItemRepasse;
	}

	public void setCdItemRepasse(Long cdItemRepasse) {
		this.cdItemRepasse = cdItemRepasse;
	}
	

	//bi-directional many-to-one association to SccProdutoCobilling
    @ManyToOne
	@JoinColumn(name="CD_PRODUTO_COBILLING")
	public SccProdutoCobilling getProduto() {
		return this.produto;
	}

	public void setProduto(SccProdutoCobilling produto) {
		this.produto = produto;
	}
	

	
   
	@Column(name="CD_TIPO_CONTRATO")
    public String getSccTipoContrato() {
		return this.sccTipoContrato;
	}

	public void setSccTipoContrato(String sccTipoContrato) {
		this.sccTipoContrato = sccTipoContrato;
	}
	
}
