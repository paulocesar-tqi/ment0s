package com.claro.sccweb.decorator;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.claro.sccweb.excel.ExcelUtils;


/**
 * Decorator para demonstrativo de repasse pré-pago.
 * Esse decorator (assim como o de pós-pago) é gerado em um service e não em um controller uma vez que
 * o layout/formatação do demonstrativo faz parte da regra de negócio. 
 * 
 *
 */
public class DemonstrativoRepassePreItemDecorator {

	private static NumberFormat sccCurrencyFormat = new DecimalFormat("#.##");
	
	static {
    	Locale locale = new Locale("pt","BR");

    	sccCurrencyFormat = NumberFormat.getInstance(locale);
    	sccCurrencyFormat.setMinimumFractionDigits(2);
    	sccCurrencyFormat.setMaximumFractionDigits(2);
    }
	
	private String descricao;
	
	private Double quantidadeChamandas;
	
	private Double quantidadeMinutos;
	
	private Double valorBruto;
	
	private Double icmsNaoRepassado;
	
	private Double icmsRepassar;
	
	private Double pisCofins;
	
	private Double iss;
	
	private Double valorLiquido;
	
	private Double valorRepassar;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getQuantidadeChamandas() {
		return quantidadeChamandas;
	}

	public void setQuantidadeChamandas(Double quantidadeChamandas) {
		this.quantidadeChamandas = quantidadeChamandas;
	}

	public Double getQuantidadeMinutos() {
		return quantidadeMinutos;
		
	}

	public void setQuantidadeMinutos(Double quantidadeMinutos) {
		this.quantidadeMinutos = quantidadeMinutos;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getIcmsNaoRepassado() {
		return icmsNaoRepassado;
	}

	public void setIcmsNaoRepassado(Double icmsNaoRepassado) {
		this.icmsNaoRepassado = icmsNaoRepassado;
	}

	public Double getPisCofins() {
		return pisCofins;
	}

	public void setPisCofins(Double pisCofins) {
		this.pisCofins = pisCofins;
	}

	public Double getIss() {
		return iss;
	}

	public void setIss(Double iss) {
		this.iss = iss;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Double getValorRepassar() {
		return valorRepassar;
	}

	public void setValorRepassar(Double valorRepassar) {
		this.valorRepassar = valorRepassar;
	}

	public Double getIcmsRepassar() {
		return icmsRepassar;
	}

	public void setIcmsRepassar(Double icmsRepassar) {
		this.icmsRepassar = icmsRepassar;
	}
	
	public String getCampoChamadas()
	{
		if (getQuantidadeChamandas() == null)
			return " ";
		if (getQuantidadeChamandas().equals(0.0))
			return " ";
		Integer qtdChamadas = getQuantidadeChamandas().intValue();
		return qtdChamadas.toString();
	}
	
	public String getCampoMinutos()
	{
		if (getQuantidadeMinutos() == null)
			return " ";
		if (getQuantidadeMinutos().equals(0.0))
			return " ";		
		return sccCurrencyFormat.format(getQuantidadeMinutos());
	}
	
	public String getCampoValorBruto()
	{
		if (valorBruto == null)
			return " ";
		if (valorBruto.equals(0.0))
			return " ";
		return sccCurrencyFormat.format(valorBruto);
	}
	
	public String getCampoIcmsNaoRepassado()
	{
		if (getIcmsNaoRepassado() == null)
			return " ";
		if (getIcmsNaoRepassado().equals(0.0))
			return " ";
		return sccCurrencyFormat.format(getIcmsNaoRepassado());
	}
	
	public String getCampoIcmsRepassar()
	{
		if (getIcmsRepassar() == null)
			return " ";
		if (getIcmsRepassar().equals(0.0))
			return " ";
		return sccCurrencyFormat.format(getIcmsRepassar());
	}
	
	public String getCampoPisCofins()
	{
		if (getPisCofins() == null)
			return " ";
		if (getPisCofins().equals(0.0))
			return " ";
		return sccCurrencyFormat.format(getPisCofins());
	}
	
	public String getCampoIss()
	{
		if (getIss() == null)
			return " ";
		if (getIss().equals(0.0))
			return " ";
		return sccCurrencyFormat.format(getIss());
	}
	
	public String getCampoValorLiquido()
	{
		if (getValorLiquido() == null)
			return " ";
		if (getValorLiquido().equals(0.0))
			return " ";
		return sccCurrencyFormat.format(getValorLiquido());
	}
	
	public String getCampoValorRepassar()
	{
		if (getValorRepassar() == null)
			return " ";
		if (getValorRepassar().equals(0.0))
			return " ";
		return sccCurrencyFormat.format(getValorRepassar());
	}
	
	 public static String getNumberFormat(Double numero, int casas){
	    	Locale locale = new Locale("pt","BR");
			NumberFormat nf = NumberFormat.getNumberInstance(locale);
			nf.setMaximumFractionDigits(casas);
	        nf.setMinimumFractionDigits(casas);	        
			return nf.format(numero);    	
	    }
	    
	
	
}
