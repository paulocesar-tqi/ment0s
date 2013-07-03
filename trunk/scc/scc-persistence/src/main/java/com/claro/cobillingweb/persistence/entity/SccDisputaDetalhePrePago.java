/**
 * 
 */
package com.claro.cobillingweb.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author vagner.souza
 *
 */
/**
 * The persistent class for the SCC_DISPUTA_DETALHE_PRE_PAGO database table.
 * 
 */
@Entity
@Table(name="SCC_DISPUTA_DETALHE_PRE_PAGO")
public class SccDisputaDetalhePrePago implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 865232490428582432L;

	@EmbeddedId
	private SccDisputaDetalhePrePagoPK id;

	@Column(name="CD_ANALISTA_INPUT_REPASSE")
	private String cdAnalistaInputRepasse;

    @Temporal( TemporalType.DATE)
	@Column(name="DT_LIBERACAO_REPASSE")
	private Date dtLiberacaoRepasse;

	@Column(name="PC_RATEIO")
	private BigDecimal pcRateio;

	@Column(name="VL_ACERTO_CONCILIACAO_CLARO")
	private BigDecimal vlAcertoConciliacaoClaro;

	@Column(name="VL_ACERTO_CONCILIACAO_LD")
	private BigDecimal vlAcertoConciliacaoLd;

	@Column(name="VL_ACORDADO")
	private BigDecimal vlAcordado;

	@Column(name="VL_DIFERENCA_VL_PLEITO")
	private BigDecimal vlDiferencaVlPleito;

	@Column(name="VL_DIFERENCA_VL_PROPOSTO")
	private BigDecimal vlDiferencaVlProposto;

	@Column(name="VL_PLEITO")
	private BigDecimal vlPleito;

	@Column(name="VL_PROPOSTO")
	private BigDecimal vlProposto;

	@Column(name="VL_PROVISAO_DISPUTA")
	private BigDecimal vlProvisaoDisputa;

	//bi-directional many-to-one association to SccDisputaPrePago
    @ManyToOne
	@JoinColumn(name="SQ_DISPUTA_PRE_PAGO", insertable=false, updatable=false)
	private SccDisputaPrePago sccDisputaPrePago;

	//bi-directional many-to-one association to SccOperadora
    @ManyToOne
	@JoinColumn(name="CD_EOT_CLARO", insertable=false, updatable=false)
	private SccOperadora sccOperadora;

    public SccDisputaDetalhePrePago() {
    }

	public SccDisputaDetalhePrePagoPK getId() {
		return this.id;
	}

	public void setId(SccDisputaDetalhePrePagoPK id) {
		this.id = id;
	}
	
	public String getCdAnalistaInputRepasse() {
		return this.cdAnalistaInputRepasse;
	}

	public void setCdAnalistaInputRepasse(String cdAnalistaInputRepasse) {
		this.cdAnalistaInputRepasse = cdAnalistaInputRepasse;
	}

	public Date getDtLiberacaoRepasse() {
		return this.dtLiberacaoRepasse;
	}

	public void setDtLiberacaoRepasse(Date dtLiberacaoRepasse) {
		this.dtLiberacaoRepasse = dtLiberacaoRepasse;
	}

	public BigDecimal getPcRateio() {
		return this.pcRateio;
	}

	public void setPcRateio(BigDecimal pcRateio) {
		this.pcRateio = pcRateio;
	}

	public BigDecimal getVlAcertoConciliacaoClaro() {
		return this.vlAcertoConciliacaoClaro;
	}

	public void setVlAcertoConciliacaoClaro(BigDecimal vlAcertoConciliacaoClaro) {
		this.vlAcertoConciliacaoClaro = vlAcertoConciliacaoClaro;
	}

	public BigDecimal getVlAcertoConciliacaoLd() {
		return this.vlAcertoConciliacaoLd;
	}

	public void setVlAcertoConciliacaoLd(BigDecimal vlAcertoConciliacaoLd) {
		this.vlAcertoConciliacaoLd = vlAcertoConciliacaoLd;
	}

	public BigDecimal getVlAcordado() {
		return this.vlAcordado;
	}

	public void setVlAcordado(BigDecimal vlAcordado) {
		this.vlAcordado = vlAcordado;
	}

	public BigDecimal getVlDiferencaVlPleito() {
		return this.vlDiferencaVlPleito;
	}

	public void setVlDiferencaVlPleito(BigDecimal vlDiferencaVlPleito) {
		this.vlDiferencaVlPleito = vlDiferencaVlPleito;
	}

	public BigDecimal getVlDiferencaVlProposto() {
		return this.vlDiferencaVlProposto;
	}

	public void setVlDiferencaVlProposto(BigDecimal vlDiferencaVlProposto) {
		this.vlDiferencaVlProposto = vlDiferencaVlProposto;
	}

	public BigDecimal getVlPleito() {
		return this.vlPleito;
	}

	public void setVlPleito(BigDecimal vlPleito) {
		this.vlPleito = vlPleito;
	}

	public BigDecimal getVlProposto() {
		return this.vlProposto;
	}

	public void setVlProposto(BigDecimal vlProposto) {
		this.vlProposto = vlProposto;
	}

	public BigDecimal getVlProvisaoDisputa() {
		return this.vlProvisaoDisputa;
	}

	public void setVlProvisaoDisputa(BigDecimal vlProvisaoDisputa) {
		this.vlProvisaoDisputa = vlProvisaoDisputa;
	}

	public SccDisputaPrePago getSccDisputaPrePago() {
		return this.sccDisputaPrePago;
	}

	public void setSccDisputaPrePago(SccDisputaPrePago sccDisputaPrePago) {
		this.sccDisputaPrePago = sccDisputaPrePago;
	}
	
	public SccOperadora getSccOperadora() {
		return this.sccOperadora;
	}

	public void setSccOperadora(SccOperadora sccOperadora) {
		this.sccOperadora = sccOperadora;
	}
	
	

}
