/**
 * 
 */
package com.claro.sccweb.decorator.view;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.constants.AbrangenciaChamadaConstants;
import com.claro.cobillingweb.persistence.constants.TipoAtivacaoConstants;
import com.claro.cobillingweb.persistence.constants.TipoFranquiaConstants;
import com.claro.cobillingweb.persistence.constants.TipoTerminalConstants;
import com.claro.cobillingweb.persistence.entity.SccComposicaoProduto;

/**
 * @author rodvagne
 *
 */
public class ComposicaoProdutoDecorator extends TableDecorator {
	
	public SccComposicaoProduto gerarEntidade(){
		return (SccComposicaoProduto) getCurrentRowObject();
	}
	
	public String getMotivoRetarifacao() {
		String value = "";
		if(gerarEntidade().getCdMotivoRetarifacao() != null){
			value = gerarEntidade().getCdMotivoRetarifacao().toString();
		}else{
			value = "0";
		}
		return value;
	}
	
	public String getCodigoProduto() {
		String value = "";
		if(gerarEntidade().getSccProdutoCobilling().getCdProdutoCobilling() != null){
			value = gerarEntidade().getSccProdutoCobilling().getCdProdutoCobilling().toString();
		}
		return value;
	}
	
	public String getProduto() {
		String value = "";
		if(gerarEntidade().getSccProdutoCobilling().getNoProdutoCobilling() != null){
			value = gerarEntidade().getSccProdutoCobilling().getNoProdutoCobilling();
		}
		return value;
	}
	
	public String getItemCobilling() {
		String value = "";
		if(gerarEntidade().getSccItemCobilling().getNoItemCobilling() != null){
			value = gerarEntidade().getSccItemCobilling().getNoItemCobilling();
		}
		return value;
	}
	
	public String getTipoAtivacao() {
		return TipoAtivacaoConstants.getLabel(gerarEntidade().getSccItemCobilling().getInTipoItem());		 
	}
	
	public String getAbrangenciaChamada() {
		return AbrangenciaChamadaConstants.getLabel(gerarEntidade().getSccItemCobilling().getInAbrangenciaChamada());		 
	}
	
	public String getTipoTerminal() {
		return TipoTerminalConstants.getLabel(gerarEntidade().getSccItemCobilling().getInTipoTerminal());		 
	}
	
	public String getTipoFranquia() {
		return TipoFranquiaConstants.getLabel(gerarEntidade().getSccItemCobilling().getInUsoFranquia());		 
	}
	

}
