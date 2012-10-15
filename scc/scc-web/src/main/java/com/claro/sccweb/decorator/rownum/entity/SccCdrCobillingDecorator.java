package com.claro.sccweb.decorator.rownum.entity;

import java.text.SimpleDateFormat;

import com.claro.cobillingweb.persistence.entity.SccArquivoCobilling;
import com.claro.cobillingweb.persistence.entity.SccCdrCobilling;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccCdrCobillingDecorator extends RownumDecorator<SccCdrCobilling>{

	SimpleDateFormat horaInput = new SimpleDateFormat("HHmmss");
	SimpleDateFormat horaOutput = new SimpleDateFormat("HH:mm:ss");
	
	private SccArquivoCobilling arquivoRemessa;
	
	
	public SccCdrCobillingDecorator(SccCdrCobilling entity, int rownum) {
		super(entity, rownum);
	}
	
	public SccCdrCobillingDecorator(SccCdrCobilling entity, SccArquivoCobilling arquivoRemessa ,int rownum) {
		super(entity, rownum);
		this.arquivoRemessa = arquivoRemessa;
	}

	 
	protected boolean isDeleteEnabled() {
		return false;
	}

	
	public String getStatus(){
		
		if(getRow().getStatusCdr() != null && getRow().getStatusCdr().getDsStatusCdr() != null){
			return formataString(getRow().getStatusCdr().getDsStatusCdr());
		}
		
		return "";
	}
	
	public String getMotivo()
	{
		if(getRow().getCdMotivoRejeicao() != null)
			return formataString(getRow().getCdMotivoRejeicao().getDsMotivoRejeicao());
		else
			return " ";
	}
	
	public String getCdMotivo()	{
		
		String retorno = "";
		if(getRow().getCdMotivoRejeicao() != null && getRow().getCdMotivoRejeicao().getCdMotivoRejeicao() != null){
			retorno =  formataString(getRow().getCdMotivoRejeicao().getCdMotivoRejeicao());
		}
		return retorno;
	}
	
	public String getQuantidade()
	{
		return getRow().getNuCdr().toString();
	}
	
	public String getCiclo()
	{
		return getRow().getMmCiclo()+"/"+getRow().getAaCiclo()+" "+getRow().getCdCiclo();
	}
	
	public String getListar()
	{
		return "<a href='#' onClick='listar("+getRownum()+")'> Listar CDRs </a>";
	}
	
	public String getDataStatus()
	{
		return formataDate(getRow().getDtStatusCdr());
	}
	
	public String getCSP()
	{
		return formataString(getRow().getCdCsp());
	}
	
	public String getNumeroA()
	{
		return getRow().getId().getNuMsisdnOrigem();
	}
	
	public String getNumeroB()
	{
		return getRow().getId().getNuTelefoneDestino();
	}
	
	public String getEOTOrigem()
	{
		return getRow().getCdEotOrigem();
	}
	
	public String getEOTExterna()
	{
		return getRow().getCdEotDestino();
	}
	
	public String getDataChamada()
	{
		return formataDate(getRow().getId().getDtChamada());
	}
	
	public String getHoraChamada()
	{
		String horaInicio = getRow().getId().getHrInicioChamada();
		if (horaInicio.length() == 5)
			horaInicio = "0"+horaInicio;	
		try {
		return horaOutput.format(horaInput.parse(horaInicio));
		} catch (Exception e)
			{
			return "ERROR!";
			}
	}
	
	public String getDuracao()
	{
		return formataDouble(getRow().getMiDuracaoTarifada());
	}
	
	
	
	public String getValorLiquido()
	{
		return formataDouble(getRow().getVlLiquidoChamada());
	}
	
	public String getArquivoRetorno()
	{
		try {
			return getRow().getArquivoRetorno().getNoArquivo();			
		} catch (Exception e) {
			return " ";
		}
	}

	public SccArquivoCobilling getArquivoRemessa() {
		return arquivoRemessa;
	}

	public void setArquivoRemessa(SccArquivoCobilling arquivoRemessa) {
		this.arquivoRemessa = arquivoRemessa;
	}
	
	public String getNomeArquivo()
	{
		if (arquivoRemessa != null)
			return arquivoRemessa.getNoArquivo();
		else
			return " ";
	}
	
	public String getEvento()
	{
		if (getRow().getStatusCdr().getCdStatusCdr().equals(10L))
				return "C";
		else
			return "E";
	}
	
	public String getDestinoChamada()
	{
		return formataString(getRow().getCdLocalidadeDestino());
	}
	
	public String getNaturezaCobranca()
	{
		if (getRow().getCdNaturezaCobranca() != null)
			return getRow().getCdNaturezaCobranca().getCdNaturezaCobranca().toString();
		else
			return " ";
	}
	
	public String getOperadoraClaro()
	{
		if (arquivoRemessa != null)
			return arquivoRemessa.getOperadoraClaro().getCdEot();
		else
			return " ";
	}
	
	public String getOperadoraLD()
	{
		if (arquivoRemessa != null)
			return arquivoRemessa.getOperadoraExterna().getCdEot();
		else
			return " ";
	}
	
	public String getPaisDestino()
	{
		return formataString(getRow().getCdsPaisDestino());
	}
	
	public String getLocalidadeOrigem()
	{
		return formataString(getRow().getCdLocalidadeOrigem());
	}
	
	public String getDuracaoReal()
	{
		return formataLong(getRow().getHrDuracaoReal());
	}
	
	public String getGrupoHorario()
	{
		return formataString(getRow().getCdPeriodoChamada());
	}
	
	public String getDegrau()
	{
		return formataString(getRow().getCdTipoChamada());
	}
	
	public String getAreaRoaming()
	{
		return formataString(getRow().getCdAreaRoaming());
	}
	
	
	
}
