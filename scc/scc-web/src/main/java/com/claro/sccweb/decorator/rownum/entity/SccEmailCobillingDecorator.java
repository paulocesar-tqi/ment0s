/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccEmailCobilling;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 93046251
 *
 */
public class SccEmailCobillingDecorator extends RownumDecorator<SccEmailCobilling> {

	public SccEmailCobillingDecorator(SccEmailCobilling entity, int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return true;
	}
	
	

}
