package com.claro.sccweb.service.data.saldos;

import com.claro.sccweb.decorator.BasicSccDecorator;

/**
 * Tabela final com o demonstrativo de saldos e juros;
 *
 */
public class TabelaDemonstrativoSaldo extends BasicSccDecorator {

	private DemonstrativoSaldoEvento itemEvento;
	private DemonstrativoSaldoRejeicao itemRejeicao;
	
	
	
	
	
	public TabelaDemonstrativoSaldo(DemonstrativoSaldoEvento itemEvento)
	{
		this.itemEvento = itemEvento;
	}
	
	public TabelaDemonstrativoSaldo(DemonstrativoSaldoRejeicao itemRejeicao)
	{
		this.itemRejeicao = itemRejeicao;
	}
	
	
	public String getEvento() {
		if (itemEvento != null)
			return formataString(itemEvento.getDsMotivoEvento());
		return "";
	}

	public String getCodigo() {
		if (itemRejeicao != null)
			return formataString(itemRejeicao.getCdMotivoRejeicao());
		return "";
	}

	public String getMotivo() {
		if (itemRejeicao != null)
			return formataString(itemRejeicao.getDsMotivoRejeicao());
		return "";
	}

	public String getQtCdrs() {
		if (itemEvento != null)
			return formataLong(itemEvento.getQtCdrs());
		else if(itemRejeicao != null)	 
			return formataLong(itemRejeicao.getQtCdrs());
		return " ";
	}

	public String getPerCdrs() {
		if (itemEvento != null)
			return formataDouble(itemEvento.getPercentualCDRs());
		else if(itemRejeicao != null)
			return formataDouble(itemRejeicao.getPercentualCDRs());
		return " ";
	}

	public String getQtMinutos() {
		if (itemEvento != null)
			return formataDouble(itemEvento.getQtMinutos());
		else if(itemRejeicao != null)			
			return formataDouble(itemRejeicao.getQtMinutos());
		return " ";
	}

	public String getPerMinutos() {
		if (itemEvento != null)
			return formataDouble(itemEvento.getPercentualMinutos());
		else if(itemRejeicao != null)	
			return formataDouble(itemRejeicao.getPercentualMinutos());
		return " ";
	}

	public String getValor() {
		if (itemEvento != null)
			return formataDouble(itemEvento.getValor());
		else if(itemRejeicao != null)	
			return formataDouble(itemRejeicao.getValor());
		return " ";
	}

	public String getPerValor() {
		if (itemEvento != null)
			return formataDouble(itemEvento.getPercentualValor());
		else if(itemRejeicao != null)
			return formataDouble(itemRejeicao.getPercentualValor());
		return " ";
	}
	
	
	
	
	
}
