/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccRelatorioCobilling;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 93046251
 *
 */
public class SccRelatorioCobillingDecorator extends RownumDecorator<SccRelatorioCobilling> {

	public SccRelatorioCobillingDecorator(SccRelatorioCobilling entity,
			int rownum) {
		super(entity, rownum);
		
	}
	
	@Override
	protected boolean isDeleteEnabled() {
		return true;
	}


}
