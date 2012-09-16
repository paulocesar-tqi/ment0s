package com.claro.sccweb.decorator;

import java.text.SimpleDateFormat;

import org.displaytag.decorator.TableDecorator;

import com.claro.sccweb.service.composite.SolicitacaoRepassePosComposite;

/**
 * Decorator para {@link SolicitacaoRepassePosComposite}
 *
 */
public class RelatorioRepasseDecorator extends TableDecorator {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public String getPeriodo()
	{
		StringBuffer periodo = new StringBuffer();
		periodo.append(dateFormat.format(getRow().getPeriodoInicio()));
		periodo.append("  ");
		periodo.append(dateFormat.format(getRow().getPeriodoFinal()));
		return periodo.toString();
	}
	
	
	public String getRequisicao()
	{
		return getRow().getNoRequisicao();
	}
	
	public String getOperadoraLD()
	{
		return getRow().getOperadoraExterna().getDsOperadora();
	}
	
	public String getOperadoraClaro()
	{		
		return getRow().getOperadoraClaro().getDsOperadora();
	}
	
	public String getDataCriacao()
	{
		return dateFormat.format(getRow().getDataCriacao());
	}
	
	public String getUsuario()
	{
		return getRow().getUsuario();
	}
	
	public String getProduto()
	{
		return getRow().getProdutoCobilling().getNoProdutoCobilling();
	}
	
	public String getRemover()
	{
		return "<a href='/scc/user/repasse/pos/solicitacao/delete/"+getRow().getNoRequisicao()+".scc'>Remover</a>";
	}
	
	private SolicitacaoRepassePosComposite getRow()
	{
		return (SolicitacaoRepassePosComposite)getCurrentRowObject();		
	}
	
	 
	
}
