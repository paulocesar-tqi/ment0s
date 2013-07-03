package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccContaContabil;
import com.claro.sccweb.decorator.rownum.RownumDecorator;
import com.claro.cobillingweb.persistence.constants.TipoStatusConstants;
import com.claro.cobillingweb.persistence.constants.TipoCentroConstants;

public class SccContaContabilDecorator extends RownumDecorator<SccContaContabil> {

	public SccContaContabilDecorator(SccContaContabil entity, int rownum) {
		super(entity, rownum);
	}
	
	protected boolean isDeleteEnabled() {
		return true;
	}
	
	public String getStatusConta() {
		return TipoStatusConstants.getLabel(getRow().getInStatusConta());		 
	}
	
	public String getTpCentro() {
		return TipoCentroConstants.getLabel(getRow().getTpCentro());		 
	}
	
	public String getCdConta() {
		return (getRow().getCdConta().toString());		 
	}
	
	public String getDsConta() {
		return (getRow().getDsConta().toString());		 
	}
	
}
