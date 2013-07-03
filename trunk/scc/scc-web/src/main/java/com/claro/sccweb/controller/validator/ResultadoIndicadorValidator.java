package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.ResultadoIndicadorForm;

public class ResultadoIndicadorValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(ResultadoIndicadorForm.class);
	}

	@SuppressWarnings("unused")
	@Override
	public void validate(Object obj, Errors errors) {
		
		ResultadoIndicadorForm form = (ResultadoIndicadorForm) obj;
		
	}

}
