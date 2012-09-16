package com.claro.sccweb.decorator;

import java.text.DecimalFormat;

import com.claro.cobillingweb.persistence.entity.SccItemRepasse;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.sccweb.service.helper.DemonstrativoPosPagoConstantes;


/**
 * Decorator para a tabela com itens de repasse da tela de Relatório e Ajuste de Repasse.
 *
 */
public class AjusteRepassePosDecorator {
	
	private DecimalFormat sccCurrencyFormat = new DecimalFormat("#.##");
	 
	
	private SccRepasse row;
	private SccItemRepasse itemRepasse;
	private boolean modificado;
	
	public AjusteRepassePosDecorator(SccRepasse row,SccItemRepasse itemRepasse)
	{
		this.row = row;
		this.itemRepasse = itemRepasse;
	}
	
	public String getItemRepasse()
	{
		return itemRepasse.getDsItemRepasse();
	}
	
	public String getValorLiquido()
	{
		if (getRow().getVlLiquidoItemRepasse() == null)
			return "";
		return sccCurrencyFormat.format(getRow().getVlLiquidoItemRepasse());
	}
	
	public String getValorBruto()
	{
		if (getRow().getVlBrutoItemRepasse() == null)
			return "";
		return sccCurrencyFormat.format(getRow().getVlBrutoItemRepasse());
	}
	
	
	
	public String getOperacao()
	{
		if (editavel())
			return "<span class='ui-icon ui-icon-pencil' onClick=\"ajustar("+getRow().getCdItemRepasse()+",'"+itemRepasse.getDsItemRepasse()+"')\"></span>";
		else			
			return "<span class='ui-icon ui-icon-locked'></span>";
	}
	
	public String getApagar()
	{
		if (editavel())
			return "<span class='ui-icon ui-icon-trash' onClick='remover("+getRow().getCdItemRepasse()+")'></span>";
		else			
			return "<span class='ui-icon ui-icon-locked'></span>";
	}
	
	public SccRepasse getRow()
	{
		return row;
	}
	
	
	/**
	 * Verifica se o tipo de item é editável.
	 * @return true se o usuário poder remover ou editar o item.
	 */
	public boolean editavel()
	{
		Long item = getRow().getCdItemRepasse();
		if (item.equals(DemonstrativoPosPagoConstantes.ACERTO_CONCILIACAO_CONTRA_OPERADORA_LD))
			return true;
		else if (item.equals(DemonstrativoPosPagoConstantes.ACERTO_CONCILIACAO_CONTRA_CLARO))
			return true;
		else if (item.equals(DemonstrativoPosPagoConstantes.MULTAS_JUROS_ATRASO_REPASSE_CONTRA_CLARO))
			return true;
		else if (item.equals(DemonstrativoPosPagoConstantes.MULTAS_JUROS_ATRASO_PAGAMENTO_CONTRA_LD))
			return true;
		else if (item.equals(DemonstrativoPosPagoConstantes.PENALIDADE_REJEICOES_INDEVIDAS_CONTRA_CLARO))
			return true;
		else if (item.equals(DemonstrativoPosPagoConstantes.PENALIDADE_REJEICOES_DEVIDAS_CONTRA_LD))
			return true;
		return false;
	}

	public boolean isModificado() {
		return modificado;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}
	
	
}
