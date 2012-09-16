package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.entity.SccFaixaPenalidade;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccFaixaPenalidadeDecorator extends RownumDecorator<SccFaixaPenalidade> {
	
	public SccFaixaPenalidadeDecorator(SccFaixaPenalidade entity, int rownum) {
		super(entity, rownum);
	}
	
	protected boolean isDeleteEnabled() {		
		return true;
	}
	
	public String getCdFaixaPenalidade() {
		//return formataLong(getRow().getCdFaixaPenalidade());
		return getRow().getCdFaixaPenalidade()+"";
	}
	
	public String getDsFaixaPenalidade() {
		return getRow().getDsFaixaPenalidade();
	}
	
	public String getDtFimVigencia() {
		return formataDate(getRow().getDtFimVigencia());
	}
	
	public String getDtInicioVigencia() {
		return formataDate(getRow().getDtInicioVigencia());
	}
	
	public String getInTipoPenalidade() {
		String tipo = " ";
		if (getRow().getInTipoPenalidade().equalsIgnoreCase("C"))
			tipo = "Chamadas Perdidas";
		else if (getRow().getInTipoPenalidade().equalsIgnoreCase("Q"))
			tipo = "Questionamento";
		return tipo;
	}
	
	public String getPeInicioFaixaPenalidade() {
		return formataDouble34(getRow().getPeInicioFaixaPenalidade());
	}
	
	public String getPeFimFaixaPenalidade() {
		return formataDouble34(getRow().getPeFimFaixaPenalidade());
	}
	
	public String getVlFatorCalculoPenalidade() {
		return formataDouble(getRow().getVlFatorCalculoPenalidade());
	}
	
}
