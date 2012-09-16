package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccMotivoRejeicaoDecorator extends RownumDecorator<SccMotivoRejeicao>{

	
	
	public SccMotivoRejeicaoDecorator(SccMotivoRejeicao entity, int rownum) {
		super(entity, rownum);
	}

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	
	
}
