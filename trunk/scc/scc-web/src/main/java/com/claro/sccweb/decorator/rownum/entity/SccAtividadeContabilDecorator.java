package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccAtividadeContabil;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccAtividadeContabilDecorator extends RownumDecorator<SccAtividadeContabil> {

	public SccAtividadeContabilDecorator(SccAtividadeContabil entity, int rownum) {
		super(entity, rownum);
	}

	 
	protected boolean isDeleteEnabled() {
		return true;
	}

	public String getDtExpiracao() {
		return formataDate(getRow().getDtExpiracao());
	}
	
	
	public String getDsAtividadeContabil() {
		return (getRow().getDsAtividadeContabil());		 
	}
	
	public String getDominioContabil() {
		return (getRow().getDominioContabil().getDsDominioContabil());		 
	}
	
	public String getContaCredito() {
		return (getRow().getContaCredito().getDsConta());		 
	}
	
	public String getContaDebito() {
		return (getRow().getContaDebito().getDsConta());		 
	}
	
	public String getOperadoraLD() {
		return (getRow().getOperadoraLD().getDsOperadora());		 
	}
	
	public String getOperadoraLDEOT() {
		return (getRow().getOperadoraLD().getCdEot());		 
	}

	public String getTxHistoricoAtividade() {
		return (getRow().getTxHistoricoAtividade());		 
	}
	
}
