package com.claro.sccweb.controller.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.SccTarifaForm;

public class SccTarifaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(SccTarifaForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
	}

}
