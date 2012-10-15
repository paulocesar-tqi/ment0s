package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.SccPreChamadasCreditoForm;

/**
 * @author 92038883
 *
 */

public class SccPreChamadasCreditoValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(SccPreChamadasCreditoForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		
		SccPreChamadasCreditoForm form = (SccPreChamadasCreditoForm)_form;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
		{
			ValidatorUtil.verificaCampoObrigatorio("dtInicioCredito", form.getDtInicioCredito(), errors);
			ValidatorUtil.verificaCampoObrigatorio("dtFimCredito", form.getDtFimCredito(), errors);
			ValidatorUtil.verificaPeriodo("dataFinal", form.getDtInicioCredito(), form.getDtFimCredito(), errors);	
		}
		
	}

}
