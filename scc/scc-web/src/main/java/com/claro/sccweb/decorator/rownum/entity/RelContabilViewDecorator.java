package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.view.RelContabilView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class RelContabilViewDecorator extends RownumDecorator<RelContabilView>{

	public RelContabilViewDecorator(RelContabilView entity, int rownum) {
		super(entity, rownum);
	}

	 
	protected boolean isDeleteEnabled() {		
		return false;
	}

	public String getQtCdrs()
	{
		return formataLong(getRow().getQtCdrs());
	}
	
	
	public String getVlLiquido()
	{
		return formataDouble(getRow().getVlLiquido());
	}
	
	public String getVlBruto()
	{
		return formataDouble(getRow().getVlBruto());
	}
	
	public String getCdEOTLD(){
		String value = "";
		if(getRow().getCdEOTLD() != null){
			value = getRow().getCdEOTLD();
		}
		return value;
	}
	
	public String getCdEOTClaro(){
		String value = "";
		if(getRow().getCdEOTClaro() != null){
			value = getRow().getCdEOTClaro();
		}
		return value;
	}
	
	public String getDescricao(){
		String value = "";
		if(getRow().getDescricao() != null){
			value = getRow().getDescricao();
		}
		return value;
	}
	
	public String getCdContabil(){
		String value = "";
		if(getRow().getCdContabil() != null){
			value = getRow().getCdContabil();
		}
		return value;
	}
	
	public String getDataFechamento(){
		String value = "";
		if(getRow().getDataFechamento() != null){
			value = getRow().getDataFechamento();
		}
		return value;
	}
	
	
}
