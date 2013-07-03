/**
 * 
 */
package com.claro.sccweb.decorator.view;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.entity.SccDisputaPrePago;

/**
 * @author vagner.souza
 *
 */
public class SccDisputaPrePagoDecorator extends TableDecorator {
	
	public String getOperadora(){
		
		SccDisputaPrePago entity = (SccDisputaPrePago) getCurrentRowObject();
		return entity.getSccOperadora().getDsOperadora() + " ( "+entity.getSccOperadora().getCdEot() +" )";
	}

}
