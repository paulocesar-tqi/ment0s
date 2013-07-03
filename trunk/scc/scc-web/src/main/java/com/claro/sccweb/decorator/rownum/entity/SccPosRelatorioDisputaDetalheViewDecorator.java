/**
 * 
 */
package com.claro.sccweb.decorator.rownum.entity;

import org.apache.commons.lang.StringUtils;

import com.claro.cobillingweb.persistence.view.SccPosRelatorioDisputaDetalheView;
import com.claro.sccweb.decorator.rownum.RownumDecorator;

/**
 * @author 92038883
 *
 */
	public class SccPosRelatorioDisputaDetalheViewDecorator extends RownumDecorator<SccPosRelatorioDisputaDetalheView> {
		

		public SccPosRelatorioDisputaDetalheViewDecorator(SccPosRelatorioDisputaDetalheView entity,
				int rownum) {
			super(entity, rownum);
		}

		@Override
		protected boolean isDeleteEnabled() {
			return false;
		}
		
		public String getSqDisputa(){
			String value = "";
			if(getRow().getSqDisputa()!= null){
				value = getRow().getSqDisputa().toString();
			}
			return value;
		}
		
		
		public String getCdEotLD(){
			String value = "";
			if(StringUtils.isNotEmpty(getRow().getCdEotLD())){
				value = getRow().getCdEotLD();
			}
			return value;
		}
		
		public String getCdStatusDisputa(){
			String value = "";
			if(StringUtils.isNotEmpty(getRow().getCdStatusDisputa())){
				value = getRow().getCdStatusDisputa();
			}
			return value;
		}
		
		
		public String getInRiscoDisputa(){
			String value = "";
			if(StringUtils.isNotEmpty(getRow().getInRiscoDisputa())){
				value = getRow().getInRiscoDisputa();
			}
			return value;
		}

		public String getDtCriacao(){
			String value = "";
			if(getRow().getDtCriacao() != null){
				value = formataDate(getRow().getDtCriacao());
			}
			return value;
		}
		
		public String getDtAtualizacao(){
			String value = "";
			if(getRow().getDtAtualizacao() != null){
				value = formataDate(getRow().getDtAtualizacao());
			}
			return value;
		}

		public String getCdUsuarioManut(){
			String value = "";
			if(StringUtils.isNotEmpty(getRow().getCdUsuarioManut())){
				value = getRow().getCdUsuarioManut();
			}
			return value;
		}


}
