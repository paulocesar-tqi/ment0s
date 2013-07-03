package com.claro.sccweb.decorator;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.displaytag.decorator.TableDecorator;
import org.displaytag.decorator.TotalTableDecorator;

import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;


/**
 * Decorator para tabela com lista de CDRs usando displaytag.
 *
 */
public class SccCdrCobilling2Decorator extends TableDecorator {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat hourFormat = new SimpleDateFormat("HHmmss");
	private SimpleDateFormat toHourFormat = new SimpleDateFormat("HH:mm:ss");
	
	private DecimalFormat sccCurrencyFormat = new DecimalFormat("#.##");
	
	private TotalTableDecorator totals = new TotalTableDecorator();
	
	
	private String getLink(String text)
	{
		return "<a href=/scc/user/pos/processados/item/cdr/"+getRow().getNuCdr()+".scc>"+text+"</>";
	}
	
	public String getCdMotivo(){
		return getMotivoRejeicao();
	}
	
	public String getMotivoRejeicao()	
	{
		if (getRow().getCdMotivoRejeicao() == null)
			return getLink("-");
		return getLink(getRow().getCdMotivoRejeicao().getDsMotivoRejeicao());
	}
	
	public String getStatus()
	{
		return getLink(getRow().getStatusCdr().getDsStatusCdr());
	}
	
	public String getDataStatus()
	{
		return getLink(dateFormat.format(getRow().getDtStatusCdr()));
	}
	
	public String getCsp()
	{
		return getLink(getRow().getCdCsp());
	}
	
	public String getNumeroA()
	{
		return getLink(getRow().getId().getNuMsisdnOrigem());
	}
	
	public String getNumeroB()
	{
		return getLink(getRow().getId().getNuTelefoneDestino());
		
	}
	
	public String getEOT()
	{
		return getLink(getRow().getCdEotOrigem());
	}
	
	public String getEOTExterna()
	{
		return getLink(getRow().getCdEotDestino());
	}
	
	public String getDataChamada()
	{
		return getLink(dateFormat.format(getRow().getId().getDtChamada()));
	}
	
	public String getHoraChamada()
	{		
		try {
			return getLink(toHourFormat.format(hourFormat.parse(getRow().getId().getHrInicioChamada())));	
		} catch (Exception e)
			{
			return getLink("error");
			}		
	}
	
	public String getDuracao()
	{
		if (getRow().getHrDuracaoReal() != null)
			Long.toString(getRow().getHrDuracaoReal());
		return getLink("0");
	}
	
	public String getValorLiquido()
	{
		if (getRow().getVlLiquidoChamada() == null)
			return getLink("-");
		return getLink(sccCurrencyFormat.format(getRow().getVlLiquidoChamada()));
	}
	
	public String getArquivoRetorno()
	{
		if (getRow().getArquivoRetorno() == null)
			return getLink("-");
		return getLink(getRow().getArquivoRetorno().getNoArquivo());
	}
	
	private SccCdrCobilling getRow()
	{
		return (SccCdrCobilling)getCurrentRowObject();		
	}
	
	
	
}
