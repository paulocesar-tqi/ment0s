package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccPreDominio;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccPreDominioDecorator extends RownumDecorator<SccPreDominio> {

	public SccPreDominioDecorator(SccPreDominio entity, int rownum) {
		super(entity, rownum);	
	}

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	
}
