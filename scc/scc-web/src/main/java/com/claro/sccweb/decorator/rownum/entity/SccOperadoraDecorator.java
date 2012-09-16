package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccOperadoraDecorator extends RownumDecorator<SccOperadora> {

	
	
	public SccOperadoraDecorator(SccOperadora entity, int rownum) {
		super(entity, rownum);
	}

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	public String getQtDiasChamadaIntExpira() {
		return formataInteger(getRow().getQtDiasChamadaIntExpira());
	}

	public String getQtDiasChamadaNacExpira() {
		return formataInteger(getRow().getQtDiasChamadaNacExpira());
	}

	public String getQtDiasExclusao() {
		return formataInteger(getRow().getQtDiasExclusao());
	}

	public String getQtLimiteIdadeInt() {
		return formataInteger(getRow().getQtLimiteIdadeInt());
	}

	public String getQtLimiteIdadeNac() {
		return formataInteger(getRow().getQtLimiteIdadeNac());
	}

	public String getDtAtualizacao() {
		return formataDate(getRow().getDtAtualizacao());
	}

	public String getDtCriacao() {
		return formataDate(getRow().getDtCriacao());
	}

	public String getEOTHolding() {
		return formataString(getRow().getCdOperadoraHolding());
	}
	
	public String getCSP()
	{
		return formataString(getRow().getCdCsp());
	}
}
