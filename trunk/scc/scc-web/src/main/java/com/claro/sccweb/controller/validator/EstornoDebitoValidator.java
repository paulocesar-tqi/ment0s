package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.admin.EstornoDebitoController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.EstornoDebitoForm;

public class EstornoDebitoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(EstornoDebitoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		EstornoDebitoForm form = (EstornoDebitoForm)arg;
		if (form.getOperacao() != null)
			{
			if (form.getOperacao().equals(EstornoDebitoController.OPERACAO_SALVAR))
				{
				ValidatorUtil.verificaCampoObrigatorio("ano", form.getAno(), errors);
				ValidatorUtil.verificaCampoObrigatorio("mes", form.getMes(), errors);
				}
			}
		
		
		
	}

	
	
}
