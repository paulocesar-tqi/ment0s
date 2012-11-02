package com.claro.sccweb.decorator;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.entity.SccPenalidadePorRejeicao;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccPenalidadePorRejeicaoDecorator extends RownumDecorator<SccPenalidadePorRejeicao> {
	
	private String nomeOperadoraLD;
	private String nomeMotivoRejeicao;
	private String descCobraPenalidade;
	
	public SccPenalidadePorRejeicaoDecorator(SccPenalidadePorRejeicao entity, int rownum) {
    	super(entity, rownum);
		try {
			this.nomeOperadoraLD = entity.getOperadoraLD().getDsOperadora();
			this.nomeMotivoRejeicao = entity.getMotivoRejeicao().getDsMotivoRejeicao();

			if (entity.getFgCobrarPenalidade().equalsIgnoreCase("S"))
				this.descCobraPenalidade = "Sim";
			else if (entity.getFgCobrarPenalidade().equalsIgnoreCase("N"))
				this.descCobraPenalidade = "Não";

		} catch (Exception e) {}

	}
	
	protected boolean isDeleteEnabled() {
		return true;
	}
	
	public String getNomeOperadoraLD() {
		return nomeOperadoraLD;
	}
	
	public String getNomeMotivoRejeicao() {
		return nomeMotivoRejeicao;
	}
	
	public String getVlPenalidade() {
		
		return formataDouble(getRow().getVlPenalidade());
	}

	public String getFgCobrarPenalidade() {
		return getRow().getFgCobrarPenalidade();
	}

	public String getDescCobraPenalidade() {
		return descCobraPenalidade;
	}

	public void setDescCobraPenalidade(String descCobraPenalidade) {
		this.descCobraPenalidade = descCobraPenalidade;
	}
	
	public String getCdOperadoraLD() {
		return getRow().getId().getCdEotLd();
	}

	public String getCdMotivoRejeicao() {
		return getRow().getId().getCdMotivoRejeicao();
	}

		
}
