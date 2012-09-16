package com.claro.sccweb.decorator;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.claro.cobillingweb.persistence.entity.SccRepasse;
import com.claro.sccweb.service.composite.RepassePosPagoComposite;



/**
 * Decorator para a tela de pesquisa de repasses pós pago.
 *
 */
public class ConsultaRepassePosPagoDecorator  {
	
	private DecimalFormat sccCurrencyFormat = new DecimalFormat("#.##");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
	private RepassePosPagoComposite row;
	private int index;
	private String novoStatus;
	private String anoMes;
	private boolean modificado;
	
	public ConsultaRepassePosPagoDecorator(RepassePosPagoComposite row,int index)
	{
		this.row = row;
		this.index = index;
	}
	
	public String getNuRepasse()
	{
		if (getRow().getNuRepasse() != null)
			return Long.toString(getRow().getNuRepasse());
		else
			return "-";
	}
	
	public String getQuinzena()
	{
		return getRow().getPeriodicidadeRepasse().getCdPeriodicidadeRepasse()+"("+getRow().getPeriodicidadeRepasse().getDdInicioPeriodoRepasse()+" a "+getRow().getPeriodicidadeRepasse().getDdFimPeriodoRepasse()+")";
	}
		
	
	public String getAnoMes()
	{
		return anoMes;
	}

	
	
	public void setAnoMes(String anoMes) {
		this.anoMes = anoMes;
	}

	public String getOperadoraLD()
	{
		return getRow().getOperadoraLD().getDsOperadora();
	}
	
	public String getOperadoraClaro()
	{
		return getRow().getOperadoraClaro().getDsOperadora()+"("+getRow().getOperadoraClaro().getCdEot()+")";
	}
	
	public String getValorBrutoRepasse()
	{
		return sccCurrencyFormat.format(getRow().getValorBrutoRepasse());
	}
	
	public String getConfirmacao()
	{		
		StringBuffer sb = new StringBuffer();		
		if (getRow().getStatusRepasse() == null)
			{
			sb.append("<input type='radio' name='index_"+index+"' "); 
			if ((novoStatus == null) || (novoStatus.equals(SccRepasse.STATUS_NULO)))
				sb.append(" checked ");
			sb.append("value='E"+index+"'>Nulo");
			
			
			sb.append("<input type='radio' name='index_"+index+"' "); 
			if ((novoStatus != null) && (novoStatus.equals(SccRepasse.STATUS_CONFIRMADO)))
				sb.append(" checked ");
			sb.append("value='C"+index+"'>Sim");			
			
			sb.append("<input type='radio' name='index_"+index+"' ");
			if ((novoStatus != null) && (novoStatus.equals(SccRepasse.STATUS_CANCELADO)))
				sb.append(" checked ");
			sb.append("value='N"+index+"'>Não");
			}
		else if (getRow().getStatusRepasse().equalsIgnoreCase(SccRepasse.STATUS_CONFIRMADO))
			{
			sb.append("<font color='#00aa00'>");
			sb.append("Confirmado");
			sb.append("</font>");
			}
		else if (getRow().getStatusRepasse().equalsIgnoreCase(SccRepasse.STATUS_CANCELADO))
			{
			sb.append("<font color='#aa0000'>");
			sb.append("Cancelado");
			sb.append("</font>");
			}
		return sb.toString();
	}
	
	public String getAjuste()
	{
		StringBuffer sb = new StringBuffer();
		if (getRow().getStatusRepasse() == null)
			{
			sb.append("<a href=/scc/user/repasse/pos/ajuste/new/"+getRow().getNuRepasse()+".scc>Ajustar</a>");
			}
		else if (getRow().getStatusRepasse().equalsIgnoreCase(SccRepasse.STATUS_CONFIRMADO))
			{
			sb.append("<font color='#00aa00'>");
			sb.append("Confirmado");
			sb.append("</font>");
			}
		else if (getRow().getStatusRepasse().equalsIgnoreCase(SccRepasse.STATUS_CANCELADO))
			{
			sb.append("<font color='#aa0000'>");
			sb.append("Cancelado");
			sb.append("</font>");
			}
		return sb.toString();
	}
	
	
	
	public String getDataInicial()
	{
		return dateFormat.format(getRow().getDtInicialRepasse());
	}
	
	public RepassePosPagoComposite getRow()
	{
		return this.row;
	}

	public int getIndex() {
		return index;
	}

	public String getNovoStatus() {
		return novoStatus;
	}

	public void setNovoStatus(String novoStatus) {
		this.novoStatus = novoStatus;
	}

	public boolean isModificado() {
		return modificado;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}
	
	
	
}
