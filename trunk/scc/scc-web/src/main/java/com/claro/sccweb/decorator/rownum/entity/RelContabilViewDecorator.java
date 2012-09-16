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
	
	
	
	
}
