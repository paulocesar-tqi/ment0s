package com.claro.sccweb.decorator;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.RelApuracaoFechamentoPrePagoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class RelApuracaoFechamentoPrePagoViewDecorator extends RownumDecorator<RelApuracaoFechamentoPrePagoView> {

	public RelApuracaoFechamentoPrePagoViewDecorator(RelApuracaoFechamentoPrePagoView entity, int rownum) {
		super(entity, rownum);
		valorLiquidoApurado = zeroIfNull(getRow().getValor11())+zeroIfNull(getRow().getValor19())-(zeroIfNull(getRow().getValor15())
				+zeroIfNull(getRow().getValor16())+zeroIfNull(getRow().getValor23())+zeroIfNull(getRow().getValor24()))
		-(zeroIfNull(getRow().getValor14())+zeroIfNull(getRow().getValor22()));
		
		valorPisCofins = zeroIfNull(getRow().getValor15())+zeroIfNull(getRow().getValor16())+zeroIfNull(getRow().getValor23())+zeroIfNull(getRow().getValor24());
		
		valorServicePrestadoBruto = zeroIfNull(getRow().getValor25());
		
		valorCreditosAutorizados = zeroIfNull(getRow().getValor29());
		
		if (isFlagTrue(getRow().getRepassaICMS()))
			{
			valorCreditos226 = zeroIfNull(getRow().getValor32());
			}
		else
			{
			valorCreditos226 = zeroIfNull(getRow().getValor32())-zeroIfNull(getRow().getValor44());
			}
		
		valorPenalidadeMinutosPerdidos = zeroIfNull(getRow().getValor33());
		valorMultas = zeroIfNull(getRow().getValor35())+zeroIfNull(getRow().getValor34());
		valorAcertos = zeroIfNull(getRow().getValor37())+zeroIfNull(getRow().getValor36());

	}

	private Double valorLiquidoApurado = 0.0;
	
	private Double valorPisCofins = 0.0;
	
	private Double valorIcmsRepassar = 0.0;
	
	private Double valorServicePrestadoBruto = 0.0;
	
	private Double valorCreditosAutorizados = 0.0;
	
	private Double valorCreditos226 = 0.0;
	
	private Double valorPenalidadeMinutosPerdidos = 0.0;
	
	private Double valorMultas = 0.0;
	
	private Double valorAcertos = 0.0;
	
	
	/*
	public RelApuracaoFechamentoPrePagoViewDecorator()
	{
		valorLiquidoApurado = zeroIfNull(getRow().getValor11())+zeroIfNull(getRow().getValor19())-(zeroIfNull(getRow().getValor15())
				+zeroIfNull(getRow().getValor16())+zeroIfNull(getRow().getValor23())+zeroIfNull(getRow().getValor24()))
		-(zeroIfNull(getRow().getValor14())+zeroIfNull(getRow().getValor22()));
		
		valorPisCofins = zeroIfNull(getRow().getValor15())+zeroIfNull(getRow().getValor16())+zeroIfNull(getRow().getValor23())+zeroIfNull(getRow().getValor24());
		
		valorServicePrestadoBruto = zeroIfNull(getRow().getValor25());
		
		valorCreditosAutorizados = zeroIfNull(getRow().getValor29());
		
		if (isFlagTrue(getRow().getRepassaICMS()))
			{
			valorCreditos226 = zeroIfNull(getRow().getValor32());
			}
		else
			{
			valorCreditos226 = zeroIfNull(getRow().getValor32())-zeroIfNull(getRow().getValor44());
			}
		
		valorPenalidadeMinutosPerdidos = zeroIfNull(getRow().getValor33());
		valorMultas = zeroIfNull(getRow().getValor35())+zeroIfNull(getRow().getValor34());
		valorAcertos = zeroIfNull(getRow().getValor37())+zeroIfNull(getRow().getValor36());
		
	}
	*/
	
	


	public String getOperadoraClaro() {
		return getRow().getUfClaro()+" "+getRow().getDsOperadora();
	}

	public String getValorApuradoLiquido() {		
		return decimalFormat.format(valorLiquidoApurado);
	}

	public String getPisCofins() {		
		return decimalFormat.format(valorPisCofins);
	}

	public String getValorIcmsRepassar() {		
		if (isFlagTrue(getRow().getRepassaICMS()))
			{
			valorIcmsRepassar = zeroIfNull(getRow().getValor14())+zeroIfNull(getRow().getValor22());
			}
		return decimalFormat.format(valorIcmsRepassar);
	}

	public String getValorIcmsNaoRepassado() {
		Double valor = 0.0;
		if (!isFlagTrue(getRow().getRepassaICMS()))
			{
			valor = zeroIfNull(getRow().getValor14())+zeroIfNull(getRow().getValor22());
			}
		 return decimalFormat.format(valor);
	}

	public String getValorRepassar() {		
		return decimalFormat.format(valorLiquidoApurado+valorPisCofins+valorIcmsRepassar);
	}

	public String getValorNotaFiscal() {		
		return decimalFormat.format((valorLiquidoApurado+valorPisCofins+valorIcmsRepassar)-(valorCreditos226-valorAcertos));
	}
	
	public String getDestaqueIcms() {
		Double valorRepassar = (valorLiquidoApurado+valorPisCofins+valorIcmsRepassar);
		if (valorRepassar == 0.0) {
			return "0.00";
		}
		Double valor = 0.0;
		if (isFlagTrue(getRow().getRepassaICMS()))
			{
			valor = zeroIfNull(getRow().getValor14())+zeroIfNull(getRow().getValor22());
			}
		return decimalFormat.format(valor - (valorCreditos226 * (valor/valorRepassar)));
	}
	
	public String getServicoPrestadoLiquido() {
		Double valor = 0.0;
		valor = zeroIfNull(getRow().getValor25()) -(zeroIfNull(getRow().getValor26())+zeroIfNull(getRow().getValor27())+zeroIfNull(getRow().getValor28()));
		return decimalFormat.format(valor);
	}

	public String getPisCofinsServicePrestado() {
		Double valor = zeroIfNull(getRow().getValor26())+zeroIfNull(getRow().getValor27());
		return decimalFormat.format(valor);
	}

	public String getIss() {
		return decimalFormat.format(zeroIfNull(getRow().getValor28()));
	}

	public String getValorBrutoServico() {
		
		return decimalFormat.format(valorServicePrestadoBruto);
	}

	public String getCreditosAutorizados() {
		return decimalFormat.format(valorCreditosAutorizados);		
	}

	public String getCreditos226() {		
		return decimalFormat.format(valorCreditos226);
	}

	public String getPenalidadesMinutosPerdidos() {		
		return decimalFormat.format(valorPenalidadeMinutosPerdidos);
	}

	public String getTotalMultasJuros() {		
		return decimalFormat.format(valorMultas);
	}

	public String getTotalAcertosConciliacoes() {		
		return decimalFormat.format(valorAcertos);
	}

	public String getCpmfDescontar() {
		return " ";
	}

	public String getIcmsDescontar() {
		return " ";
	}

	public String getIcmsRepassar() {
		Double valor = 0.0;
		if (isFlagTrue(getRow().getRepassaICMS()))
			{
			valor = zeroIfNull(getRow().getValor14())+zeroIfNull(getRow().getValor22());
			}
		return decimalFormat.format(valor);
	}

	public String getValorFinalRepassar() {
		return decimalFormat.format(valorLiquidoApurado+valorPisCofins-valorServicePrestadoBruto-valorCreditosAutorizados-valorCreditos226+valorPenalidadeMinutosPerdidos+
				valorMultas+valorAcertos+valorIcmsRepassar);
	}

	public String getUf(){
		String value ="";
		if(StringUtils.isNotEmpty(getRow().getUfClaro())){
			value = getRow().getUfClaro();
		}
		return value;
	}
	
	 
	protected boolean isDeleteEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
