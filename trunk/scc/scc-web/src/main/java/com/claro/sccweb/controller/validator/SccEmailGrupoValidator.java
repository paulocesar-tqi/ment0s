package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.CadastroEmailGrupoForm;

public class SccEmailGrupoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(CadastroEmailGrupoForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		
	}
	

}
