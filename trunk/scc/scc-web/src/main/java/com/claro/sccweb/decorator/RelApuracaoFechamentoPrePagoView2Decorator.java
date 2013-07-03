package com.claro.sccweb.decorator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;
import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.view.RelApuracaoFechamentoPrePagoView;

public class RelApuracaoFechamentoPrePagoView2Decorator extends TableDecorator {
	
	protected static NumberFormat decimalFormat = new DecimalFormat("#.##");
	
	private Double totalRepassar;
	
	
	
	public RelApuracaoFechamentoPrePagoView getEntity(){
		
		RelApuracaoFechamentoPrePagoView entity = (RelApuracaoFechamentoPrePagoView) getCurrentRowObject();
		return entity;
	}
	
	
	public Double getValorLiquidoApurado(){
		
		Double vlrLiquidoApurado =  zeroIfNull(getEntity().getValor11())+zeroIfNull(getEntity().getValor19())-(zeroIfNull(getEntity().getValor15())
						+zeroIfNull(getEntity().getValor16())+zeroIfNull(getEntity().getValor23())+zeroIfNull(getEntity().getValor24()))
				-(zeroIfNull(getEntity().getValor14())+zeroIfNull(getEntity().getValor22()));
				
		return vlrLiquidoApurado;
		
	}
	
	public Double getValorPisCofins(){
		
		Double valorPisCofins = zeroIfNull(getEntity().getValor15())+zeroIfNull(getEntity().getValor16())+zeroIfNull(getEntity().getValor23())+zeroIfNull(getEntity().getValor24());
		return valorPisCofins;
	}
	
	public Double getValorServicePrestadoBruto(){
		
		Double valorServicePrestadoBruto = zeroIfNull(getEntity().getValor25());
		return valorServicePrestadoBruto;
	}
	
	public Double getValorCreditosAutorizados(){
	
		Double valorCreditosAutorizados = zeroIfNull(getEntity().getValor29());
		return valorCreditosAutorizados;
	}
	
	public Double getValorCreditos226(){
		
		Double valorCreditos226 = null;
		if (isFlagTrue(getEntity().getRepassaICMS())){
			valorCreditos226 = zeroIfNull(getEntity().getValor32());
		}
		else{
			valorCreditos226 = zeroIfNull(getEntity().getValor32())-zeroIfNull(getEntity().getValor44());
		}

		return valorCreditos226;
		
	}
	
	public Double getValorPenalidadeMinutosPerdidos(){
		
		Double valorPenalidadeMinutosPerdidos = zeroIfNull(getEntity().getValor33()); 
		return valorPenalidadeMinutosPerdidos;
	}
	
	public Double getValorMultas(){
		Double valorMultas = zeroIfNull(getEntity().getValor35())+zeroIfNull(getEntity().getValor34());
		return valorMultas;
	}
	
	public Double getValorAcertos(){
	
		Double valorAcertos = zeroIfNull(getEntity().getValor37())+zeroIfNull(getEntity().getValor36());
		return valorAcertos;
	}
	

	public String getOperadoraHolding(){
		
		String value = "";
		if(StringUtils.isNotEmpty(getEntity().getCdEotHolding())){
			value = getEntity().getCdEotHolding();
		}
		return value;
	}
	
	public String getOperadoraClaro() {
		
		if(StringUtils.isEmpty(getEntity().getUfClaro())){
			return getEntity().getDsOperadora();
		}
		return getEntity().getUfClaro()+" "+getEntity().getDsOperadora();
	}

	public String getValorApuradoLiquido() {		
		return decimalFormat.format(getValorLiquidoApurado());
	}
	

	public String getPisCofins() {		
		return decimalFormat.format(getValorPisCofins());
	}
	
	public Double getValorIcmsRepassarDouble(){
		
		Double valorIcmsRepassar = zeroIfNull(getEntity().getValor14())+zeroIfNull(getEntity().getValor22());
		return valorIcmsRepassar;
	}
	
	public Double getValorIcmsRepassar2(){
		Double valorIcmsRepassar = null;
		if (isFlagTrue(getEntity().getRepassaICMS()))
			{
			valorIcmsRepassar = zeroIfNull(getEntity().getValor14())+zeroIfNull(getEntity().getValor22());
			}
		return valorIcmsRepassar;
		
	}
	
