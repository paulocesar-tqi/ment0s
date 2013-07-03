/**
 * 
 */
package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;
import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.entity.SccAgingIndicador;
import com.claro.cobillingweb.persistence.entity.SccIndicador;

/**
 * @author vagner.souza
 *
 */
public class SccIndicadorDecorator extends TableDecorator {
	
	
	public String getCdTipoContrato(){
		String value = "";
		SccIndicador entity = (SccIndicador) getCurrentRowObject();
		
		if(StringUtils.isNotEmpty(entity.getCdIndicador())){
			if(!entity.getCdIndicador().substring(0,3).equalsIgnoreCase("PRE")){
				value = "Pos-Pago Faturado";
			}else{
				value = entity.getCdIndicador().substring(0, 3);
			}
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
	
	public String getRemover(){
		
		SccIndicador entity = (SccIndicador) getCurrentRowObject();
		
		return "<a href=\"removerIndicador.scc?indicador=" + entity.getCdIndicador() + "\"></a>" ;
	}

}
