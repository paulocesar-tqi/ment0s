/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccRelDemonstrativoDesempenhoPenalidadeView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92038883
 *
 */
	public class SccRelDemonstrativoDesempenhoPenalidadeViewDecorator extends RownumDecorator<SccRelDemonstrativoDesempenhoPenalidadeView> {
		

		public SccRelDemonstrativoDesempenhoPenalidadeViewDecorator(SccRelDemonstrativoDesempenhoPenalidadeView entity,
				int rownum) {
			super(entity, rownum);
		}

		@Override
		protected boolean isDeleteEnabled() {
			return false;
		}
		

		
		public String getIndicador(){
			String value = "";
			if(StringUtils.isNotEmpty(getRow().getIndicador())){
				value = getRow().getIndicador();
			}
			return value;
		}
		
		public String getDescIndicador(){
			String value = "";
			if(StringUtils.isNotEmpty(getRow().getDescIndicador())){
				value = getRow().getDescIndicador();
			}
			return value;
		}
		
		
		public String getRespIndicador(){
			String value = "";
			if(StringUtils.isNotEmpty(getRow().getRespIndicador())){
				value = getRow().getRespIndicador();
			}
			return value;
		}
		
		
		public String getVlOrigem(){
			String value = "";
			if(getRow().getVlOrigem() != null){
				value = formataDouble(getRow().getVlOrigem());
			}
			return value;
		}
		
		public String getMetaIndicador(){
			String value = "";
			if(getRow().getMetaIndicador() != null){
				value = formataDouble(getRow().getMetaIndicador()/100);
			}
			return value;
		}
		
		public String getPesoIndicador(){
			String value = "";
			if(getRow().getPesoIndicador() != null){
				value = formataDouble(getRow().getPesoIndicador()/100);
			}
			return value;
		}
		
		
		public String getAtingimento() {
			String str = "0.0";
			
			if(StringUtils.isNotEmpty(getMetaIndicador())){
				str = decimalFormat.format(zeroIfNull(getRow().getVlOrigem()*10000)
						/ zeroIfNull(getRow().getMetaIndicador()/100)/10000);
				
			}
			return str;
		}
		
		public Double getAtingimento2(){
			Double value = zeroIfNull(getRow().getVlOrigem()*10000)
					/ zeroIfNull(getRow().getMetaIndicador()/100)/10000;
			
			return value;
		}
		
		public String getAcelerador(){
			
			String value = null;

			
			if(getAtingimento2() != null){
				Double atigimentoAux = getAtingimento2();
				if (atigimentoAux > 1.10 && value == null){
					value = "0,80";
				}
				if((atigimentoAux > 1.00 && atigimentoAux <= 1.10) && value == null){
					value = "0,90";
				}
				if((atigimentoAux > 0.90 && atigimentoAux <= 1.00) && value == null){
					value = "1,00";
				}
				if((atigimentoAux > 0.80 && atigimentoAux <= 0.90) && value == null){
					value = "1,10";
				}
				if((atigimentoAux > 0.70 && atigimentoAux <= 0.80) && value == null){
					value = "1,50";
				}
				if (atigimentoAux > 0.70 && value == null){
					value = "2,00";
				}
				
				if(value == null){
					value = getAtingimento().replace(",", ".");
				}
				
			}
		
		return value;
		}

}
