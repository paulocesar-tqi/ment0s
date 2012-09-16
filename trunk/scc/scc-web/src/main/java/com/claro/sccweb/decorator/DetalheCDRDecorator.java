package com.claro.sccweb.decorator;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;

public class DetalheCDRDecorator {

	public SccCdrCobilling sccCdrCobilling;
	
	public SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	public SimpleDateFormat hourFormat = new SimpleDateFormat("HHmmss");
	public SimpleDateFormat toHourFormat = new SimpleDateFormat("HH:mm:ss");
	
	public DecimalFormat sccCurrencyFormat = new DecimalFormat("#.##");
	
	public DetalheCDRDecorator(SccCdrCobilling sccCdrCobilling)
	{
		this.sccCdrCobilling = sccCdrCobilling;
	}
	
	public String getCodNatureza()
	{
		if (sccCdrCobilling.getCdNaturezaCobranca() != null)
			return sccCdrCobilling.getCdNaturezaCobranca().getDsNaturezaCobranca()+" ("+sccCdrCobilling.getCdNaturezaCobranca().getCdNaturezaCobranca()+")";
		return "-";
	}
	
	public String getNumA()
	{
		return sccCdrCobilling.getId().getNuMsisdnOrigem();
	}
	
	public String getNumB()
	{
		return sccCdrCobilling.getId().getNuTelefoneDestino();
	}
	
	public String getDataHoraChamada()
	{
		try {
			String X = null;
			if (sccCdrCobilling.getId().getHrInicioChamada().length() == 4)
				X = sccCdrCobilling.getId().getHrInicioChamada()+"00";
			else
				X = sccCdrCobilling.getId().getHrInicioChamada();				
		return dateFormat.format(sccCdrCobilling.getId().getDtChamada())+" "+toHourFormat.format(hourFormat.parse(X));
		} catch (Exception e)
			{
			return e.getMessage();
			}
	}
	
	public String getAreaVisitada()
	{
		return sccCdrCobilling.getCdAreaRoaming();
	}
	
	
	public String getDuracaoTarifada()
	{
		return sccCurrencyFormat.format(sccCdrCobilling.getMiDuracaoTarifada());
	}
	
	public String getCSP()
	{
		return sccCdrCobilling.getCdCsp();
	}
	
	public String getGrupoHorario()
	{
		return sccCdrCobilling.getCdPeriodoChamada();
	}
	
	public String getTipoChamada()
	{
		return sccCdrCobilling.getCdTipoChamada();
	}
	
	public String getStatus()
	{
		return sccCdrCobilling.getStatusCdr().getDsStatusCdr();
	}
	
	
	public String getDataStatus()
	{
		return dateFormat.format(sccCdrCobilling.getDtStatusCdr());
	}
	
	public String getMotivoRejeicao()
	{
		if (sccCdrCobilling.getCdMotivoRejeicao() != null)
			return sccCdrCobilling.getCdMotivoRejeicao().getDsMotivoRejeicao();
		return "-";
	}
	
	public String getErro()
	{
		return "-";
	}
	
	public String getCiclo()
	{
		if (sccCdrCobilling.getCdCiclo() != null)
			return sccCdrCobilling.getCdCiclo().toString();
		return "-";
	}
	
	public String getCicloMesAno()
	{
		return sccCdrCobilling.getMmCiclo()+"/"+sccCdrCobilling.getAaCiclo();
	}
	
	public String getDataEmissaoFatura()
	{
		if (sccCdrCobilling.getDtEmissaoFatura() != null)
			return dateFormat.format(sccCdrCobilling.getDtEmissaoFatura());
		return "-";
	}
	
	
	public String getDataVencimentoFatura()
	{
		if (sccCdrCobilling.getDtVencimentoFatura() != null)
			return dateFormat.format(sccCdrCobilling.getDtVencimentoFatura());
		return "-";
	}
	
	
	public String getNumeroFatura()
	{
		return sccCdrCobilling.getNuFatura();
	}
	
	public String getValorBruto()
	{
		if (sccCdrCobilling.getVlBrutoChamada() != null)
			return sccCurrencyFormat.format(sccCdrCobilling.getVlBrutoChamada());
		return "-";
	}
	
	public String getPais()
	{
		return sccCdrCobilling.getCdsPaisDestino();
	}
	
	public String getArquivoRetorno()
	{
		if (sccCdrCobilling.getArquivoRetorno() != null)
			return sccCdrCobilling.getArquivoRetorno().getNoArquivo();
		return "-";
	}
	
	
}
	
	
	
	

