package com.claro.sccweb.decorator;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.claro.cobillingweb.persistence.entity.SccRelatorioJurosMulta;

public class RelatorioJurosMultaDecorator {

	private DecimalFormat sccCurrencyFormat = new DecimalFormat("#.##");
	public SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private SccRelatorioJurosMulta row;
	
	public RelatorioJurosMultaDecorator(SccRelatorioJurosMulta row)
	{
		this.row = row;
	}
	
	/**
	 * Item de juro/multa foi selecionado pelo usuário.
	 */
	private boolean selecionado;
	
	public String getCSP()
	{
		return getRow().getOperadoraLD().getCdCsp();
	}
	
	
	public String getOperadoraClaro()
	{
		return getRow().getOperadoraClaro().getDsOperadora();
	}
	
	public String getUF()
	{
		return getRow().getSgUf();
	}
	
	public String getFatura()
	{
		return getRow().getNuFatura();
	}
	
	
	public String getDataArrecadacao()
	{
		return dateFormat.format(getRow().getDtArrecadacao());
	}
	
	public String getDataVencimento()
	{
		return dateFormat.format(getRow().getDtVencimento());
	}
	
	public String getValorJuros()
	{
		return sccCurrencyFormat.format(getRow().getVlJuros());
	}
	
	public String getValorMulta()
	{
		return sccCurrencyFormat.format(getRow().getVlMultas());
	}
	
	public String getTotal()
	{
		return sccCurrencyFormat.format(getRow().getVlJuros()+getRow().getVlMultas());
	}
	
	public String getDsName()
	{
		return getRow().getNoArquivo();
	}
	
	
	public String getLink()
	{
		StringBuffer bf = new StringBuffer("<input type='checkbox' value="+getRowNum()+" ");
		if (isSelecionado())
			bf.append("checked");
		bf.append("/>");
		return bf.toString();
	}
	
	public int getRowNum()
	{
		return getRow().getRowNum();
	}
	
	public boolean isSelecionado() {
		return selecionado;
	}


	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}


	private SccRelatorioJurosMulta getRow()
	{
		return row;
	}
	
	
}
