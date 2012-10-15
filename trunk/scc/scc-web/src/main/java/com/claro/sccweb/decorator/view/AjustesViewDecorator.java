/**
 * 
 */
package com.claro.sccweb.decorator.view;

import com.claro.cobillingweb.persistence.view.AjustesView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 93046251
 *
 */
public class AjustesViewDecorator extends RownumDecorator<AjustesView> {

	public AjustesViewDecorator(AjustesView entity, int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		
		return false;
	}
	
	public String getOperadoraLd(){
		
		return getRow().getOperadoraLD();
	}
	
	public String getOperadoraClaro(){
		return getRow().getOperadoraClaro();
	}
	
	public String getUf(){
		return getRow().getUf();
	}
	
	public String getDataAjuste(){
		return formataDate(getRow().getDataAjuste());
	}
	
	public String getValorAjuste(){
		return formataDouble(getRow().getValorAjustado());
	}
	
	

}
