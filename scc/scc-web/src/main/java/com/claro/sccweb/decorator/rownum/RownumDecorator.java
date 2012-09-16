package com.claro.sccweb.decorator.rownum;

import com.claro.sccweb.decorator.BasicSccDecorator;

public abstract class RownumDecorator<E>  extends BasicSccDecorator {

	private E entity;
	
	public RownumDecorator(E entity,int rownum)
	{
		this.rownum = rownum;
		this.entity = entity;
	}
	
	private int rownum;
	
	
	public int getRownum() {
		return rownum;	}




	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public String getRemover()
	{
		if (isDeleteEnabled())
			{
			return "<a href='#' onClick='remover("+getRownum()+")'> Remover </a>";
			}
		return "<font color='red'>Remover</font>";		
	}
	
	
	public String getEditar()
	{
		return "<a href='#' onClick='editar("+getRownum()+")'> Editar </a>";
	}
	
	public String getSelecionar()
	{
		return "<a href='#' onClick='selecionar("+getRownum()+")'> Selecionar </a>";
	}
	
	
	protected abstract boolean isDeleteEnabled();


	public E getRow()
	{
		return entity;
	}
	
}
