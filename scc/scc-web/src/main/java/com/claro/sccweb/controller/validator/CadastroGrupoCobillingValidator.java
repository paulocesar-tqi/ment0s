package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroGrupoCobillingForm;

public class CadastroGrupoCobillingValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(CadastroGrupoCobillingForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		CadastroGrupoCobillingForm form = (CadastroGrupoCobillingForm)_form;
		
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE))){
			ValidatorUtil.verificaCampoObrigatorio("entity.noGrupo", form.getEntity().getNoGrupo(), errors);
								
		}


	}

}
