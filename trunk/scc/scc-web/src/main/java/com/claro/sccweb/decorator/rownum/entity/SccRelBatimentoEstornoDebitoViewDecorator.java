package com.claro.sccweb.decorator.rownum.entity;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccRelBatimentoEstornoDebitoView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
* @author 92038883
*
*/

	public class SccRelBatimentoEstornoDebitoViewDecorator extends RownumDecorator<SccRelBatimentoEstornoDebitoView> {
		

		public SccRelBatimentoEstornoDebitoViewDecorator(SccRelBatimentoEstornoDebitoView entity,
				int rownum) {
			super(entity, rownum);
		}

		@Override
		protected boolean isDeleteEnabled() {
			return false;
		}
		
		
		public String getSqArquivo(){
			String value = "";
			if(getRow().getSqArquivo()!= null){
				value = getRow().getSqArquivo().toString();
			}
			return value;
		}
		
		
		public String getOperadoraLD(){
			String value = "";
			if(StringUtils.isNotEmpty(getRow().getOperadoraLD())){
				value = getRow().getOperadoraLD();
			}
			return value;
		}
		
		public String getOperadoraClaro(){
			String value = "";
			if(StringUtils.isNotEmpty(getRow().getOperadoraClaro())){
				value = getRow().getOperadoraClaro();
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
		
		public String getNoArquivo(){
			String value = "";
			if(StringUtils.isNotEmpty(getRow().getNoArquivo())){
				value = getRow().getNoArquivo();
			}
			return value;
		}
		
		public String getDsStatus(){
			String value = "";
			if(StringUtils.isNotEmpty(getRow().getDsStatus())){
				value = getRow().getDsStatus();
			}
			return value;
		}
		
		public String getValorTotalNf(){
			String value = "";
			if(getRow().getValorTotalNf() != null){
				value = formataDouble(getRow().getValorTotalNf());
			}
			return value;
		}
		
	
		public String getValorTotalImpugnado(){
			String value = "";
			if(getRow().getValorTotalImpugnado() != null){
				value = formataDouble(getRow().getValorTotalImpugnado());
			}
			return value;
		}
		
	
		public String getDtConnect(){
			String value = "";
			if(getRow().getDtConnect() != null){
				value = formataDate(getRow().getDtConnect());
			}
			return value;
		}
		

}
