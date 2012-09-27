package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.RelatorioConciliacaoPreForm;

public class RelatorioConciliacaoPreValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {		
		return clazz.equals(RelatorioConciliacaoPreForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		RelatorioConciliacaoPreForm form = (RelatorioConciliacaoPreForm)arg;
		ValidatorUtil.verificaMes("mes", form.getMes(), errors);
		ValidatorUtil.verificaCampoObrigatorio("ano", form.getAno(), errors);
		ValidatorUtil.verificaNumeroPositivo("ano", form.getAno(), errors);
	}

	
	
}
