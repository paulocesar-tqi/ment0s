package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.EvolucaoStatusForm;

public class EvolucaoStatusValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(EvolucaoStatusForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		EvolucaoStatusForm form = (EvolucaoStatusForm)arg;
		
		ValidatorUtil.verificaCampoObrigatorio("anoInicial", form.getAnoInicial(), errors);
		ValidatorUtil.verificaCampoObrigatorio("anoFinal", form.getAnoFinal(), errors);		
	}	
	
}
