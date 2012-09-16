package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccAtividadeContabil;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccAtividadeContabilDecorator extends RownumDecorator<SccAtividadeContabil> {

	public SccAtividadeContabilDecorator(SccAtividadeContabil entity, int rownum) {
		super(entity, rownum);
	}

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	public String getDtExpiracao() {
		return formataDate(getRow().getDtExpiracao());
	}
	
}
