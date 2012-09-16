package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccContratoCobl;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccContratoCoblDecorator extends RownumDecorator<SccContratoCobl>{

	public SccContratoCoblDecorator(SccContratoCobl entity, int rownum) {
		super(entity, rownum);	
	}

	 
	protected boolean isDeleteEnabled() {
		return true;
	}
	
	public String getDsContratoCobilling() 
	{
		return getRow().getDsContratoCobilling();
	}
	
	public String getDtInicioVigencia()
	{
		return formataDate(getRow().getDtInicioVigencia());
	}
	
	
	public String getDtFimVigencia()
	{
		return formataDate(getRow().getDtFimVigencia());
	}

	
	
}
