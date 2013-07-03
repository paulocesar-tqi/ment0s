package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.CadastroGrupoRelatorioForm;

public class CadastroGrupoRelatorioValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(CadastroGrupoRelatorioForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		
		
	}

}
