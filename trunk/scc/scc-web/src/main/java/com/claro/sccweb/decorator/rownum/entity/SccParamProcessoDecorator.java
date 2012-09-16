package com.claro.sccweb.decorator.rownum.entity;

import java.util.Date;

import com.claro.sccweb.decorator.rownum.RownumDecorator;
import com.claro.sccweb.dto.SccParamProcessoDto;

public class SccParamProcessoDecorator extends RownumDecorator<SccParamProcessoDto> {
	
	private SccParamProcessoDto entity;

	public SccParamProcessoDecorator(SccParamProcessoDto entity, int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}

	public SccParamProcessoDto getEntity() {
		return entity;
	}

	public void setEntity(SccParamProcessoDto entity) {
		this.entity = entity;
	}
	
	
	
	public String getProcesso() {
		return getRow().getCdProcesso();
	}
	
	public Date getDtExercicio(){
		return getRow().getDtAtualizacao();
	}
	
	public String getParametro() {
		return getRow().getNmParametro();
	}
	
	public String getTipo() {
		return getRow().getCdTipoParametro();
	}
	
	public String getValor() {
		return getRow().getTxValorParametro();
	}

}
