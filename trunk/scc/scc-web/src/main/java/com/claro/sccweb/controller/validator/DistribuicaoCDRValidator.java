package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.DistribuicaoCDRForm;

public class DistribuicaoCDRValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(DistribuicaoCDRForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		DistribuicaoCDRForm form = (DistribuicaoCDRForm)arg;
		ValidatorUtil.verificaCampoObrigatorio("dataInicial", form.getDataInicial(), errors);
		ValidatorUtil.verificaCampoObrigatorio("dataFinal", form.getDataFinal(), errors);
		ValidatorUtil.verificaPeriodo("dataInicial", form.getDataInicial(), form.getDataFinal(), errors);
	}

	
	
}
