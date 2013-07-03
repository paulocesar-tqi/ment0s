/**
 * 
 */
package com.claro.sccweb.decorator.view;

import org.displaytag.decorator.TableDecorator;

import com.claro.cobillingweb.persistence.entity.SccContestacaoPrePago;

/**
 * @author vagner.souza
 *
 */
public class SccConstestacaoPrePagoDecorator extends TableDecorator {
	
	public String getOperadora(){
		
		SccContestacaoPrePago entity = (SccContestacaoPrePago) getCurrentRowObject();
		return entity.getSccOperadora().getDsOperadora() + " ( "+entity.getSccOperadora().getCdEot() +" )";
	}
	


}
