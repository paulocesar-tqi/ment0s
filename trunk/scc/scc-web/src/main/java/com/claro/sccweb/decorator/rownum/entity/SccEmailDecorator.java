package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccEmailDecorator extends RownumDecorator<SccEmailCobilling> {

	public SccEmailDecorator(SccEmailCobilling entity, int rownum) {
		super(entity, rownum);
		
	}
	
	public String getEmail(){
		
		String value = "";
		if(getRow().getDescricao() != null){
			value = getRow().getDescricao();
		}
		return getLink(value);
		
	}
	private String getLink(String valor){
		return "<a href='#' onClick=editarEmail("+getRownum()+")>"+valor+"</a>";		
	}


	@Override
	protected boolean isDeleteEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
