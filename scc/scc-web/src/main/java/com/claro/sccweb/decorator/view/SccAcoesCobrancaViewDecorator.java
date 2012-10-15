/**
 * 
 */
package com.claro.sccweb.decorator.view;

import com.claro.cobillingweb.persistence.view.SccAcoesCobrancaView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 93046251
 *
 */
public class SccAcoesCobrancaViewDecorator extends RownumDecorator<SccAcoesCobrancaView>{

	public SccAcoesCobrancaViewDecorator(SccAcoesCobrancaView entity, int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		
		return false;
	}

}
