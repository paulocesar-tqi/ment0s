package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroContaContabilForm;

public class CadastroContaContabilValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroContaContabilForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroContaContabilForm form = (CadastroContaContabilForm)arg;
		
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE))){
			ValidatorUtil.verificaCampoObrigatorio("entity.cdConta", form.getEntity().getCdConta(), errors);
			ValidatorUtil.verificaCampoObrigatorio("entity.dsConta", form.getEntity().getDsConta(), errors);					
		}
		
	}

	
	
}
