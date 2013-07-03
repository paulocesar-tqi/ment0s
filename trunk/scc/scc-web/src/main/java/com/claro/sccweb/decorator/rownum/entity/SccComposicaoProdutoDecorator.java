package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.constants.AbrangenciaChamadaConstants;
import com.claro.cobillingweb.persistence.constants.TipoFranquiaConstants;
import com.claro.cobillingweb.persistence.constants.TipoTerminalConstants;
import com.claro.cobillingweb.persistence.constants.TipoAtivacaoConstants;
import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccComposicaoProdutoDecorator extends RownumDecorator<SccComposicaoProduto> {
	
	public SccComposicaoProdutoDecorator(SccComposicaoProduto entity, int rownum) {
		super(entity, rownum);	
	}
	
	public String getMotivoRetarifacao() {
		String value = "";
		if(getRow().getCdMotivoRetarifacao() != null){
			value = getRow().getCdMotivoRetarifacao().toString();
		}else{
			value = "0";
		}
		return value;
	}
	
	public String getCodigoProduto() {
		String value = "";
		if(getRow().getSccProdutoCobilling().getCdProdutoCobilling() != null){
			value = getRow().getSccProdutoCobilling().getCdProdutoCobilling().toString();
		}
		return value;
	}
	
	public String getProduto() {
		String value = "";
		if(getRow().getSccProdutoCobilling().getNoProdutoCobilling() != null){
			value = getRow().getSccProdutoCobilling().getNoProdutoCobilling();
		}
		return value;
	}
	
	public String getItemCobilling() {
		String value = "";
		if(getRow().getSccItemCobilling().getNoItemCobilling() != null){
			value = getRow().getSccItemCobilling().getNoItemCobilling();
		}
		return value;
	}
	
	public String getTipoAtivacao() {
		return TipoAtivacaoConstants.getLabel(getRow().getSccItemCobilling().getInTipoItem());		 
	}
	
	public String getAbrangenciaChamada() {
		return AbrangenciaChamadaConstants.getLabel(getRow().getSccItemCobilling().getInAbrangenciaChamada());		 
	}
	
	public String getTipoTerminal() {
		return TipoTerminalConstants.getLabel(getRow().getSccItemCobilling().getInTipoTerminal());		 
	}
	
	public String getTipoFranquia() {
		return TipoFranquiaConstants.getLabel(getRow().getSccItemCobilling().getInUsoFranquia());		 
	}
	 
	protected boolean isDeleteEnabled() {
		return true;
	}
	
}
