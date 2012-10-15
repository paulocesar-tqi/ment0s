package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.SccAgingFaturasForm;

public class SccAgingFaturasValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(SccAgingFaturasForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		SccAgingFaturasForm form = (SccAgingFaturasForm) obj;
		if(form.getOperacao() != null  && form.getOperacao().equalsIgnoreCase("pesquisar")){
			ValidatorUtil.verificaCampoObrigatorio("dataInicialPeriodo", form.getDataInicialPeriodo(), errors);
			ValidatorUtil.verificaCampoObrigatorio("dataFinalPeriodo", form.getDataFinalPeriodo(), errors);
			ValidatorUtil.verificaPeriodo("dataFinalPeriodo", form.getDataInicialPeriodo(), form.getDataFinalPeriodo(), errors);	
			
		}

	}


}
