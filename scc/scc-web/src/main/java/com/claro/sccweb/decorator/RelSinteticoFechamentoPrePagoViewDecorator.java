package com.claro.sccweb.decorator;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.RelSinteticoFechamentoPrePagoView;

public class RelSinteticoFechamentoPrePagoViewDecorator extends BasicSccDecorator {

	
	private RelSinteticoFechamentoPrePagoView getRow(){
		return (RelSinteticoFechamentoPrePagoView)getCurrentRowObject();
	}
	
	public String getMinutos() {
		if (getRow().getMinutos() == null)
			return "0,0";
		else
			return decimalFormat.format(getRow().getMinutos());
	}
	

	/*
	 * item.setVlLiquido(item.getVlBruto()-item.getVlIcmsADescontar()-item.
	 * getVlIcmsRepassar()-item.getVlPis()-item.getVlCofins());
	 */
	public String getValorLiquido() {
		
		
		String value = decimalFormat.format(zeroIfNull(getRow().getValorBruto())
							- zeroIfNull(getRow().getIcmsRepassar())
				- zeroIfNull(getRow().getValorPis())
				- zeroIfNull(getRow().getValorCofins())
				- zeroIfNull(getRow().getIcmsNaoRepassado()));
				
		return value;
	}

	public String getPisCofins() {
		return decimalFormat.format(zeroIfNull(getRow().getValorPis())
				+ zeroIfNull(getRow().getValorCofins()));
	}

	public String getIcmsRepassar() {
		return decimalFormat.format(zeroIfNull(getRow().getIcmsRepassar()));
	}

	public String getValorRepassar() {
		return decimalFormat.format(zeroIfNull(getRow().getValorRepassar()));
	}

	public String getIcmsNaoRepassado() {
		return decimalFormat.format(zeroIfNull(getRow().getIcmsNaoRepassado()));
	}

	public String getValorBruto() {
		return decimalFormat.format(getRow().getValorBruto());
	}

	
	public String getCdEOTHolding() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdEOTHolding())){
			value = getRow().getCdEOTHolding();
		}
		return value;
	}

	public String getCdEOTClaro() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdEOTClaro())){
			value = getRow().getCdEOTClaro();
		}
		return value;

	}

	public String getOperadoraClaro() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getOperadoraClaro())){
			value = getRow().getOperadoraClaro();
		}
		return value;

	}

	public String getCdEOTLD() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdEOTLD())){
			value = getRow().getCdEOTLD();
		}
		return value;
	}

	public String getUf() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getUf())){
			value = getRow().getUf();
		}
		return value;

	}

	public String getTrafego() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getTrafego())){
			value = getRow().getTrafego();
		}
		return value;
	}

	public String getCnAssinante() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCnAssinante())){
			value = getRow().getCnAssinante();
		}
		return value;
	}

	public String getCnOrigem() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCnOrigem())){
			value = getRow().getCnOrigem();
		}
		return value;
	}

	public String getCdEotOrigem() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdEotOrigem())){
			value = getRow().getCdEotOrigem();
		}
		return value;
	}

	public String getTipo() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getTipo())){
			value = getRow().getTipo();
		}
		return value;
	}

	public String getPeriodo() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getPeriodo())){
			value = getRow().getPeriodo();
		}
		return value;
	}

	public String getChamadas() {
		String value = "";
		if(getRow().getChamadas() != null){
			value = formataLong(getRow().getChamadas());
		}
		return value;
	}

	public String getValorPis() {
		String value = "";
		if(getRow().getValorPis() != null){
			value = formataDouble(getRow().getValorPis());
		}
		return value;
	}

	public String getValorCofins() {
		String value = "";
		if(getRow().getValorCofins() != null){
			value = formataDouble(getRow().getValorCofins());
		}
		return value;
	}
	
	public String getValorLiquido2(){
		String value = "";
		if(getRow().getValorLiquido() != null){
			value = formataDouble(getRow().getValorLiquido());
		}
		return value;
	}
	

}
