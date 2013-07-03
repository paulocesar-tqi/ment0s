package com.claro.cobillingweb.persistence.view;

import java.util.List;

public class RelatorioApuracaoSumarizado {
	

	private List<RelatorioApuracaoFechamentoPrePagoView> listSumarizado;
	private double valorApuradoLiquido = 0;
	private double pisCofins = 0;
	private double icmsRepassar = 0;
	private double icmsNaoRepassado = 0;
	private double valorRepassarSumarizado = 0;
	private double servPrestLiquido = 0;
	private double pisCofinsServPrest = 0;
	private double iss = 0;
	private double vlrBrutoServPrest = 0;
	private double creditoAutorizados = 0;
	private double creditos226 = 0;
	private double penalidadesMinutPerdidos = 0;
	private double totalMultasJuros = 0;
	private double totalAcertosConciliacao = 0;
	private double cpmfDescontar = 0;
	private double icmsDescontar = 0;
	private double icmsaRepassar = 0;
	private double vlrFinalRepassar = 0;
	private double vlrNotaFiscal = 0;
	private double destaqueIcms = 0;
	
	private String dsOperadoraClaro;
	private String dsOperadoraHolding;
	private String cdEotHolding;
	

	
	public RelatorioApuracaoSumarizado(List<RelatorioApuracaoFechamentoPrePagoView> listSumarizado) {
		this.listSumarizado = listSumarizado;
	}

	public List<RelatorioApuracaoFechamentoPrePagoView> getListSumarizado() {
		return listSumarizado;
	}

	public double getValorApuradoLiquido() {
		return valorApuradoLiquido;
	}

	public void setValorApuradoLiquido(double valorApuradoLiquido) {
		this.valorApuradoLiquido = valorApuradoLiquido;
	}

	public double getPisCofins() {
		return pisCofins;
	}

	public void setPisCofins(double pisCofins) {
		this.pisCofins = pisCofins;
	}

	public double getIcmsRepassar() {
		return icmsRepassar;
	}

	public void setIcmsRepassar(double icmsRepassar) {
		this.icmsRepassar = icmsRepassar;
	}

	public double getIcmsNaoRepassado() {
		return icmsNaoRepassado;
	}

	public void setIcmsNaoRepassado(double icmsNaoRepassado) {
		this.icmsNaoRepassado = icmsNaoRepassado;
	}

	public double getValorRepassarSumarizado() {
		return valorRepassarSumarizado;
	}

	public void setValorRepassarSumarizado(double valorRepassarSumarizado) {
		this.valorRepassarSumarizado = valorRepassarSumarizado;
	}

	public double getServPrestLiquido() {
		return servPrestLiquido;
	}

	public void setServPrestLiquido(double servPrestLiquido) {
		this.servPrestLiquido = servPrestLiquido;
	}

	public double getPisCofinsServPrest() {
		return pisCofinsServPrest;
	}

	public void setPisCofinsServPrest(double pisCofinsServPrest) {
		this.pisCofinsServPrest = pisCofinsServPrest;
	}

	public double getIss() {
		return iss;
	}

	public void setIss(double iss) {
		this.iss = iss;
	}

	public double getVlrBrutoServPrest() {
		return vlrBrutoServPrest;
	}

	public void setVlrBrutoServPrest(double vlrBrutoServPrest) {
		this.vlrBrutoServPrest = vlrBrutoServPrest;
	}

	public double getCreditoAutorizados() {
		return creditoAutorizados;
	}

	public void setCreditoAutorizados(double creditoAutorizados) {
		this.creditoAutorizados = creditoAutorizados;
	}

	public double getCreditos226() {
		return creditos226;
	}

	public void setCreditos226(double creditos226) {
		this.creditos226 = creditos226;
	}

	public double getPenalidadesMinutPerdidos() {
		return penalidadesMinutPerdidos;
	}

	public void setPenalidadesMinutPerdidos(double penalidadesMinutPerdidos) {
		this.penalidadesMinutPerdidos = penalidadesMinutPerdidos;
	}

	public double getTotalMultasJuros() {
		return totalMultasJuros;
	}

	public void setTotalMultasJuros(double totalMultasJuros) {
		this.totalMultasJuros = totalMultasJuros;
	}

	public double getTotalAcertosConciliacao() {
		return totalAcertosConciliacao;
	}

	public void setTotalAcertosConciliacao(double totalAcertosConciliacao) {
		this.totalAcertosConciliacao = totalAcertosConciliacao;
	}

	public double getCpmfDescontar() {
		return cpmfDescontar;
	}

	public void setCpmfDescontar(double cpmfDescontar) {
		this.cpmfDescontar = cpmfDescontar;
	}

	public double getIcmsDescontar() {
		return icmsDescontar;
	}

	public void setIcmsDescontar(double icmsDescontar) {
		this.icmsDescontar = icmsDescontar;
	}

	public double getIcmsaRepassar() {
		return icmsaRepassar;
	}

	public void setIcmsaRepassar(double icmsaRepassar) {
		this.icmsaRepassar = icmsaRepassar;
	}

	public double getVlrFinalRepassar() {
		return vlrFinalRepassar;
	}

	public void setVlrFinalRepassar(double vlrFinalRepassar) {
		this.vlrFinalRepassar = vlrFinalRepassar;
	}

	public double getVlrNotaFiscal() {
		return vlrNotaFiscal;
	}

	public void setVlrNotaFiscal(double vlrNotaFiscal) {
		this.vlrNotaFiscal = vlrNotaFiscal;
	}

	public double getDestaqueIcms() {
		return destaqueIcms;
	}

	public void setDestaqueIcms(double destaqueIcms) {
		this.destaqueIcms = destaqueIcms;
	}

	public void setListSumarizado(
			List<RelatorioApuracaoFechamentoPrePagoView> listSumarizado) {
		this.listSumarizado = listSumarizado;
	}

	public String getDsOperadoraClaro() {
		return dsOperadoraClaro;
	}

	public void setDsOperadoraClaro(String dsOperadoraClaro) {
		this.dsOperadoraClaro = dsOperadoraClaro;
	}

	public String getDsOperadoraHolding() {
		return dsOperadoraHolding;
	}

	public void setDsOperadoraHolding(String dsOperadoraHolding) {
		this.dsOperadoraHolding = dsOperadoraHolding;
	}

	public String getCdEotHolding() {
		return cdEotHolding;
	}

	public void setCdEotHolding(String cdEotHolding) {
		this.cdEotHolding = cdEotHolding;
	}
}
