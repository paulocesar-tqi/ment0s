package com.claro.cobillingweb.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="SCC_PRE_FECHAMENTO_ASSINATURA")
public class SccPreFechamentoAssinatura {

	private static final long serialVersionUID = 1L;
	private Long sqPreFechamentoAssinatura;
	private String cdStatusFechamento;
	private Date dtFechamento;
	private Date dtFimFechamento;
	private Date dtInicialFechamento;
	private String flRepassaIcms;
	private Long qtAssinaturas;
	private Long qtCdrs;
	private Double qtMinutosConcedidos;
	private Double qtMinutosExpirados;
	private Double qtMinutosUtilizados;
	private Long sqPedido;
	private Double vlBrutoAssinatura;
	private Double vlCofins;
	private Double vlCusto;
	private Double vlIcms;
	private Double vlIcmsAnt;
	private Double vlLiquidoAssinatura;
	private Double vlPis;
	private Double vlRepasse;
	private Double vlRepasseFinal;
	
	private String cdEOTClaro;
	private String cdEOTLD;
	
	private String cdProdutoPrepago;

    public SccPreFechamentoAssinatura() {
    }


	@Id
	@Column(name="SQ_PRE_FECHAMENTO_ASSINATURA")
	public long getSqPreFechamentoAssinatura() {
		return this.sqPreFechamentoAssinatura;
	}

	public void setSqPreFechamentoAssinatura(long sqPreFechamentoAssinatura) {
		this.sqPreFechamentoAssinatura = sqPreFechamentoAssinatura;
	}


	@Column(name="CD_STATUS_FECHAMENTO")
	public String getCdStatusFechamento() {
		return this.cdStatusFechamento;
	}

	public void setCdStatusFechamento(String cdStatusFechamento) {
		this.cdStatusFechamento = cdStatusFechamento;
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
	@Column(name="DT_FIM_FECHAMENTO")
	public Date getDtFimFechamento() {
		return this.dtFimFechamento;
	}

	public void setDtFimFechamento(Date dtFimFechamento) {
		this.dtFimFechamento = dtFimFechamento;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="DT_INICIAL_FECHAMENTO")
	public Date getDtInicialFechamento() {
		return this.dtInicialFechamento;
	}

	public void setDtInicialFechamento(Date dtInicialFechamento) {
		this.dtInicialFechamento = dtInicialFechamento;
	}


	@Column(name="FL_REPASSA_ICMS")
	public String getFlRepassaIcms() {
		return this.flRepassaIcms;
	}

	public void setFlRepassaIcms(String flRepassaIcms) {
		this.flRepassaIcms = flRepassaIcms;
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


	@Column(name="QT_MINUTOS_CONCEDIDOS")
	public Double getQtMinutosConcedidos() {
		return this.qtMinutosConcedidos;
	}

	public void setQtMinutosConcedidos(Double qtMinutosConcedidos) {
		this.qtMinutosConcedidos = qtMinutosConcedidos;
	}


	@Column(name="QT_MINUTOS_EXPIRADOS")
	public Double getQtMinutosExpirados() {
		return this.qtMinutosExpirados;
	}

	public void setQtMinutosExpirados(Double qtMinutosExpirados) {
		this.qtMinutosExpirados = qtMinutosExpirados;
	}


	@Column(name="QT_MINUTOS_UTILIZADOS")
	public Double getQtMinutosUtilizados() {
		return this.qtMinutosUtilizados;
	}

	public void setQtMinutosUtilizados(Double qtMinutosUtilizados) {
		this.qtMinutosUtilizados = qtMinutosUtilizados;
	}


	@Column(name="SQ_PEDIDO")
	public Long getSqPedido() {
		return this.sqPedido;
	}

	public void setSqPedido(Long sqPedido) {
		this.sqPedido = sqPedido;
	}


	@Column(name="VL_BRUTO_ASSINATURA")
	public Double getVlBrutoAssinatura() {
		return this.vlBrutoAssinatura;
	}

	public void setVlBrutoAssinatura(Double vlBrutoAssinatura) {
		this.vlBrutoAssinatura = vlBrutoAssinatura;
	}


	@Column(name="VL_COFINS")
	public Double getVlCofins() {
		return this.vlCofins;
	}

	public void setVlCofins(Double vlCofins) {
		this.vlCofins = vlCofins;
	}


	@Column(name="VL_CUSTO")
	public Double getVlCusto() {
		return this.vlCusto;
	}

	public void setVlCusto(Double vlCusto) {
		this.vlCusto = vlCusto;
	}


	@Column(name="VL_ICMS")
	public Double getVlIcms() {
		return this.vlIcms;
	}

	public void setVlIcms(Double vlIcms) {
		this.vlIcms = vlIcms;
	}


	@Column(name="VL_ICMS_ANT")
	public Double getVlIcmsAnt() {
		return this.vlIcmsAnt;
	}

	public void setVlIcmsAnt(Double vlIcmsAnt) {
		this.vlIcmsAnt = vlIcmsAnt;
	}


	@Column(name="VL_LIQUIDO_ASSINATURA")
	public Double getVlLiquidoAssinatura() {
		return this.vlLiquidoAssinatura;
	}

	public void setVlLiquidoAssinatura(Double vlLiquidoAssinatura) {
		this.vlLiquidoAssinatura = vlLiquidoAssinatura;
	}


	@Column(name="VL_PIS")
	public Double getVlPis() {
		return this.vlPis;
	}

	public void setVlPis(Double vlPis) {
		this.vlPis = vlPis;
	}


	@Column(name="VL_REPASSE")
	public Double getVlRepasse() {
		return this.vlRepasse;
	}

	public void setVlRepasse(Double vlRepasse) {
		this.vlRepasse = vlRepasse;
	}


	@Column(name="VL_REPASSE_FINAL")
	public Double getVlRepasseFinal() {
		return this.vlRepasseFinal;
	}

	public void setVlRepasseFinal(Double vlRepasseFinal) {
		this.vlRepasseFinal = vlRepasseFinal;
	}


	
		
    
	@Column(name="CD_EOT_CLARO")
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}


	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}

	@Column(name="CD_EOT_LD")
	public String getCdEOTLD() {
		return cdEOTLD;
	}


	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}



	@Column(name="CD_PRODUTO_PREPAGO")
	public String getCdProdutoPrepago() {
		return cdProdutoPrepago;
	}


	public void setCdProdutoPrepago(String cdProdutoPrepago) {
		this.cdProdutoPrepago = cdProdutoPrepago;
	}
	
	@Transient
	public boolean repassaICMS()
	{
		if (this.flRepassaIcms == null)
			return false;
		else
			return this.flRepassaIcms.equalsIgnoreCase("S");
	}
	
}
