package com.claro.sccweb.decorator;

import java.text.SimpleDateFormat;

import org.displaytag.decorator.TableDecorator;

import com.claro.sccweb.service.composite.SolicitacaoRepassePreComposite;

/**
 * Decorator para tela de solicitação de relatório de repasse pré-pago.
 *
 */
public class RelatorioRepassePreDecorator extends TableDecorator {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public String getReferencia()
	{
		return getRow().getPeriodo();
	}
	
	public String getRequisicao()
	{
		return getRow().getNmParametro();
	}
	
	public String getOperadoraLD()
	{
		return getRow().getOperadoraLD().getDsOperadora();
	}
	
	public String getOperadoraClaro()
	{
		return getRow().getOperadoraClaro().getDsOperadora();
	}
	
	public String getCriterio()
	{
		return getRow().getCriterio();
	}
	
	public String getProduto()
	{
		return getRow().getProdutoPrepago().getNoProdutoPrepago();
	}
	
	public String getDtCriacao()
	{
		return dateFormat.format(getRow().getDtCriacao());
	}
	
	public String getDtEvento()
	{
		if (getRow().getDtEvento() != null)
			return dateFormat.format(getRow().getDtEvento());
		else
			return "";
	}
	
	public String getUsuario()
	{
		return getRow().getUsuario();
	}
	
	public String getRemover()
	{		
		return "<a href='#' onClick=\"remove('"+getRow().getNmParametro()+"')\">Remover</a>";
	}
	
	public SolicitacaoRepassePreComposite getRow()
	{
		return (SolicitacaoRepassePreComposite)getCurrentRowObject();
	}
	
}
