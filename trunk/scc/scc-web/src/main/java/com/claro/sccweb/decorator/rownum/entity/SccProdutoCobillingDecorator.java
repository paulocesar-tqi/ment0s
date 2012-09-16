package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccProdutoCobilling;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccProdutoCobillingDecorator extends RownumDecorator<SccProdutoCobilling>{

	
	
	public SccProdutoCobillingDecorator(SccProdutoCobilling entity, int rownum) {
		super(entity, rownum);
	}
	
	
	public String getProduto()
	{
		return getRow().getNoProdutoCobilling();
	}
	
	public String getTipoProduto()
	{
		return getRow().getCdTipoProduto();
	}
	
	public String getDataInicioVigencia()
	{
		return formataDate(getRow().getDtInicioVigencia());
	}
	
	public String getDataFinalVigencia()
	{
		return formataDate(getRow().getDtFimVigencia());
	}

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	
	
}
