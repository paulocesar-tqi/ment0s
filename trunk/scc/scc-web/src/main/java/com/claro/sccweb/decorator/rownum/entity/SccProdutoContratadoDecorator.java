package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccProdutoContratado;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccProdutoContratadoDecorator extends RownumDecorator<SccProdutoContratado> {

	
	
	public SccProdutoContratadoDecorator(SccProdutoContratado entity, int rownum) {
		super(entity, rownum);
	}




	public String getContrato() {
		return getRow().getSccContratoCobl().getDsContratoCobilling();
	}




	public String getProduto() {
		return getRow().getSccProdutoCobilling().getNoProdutoCobilling();
	}




	public String getProdutoPadrao() {
		return getRow().getFgProdutoPadrao();
	}




	public String getInicio() {
		return formataDate(getRow().getDtInicioVigencia());
	}




	public String getFim() {
		return formataDate(getRow().getDtFimVigencia());
	}




	public String getRepasse() {
		return "<a href='#' onClick='repasse("+getRow().getId().getCdContratoCobilling()+","+getRow().getId().getCdProdutoCobilling()+")'> Repasse </a>";
	}




	public String getPeriodicidade() {
		return "<a href='#' onClick='periodicidade("+getRow().getId().getCdContratoCobilling()+","+getRow().getId().getCdProdutoCobilling()+")'> Periodicidade </a>";
	}




	 
	protected boolean isDeleteEnabled() {		
		return true;
	}

	
	
}
