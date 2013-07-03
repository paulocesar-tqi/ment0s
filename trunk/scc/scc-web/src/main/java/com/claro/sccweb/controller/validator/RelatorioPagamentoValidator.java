package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.RelatorioPagamentoForm;

public class RelatorioPagamentoValidator implements Validator {

	 
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(RelatorioPagamentoForm.class);
	}

	@Override 
	public void validate(Object obj, Errors errors) {
		RelatorioPagamentoForm form = (RelatorioPagamentoForm)obj;
		if(form.getOperacao() != null  && form.getOperacao().equalsIgnoreCase("pesquisar")){
/*			if(form.getCdProdutoCobilling() == null){
				errors.rejectValue("cdProdutoCobilling", "campoObrigatorio", "Campo Obrigatório");
			}
			
			if(form.getAnoRepasse() == null){
				errors.rejectValue("anoRepasse", "campoObrigatorio", "Campo Obrigatório");
			}
			if(form.getMesRepasse() == null){
				errors.rejectValue("mesRepasse", "campoObrigatorio", "Campo Obrigatório");
			}
*/			
		}
		
	}

	
	
}