	public String getValorIcmsRepassar() {	
		Double valorIcmsRepassar = null;
		if (isFlagTrue(getEntity().getRepassaICMS()))
			{
			valorIcmsRepassar = zeroIfNull(getEntity().getValor14())+zeroIfNull(getEntity().getValor22());
			}
		return decimalFormat.format(valorIcmsRepassar);
	}
	
	public String getValorIcmsNaoRepassado() {
		Double valor = 0.0;
		if (!isFlagTrue(getEntity().getRepassaICMS()))	{
			valor = zeroIfNull(getEntity().getValor14())+zeroIfNull(getEntity().getValor22());
		}
		 return decimalFormat.format(valor);
	}
	
	public String getValorRepassar() {	
		String value = "";
		
		if(StringUtils.isNotEmpty(decimalFormat.format(getValorLiquidoApurado() + getValorPisCofins() + getValorIcmsRepassarDouble()))){
			value = decimalFormat.format(getValorLiquidoApurado() + getValorPisCofins() + getValorIcmsRepassarDouble());
		}
		
		return value;
	}

	public String getValorNotaFiscal() {		
		return decimalFormat.format((getValorLiquidoApurado() + getValorPisCofins() + getValorIcmsRepassarDouble()) - (getValorCreditos226() -getValorAcertos()));
	}


	public String getDestaqueIcms() {
		Double valorRepassar = (getValorLiquidoApurado() + getValorPisCofins() +  getValorIcmsRepassarDouble());
		if (valorRepassar == 0.0) {
			return "0.00";
		}
		Double valor = 0.0;
		if (isFlagTrue(getEntity().getRepassaICMS()))
			{
			valor = zeroIfNull(getEntity().getValor14())+zeroIfNull(getEntity().getValor22());
			}
		return decimalFormat.format(valor - (getValorCreditos226() * (valor/valorRepassar)));
	}
	
	public String getServicoPrestadoLiquido() {
		Double valor = 0.0;
		valor = zeroIfNull(getEntity().getValor25()) -(zeroIfNull(getEntity().getValor26())+zeroIfNull(getEntity().getValor27())+zeroIfNull(getEntity().getValor28()));
		return decimalFormat.format(valor);
	}
	
	public String getPisCofinsServicePrestado() {
		Double valor = zeroIfNull(getEntity().getValor26())+zeroIfNull(getEntity().getValor27());
		return decimalFormat.format(valor);
	}
	
	public String getIss() {
		return decimalFormat.format(zeroIfNull(getEntity().getValor28()));
	}

	
	public String getValorBrutoServico() {
		
		return decimalFormat.format(getValorServicePrestadoBruto());
	}
	
	public String getCreditosAutorizados() {
		return decimalFormat.format(getValorCreditosAutorizados());		
	}
	
	public String getCreditos226() {		
		return decimalFormat.format(getValorCreditos226());
	}
	
	public String getPenalidadesMinutosPerdidos() {		
		return decimalFormat.format(getValorPenalidadeMinutosPerdidos());
	}
	
	public String getTotalMultasJuros() {		
		return decimalFormat.format(getValorMultas());
	}

	public String getTotalAcertosConciliacoes() {		
		return decimalFormat.format(getValorAcertos());
	}
	
	public String getCpmfDescontar() {
		return " ";
	}

	public String getIcmsDescontar() {
		return " ";
	}

	public String getIcmsRepassar() {
		Double valor = 0.0;
		if (isFlagTrue(getEntity().getRepassaICMS())){
			valor = zeroIfNull(getEntity().getValor14())+zeroIfNull(getEntity().getValor22());
		}
		return decimalFormat.format(valor);
	}
	
	public String getValorFinalRepassar() {
		return decimalFormat.format(getValorLiquidoApurado() +getValorPisCofins() - getValorServicePrestadoBruto() - getValorCreditosAutorizados() -getValorCreditos226() + getValorPenalidadeMinutosPerdidos() +
				getValorMultas() + getValorAcertos() + getValorIcmsRepassarDouble());
	}
	
	
	public String getUf(){
		String value ="";
		if(StringUtils.isNotEmpty(getEntity().getUfClaro())){
			value = getEntity().getUfClaro();
		}
		return value;
	}
	

	
	protected boolean isFlagTrue(String value) {
		return ((value != null) && (value.equalsIgnoreCase("S")));
	}

	
	protected Double zeroIfNull(Double value) {
		if (value == null) {
			return 0.0;
		}
		return value;
	}

	public Double getTotalRepassar() {
		return totalRepassar;
	}

	public void setTotalRepassar(Double totalRepassar) {
		this.totalRepassar = totalRepassar;
	}

	

}
