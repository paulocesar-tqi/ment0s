package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroContratoPreForm;
import com.claro.sccweb.form.CadastroPenalidadeForm;

public class CadastroContratoPreValidator implements Validator{

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroContratoPreForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroContratoPreForm form = (CadastroContratoPreForm)arg;
		
		
	}

	
	
}
