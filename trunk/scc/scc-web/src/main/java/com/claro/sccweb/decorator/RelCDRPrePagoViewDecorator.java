package com.claro.sccweb.decorator;

import java.text.SimpleDateFormat;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.view.RelCDRPrePagoView;

public class RelCDRPrePagoViewDecorator extends TableDecorator {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
		
	public String getDataChamada()
	{
		return dateFormat.format(getRow().getDataChamada());
	}
	
	public String getDataCarga()
	{
		return dateFormat.format(getRow().getDataCarga());
	}
	
	
	public String getDataFechamento() {
		String value = "";
		if(getRow().getDataFechamento() != null){
			value = dateFormat.format(getRow().getDataFechamento());
		}
		return value;
	}
	
	
	/*public String getDataFechamento()
	{
		return dateFormat.format(getRow().getDataFechamento());
	}*/
	
	private RelCDRPrePagoView getRow()
	{
		return (RelCDRPrePagoView)getCurrentRowObject();
	}
	
}
