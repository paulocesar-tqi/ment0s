package com.claro.sccweb.decorator.rownum.entity;

import com.claro.cobillingweb.persistence.entity.SccAliquotaImposto;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccAliquotaImpostoDecorator extends RownumDecorator<SccAliquotaImposto> {
	
	
	public SccAliquotaImpostoDecorator(SccAliquotaImposto entity, int rownum) {
		super(entity, rownum);
	}

	 
	protected boolean isDeleteEnabled() {	
		return true;
	}

	public String getDtFimVigencia() {
		return formataDate(getRow().getDtFimVigencia());
	}


	public String getImposto()
	{
		String _imposto = getRow().getId().getNoImposto();
		if (_imposto.equals("I"))
			return "ICMS";
		else if (_imposto.equals("P"))
			return "PIS";
		else if (_imposto.equals("C"))
			return "COFINS";
		else if (_imposto.equals("S"))
			return "ISS";
		return _imposto;
	}
	
	public String getTipoServico()
	{
		String _tipoServico = getRow().getId().getInTipoServico();
		if (_tipoServico.equals("T"))
			return "Telecom/Chamadas";
		else if (_tipoServico.equals("C"))
			return "Cobilling";
		return _tipoServico;
	}

	public String getDtInicioVigencia() {
		return formataDate(getRow().getId().getDtInicioVigencia());
	}

	public String getAliquota()
	{
		return formataDouble(getRow().getPcAliquotaImposto());
	}

	
	
}
