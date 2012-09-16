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
		return getRow().getCdMotivoRetarifacao().toString();
	}
	
	public String getCodigoProduto() {
		return getRow().getSccProdutoCobilling().getCdProdutoCobilling().toString();
	}
	
	public String getProduto() {
		return getRow().getSccProdutoCobilling().getNoProdutoCobilling();
	}
	
	public String getItemCobilling() {
		return getRow().getSccItemCobilling().getNoItemCobilling();
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
