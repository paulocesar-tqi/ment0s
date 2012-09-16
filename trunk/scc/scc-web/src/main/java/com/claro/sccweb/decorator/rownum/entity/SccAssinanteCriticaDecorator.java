package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccAssinanteCritica;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccAssinanteCriticaDecorator extends RownumDecorator<SccAssinanteCritica>{
	
	public SccAssinanteCriticaDecorator(SccAssinanteCritica entity, int rownum) {
		super(entity, rownum);
	}

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	public String getDtFimVigencia() {
		return formataDate(getRow().getDtFimVigencia());
	}

	public String getDtInicioVigencia() {
		return formataDate(getRow().getDtInicioVigencia());
	}

	
	
}
