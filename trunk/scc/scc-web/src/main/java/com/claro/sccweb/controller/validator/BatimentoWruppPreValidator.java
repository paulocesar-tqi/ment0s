package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.BatimentoWruppPreForm;

public class BatimentoWruppPreValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(BatimentoWruppPreForm.class);
	}

	@Override
	public void validate(Object arg, Errors errors) {
		BatimentoWruppPreForm form = (BatimentoWruppPreForm)arg;
		
		ValidatorUtil.verificaCampoObrigatorio("anoRelatorio", form.getAnoRelatorio(), errors);
		ValidatorUtil.verificaCampoObrigatorio("mesRelatorio", form.getMesRelatorio(), errors);
		ValidatorUtil.verificaCampoObrigatorio("cdEOTLD", form.getCdEOTLD(), errors);
		ValidatorUtil.verificaCampoObrigatorio("cdEOTClaro", form.getCdEOTClaro(), errors);
		
		
	}

}
