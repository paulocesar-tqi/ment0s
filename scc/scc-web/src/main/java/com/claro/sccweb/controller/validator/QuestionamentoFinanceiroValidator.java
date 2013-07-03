package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.QuestionamentoFinanceiroForm;

public class QuestionamentoFinanceiroValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(QuestionamentoFinanceiroForm.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		QuestionamentoFinanceiroForm form = (QuestionamentoFinanceiroForm) obj;
		if(form.getOperacao() != null  && form.getOperacao().equalsIgnoreCase("pesquisar")){
			ValidatorUtil.verificaCampoObrigatorio("filtro.ano", form.getFiltro().getAno(), errors);
			
		}

	}

}
