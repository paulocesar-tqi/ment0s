package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.BaseCRUDAndMethodController;
import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.CadastroAtividadeContabilForm;

public class CadastroAtividadeContabilValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CadastroAtividadeContabilForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		CadastroAtividadeContabilForm form = (CadastroAtividadeContabilForm)arg;
		
		if (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_CREATE) || (form.getOperacao().equals(BaseCRUDAndMethodController.OPERACAO_UPDATE)))
		{
			ValidatorUtil.verificaCampoObrigatorio("entity.dsAtividadeContabil", form.getEntity().getDsAtividadeContabil(), errors);
		}	
		
	}

	
	
}
