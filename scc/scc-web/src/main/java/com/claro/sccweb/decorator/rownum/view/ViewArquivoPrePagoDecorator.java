package com.claro.sccweb.decorator.rownum.view;

import com.claro.cobillingweb.persistence.entity.external.ViewArquivoPrePago;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class ViewArquivoPrePagoDecorator extends RownumDecorator<ViewArquivoPrePago> {

	public ViewArquivoPrePagoDecorator(ViewArquivoPrePago entity, int rownum) {
		super(entity, rownum);		
	}

	 
	protected boolean isDeleteEnabled() {	
		return false;
	}

	
	public String getDataMovimento()
	{
		return formataDate(getRow().getDT_MOVIMENTO());
	}
	
	public String getDataProcesso()
	{
		return formataDate(getRow().getDH_PROCESSO());
	}
	
}
