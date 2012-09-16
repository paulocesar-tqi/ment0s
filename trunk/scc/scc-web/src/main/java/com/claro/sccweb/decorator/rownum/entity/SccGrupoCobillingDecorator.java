/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccGrupoCobilling;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 93046251
 *
 */
public class SccGrupoCobillingDecorator extends RownumDecorator<SccGrupoCobilling> {

	public SccGrupoCobillingDecorator(SccGrupoCobilling entity, int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		
		return true;
	}

}
