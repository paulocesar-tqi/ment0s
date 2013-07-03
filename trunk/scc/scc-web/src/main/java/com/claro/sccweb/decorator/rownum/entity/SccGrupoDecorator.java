package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccGrupoDecorator extends RownumDecorator<SccGrupoCobilling> {

	public SccGrupoDecorator(SccGrupoCobilling entity, int rownum) {
		super(entity, rownum);
		
	}
	
	
	private String getLink(String valor){
		return "<a href='#' onClick=editar("+getRownum()+")>"+valor+"</a>";		
	}
	
	public String getDescricaoGrupo(){
		
		return getLink(getRow().getNoGrupo());
	}


	@Override
	protected boolean isDeleteEnabled() {

		return false;
	}
	
	public Long getSeqGrupo(){
		
		return getRow().getSqGrupo();
	}

}
