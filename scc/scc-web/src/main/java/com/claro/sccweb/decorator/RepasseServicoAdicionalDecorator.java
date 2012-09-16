package com.claro.sccweb.decorator;

import java.text.DecimalFormat;

import com.claro.cobillingweb.persistence.entity.SccRepasseServicoAdicional;

public class RepasseServicoAdicionalDecorator {

	SccRepasseServicoAdicional entity;
	
	private String operadora;
	private String qtFaturasAssinadas;
	private String qtChamadas;
	private String minutagem;
	private String valorSemDesconto;
	private String desconto;
	private String valorFaturado;
	private String valorArrecadadoDesconto;
	private String valorArrecadadoInadimplente;
	private String contestado;
	private String reversao;
	private String custo;
	private String repasse;
	
	DecimalFormat decimalFormat = new DecimalFormat("#.##");
	
	public RepasseServicoAdicionalDecorator(SccRepasseServicoAdicional entity)
	{
		this.entity = entity;
	}

	public SccRepasseServicoAdicional getEntity() {
		return entity;
	}

	public void setEntity(SccRepasseServicoAdicional entity) {
		this.entity = entity;
	}

	public String getOperadora() {
		return getEntity().getOperadoraClaro().getDsOperadora();
	}

	

	public String getQtFaturasAssinadas() {
		return decimalFormat.format(getEntity().getQtAssinaturas());		
	}
	

	public String getQtChamadas() {
		if (getEntity().getQtCdrs() == null)
			return " ";
		return Long.toString(getEntity().getQtCdrs());		
	}

	
	public String getMinutagem() {
		if (getEntity().getQtDuracaoTarifada() == null)
			return " ";
		return decimalFormat.format(getEntity().getQtDuracaoTarifada());
	}

	
	public String getValorSemDesconto() {
		if (getEntity().getVlFaturado() == null)
			return " ";
		return decimalFormat.format(getEntity().getVlFaturado());
	}


	public String getDesconto() {
		if (getEntity().getVlDescontoConcedido() == null)
			return " ";
		return decimalFormat.format(getEntity().getVlDescontoConcedido());		
	}
	

	public String getValorFaturado() {
		Double valor = getEntity().getVlFaturado() - getEntity().getVlDescontoConcedido();
		return decimalFormat.format(valor);
	}


	public String getValorArrecadadoDesconto() {
		if (getEntity().getVlArrecadado() == null)
			return " ";
		return decimalFormat.format(getEntity().getVlArrecadado());	
	}

	

	public String getValorArrecadadoInadimplente() {
		if (getEntity().getVlArrInadimplente() == null)
			return " ";
		return decimalFormat.format(getEntity().getVlArrInadimplente());	
	}

	

	public String getContestado() {
		if (getEntity().getVlContestado() == null)
			return " ";
		return decimalFormat.format(getEntity().getVlContestado());	
	}

	

	public String getReversao() {
		if (getEntity().getVlReversao() == null)
			return " ";
		return decimalFormat.format(getEntity().getVlReversao());	
	}

	

	public String getCusto() {
		if (getEntity().getVlServPrestado() == null)
			return " ";
		return decimalFormat.format(getEntity().getVlServPrestado());
	}

	
	public String getRepasse() {		
		Double valor = getEntity().getVlBrutoRepasse() - getEntity().getVlContestado() - getEntity().getVlReversao();		
		return decimalFormat.format(valor);
	}

	
	
}
