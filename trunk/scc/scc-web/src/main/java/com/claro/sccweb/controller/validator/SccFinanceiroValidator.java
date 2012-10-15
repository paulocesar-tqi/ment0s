package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.SccFinanceiroForm;

public class SccFinanceiroValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(SccFinanceiroForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		SccFinanceiroForm form = (SccFinanceiroForm) obj;
		if(form.getOperacao() != null  && form.getOperacao().equalsIgnoreCase("pesquisar")){
			ValidatorUtil.verificaCampoObrigatorio("ano", form.getAno(), errors);
			
		}
	}

}
