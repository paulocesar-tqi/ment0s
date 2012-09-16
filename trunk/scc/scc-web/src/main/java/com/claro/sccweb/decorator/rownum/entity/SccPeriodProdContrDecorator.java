package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccPeriodProdContr;
import com.claro.cobillingweb.persistence.entity.SccPeriodicidadeRepasse;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccPeriodProdContrDecorator extends RownumDecorator<SccPeriodProdContr>{

		
	public SccPeriodProdContrDecorator(SccPeriodProdContr entity, int rownum) {
		super(entity, rownum);	
	}
	
	public String getContrato()
	{
		return getRow().getSccProdutoContratado().getSccContratoCobl().getDsContratoCobilling();
	}
	
	public String getProduto()
	{
		return getRow().getSccProdutoContratado().getSccProdutoCobilling().getNoProdutoCobilling();
	}
	
	public String getPeriodicidade()
	{
		SccPeriodicidadeRepasse periodo = getRow().getSccPeriodicidadeRepasse();
		return periodo.getNoPeriodicidadeRepasse();
	}
	

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	
}
