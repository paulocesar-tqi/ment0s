package com.claro.sccweb.decorator.rownum.entity;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccPreChamadasCreditoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
* @author 92038883
*
*/
public class SccPreChamadasCreditoViewDecorator extends RownumDecorator<SccPreChamadasCreditoView> {
			

	public SccPreChamadasCreditoViewDecorator(SccPreChamadasCreditoView entity,
			int rownum) {
		super(entity, rownum);
	}

	@Override
	protected boolean isDeleteEnabled() {
		return false;
	}
	
		public String getArquivoCredito(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getArquivoCredito())){
			value = getRow().getArquivoCredito();
		}
		return value;
	}
	
	public String getDsOperadoraExterna(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getDsOperadoraExterna())){
			value = getRow().getDsOperadoraExterna();
		}
		return value;
	}
	
	public String getDsOperadoraClaro(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getDsOperadoraClaro())){
			value = getRow().getDsOperadoraClaro();
		}
		return value;
	}
	
	public String getCdCsp(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getCdCsp())){
			value = getRow().getCdCsp();
		}
		return value;
	}
	
	public String getStatusCredito(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getStatusCredito())){
			value = getRow().getStatusCredito();
		}
		return value;
	}
	
	public String getDtCredito(){
		String value = "";
		if(getRow().getDtCredito() != null){
			value = formataDate(getRow().getDtCredito());
		}
		return value;
	}
	
	public String getValorCredito(){
		String value = "";
		if(getRow().getValorCredito() != null){
			value = formataDouble(getRow().getValorCredito());
		}
		return value;
	}
	
	public String getNuCDR(){
		String value = "";
		if(getRow().getNuCDR()!= null){
			value = getRow().getNuCDR().toString();
		}
		return value;
	}
	
	public String getCdCredito(){
		String value = "";
		if(getRow().getCdCredito()!= null){
			value = getRow().getCdCredito().toString();
		}
		return value;
	}
	
	public String getNuAssA(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getNuAssA())){
			value = getRow().getNuAssA();
		}
		return value;
	}
	
	public String getNuAssB(){
		String value = "";
		if(StringUtils.isNotEmpty(getRow().getNuAssB())){
			value = getRow().getNuAssB();
		}
		return value;
	}
	
	public String getDtChamada(){
		String value = "";
		if(getRow().getDtChamada() != null){
			value = formataDate(getRow().getDtChamada());
		}
		return value;
	}
	
	public String getInicioChamada(){
		
		return formatarhorachamada(getRow().getInicioChamada());
		
	}
	
	public String getDuracaoTarifada(){
		String value = "";
		if(getRow().getDuracaoTarifada() != null){
			value = formataDouble(getRow().getDuracaoTarifada());
		}
		return value;
	}
	
	public String getValorBrutoChamada(){
		String value = "";
		if(getRow().getValorBrutoChamada() != null){
			value = formataDouble(getRow().getValorBrutoChamada());
		}
		return value;
	}
	
    //Formatando a hora:
	private String formatarhorachamada(Integer InicioChamada){
	    String horasStr = (getRow().getInicioChamada().intValue()>0)?getRow().getInicioChamada()+"":"";
		
		if(horasStr.length() > 5 ){
			horasStr = horasStr.substring(0, 2)+":"+horasStr.substring(2, 4)+":"+horasStr.substring(4, 6);
		}else if(horasStr.length() > 4){
			horasStr = "0" + horasStr;
			horasStr = horasStr.substring(0, 2)+":"+horasStr.substring(2, 4)+":"+horasStr.substring(4, 6);
		}else if(horasStr.length() > 3){
				horasStr = "00" + horasStr;
				horasStr = horasStr.substring(0, 2)+":"+horasStr.substring(2, 4)+":"+horasStr.substring(4, 6);
		}
		return horasStr;
	}
	
}
