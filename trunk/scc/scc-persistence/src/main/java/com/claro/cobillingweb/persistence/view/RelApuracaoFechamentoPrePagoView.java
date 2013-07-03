package com.claro.cobillingweb.persistence.view;

import java.util.Date;

/**
 * View para a query que monta o relatório de Resumo de Apuração
 * para repasse de pré-pago. 
 *
 */
/*Devido ao prazo muito curto , a query será usada da forma original sem análise 
 * de performance.
 * Os campos valorXX são derivados do SQL original. Analisar e renomear esses campos 
 * fica fora do escopo devido ao prazo.
 * */
public class RelApuracaoFechamentoPrePagoView {

	private String cdEOTLD;
	private Date dtInicialFechamento;
	private Date dtFinalFechamento;
	private String cdEOTClaro;
	private Date dtFechamento;
	private String cdStatusFechamento;
	private Long sqPedido;
	private Long qtCdrs;
	private Double valor10;
	private Double valor11;
	private Double valor12;
	private Double valor13;
	private Double valor14;
	private Double valor15;
	private Double valor16;
	private Long qtCdrsOm;
	private Double valor18;
	private Double valor19;
	private Double valor20;
	private Double valor21;
	private Double valor22;
	private Double valor23;
	private Double valor24;
	private Double valor25;
	private Double valor26;
	private Double valor27;
	private Double valor28;
	private Double valor29;
	private Long qtCdrs226;
	private Double valor31;
	private Double valor32;
	private Double valor33;
	private Double valor34;
	private Double valor35;
	private Double valor36;
	private Double valor37;
	private Double valor38;
	private Double valor39;
	private String repasseCPFM;
	private String repassaICMS;
	private Double valor42;
	private Double valor43;
	private Double valor44;
	private String dsOperadoraHolding;
	private String dsOperadora;
	
	private String ufClaro;
	private String cdEotHolding;
	private String dsOperadoraLd;
	private Double vlrLiquidoChamada;
	private Double vlrBrutoChamada;
	private Double qtDuracaoRealOm;
	private Double qtdDuracaoTarifadaOm;
	
	private Double vlrIcmOm;
	private Double vrlPisOm;
	private Double vlrCofinsOm;
	private Double vrlServPrestBruto;
	private Double vlrServPrestPis;
	private Double vlrServPrestCofins;
	private Double vlrServPrestIss;
	private Double vlrCreditoAut;
	private Long qtdCrs;
	private Double qtdDuracaoTarifada;
	private Double qtdDuracaoTarifada226;
	private Double vlrCredito226;
	private Double vlrPenalMinPerd;
	private Double vlrMultasClaro;
	private Double vlrMultasLd;
	private Double vlrAcertosClaro;
	private Double vlrAcertosLd;
	private Double vlrIcmsAnt;
	private Double vlrCpmf;
	private String flRepassaCpmf;
	private String flRepassaIcms;
	private Double vlrLiquido226;
	private Double vlrPisCofins226;
	private Double vlrIcms226;
	
	private String criterioCusto;
	private String sqUfClaro;
	

