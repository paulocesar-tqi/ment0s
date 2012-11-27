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
	
	private Long qtCdrsOm;
	private Long qtdAssinaturasOm;
	private Double qtMinutosConcedidosOm;
	private Double qtMinutosExpiradosOm;
	private Double qtMinutosUtilizadosOm;
	private Double vlBrutoAssinaturaOm;
	private Double vlLiquidoAssinaturaOm;
	private Double vlPisOm;
	private Double vlCofinsOm;
	private Double vlIcmsOm;
	private Double vlIcmsAntOm;
	private Double vlRepasseOm;
	private Double vlRepasseFinalOm;
	private Double vlCustoOm;
	
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
	
	
	@Column(name="QT_CDRS_OM")
	public Long getQtCdrsOm() {
		return qtCdrsOm;
	}


	public void setQtCdrsOm(Long qtCdrsOm) {
		this.qtCdrsOm = qtCdrsOm;
	}

	@Column(name="QT_ASSINATURAS_OM")
	public Long getQtdAssinaturasOm() {
		return qtdAssinaturasOm;
	}


	public void setQtdAssinaturasOm(Long qtdAssinaturasOm) {
		this.qtdAssinaturasOm = qtdAssinaturasOm;
	}

	@Column(name="QT_MINUTOS_CONCEDIDOS_OM")
	public Double getQtMinutosConcedidosOm() {
		return qtMinutosConcedidosOm;
	}


	public void setQtMinutosConcedidosOm(Double qtMinutosConcedidosOm) {
		this.qtMinutosConcedidosOm = qtMinutosConcedidosOm;
	}

	@Column(name="QT_MINUTOS_EXPIRADOS_OM")
	public Double getQtMinutosExpiradosOm() {
		return qtMinutosExpiradosOm;
	}


	public void setQtMinutosExpiradosOm(Double qtMinutosExpiradosOm) {
		this.qtMinutosExpiradosOm = qtMinutosExpiradosOm;
	}

	@Column(name="QT_MINUTOS_UTILIZADOS_OM")
	public Double getQtMinutosUtilizadosOm() {
		return qtMinutosUtilizadosOm;
	}


	public void setQtMinutosUtilizadosOm(Double qtMinutosUtilizadosOm) {
		this.qtMinutosUtilizadosOm = qtMinutosUtilizadosOm;
	}

	@Column(name="VL_BRUTO_ASSINATURA_OM")
	public Double getVlBrutoAssinaturaOm() {
		return vlBrutoAssinaturaOm;
	}


	public void setVlBrutoAssinaturaOm(Double vlBrutoAssinaturaOm) {
		this.vlBrutoAssinaturaOm = vlBrutoAssinaturaOm;
	}

	@Column(name="VL_LIQUIDO_ASSINATURA_OM")
	public Double getVlLiquidoAssinaturaOm() {
		return vlLiquidoAssinaturaOm;
	}


	public void setVlLiquidoAssinaturaOm(Double vlLiquidoAssinaturaOm) {
		this.vlLiquidoAssinaturaOm = vlLiquidoAssinaturaOm;
	}

	@Column(name="VL_PIS_OM")
	public Double getVlPisOm() {
		return vlPisOm;
	}


	public void setVlPisOm(Double vlPisOm) {
		this.vlPisOm = vlPisOm;
	}

	@Column(name="VL_COFINS_OM")
	public Double getVlCofinsOm() {
		return vlCofinsOm;
	}


	public void setVlCofinsOm(Double vlCofinsOm) {
		this.vlCofinsOm = vlCofinsOm;
	}

	@Column(name="VL_ICMS_OM")
	public Double getVlIcmsOm() {
		return vlIcmsOm;
	}


	public void setVlIcmsOm(Double vlIcmsOm) {
		this.vlIcmsOm = vlIcmsOm;
	}

	@Column(name="VL_ICMS_ANT_OM")
	public Double getVlIcmsAntOm() {
		return vlIcmsAntOm;
	}


	public void setVlIcmsAntOm(Double vlIcmsAntOm) {
		this.vlIcmsAntOm = vlIcmsAntOm;
	}

	@Column(name="VL_REPASSE_OM")
	public Double getVlRepasseOm() {
		return vlRepasseOm;
	}


	public void setVlRepasseOm(Double vlRepasseOm) {
		this.vlRepasseOm = vlRepasseOm;
	}

	@Column(name="VL_REPASSE_FINAL_OM")
	public Double getVlRepasseFinalOm() {
		return vlRepasseFinalOm;
	}


	public void setVlRepasseFinalOm(Double vlRepasseFinalOm) {
		this.vlRepasseFinalOm = vlRepasseFinalOm;
	}

	@Column(name="VL_CUSTO_OM")
	public Double getVlCustoOm() {
		return vlCustoOm;
	}


	public void setVlCustoOm(Double vlCustoOm) {
		this.vlCustoOm = vlCustoOm;
	}


	@Transient
	public boolean repassaICMS()
	{
		if (this.flRepassaIcms == null)
			return false;
		else
			return this.flRepassaIcms.equalsIgnoreCase("S");
	}
	
	public void append(SccPreFechamentoAssinatura valor) {
		SccPreFechamentoAssinatura total = this;
		total.setQtCdrs(somaValores(total.getQtCdrs(),valor.getQtCdrs()));
		total.setQtCdrsOm(somaValores(total.getQtCdrsOm(),valor.getQtCdrsOm()));
		total.setQtAssinaturas(somaValores(total.getQtAssinaturas(), valor.getQtAssinaturas()));
		total.setQtdAssinaturasOm(somaValores(total.getQtdAssinaturasOm(), valor.getQtdAssinaturasOm()));
		total.setQtMinutosConcedidos(somaValores(total.getQtMinutosConcedidos(), valor.getQtMinutosConcedidos()));
		total.setQtMinutosConcedidosOm(somaValores(total.getQtMinutosConcedidosOm(), valor.getQtMinutosConcedidosOm()));
		total.setQtMinutosExpirados(somaValores(total.getQtMinutosExpirados(), valor.getQtMinutosExpirados()));
		total.setQtMinutosExpiradosOm(somaValores(total.getQtMinutosExpiradosOm(), valor.getQtMinutosExpiradosOm()));
		total.setQtMinutosUtilizados(somaValores(total.getQtMinutosUtilizados(), valor.getQtMinutosUtilizados()));
		total.setQtMinutosUtilizadosOm(somaValores(total.getQtMinutosUtilizadosOm(), valor.getQtMinutosUtilizadosOm()));
		total.setVlBrutoAssinatura(somaValores(total.getVlBrutoAssinatura(), valor.getVlBrutoAssinatura()));
		total.setVlBrutoAssinaturaOm(somaValores(total.getVlBrutoAssinaturaOm(), valor.getVlBrutoAssinaturaOm()));
		total.setVlCofins(somaValores(total.getVlCofins(), valor.getVlCofins()));
		total.setVlCofinsOm(somaValores(total.getVlCofinsOm(), valor.getVlCofinsOm()));
		total.setVlCusto(somaValores(total.getVlCusto(), valor.getVlCusto()));
		total.setVlCustoOm(somaValores(total.getVlCustoOm(), valor.getVlCustoOm()));
		total.setVlIcms(somaValores(total.getVlIcms(), valor.getVlIcms()));
		total.setVlIcmsOm(somaValores(total.getVlIcmsOm(), valor.getVlIcmsOm()));
		total.setVlIcmsAnt(somaValores(total.getVlIcmsAnt(), valor.getVlIcmsAnt()));
		total.setVlIcmsAntOm(somaValores(total.getVlIcmsAntOm(), valor.getVlIcmsAntOm()));
		total.setVlLiquidoAssinatura(somaValores(total.getVlLiquidoAssinatura(), valor.getVlLiquidoAssinatura()));
		total.setVlLiquidoAssinaturaOm(somaValores(total.getVlLiquidoAssinaturaOm(), valor.getVlLiquidoAssinaturaOm()));
		total.setVlPis(somaValores(total.getVlPis(), valor.getVlPis()));
		total.setVlPisOm(somaValores(total.getVlPisOm(), valor.getVlPisOm()));
		total.setVlRepasse(somaValores(total.getVlRepasse(), valor.getVlRepasse()));
		total.setVlRepasseOm(somaValores(total.getVlRepasseOm(), valor.getVlRepasseOm()));
		total.setVlRepasseFinal(somaValores(total.getVlRepasseFinal(), valor.getVlRepasseFinal()));
		total.setVlRepasseFinalOm(somaValores(total.getVlRepasseFinalOm(), valor.getVlRepasseFinalOm()));
	}
	private Long somaValores(Long v1, Long v2) {
		Long val = null;
		if (v1 != null && v2 != null)
			val = v1 + v2;
		else if (v1 != null)
			val = v1;
		else
			val = v2;
		return val;
	}
	private Double somaValores(Double v1, Double v2) {
		Double val = null;
		if (v1 != null && v2 != null)
			val = v1 + v2;
		else if (v1 != null)
			val = v1;
		else
			val = v2;
		return val;
	}
}
