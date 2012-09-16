package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccMapaStatus;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccMapaStatusDecorator extends RownumDecorator<SccMapaStatus> {

	
	
	public SccMapaStatusDecorator(SccMapaStatus entity, int rownum) {
		super(entity, rownum);		
	}

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	public String getDtFimVigencia() {
		return  formataDate(getRow().getDtFimVigencia());
	}

	public String getDtInicioVigencia() {
		return  formataDate(getRow().getId().getDtInicioVigencia());
	}

	
	
	
}
