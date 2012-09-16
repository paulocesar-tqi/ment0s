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
@Table(name="SCC_PRE_FECHAMENTO")
public class SccPreFechamento {

	/*Contantes para campo dsCriterioCusto*/
	public static final String CRITERIO_CHAMADAS = "POR CHAMADA";
	public static final String CRITERIO_MINUTO = "POR MINUTO";
	
	private static final long serialVersionUID = 1L;
	private Long sqPreFechamento;
	private String cdEotClaro;
	private String cdEotLd;
	private String cdStatusFechamento;
	private String dsCriterioCusto;
	private Date dtAtualizacao;
	private Date dtFechamento;
	private Date dtFimFechamento;
	private Date dtInicialFechamento;
	private String flRepassaCpmf;
	private String flRepassaIcms;
	private Double qtCdrs = 0.0;
	private Double qtCdrs226 = 0.0;
	private Double qtCdrsOm = 0.0;
	private Double qtDuracaoReal = 0.0;
	private Long qtDuracaoRealOm;
	private Double qtDuracaoTarifada;
	private Double qtDuracaoTarifada226 = 0.0;
	private Double qtDuracaoTarifadaOm;
	private Long sqPedido;
	private Double vlAcertoClaro = 0.0;
	private Double vlAcertoLd = 0.0;
	private Double vlBrutoChamada = 0.0;
	private Double vlBrutoChamadaOm = 0.0;
	private Double vlCofins = 0.0;
	private Double vlCofinsOm = 0.0;
	private Double vlCpmf = 0.0;
	private Double vlCredito226 = 0.0;
	private Double vlCreditoAut = 0.0;
	private Double vlFinalRepassar = 0.0;
	private Double vlIcms = 0.0;
	private Double vlIcms226 = 0.0;
	private Double vlIcmsAnt = 0.0;
	private Double vlIcmsOm = 0.0;
	private Double vlLiquido226 = 0.0;
	private Double vlLiquidoChamada = 0.0;
	private Double vlLiquidoChamadaOm = 0.0;
	private Double vlMultasClaro = 0.0;
	private Double vlMultasLd = 0.0;
	private Double vlPenalMinPerd = 0.0;
	private Double vlPis = 0.0;
	private Double vlPisCofins226 = 0.0;
	private Double vlPisOm = 0.0;
	private Double vlServPrestBruto = 0.0;
	private Double vlServPrestCofins = 0.0;
	private Double vlServPrestIss = 0.0;
	private Double vlServPrestPis = 0.0;
	private String cdProdutoPrepago;

    public SccPreFechamento() {
    }


	@Id
	@Column(name="SQ_PRE_FECHAMENTO")
	public Long getSqPreFechamento() {
		return this.sqPreFechamento;
	}

