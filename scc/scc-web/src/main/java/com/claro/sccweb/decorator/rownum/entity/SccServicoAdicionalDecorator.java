package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccServicoAdicional;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccServicoAdicionalDecorator extends RownumDecorator<SccServicoAdicional> {

	
	
	public SccServicoAdicionalDecorator(SccServicoAdicional entity, int rownum) {
		super(entity, rownum);
	}
	
	public String getChargeCode()
	{
		return getRow().getCdCharge();
	}
	
	public String getTipoCharge()
	{
		return getRow().getCdTipoCharge();
	}
	
	public String getDataCriacao()
	{
		return formataDate(getRow().getDtCriacao());
	}
	
	public String getDataAtualizacao()
	{
		return formataDate(getRow().getDtAtualizacao());
	}
	
	public String getUsuario()
	{
		return formataString(getRow().getCdUsuarioManut());
	}
	
	public String getProduto()
	{
		return getRow().getSccProdutoCobilling().getNoProdutoCobilling();
	}

	 
	protected boolean isDeleteEnabled() {		
		return true;
	}

	
	
}
