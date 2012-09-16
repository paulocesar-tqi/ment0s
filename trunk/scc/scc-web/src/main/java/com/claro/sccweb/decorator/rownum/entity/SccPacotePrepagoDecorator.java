package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccPacotePrepago;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccPacotePrepagoDecorator extends RownumDecorator<SccPacotePrepago>{

	
	
	public SccPacotePrepagoDecorator(SccPacotePrepago entity, int rownum) {
		super(entity, rownum);
	}
	
	public Long getCodigoPacote()
	{
		return getRow().getCdPacotePrepago();
	}
	
	public String getNomePacote()
	{
		return getRow().getNoPacotePrepago();
	}
	
	public String getProduto()
	{
		return getRow().getProdutoPrepago().getNoProdutoPrepago();
	}

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	
	
}
