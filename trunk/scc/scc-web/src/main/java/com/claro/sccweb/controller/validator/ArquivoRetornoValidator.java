package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.ArquivoRetornoForm;

public class ArquivoRetornoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ArquivoRetornoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		ArquivoRetornoForm form = (ArquivoRetornoForm)arg;
		ValidatorUtil.verificaPeriodo("dataInicial", form.getDataInicial(), form.getDataFinal(), errors);
		ValidatorUtil.verificaCampoObrigatorio("dataInicial", form.getDataInicial(), errors);
		ValidatorUtil.verificaCampoObrigatorio("dataFinal", form.getDataFinal(), errors);		
	}

	
	
}
