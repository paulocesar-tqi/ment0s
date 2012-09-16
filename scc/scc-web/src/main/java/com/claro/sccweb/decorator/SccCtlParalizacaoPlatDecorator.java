package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.entity.SccCtlParalizacaoPlat;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccCtlParalizacaoPlatDecorator extends RownumDecorator<SccCtlParalizacaoPlat>{
	
	public SccCtlParalizacaoPlatDecorator(SccCtlParalizacaoPlat entity,int rownum) {
		super(entity, rownum);
	}
	
	protected boolean isDeleteEnabled() {		
		return false;
	}
	
	public String getDtInicioParalizacao() {
		return formataDate(getRow().getDtInicioParalizacao());		
	}
	
	public String getDtFimParalizacao() {
		return formataDate(getRow().getDtFimParalizacao());		
	}	
	
	public String getQtDuracaoParalizacao() {
		if (getRow().getQtDuracaoParalizacao() != null)
			return Long.toString(getRow().getQtDuracaoParalizacao());
		else
			return "";
	}

}
