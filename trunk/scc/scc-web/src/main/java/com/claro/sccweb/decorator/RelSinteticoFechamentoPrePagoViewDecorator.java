package com.claro.sccweb.decorator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;

public class RelSinteticoFechamentoPrePagoViewDecorator extends BasicSccDecorator {


	
	
	private RelSinteticoFechamentoPrePagoView getRow()
	{
		return (RelSinteticoFechamentoPrePagoView)getCurrentRowObject();
	}
	
	public String getMinutos()
	{
		if (getRow().getMinutos() == null)
			return "0,0";
		else
			return decimalFormat.format(getRow().getMinutos());
	}
	
	/*item.setVlLiquido(item.getVlBruto()-item.getVlIcmsADescontar()-item.getVlIcmsRepassar()-item.getVlPis()-item.getVlCofins());*/
	public String getValorLiquido()
	{
		return decimalFormat.format(zeroIfNull(getRow().getValorBruto())-zeroIfNull(getRow().getIcmsRepassar())-zeroIfNull(getRow().getValorPis())-zeroIfNull(getRow().getValorCofins())-zeroIfNull(getRow().getIcmsNaoRepassado()));
	}
	
	public String getPisCofins()
	{
		return decimalFormat.format(zeroIfNull(getRow().getValorPis())+zeroIfNull(getRow().getValorCofins()));
	}
	
	public String getIcmsRepassar()
	{
		return decimalFormat.format(zeroIfNull(getRow().getIcmsRepassar()));
	}
	
	public String getValorRepassar()
	{
		return decimalFormat.format(zeroIfNull(getRow().getValorRepassar()));
	}
	
	public String getIcmsNaoRepassado()
	{
		return decimalFormat.format(zeroIfNull(getRow().getIcmsNaoRepassado()));
	}
		
	
	public String getValorBruto()
	{
		return decimalFormat.format(getRow().getValorBruto());
	}
	
}
