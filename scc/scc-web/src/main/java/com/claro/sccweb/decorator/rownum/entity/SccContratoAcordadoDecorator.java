package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccContratoAcordadoDecorator extends RownumDecorator<SccContratoAcordado>{

	
	public SccContratoAcordadoDecorator(SccContratoAcordado entity, int rownum) {
		super(entity, rownum);
	}
	
	public String getOperadoraClaro()
	{
		return getRow().getOperadoraClaro().getDsOperadora();
	}
	
	
	public String getOperadoraExterna()
	{
		return getRow().getOperadoraExterna().getDsOperadora();
	}
	
	
	

	 
	protected boolean isDeleteEnabled() {		
		return true;
	}

	
	
}
