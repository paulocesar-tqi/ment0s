package com.claro.sccweb.decorator;

import com.claro.cobillingweb.persistence.entity.SccPreFechamento;
import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.sccweb.decorator.rownum.RownumDecorator;
import com.claro.sccweb.service.calc.CalculoRepassePrePago;
import com.claro.sccweb.service.composite.RepassePrePagoComposite;

public class ConsultaRepassePrePagoDecorator extends RownumDecorator<RepassePrePagoComposite> {

	private String novoStatus;	
	private boolean modificado;
	CalculoRepassePrePago calculoRepassePrePago = null;
	
	
	public ConsultaRepassePrePagoDecorator(RepassePrePagoComposite entity,int rownum) {
		super(entity, rownum);
		calculoRepassePrePago = new CalculoRepassePrePago(entity.getPreFechamento());
	}

	
	public String getNovoStatus() {
		return novoStatus;
	}

	public void setNovoStatus(String novoStatus) {
		this.novoStatus = novoStatus;
	}
	
		
	
	public String getAnoMes()
	{
		return getRow().getPeriodo();
	}
	
	public String getOperadoraLD()
	{
		return getRow().getOperadoraLD().getDsOperadora();
	}
	
	public String getOperadoraClaro()
	{
		return getRow().getOperadoraClaro().getDsOperadora();
	}
	
	public String getValorRepasse()
	{
		DemonstrativoRepassePreItemDecorator valor = calculoRepassePrePago.getValorFinal();		
		return formataDouble(valor.getValorRepassar());
	}
	
	public String getConfirmacao()
	{		
		StringBuffer sb = new StringBuffer();		
		if (getRow().getStatus() == null)
			{
			sb.append("<input type='radio' name='getRownum()_"+getRownum()+"' "); 
			if ((novoStatus == null) || (novoStatus.equals(SccRepasse.STATUS_NULO)))
				sb.append(" checked ");
			sb.append("value='E"+getRownum()+"'>Nulo");
			
			
			sb.append("<input type='radio' name='getRownum()_"+getRownum()+"' "); 
			if ((novoStatus != null) && (novoStatus.equals(SccRepasse.STATUS_CONFIRMADO)))
				sb.append(" checked ");
			sb.append("value='C"+getRownum()+"'>Sim");			
			
			sb.append("<input type='radio' name='getRownum()_"+getRownum()+"' ");
			if ((novoStatus != null) && (novoStatus.equals(SccRepasse.STATUS_CANCELADO)))
				sb.append(" checked ");
			sb.append("value='N"+getRownum()+"'>Não");
			}
		else if (getRow().getStatus().equalsIgnoreCase(SccRepasse.STATUS_CONFIRMADO))
			{
			sb.append("<font color='#00aa00'>");
			sb.append("Confirmado");
			sb.append("</font>");
			}
		else if (getRow().getStatus().equalsIgnoreCase(SccRepasse.STATUS_CANCELADO))
			{
			sb.append("<font color='#aa0000'>");
			sb.append("Cancelado");
			sb.append("</font>");
			}
		return sb.toString();
	}


	public boolean isModificado() {
		return modificado;
	}


	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}



	
	public SccPreFechamento getEntity()
	{
		return this.getRow().getPreFechamento();
	}


	 
	protected boolean isDeleteEnabled() {
		return false;
	}
	
	
	
}