	public void setSqPreFechamento(Long sqPreFechamento) {
		this.sqPreFechamento = sqPreFechamento;
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


	@Column(name="CD_STATUS_FECHAMENTO")
	public String getCdStatusFechamento() {
		return this.cdStatusFechamento;
	}

	public void setCdStatusFechamento(String cdStatusFechamento) {
		this.cdStatusFechamento = cdStatusFechamento;
	}


	@Column(name="DS_CRITERIO_CUSTO")
	public String getDsCriterioCusto() {
		return this.dsCriterioCusto;
	}

	public void setDsCriterioCusto(String dsCriterioCusto) {
		this.dsCriterioCusto = dsCriterioCusto;
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


	@Column(name="FL_REPASSA_CPMF")
	public String getFlRepassaCpmf() {
		return this.flRepassaCpmf;
	}

	public void setFlRepassaCpmf(String flRepassaCpmf) {
		this.flRepassaCpmf = flRepassaCpmf;
	}


	@Column(name="FL_REPASSA_ICMS")
	public String getFlRepassaIcms() {
		return this.flRepassaIcms;
	}

	public void setFlRepassaIcms(String flRepassaIcms) {
		this.flRepassaIcms = flRepassaIcms;
	}


	@Column(name="QT_CDRS")
	public Double getQtCdrs() {
		return this.qtCdrs;
	}

	public void setQtCdrs(Double qtCdrs) {
		this.qtCdrs = qtCdrs;
	}


	@Column(name="QT_CDRS_226")
	public Double getQtCdrs226() {
		return this.qtCdrs226;
	}

	public void setQtCdrs226(Double qtCdrs226) {
		this.qtCdrs226 = qtCdrs226;
	}


	@Column(name="QT_CDRS_OM")
	public Double getQtCdrsOm() {
		return this.qtCdrsOm;
	}

	public void setQtCdrsOm(Double qtCdrsOm) {
		this.qtCdrsOm = qtCdrsOm;
	}


	@Column(name="QT_DURACAO_REAL")
	public Double getQtDuracaoReal() {
		return this.qtDuracaoReal;
	}

	public void setQtDuracaoReal(Double qtDuracaoReal) {
		this.qtDuracaoReal = qtDuracaoReal;
	}


	@Column(name="QT_DURACAO_REAL_OM",precision=18,scale=5)
	public Long getQtDuracaoRealOm() {
		return this.qtDuracaoRealOm;
	}

	public void setQtDuracaoRealOm(Long qtDuracaoRealOm) {
		this.qtDuracaoRealOm = qtDuracaoRealOm;
	}


	@Column(name="QT_DURACAO_TARIFADA",precision=18,scale=5)
	public Double getQtDuracaoTarifada() {
		return this.qtDuracaoTarifada;
	}

	public void setQtDuracaoTarifada(Double qtDuracaoTarifada) {
		this.qtDuracaoTarifada = qtDuracaoTarifada;
	}


	@Column(name="QT_DURACAO_TARIFADA_226")
	public Double getQtDuracaoTarifada226() {
		return this.qtDuracaoTarifada226;
	}

	public void setQtDuracaoTarifada226(Double qtDuracaoTarifada226) {
		this.qtDuracaoTarifada226 = qtDuracaoTarifada226;
	}


	@Column(name="QT_DURACAO_TARIFADA_OM")
	public Double getQtDuracaoTarifadaOm() {
		return this.qtDuracaoTarifadaOm;
	}

	public void setQtDuracaoTarifadaOm(Double qtDuracaoTarifadaOm) {
		this.qtDuracaoTarifadaOm = qtDuracaoTarifadaOm;
	}


	@Column(name="SQ_PEDIDO")
	public Long getSqPedido() {
		return this.sqPedido;
	}

	public void setSqPedido(Long sqPedido) {
		this.sqPedido = sqPedido;
	}


	@Column(name="VL_ACERTO_CLARO")
	public Double getVlAcertoClaro() {
		return this.vlAcertoClaro;
	}

	public void setVlAcertoClaro(Double vlAcertoClaro) {
		this.vlAcertoClaro = vlAcertoClaro;
	}


	@Column(name="VL_ACERTO_LD")
	public Double getVlAcertoLd() {
		return this.vlAcertoLd;
	}

	public void setVlAcertoLd(Double vlAcertoLd) {
		this.vlAcertoLd = vlAcertoLd;
	}


	@Column(name="VL_BRUTO_CHAMADA")
	public Double getVlBrutoChamada() {
		return this.vlBrutoChamada;
	}

	public void setVlBrutoChamada(Double vlBrutoChamada) {
		this.vlBrutoChamada = vlBrutoChamada;
	}


	@Column(name="VL_BRUTO_CHAMADA_OM")
	public Double getVlBrutoChamadaOm() {
		return this.vlBrutoChamadaOm;
	}

	public void setVlBrutoChamadaOm(Double vlBrutoChamadaOm) {
		this.vlBrutoChamadaOm = vlBrutoChamadaOm;
	}


	@Column(name="VL_COFINS")
	public Double getVlCofins() {
		return this.vlCofins;
	}

	public void setVlCofins(Double vlCofins) {
		this.vlCofins = vlCofins;
	}


	@Column(name="VL_COFINS_OM")
	public Double getVlCofinsOm() {
		return this.vlCofinsOm;
	}

	public void setVlCofinsOm(Double vlCofinsOm) {
		this.vlCofinsOm = vlCofinsOm;
	}


	@Column(name="VL_CPMF")
	public Double getVlCpmf() {
		return this.vlCpmf;
	}

	public void setVlCpmf(Double vlCpmf) {
		this.vlCpmf = vlCpmf;
	}


	@Column(name="VL_CREDITO_226")
	public Double getVlCredito226() {
		return this.vlCredito226;
	}

	public void setVlCredito226(Double vlCredito226) {
		this.vlCredito226 = vlCredito226;
	}


	@Column(name="VL_CREDITO_AUT")
	public Double getVlCreditoAut() {
		return this.vlCreditoAut;
	}

	public void setVlCreditoAut(Double vlCreditoAut) {
		this.vlCreditoAut = vlCreditoAut;
	}


	@Column(name="VL_FINAL_REPASSAR")
	public Double getVlFinalRepassar() {
		return this.vlFinalRepassar;
	}

	public void setVlFinalRepassar(Double vlFinalRepassar) {
		this.vlFinalRepassar = vlFinalRepassar;
	}


	@Column(name="VL_ICMS")
	public Double getVlIcms() {
		return this.vlIcms;
	}

	public void setVlIcms(Double vlIcms) {
		this.vlIcms = vlIcms;
	}


	@Column(name="VL_ICMS_226")
	public Double getVlIcms226() {
		return this.vlIcms226;
	}

	public void setVlIcms226(Double vlIcms226) {
		this.vlIcms226 = vlIcms226;
	}


	@Column(name="VL_ICMS_ANT")
	public Double getVlIcmsAnt() {
		return this.vlIcmsAnt;
	}

	public void setVlIcmsAnt(Double vlIcmsAnt) {
		this.vlIcmsAnt = vlIcmsAnt;
	}


	@Column(name="VL_ICMS_OM")
	public Double getVlIcmsOm() {
		return this.vlIcmsOm;
	}

	public void setVlIcmsOm(Double vlIcmsOm) {
		this.vlIcmsOm = vlIcmsOm;
	}


	@Column(name="VL_LIQUIDO_226")
	public Double getVlLiquido226() {
		return this.vlLiquido226;
	}

	public void setVlLiquido226(Double vlLiquido226) {
		this.vlLiquido226 = vlLiquido226;
	}


	@Column(name="VL_LIQUIDO_CHAMADA")
	public Double getVlLiquidoChamada() {
		return this.vlLiquidoChamada;
	}

	public void setVlLiquidoChamada(Double vlLiquidoChamada) {
		this.vlLiquidoChamada = vlLiquidoChamada;
	}


	@Column(name="VL_LIQUIDO_CHAMADA_OM")
	public Double getVlLiquidoChamadaOm() {
		return this.vlLiquidoChamadaOm;
	}

	public void setVlLiquidoChamadaOm(Double vlLiquidoChamadaOm) {
		this.vlLiquidoChamadaOm = vlLiquidoChamadaOm;
	}


	@Column(name="VL_MULTAS_CLARO")
	public Double getVlMultasClaro() {
		return this.vlMultasClaro;
	}

	public void setVlMultasClaro(Double vlMultasClaro) {
		this.vlMultasClaro = vlMultasClaro;
	}


	@Column(name="VL_MULTAS_LD")
	public Double getVlMultasLd() {
		return this.vlMultasLd;
	}

	public void setVlMultasLd(Double vlMultasLd) {
		this.vlMultasLd = vlMultasLd;
	}


	@Column(name="VL_PENAL_MIN_PERD")
	public Double getVlPenalMinPerd() {
		return this.vlPenalMinPerd;
	}

	public void setVlPenalMinPerd(Double vlPenalMinPerd) {
		this.vlPenalMinPerd = vlPenalMinPerd;
	}


	@Column(name="VL_PIS")
	public Double getVlPis() {
		return this.vlPis;
	}

	public void setVlPis(Double vlPis) {
		this.vlPis = vlPis;
	}


	@Column(name="VL_PIS_COFINS_226")
	public Double getVlPisCofins226() {
		return this.vlPisCofins226;
	}

	public void setVlPisCofins226(Double vlPisCofins226) {
		this.vlPisCofins226 = vlPisCofins226;
	}


	@Column(name="VL_PIS_OM")
	public Double getVlPisOm() {
		return this.vlPisOm;
	}

	public void setVlPisOm(Double vlPisOm) {
		this.vlPisOm = vlPisOm;
	}


	@Column(name="VL_SERV_PREST_BRUTO")
	public Double getVlServPrestBruto() {
		return this.vlServPrestBruto;
	}

	public void setVlServPrestBruto(Double vlServPrestBruto) {
		this.vlServPrestBruto = vlServPrestBruto;
	}


	@Column(name="VL_SERV_PREST_COFINS")
	public Double getVlServPrestCofins() {
		return this.vlServPrestCofins;
	}

	public void setVlServPrestCofins(Double vlServPrestCofins) {
		this.vlServPrestCofins = vlServPrestCofins;
	}


	@Column(name="VL_SERV_PREST_ISS")
	public Double getVlServPrestIss() {
		return this.vlServPrestIss;
	}

	public void setVlServPrestIss(Double vlServPrestIss) {
		this.vlServPrestIss = vlServPrestIss;
	}


	@Column(name="VL_SERV_PREST_PIS")
	public Double getVlServPrestPis() {
		return this.vlServPrestPis;
	}

	public void setVlServPrestPis(Double vlServPrestPis) {
		this.vlServPrestPis = vlServPrestPis;
	}


	

	@Column(name="CD_PRODUTO_PREPAGO")
	public String getCdProdutoPrepago() {
		return cdProdutoPrepago;
	}


	public void setCdProdutoPrepago(String cdProdutoPrepago) {
		this.cdProdutoPrepago = cdProdutoPrepago;
	}
	
	@Transient
	public boolean repassarICMS()
	{
		if ((getFlRepassaIcms() != null) && (getFlRepassaIcms().equalsIgnoreCase("S")))
			return true;
		return false;
	}
	
}
