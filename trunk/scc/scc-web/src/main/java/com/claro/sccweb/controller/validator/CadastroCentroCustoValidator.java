package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroCentroCustoForm;

public class CadastroCentroCustoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroCentroCustoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroCentroCustoForm form = (CadastroCentroCustoForm)arg;
		
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
		{
			ValidatorUtil.verificaCampoObrigatorio("entity.id.cdCentro", form.getEntity().getId().getCdCentro(), errors);
		}	
	}

	
	
}
