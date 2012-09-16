package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.view.RelCreditosPrePagoView;

public class RelCreditosPrePagoDecorator extends BasicSccDecorator {
	
	
	public String getNomeArquivo() {
		return getLink(getRow().getNoArquivo());
	}


	public String getOperadoraLD() {
		return getLink(getRow().getCdEOTLD());
	}


	public String getOperadoraClaro() {
		return getLink(getRow().getCdEOTClaro());
	}


	public String getTipoCredito() {
		return getLink(getRow().getDsCredito());
	}


	public String getStatusCredito() {
		return getLink(getRow().getDsStatus());
	}


	public String getTelefone() {
		return getLink(getRow().getTelefone());
	}



	public String getDataCerdito() {		
		return getLink(dateFormat.format(getRow().getDtCredito()));
	}


	public String getValorCredito() {
		if (getRow().getVlrCredito() == null)
			return " ";
		else return getLink(sccCurrencyFormat.format(getRow().getVlrCredito()));
	}


	public String getQtChamadasAgrupadas() {
		if (getRow().getQuantidade() == null)
				return " ";
		else return getLink(sccCurrencyFormat.format(getRow().getQuantidade()));
	}


	public String getMinutosQueimados() {
		if (getRow().getMinutosQueimados() == null)
			return " ";
		else return getLink(sccCurrencyFormat.format(getRow().getMinutosQueimados()));	
	}


	public String getMinutosAjustados() {
		if (getRow().getMinutosAjustados() == null)
			return " ";
		else return getLink(sccCurrencyFormat.format(getRow().getMinutosAjustados()));	
	}

	private String getLink(String valor)
	{
		return "<a href='#' onClick=selecionaDetalhe("+getRow().getSqArquivo()+","+getRow().getSqCredito()+")>"+valor+"</a>";		
	}

	private RelCreditosPrePagoView getRow()
	{		
		return (RelCreditosPrePagoView)getCurrentRowObject();
	}
	
}
