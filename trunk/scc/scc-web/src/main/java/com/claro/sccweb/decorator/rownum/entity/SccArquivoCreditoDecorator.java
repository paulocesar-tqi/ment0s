package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccArquivoCredito;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccArquivoCreditoDecorator extends RownumDecorator<SccArquivoCredito> {

	private String status;
	private String origem;
	
	
	public SccArquivoCreditoDecorator(SccArquivoCredito entity,String status,String origem,  int rownum) {
		super(entity, rownum);
		this.status = status;
		this.origem = origem;		
	}
	
	
	public SccArquivoCreditoDecorator(SccArquivoCredito entity,int rownum) {
		super(entity, rownum);
	}

	 
	protected boolean isDeleteEnabled() {
		return false;
	}


	public String getStatus() {
		if (this.status != null)
			return status;
		else
			return getRow().getCdStatus();
	}


	public String getOrigem() {
		if (this.origem != null)
			return origem;
		else
			return getRow().getCdOrigem();
	}
	
	public String getDataProcessamento()
	{
		return formataDate(getRow().getDtCarga());
	}

	
	 
	public SccArquivoCredito getRow() {
		return super.getRow();
	}
	
}
