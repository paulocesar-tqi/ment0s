/**
 * 
 */
package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;
import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;

/**
 * @author vagner.souza
 *
 */
public class SccAgingDecorator extends TableDecorator {
	
	public String getCdTipoContrato(){
		String value = "";
		SccAgingIndicador entity = (SccAgingIndicador) getCurrentRowObject();
		
		if(entity.getId()!= null && StringUtils.isNotEmpty(entity.getId().getCdIndicador())){
			value = entity.getId().getCdIndicador().substring(0, 3);
		}
		return value.trim();
	}
	
	public String getCdTipoContratoPos(){
		String value = "";
		SccAgingIndicador entity = (SccAgingIndicador) getCurrentRowObject();
		
		if(entity.getId()!= null && StringUtils.isNotEmpty(entity.getId().getCdIndicador())){
			if(!entity.getId().getCdIndicador().substring(0,3).equalsIgnoreCase("PRE")){
				value = "Pos-Pago Faturado";
			}
			
		}
		return value.trim();
	}
	
	public String remover(){
		
		SccAgingIndicador entity = (SccAgingIndicador) getCurrentRowObject();
		
		return "href=removerAging.scc?indicador="+ entity.getId().getCdIndicador();
	}
	
	
	public String getCdIndicador(){
		String value = "";
		SccAgingIndicador entity = (SccAgingIndicador) getCurrentRowObject();
		if(entity.getId()!= null && StringUtils.isNotEmpty(entity.getId().getCdIndicador())){
			value = entity.getId().getCdIndicador();
		}
		return value.trim();
	}
	
	public String getAging(){
		String value = "";
		SccAgingIndicador entity = (SccAgingIndicador) getCurrentRowObject();
		if(entity.getId() != null && entity.getId().getSqAgingIndicador() != null){
			value = entity.getId().getSqAgingIndicador().toString();
		}
		
		return value.trim();
	}

}
