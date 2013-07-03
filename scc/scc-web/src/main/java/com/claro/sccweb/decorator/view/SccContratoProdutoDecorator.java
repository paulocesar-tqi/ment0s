package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;
import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;

public class SccContratoProdutoDecorator extends TableDecorator {
	
	public SccContratoCobillingSearchView getEntity(){
		
		
		SccContratoCobillingSearchView entity = (SccContratoCobillingSearchView) getCurrentRowObject();
	
		return entity;
		
	}
	
	public String getOperadoraLD(){
		String value = "";
		if(getEntity() != null && StringUtils.isNotEmpty(getEntity().getCdEOTLD()) && StringUtils.isNotEmpty(getEntity().getDsOperadoraLD())){
			
			value = getEntity().getDsOperadoraLD() + "( " + getEntity().getCdEOTLD() + " )";
		}
		return value;
	}
	
	public String getOperadoraClaro(){
		String value = "";
		if(getEntity() != null && StringUtils.isNotEmpty(getEntity().getCdEOTClaro()) && StringUtils.isNotEmpty(getEntity().getDsOperadoraClaro())){
			
			value = getEntity().getDsOperadoraClaro() + "( " + getEntity().getCdEOTClaro() + " )";
		}
		return value;
	}
	
	
	public String getEditar(){
		
		
		return "<a href='#' onClick='editar("+getEntity()+")'> Editar </a>";
		
		
	}
	

}
