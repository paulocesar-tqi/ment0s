package com.claro.sccweb.decorator;

import java.text.SimpleDateFormat;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.entity.SccContratoAcordado;


/**
 * Decorator para tabela com pares de operadoras na pequisa por produto e período na tela 
 * de demonstrativo de repasse.
 *
 */
public class ParOperadorasRepassePosDecorator extends TableDecorator {

	public SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public String getOperadoraClaro()
	{			
		return getSolicitar(getRow().getOperadoraClaro().getDsOperadora()+"("+getRow().getOperadoraClaro().getCdEot()+")");
	}
	
	public String getOperadoraExterna()
	{
		return getSolicitar(getRow().getOperadoraExterna().getDsOperadora()+"("+getRow().getOperadoraExterna().getCdEot()+")");
	}
	
	public String getCriacaoContrato()
	{
		return getSolicitar(dateFormat.format(getRow().getDtCriacao()));
	}
	
	public String getFinalVigencia()
	{
		return getSolicitar(dateFormat.format(getRow().getDtFimVigencia()));
	}
	
	private SccContratoAcordado getRow()
	{
		return (SccContratoAcordado)getCurrentRowObject();		
	}
	
	public String getSolicitar(String texto)
	{		
		if (getRow().getOperadoraClaro().isHolding())			
			return "<a href='#' onClick=selecionarParOperadoras('"+getRow().getId().getCdEotClaro()+"','"+getRow().getOperadoraExterna().getCdEot()+"') ><font color='red'>"+texto+"</font></a>";	
		else
			return "<a href='#' onClick=selecionarParOperadoras('"+getRow().getId().getCdEotClaro()+"','"+getRow().getOperadoraExterna().getCdEot()+"') >"+texto+"</a>";
		
	}
}
