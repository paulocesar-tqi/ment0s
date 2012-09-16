package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.AmostragemCriticaForm;

public class AmostragemCriticaValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(AmostragemCriticaForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		AmostragemCriticaForm form = (AmostragemCriticaForm)arg;
		ValidatorUtil.verificaCampoObrigatorio("dataInicial", form.getDataInicial(), errors);
		ValidatorUtil.verificaCampoObrigatorio("dataFinal", form.getDataFinal(), errors);
		ValidatorUtil.verificaPeriodo("dataInicial", form.getDataInicial(), form.getDataFinal(), errors);
		
	}

	
	
}
