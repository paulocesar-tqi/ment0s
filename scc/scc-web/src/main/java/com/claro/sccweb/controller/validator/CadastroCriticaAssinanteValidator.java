package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.CadastroCriticaAssinanteForm;

public class CadastroCriticaAssinanteValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {		
		return clazz.equals(CadastroCriticaAssinanteForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		
	}

	
	
}
