package com.claro.sccweb.decorator.view;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccFaturaView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

public class SccFaturasViewDecorator extends RownumDecorator<SccFaturaView> {

	public SccFaturasViewDecorator(SccFaturaView entity, int rownum) {
		super(entity, rownum);
		
	}

	@Override
	protected boolean isDeleteEnabled() {
		
		return false;
	}
	
	public String getOperadoraClaro(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getEotClaro())){
			value = getRow().getEotClaro();
		}
		return value;
	}
	
	
	public String getCsp(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCsp())){
			value = getRow().getCsp();
		}
		return value;
	}

	
	public String getOperadoraLd(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getOperadoraLD())){
			value = getRow().getOperadoraLD();
		}
		return value;
	}
	
	public String getUf(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getUf())){
			value = getRow().getUf();
		}
		return value;
	}
	
	public String getCicloMesAno(){
		String value = "";
		
		if(getRow().getCdCiclo() != null && getRow().getMmCiclo() != null && getRow().getAaCiclo() != null){
			value = getRow().getCdCiclo().toString() + " - " + getRow().getMmCiclo().toString() + "/" + getRow().getAaCiclo();
		}
		
		return value;
	}
	
	public String getNumeroFatura(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getNumeroFatura())){
			value = getRow().getNumeroFatura();
		}
		return value;
	}
	
	public String getDataEmissao(){
		String value = "";
		if(getRow().getDataEmissao() != null){
			value = formataDate(getRow().getDataEmissao());
		}
		return value;
	}
	
	public String getDataVencimento(){
		String value = "";
		if(getRow().getDataVencimento() != null){
			value = formataDate(getRow().getDataVencimento()); 
		}
		return value;
	}
	
	public String getValorOriginal(){
		String value = "0";
		if(getRow().getValorOriginal() != null){
			value = formataDouble(getRow().getValorOriginal());
		}
		
		return value;
	}
	
	public String getValor(){
		String value = "0";
		if(getRow().getValor() != null){
			value = formataDouble(getRow().getValor());
		}
		return value;
	}
	
	public String getValorIcms(){
		String value = "";
		if(getRow().getValorICMS() != null){
			value = formataDouble(getRow().getValorICMS());
		}
		return value;
	}
	
	
	public String getStatus(){
		String value ="";
		if(StringUtils.isNotEmpty(getRow().getStatus())){
			value = getRow().getStatus();
		}
		return value;
	}
	
	public String getSituacaoEvento(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getSituacaoEvento())){
			value = getRow().getSituacaoEvento();
		}
		return value;
	}
	
	public String getAging(){
		String value ="";
		if(getRow().getAging() != null){
			value = formataLong(getRow().getAging());
		}
		return value;
	}
	
	public String getAjuste(){
		String value = "0,00";
		if(getRow().getAjuste() != null){
			value = formataDouble(getRow().getAjuste());
		}
		return value;
	}
	
	public String getNumeroNotaFiscal(){
		String value = "0,00";
		if(getRow().getNumeroNotaFiscal() != null){
			value = formataLong(getRow().getNumeroNotaFiscal());
		}
		return value;
	}
	
	public String getSerie(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getSerie())){
			value = getRow().getSerie();
		}
		return value;
	}
	
	public String getSubSerie(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getSubSerie())){
			value = getRow().getSubSerie();
			
		}
		return value;
	}
	
	public String getTotalCreditos(){
		String value = "0,00";
		if(getRow().getTotalCreditos() != null){
			value = formataDouble(getRow().getTotalCreditos());
		}
		return value;
	}
	
	public String getValorOfertasLd(){
		String value = "0,00";
		if(getRow().getValorCreditosLD() != null){
			value = formataDouble(getRow().getValorCreditosLD());
		}
		return value;
	}
	
	public String getDescontosLd(){
		String value = "0,00";
		if(getRow().getValorDescontosLD() != null){
			value = formataDouble(getRow().getValorDescontosLD());
		}
		return value;
	}
	
	public String getValorCreditoLd(){
		String value = "0,00";
		if(getRow().getValorCreditosLD() != null){
			value = formataDouble(getRow().getValorCreditosLD());
		}
		return value;
	}
	
	public String getJuros(){
		String value = "0,00";
		if(getRow().getJuros() != null){
			value = formataDouble(getRow().getJuros());
		}
		return value;
	}
	
	public String getValorPago(){
		String value = "0,00";
		if(getRow().getValorPago() != null){
			value = formataDouble(getRow().getValorPago());
		}
		return value;
	}
	
	public String getQuantidadeEventos(){
		String value = "0,0";
		if(getRow().getQuantidadeEventos() != null){
			value = formataLong(getRow().getQuantidadeEventos());
		}
		return value;
	}
	
	
	public String getMulta(){
		String value = "0,0";
		if(getRow().getMultas() != null){
			value = formataDouble(getRow().getMultas());
		}
		return value;
	}
	

}
