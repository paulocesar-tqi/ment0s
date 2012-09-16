package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccTipoEvento;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccTipoEventoDecorator extends RownumDecorator<SccTipoEvento> {

	public SccTipoEventoDecorator(SccTipoEvento entity, int rownum) {
		super(entity, rownum);
	}
	
	public String getCategoria()
	{
		String categoria =  getRow().getId().getInCategoriaEvento();
		if (categoria.equals("A"))
			categoria = "Assinaturas";
		else if (categoria.equals("C"))
			categoria = "Chamadas";
		return categoria;
	}
	
	public String getCodigo()
	{
		return getRow().getId().getCdTipoEvento();
	}
	
	public String getNome()
	{
		return getRow().getNoTipoEvento();
	}
	
	public String getAtribuirPadrao()
	{
		return getRow().getFgAtribuirProdPadrao();
	}
	

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	
	
}
