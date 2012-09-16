package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.CadastroTarifasForm;

public class CadastroTarifasValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroTarifasForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroTarifasForm form = (CadastroTarifasForm)arg;
	}

	
	
}
