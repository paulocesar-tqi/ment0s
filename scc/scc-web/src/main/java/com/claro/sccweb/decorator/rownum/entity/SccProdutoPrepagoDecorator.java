package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccProdutoPrepago;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccProdutoPrepagoDecorator extends RownumDecorator<SccProdutoPrepago> {

	public SccProdutoPrepagoDecorator(SccProdutoPrepago entity, int rownum) {
		super(entity, rownum);
	}
	
	public String getProduto()
	{
		return getRow().getNoProdutoPrepago();
	}
	
	public String getPrestacaoServico()
	{
		return getRow().getFgCobrarPrestacaoServico();
	}
	
	

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	
	
}
