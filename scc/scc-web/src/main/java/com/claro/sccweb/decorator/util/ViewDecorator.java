package com.claro.sccweb.decorator.util;

import com.claro.cobillingweb.persistence.view.mapper.View;
import com.claro.sccweb.decorator.BasicSccDecorator;

public abstract class ViewDecorator<E extends View> extends BasicSccDecorator {

	
	public String getRemover()
	{
		if (isDeleteEnabled())
			{
			return "<a href='#' onClick='remover("+getRow().getRownum()+")'> Remover </a>";
			}
		return "<font color='red'>Remover</font>";		
	}
	
	
	public String getEditar()
	{
		return "<a href='#' onClick='editar("+getRow().getRownum()+")'> Editar </a>";
	}
	
	
	protected abstract boolean isDeleteEnabled();
	
	protected E getRow()
	{
		return (E)getCurrentRowObject();
	}
	
	
}
