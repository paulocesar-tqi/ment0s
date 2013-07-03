package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.SccRelatorioQuestionamentoForm;

/**
 * @author 92038883
 *
 */

public class SccRelatorioQuestionamentoValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(SccRelatorioQuestionamentoForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		
		SccRelatorioQuestionamentoForm form = (SccRelatorioQuestionamentoForm)_form;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
		{
			ValidatorUtil.verificaCampoObrigatorio("cdEOTLD", form.getCdEOTLD(), errors);
			ValidatorUtil.verificaCampoObrigatorio("tpStatus", form.getTpStatus(), errors);
		}
		
	}

}
