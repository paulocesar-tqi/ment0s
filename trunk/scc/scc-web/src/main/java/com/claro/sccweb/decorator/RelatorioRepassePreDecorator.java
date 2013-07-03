package com.claro.sccweb.decorator;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
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
	
	public String getOperadoraClaro(){
		String value = "";
		if(getRow() != null && getRow().getOperadoraClaro() != null && getRow().getOperadoraClaro().getDsOperadora() != null){
			value = getRow().getOperadoraClaro().getDsOperadora();
		}
		
		return value;
	}
	
	public String getCriterio()
	{
		return getRow().getCriterio();
	}
	
	public String getProduto(){
		
		String value = "";
		if(getRow() != null && getRow().getProdutoPrepago() != null && StringUtils.isNotEmpty(getRow().getProdutoPrepago().getNoProdutoPrepago())){
			value = getRow().getProdutoPrepago().getNoProdutoPrepago();
		}
			
		return value;
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
	
	public String getEmpresaLD(){
		String value = "";
		if(getRow() != null && getRow().getOperadoraLD() != null && StringUtils.isNotEmpty(getRow().getOperadoraLD().getCdEot())){
			value = getRow().getOperadoraLD().getDsOperadora();
		}
		return value;
	}
	
	public String getEmpresaClaro(){
		String value = "";
		if(getRow() != null && getRow().getOperadoraClaro() != null && StringUtils.isNotEmpty(getRow().getOperadoraClaro().getCdEot())){
			value = getRow().getOperadoraClaro().getDsOperadora();
		}
		
		return value;
	}
	
	
	
}
