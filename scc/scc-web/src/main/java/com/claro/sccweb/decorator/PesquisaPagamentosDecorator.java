package com.claro.sccweb.decorator;

import com.claro.sccweb.controller.BaseFormController;
import com.claro.sccweb.service.composite.RepassePosPagoComposite;
import com.claro.sccweb.service.composite.RepassePrePagoComposite;

public class PesquisaPagamentosDecorator extends BasicSccDecorator {


	private String periodo;
	private String nuRepasse;
	private String operadoraLD;
	private String operadoraClaro;
	private String vlrRepasse;
	private String lancado;
	
	private int posicaoLinha;
	private String tipoContrato;
	
	
	
	public PesquisaPagamentosDecorator(int posicaoLinha,RepassePosPagoComposite composite)
	{
		this.posicaoLinha = posicaoLinha;
		periodo = datePeriodoFormat.format(composite.getDtInicialRepasse());
		nuRepasse = composite.getNuRepasse().toString();
		operadoraLD = composite.getOperadoraLD().getDsOperadora()+" ("+composite.getOperadoraLD().getCdEot()+")";
		operadoraClaro = composite.getOperadoraClaro().getDsOperadora()+" ("+composite.getOperadoraClaro().getCdEot()+")";
		vlrRepasse = decimalFormat.format(composite.getValorBrutoRepasse());
		tipoContrato = BaseFormController.MODULO_POS_PAGO;
		lancado = composite.getStatusRepasse();
	}
	
	public PesquisaPagamentosDecorator(int posicaoLinha,RepassePrePagoComposite composite)
	{
		this.posicaoLinha = posicaoLinha;
		periodo = composite.getPeriodo();
		nuRepasse = composite.getSqPreFechamento().toString();
		operadoraLD = composite.getOperadoraLD().getDsOperadora()+" ("+composite.getOperadoraLD().getCdEot()+")";
		operadoraClaro = composite.getOperadoraClaro().getDsOperadora()+" ("+composite.getOperadoraClaro().getCdEot()+")";
		vlrRepasse = decimalFormat.format(composite.getValorRepasse());
		tipoContrato = BaseFormController.MODULO_PRE_PAGO;
		lancado = composite.getStatus();
	}

	public String getPeriodo() {
		return periodo;
	}

	public String getNuRepasse() {
		return nuRepasse;
	}
	
	public Long getPkRepasse()
	{
		return Long.parseLong(nuRepasse);
	}

	public String getOperadoraLD() {
		return operadoraLD;
	}

	public String getOperadoraClaro() {
		return operadoraClaro;
	}

	public String getVlrRepasse() {
		return vlrRepasse;
	}

	public String getLancado() {
		String value = "";
		if(lancado.equalsIgnoreCase("C")){
			value = "Sim";
		}else{
			value = "Não";
		}
		return value;
	}
	
	
	@SuppressWarnings("unused")
	private String getLink(String valor)
	{
		return "<a href='#' onClick=selecionaRepasse("+posicaoLinha+")>"+valor+"</a>";		
	}

	public String getTipoContrato() {
		return tipoContrato;
	}
	
	
	
	
}
