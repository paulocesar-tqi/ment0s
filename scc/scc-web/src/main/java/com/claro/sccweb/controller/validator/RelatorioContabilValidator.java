package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.RelatorioContabilForm;

public class RelatorioContabilValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {		
		return clazz.equals(RelatorioContabilForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		RelatorioContabilForm form = (RelatorioContabilForm)arg;
		ValidatorUtil.verificaMes("mes", form.getMes(), errors);
		ValidatorUtil.verificaCampoObrigatorio("ano", form.getAno(), errors);
		ValidatorUtil.verificaNumeroPositivo("ano", form.getAno(), errors);
	}

	
	
}
