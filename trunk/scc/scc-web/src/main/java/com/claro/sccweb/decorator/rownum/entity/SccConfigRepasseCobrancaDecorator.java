package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.constants.FormaCobrancaConstants;
import com.claro.cobillingweb.persistence.constants.FormaPagamentoConstants;
import com.claro.cobillingweb.persistence.constants.FormaRepasseConstants;
import com.claro.cobillingweb.persistence.entity.SccConfigRepasseCobranca;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccConfigRepasseCobrancaDecorator extends RownumDecorator<SccConfigRepasseCobranca> {

	public SccConfigRepasseCobrancaDecorator(SccConfigRepasseCobranca entity,int rownum) {
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
	
	public String getItemProduto()
	{
		return getRow().getSccComposicaoProduto().getSccItemCobilling().getNoItemCobilling();
	}
	
	public String getCDRRepasse()
	{
		return getRow().getStatusCdrRepasse().getDsStatusCdr();
	}
	
	public String getCDRCobranca()
	{
		return getRow().getStatusCdrCobranca().getDsStatusCdr();
	}
	
	public String getFormaCobranca()
	{
		return FormaCobrancaConstants.getFormaCobranca(getRow().getInFormaPagamento());
	}
	
	public String getFormaPagamento()
	{
		return FormaPagamentoConstants.getFormaPagamento(getRow().getInFormaPagamento());
	}
	
	public String getFormaRepasse()
	{
		return FormaRepasseConstants.getFormaRepasse(getRow().getInFormaPagamento());
	}
	
	public String getPrestacaoServicos()
	{
		return getRow().getFgCobrarPrestacaoServico();
	}
	
	public String getCalculoCobilling()
	{
		return formataDouble(getRow().getVlCalculoCobilling());
	}
	
	
	public String getCalculoRepasse()
	{
		return formataDouble(getRow().getVlCalculoRepasse());
	}
	
	
	 
	protected boolean isDeleteEnabled() {		
		return true;
	}

	
	
}
