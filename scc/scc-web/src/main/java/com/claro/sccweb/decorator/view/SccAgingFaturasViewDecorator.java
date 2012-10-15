package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccAgingFaturasView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccAgingFaturasViewDecorator extends RownumDecorator<SccAgingFaturasView> {

	public SccAgingFaturasViewDecorator(SccAgingFaturasView entity, int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		
		return false;
	}
	
	public String getOperadoraClaro(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getOperadoraClaro())){
			value = getRow().getOperadoraClaro();
		}
		return value;
	}
	
	
	public String getOperadoraLd(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getOperadoraLD())){
			value = getRow().getOperadoraLD();
		}
		return value;
	}
	
	
	public String getVencer(){
		String value = "";
		if(getRow().getVencer() != null){
			value = formataDouble(getRow().getVencer());
		}
		
		return value;
	}
	
	public String getValor1a10Dias(){
		String value = "";
		if(getRow().getValor1a10Dias() != null){
			value = formataDouble(getRow().getValor1a10Dias());
		}
		
		return value;
		
	}
	
	public String getValor11a20Dias(){
		String value = "";
		if(getRow().getValor11a20Dias() != null){
			value = formataDouble(getRow().getValor11a20Dias());
		}
		
		return value;
		
	}
	
	public String getValor21a30Dias(){
		String value = "";
		if(getRow().getValor21a30Dias() != null){
			value = formataDouble(getRow().getValor21a30Dias());
		}
		
		return value;
		
	}
	
	public String getValor31a60Dias(){
		String value = "";
		if(getRow().getValor31a60Dias() != null){
			value = formataDouble(getRow().getValor31a60Dias());
		}
		
		return value;
		
	}
	
	public String getValor61a90Dias(){
		String value = "";
		if(getRow().getValor61a90Dias() != null){
			value = formataDouble(getRow().getValor61a90Dias());
		}
		
		return value;
		
	}
	
	public String getMaior90Dias(){
		String value = "";
		if(getRow().getMaior90Dias() != null){
			value = formataDouble(getRow().getMaior90Dias());
		}
		
		return value;
		
	}
	
	

}
