package com.claro.sccweb.decorator.view;

import com.claro.cobillingweb.persistence.view.SccContratoCobillingSearchView;
import com.claro.sccweb.decorator.util.ViewDecorator;

public class SccContratoCobillingSearchDecorator extends ViewDecorator<SccContratoCobillingSearchView>{
	
	
	public String getDtInicioContrato() {
		return formataDate(getRow().getDtInicioContrato());
	}


	public String getDtFinalContrato() {
		return formataDate(getRow().getDtFinalContrato());
	}


	public String getValorAssociadoCriterioCusto() {
		return formataDouble(getRow().getValorAssociadoCriterioCusto());
	}




	public String getValorCPMF() {
		return formataDouble(getRow().getValorCPMF());
	}




	public String getValorISS() {
		return formataDouble(getRow().getValorISS());
	}




	public String getVlCriterioCustoLiquido() {
		return formataDouble(getRow().getVlCriterioCustoLiquido());
	}


	public String getQtRepasses() {
		return formataLong(getRow().getQtRepasses());
	}




	 
	protected boolean isDeleteEnabled() {
		return getRow().getQtRepasses().intValue() == 0;		
	}

	
	
}
