package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicao;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccPenalidadePorRejeicaoDecorator extends RownumDecorator<SccPenalidadePorRejeicao> {
	
	public SccPenalidadePorRejeicaoDecorator(SccPenalidadePorRejeicao entity, int rownum) {
		super(entity, rownum);
	}
	
	protected boolean isDeleteEnabled() {
		return true;
	}
	
	public String getNomeOperadoraLD() {
		//return formataLong(getRow().getCdFaixaPenalidade());
		return getRow().getOperadoraLD().getDsOperadora();
	}
	
	public String getNomeMotivoRejeicao() {
		return getRow().getMotivoRejeicao().getDsMotivoRejeicao();
	}
	
	public String getVlPenalidade() {
		return formataDouble(getRow().getVlPenalidade());
	}
	
	public String getDescCobraPenalidade() {
		String retorno = " ";
		if (getRow().getFgCobrarPenalidade().equalsIgnoreCase("S"))
			retorno = "Sim";
		else if (getRow().getFgCobrarPenalidade().equalsIgnoreCase("N"))
			retorno = "Não";
		return retorno;

	}
	
}
