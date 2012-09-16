package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.ConfigRepasseContratoForm;

public class ConfigRepasseContratoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ConfigRepasseContratoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		// TODO Auto-generated method stub
		
	}

	
	
}
