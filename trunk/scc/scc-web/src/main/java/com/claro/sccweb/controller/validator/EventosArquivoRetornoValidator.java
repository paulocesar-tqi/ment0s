package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.EventosArquivoRetornoForm;

public class EventosArquivoRetornoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {		
		return clazz.equals(EventosArquivoRetornoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		EventosArquivoRetornoForm form = (EventosArquivoRetornoForm)arg;
		ValidatorUtil.verificaPeriodo("dataInical", form.getDataInicial(), form.getDataFinal(), errors);
		ValidatorUtil.verificaCampoObrigatorio("dataInicial", form.getDataInicial(), errors);
		ValidatorUtil.verificaCampoObrigatorio("dataFinal", form.getDataFinal(), errors);		
	}

	
	
}
