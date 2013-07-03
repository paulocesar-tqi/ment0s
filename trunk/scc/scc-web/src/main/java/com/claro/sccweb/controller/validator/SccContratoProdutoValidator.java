package com.claro.sccweb.controller.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.SccContratoProdutoForm;


public class SccContratoProdutoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(SccContratoProdutoForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		
	}

}
