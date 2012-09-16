package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.view.RelDetalheCreditoPrePagoView;


public class RelDetalheCreditoPrePagoDecorator extends BasicSccDecorator {

	
	public String getNomeArquivo() {
		return getRow().getNomeArquivo();
	}
	public String getNumeroOrigem() {
		if (getRow().getSeqCredito() == null)
			{
			return " ";
			}
		else return getRow().getSeqCredito().toString();
	}
	
	
	public String getNumeroDestino() {
		return getRow().getNumeroDestino();
	}
	public String getDataChamada() {
		if (getRow().getDataChamada() == null)
			{
			return "--/--/----";
			}			
		else 
			{
			return dateFormat.format(getRow().getDataChamada());
			}
	}
	
	
	public String getHoraInicioChamada() {
		String horaInicioChamada;
		if (getRow().getHoraInicioChamada().toString().length() == 5)
			{
			horaInicioChamada =  "0"+getRow().getHoraInicioChamada().toString();
			}			
		else if (getRow().getHoraInicioChamada().toString().length() == 4)
			{
			horaInicioChamada =  "00"+getRow().getHoraInicioChamada().toString();
			}			
		else
			{
			horaInicioChamada =  getRow().getHoraInicioChamada().toString();
			}
		return horaInicioChamada;
	}
	
	
	public String getNuCDR() {
		return getRow().getNuCDR().toString();
	}
	public String getSeqCredito() {
		if (getRow().getSeqCredito() == null)
			{
			return " ";
			}			
		else
			{
			return getRow().getSeqCredito().toString();	
			}
	}
	
	public String getDataCredito() {
		if (getRow().getDataCredito() == null)
			{
			return "--/--/----";
			}
		else
			{
			return dateFormat.format(getRow().getDataCredito());
			}		
	}
	public String getValorCredito() {
		if (getRow().getValorCredito() == null)
			{
			return " ";
			}
		else
			{
			return sccCurrencyFormat.format(getRow().getValorCredito());
			}		
	}
	public String getDuracaoTarifada() {
		if (getRow().getDuracaoTarifada() == null)
			{
			return " ";
			}
	  else
			{
			return sccCurrencyFormat.format(getRow().getDuracaoTarifada());
			}	
	}
	public String getValorBruto() {
		if (getRow().getValorBruto() == null)
			{
			return " ";
			}
       else
			{
			return sccCurrencyFormat.format(getRow().getValorBruto());
			}	
	}
	
	public RelDetalheCreditoPrePagoView getRow()
	{
		return (RelDetalheCreditoPrePagoView)getCurrentRowObject();
	}
	
	
	
	
}
