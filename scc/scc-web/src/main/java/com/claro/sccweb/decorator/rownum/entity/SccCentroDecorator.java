package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccCentro;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccCentroDecorator extends RownumDecorator<SccCentro> {
	
	public SccCentroDecorator(SccCentro entity, int rownum) {
		super(entity, rownum);
	}
	
	protected boolean isDeleteEnabled() {
		return true;
	}
	
}
