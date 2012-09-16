package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.RelatorioPagamentoForm;

public class RelatorioPagamentoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(RelatorioPagamentoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		// TODO Auto-generated method stub
		
	}

	
	
}
