package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccAcordoParcelamentoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccAcordoParcelamentoViewDecorator extends RownumDecorator<SccAcordoParcelamentoView> {
	
	private double totalAcordado = 0.0;

	public SccAcordoParcelamentoViewDecorator(SccAcordoParcelamentoView entity,	int rownum) {
		super(entity, rownum);
		
		totalAcordado = totalAcordado + entity.getValorAcordado();
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		
		return false;
	}

	public String getOperadoraLD() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getOperadoraLD())){
			value = getRow().getOperadoraLD();
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

	public String getCodAcordo() {
		String value = "";
		if(getRow().getCodAcordo() != null){
			value = formataDouble2(getRow().getCodAcordo());
		}
		return value;
	}

	public String getDataAcordo() {
		String value = "";
		if(getRow().getDataAcordo() != null){
			value = formataDate(getRow().getDataAcordo());
		}
		return value;
	}

	public String getValorAcordado() {
		String value = "";
		if(getRow().getValorAcordado() != null){
			value = formataDouble(getRow().getValorAcordado());
		}
		return value;
	}

	public String  getNumAcordoParcelamento() {
		String value = "";
		if(getRow().getNuAcordoParcelamento() != null){
			value = getRow().getNuAcordoParcelamento().toString();
		}
		return value;
	}
	
	public String getNumFatura() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getNuFatura())){
			value = getRow().getNuFatura();
		}
		return value;
	}

	public String getNumConta() {
		String value = "";
		if(getRow().getNumConta() != null){
			value = getRow().getNumConta().toString();
		}
		return value;
	}

	public String getVlParcelaOperadora() {
		String value = "";
		if(getRow().getVlParcelaOperadora() != null){
			value = formataDouble(getRow().getVlParcelaOperadora());
		}
		return value;
	}

	public String getStatus() {
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getStatus())){
			value = getRow().getStatus();
		}
		return value;
	}

	public String getQtdParcelas() {
		if(getRow().getQtdParcelas() != null)
			return getRow().getQtdParcelas().toString();
		return " ";
	}
	
	public String getNumeroParcela(){
		String value = "";
		if(getRow().getNuParcela() != null){
			value = getRow().getNuParcela().toString();
		}
		return value;
	}
	
	public String getTotalAcordado(){
		
		Double total = totalAcordado;
		return total.toString();
		
	}

	public void setTotalAcordado(double totalAcordado) {
		this.totalAcordado = totalAcordado;
	}
	
	
	
	
}