	public String getCdEOTLD() {
		return cdEOTLD;
	}
	public void setCdEOTLD(String cdEOTLD) {
		this.cdEOTLD = cdEOTLD;
	}
	public Date getDtInicialFechamento() {
		return dtInicialFechamento;
	}
	public void setDtInicialFechamento(Date dtInicialFechamento) {
		this.dtInicialFechamento = dtInicialFechamento;
	}
	public Date getDtFinalFechamento() {
		return dtFinalFechamento;
	}
	public void setDtFinalFechamento(Date dtFinalFechamento) {
		this.dtFinalFechamento = dtFinalFechamento;
	}
	public String getCdEOTClaro() {
		return cdEOTClaro;
	}
	public void setCdEOTClaro(String cdEOTClaro) {
		this.cdEOTClaro = cdEOTClaro;
	}
	public Date getDtFechamento() {
		return dtFechamento;
	}
	public void setDtFechamento(Date dtFechamento) {
		this.dtFechamento = dtFechamento;
	}
	public String getCdStatusFechamento() {
		return cdStatusFechamento;
	}
	public void setCdStatusFechamento(String cdStatusFechamento) {
		this.cdStatusFechamento = cdStatusFechamento;
	}
	public Long getSqPedido() {
		return sqPedido;
	}
	public void setSqPedido(Long sqPedido) {
		this.sqPedido = sqPedido;
	}
	public Long getQtCdrs() {
		return qtCdrs;
	}
	public void setQtCdrs(Long qtCdrs) {
		this.qtCdrs = qtCdrs;
	}
	public Double getValor10() {
		return valor10;
	}
	public void setValor10(Double valor10) {
		this.valor10 = valor10;
	}
	public Double getValor11() {
		return valor11;
	}
	public void setValor11(Double valor11) {
		this.valor11 = valor11;
	}
	public Double getValor12() {
		return valor12;
	}
	public void setValor12(Double valor12) {
		this.valor12 = valor12;
	}
	public Double getValor13() {
		return valor13;
	}
	public void setValor13(Double valor13) {
		this.valor13 = valor13;
	}
	public Double getValor14() {
		return valor14;
	}
	public void setValor14(Double valor14) {
		this.valor14 = valor14;
	}
	public Double getValor15() {
		return valor15;
	}
	public void setValor15(Double valor15) {
		this.valor15 = valor15;
	}
	public Double getValor16() {
		return valor16;
	}
	public void setValor16(Double valor16) {
		this.valor16 = valor16;
	}
	public Long getQtCdrsOm() {
		return qtCdrsOm;
	}
	public void setQtCdrsOm(Long qtCdrsOm) {
		this.qtCdrsOm = qtCdrsOm;
	}
	public Double getValor18() {
		return valor18;
	}
	public void setValor18(Double valor18) {
		this.valor18 = valor18;
	}
	public Double getValor19() {
		return valor19;
	}
	public void setValor19(Double valor19) {
		this.valor19 = valor19;
	}
	public Double getValor20() {
		return valor20;
	}
	public void setValor20(Double valor20) {
		this.valor20 = valor20;
	}
	public Double getValor21() {
		return valor21;
	}
	public void setValor21(Double valor21) {
		this.valor21 = valor21;
	}
	public Double getValor22() {
		return valor22;
	}
	public void setValor22(Double valor22) {
		this.valor22 = valor22;
	}
	public Double getValor23() {
		return valor23;
	}
	public void setValor23(Double valor23) {
		this.valor23 = valor23;
	}
	public Double getValor24() {
		return valor24;
	}
	public void setValor24(Double valor24) {
		this.valor24 = valor24;
	}
	public Double getValor25() {
		return valor25;
	}
	public void setValor25(Double valor25) {
		this.valor25 = valor25;
	}
	public Double getValor26() {
		return valor26;
	}
	public void setValor26(Double valor26) {
		this.valor26 = valor26;
	}
	public Double getValor27() {
		return valor27;
	}
	public void setValor27(Double valor27) {
		this.valor27 = valor27;
	}
	public Double getValor28() {
		return valor28;
	}
	public void setValor28(Double valor28) {
		this.valor28 = valor28;
	}
	public Double getValor29() {
		return valor29;
	}
	public void setValor29(Double valor29) {
		this.valor29 = valor29;
	}
	public Long getQtCdrs226() {
		return qtCdrs226;
	}
	public void setQtCdrs226(Long qtCdrs226) {
		this.qtCdrs226 = qtCdrs226;
	}
	public Double getValor31() {
		return valor31;
	}
	public void setValor31(Double valor31) {
		this.valor31 = valor31;
	}
	public Double getValor32() {
		return valor32;
	}
	public void setValor32(Double valor32) {
		this.valor32 = valor32;
	}
	public Double getValor33() {
		return valor33;
	}
	public void setValor33(Double valor33) {
		this.valor33 = valor33;
	}
	public Double getValor34() {
		return valor34;
	}
	public void setValor34(Double valor34) {
		this.valor34 = valor34;
	}
	public Double getValor35() {
		return valor35;
	}
	public void setValor35(Double valor35) {
		this.valor35 = valor35;
	}
	public Double getValor36() {
		return valor36;
	}
	public void setValor36(Double valor36) {
		this.valor36 = valor36;
	}
	public Double getValor37() {
		return valor37;
	}
	public void setValor37(Double valor37) {
		this.valor37 = valor37;
	}
	public Double getValor38() {
		return valor38;
	}
	public void setValor38(Double valor38) {
		this.valor38 = valor38;
	}
	public Double getValor39() {
		return valor39;
	}
	public void setValor39(Double valor39) {
		this.valor39 = valor39;
	}
	public String getRepasseCPFM() {
		return repasseCPFM;
	}
	public void setRepasseCPFM(String repasseCPFM) {
		this.repasseCPFM = repasseCPFM;
	}
	public String getRepassaICMS() {
		return repassaICMS;
	}
	public void setRepassaICMS(String repassaICMS) {
		this.repassaICMS = repassaICMS;
	}
	public Double getValor42() {
		return valor42;
	}
	public void setValor42(Double valor42) {
		this.valor42 = valor42;
	}
	public Double getValor43() {
		return valor43;
	}
	public void setValor43(Double valor43) {
		this.valor43 = valor43;
	}
	public Double getValor44() {
		return valor44;
	}
	public void setValor44(Double valor44) {
		this.valor44 = valor44;
	}
	public String getDsOperadora() {
		return dsOperadora;
	}
	public void setDsOperadora(String dsOperadora) {
		this.dsOperadora = dsOperadora;
	}
	public String getCriterioCusto() {
		return criterioCusto;
	}
	public void setCriterioCusto(String criterioCusto) {
		this.criterioCusto = criterioCusto;
	}
	public String getUfClaro() {
		return ufClaro;
	}
	public void setUfClaro(String ufClaro) {
		this.ufClaro = ufClaro;
	}
	public String getCdEotHolding() {
		return cdEotHolding;
	}
	public void setCdEotHolding(String cdEotHolding) {
		this.cdEotHolding = cdEotHolding;
	}
	public String getDsOperadoraLd() {
		return dsOperadoraLd;
	}
	public void setDsOperadoraLd(String dsOperadoraLd) {
		this.dsOperadoraLd = dsOperadoraLd;
	}
	public Double getVlrLiquidoChamada() {
		return vlrLiquidoChamada;
	}
	public void setVlrLiquidoChamada(Double vlrLiquidoChamada) {
		this.vlrLiquidoChamada = vlrLiquidoChamada;
	}
	public Double getVlrBrutoChamada() {
		return vlrBrutoChamada;
	}
	public void setVlrBrutoChamada(Double vlrBrutoChamada) {
		this.vlrBrutoChamada = vlrBrutoChamada;
	}
	public Double getQtDuracaoRealOm() {
		return qtDuracaoRealOm;
	}
	public void setQtDuracaoRealOm(Double qtDuracaoRealOm) {
		this.qtDuracaoRealOm = qtDuracaoRealOm;
	}
	public Double getVlrIcmOm() {
		return vlrIcmOm;
	}
	public void setVlrIcmOm(Double vlrIcmOm) {
		this.vlrIcmOm = vlrIcmOm;
	}
	public Double getVrlPisOm() {
		return vrlPisOm;
	}
	public void setVrlPisOm(Double vrlPisOm) {
		this.vrlPisOm = vrlPisOm;
	}
	public Double getVlrCofinsOm() {
		return vlrCofinsOm;
	}
	public void setVlrCofinsOm(Double vlrCofinsOm) {
		this.vlrCofinsOm = vlrCofinsOm;
	}
	public Double getVrlServPrestBruto() {
		return vrlServPrestBruto;
	}
	public void setVrlServPrestBruto(Double vrlServPrestBruto) {
		this.vrlServPrestBruto = vrlServPrestBruto;
	}
	public Double getVlrServPrestPis() {
		return vlrServPrestPis;
	}
	public void setVlrServPrestPis(Double vlrServPrestPis) {
		this.vlrServPrestPis = vlrServPrestPis;
	}
	public Double getVlrServPrestCofins() {
		return vlrServPrestCofins;
	}
	public void setVlrServPrestCofins(Double vlrServPrestCofins) {
		this.vlrServPrestCofins = vlrServPrestCofins;
	}
	public Double getVlrServPrestIss() {
		return vlrServPrestIss;
	}
	public void setVlrServPrestIss(Double vlrServPrestIss) {
		this.vlrServPrestIss = vlrServPrestIss;
	}
	public Double getVlrCreditoAut() {
		return vlrCreditoAut;
	}
	public void setVlrCreditoAut(Double vlrCreditoAut) {
		this.vlrCreditoAut = vlrCreditoAut;
	}
	public Long getQtdCrs() {
		return qtdCrs;
	}
	public void setQtdCrs(Long qtdCrs) {
		this.qtdCrs = qtdCrs;
	}
	public Double getQtdDuracaoTarifada() {
		return qtdDuracaoTarifada;
	}
	public void setQtdDuracaoTarifada(Double qtdDuracaoTarifada) {
		this.qtdDuracaoTarifada = qtdDuracaoTarifada;
	}
	public Double getVlrCredito226() {
		return vlrCredito226;
	}
	public void setVlrCredito226(Double vlrCredito226) {
		this.vlrCredito226 = vlrCredito226;
	}
	public Double getVlrPenalMinPerd() {
		return vlrPenalMinPerd;
	}
	public void setVlrPenalMinPerd(Double vlrPenalMinPerd) {
		this.vlrPenalMinPerd = vlrPenalMinPerd;
	}
	public Double getVlrMultasClaro() {
		return vlrMultasClaro;
	}
	public void setVlrMultasClaro(Double vlrMultasClaro) {
		this.vlrMultasClaro = vlrMultasClaro;
	}
	public Double getVlrMultasLd() {
		return vlrMultasLd;
	}
	public void setVlrMultasLd(Double vlrMultasLd) {
		this.vlrMultasLd = vlrMultasLd;
	}
	public Double getVlrAcertosClaro() {
		return vlrAcertosClaro;
	}
	public void setVlrAcertosClaro(Double vlrAcertosClaro) {
		this.vlrAcertosClaro = vlrAcertosClaro;
	}
	public Double getVlrAcertosLd() {
		return vlrAcertosLd;
	}
	public void setVlrAcertosLd(Double vlrAcertosLd) {
		this.vlrAcertosLd = vlrAcertosLd;
	}
	public Double getVlrIcmsAnt() {
		return vlrIcmsAnt;
	}
	public void setVlrIcmsAnt(Double vlrIcmsAnt) {
		this.vlrIcmsAnt = vlrIcmsAnt;
	}
	public Double getVlrCpmf() {
		return vlrCpmf;
	}
	public void setVlrCpmf(Double vlrCpmf) {
		this.vlrCpmf = vlrCpmf;
	}
	public String getFlRepassaCpmf() {
		return flRepassaCpmf;
	}
	public void setFlRepassaCpmf(String flRepassaCpmf) {
		this.flRepassaCpmf = flRepassaCpmf;
	}
	public String getFlRepassaIcms() {
		return flRepassaIcms;
	}
	public void setFlRepassaIcms(String flRepassaIcms) {
		this.flRepassaIcms = flRepassaIcms;
	}
	public Double getVlrLiquido226() {
		return vlrLiquido226;
	}
	public void setVlrLiquido226(Double vlrLiquido226) {
		this.vlrLiquido226 = vlrLiquido226;
	}
	public Double getVlrPisCofins226() {
		return vlrPisCofins226;
	}
	public void setVlrPisCofins226(Double vlrPisCofins226) {
		this.vlrPisCofins226 = vlrPisCofins226;
	}
	public Double getVlrIcms226() {
		return vlrIcms226;
	}
	public void setVlrIcms226(Double vlrIcms226) {
		this.vlrIcms226 = vlrIcms226;
	}
	public String getDsOperadoraHolding() {
		return dsOperadoraHolding;
	}
	public void setDsOperadoraHolding(String dsOperadoraHolding) {
		this.dsOperadoraHolding = dsOperadoraHolding;
	}
	public String getSqUfClaro() {
		return sqUfClaro;
	}
	public void setSqUfClaro(String sqUfClaro) {
		this.sqUfClaro = sqUfClaro;
	}
	public Double getQtdDuracaoTarifadaOm() {
		return qtdDuracaoTarifadaOm;
	}
	public void setQtdDuracaoTarifadaOm(Double qtdDuracaoTarifadaOm) {
		this.qtdDuracaoTarifadaOm = qtdDuracaoTarifadaOm;
	}
	public Double getQtdDuracaoTarifada226() {
		return qtdDuracaoTarifada226;
	}
	public void setQtdDuracaoTarifada226(Double qtdDuracaoTarifada226) {
		this.qtdDuracaoTarifada226 = qtdDuracaoTarifada226;
	}
	
	
	
	   
      
}
