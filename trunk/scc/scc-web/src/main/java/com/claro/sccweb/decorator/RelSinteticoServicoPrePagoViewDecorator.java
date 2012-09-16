package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.view.RelSinteticoServicoPrePagoView;

public class RelSinteticoServicoPrePagoViewDecorator extends BasicSccDecorator {

	
	public String getChamadas() {
		if (getRow().getQtCDR() == null)
			return " ";
		if (getRow().getQtCDROM() == null) 
			return getRow().getQtCDR().toString();
		Long total = getRow().getQtCDR()+getRow().getQtCDROM();
		return total.toString();
	}



	public String getValorLiquido() {		
		return formataDouble(zeroIfNull(getRow().getValorBruto())-zeroIfNull(getRow().getValorISS())-zeroIfNull(getRow().getValorCofins())-zeroIfNull(getRow().getValorPis()));
	}



	public String getPisCofins() {
		return formataDouble(zeroIfNull(getRow().getValorPis())+zeroIfNull(getRow().getValorCofins()));
	}



	public String getIss() {
		return formataDouble(zeroIfNull(getRow().getValorISS()));
	}



	public String getValorBruto() {
		return formataDouble(zeroIfNull(getRow().getValorBruto()));
	}



	private RelSinteticoServicoPrePagoView getRow()
	{
		return (RelSinteticoServicoPrePagoView)getCurrentRowObject();
	}
	
}
