package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.ConfigPeriodicidadeContratoForm;

public class ConfigPeriodicidadeContratoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ConfigPeriodicidadeContratoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {

		
	}

	
	
}
